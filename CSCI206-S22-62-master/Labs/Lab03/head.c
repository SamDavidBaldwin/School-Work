/*
 * File name head.c
 * Compile with make head
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <ctype.h>
#include <unistd.h>

int read_file_lines(FILE*, char (*)[], int);
int MAXLINES = 100;
int MAXBYTES = 255;

int main(int argc, char * argv[]){
    FILE* fp;
    int lines;

    char buffer[MAXLINES][MAXBYTES+1];
    if(argc ==0||argc > 3){
        printf("Wrong number of args");
        return 1;
    }

    if(argc > 2){ //If there is a lines argument provided, sets the number of lines to read with that argument 
        lines = atoi(argv[2]);
    }
    else{
        lines = 10;
    }

     fp = fopen(argv[1],"r");
     if(fp == NULL){ //checks if it is actually a file 
         perror("fopen failed");
         return 1;
     }
    int lines_read; //The number of lines actually read by read_file_lines
    lines_read =  read_file_lines(fp,buffer,lines);
    for(int i = 0; i < lines_read; i++){
        printf("%s",buffer[i]); //print the lines in the buffer
    }
    fclose(fp); //close the file
    return 0;

}

int read_file_lines(FILE* fp, char buffer[MAXLINES][MAXBYTES+1],  int lines_expected){
    
    int num_lines_read = 0; //checks the number of lines read 
    
    for( int i = 0; i< lines_expected; i++){
        fgets(buffer[i],MAXBYTES, fp); //sets the buffer at the given index to the line of the file
        num_lines_read++;
    
    }
    return num_lines_read;
}


