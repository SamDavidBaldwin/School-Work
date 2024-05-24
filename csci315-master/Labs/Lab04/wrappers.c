#include "wrappers.h"
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h> 
#include <sys/socket.h> 
#include <unistd.h>
#include <stdlib.h> 
#include <fcntl.h>


pid_t Fork(void){
    pid_t pid;
    pid = fork();
    if(pid  < 0){
        perror("Forking failed");
        return -1;
    }
    return pid;
}
int Pipe(int pipefd[2]){
    int hold; 
    hold = pipe(pipefd);
    if (hold < 0){
        perror("Pipe failed");
        return -1;
    }
    return hold;
}

pid_t Wait(int *wstatus){
    pid_t hold ; 
    if((hold = wait(wstatus)) < 0){
        perror("Error in waiting");
        return -1;
    }
    return hold;
}

pid_t Waitpid(pid_t pid, int *wstatus, int options){
    pid_t returnPID;
    if((returnPID = waitpid(pid, wstatus, options)) < 0){
        perror("Error in waitPID call");
        return -1;
    }
    return returnPID;
}
int Open(const char *pathname, int flags, mode_t mode){
    int hold;
    if ((hold = open(pathname, flags, mode)) < 0){
        perror("Error in open call");
        return -1;
    }
    return hold;
}
int Close(int fd){
    int hold;
    if((hold = close(fd)) <0){
        perror("Error in close call");
        return -1;
    }
    return hold;
}
int Read(int fd, void *buf, size_t count){
    int hold;
    if((hold = read(fd, buf, count)) < 0){
        perror("Error in read call");
        return -1;
    }
    return hold;
}
int Write(int fd, void *buf, size_t count){
    int hold;
    if((hold = write(fd,buf,count))< 0){
        perror("Error in write call\n");
        return -1;
    }
    return hold;
}
int Connect(int sockfd, const struct sockaddr *addr, socklen_t addrlen){
    int hold; 
    if((hold = connect(sockfd, addr, addrlen)) < 0){
        perror("Error in connect call\n");
        return -1;
    }
    return hold;
}
int Bind(int sockfd, const struct sockaddr *addr, socklen_t addrlen){
    int hold;
    if ((hold = bind(sockfd, addr, addrlen))< 0){
        perror("Error in bind call\n");
        return -1;
    }
    return hold;
}
int Listen(int sockfd, int backlog){
    int hold;
    if((hold = listen(sockfd, backlog)) < 0){
        perror("Error in listen call\n");
        return -1;
    }
    return hold;
}
int Accept(int sockfd, struct sockaddr *addr, socklen_t *addrlen){
    int hold;
    if((hold = accept(sockfd, addr, addrlen)) < 0){
        perror("Error in accept call\n");
        return -1;
    }
    return hold;
}


