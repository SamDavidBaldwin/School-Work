#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <semaphore.h>

int main(int argc, char *argv[]) {
	if (argc != 2) {
		printf("Follow this format: ./sender message\n");
		exit(-1);
	}
	char msg[1000];
	strcpy(msg, argv[1]);

	FILE *f = fopen("pipe.txt", "a+");

	sem_t *receive_sem = sem_open("receive sem", O_CREAT, 0777, 1);
	sem_t *send_sem = sem_open("send sem", O_CREAT, 0777, 0); 

	sem_wait(receive_sem);
	fprintf(f, "%s\n", msg);
	sem_post(send_sem);
	sem_post(receive_sem);

	fclose(f);
}

