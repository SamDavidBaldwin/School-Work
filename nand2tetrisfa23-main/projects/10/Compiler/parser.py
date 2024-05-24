import re

class JackParser:
    def __init__(self, jack_code = None):
        self.jack_code = jack_code
        self.xml_code = ""
        self.tokens = self.tokenize(jack_code)
        self.current_token_index = 0

    def tokenize(self, jack_code):
        # Tokenize the Jack code using regular expressions
        keywords = ["class", "constructor", "function", "method", "field", "static", "var", "int", "char", "boolean", "void", "true", "false", "null", "this", "let", "do", "if", "else", "while", "return"]
        symbols = "{}()[].,;+-*/&|<>=~"
        token_pattern = rf"(\b(?:{'|'.join(map(re.escape, keywords))})\b)|({symbols})|(\b\w+\b)|(\d+)|('(?:[^'\\]|\\')*')|(\"(?:[^\"\\]|\\\")*\")"
        return [match.group() for match in re.finditer(token_pattern, jack_code)]

    def parse(self, jack_file):
        with open(jack_file, 'r') as file:
            print("Hello")
            jack_code = file.read()
        print(jack_code)
        
        self.tokens = self.tokenize(jack_code)
        self.xml_code += "<jack>\n"
        self.parse_class()
        self.xml_code += "</jack>"

    def parse_class(self):
        self.xml_code += "<class>\n"
        self.xml_code += f"<keyword>class</keyword>\n"
        self.advance()  # Skip "class"
        self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
        self.advance()  # Skip class name
        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip "{"

        while self.get_current_token() != "}":
            if self.get_current_token() in ("field", "static"):
                self.parse_class_var_dec()
            elif self.get_current_token() in ("constructor", "function", "method"):
                self.parse_subroutine()
            self.advance()

        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.xml_code += "</class>\n"

    def parse_class_var_dec(self):
        self.xml_code += "<classVarDec>\n"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip "field" or "static"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip variable type
        self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
        self.advance()  # Skip variable name

        while self.get_current_token() == ",":
            self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
            self.advance()  # Skip ","
            self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
            self.advance()  # Skip variable name

        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip ";"
        self.xml_code += "</classVarDec>\n"

    def parse_subroutine(self):
        self.xml_code += "<subroutineDec>\n"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip "constructor", "function", or "method"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip return type
        self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
        self.advance()  # Skip subroutine name
        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip "("

        self.parse_parameter_list()

        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip ")"
        self.xml_code += "<subroutineBody>\n"
        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip "{"

        while self.get_current_token() == "var":
            self.parse_var_dec()

        # Implement parsing of statements

        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip "}"
        self.xml_code += "</subroutineBody>\n"
        self.xml_code += "</subroutineDec>\n"

    def parse_parameter_list(self):
        self.xml_code += "<parameterList>\n"
        
        while self.get_current_token() != ")":
            self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
            self.advance()  # Skip parameter type
            self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
            self.advance()  # Skip parameter name

            if self.get_current_token() == ",":
                self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
                self.advance()  # Skip ","
        
        self.xml_code += "</parameterList>\n"

    def parse_var_dec(self):
        self.xml_code += "<varDec>\n"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip "var"
        self.xml_code += f"<keyword>{self.get_current_token()}</keyword>\n"
        self.advance()  # Skip variable type
        self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
        self.advance()  # Skip variable name

        while self.get_current_token() == ",":
            self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
            self.advance()  # Skip ","
            self.xml_code += f"<identifier>{self.get_current_token()}</identifier>\n"
            self.advance()  # Skip variable name

        self.xml_code += f"<symbol>{self.get_current_token()}</symbol>\n"
        self.advance()  # Skip ";"
        self.xml_code += "</varDec>\n"

    def get_current_token(self):
        return self.tokens[self.current_token_index]

    def advance(self):
        self.current_token_index += 1

    def save_xml(self, output_file):
        print(self.xml_code)
        with open(output_file, "w") as f:
            f.write(self.xml_code)


if __name__ == "__main__":
    jack_file = "./ArrrayTest/Main.jack"  # Replace with your Jack code file
    parser = JackParser()
    parser.parse(jack_file)

    output_file = "output.xml"
    parser.save_xml(output_file)