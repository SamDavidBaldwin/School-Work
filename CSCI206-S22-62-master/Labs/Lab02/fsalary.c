/**
 * CSCI 206
 * lab 02 - fsalary.c
 * compile with: make fsalary
 * notes: none
 */
#include <stdio.h>

int main(void) 
{
    //Initial value
   float hourly_wage = 20;

   int weeks_worked = 0;
    
   printf("Enter hourly wage (float): ");
   scanf("%f", &hourly_wage);

   printf("Enter weeks worked (integer): ");
   scanf("%d", &weeks_worked);

   printf("Annual salary is: $%.2f\n", hourly_wage * 40 * weeks_worked);
   

   return 0;
}
