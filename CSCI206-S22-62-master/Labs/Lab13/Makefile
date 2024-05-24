CFLAGS=-Wfatal-errors -std=c99 -g
LDLIBS=-lpthread
CC=gcc
EXEC=thread_count selection_sort thread_find_max pthread_selection_sort
 
all: $(EXEC)
 
thread_count: thread_count.o thread_count.c
	$(CC) $(CFLAGS) $(LDLIBS) thread_count.c -o thread_count
 
selection_sort: selection_sort.o selection_sort.c
	$(CC) $(CFLAGS) $(LDLIBS) selection_sort.c -o selection_sort
 
pthread_selection_sort: pthread_selection_sort.o pthread_selection_sort.c
	$(CC) $(CFLAGS) $(LDLIBS) pthread_selection_sort.c -o pthread_selection_sort
 
thread_find_max: thread_find_max.o thread_find_max.c
	$(CC) $(CFLAGS) $(LDLIBS) thread_find_max.c -o thread_find_max
 
 
clean:
	/bin/rm -f $(EXEC) *.o *~
