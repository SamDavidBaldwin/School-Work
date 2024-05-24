#include <stdio.h>
#include <stdlib.h>

int main(int argc, char * argv[]){
    if(argc < 4){
        exit(0);
    }
    else{
        char  arg1 = *argv[1];
        int arg2 = atoi(argv[2]);
        float arg3 = atof(argv[3]);
        char *arg4 = argv[4];
        printf("Arg1: %c, Arg2: %i, Arg3: %.3f, Arg4: %s\n", arg1, arg2, arg3, arg4);
    }
    return 0;
}
