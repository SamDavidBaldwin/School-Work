// push constant 111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop static 8
@SP
A=M-1
D=M
@StaticTest.8
M=D
@SP
M=M-1
// pop static 3
@SP
A=M-1
D=M
@StaticTest.3
M=D
@SP
M=M-1
// pop static 1
@SP
A=M-1
D=M
@StaticTest.1
M=D
@SP
M=M-1
// push static 3
@0
D=M
@SP
A=M
M=D
@SP
M=M+1
// push static 1
@0
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
// push static 8
@0
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
