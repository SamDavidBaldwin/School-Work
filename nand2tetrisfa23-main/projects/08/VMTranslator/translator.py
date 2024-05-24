import sys
import os

class VMTranslator:
    def __init__(self, input_file, output_file):
        self.input_file = input_file
        self.output_file = "./" + output_file
        self.eq_label_count = 0
        self.lt_label_count = 0
        self.gt_label_count = 0
        self.address = 0
        self.writefile_ind = self.output_file.rfind('/')
        self.static_var = self.output_file[self.writefile_ind + 1:]

    def translate(self):
        with open(self.input_file, 'r') as input_file, open(self.output_file, 'w') as output_file:
            self.writeInit(output_file)
            for line in input_file:
                command = line.strip()
                if command and not command.startswith('//'):
                    self.address += 1
                    self.translate_command(command, output_file)

    def writeInit(self, output_file):
        output_file.write('@256\n')
        output_file.write('D=A\n')
        output_file.write('@SP\n')
        output_file.write('M=D\n')
        output_file.write('@300\n')
        output_file.write('D=A\n')
        output_file.write('@LCL\n')
        output_file.write('M=D\n')
        output_file.write('@400\n')
        output_file.write('D=A\n')
        output_file.write('@ARG\n')
        output_file.write('M=D\n')
        output_file.write('@3000\n')
        output_file.write('D=A\n')
        output_file.write('@THIS\n')
        output_file.write('M=D\n')
        output_file.write('@3010\n')
        output_file.write('D=A\n')
        output_file.write('@THAT\n')
        output_file.write('M=D\n')


    def translate_command(self, command, output_file):
        parts = command.split()
        print(parts)
        if len(parts) == 1:
            if parts[0] in ('add', 'sub', 'neg', 'eq', 'gt', 'lt', 'and', 'or', 'not'):
                self.write_arithmetic(parts[0], output_file)
            if parts[0] == "return":
                self.writeReturn(output_file)
        elif len(parts) == 2:
            if parts[0] == 'push':
                self.write_push(parts[1], 'constant', 0, output_file)
            else:
                self.write_program_flow(parts, output_file)
        elif len(parts) == 3:
            if parts[0] == 'push':
                self.write_push(parts[1], parts[2], 0, output_file)
            elif parts[0] == 'pop':
                self.write_pop(parts[1], int(parts[2]), output_file)
            if parts[0] == 'function':
                self.translateFunction(parts, output_file)
            if parts[0] == 'call':
                self.translateCall(parts, output_file)

    def write_program_flow(self, parts, output_file):
        if parts[0] == 'label':
            self.translateLabel(parts[1],output_file)
        if parts[0] == 'goto':
            self.translateGoto(parts[1], output_file)
        if parts[0] == "if-goto":
            self.translateIfGoto(parts[1], output_file)
        
    def translateLabel(self, parts, output_file):
        output_file.write("(" + parts + ")\n")

    def translateGoto(self, parts, output_file):
        output_file.write("@" + parts + "\n")
        output_file.write("0;JMP\n")

    def translateIfGoto(self, parts, output_file):
        output_file.write("@SP\n")
        output_file.write("AM=M-1\n")
        output_file.write("D=M\n")
        output_file.write("@" + parts + "\n")
        output_file.write("D;JNE\n")
    
    def translateFunction(self, parts, output_file):
        print("Function")
        output_file.write('('  + parts[1] + ')\n')
        output_file.write('@' + parts[2] + '\n')
        output_file.write('D=A\n')
        output_file.write('@13\n')
        output_file.write('M=D\n')
        output_file.write('(LOOP_' + parts[1] + ')\n')
        output_file.write('@13\n')
        output_file.write('D=M\n')
        output_file.write('@END_' + parts[1] + '\n')
        output_file.write('D;JEQ\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=0\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@13\n')
        output_file.write('M=M-1\n')
        output_file.write('@LOOP_' + parts[1] + '\n')
        output_file.write('0;JMP\n')
        output_file.write('(END_' + parts[1] + ')\n')
    
    def writeReturn(self, output_file):
        output_file.write('@LCL\n')
        output_file.write('D=M\n')
        output_file.write('@13\n')     
        output_file.write('M=D\n')
        output_file.write('@13\n')
        output_file.write('D=M\n')
        output_file.write('@5\n')
        output_file.write('D=D-A\n')
        output_file.write('A=D\n')
        output_file.write('D=M\n')   
        output_file.write('@14\n')    
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('A=M-1\n')
        output_file.write('D=M\n')
        output_file.write('@ARG\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M-1\n')
        output_file.write('@ARG\n')
        output_file.write('D=M+1\n')
        output_file.write('@SP\n')
        output_file.write('M=D\n')
        output_file.write('@13\n')
        output_file.write('A=M-1\n')
        output_file.write('D=M\n')
        output_file.write('@THAT\n')
        output_file.write('M=D\n')
        output_file.write('@13\n')
        output_file.write('D=M\n')
        output_file.write('@2\n')
        output_file.write('A=D-A\n')
        output_file.write('D=M\n')
        output_file.write('@THIS\n')
        output_file.write('M=D\n')
        output_file.write('@13\n')
        output_file.write('D=M\n')
        output_file.write('@3\n')
        output_file.write('A=D-A\n')
        output_file.write('D=M\n')
        output_file.write('@ARG\n')
        output_file.write('M=D\n')
        output_file.write('@13\n')
        output_file.write('D=M\n')
        output_file.write('@4\n')
        output_file.write('A=D-A\n')
        output_file.write('D=M\n')
        output_file.write('@LCL\n')
        output_file.write('M=D\n')
        output_file.write('@14\n')     
        output_file.write('A=M\n')   
        output_file.write('0;JMP\n')

    def translateCall(self, parts, output_file):
        output_file.write('D=A\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@LCL\n')
        output_file.write('D=M\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@ARG\n')
        output_file.write('D=M\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@THIS\n')
        output_file.write('D=M\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@THAT\n')
        output_file.write('D=M\n')
        output_file.write('@SP\n')
        output_file.write('A=M\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('M=M+1\n')
        output_file.write('@SP\n')
        output_file.write('D=M\n')
        output_file.write('@%s\n' % parts[2])
        output_file.write('D=D-A\n')
        output_file.write('@5\n')
        output_file.write('D=D-A\n')
        output_file.write('@ARG\n')
        output_file.write('M=D\n')
        output_file.write('@SP\n')
        output_file.write('D=M\n')
        output_file.write('@LCL\n')
        output_file.write('M=D\n')
        output_file.write('@%s\n' % parts[1])
        output_file.write('0;JMP\n')

    def write_arithmetic(self, command, output_file):
        #output_file.write('// ' + command + '\n')
        if command == 'add':
            output_file.write('@SP\nA=M-1\nD=M\nA=A-1\nD=D+M\nM=D\n@SP\nM=M-1\n')
        elif command == 'sub':
            output_file.write('@SP\nM=M-1\nD=M\nA=A-1\nD=M-D\nM=D\n@SP\nM=M-1\n')
        elif command == 'neg':
            output_file.write('@SP\nA=M-1\nM=-M\n')
        elif command == "eq":
            self.eq_label_count += 1
            self.output_file.write('@SP\nA=M-1\nD=M\nA=A-1\nD=M-D\n')
        elif command in ('eq', 'gt', 'lt'):
            label = ''
            if command == 'eq':
                label = 'EQ'
                self.eq_label_count += 1
            elif command == 'gt':
                label = 'GT'
                self.gt_label_count += 1
            elif command == 'lt':
                label = 'LT'
                self.lt_label_count += 1
            output_file.write('@SP\nM=M-1\nD=M\nA=A-1\nD=M-D\n@TRUE' + label + str(self.eq_label_count) + '\nD;' + command.upper() + '\n@SP\nA=M-1\nM=0\n@CONTINUE' + label + str(self.eq_label_count) + '\n0;JMP\n(TRUE' + label + str(self.eq_label_count) + ')\n@SP\nA=M-1\nM=-1\n(CONTINUE' + label + str(self.eq_label_count) + ')\n')
        
        elif command == 'and':
            output_file.write('@SP\nA=M-1\nD=M\nA=A-1\nM=D&M\n@SP\nM=M-1\n')
        elif command == 'or':
            output_file.write('@SP\nA=M-1\nD=M\nA=A-1\nM=D|M\n@SP\nM=M-1\n')
        elif command == 'not':
            output_file.write('@SP\nA=M-1\nM=!M\n')

    def write_push(self, segment, segment_type, index, output_file):
        if segment == 'constant':
            output_file.write('@' + segment_type + '\nD=A\n@SP\nA=M\nM=D\n') 
        elif segment != 'static': 
            output_file.write('@' + segment_type + "\nD=A\n")
            if segment == 'temp':
                output_file.write("@5\nA=D+A\n")
            if segment == 'pointer':
                output_file.write("@3\nA=D+A\n")
            if segment == 'local':
                output_file.write("@LCL\nA=D+A\n")
            if segment == 'argument':
                output_file.write("@ARG\nA=D+A\n")
            if segment == 'this':
                output_file.write("@THIS\nA=D+A\n")
            if segment == 'that':
                output_file.write("@THAT\nA=D+A\n")
            output_file.write('D=M\n@SP\nA=M\nM=D\n')
        elif segment == 'static':
                output_file.write('@' + str(index) + '\nD=M\n@SP\nA=M\nM=D\n')
        output_file.write('@SP\nM=M+1\n')
    
    def write_pop(self, segment, index, output_file):
        #output_file.write('// pop ' + segment + ' ' + str(index) + '\n')
        output_file.write('@' + str(index) + '\nD=A\n')
        if segment != 'static':
            if segment == 'local':
                output_file.write('@LCL\nD=D+M\n')
            if segment == 'argument':
                output_file.write('@ARG\nD=D+M\n')
            if segment == 'this':
                output_file.write('@THIS\nD=D+M\n')
            if segment == 'that':
                output_file.write('@THAT\nD=D+M\n')    
            if segment == 'temp':
                output_file.write("@5\nD=D+A")
            if segment == 'pointer':
                output_file.write('@3\nD=D+A\n')
        
            output_file.write('@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M+1\n')
        else: 
            output_file.write('@SP\nA=M-1\nD=M\n@%s.%s\nM=D\n@SP\nM=M-1\n' % (self.input_file.rsplit('.', 1)[0].rsplit('\\', 1)[1], index))
   

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage: python VMTranslator.py <input_directory>')
    else:
        input_files = sys.argv[1]
        print(input_files)
        hold = input_files.split('\\')
        print(hold)
        output_file =  hold[2] + ".asm"
        for file in os.scandir(input_files):
            print(file.name)
            if file.name.__contains__(".vm"):
                translator = VMTranslator(file, output_file)
                translator.translate()