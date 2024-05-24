import argparse
import socket
import struct
import time
import random

def icmp_socket(timeout = 10.0, ttl = 30):
    "Create a raw socket for ICMP messages"
    s = socket.socket(socket.AF_INET, socket.SOCK_RAW, socket.getprotobyname("icmp"))
	# Set the timeout
    s.settimeout(timeout)
	# Set the TTL
    s.setsockopt(socket.SOL_IP, socket.IP_TTL, ttl)
    return s

def checksum(data):
	"Calculate the checksum for the data"
	sum = 0
	# make 16 bit words out of every two adjacent 8 bit words in the packet
	for i in range(0, len(data), 2):
		if i + 1 < len(data):
			sum += (data[i] << 8) + data[i+1]
		else:
			sum += data[i] << 8
	# take only 16 bits out of the 32 bit sum and add up the carries
	sum = (sum >> 16) + (sum & 0xffff)
	sum += (sum >> 16)
	
 	# one's complement
	return ~sum & 0xffff


def ping(host, skt = None, seqno = 1, ttl = 30, quiet = False):
    ident = random.randint(0, 0xffff)
    header = struct.pack("!BBHHH", 8, 0, 0, ident, seqno)
    send_time_ns = int (1e9 * time.time())
    payload = struct.pack ("!Q", send_time_ns)

    packet = header + payload
    
    c = checksum(packet)
    header = struct.pack("!BBHHH", 8, 0, c, ident, seqno)
    packet = header + payload


    # Set the TTL
    skt.setsockopt(socket.SOL_IP, socket.IP_TTL, ttl)
    skt.sendto(packet, (host, 0)) 
    try:
        resp, addr = skt.recvfrom(1024)
    except: 
        print("Request Timed Out")
        return False
    

    # parse the ICMP header received.
    icmp_type, icmp_code, icmp_checksum, icmp_id, icmp_seq = \
        struct.unpack("!BBHHH", resp[20:28])

    
    if icmp_type == 0: 
        print(host + " reached in " + str(ttl) + " hops")
        return False
    try: 
        hostname = str(socket.gethostbyaddr(str(addr[0]))[0])
    except: 
        hostname = str(addr[0])
    print(" " + str(ttl) + "  " + hostname + "(" + str(addr[0]) + ")")


    return True

if __name__=="__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("host", help="Host to ping")
    parser.add_argument("-t", "--ttl", help="Time to live", default=30, type=int)
    parser.add_argument('-n', '--num', help="Number of pings", default=3, type=int)
    args = parser.parse_args()
    s = icmp_socket()
    print("traceroute to " + args.host + " (" + str(socket.gethostbyname(args.host)) + ") " + str(args.ttl)  + " hops max, 16 byte packets" )
    try:
        i = 0
        valid = True
        while i < args.ttl and valid == True: 
            i += 1
            valid  = ping(args.host, seqno = i, skt = s, ttl= i)
    except KeyboardInterrupt:
        print("^C")
    finally:
        s.close()
