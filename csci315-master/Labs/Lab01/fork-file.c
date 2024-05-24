/*
 * CSCI 315 Operating Systems Design
 * Date: 2014-09-02
 * Copyright (c) 2014 Bucknell University
 *
 * Permission is hereby granted, free of charge, to any individual or
 * institution obtaining a copy of this software and associated
 * documentation files (the "Software"), to use, copy, modify, and
 * distribute without restriction, provided that this copyright and
 * permission notice is maintained, intact, in all copies and supporting
 * documentation.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL BUCKNELL UNIVERSITY BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/*
 * Revised by Xiannong Meng
 * 2017-08-23
 * added
 * #include <unistd.h>
 * #include <sys/types.h>
 * #include <sys/wait.h>
 */
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
int i = 7;
double x = 3.14;
pid_t Fork(void);
int main(int argc, char* argv[]) {

  int pid;
  double y = 2.18;

  char buf_parent[6];
  char buf_child[6]; 
  int status; // exit status to be filled by wait

  // create and open a file called data.txt
  // use open (file descriptors)
    int fptr;
    fptr = open("data.txt", O_CREAT|O_WRONLY, S_IRWXU);
    
    char inputString[] = "this is a test for processes created with fork\nthis is another line";
    write(fptr, inputString, strlen(inputString));
    close(fptr);
    
    fptr = open("data.txt",O_CREAT|O_RDONLY, S_IRWXU);

  if ((pid = Fork()) == 0) {
    // child process
    
    // read 5 characters from file into buf_child
    read(fptr, buf_child, sizeof(buf_child)-1);
    printf("The characters read from data.txt are: %s\n", buf_child);
    // print the characters in buf_child to terminal
    printf("pid= %d -- initially, child sees x= %lf, y=%lf\n", pid, x, y);
    x = 0;
    y = 0;
    printf("pid= %d -- child sees x= %lf, y=%lf\n", pid, x, y);
    printf("child is terminating\n");

    // close the file
    close(fptr);
    exit(0);

  } else {

    // parent process

    // read 5 characters from file into buf_parent
    // print the characters in buf_parent to terminal
    int termCharLocation =read(fptr, buf_parent, 5);
    buf_parent[termCharLocation] = '\0';
    printf("The characters read from data.txt are: %s\n", buf_parent);
    printf("pid= %d -- parent waits for child to terminate\n", pid);
    printf("pid= %d -- before wait parent sees x= %lf, y=%lf\n", pid, x, y);

    wait(&status); // note we are not catching the return value of wait!
    printf("parent got termination status= %d from child\n", status);
    printf("pid= %d -- after wait sees x= %lf, y=%lf\n", pid, x, y);

    // read another 5 characters from file into buf_parent
    // print the characters in buf_parent to terminal

    // close the file
    close(fptr);
    printf("parent is terminating\n");
    exit(0);
  }

}
pid_t Fork(void){
    pid_t returnValue = fork();
    if(returnValue == -1){
        perror("There was an error in forking");
        exit(-1);
    }
    return returnValue;
}
    
