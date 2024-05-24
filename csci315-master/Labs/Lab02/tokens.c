#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>
#include <readline/readline.h>
#include <readline/history.h>
#include <sys/wait.h>
#define BUFFER_SIZE 1024
#define READ_END 0 
#define WRITE_END 1
int Pipe(int fd[2]);
int Read(int fd, void *buf, size_t count);
int Write(int fd, const void *buf, size_t count);
int main(void){
    char write_msg[BUFFER_SIZE];
    char read_msg[BUFFER_SIZE];
    char fixed_write_msg[BUFFER_SIZE];
    int c_to_p[2];
    int p_to_c[2];
    pid_t pid;
    int flag = 0;
    Pipe(p_to_c);
    Pipe(c_to_p);
    pid = fork();
    int lengthToRead; 
    int i = 0; 
    if (pid < 0){
        fprintf(stderr, "Fork Failed\n");
        return 1;
    }
    if( pid > 0){
        //Parent

       while(flag == 0){
           char *input = readline("Enter an input:");
           strcpy(write_msg, input);
           i = strlen(write_msg);
           //Write the number of bits to read into the pipe
           Write(p_to_c[WRITE_END], (void *)&i, sizeof(int));
           //Write the string into the pipe
           Write(p_to_c[WRITE_END],write_msg, i);
           
           //Recieve Message From Child
            Read(c_to_p[READ_END],(void *)&lengthToRead,  sizeof(int));
            Read(c_to_p[READ_END], read_msg, lengthToRead);    
            read_msg[lengthToRead] = '\0';
            printf("Output: %s\n", read_msg);
       }

    }
    else{
        while(flag == 0){
            //Recieve the number of bytes to read
            Read(p_to_c[READ_END], (void *)&lengthToRead, sizeof(int));
            //Recieve the message
            Read(p_to_c[READ_END],read_msg, lengthToRead);
            //Return without extra spaces
            read_msg[lengthToRead]= '\0';
            char *token;  
            token = strtok(read_msg, " ");        
            while(token != NULL){
                strcat(fixed_write_msg, token);
                strcat(fixed_write_msg, " ");
                token = strtok(NULL, " ");
            }
            i = strlen(fixed_write_msg);
            fixed_write_msg[i] = '\0';
            Write(c_to_p[WRITE_END], (void *)&i, sizeof(int));
            Write(c_to_p[WRITE_END], fixed_write_msg, i);
            fixed_write_msg[0] = '\0'; 
        }
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

