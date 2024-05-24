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
   int hourly_wage = 20;

   printf("Annual salary is: ");
   printf("%d", hourly_wage * 40 * 50);
   printf("\n");

   return 0;
}
