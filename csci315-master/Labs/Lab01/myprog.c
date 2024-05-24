#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <stdbool.h>
int main(int argc, char * argv[]){
    int counter = 0;
    int child_a, child_b;
    child_a = fork();
    if(child_a == 0){
        while(1){
            printf("Child a %d\n", counter);
            counter ++;
        }
    }
    else{
        child_b = fork();
        if(child_b == 0){
            while(1){
            printf("Child b %d\n", counter);
            counter ++;
            }
        }
        else{
            while(1){
            printf("Parent %d\n", counter);
            counter++;
            }
        }
    }
    return 0;
}

