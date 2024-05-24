#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

int main(int argc, char **argv){
    if(argc != 2){
        printf("incorrect number of arguments\n");
        exit(0);
    }
    int i, len, temp;
    char *outputstr = (char *) malloc(argc * sizeof(char));
    for(int j = argc-1; j > 0; j--){
        len = strlen(argv[j]);
        for(i = 0; i < len/2;i++){
            temp = argv[j][i];
            argv[j][i] = argv[j][len-i-1];
            argv[j][len-i-1] = temp;
        }
        strcat(outputstr, argv[j]);
        strcat(outputstr," ");
    }
    printf("reversed: %s\n", outputstr);
    return 0;
}
