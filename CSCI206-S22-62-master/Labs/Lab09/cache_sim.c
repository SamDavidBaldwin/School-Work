#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>

int main(int argc, char* argv[])
{
  if (argc < 4){
    printf("Usage: %s <cache size> <block size> <associativity> <trace file>\n", argv[0]);
    printf("  all sizes are in bytes!\n");
    printf("  examples:\n");
    printf("    DM 4 kb with 8 byte blocks: %s 4096 8 1 <trace>\n", argv[0]);
    printf("    DM 16 kb with 16 byte blocks: %s 16384 16 1 <trace>\n", argv[0]);
    printf("    4-way 16 kb with 16 byte blocks: %s 16384 16 4 <trace>\n", argv[0]);
    exit(1);
  }

  FILE* f = fopen(argv[4], "r");
  if (f == NULL){
    printf("Could not open file %s!\n", argv[4]);
    perror("fatal error");
    exit(-1);
  }

  int cache_size = atoi(argv[1]);
  int block_size = atoi(argv[2]);
  int associativity = atoi(argv[3]);
  printf("Simulating %d-way associative %d kb cache with %d byte blocks on trace %s.\n",
    associativity, cache_size >> 10, block_size, argv[4]);

  // count of total cache accesses and number of hits
  // count - hits = misses
  long count = 0;
  long hits = 0;
  long cache[cache_size/associativity][associativity];
  
  for(int i = 0; i< cache_size/associativity; i++){
	for(int j = 0;j < associativity; j++){
	  cache[i][j] = -1;
  
    }
    
  }
   char* line = NULL;
   size_t length = 0;
   while(getline(&line, &length, f) != -1){
        char* token = strtok(line, " ,\t");
        int tokenNumber = 1;
        long key, tag;
        int index;
        while(token != NULL){
            if (tokenNumber == 2){
                key = strtol(token, NULL, 16);
                tag = key / cache_size;
                index = (key / block_size) % (cache_size/associativity);
                int indexToReplace = 0; 
                int cacheEdit = 0;
                for(int i = 0; i < associativity; i++){
                     if(cache[index][i] == -1){
                         cache[index][i] = tag;
                         count++;
                         cacheEdit = 1;
                         break;
                     }
                     if(cache[index][i] == tag){
                         cacheEdit = 1;
                         hits++;
                         count++;
                         break;
                     }
                }
                 //Not made an edit
                if(cacheEdit == 0){
                    cache[index][indexToReplace%associativity] = tag;
                    count++;
                    indexToReplace++;
                    if (indexToReplace >= cache_size)
                        indexToReplace = 0;
                }             
                int next_index = index + 1;
                int cacheEdit2 = 1;
                for (int j = 0; j < associativity; j++){
                     if (key%block_size != 0){
                         cacheEdit2 = 0;
                        if (next_index < (cache_size / block_size / associativity) - 1){
                            if (cache[next_index][j] == tag) {
                                hits++;
                                cacheEdit2 = 1;
                                break;
                            }
                            else {
                                cache[next_index][j] = tag;
								count++;
                                cacheEdit2 = 1;
                                break;
                            }
                            }
                        }
                }
                    //Not made an edit part 2
                if(cacheEdit2 == 0){
                    cache[next_index][indexToReplace%associativity] = tag;
                    indexToReplace++;
                    if (indexToReplace >= cache_size)
                        indexToReplace = 0;
                }
            }
            tokenNumber += 1;
            token = strtok(NULL, " ,\t");
        }
   }

  // finally print results, do not change the last 3 lines of output.
  printf("  Hits      %ld\n", hits);
  printf("  Misses    %ld\n", count-hits);
  printf("  Miss rate %f\n", (double)(count-hits)/(double)count);

  return 0;
}



