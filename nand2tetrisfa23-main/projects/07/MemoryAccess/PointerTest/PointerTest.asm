// push constant 3030
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1
// push constant 3040
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1
// push constant 32
@32
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop this 2
@THIS
D=M
@2
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1
// push constant 46
@46
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 6
@THAT
D=M
@6
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R13
A=M
M=D
@SP
M=M-1
// push pointer 0
@3
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// push pointer 1
@3
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D+M
@SP
A=M-1
M=D
// push this 2
@THIS
D=M
@2
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D-M
@SP
A=M-1
M=D
// push that 6
@THAT
D=M
@6
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
A=M-1
D=M
@R13
M=D
@SP
M=M-1
@SP
A=M-1
D=M
@R13
D=D+M
@SP
A=M-1
M=D
