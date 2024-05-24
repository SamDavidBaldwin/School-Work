#RISC-V assembly double_value

.text
.globl double_value

double_value:
    # a0 - parameter to be doubled
    # a0 - the squared value to return

    add a0, a0, a0       # add a0 to itself, save in a0
    jr  ra
