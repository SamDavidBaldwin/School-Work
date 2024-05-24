#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <pthread.h>
void napping(int timie);

void* Philosopher(void* ID){
    long long pid = (long long) ID;
    while(true){
    printf("Philosopher %lld is thinking.\n", pid);
    napping(2);
    printf("Philosopher %lld is hungry.\n", pid);
    printf("Philosopher %lld is starting to eat.\n", pid);
    napping(1);
    printf("Philosopher %lld is done eating.\n", pid);
    }
    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
    pthread_t Philosophers[5];
    int thread;

    for(int i = 0; i < 5; i++){
        thread = pthread_create(&Philosophers[i], NULL, Philosopher, (void*)(long long)i);
        if(thread){
            perror("Error in creating threads");
            exit(-1);
        }
    }
    for(int i = 0; i < 5; i++){
        thread = pthread_join(Philosophers[i], NULL);
        if(thread){
            perror("Error in joining threads");
            exit(-1);
        }
    }
}
void napping(int time){
    unsigned int seed = pthread_self();
    double t = ((double)rand_r(&seed)/RAND_MAX+ time)-1;
    printf("Napping for %f seconds.\n", t);
    sleep(t);
    usleep(t-time+1*100000);
}
