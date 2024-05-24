#include <stdio.h>
#include <stdlib.h>

void nop(){
}

void my_function(int a, int b, int c) {
    char buffer1[5];    
    char buffer2[10];   
    int *ret;

    nop();

	ret = (int*)(buffer1 + 24);
    (*ret) = (*ret) + 10;
}

int main() {
   int x;
	
   x = 5;
   my_function(1,7,3);
   x = 4;
   printf("dec %d hex 0x%x\n", x, x);

   return x;
}
