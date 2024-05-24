// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(LOOP)

@8192 //There are 8192 unique registers to "paint" 
D=A    
@register     
M=D    

//Set value of keyboard press
@KBD    
D=M     

//If No input D=0 so set screen white
@WHITE
D; JEQ   
@BLACK
0; JMP   


//Set screen to black, starting at the 0 register and incrementing to 8192
(BLACK)
@register
A = M
M = -1


// increment register by 1
@register
M = M + 1
D = M  
@KBD 
D = A - D 

//If the value of D is 0, the current register is 8192 so the whole screen has been set
@LOOP
D; JEQ    
@BLACK   
0; JMP


//Same thing just with M = 0 instead of -1
(WHITE)
@register
A = M
M = 0

@register
M = M + 1

D = M
@KBD 
D = A - D 

@LOOP
D; JEQ    
@WHITE   
0; JMP