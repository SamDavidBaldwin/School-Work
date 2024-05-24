#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "node.h"
struct node *node_create(void *data, int length){
    struct node* node_new;
    node_new = (struct node *)malloc(sizeof(struct node));
    node_new->data = (void*)malloc(length+1);
    memcpy(node_new->data, data,length);
    node_new->length = length;
    node_new->next = NULL;
    return node_new;
}
void node_destroy(struct node *n){
    free(n->data);
    free(n);
}
