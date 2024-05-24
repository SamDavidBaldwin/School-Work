#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#define BUFFER_SIZE 25
#define READ_END 0 
#define WRITE_END 1
int Pipe(int fd[2]);
int Read(int fd, void *buf, size_t count);
int Write(int fd, const void *buf, size_t count);
int main(void){
    char write_msg[BUFFER_SIZE] = "Greetings";
    char read_msg[BUFFER_SIZE];
    char upper_write_msg[BUFFER_SIZE];
    char upper_read_msg[BUFFER_SIZE];
    int c_to_p[2];
    int p_to_c[2];

    pid_t pid;

    Pipe(p_to_c);
    Pipe(c_to_p);

    pid = fork();
    if (pid < 0){
        fprintf(stderr, "Fork Failed\n");
        return 1;
    }
    if( pid > 0){
        //Parent
        close(p_to_c[READ_END]);
        int iterator = 0;
        while(write_msg[iterator] != '\0'){
            Write(p_to_c[WRITE_END], &write_msg[iterator], 1);
            iterator++;
        }
        close(p_to_c[WRITE_END]);
        close(c_to_p[WRITE_END]);
        iterator = 0;
        printf("Uppercase read: ");
        while(Read(c_to_p[READ_END], &upper_read_msg[iterator], 1) > 0){
            printf("%c", upper_read_msg[iterator]);
            iterator++;
        }
        printf("\n");
        close(c_to_p[READ_END]);
    }
    else{
        //Child
        close(p_to_c[WRITE_END]);
        int i=0;
        printf("read: " );
        while(Read(p_to_c[READ_END], &read_msg[i], 1) > 0){
            printf("%c", read_msg[i]);
            i++;
        }
        read_msg[i] = '\0';
        printf("\n");
        close(p_to_c[READ_END]);
        close(c_to_p[READ_END]);
        for(int j = 0; j < i; j ++){
            upper_write_msg[j] = toupper(read_msg[j]);
        }
        i = 0;
        while(upper_write_msg[i] != '\0'){
            Write(c_to_p[WRITE_END], &upper_write_msg[i], 1);
            i++;
        }
        close(c_to_p[WRITE_END]);
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

