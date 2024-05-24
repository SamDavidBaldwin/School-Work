import json
import logging 
import math 
import os.path
import socket 
import pickle
import sys
import time
from algs.utils import load_file
from algs.udp_wrapper import UdpWrapper
from algs.texcept import TransferFailed

from datetime import datetime, timedelta

log = logging.getLogger(__name__)

class ReliableDataTransferProtocol: 
    def __init__(self, retries=20):
        self.retries = retries
        self.timeout = timedelta(seconds=10)
        self.recv = False
    

    def run_server(self, outdir, addr, mtu):
        "run the server on the given addr/port/mtu, files are stored in outdir"
        # make sure directory exists
        os.makedirs(outdir, exist_ok=True)
        highest_packet = 0 
        # create the socket to listen on
        sock = UdpWrapper(addr)

        # use blocking on the server.
        sock.setblocking(True)

        # bind the socket to the (address, port)
        sock.bind(addr)
        in_xfr = False
        outfile = None
        last = datetime.now() - self.timeout

        output_list = [[None for i in range(5)]for j in range(5000)]
        log.info("Server started on {}".format(addr))
        while True:
            # wait for some data to arrive
            data,remote_addr = sock.recvfrom(mtu)

            if in_xfr and datetime.now() - last > self.timeout:
                # we got something but it's been too long, abort
                log.info("Abort transfer due to timeout.".format())
                in_xfr = False
                if outfile:
                    outfile.close()
                    outfile = None

            if in_xfr:
                # we are in a transfer, check for end of file
                if data[:9] == B"///END\\\\\\": 
                    missed = []
                    for i in range(highest_packet+1):
                        if output_list[i][0] != None:
                            outfile.write(output_list[i][0][0])
                    else: 
                        print(i)
                    
                    outfile.close()
                    output_list = [[None for i in range(5)]for j in range(5000)]
                    highest_packet = 0 
                    outfile = None
                    log.info("Done receiving file from {}.".format(
                            filepath, remote_addr))
                        # let the client know we are done (ack the END message)
                    sock.sendto(B"OKEND", remote_addr)
                    in_xfr = False
                 

                else:
                    # else we got a chunk of data
                    log.debug("Got a chunk!")
                    try:
                        data = pickle.loads(data)
                        if(sum(data[0]) == data[2] and sum(data[0])%997 == data[3] and sum(data[0])%5003 == data[4]):
                            if output_list[data[1]][0] == None:
                                output_list[data[1]][0] = data
                                sock.sendto(B'ACK', remote_addr)
                            else: 
                                sock.sendto(B'ACK', remote_addr)
                        else:
                            sock.sendto(B'NACK', remote_addr)
                    except Exception:
                        sock.sendto(B'NACK', remote_addr)
                    
                
                    if data[1] > highest_packet:
                        highest_packet = data[1]
                    
                    
            else:
                # we are not in a transfer, check for begin
                if data[:5] == B'BEGIN':    

                    # parse the message to get mtu and filename
                    smsg = data.decode('utf-8').split('\n')
                    beginmsg = smsg[0]
                    filename = smsg[1]
                    filepath = os.path.join(outdir, filename)

                    # check mtu
                    remote_mtu= int(beginmsg.split("/")[1])
                    if remote_mtu > mtu:
                        log.error("Cannot receive {} from {}, MTU({}) is too large.".format(
                            filepath, remote_addr, remote_mtu))
                        # send an error to the client
                        sock.sentdo(B'ERROR_MTU', remote_addr)
                    else:
                        log.info("Begin receiving file {} from {}.".format(
                            filepath, remote_addr))
                        outfile = open(filepath, 'wb')
                        in_xfr = True
                        # ack the begin message to the client
                        sock.sendto(B'OKBEGIN', remote_addr)
                else:
                    # we got something unexpected, ignore it.
                    log.info("Ignoring junk, not in xfer.")
                    pass
            last = datetime.now()
            
    def begin_xfr(self, dest, filename, mtu):

        sock = UdpWrapper(dest) #Create a socket to dest

        filename = os.path.basename(filename)

        sock.settimeout(.1)
        tries = 0

        while tries < self.retries: 
            msg = "BEGIN/{}\n{}".format(mtu, filename).encode('utf-8')

            sock.sendto(msg, dest)
            try: 
                data, addr = sock.recvfrom(mtu)
            except socket.timeout: 
                log.info("No response to BEGIN message, RETRY")
                tries += 1
                continue
            break
        if tries >= self.retries: 
            raise TransferFailed("No response to BEGIN message.")
        if data != B"OKBEGIN":
            raise TransferFailed("Bad BEGIN response from server, got {}".format(data))
        return sock
        

    def end_xfr(self, sock, dest, mtu):
        # send the END message
        tries = 0
        while tries < self.retries:
            # send the message
            sock.sendto(B"///END\\\\\\", dest)
            try:
                # wait for a response
                data, addr = sock.recvfrom(mtu)
            except socket.timeout:
                log.info("No response to END message, RETRY")
                tries += 1
                continue
            break
        finished = True
        if (tries >= self.retries):
            raise TransferFailed("No response to END message.")
        # if we got a response, make sure it's the right one.
        if data != B"OKEND" and data != B"ACK" and data != B"NACK":
            raise TransferFailed("Bad END response from server, got {}".format(
                data
            ))
 
    def xfr(self, sock, payload, dest, mtu):
        # send each chunk, waiting for an ACK
        for i,chunk in enumerate(payload):
            tries = 0
            log.info("Sending chunk {} of {}".format(i, len(payload)-1))
            while tries < self.retries:
                print("Tries {}".format(tries))
                data = None
                # send the chunk
                output = pickle.dumps(chunk)
                sock.sendto(output, dest)
                sock.sendto(output, dest)

                try:
                    data, addr = sock.recvfrom(mtu)
                    data, addr = sock.recvfrom(mtu)
                except socket.timeout:
                    log.info("No response to CHUNK message, RETRY")
                    tries += 1
                    continue

                if data == B"ACK":
                    print("Recieved an ACK for packet {}".format(i))
                    break

                elif data == B'NACK':
                    log.info("NACK recieved, RETRY")
                    tries + 1                    
                    continue

                else:
                    log.info("Bad response from server, got {} instead of ACK, RETRY".format(
                        data))
            if (tries >= self.retries):
                raise TransferFailed("No response to CHUNK message.")



    def chunk(self, payload, mtu):
        chunks = math.ceil(len(payload)/mtu)
        chunks *= 2
        hold = []
        for i in range(chunks):
            data = payload[i*(mtu//2):(i+1)*(mtu//2)]
            hold.append([data, i, sum(data), sum(data) % 997, sum(data) % 5003, sum(data) % 7])

        return hold, len(payload)
    

    def send_file(self, filename, dest, mtu):
        st = datetime.now() #Get the current datetime for reference 
        log.info("Sending with RDTP {} --> {}:{} [MTU == {}".format(filename, dest[0], dest[1], mtu))

        payload, total_bytes = self.chunk(load_file(filename), mtu)
        s = self.begin_xfr(dest, filename, mtu)

        self.xfr(s, payload, dest, mtu)
        self.end_xfr(s, dest, mtu)
     
        et = datetime.now()
        seconds = (et-st).total_seconds()
        log.info("Sent with RDTP {} in {} seconds = {:.0f} bps.".format(filename, seconds, total_bytes/seconds))



        return True

rtp = ReliableDataTransferProtocol()
