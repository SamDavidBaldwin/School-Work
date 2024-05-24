#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.h"
#include "node.h"
struct stack *stack_create(){
    struct stack* new_stack=malloc(sizeof(struct stack));
    new_stack->top = NULL;
    return new_stack;
}

int stack_isempty(struct stack *s){
    if(s->top == NULL){
        return 1;
    }
    return 0;
}

void stack_destroy(struct stack *s){
    struct node* n = s->top;
    struct node* np;
    free(s);
    while(n != NULL){
        np = n;
        n = n->next;
        node_destroy(np);

    }
}

void stack_push(struct stack* s, void* src, int size){
    struct node* new_node = node_create(src, size);
    new_node->next = s->top;
    s->top  = new_node;
}

void stack_pop(struct stack* s, void* dest, int size){
    if(stack_isempty(s) == 0){
        struct node* newTop = (s->top)->next;

        memcpy(dest, s->top->data, size);
        
        node_destroy(s->top);;
        s->top = newTop;
}
}
