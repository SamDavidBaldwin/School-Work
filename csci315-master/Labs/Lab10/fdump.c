#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hexdump.c"

int main(int argc, char**argv){
    if(argc != 4){
        printf("Correct usage: [filename] [offset] [size]\n");
        return -1;
    }
    char filename[256];
    unsigned int offset;
    unsigned int size;
    long temp;
    FILE *fp;

    strcpy(filename, argv[1]);
    temp = atol(argv[2]);
    offset = (unsigned int)temp;
    temp = atol(argv[3]);
    size = (unsigned int)temp;

    char buf[size+1];

    fp = fopen(filename, "r");
    fseek(fp, offset, SEEK_SET);
    if(fgets(buf, size, fp)!=NULL){
        puts(buf);
    }

    hexdump(buf, size);
    printf("\n");
    fclose(fp);

    return 0;
}
