SYMBOLS = {
    "SP": 0,
    "LCL": 1,
    "ARG": 2,
    "THIS": 3,
    "THAT": 4,
    "R0": 0,
    "R1": 1,
    "R2": 2,
    "R3": 3,
    "R4": 4,
    "R5": 5,
    "R6": 6,
    "R7": 7,
    "R8": 8,
    "R9": 9,
    "R10": 10,
    "R11": 11,
    "R12": 12,
    "R13": 13,
    "R14": 14,
    "R15": 15,
    "SCREEN": 16384,
    "KBD": 24576
}

# Define the computation codes
COMP = {
    "": "0000000",
    "0": "0101010",
    "1": "0111111",
    "-1": "0111010",
    "D": "0001100",
    "A": "0110000",
    "!D": "0001101",
    "!A": "0110001",
    "-D": "0001111",
    "-A": "0110011",
    "D+1": "0011111",
    "A+1": "0110111",
    "D-1": "0001110",
    "A-1": "0110010",
    "D+A": "0000010",
    "D-A": "0010011",
    "A-D": "0000111",
    "D&A": "0000000",
    "D|A": "0010101"
}

# Define the destination codes
DEST = {
    "": "000",
    "M": "001",
    "D": "010",
    "MD": "011",
    "A": "100",
    "AM": "101",
    "AD": "110",
    "AMD": "111"
}

# Define jump codes
JUMP = {
    "": "000",
    "JGT": "001",
    "JEQ": "010",
    "JGE": "011",
    "JLT": "100",
    "JNE": "101",
    "JLE": "110",
    "JMP": "111"
}

# Initialize symbol table with predefined symbols
symbol_table = SYMBOLS.copy()

# Function to parse and preprocess the input assembly code
def preprocess(input_file):
    lines = []
    line_number = 0

    with open(input_file, "r") as file:
        for line in file:
            line = line.strip()

            # Remove comments and empty lines
            if not line.startswith("//") and line != "":
                # Remove inline comments
                if "//" in line:
                    line = line.split("//")[0].strip()
                lines.append(line)

                # Check if it's a label declaration
                if line.startswith("(") and line.endswith(")"):
                    symbol = line[1:-1]
                    symbol_table[symbol] = line_number
                else:
                    line_number += 1

    return lines

# Function to process A-instructions
def process_A_instruction(instruction):
    try:
        address = int(instruction[1:])
    except ValueError:
        # Handle symbol references
        symbol = instruction[1:]
        if symbol in symbol_table:
            address = symbol_table[symbol]
        else:
            # If symbol is not found, assume it's a new variable
            address = len(symbol_table)
            symbol_table[symbol] = address

    return format(address, "016b")

# Function to process C-instructions
def process_C_instruction(instruction):
    dest = ""
    comp = ""
    jump = ""

    if "=" in instruction and ";" in instruction:
        dest, rest = instruction.split("=")
        comp, jump = rest.split(";")
    elif "=" in instruction:
        dest, comp = instruction.split("=")
    elif ";" in instruction:
        comp, jump = instruction.split(";")
    else:
        comp = instruction

    return "111" + COMP[comp] + DEST[dest] + JUMP[jump]


# Main function to assemble the code
def assemble(input_file, output_file):
    lines = preprocess(input_file)

    output_lines = []
    for line in lines:
        if line.startswith("@"):
            output_lines.append(process_A_instruction(line))
        else:
            output_lines.append(process_C_instruction(line))

    with open(output_file, "w") as file:
        file.write("\n".join(output_lines))

if __name__ == "__main__":
    input_file = "max/Max.asm"  # Replace with your input assembly file
    output_file = "output.hack"  # Replace with your desired output file
    print("here")
    assemble(input_file, output_file)