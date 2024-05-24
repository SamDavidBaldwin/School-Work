#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>
#include <unistd.h>
int main(int argc, char * argv[]){
    char * stringTest = (char *) malloc(256 * sizeof(char));
    strcpy(stringTest, "This is a test string");
    int pid; 
    if((pid = fork())==0){
        ///Child Program
        printf("%s\n", stringTest);
    }
    else{
        //Parent Program
        printf("%s\n", stringTest);
    }
    return 0;
}

