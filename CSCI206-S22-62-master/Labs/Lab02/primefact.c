/**
 * Samuel Baldwin   
 * CSCI 206
 * Lab02 - primefact.c
 * compile with: make primefact
 * notes:
 * time python primefact.py : 
 * real: 3.429s
 * user: 3.394s
 * sys: .007s
 * time ./primefact -> Compiled from primefact.c
 * real: .063s
 * user: .062s
 * sys: .001s
 *
 * Comparison:
 * C is roughly (3.429/.064) or 53.58 times faster than python
 */
#include <stdio.h> 

int main (void)
{
    int n = 2147483645;

    int i = 2;

    while(i<= n){
        if (n%i == 0){
            printf("%d\n",i);
            n = n/i;
        }
        else{
            i++;
        }
    }
    return 0;
}

