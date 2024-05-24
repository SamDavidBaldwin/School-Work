CC = gcc
CFLAGS = -std=gnu99 -Wall -g #-DDEBUG

EXECS = dlisttest

all: $(EXECS)

dnode.o: dnode.c dnode.h
dlist.o: dlist.h dlist.c dnode.h
dlisttest: dlisttest.c dnode.o dlist.o
.PHONY: clean
clean:
	/bin/rm -rf $(EXECS) dnode.o dlist.o
