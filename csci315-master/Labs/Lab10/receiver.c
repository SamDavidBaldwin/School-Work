#include <stdio.h>

#include <fcntl.h>

#include <string.h>

#include <stdbool.h>

#include <semaphore.h>



#define MAX_LENGTH 255

#define CHANNEL "channel.txt"



int main(int argc, char* argv[]){

    sem_t *rec, *message;



    char message_str[MAX_LENGTH];

    FILE *fd;



    fd = fopen(CHANNEL, "rb");



    sem_unlink("rec"); sem_unlink("message");



    rec = sem_open("rec", O_CREAT, 0777, 1);

    message = sem_open("message", O_CREAT, 0777, 0);

    

    while(1){

        sem_wait(message);

        sem_wait(rec);



        if(fgets(message_str, sizeof(message_str), fd) != NULL){

            printf("receiver [msg arrival]: %s\n", message_str);

        }

        sem_post(rec);

    }

}


