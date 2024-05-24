
import socket
import sys
channel_list =[]
client_list = []
def check_commands(message,client_list, client_address, channel_list):
    check = message.split()
    if (check[0] == "/nick"):
        for element in client_list:
            if client_address in element and check[1] != None:
                element[0] = check[1]
    if (check[0] == "/list"):
        for element in client_list:
            if element[0] != None:
                response = "Name: Port -> " + str(element[0]) + ": " + str(element[1][1]) + "\n"
            else:
                response = "Name: Port -> None: " + str(element[1][1]) +"\n"
            s.sendto(response.encode(), client_address)
    if (check[0] == "/quit"):
        for element in client_list:
            if client_address in element:
                client_list.remove(element)
                response = "Connection Closed, press crtl+c to exit."
                s.sendto(response.encode(), client_address)
    if (check[0] == "/channel"):
	#This is for actually sending a message in a channel
	#Checks which user is sending a message
        for value in client_list:
            if client_address in value:
                name = value[0]
	#Formulates a response message in the form of [name][channel] message
        response = "[" + name + "][" + check[1] + "]:"
	#Adds the message part to the response
        for i in range(2, len(check)):
            response += " "+str(check[i])
        response += "\n"
	#Checks if the channel actually exists
        if check[1] in channel_list:
            for element in client_list:
		#Then checks every client within the client list if they have joined a specific channel, and if they have, send them a message
                if element[channel_list.index(check[1]) +2] == 1:
                    s.sendto(response.encode(), element[1])

def check_message(message, client_list, client_info):
    check = message.split()
    #If the message has the /msg tag
    if(check[0] == "/msg"):
	#Searches for the destination user
        for element in client_list:
            if check[1] in element:
		#Formats the message with the additional tag of (Private Message)
                response = "(Private Message) [" + str(client_info[0]) + "]:"
                for i in range(2,len(check)):
		    #Combines the rest of the message back into a single string to send to the recipient
                    response += " " + str(check[i])
                response += "\n"
                s.sendto(response.encode(),element[1])


def add_channels(message,client_list,client_info, channel_list):
    check = message.split()
    if(check[0] == "/join"):
        #Check if the channel eists. If it does, adjusts the client info to say they have joined the channel.
        if (check[1] in channel_list):
            client_info[channel_list.index(check[1]) + 2] = 1
        else:
	    #If the channel doesn't exist
            for element in client_list:
		#Checks if the user that is trying to join a channel has under 100 channels joined
                if client_address in element:
		    #If they are under 100 and the channel doesn't previously exist, add a column to the user data and set the specific users value to 1
                    if sum(element[2:]) < 100:
                        element.append(1)
                    else:
			#If they are over 100 channels doesn't let them join the channel
                        response = "This user has already joined the maximum number of channels\n"
                        s.sendto(response.encode(), element[1])
                else:
		    #After creating a channel, gives every user a field to denote their access to that channel
                    element.append(0)
	    #Adds the newly created channel to the channel list.
            channel_list.append(check[1])


s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
IP = socket.gethostbyname(socket.gethostname())
s.bind((IP, int(sys.argv[1])))

print("UDP chat server started on %s:%s" % (IP, int(sys.argv[1])))

while True:
    message, client_address = s.recvfrom(4096)
    in_list = 0
    #Check if this is a new client or one that is already within the client list
    for element in client_list:
        if client_address in element:
            in_list = 1
    #If this is a return client or there are still stots to connect
    if len(client_list) < 100 or in_list==1:
	#If this is a new client, append the information to the list
        if in_list ==0:
            client_info = [None, client_address]
            for i in range(len(channel_list)):
                client_info.append(0)
            client_list.append(client_info)
	#Otherwise, access the current client info from the list
        else:
            for element in client_list:
                if client_address in element:
                    client_info = element
	#Take message input
        message = message.decode()
        add_channels(message, client_list, client_info, channel_list)
        check_commands(message,client_list, client_address, channel_list)
        check_message(message, client_list, client_info)
	#If the message is a slash command, don't send it to other users
        if message[0] == '/':
            pass
        else:
	    #If the user doesn't have a name, do not append the name to the outgoing message
            if client_info[0] == None:
                response = message
            else:
		#If they do have a name, format the name for the outgoing message
                response = "[%s] %s" % (client_info[0], message)
            #Send the message to every client address within the client list except for the source address
            for element in client_list:
                if element[1] != client_address:
                    s.sendto(response.encode(), element[1])
    else:
	#This is the case in which there are currently over 100 members connected. Just sends a message to the attempted connection telling them to close the connection.
        response = "There are currently too many members connected to the chat server, try again later. Press ctrl+c to close the connection. "
        s.sendto(response.encode(), client_address)

s.close()
