/*
 * CSCI 206 Computer Organization & Programming
 * Author: Alan Marchiori
 * Date: 2014-03-01
 * Updated for C: 2020-03-24
 * Copyright (c) 2014 Bucknell University
 *
 * Permission is hereby granted, free of charge, to any individual or
 * institution obtaining a copy of this software and associated
 * on files (the "Software"), to use, copy, modify, and
 * distribute without restriction, provided that this copyright and
 * permission notice is maintained, intact, in all copies and supporting
 * documentation.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL BUCKNELL UNIVERSITY BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
#include <math.h>
float u2f(unsigned d);
unsigned f2u(float f);
double u2d(unsigned long long d);
unsigned long long d2u(double d);
float next_float(float f);
void inspect_float(float f);
void inspect_double(double d);
bool is_near(float f, float g, float epsilon);

float one_half_single(void){
  /*
  This function creates and returns a float with the value 0.5
  You cannot just "return 0.5;"!

  The 32-bit value 0x3f000000 is the value of 0.5 in IEEE 754.

  If we use an array to access the bytes, we have to return
  it in reverse order. Since the array is stored in little-endian
  byte order, the most significant bits of the float are in index [3]!
  +-------+-------+-------+-------+
  |  [0]  |  [1]  |  [2]  |  [3]  | Memory byte offset
  +-------+-------+-------+-------+
  |  7-0  |  15-8 | 23-16 | 31-24 | Bits (IEEE single)
  +-------+-------+-------+-------+
  */
  unsigned char val[4] = {0, 0, 0, 0x3f};
  return *(float*)val;
}
double one_half_double(void){
  /*
  Use an approach similar to one_half_single but now return
  the double precision IEEE 754 value for 0.5 (8 bytes).
  */
    // 1 sign bit 
    // 11 exponent bits
    // 52 fraction bits
    // 0x3FE0000000000000
    unsigned char val[8] = {0,0,0,0,0,0,0xE0,0x3f};
    return *(double*)val;
}

int main()
{
    float a = 0.1;
    float sum = 0;
    int i;
    for(i = 0; i< 1000; i++){
        sum += a;
    }
    printf("a = %1.23f, sum = %1.23f, sum is_near 100 ==> %s\n", a, sum, is_near(sum, 100, .1)? "TRUE":"FALSE"); 
    //The reason that == is not entirely accurate when working with floats, is because in the case where the float cannot be perfectly represented with IEEE, and there is a remainder
    //that float will be rounded in order for it to fit into memory. This rounding ultimately makes it so when adding two floats together, if either of those floats has an infinite
    //portion, you aren't actually adding those exact two floats together but rather a close approximation of the floats together. Given that == checks if the two arguments are 
    //EXACTLY the same, this rounding results in the output being false when conventional logic would dictate it should be true. In this case, 0.1 is a decimal that cannot be cleanly, 
    //and non infinitely represented, and as such the decmial that the computer is actually storing is a rounded approximation that can be saved within the computers memory that is very 
    //slightly less that 0.1. Therfore when this approximation is added 1000 times by the loop, the actual result is slightly different than the expected result. 
    inspect_float(a);
    inspect_float(sum);
    inspect_float(100-sum);
    return 0;
}
bool is_near(float f, float g, float epsilon){
    return(fabs(f-g)<epsilon);
}
void inspect_float(float f)
{
    unsigned q = f2u(f);   
         
    //Moves the bits to the right until only the sign bit remains       
    int sign = (q >> 31);
    //Moves the bits to the right until only the exponent and sign bit remain, then uses a mask to get the exponent
    int exp = ((q >> 23)&255);
    //Moves the bits to the left to clear the exponent and sign bit, then moves them back to the initial positions
    int frac = ((q << 9)>>9);

    printf("Inspecting single % e, sign = %d, biased exponent = 0x%x, fraction = 0x%06x\n",f, sign, exp, frac);
         
}
                                                  
void inspect_double(double f)             
{   

    unsigned long long q = d2u(f);
         
    //Moves the bits to the right until only the sign bit remains        
    int sign = (q>>63);      
    //Moves the bits to the right until only the exponent and sign bit remain, then uses a mask to get the exponent
    int exp = ((q>>52)&2047);     
    //Moves the bits to the left to clear the exponent and sign bit, then moves them back to the initial position
    unsigned long long frac = ((q<<12)>>12);
    
    printf("Inspecting double % le, sign = %d, biased exponent = 0x%x, fraction = 0x%013llx\n", f, sign, exp, frac);
}

unsigned f2u(float f){
    union {
        unsigned u;          
        float f;     
    } v;
    v.f = f;      
    return v.u;
}

float u2f(unsigned d){  
    union {
        unsigned u;
        float f;
    } v;
    v.u = d;
    return v.f;
}

unsigned long long d2u(double d){
    union{
        unsigned long long u;
        double d;
    } v;
    v.d = d;
    return v.u;
}

double u2d(unsigned long long f){
    union{
        unsigned long long u;
        double d; 
    } v;
    v.u = f;
    return v.d;
}

float next_float(float f){
    //Given
    return u2f(f2u(f) + 1);
}

