
The file can be run with python3 chat\_server.py {port}
Connections can be done in terminal nc -u IP {port}

After the first message, a connection from the terminal is established and can be accessed. 

/list will list all connections that have sent at least one message to the server, and hasn't quit. 

/nick {alias} will set a specific connections name. 

/join {channelName} will join a specific process to a channel. Messages can then be sent to that channel by /channel channelName {message}

/msg {alias} {message} will send a private message to the specified alias. Right now, an alias must be set to do private messaging. 

/quit will remove a user from the client list and will stop all outgoing messages to that specific client. 
