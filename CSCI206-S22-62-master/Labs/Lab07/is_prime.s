.text
.globl is_prime

	#a0 is the argument
 
is_prime:	
	#li a0, 25	
	li t2, 2		#Incremented variable
	li t6, 1
	beq a0, t6, yes 
	li t6, 2
	beq a0, t6, no
	
	addi sp, sp, -4
	sw ra, 0(sp)
	jal loop
	lw ra, 0(sp)
	addi sp, sp, 4
	jr ra
	

loop:
	div t4, a0, t2		#Divides a0 by t2, and stores the remainder in t4 
	mul t3, t2, t4		#Multiplies t2 by t4, and stores that value in t3
	beq t3, a0, yes		#If the product in t3 is equal to the original number, it is prime
	
	addi t2,t2,1
	beq t2, a0, no		#Branches to yes if s2 reaches a0
	
	j loop
yes:	
	li a0, 0
	jr ra
	
no:
	li a0, 1
	jr ra


