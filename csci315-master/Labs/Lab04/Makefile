CC = gcc 
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG
 
EXECS: echod echoreq echoreq2 

all: $(EXECS)

wrappers.o: wrappers.c wrappers.h
	$(CC) $(CFLAGS) -c wrappers.c
echod: echod.c wrappers.o
	$(CC) $(CFLAGS) wrappers.o echod.c -o echod 
echoreq: echoreq.c wrappers.o 
	$(CC) $(CFLAGS) wrappers.o echoreq.c -o ecoreq
echoreq2: echoreq2.c wrappers.o
	$(CC) $(CFLAGS) wrappers.o echoreq2.c -o echoreq2
.PHONY: clean
clean:
	/bin/rm -rf $(EXECS)
