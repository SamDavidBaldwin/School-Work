#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
extern char **environ;
void print_environment(void);
int main(int argc, char * argv[]){
    if(argc != 2){
        exit(-1);
    }
    print_environment();
    int pid, pid2;
    if((pid = fork()) == 0){
        execlp("/bin/cat", "/bin/cat", argv[1], (char*) NULL);
    }
   else
    {
        wait(&pid);
        if((pid2 = fork()) == 0){
            execlp("usr/bin/wc", "usr/bin/wc",argv[1], (char*) NULL);
        }
        wait(&pid2);
    }
    return 0;
}

void print_environment(void){
    int i = 0;
    while(environ[i] != NULL){
        printf("%s\n", environ[i]);
        i++;
    }
}

