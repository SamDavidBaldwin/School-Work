import sys

class VMTranslator:
    def __init__(self, input_file, output_file):
        self.input_file = input_file
        self.output_file = output_file
        self.eq_label_count = 0
        self.lt_label_count = 0
        self.gt_label_count = 0
        self.address = 0
        self.writefile_ind = self.output_file.rfind('/')
        self.static_var = self.output_file[self.writefile_ind + 1:]

    def translate(self):
        with open(self.input_file, 'r') as input_file, open(self.output_file, 'w') as output_file:
            for line in input_file:
                command = line.strip()
                if command and not command.startswith('//'):
                    self.address += 1
                    self.translate_command(command, output_file)

    def translate_command(self, command, output_file):
        parts = command.split()
        if len(parts) == 1:
            if parts[0] in ('add', 'sub', 'neg', 'eq', 'gt', 'lt', 'and', 'or', 'not'):
                self.write_arithmetic(parts[0], output_file)
        elif len(parts) == 2:
            if parts[0] == 'push':
                self.write_push(parts[1], 'constant', 0, output_file)
        elif len(parts) == 3:
            if parts[0] == 'push':
                self.write_push(parts[1], parts[2], 0, output_file)
            elif parts[0] == 'pop':
                self.write_pop(parts[1], int(parts[2]), output_file)

    def write_arithmetic(self, command, output_file):
        output_file.write('// ' + command + '\n')
        if command == 'add':
            output_file.write('@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nD=D+M\n@SP\nA=M-1\nM=D\n')
        elif command == 'sub':
            output_file.write('@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nD=D-M\n@SP\nA=M-1\nM=D\n')
        elif command == 'neg':
            output_file.write('@SP\nA=M-1\nM=-M\n')
        elif command == "eq":
            self.eq_label_count += 1
            self.file.write('@SP\nA=M-1\nD=M\nA=A-1\nD=M-D\n')
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
        output_file.write('// push ' + segment + ' ' + str(segment_type) + '\n')
        if segment == 'constant':
            output_file.write('@' + segment_type + '\nD=A\n@SP\nA=M\nM=D\n') 
        elif segment != 'static': 
            if segment == 'temp':
                output_file.write("@5\nD=A\n@" + segment_type + "\nA=D+A\n")
            if segment == 'pointer':
                output_file.write("@3\nA=D+A\n")
            if segment == 'local':
                output_file.write("@LCL\nD=M\n@" + segment_type + "\nA=D+A\n")
            if segment == 'argument':
                output_file.write("@ARG\nD=M\n@" + segment_type +"\nA=D+A\n")
            if segment == 'this':
                output_file.write("@THIS\nD=M\n@" + segment_type +"\nA=D+A\n")
            if segment == 'that':
                output_file.write("@THAT\nD=M\n@" + segment_type + "\nA=D+A\n")

            output_file.write('D=M\n@SP\nA=M\nM=D\n')
        elif segment == 'static':
                output_file.write('@' + str(index) + '\nD=M\n@SP\nA=M\nM=D\n')
        output_file.write('@SP\nM=M+1\n')
    
    def write_pop(self, segment, index, output_file):
        output_file.write('// pop ' + segment + ' ' + str(index) + '\n')
        #output_file.write('@' + str(index) + '\nD=A\n')
        if segment != 'static':
            if segment == 'local':
                output_file.write('@LCL\nD=M\n@' + str(index) + "\nD=D+A\n")
            if segment == 'argument':
                output_file.write('@ARG\nD=M\n@' + str(index) + "\nD=D+A\n")
            if segment == 'this':
                output_file.write('@THIS\nD=M\n@' + str(index) + '\nD=D+A\n')
            if segment == 'that':
                output_file.write('@THAT\nD=M\n@' + str(index) + '\nD=D+A\n')    
            if segment == 'temp':
                output_file.write('@5\nD=A\n@' + str(index) + '\nD=D+A\n')
            if segment == 'pointer':
                output_file.write('@3\nD=D+A\n')
            output_file.write('@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n')
        else: 
            output_file.write('@SP\nA=M-1\nD=M\n@%s.%s\nM=D\n@SP\nM=M-1\n' % (self.input_file.rsplit('.', 1)[0].rsplit('\\', 1)[1], index))
   

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage: python VMTranslator.py <input_file.vm>')
    else:
        input_file = sys.argv[1]
        output_file = input_file.replace('.vm', '.asm')
        translator = VMTranslator(input_file, output_file)
        translator.translate()