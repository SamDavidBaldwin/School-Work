/*
 * File name: cmd_args.c
 * Compile with: gcc -std=c99 cmd_args.c -o cmd_args
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
bool isinteger(char *);
int  my_isvalid(char *);

int main(int argc, char * argv[]) {
    if (sizeof(argv) < 3){
        exit(0);
    }
    my_isvalid(argv[1]);
    for(int i = 2; i< argc; i++){
        if(isinteger(argv[i])){
            printf("%s is an integer\n", argv[i]);
        }
        else{
         printf("%s is not an integer\n",argv[i]);
        }
    }
    return 0;
}

int my_isvalid(char * s){
    if((strcmp(s, "add")==0)||(strcmp(s,"sub")==0)||(strcmp(s,"mult")==0)||(strcmp(s,"div")==0)){
        printf("\"%s\" is a valid command\n", s);
    }
    else{
        printf("\"%s\" is an invalid command\n",s);
        }
    return 0;
}

bool isinteger(char * c){
    for(int i = 0;i< strlen(c);i++){
        if(c[i] >57 || c[i] < 49){
            return false;
        }
    }
    return true;
}
