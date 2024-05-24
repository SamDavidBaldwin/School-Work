import sys
import re

class Assembler():
 
    def __init__(self, file):
        self.mem_address_jump = 16
        self.parser = fileParser(file)
        self.symbol_table = {'SP'  : 0,'LCL' : 1,'ARG' : 2,'THIS': 3,'THAT': 4,'R0'  : 0,'R1'  : 1,
                            'R2'  : 2,'R3'  : 3,'R4'  : 4,'R5'  : 5,'R6'  : 6,'R7'  : 7,'R8'  : 8,
                            'R9'  : 9,'R10' : 10,'R11' : 11,'R12' : 12,'R13' : 13,'R14' : 14,'R15' : 15,
                            'SCREEN': 16384,'KBD': 24576}

    def main(self):
        self.find_labels()
        self.translate()

    def find_labels(self):
    
        num_instructions = 0
        while self.parser.not_at_end:
            self.parser.next_line()

            if self.parser.current_command_type == 'not_instruction':
                continue
            elif self.parser.current_command_type == 'label':
                self.symbol_table[self.parser.symbol()] = num_instructions
            else:
                num_instructions += 1


    def translate(self):
        file_name = self.parser.input_file.name.split('.')[0] + '.hack'
        outfile = open(file_name, 'w+')

        char_only_matcher = re.compile('[a-zA-Z]+')
        self.parser.input_file.seek(0)
        self.parser.not_at_end = True
        
        while self.parser.not_at_end:
            self.parser.next_line()
            machine_code = ''

            if self.parser.current_command_type == 'address':
                symbol = self.parser.symbol()
                not_number = char_only_matcher.match(symbol)
            
                if not_number:
                    if symbol in self.symbol_table:
                        register_number = self.symbol_table[symbol]
                    else:
                        self.symbol_table[self.next_symbol_addr] = symbol
                        register_number = self.next_symbol_addr
                        self.next_symbol_addr += 1
                else:
                    register_number = int(symbol)

                machine_code = decoder.decimal_to_binary_string(register_number)
           
           
            elif self.parser.current_command_type == 'computation':
                print("Computation Type")
                init_bits = decoder.init_bits
                comp_bits = self.parser.comp_mnemonic()
                dest_bits = self.parser.dest_mnemonic()
                jump_bits = self.parser.jump_mnemonic()

                machine_code = init_bits + comp_bits + dest_bits + jump_bits

            if len(machine_code) > 0:
                outfile.write(machine_code + '\n')

        outfile.close()


class decoder():
    init_bits = '111'

    dest_bits = { None : '000', 'M'  : '001', 'D'  : '010', 'MD' : '011', 'A'  : '100', 'AM' : '101', 'AD' : '110', 'AMD': '111'}

    comparison_bits = {None : '', '0'  : '0101010', '1'  : '0111111', '-1' : '0111010', 'D'  : '0001100', 'A'  : '0110000', 'M'  : '1110000',
                        '!D' : '0001101', '!A' : '0110001','!M' : '1110001', '-D' : '0001111', '-A' : '0110011', '-M' : '1110011','D+1': '0011111',
                        'A+1': '0110111','M+1': '1110111','D-1': '0001110','A-1': '0110010','M-1': '1110010','D+A': '0000010','D+M': '1000010','D-A': '0010011',
                        'D-M': '1010011','A-D': '0000111','M-D': '1000111', 'D&A': '0000000','D&M': '1000000','D|A': '0010101','D|M': '1010101'}

    jump_bits = {None : '000','JGT': '001','JEQ': '010','JGE': '011','JLT': '100','JNE': '101','JLE': '110','JMP': '111'}

    def decimal_to_binary_string(num):
        return '{0:016b}'.format(num)


class fileParser():

    def __init__(self, input_file):
        self.input_file = open(input_file, 'r')
        self.current_command = None
        self.not_at_end = True
      
    def dest_mnemonic(self):
        if "=" in self.current_command:
            return decoder.dest_bits[self.current_command.split("=")[0]]

    def comp_mnemonic(self):
        if "=" in self.current_command:
            return decoder.comparison_bits[self.current_command.split("=")[1]]
        elif ";" in self.current_command:
            return decoder.comparison_bits[self.current_command.split(";")[0]]

    def jump_mnemonic(self):
        if  ";" in self.current_command:
            return decoder.jump_bits[self.current_command.split(";")[1]]

    def symbol(self):
        return ''.join(c for c in self.current_command if c not in '()@/')

    def next_line(self):
        if self.current_command == None:
            self.current_command = self.input_file.readline()
        else:
            self.current_command = self.next_line

        self.current_command = self.current_command.strip()
        self.current_command = self.current_command.split('//')[0]
        self.current_command = self.current_command.strip(" ")
        print("Current Command: " + self.current_command)
        self.next_line = self.input_file.readline()

        if self.next_line == '':
            self.not_at_end = False

        self.line_type()

    def line_type(self):
        if self.current_command == '':
            self.current_command_type = 'not_instruction'
        elif self.current_command[0] == '@':
            self.current_command_type = 'address'
        elif self.current_command[0] == '(':
            self.current_command_type = 'label'
        else:
            self.current_command_type = 'computation'

file = sys.argv[1]
assembler = Assembler(file)
assembler.main()