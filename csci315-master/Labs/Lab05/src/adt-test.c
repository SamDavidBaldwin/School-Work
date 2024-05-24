#include "circular-list.h"
#include <stdio.h>
struct circular_list cl;

int main(){
    circular_list_create(&cl, 5);

    circular_list_insert(&cl, 1);
    circular_list_insert(&cl, 2);
    circular_list_insert(&cl, 3);
    for(int i= 0; i < cl.size; i++){
        printf("%f\n", cl.buffer[i]);
    }
    printf("start is %d, end is %d, elems is %d\n", cl.start, cl.end, cl.elems);
    printf("Should be 0, 2, 3\n");
    double rm = 2.0;
    circular_list_remove(&cl, &rm);
    for(int i= 0; i < cl.size; i++){
         printf("%f\n", cl.buffer[i]);
    }   
    printf("start is %d, end is %d, elems is %d\n", cl.start, cl.end, cl.       elems);
    printf("Should be 1, 1, 2\n");
    return 0;
}
