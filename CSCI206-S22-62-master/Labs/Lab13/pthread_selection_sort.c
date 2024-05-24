#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include <pthread.h>


// swap two locations (i,j) in the array

void merge(double* array, int left, int middle, int right);


struct param_t {
    double* array;
    int start;
	int len;

};

void merge_multiple(double* array, int num_elements, int num_threads){
    int left, right, middle;
    int block_size = num_elements/num_threads;
    while(block_size <= num_elements/2){
        for(int i = 0; i < num_elements; i+= block_size*2){
            left = i;
            right = i + block_size*2 - 1;
            middle = i + block_size-1;
            merge(array, left, middle, right);
        }
        block_size*=2;
    }
}

void merge(double arr[], int left, int middle, int right)
{
     int i, j, k;
     int n1 = middle - left + 1;         
     int n2 = right - middle; 

     double *L = malloc(sizeof(double) * n1);
     double *R = malloc(sizeof(double) * n2);

     memcpy(L, arr + left, n1 * sizeof(double));
     memcpy(R, arr + middle + 1, n2 * sizeof(double));

   
     i = 0; 
     j = 0; 
     k = left;
     while ((i < n1) && (j < n2))
     {
         if (L[i] <= R[j])
             arr[k++] = L[i++];
         else
             arr[k++] = R[j++];
     }

 
     while (i < n1){
		 arr[k++] = L[i++];
	}	
   
     while (j < n2){
		 arr[k++] = R[j++];
	}

    free(L);
    free(R);
}


void swap(double arr[], int i, int j) 
{
	double tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

void * selection_sort(void *param) 
{

    struct param_t *p = (struct param_t *)param;

    int i, j, min;
    for(i = p->start; i < p->start+p->len-1; i++){
        min = i;
        for(j = i+1; j< p->start+p->len; j++){
            if(p->array[j] < p->array[min]){
                min = j;
            }
        }

        swap(p->array, min, i);
    }
    return NULL;
}

/* UTILITY FUNCTIONS */
// print an array of size n
void print_array(double * a, int n)
{
	int i;
	if(n <= 16){
		// print the whole thing
		printf("[");
		for (i=0; i<n-1; i++){
			printf("%f, ", a[i]);
		}
		printf("%f]\n", a[i]);
	} else {
		// truncate the printout
		printf("[");
		// print first 8
		for (i=0; i<8; i++){	
			printf("%f, ", a[i]);
		}
		printf("..., ");
		// print last 8
		for (i=n-9; i < n-1; i++){
			printf("%f, ", a[i]);
		}
	  printf("%f]\n", a[i]);
	}
}

// pseudo random array filler
void fill_array(int seed, double * a, int n){
	if (n == 16){
		// constant data if n == 16 (do not change this!)
		const double d[16] = {4,4,4,4,7,7,7,7,1,1,1,1,5,5,5,5};
		memcpy(a, d, sizeof(double)*16);
		return;
	}
	srand(seed);
	while(n--){
		// rand returns an int between 0 and RAND_MAX, this maps it to [0...1]
		*a++ = (double)rand() / (double)RAND_MAX;
	}
}

// check if an array is sorted, return 1 if true, 0 if not.
int is_sorted(double arr[], int n)
{
	int i;
	// check for any out of order elements
	for (i=0; i<n-1; i++){
		if (arr[i] > arr[i+1])
			return 0;
	}
	return 1;
}

/* Driver program to test above functions */
int main(int argc, char *argv[])  
{
	int NUM_THREADS;              // how many threads to use
	int NUM_VALS;                 // how many values to sum
	double * a = NULL;            // the array of values

	switch (argc){
		case 1:   // no arguments, use default values
			NUM_VALS = 16;
			NUM_THREADS = 1;
			break;
		case 2:   // just one argument, NUM_VALS
			NUM_VALS = atof(argv[1]);
			NUM_THREADS = 1;
			break;
		default:  // command line sets both values
			NUM_VALS = atof(argv[1]);
			NUM_THREADS = atoi(argv[2]);
	}
	printf("%s using %d threads on array of %d elements.\n",
		argv[0],
		NUM_THREADS,
		NUM_VALS);
	if (NUM_VALS % NUM_THREADS != 0){
		printf("ERROR: array size must be evenly divisible by the number of threads!\n");
		exit(1);
	}

	a = malloc(sizeof(double)*NUM_VALS); 
	fill_array(7891, a, NUM_VALS);
    
    pthread_t* worker;
    struct param_t* param;
    int length = NUM_VALS/NUM_THREADS;

    worker = malloc(sizeof(pthread_t)*NUM_THREADS);
    param = malloc(sizeof(struct param_t)*NUM_THREADS);
    
    //create threads
    for(int i = 0; i < NUM_THREADS; i++){
        param[i].array = a;
        param[i].start = i*length;
        param[i].len = length;
        int return_value = pthread_create(&(worker[i]), NULL, selection_sort, (void*)&(param[i]));
    
    }
    
    for(int i = 0; i < NUM_THREADS; i++){
        pthread_join(worker[i], NULL);
    }

    //merge results of threads
    merge_multiple(a, NUM_VALS, NUM_THREADS);


	printf("Generated array ");
	print_array(a, NUM_VALS);

	//printf("Sorting. . .\n");
	//selection_sort(a, NUM_VALS);

	printf("Sorted array is ");
	print_array(a, NUM_VALS);

	printf("Is sorted: %s\n",
	is_sorted(a, NUM_VALS) ? "YES": "NO -- FAILED");

  free(a);

  return 0;
}

