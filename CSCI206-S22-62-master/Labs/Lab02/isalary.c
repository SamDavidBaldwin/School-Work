/**
 * Samuel Baldwin
 * CSCI 206
 * lab 02 - salary.c
 * compile with: make salary
 * notes: none
 */
#include <stdio.h>

int main(void) 
{
    //Initial value
   int hourly_wage = 20;
    
   printf("Enter hourly wage (integer): ");
    
   scanf("%d", &hourly_wage);

   printf("Annual salary is: %d", hourly_wage * 40 * 50);
   printf("\n");

   return 0;
}
