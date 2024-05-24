.text

    addi  a0, zero, 4
    jal   double         # a0 = result

    addi  a0, zero, 15
    jal   double
        
    li    a7, 10         # call for exit
    ecall
    
double:
	# a0 - parameter to be doubled
	# a0 - the squared value to return
   	addi sp, sp, -4		#Allocate 4 bytes for stack space 
   	sw ra, 0(sp)		#Store the value of ra in the stack
   	add a0, a0, a0       	#add a0 to itself, save in a0
    	jal extra		#Extra is a do nothing procedure
	lw ra, 0(sp)		#Retrieve the value of ra
	addi sp, sp, 4		#Deallocate the stack space
	jr  ra

extra:
	jr ra
