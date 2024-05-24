/*
 * Copyright (c) 2012 Bucknell University
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Modified from fortune-client.c by
 * Author: L. Felipe Perrone (perrone@bucknell.edu)
 *
 * Revised by Marchiori
 * 2022-04-22 - initial version based on fortune-client
 */

#include <stdio.h>
#include <stdlib.h>
#include "common-lib.h"

/*------------------------------------------------------------------------
 * Program:   fetch
 *
 * Purpose:   perform an HTTP/1.1 GET request and print the result to STDOUT.
 *
 * Syntax:    fetch [ host ] [ port ] [ path ]
 *
 *		 host  	- name of a computer on which server is executing
 *		 port  	- protocol port number server is using
 *		 path  	- the path & filename to request from the given host
 *
 * Example:

$ ./fetch eg.bucknell.edu 80 /~cs206/test
GET /~cs206/test HTTP/1.1
Host: eg.bucknell.edu
Connection: close


HTTP/1.1 200 OK
Date: Thu, 21 Apr 2022 18:37:42 GMT
Server: Apache/2.4.6 (CentOS) OpenSSL/1.0.2k-fips SVN/1.7.14
Last-Modified: Thu, 21 Apr 2022 18:24:59 GMT
ETag: "e-5dd2e3a4aa808"
Accept-Ranges: bytes
Content-Length: 14
Connection: close

Hello World!

 *
 *------------------------------------------------------------------------
 */
int main(int argc, char *argv[])
{
	// Check command-line arguments
	if (argc < 4)
	{
		printf("usage: %s [ host ] [ port ] [ path ]\n", argv[0]);
		exit(-1);
	}

	char *host = argv[1];
	int   port = atoi(argv[2]);
	char *path = argv[3];

	// open a TCP socket to the host:port
	int   sd = open_client_tcp_socket(host, port);

	/*
	  Build and send the request to the server;
	  Read back the response from the server;
	  Print it on the screen;
	*/
	char request[MAX_LENG+1];
    char buf[MAX_LENG+1];

    strcpy(request, "GET ");
    strncat(request, path, strlen(path));
    strcat(request, " HTTP/1.1\r\nHOST: ");
    strncat(request, host, strlen(host));
    strcat(request, "\r\nConnection: close\r\n\r\n");

    int bytes_sent = strlen(request);

    if(writen(sd, request, bytes_sent) != bytes_sent){
        error("write error");
    }

    readn(sd, buf, MAX_LENG);
    read_rest(sd);

    buf[MAX_LENG] = 0;
    char *token = strtok(buf, "\n");
    for(int i = 0; i < 9; i++){
        token = strtok(NULL, "\n");
    }

    printf("%s\n", token);


	// tell the OS we want to shutdown this socket
	shutdown(sd, SHUT_RDWR);

	// Close the socket.
	close(sd);

	// Terminate the client program gracefully.
	exit(EXIT_SUCCESS);
}
