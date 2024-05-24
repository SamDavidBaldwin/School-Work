import sys, os, os.path, glob
import JackParser

class JackAnalyzer():
    def __init__(self):
        pass

    def analyze(self, file, outfile):
        JackParser.Parser(file, outfile)

def main():
        if len(sys.argv) != 2:
            print( "Usage: JackAnalyzer [file.jack]" )
        else:
            infile = sys.argv[1] 
            outfile = infile.replace('.jack', 'Test.xml')
            analyzer = JackAnalyzer()
            analyzer.analyze(infile, outfile)

main()