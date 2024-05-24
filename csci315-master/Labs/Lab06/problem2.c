#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <pthread.h>
void napping(int time);
pthread_mutex_t chopsticks[5];

void* Philosopher(void* ID){
    long long pid = (long long) ID;
    long long right = (pid+1)%5;
    long long left = pid;
    while(true){
    printf("Philosopher %lld is thinking.\n", pid);
    napping(2);
    printf("Philosopher %lld is hungry.\n", pid);
    
    printf("Philosopher %lld is picking up chopstick %lld\n", pid, left);
    pthread_mutex_lock(&chopsticks[left]);
    napping(3);
    printf("Philosopher %lld is picking up chopstick %lld\n", pid, right);
    pthread_mutex_lock(&chopsticks[right]);
    
    printf("Philosopher %lld is starting to eat.\n", pid);
    napping(1);
    printf("Philosopher %lld is done eating.\n", pid);
    
    printf("Philosopher %lld is putting down chopstick %lld\n", pid, left);
    pthread_mutex_unlock(&chopsticks[left]);
    printf("Philosopher %lld is putting down chopstick %lld\n", pid, right);
    pthread_mutex_unlock(&chopsticks[(pid+1)%5]);
    }
    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
    pthread_t Philosophers[5];
    int thread;
    int chopstick;
    for(int i = 0; i < 5; i++){
        chopstick = pthread_mutex_init(&chopsticks[i], NULL);
        if(chopstick){
            perror("Error in creating mutex");
            exit(-1);
        }

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
     printf("Napping for %f. seconds.\n", t);
     sleep(t);
     usleep(t-time+1*100000);

}

