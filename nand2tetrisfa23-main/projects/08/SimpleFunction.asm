(SimpleFunction.test)
@2
D=A
@13
M=D
(LOOP_SimpleFunction.test)
@13
D=M
@END_SimpleFunction.test
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@13
M=M-1
@LOOP_SimpleFunction.test
0;JMP
(END_SimpleFunction.test)
@0
D=A
@LCL
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
D=D+M
M=D
@SP
M=M-1
@SP
A=M-1
M=!M
@0
D=A
@ARG
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
D=D+M
M=D
@SP
M=M-1
@1
D=A
@ARG
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
D=M
A=A-1
D=M-D
M=D
@SP
M=M-1
@LCL
D=M
@13
M=D
@13
D=M
@5
D=D-A
A=D
D=M
@14
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@SP
M=M-1
@ARG
D=M+1
@SP
M=D
@13
A=M-1
D=M
@THAT
M=D
@13
D=M
@2
A=D-A
D=M
@THIS
M=D
@13
D=M
@3
A=D-A
D=M
@ARG
M=D
@13
D=M
@4
A=D-A
D=M
@LCL
M=D
@14
A=M
0;JMP
