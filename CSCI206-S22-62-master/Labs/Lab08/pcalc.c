#include <stdio.h>
#include "stack.h"
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#include <ctype.h>
bool is_operator(char* o);
float evaluate(float operand1, float operand2, char operator);
bool is_operand(char* o);


int main(int argc, char* argv[]){
    struct stack* theStack = malloc(sizeof(struct stack));
    for(int i = 1; i < argc; i++){
        char* input = argv[i];
        if(is_operator(input)){
            float operand1;
            stack_pop(theStack, &operand1, 4);
            float operand2;
            stack_pop(theStack, &operand2, 4);
            float result = evaluate(operand1, operand2, *input);
            stack_push(theStack, &result, 4);    
        }
        else if(is_operand(input)){
            
            float f; 
            sscanf(input, "%f", &f);
            stack_push(theStack, &f, 4);
        }
        
    }
    while(!stack_isempty(theStack)){
        float result;
        stack_pop(theStack, &result, 4);
        printf("%f\n", result);
    }
    stack_destroy(theStack);
    return 0;
}

bool is_operator(char * o){
    return strstr("+-/x^", o);
}

bool is_operand(char* o){
    for(int i = 0;  o[i] != '\0'; i++){
        char c = (o[i]);
        if(!((c>=48 && c<=57) || c == '.')){
            return false;
        }
    }
    return true;
}

float evaluate(float operand1, float operand2, char operator){
    if(operator=='+'){
        return operand1 + operand2;
    }
    else if(operator == 'x'){
        return operand1 * operand2;
    }
    else if(operator == '/'){
        return operand2 / operand1;
    }
    else if(operator == '-'){
        return operand2 - operand1;

    }else if(operator == '^'){
        return pow(operand2, operand1);
    }
    return 0; 
}
