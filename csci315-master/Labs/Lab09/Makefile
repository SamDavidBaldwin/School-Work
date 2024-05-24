CC = gcc 
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG
 
EXECS: ishell 

all: $(EXECS)

ishell: ishell.c 
	$(CC) $(CFLAGS) ishell.c -o ishell -lreadline
.PHONY: clean
clean:
	/bin/rm -rf $(EXECS)
