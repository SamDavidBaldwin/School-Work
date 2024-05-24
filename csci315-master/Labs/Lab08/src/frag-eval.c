#include <stdio.h> 
#include "allocator.h"
#include "dnode.h"
#include "dlist.h"
#include <stdlib.h>


int main(int argc, char *argv[]){
    unsigned int seed; 
    int requests;
    int algorithm;
    double avg_frag;
    if(argc != 4){
        printf("Usage: frag-eval [algorithm] [seed] [requests]");
        exit(-1);
    }
    algorithm = atoi(argv[1]);
    seed = atoi(argv[2]);
    requests = atoi(argv[3]);
    srandom(seed);
    
    int s = 0;
    int r = 0;
    void* p[requests];

    allocator_init(10240);
    while(r < requests){
        s = (random()%900) + 100 + 1;
        p[r] = allocate(algorithm,s);
        r++;
    }
    
    r = 0;
    while(r < requests){
        deallocate(p[r]);
        r++;
    }
    avg_frag = average_frag();
    printf("Average Fragmentation: %f\n", avg_frag);
}
