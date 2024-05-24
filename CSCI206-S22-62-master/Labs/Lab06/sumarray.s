# CSCI 206 Computer Organization & Programming
# Date: 2011-09-13
# Revised: 2019-10-31 for RISC-V
#
# Copyright (c) 2011 Bucknell University
#
# Permission is hereby granted, free of charge, to any individual or
# institution obtaining a copy of this software and associated
# documentation files (the "Software"), to use, copy, modify, and
# distribute without restriction, provided that this copyright and
# permission notice is maintained, intact, in all copies and
# supporting
# documentation.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
# KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
# WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
# NONINFRINGEMENT. IN NO EVENT SHALL BUCKNELL UNIVERSITY BE LIABLE FOR ANY
# CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
# TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
# THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
#
# Student name:
#
# This program calculates the sum of the values in array A and counts
# the number of values in the array.
#
# Similarly to a C-string, the array is terminated by sentinel value
# equal to zero.
#
# The sum is stored in $s0 and the count in $s1

	.data
A:	.word 2,3,5,7,11,13,17,19,23,29,31,37,41,0# declare array int A[]={5,4,3,2,4,1,0};
sum: .asciz "Sum = "
num_elements: .asciz "Number of elements = "
nl: .asciz "\n"
    .globl main
	.text	
	
main:				# This symbols marks the first instruction of your program
	
	li s0, 0  	#This is the sum
	li s1, 0	#This is the number of elements 
	la t2, A 	#Stores the word A into the t2 register
	
	lw t3 0(t2)
	beqz t3, after
    	
loop:
	lw t3, 0(t2)	#Loads the first element of the array at address t2
	
  	add s0,s0,t3 	#Add the value of t2 at index t1 to t0
  	addi s1,s1, 1	#Increment the value of t1
  	addi t2, t2, 4	#Increment the pointer location of t2
    	bne zero, t3, loop#Loop while the element at index t1 in t2 is not equal to 0

	addi s1, s1, -1	
after:	

	la a0 num_elements #Loads the elements string to print
	li a7 4
	ecall		#print
	
			
	add a0, s1, zero	#Load the value of t1 to be printed
	li a7 1
	ecall		#print
	
	la a0 nl
	li a7 4
	ecall
	
	la a0 sum 	#Loads the sum string to print
	li a7 4
	ecall		#print
	
	add a0 ,s0, zero	#Load the value of t0 to be printed 
	li a7 1
	ecall		#print
		

			
	li	a7, 10		# system call for exit. 

	ecall			# Exit!
	ebreak
