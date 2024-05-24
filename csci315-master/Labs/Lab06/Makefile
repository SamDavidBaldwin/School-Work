CC = gcc 
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG
 
EXECS: dp problem1 problem2 problem3-1 problem3-2

all: $(EXECS)

dp: dp.c 
	$(CC) $(CFLAGS) dp.c -o dp -lpthread 
problem1: problem1.c
	$(CC) $(CFLAGS) problem1.c -o problem1 -lpthread
problem2: problem2.c 
	$(CC) $(CFLAGS) problem2.c -o problem2 -lpthread
problem3-1: problem3-1.c
	$(CC) $(CFLAGS) problem3-1.c -o problem3-1 -lpthread
problem3-2: problem3-2.c
	$(CC) $(CFLAGS) problem3-2.c -o problem3-2 -lpthread
.PHONY: clean 
clean:
	/bin/rm -rf $(EXECS)
