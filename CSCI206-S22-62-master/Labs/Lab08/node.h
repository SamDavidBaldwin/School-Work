#ifndef NODE_H_
#define NODE_H_
struct node{
    void* data;
    int length;
    struct node *next;
};
#endif
struct node *node_create(void *data, int length);
void node_destroy(struct node *n);
