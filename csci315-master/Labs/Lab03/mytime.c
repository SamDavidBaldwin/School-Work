#include <stdio.h>
#include <sys/time.h>
#include <time.h>
int main(void){
    struct timeval current_time;
    gettimeofday(&current_time, NULL);
    printf("The current time is: %s\n", ctime(&current_time.tv_sec));
    return 0;
}
