.text
.globl add_second

add_second:
	
	lw t1, 0(a0)
	lw t2, 4(a0)
	lw t3, 8(a0)
	
	li t4, 60
	
	addi t1,t1, 1		#Increment seconds
check_hr:
	beq t1, t4, incre_min	#If seconds = 60
check_min:
	beq t2, t4, incre_hour	#If hours = 60
	
	sw t1, 0(a0)		#Put seconds back in
	sw t2, 4(a0)		#Put minutes back in
	sw t3, 8(a0)		#Put hours back in

	jr ra
	
incre_min:
	li t1, 0
	addi t2, t2, 1
	j check_hr
	
	
	
incre_hour:
	li t2, 0
	addi t3, t3, 1
	j check_min
	