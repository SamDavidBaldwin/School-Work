.data
e: .byte 'e'
test: .asciz "eeeee"

.globl counte

.text

counte:
	#la a0, test
	li t6, 0		#Counting variable
	li a2, 'e'		#Loads e into a2
	addi sp, sp, -4 
	sw ra, 0(sp)
	jal loop
	lw ra, 0(sp)
	addi sp, sp, 4
	
	mv a0, t6
	jr ra
	
 
loop:
	lb t4, 0(a0)		#Loads the first element of the argument array into t4
	beq t4, a2, count	#Branch if t4 and t2 are equal
	
	addi a0, a0, 1		#Increments the location
	
	bne t4, zero, loop	#While t4 is not 0, reloop

	jr ra

count: 
	addi t6, t6, 1 
	addi a0, a0, 1
	j loop
  


