#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[])
{
  if (argc != 2){
    printf("Usage: %s <trace file>\n", argv[0]);
    exit(1);
  }

  FILE* f = fopen(argv[1], "r");
  if (f==NULL){
    printf("Could not open file %s!\n", argv[1]);
    perror("fatal error");
    exit(-1);
  }

  int i_bytes = 0;    // I type access
  int s_bytes = 0;    // S and M type access
  int l_bytes = 0;    // L type access

  char line[256];
  while(fgets(line,256 , f) != 0){  
      int i = 0;
      if(line[0] == 73){
            while(line[i] != 44){
                i++;
            }
            int hold = line[i+1];
            i_bytes += hold-48;
      }
      else if(line[1] == 83){
          while(line[i] != 44){
              i++;
          }
          int hold = line[i+1];
          s_bytes += hold-48;

      }
      else{
          while(line[i] != 44){
              i++;
          }
          int hold = line[i+1];
          l_bytes += hold-48;
      }
  }
  printf("%d, %d, %d\n", i_bytes, l_bytes, s_bytes);
  return 0;
}
