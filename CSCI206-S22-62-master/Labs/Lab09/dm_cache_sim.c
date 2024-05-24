#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>

int main(int argc, char* argv[])
{
  if (argc < 3){
    printf("Usage: %s <cache size> <block size> <trace file>\n", argv[0]);
    printf("  all sizes are in bytes!\n");
    printf("  examples:\n");
    printf("    DM 4 kb with 8 byte blocks: %s 4096 8 <trace>\n", argv[0]);
    printf("    DM 16 kb with 16 byte blocks: %s 16384 16 <trace>\n", argv[0]);
    exit(1);
  }

  FILE* f = fopen(argv[3], "r");
  if (f == NULL){
    printf("Could not open file %s!\n", argv[3]);
    perror("fatal error");
    exit(-1);
  }

  int cache_size = atoi(argv[1]);
  int block_size = atoi(argv[2]);

  printf("Simulating a direct-mapped %d kb cache with %d byte blocks on trace %s.\n", cache_size >> 10, block_size, argv[3]);

  // count of total cache accesses and number of hits
  // count - hits = misses
  long count = 0;
  long hits = 0;

  long int cache[cache_size];
  for(int i = 0; i< cache_size; i++){
      cache[i] = -1;
  } 
  
  char hold[16];
  char temp[8];

  long int key= 0;
  while(fgets(hold, 16, f)){
      for(int i = 0; i <= 8; i++){
        temp[i] = hold[i+2];
    }
    key = strtol(temp, NULL, 16); //8 character string
    long int index = key/block_size % cache_size;//Index in the cache
    long int tag = key / cache_size; //Tag value at that index
  //  printf("address: %ld, index: %ld, tag: %ld\n", key, index, tag);
    if(cache[index] == -1 || cache[index] != tag){
        cache[index] = tag;
    }
    else{
        hits++;
    }
    if(key%block_size != 0){
        if(index + 1 < cache_size / block_size -1){
            if(cache[index + 1] == tag){
                hits++;
            }
            else{
                cache[index + 1] = tag;
            }
    
        count++;
        }}
    count++;
  
  }




  // finally print results, do not change the last 3 lines of output.
  printf("  Hits      %ld\n", hits);
  printf("  Misses    %ld\n", count-hits);
  printf("  Miss rate %f\n", (double)(count-hits)/(double)count);

  return 0;
}
