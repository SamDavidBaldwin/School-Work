/*
 * File Name: cal.c
 * Compile with: gcc -std=c99 cal.c -o cal
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


bool isint(char *);
bool isop(char *);

int main(int argc, char * argv[]){
    if(argc < 2){
        printf("./calc <operation> <arguments...>\n");
        return 0;
    }
    if(isop(argv[1])&&strcmp(argv[1], "len")!=0){
        if(argc >= 4){
             if(strcmp(argv[1],"add")==0){
                for(int i = 2; i<argc; i++){
                    if(!isint(argv[i])){
                        printf("Invalid operands\n");
                        return 1;
                    }
                }
                    int hold = 0;
                    for(int i = 2; i<argc; i++){
                        hold += atoi(argv[i]);
                    }
                    printf("%d\n", hold);
                }
                else if(strcmp(argv[1], "div")==0){
                    for(int i = 2; i<argc; i++){
                        if(atoi(argv[i]) ==0){
                            printf("Divide by zero\n");
                            return 1;
                        }
                    }
                    int hold =atoi(argv[2]);
                    for(int i = 3; i<argc; i++){
                        hold /= atoi(argv[i]);
                    }
                        printf("%d\n",hold);
                    }
                else if(strcmp(argv[1],"mult")==0){
                    int hold = 1;
                    for(int i = 2; i<argc; i++){
                        hold *= atoi(argv[i]);
                    }
                    printf("%d\n", hold);
                }
        
        }
        else{
            printf("More arguments are needed\n");
    }
    }
    if (strcmp(argv[1], "len")==0){
            int hold =0;
            for(int i = 2; i<argc; i++){
                hold += strlen(argv[i]);
            }
            printf("%d\n", hold);
            hold =0;
        }

    return 0;
}



bool isint(char * s){
    for (int i =0; i<strlen(s); i++){
    if((s[i]> '9')||(s[i] <'0')){
        return false;
        }
    }
    return true;
}

bool isop(char * s){
    if((strcmp(s, "add")==0)||(strcmp(s,"len")==0)||(strcmp(s,"div")==0)||(strcmp(s,"mult")==0)){
        return true;
    }
    else{
        printf("Unsupported function, try: add, mult, div, or len\n");
        return false;
    }
}

