.text
.globl check_primes

	#a0 is the argument
 
check_primes:	

	add t0, zero, a2

	
number_loop:
	addi t1, zero, 2 
	j number_check
	
after_checking:	
	sw t1, 0(t0)
	addi t0, t0, 4
	addi a0, a0, 1
	beq a0, a1, done
	j number_loop
	
number_check:
	div t2, a0, t1
	mul t2, t2, t1
	beq t2, a0, not_prime
	addi t1, t1, 1
	beq t1, a0, is_prime
	j number_check
	
is_prime:
	li t1, 1
	j after_checking
	
not_prime:
	li t1, 0 
	j after_checking
	
done:
	jr ra


