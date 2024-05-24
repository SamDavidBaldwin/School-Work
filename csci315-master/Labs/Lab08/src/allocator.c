#include <limits.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h>
#include "dlist.h"
#include "dnode.h"
#include "allocator.h"
size_t smallest = INT_MAX;
size_t largest = INT_MIN;

int allocator_init(size_t size){
    void *space = malloc(size);
    if(space == NULL){
        return -1;
    }
    free_list = dlist_create();
    allocated_list = dlist_create();
    dlist_add_front(free_list, space, size);
    return 0;
}

void *allocate(int type, size_t size){
    void *location;
    if(type == 0){
        location = first_allocate(size);
    }
    else if(type == 1){
        location = worst_allocate(size);
    }
    else if(type == 2){
        location = best_allocate(size);
    }
    else{
    return NULL;
    }
    return location;

    
}

void* best_allocate(size_t size){
    struct dnode *iter = free_list->front;
    struct dnode *location = dnode_create();
    dnode_setdata(location, NULL, 0);

    while (iter != NULL) {
        if ((iter->size < smallest) && (iter->size >= size)) {
            smallest = iter->size;
            location = iter;
        }
        iter = iter->next;
    }

    if (smallest != INT_MAX) {
        dlist_add_back(allocated_list, location->data, size);
        void *hold = location->data;
        location->size -= size;

        if (location->size == 0) {
            dlist_find_remove(free_list, location->data);
        } else {
            location->data += size;
        }
        return hold;
    }

    return NULL;
}

void* worst_allocate(size_t size){
    struct dnode *n = free_list->front;
    struct dnode *worst = dnode_create();
    dnode_setdata(worst, NULL, 0);
    size_t worst_size = 0;

    while (n != NULL) {
        if ((n->size > worst_size) && (n->size >= size)) {
            worst_size = n->size;
            worst = n;
        }
        n = n->next;
    }

    if (worst_size != 0) {
        dlist_add_back(allocated_list, worst->data, size);
        void *ret_ptr = worst->data;
        worst->size -= size;

        if (worst->size == 0) {
            dlist_find_remove(free_list, worst->data);
        } else {
            worst->data = (void *)((intptr_t)(worst->data) + size);
        }
        return ret_ptr;
    }

    return NULL;

}
void * first_allocate(size_t size) {
 struct dnode *iter = free_list->front;
    while (iter != NULL) {
        if (iter->size >= size) {
            dlist_add_back(allocated_list, iter->data, size);
            void *location =iter->data;
            iter->size -= size;
            
            if (iter->size == 0) {
                dlist_find_remove(free_list, iter->data);
            } else {
                iter->data += size;
            }
            return location;
        }
        iter = iter->next;
    }

    return NULL;

}

int deallocate(void *ptr){
    struct dnode *iter = allocated_list->front;

    while((iter != NULL) && (iter->data != ptr)) {
        iter = iter->next;
    }

    if (iter == NULL) {
        return -1;
    }

    dlist_add_back(free_list, iter->data, iter->size);
    dlist_find_remove(allocated_list, iter->data);
    return 0;

}

double average_frag(){
    struct dnode *iter = free_list->front;
    size_t total_size=0;
    int node_count=0;

    while(iter != NULL){
        total_size += iter->size;
        node_count++;
        iter= iter->next;
    }
    printf("%lud total size, %d node_count", total_size, node_count);
    double average = (total_size/node_count);
    return average;
}

