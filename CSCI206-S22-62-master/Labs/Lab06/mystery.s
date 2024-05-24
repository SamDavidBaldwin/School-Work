
# CSCI 206 Computer Organization & Programming
# Date: 2019-10-26
# Copyright (c) 2019 Bucknell University
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
# Student name: Samuel Baldwin


	.data
	
unused:	.word 0xFEEDBEEF			
						
x:	.word 89				
						
	.text	
main:					# This tells the simulator
					# where start your program

	
	add	t0, zero, zero		#Set the value of the x5 register to 0 + 0
	add	s2, zero, zero		#Set the value of the x18 register to 0 + 0

#This loop basically iterates while the t0 register has a value < 10, and each time it loops, it adds the value in t0 to s2
#This ultimately results in adding together the numbers between 1 and 10, 
#s2 has a value of 55 when the loop is exited 
#A c pseudo code representation might be:
#value = 0;
#while(i < 11){
	#value += i;
#}s
loop:
	add	s2, s2, t0		#Sets the value in the s2 register to the value in the s2 register plus the value in the t0 regisster
	addi	t0, t0, 1		#Increments the value of the t0 register by 1
	addi	t3, t0, -10		#Sets the value in t3 to the value 0f t0-10
	blez	t3, loop		#Branch if the value of t3 iss less than or equal to 0	

	la	t2, x			#la loads the address of x into the register t2
	sw	s2, 0(t2)		#stores the word in the s2 register that is held in the s2 register 
	
	add	a0, zero, s2	# Move result into a0 for return code
	li	a7, 93			# Code (93) for system call Exit2
	ecall				# Exit!
	ebreak				# Break if something went wrong (should not get here!)

