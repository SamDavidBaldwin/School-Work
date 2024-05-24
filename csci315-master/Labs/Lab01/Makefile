CC = gcc 
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG
 
EXECS = fork-test fork cmds cmdreverse myprog fork-wait fork-data fork-file catcount

all: $(EXECS)
 
fork-test: fork-test.c 
	$(CC) $(CFLAGS) fork-test.c -o  fork-test
fork: fork.c
	$(CC) $(CFLAGS) fork.c -o fork
myprog: myprog.c
	$(CC) $(CFLAGS) myprog.c -o myprog 
cmds: cmds.c 
	$(CC) $(CFLAGS) cmds.c -o cmds 
cmdreverse: cmdreverse.c
	$(CC) $(CFLAGS) cmdreverse.c -o cmdreverse
fork-data: fork-data.c
	$(CC) $(CFLAGS) fork-data.c -o fork-data
fork-wait: fork-wait.c
	$(CC) $(CFLAGS) fork-wait.c -o fork-wait
fork-file: fork-file.c
	$(CC) $(CFLAGS) fork-file.c -o fork-file
catcount: catcount.c
	$(CC) $(CFLAGS) catcount.c -o catcount
.PHONY: clean
clean:
	/bin/rm -rf $(EXECS)
