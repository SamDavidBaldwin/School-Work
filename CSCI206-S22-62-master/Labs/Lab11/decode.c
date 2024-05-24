/*
 * CSCI 206 Computer Organization & Programming
 * Author: L. Felipe Perrone
 * Date: 2014-03-17
 * Copyright (c) 2011 Bucknell University
 *
 * Permission is hereby granted, free of charge, to any individual or
 * institution obtaining a copy of this software and associated
 * documentation files (the "Software"), to use, copy, modify, and
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

/* 
 * Exercize in decoding the stack
 *
 * Derived from:
 * EX7-17, from http://www.buildingsecuresoftware.com
 *
 * Revised for RISC-V
 * Xiannong Meng
 * 2022-03-13
 */

#include <stdio.h>

unsigned char *p;

// function prototype for main, so the symbol can be used in test()
int main();

// print the address of a few items
// even though it can pass up to 8 parameters through 
// registers, RISC V compiler stores the these parameters
// into stack anyways.
void test(int a, int b, int c, int d, int i, int j) {

	// declaration of local buffer; buf resides on the stack
	unsigned char buf[12];

	// declaration of local variable used in for loop
	int k;

	// print the address of function main, which contains a call to test
	printf("&main = %p\n", &main);

	// print the address of variable , a formal parameter of test
	printf("&d = %p\n", &d);

	// print the address of variable i, a formal parameter of test
	printf("&i = %p\n", &i);

	// print the address of variable j, a formal parameter of test
	printf("&j = %p\n", &j);

	// print the address of automatic variable k
	printf("&k = %p\n", &k);

	for (k=0; k<12; k++) {
		printf("&buf[%d] = %p\n", k, &buf[k]);
	}

	printf("\n---------------- Memory dump starts\n");
	for (p = (unsigned char*) &k - 32; p < (unsigned char*) &i + 32; p++)
		printf("%p: 0x%02x\n", p, *(unsigned char *)p);
	printf("---------------- Memory dump ends\n");
}

int main()
{
	test(1, 2, 3, 4, 1039, 12);
	
	printf("Decoding a stack frame\n");
	printf("======================\n\n");
	printf("size of an integer in bytes = %ld\n", sizeof(int));
	printf("size of a pointer in bytes = %ld\n\n", sizeof(p));

	printf("&p = %p\n\n", &p);

	// exit program, going back to the OS as normal termination
	return 0;
}
