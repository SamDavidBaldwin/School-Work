CC=gcc
CFLAGS=-std=c99 -Wall -Werror -g
SRC=fsm.c tokens.c
OBJF=fsm.o tokens.o
EXE=fsm sha_sum speller 

all: $(EXE)

%.o:%.c tokens.h
	$(CC) $(CFLAGS) -c $< -o $@

fsm: fsm.o tokens.o
	$(CC) -o $@ fsm.o tokens.o

#%.o:% sha256.h
#	$(CC) $(CFLAGS) -c $< -o $@

sha_sum: sha_sum.o sha256.o
	$(CC) -o $@ sha_sum.o sha256.o

#%.o:%.c dictionary.h
#	$(CC) $(CFLAGS) -c $< -o $@

speller: speller.o dictionary.o
	$(CC) -o $@ speller.o dictionary.o

clean: 
	/usr/bin/rm -f *.o *~ $(EXE)
