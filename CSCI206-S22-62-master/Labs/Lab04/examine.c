#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <ctype.h>
#include <unistd.h>
#include <time.h>

int main (int argc, char * argv[]){
        FILE* fp;
        if(argc > 0){
        fp = fopen(argv[1], "r");
        if(fp == NULL){
            perror("fopen failed");
            return 1;       
        }
        
        struct stat buf;
        stat(argv[1], &buf);
        printf("st_dev:\t\t%ld\n", buf.st_dev);
        printf("st_ino:\t\t%ld\n", buf.st_ino);
        printf("st_mode:\t%o\n", buf.st_mode);
        printf("st_nlink:\t%ld\n", buf.st_nlink);
        printf("st_uid:\t\t%o\n", buf.st_uid);
        printf("st_gid:\t\t%o\n", buf.st_gid);
        printf("st_rdev:\t%ld\n", buf.st_rdev);
        printf("st_size:\t%ld\n", buf.st_size);
        printf("st_blksize:\t%ld\n", buf.st_blksize);
        printf("st_blocks:\t%ld\n", buf.st_blocks);
        printf("st_atime: %s",ctime(&buf.st_atime));
        printf("st_mtime: %s", ctime(&buf.st_mtime));
        printf("st_ctime: %s", ctime(&buf.st_ctime));
                                                                                                                                                                
        return 0;
        }
}

