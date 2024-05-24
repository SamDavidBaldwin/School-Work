#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h> 
#include <sys/socket.h> 
#include <unistd.h>
#include <stdlib.h> 
#include <fcntl.h>

pid_t Fork(void);
int Pipe(int pipefd[2]);
pid_t Wait(int *wstatus);
pid_t Waitpid(pid_t pid, int *wstatus, int options);
int Open(const char *pathname, int flags, mode_t mode);
int Close(int fd);
int Read(int fd, void *buf, size_t count);
int Write(int fd, void *buf, size_t count);
int Connect(int sockfd, const struct sockaddr *addr, socklen_t addrlen);
int Bind(int sockfd, const struct sockaddr *addr, socklen_t addrlen);
int Listen(int sockfd, int backlog);
int Accept(int sockfd, struct sockaddr *addr, socklen_t *addrlen);

