#include <stdio.h>
#include <pthread.h>
int OCTO = '#';
int NUMCOUNT = 0;
int CHARCOUNT = 0;
void *numCount(void *counter){
    int counterNum = *(int*)counter;
    while(1){
        counterNum+=1;
        printf("%d\n", counterNum%10);
        long int hold2 = 0;
        for(int i = 0; i < 10000000; i++){
            hold2 += i;
        }
}   
}


void *charCount(void *counter){
    int counterChar = *(int*)counter;
    while(1){
        counterChar += 1;
        printf("%c\n", counterChar%25+97);
        long int hold = 0;
        for(int i = 0; i < 10000000; i++){
            hold += i;
        }
}
}
void *octothorp(void *character){
    char octo = *(char*)character;
    while(1){
        printf("%c\n", octo);
        long int hold3 = 0;
        for(int i = 0;i < 10000000; i++){
            hold3 += i;
        }
}
}
int main(void){
     pthread_t numtid;
     pthread_t chartid;
     pthread_t octotid;
     pthread_create(&numtid, NULL, numCount, (void *)&NUMCOUNT);
     pthread_create(&chartid, NULL, charCount,(void *)&CHARCOUNT);
     pthread_create(&octotid, NULL, octothorp, (void *)&OCTO);
     
     pthread_join(numtid, NULL);
     pthread_join(chartid, NULL);
     pthread_join(octotid, NULL);
     return 0;
 }
 

