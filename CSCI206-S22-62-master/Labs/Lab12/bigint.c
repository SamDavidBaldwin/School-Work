#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "bigint.h"

/*
 * Create an initalize a new bigint structure
 *
  struct bigint {
    int N;
    char digit[];
  };
  
*/
struct bigint* bigint_init(char* value, int N){
  int i;
  int k = strlen(value);
  struct bigint* r;

  // ensure N is divisible by 4
  if (N%4 > 0){
    N += (4-(N%4));
  }
  if (k > N){
    printf("ERROR: too many digits\n");
    exit(1);
  }

  r = malloc(sizeof(struct bigint) + N);
  r->N = N;
  bigint_zero(r);

  // copy digits from the C string value into the digits
  for(i = 0; i < k; i++){
      r->digit[k-i-1] = (int)value[i];   
  }
  return r;
}

/*
 * set every digit to 0
 */
void bigint_zero(struct bigint* bn){
  memset(bn->digit, '0', bn->N);
}

/*
 * Copy the binary digit string into dst.
 */
void bigint_str(char* dst, struct bigint* bn){
  int i;
  for(i = bn->N; i > 0; i--){
    *dst++ = bn->digit[i-1];
    if ( (i-1)%4 == 0){
      *dst++ = ' ';
    }
  }
  *dst = '\0';
}

/*
 * todo, incomplete [optional?]
 */
void bigint_hexstr(char* dst, struct bigint* bn){
  *dst= '\0';
}

/* 
 * a standard full adder.
 * this might be useful.
 */
void full_add(char carry_in, char a, char b, char* sum, char* carry_out){
  int i =0;
  if (a == '1') i++;
  if (b == '1') i++;
  if (carry_in == '1') i++;
    switch(i){
    case 0: *sum = '0'; *carry_out = '0'; break;
    case 1: *sum = '1'; *carry_out = '0'; break;
    case 2: *sum = '0'; *carry_out = '1'; break;
    case 3: *sum = '1'; *carry_out = '1'; break;
    default: printf("Impossible error!\n"); exit(255);
  }
}

/*
 * c = a + b
 */
void bigint_add(struct bigint* c, struct bigint* a, struct bigint* b){
    char carry = '0';
    char *carryout;
    carryout = &carry;
    char *sum;
    char hold;
    sum = &hold;
    for(int i = 0; i < b->N; i++){
        full_add(carry,a->digit[i],b->digit[i],sum, carryout);
        c->digit[i] =*sum;
        carry = *carryout;
    }
}

/*
 * out = -in
 */
void bigint_inv(struct bigint* out, struct bigint* in){
    for(int i = 0; i < out->N; i++){
        if(in->digit[i] == '0'){
            out->digit[i] = '1';
        }
        else{
            out->digit[i] = '0';
        }
    }
}

/*
 * c = a - b
 */
void bigint_sub(struct bigint* c, struct bigint* a, struct bigint* b){
    struct bigint *switched = bigint_init("0", b->N);
    bigint_inv(switched, b);
    struct bigint *increment = bigint_init("0001", b->N);
    bigint_add(b, switched, increment);
    bigint_add(c, a, b);
}

/*
 * Right shift the bigint, in place by *bits*
 */
void bigint_rshift(struct bigint *bn, int bits){
    for(int i = 0; i < bn->N - bits; i++){
        bn->digit[i] = bn->digit[i+bits];
    }
    for(int j = bn->N - bits; j < bn->N; j++){
        bn->digit[j] = '0';
    }
}

/*
 * Left shift the bigint, in place by *bits*
 */
void bigint_lshift(struct bigint *bn, int bits){
    for(int i= bn->N; i > bits; i--){
        bn->digit[i] = bn->digit[i-bits];
    }
    for(int j = 0; j < bits; j++){
        bn->digit[j] = '0';
    }
}

/*
 * prod = mcand * mplier
 */
void bigint_mult(
  struct bigint* prod,
  struct bigint* mcand,
  struct bigint* mplier){
    for(int i  = 0; i < mcand->N; i++){
        if(mcand->digit[i] == '1'){
            bigint_add(prod, mplier, prod);
            bigint_lshift(mplier, 1);
        }
        else{
            bigint_lshift(mplier, 1);
        }
    }
}

/*
 * quotient = dividend / divisor
 * remainder = dividend % divisor
 */
void bigint_div(
  struct bigint* quotient,
  struct bigint* remainder,
  struct bigint* dividend,
  struct bigint* divisor){

}

/*
 * return zero if *in* is zero, else 1
 */
int bigint_iszero(struct bigint* in){
  return 1;
}

/* 
 * return a new copy of *in* 
 */
struct bigint* bigint_copy(struct bigint* in){
    return NULL;
}

/*
 * result = in! (factorial)
 */
void bigint_fact(
  struct bigint* result,
  struct bigint* in){
}
