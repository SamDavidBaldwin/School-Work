import vmWriter

status = " "
class Parser(object):
    def __init__(self, infile, outfile):
        self.curr = None
        self.index = 0
        self.keywords = ["class", "constructor", "function", "method", "field", "static", "var", "int", "char", "boolean", "void", "true", "false", "null", "this", "let", "do", "if", "else", "while", "return"]
        self.symbols  = "{}()[].,;+-*/&|<>=~"
        self.inparens = False
        self.allTokens = [] 
        self.output = ""

        with open(infile, "r") as infile:
            for line in infile: 
                if line.startswith("//") or line.startswith("/**") or line.strip() == "":
                    pass
                else:    
                    self.parse(line, outfile)
        print(self.allTokens)
        self.evalWords()
        with open(outfile, "w") as out:
            out.write(self.output)
            
        print(self.output)
        
                    
       
    def parse(self, line, outfile):
        symbols = "{}()[].,;+-*/&|<>=~"
        result = []
        index = 0
        location = 0
        inQuotes = False    
        last = False 
          
        for char in line:
            location += 1
            if char == "\"":
                    if inQuotes: 
                        inQuotes = False
                    else:
                        inQuotes = True
            if char in symbols: 
                # print("index: " + str(index) + " location: " + str(location))
                result.append(line[index:location-1])
                result.append(char)
                index = location

            if char == " " and not inQuotes: 
                result.append(line[index:location])
                index = location
        result = [item for item in result if item.strip() != ""]
        result = [item.rstrip() for item in result]

        if result[0].startswith('\t'):
            result[0] = result[0].lstrip('\t')
        self.result = result
        for item in self.result:
            self.allTokens.append(item)
        
    def nextToken(self):
        self.index += 1
        self.value = self.allTokens[self.index]
        return self.value
    
    def writeLet(self):
        self.output += "<letStatement>\n<keyword> let </keyword>\n"
        self.value = self.nextToken()
        self.output += "<identifier> " + self.value + " </identifier>\n"
        self.value = self.nextToken()
        if self.value == "=":
            self.output += "<symbol> " + self.value + " </symbol>\n"
            self.writeExpression()
        elif self.value == "[":
            self.output += "<symbol> " + self.value + " </symbol>\n<expression>\n"
            while self.value != "]":
                self.value = self.nextToken()
                if self.value.isnumeric(): 
                    self.output += "<term>\n<integerConstant> " + self.value + " </integerConstant>\n</term>\n"
                    self.value = self.nextToken()
                else:
                    self.output += "<term>\n<identifier> " + self.value + " </identifier>\n</term>\n"
                    self.value = self.nextToken()
            self.output += "</expression>\n<symbol> ] </symbol>\n"
            self.value = self.nextToken()
            if self.value == "=":
                self.output += "<symbol> " + self.value + " </symbol>\n"
                self.writeExpression()
        
        return

    def writeExpression(self):
        self.value = self.nextToken()
        self.output += "<expression>\n<term>\n"

        while self.value != ";" and self.value != ")":#Go until a terminating char is reached
            if self.value == "(":
                self.output += "<symbol> " + self.value + " </symbol>\n"
                self.writeExpressionList()
                #self.output += "</term>\n</expression>\n" 
                return
            
            elif self.value.isnumeric(): 
                self.output += "<integerConstant> " + self.value + " </integerConstant>\n"
                self.value = self.nextToken()
                self.output += "</term>\n</expression>\n<symbol> " + self.value + " </symbol>\n</letStatement>\n"
                return
            
            elif self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
            
            elif self.value in self.keywords: 
                self.output += "<keyword> " + self.value + " </keyword>\n"
            
            else: 
                self.output += "<identifier> " + self.value + " </identifier>\n"
                if self.allTokens[self.index + 1] == "[":
                    self.output += "<symbol> [ </symbol>\n"
                    self.value = self.nextToken()
                    self.value = self.nextToken()
                    while self.value != "]":
                        self.output += "<expression>\n<term>\n<identifier> " + self.value + " </identifier>\n"
                        self.value = self.nextToken()
                    #self.output += "</term>\n</expression>\n<symbol> ] </symbol>\n</term>\n</expression>\n<symbol> ; </symbol>\n</letStatement>\n"
                    self.output += "</term>\n</expression>\n<symbol> ] </symbol>\n"

                    self.value = self.nextToken
                if self.allTokens[self.index + 1] == "+":
                    self.output += "</term>\n<symbol> + </symbol>\n<term>\n"
                    self.value = self.nextToken()
                if self.allTokens[self.index + 1] == ";":
                    self.output += "</term>\n</expression>\n<symbol> ; </symbol>\n</letStatement>\n"
            self.value = self.nextToken()
        
        if self.value == ")":
            self.output += "<symbol> ) </symbol>\n</expression>\n"
        return

    def writeExpressionList(self, do = None):
        self.value = self.nextToken()
        if self.value == ")": #Empty ExpressionList 
            if(do):
                self.output += "<expressionList>\n</expressionList>\n<symbol> ) </symbol>\n<symbol> ; </symbol>\n</doStatement>\n"
            else:
                self.output += "<expressionList>\n</expressionList>\n<symbol> ) </symbol>\n</term>\n</expression>\n<symbol> ; </symbol>\n</letStatement>\n"

            self.value = self.nextToken()
            print("WHEN EXITING THE CASE THE VLAUE IS :" + self.value)
            return
        self.output += "<expressionList>\n<expression>\n"
        while self.value != ")":
            if self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
            else: 
                #STRING CONSTANTS AND INTEGERS ARE IN HERE
                if "\"" in self.value:
                    self.output += "<term>\n<stringConstant> " + self.value.strip("\"") + " </stringConstant>\n</term>\n"
                else:
                    self.output += "<term>\n<identifier> " + self.value + " </identifier>\n</term>\n"
            self.value = self.nextToken()
        self.output += "</expression>\n</expressionList>\n<symbol> " + self.value + " </symbol>\n"
        self.value = self.nextToken()
        if(do):
            self.output += "<symbol> " + self.value + " </symbol>\n</doStatement>\n"
            return
        else:
            self.output += "</term>\n</expression>\n<symbol> " + self.value + " </symbol>\n</letStatement>\n"
        return

    def writeWhile(self): 
        self.output += "<whileStatement>\n"
        self.nextToken()
        self.output += "<keyword> while </keyword>\n"
        self.nextToken() 
        self.output += "<symbol> ( </symbol>\n<expression>\n"
        while self.value != ")":
            if self.value in self.symbols: 
                if self.value == "<":
                    self.value = "&lt;"
                self.output += "<symbol> " + self.value + " </symbol>\n"
            else: 
                self.output += "<term>\n<identifier> " + self.value + " </identifier>\n</term>\n"
            self.value = self.nextToken()
        self.output += "</expression>\n<symbol> ) </symbol>\n"
        self.value = self.nextToken()
        if self.value == "{":
            self.output += "<symbol> { </symbol>\n"
            first = True
            self.value = self.nextToken()
            while self.value != "}":
                if self.value == "var":
                    self.writeVarDec()
                if self.value == "while":
                    if first == True:
                        self.output += "<statements>\n"
                        first = False
                    self.writeWhile()
                if self.value == "let":
                    if first == True:
                        self.output += "<statements>\n"
                        first = False
                    self.writeLet()
                    print(self.value + "EXITING THE LET STATEMENT HERE" )
                if self.value == "do":
                    if first == True: 
                        self.output += "<statements>\n"
                        first = False
                    self.writeDo()
                self.value = self.nextToken()
            self.output += "</statements>\n<symbol> } </symbol>\n</whileStatement>\n"
        return
    
    def writeClass(self, list):
        self.output += "<class>\n"
        self.index = 0
        self.value = self.allTokens[self.index]
        self.output += "<keyword> class </keyword>\n"
        self.value = self.nextToken()
        self.output += "<identifier> " + self.value + " </identifier>\n"
        self.value = self.nextToken()
        if self.value == "{":
            self.writeSubroutine()
        self.output += "</class>"

    def writeSubroutine(self):
        self.output += "<symbol> " + self.value + " </symbol>\n"
        self.index += 1
        self.value = self.allTokens[self.index]
        while self.value in self.keywords:
            if self.value == "static":
                self.output += "<classVarDec>\n<keyword> static </keyword>\n"
                self.staticVarDec()
            if self.value == "field":
                self.output += "<classVarDec>\n<keyword> field </keyword>\n"
                self.staticVarDec()
            if self.value == "function":
                self.output += "<subroutineDec>\n"
                self.writeFunction()
                self.output += "</subroutineDec>\n"
                if self.index == len(self.allTokens) - 1:
                    self.output += "<symbol> " + self.value + " </symbol>\n"
            if self.value == "constructor":
                self.output += "<subroutineDec>\n"
                self.writeFunction()
            if self.value == "method":
                self.output += "<subroutineDec>\n"
                self.writeFunction()
            if self.value == "return":
                self.writeReturn() 
        print(self.value)
    
    def staticVarDec(self):
        self.value = self.nextToken()
        while self.value != ";":
            if self.value in self.keywords:
                self.output += "<keyword> " + self.value + " </keyword>\n"
            elif self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
            else: 
                self.output += "<identifier> " + self.value + " </identifier>\n"
            self.value = self.nextToken()
        self.output += "<symbol> ; </symbol>\n</classVarDec>\n"
        self.value = self.nextToken()
        return
    
    def writeVarDec(self):
        self.output += "<varDec>\n"
        self.value = self.allTokens[self.index]
        while self.value != ";":
            if self.value in self.keywords:
                self.output += "<keyword> " + self.value + " </keyword>\n"
            elif self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
            else: 
                self.output += "<identifier> " + self.value + " </identifier>\n"
            self.value = self.nextToken()
        self.output += "<symbol> ; </symbol>\n</varDec>\n"
        return

    def writeFunction(self):
        self.value = self.allTokens[self.index]
        self.output += "<keyword> " + self.value + " </keyword>\n"
        self.value = self.nextToken()
        self.output += "<keyword> " + self.value + " </keyword>\n"
        self.value = self.nextToken()
        self.output += "<identifier> " + self.value + " </identifier>\n"
        self.value = self.nextToken()
        if self.value == "(":
            self.writeParameters()
        self.index += 1
        if self.allTokens[self.index] == "{":
            self.writeSubroutineBody()
        return

    
    def writeSubroutineBody(self):
        self.output += "<subroutineBody>\n"
        self.output += "<symbol> " + self.allTokens[self.index] + " </symbol>\n"
        first = True
        self.value = self.nextToken()
        while self.value != "}":
           
            if self.value == "var":
                self.writeVarDec()
            if self.value == "while":
                if first == True:
                    self.output += "<statements>\n"
                    first = False
                self.writeWhile()
            if self.value == "let":
                if first == True:
                    self.output += "<statements>\n"
                    first = False
                self.writeLet()
                print(self.value + "EXITING THE LET STATEMENT HERE" )
            if self.value == "do":
                if first == True:
                    self.output += "<statements>\n"
                    first = False
                self.writeDo()
            if self.value == "if":
                if first == True:
                    self.output += "<statements>\n"
                    first = False
                self.writeIf()
            self.value = self.nextToken()
            if self.value == "return":
                self.writeReturn()

        self.output += "<symbol> " + self.value + " </symbol>\n</subroutineBody>\n"
        self.value = self.nextToken()
    
    def writeIf(self):
        self.output += "<ifStatement>\n"
        self.ifCondition()
        self.ifBody()
        self.value = self.nextToken()
        if self.value == "else":
            self.output += "<keyword> else </keyword>\n"
            self.ifBody()
        else:
            self.output += "<symbol> " + self.value + " </symbol>\n"
        self.output += "</ifStatement>\n"
        return
    def ifBody(self):
        self.value = self.nextToken()
        self.output += "<symbol> " + self.value + " </symbol>\n<statements>\n"
        self.value = self.nextToken()
        if self.value == "}":
            self.output += "</statements>\n<symbol> } </symbol>\n"
        return
    
    def ifCondition(self):
        self.output += "<keyword> " + self.value + " </keyword>\n"
        self.value = self.nextToken()
        self.output += "<symbol> ( </symbol>\n<expression>\n<term>\n"
        self.value = self.nextToken()
        if self.value in self.keywords:
            self.output += "<keyword> " + self.value + " </keyword>\n</term>\n</expression>\n"
        else:
            self.output += "<identifier> " + self.value + " </identifier>\n</term>\n</expression>\n"
        self.value = self.nextToken()
        self.output += "<symbol> " + self.value + " </symbol>\n"
        return

    def writeReturn(self):
        self.output += "<returnStatement>\n<keyword> return </keyword>\n"
        self.value = self.nextToken()
        if self.value == ";":
            self.output += "<symbol> ; </symbol>\n</returnStatement>\n</statements>\n"
            self.value = self.nextToken()
        
    def writeDo(self):
        self.output += "<doStatement>\n<keyword> do </keyword>\n"
        self.value = self.nextToken()
        self.output += "<identifier> " + self.value + " </identifier>\n"
        self.value = self.nextToken()
        while self.value != ";":
            if self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
                if self.value == "(":
                    self.writeExpressionList(True)
                    return
            else:
                self.output += "<identifier> " + self.value + " </identifier>\n"
            self.value = self.nextToken()
        return
    
    def writeParameters(self):
        self.value = self.allTokens[self.index]
        while self.value != ")":
            if self.value in self.symbols: 
                self.output += "<symbol> " + self.value + " </symbol>\n"
                self.output += "<parameterList>\n"
            self.value = self.nextToken()
        self.output += "</parameterList>\n"
        self.output += "<symbol> " + self.value + " </symbol>\n"
        return

        
    def writeNonKeyword(self, list):
        print("WRITING SOMETHING ELSE")

    def evalWords(self):
        self.value = self.allTokens[self.index]
        if self.value == "class":
            parameters = []
            while self.index < len(self.allTokens)-1:
                parameters.append(self.value)
                self.index = self.index + 1
                self.value = self.allTokens[self.index]
            self.writeClass(parameters)

        if self.value == "let":
            self.writeLet()
        if self.value == "while":
            self.writeWhile()
        if self.value == "function":
            self.writeFunction()
        return "test"
    

        
        
    
  
    
            


    


    