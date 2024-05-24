CC=gcc
CFLAGS=-Wall -Wfatal-errors -Werror -std=c99
LDLIBS=-lm
all: strnode_test
strnode_test: strnode.o strnode_test.o strnode.h
node_test: node.o node_test.o node.h
stack_test: stack.o node.o stack_test.o stack.h node.h
pcalc: pcalc.o node.o stack.o node.h stack.h
clean:
	rm -f strnode_test *.o
