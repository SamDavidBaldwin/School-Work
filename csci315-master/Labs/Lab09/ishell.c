#include <stdlib.h>
#include <stdio.h>
#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <readline/readline.h>
#include <readline/history.h>


int main(int argc, char *argv[]) {
	char *input; 
	char *prev_str = NULL;
	char **prev_cmds = NULL;
	char **arr_cmds;
	pid_t pid;
    int hold = 0;

    int status;
	while (1) {
		input = readline("ishell> ");
		if(input[0] == '\0'){
            input = readline("ishell>");
        }
        if(input[0] == '\0'){
            hold = 1;
        }
        else{
        int k = 0;
        char *token;
        token = strtok(input, " ");
        arr_cmds = malloc(sizeof(char *)*1024);
        while(token){
            if(token[strlen(token)-1] == ';'){
                token[strlen(token)-1] = '\0';
            }
            arr_cmds[k] = token;
            k++;
            token = strtok(NULL, " ");
        }
		if (strcmp(arr_cmds[0], "^]]A") == 0){
			if(prev_cmds != NULL){
                arr_cmds = prev_cmds;
			    printf("prev command(s): %s\n", prev_str);
		    }
            else{
                printf("There were no previous commands to reference\n");
            }
        }
        else {
			prev_cmds = arr_cmds;
			prev_str = input;
		}	
        }
			if ((pid =fork())== 0) {
                if(hold ==1){
                    char* arglist[] = {"ls", "-l", NULL};
                    execvp(arglist[0], arglist);
                }
                else{execvp(arr_cmds[0], arr_cmds);
                }hold = 0;
			} else {
				wait(&status);
			    if (status == 0){
                    printf("[ishell: program terminated successfully]\n");
                }else{
                    printf("[ishell: program terminated successfully][%d]\n", status);
                }
            }
	}
}
//The extra feature that I added was being able to access the previous command run in the terminal. This works for a single command back in history but could possibly be done for further back commands with another implementation. The way that the previous command is accessed is by utilizing the up arrow value or "^]]A" and hitting enter which will run the previous command and say what the previous command was. If there was no previous command it will not return a command. 



