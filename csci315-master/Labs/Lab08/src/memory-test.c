#include <stdio.h> 
#include "allocator.h"
#include "dnode.h"
#include "dlist.h"



int main(int argc, char *argv[]){

    printf("Create an allocator:\n");
    allocator_init(5000);
    printf("Initial free_list:\n");
    dlist_print(free_list);
    printf("Initial allocated_list:\n");    
    dlist_print(allocated_list);
    


    printf("Testing first allocate\n");
    void *first = allocate(0,100);
    void *second = allocate(0, 200);
    void *third = allocate(0, 300);
    
    dlist_print(free_list);
    dlist_print(allocated_list);

    printf("Testing deallocate\n");
    int hold = deallocate(first);
    printf("Deallocating first, %d\n", hold);
    deallocate(second);
    deallocate(third);
    dlist_print(free_list);
    dlist_print(allocated_list);

    printf("Testing best-allocate:\n");
    void *fourth = allocate(2, 50);
    dlist_print(free_list);
    dlist_print(allocated_list);

    printf("Testing worst_allocate:\n");
    void *fifth = allocate(1, 10);
    dlist_print(free_list);
    dlist_print(allocated_list);
    deallocate(fourth);
    deallocate(fifth);
}
