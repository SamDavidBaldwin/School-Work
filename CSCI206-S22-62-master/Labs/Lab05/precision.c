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
float u2f(unsigned d);
unsigned f2u(float f);
double u2d(unsigned long long d);
unsigned long long d2u(double d);
float next_float(float f);
void inspect_float(float f);
void inspect_double(double d);

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
    float f = 7.753e9;
    inspect_float(f);
    printf("next_float(%1.23f) == %1.23f\n", f, next_float(f));
    //The value of the next float after 1.0 is 1.00000011920928955078125 
    //Given there is a low level limitation to the number of bits that store a float, at some point, increasing the value of the float by one bit increases the value by more than other cases
    //In the case where next_float causes the mantissa to overflows, given that incrementing the bits at the next point would require more memory space than would initially be allocated, it most likely
    //would result in one of three possible cases. Either 1)Nothing happens and teh value is set to the maximum possible value, 2)The system overflows and returns an error, 3) The number loops back to 0x0000000000000000
    //Fundamentally logic would therefore dictate that the closer to 0 that a given floating point number was, the better the precision of the representation of that number will be. 
    inspect_float(next_float(f));
    return 0;
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

