CC = gcc 
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG
 
EXECS = heap-test pipe-test pipes upper tokens

all: $(EXECS)
 
heap-test: heap-test.c
	$(CC) $(CFLAGS) heap-test.c -o heap-test
pipe-test: pipe-test.c
	$(CC) $(CFLAGS) pipe-test.c -o pipe-test
pipes: pipes.c
	$(CC) $(CFLAGS) pipes.c -o pipes
upper: upper.c
	$(CC) $(CFLAGS) upper.c -o upper
tokens: tokens.c 
	$(CC) $(CFLAGS) tokens.c -o tokens -lreadline
.PHONY: clean
clean:
	/bin/rm -rf $(EXECS)
