import struct
import math
import sys
LX = [0] 
RX = [0] 
def print_trace(filename):
     # compute the table width
    wd = 2*12+10+9
    wd -= len(filename)
    wd -= 2 # spaces
 
     # print the table name
    print(math.floor(wd/2)*"*",
             filename,
             "*"*math.ceil(wd/2))
    print("{:>5} | {:>5} | {:>12} | {:>12} | {:>5}".format(
            "SWID",
            "LnkID",
            "  DESTMAC   ",
        "   SRCMAC   ",
          "OutLnk   " ))

    print("------|-------|--------------|-------------")
    with open(filename, 'rb') as f:
            switch_table = [[0]] 
            raw = f.read(14)
            hold = 0

            while raw:
                sw_id, link_id = struct.unpack("BB", raw[0:2])
                #destmac = raw[2:8]

                # struct.unpack doesn't handle 6-byte integers, so we need to
                # pad the first two bytes with 0 to make it 8 bytes

                #MAC destination address
                destmac = struct.unpack("!Q",  b"\x00\x00" + raw[2:8])[0]
                #MAC source Address 
                srcmac = struct.unpack("!Q",  b"\x00\x00" + raw[8:14])[0]
                outlink = int(link_id)
                if (link_id > len(switch_table)-1):
                     for i in range(link_id - (len(switch_table)-1)):
                        switch_table.append([0])
                        LX.append(hold)
                        RX.append(0)

                #Index switch table using MAC destination address
                if switch_table[link_id] == [0]: 
                    switch_table[link_id] = [srcmac]
                else: 
                    if srcmac not in switch_table[link_id]:
                        switch_table[link_id] += [srcmac]
                RX[link_id] += 1
                #If entry found for destination then
                if any(destmac in sublist for sublist in switch_table): 
                    #If destination on segment from shich frame arrived
                    for i in range(len(switch_table)):
                        if destmac in switch_table[i]: 
                            location = i
                            break
                    
                    if location == link_id: 
                        #Then drop frame
                        outlink = "DROP"
                    else: #Forward frame on interface indicated by entry
                        outlink = i
                        LX[outlink] += 1
                else: 
                    #Forward packet on all interfaces
                    outlink = "ff"
                    for i in range(len(LX)): 
                        LX[i] += 1
                    hold += 1
                    LX[link_id] -= 1
                        
                    
            # read next packet
        
                print("{:5x} | {:5x} | {:012x} | {:012x} | {:2}".format(
                    sw_id,
                    link_id,
                    destmac,
                    srcmac, 
                    outlink,
                    
                ))
            
                # read next packet
                raw = f.read(14)

if __name__=="__main__":
    for f in sys.argv[1:]:
            if f.endswith(".out"):
                print_trace(f)
    print("\nSummary: \n")
    print("{:>5} | {:>14} | {:>12} ".format(
            "Link",
            "RX",
            "  LX   " ))
    print("------|----------------|-----------")
    for i in range(len(LX)):
            print("{:5x} | {:14} | {:2} ".format(
                    i,
                    str(RX[i]) + " ("  + str(round(RX[i]/sum(RX)*100,2)) + "%)",
                    str(LX[i]) + " ("  + str(round(LX[i]/sum(LX)*100,2)) + "%)",
                ))
