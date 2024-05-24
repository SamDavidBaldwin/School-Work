#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define BUFFER_SIZE 25
#define READ_END 0 
#define WRITE_END 1
int Pipe(int fd[2]);
int Read(int fd, void *buf, size_t count);
int Write(int fd, const void *buf, size_t count);
int main(void){
    char write_msg[BUFFER_SIZE] = "Greetings";
    char read_msg[BUFFER_SIZE];
    int fd[2];
    pid_t pid;

    Pipe(fd);

    pid = fork();
    if (pid < 0){
        fprintf(stderr, "Fork Failed\n");
        return 1;
    }
    if( pid > 0){
        close(fd[READ_END]);
        int iterator = 0;
        while(write_msg[iterator] != '\0'){
            Write(fd[WRITE_END], &write_msg[iterator], 1);
            iterator++;
        }
        close(fd[WRITE_END]);
    }
    else{
        close(fd[WRITE_END]);
        int i=0;
        printf("read: " );
        while(Read(fd[READ_END], &read_msg[i], 1) > 0){
            printf("%c", read_msg[i]);
            i++;
        }
        printf("\n");
        close(fd[READ_END]);
    }
    return 0;
}

int Pipe(int pipefd[2]){
    
    if(pipe(pipefd) == -1){
        perror("-1 is returned and the global variable errno is set to indicate the error");
        exit(-1);
    }
    return 0;
}

int Read(int fd, void *buf, size_t count){
    int hold = read(fd, buf, count); 
    if(hold == -1){
        perror("There was an error in Reading");
        exit(-1);
    }
    return hold;

}
int Write(int fd, const void *buf, size_t count){
    int hold = write(fd, buf, count);
    if(hold == -1){
        perror("There was an error in Writing");
        exit(-1);
    }
    return hold;
}

