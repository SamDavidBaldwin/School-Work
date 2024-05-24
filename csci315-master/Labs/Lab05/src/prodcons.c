/*
 * Copyright (c) 2013 Bucknell University
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Author: L. Felipe Perrone (perrone@bucknell.edu)
 */

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h> 
#include <unistd.h>
#include <pthread.h>

#include "circular-list.h" 

#define SCALE_FACTOR 1000

struct circular_list mylist;

void *producer (void *param) {
  item i;
  unsigned int seed = 1234;
  int hold;

  while (true) {
    
    hold = rand_r(&seed);
    printf("rand: %d\n", hold);
    usleep(SCALE_FACTOR * seed / hold); 
    
    hold = rand_r(&seed);
    printf("rand: %d\n", hold);
    i = (item) (((double) hold) / RAND_MAX);

    if (circular_list_insert(&mylist, i) == -1) {
      printf("PRODUCER: error condition\n");
    } else {
      printf("PRODUCER: produced value %lf\n", i);
    }
  }
}

void *consumer (void *param) {
  item i;
  unsigned int seed = 999;
  int hold;

  while (true) {
    hold = rand_r(&seed);
    printf("random number (3): %d\n", hold);
    usleep(SCALE_FACTOR * seed / hold);

    if (circular_list_remove(&mylist, &i) == -1) {
      printf("CONSUMER: error condition\n");
    } else {
      printf("CONSUMER: consumed value %lf\n", i);
    }
  }
}

int main (int argc, char *argv[]) {
  if (argc < 4) {
    printf("Usage: ./prodcons [num_prod] [num_cons] [sleep_time]\n");
    exit(-1);
  }
  
  int prod_num = atoi(argv[1]); 
  int prod_con = atoi(argv[2]);
  int sleep_time = atoi(argv[3]);
  
  circular_list_create(&mylist, 20);
  
  pthread_t prod_threads[prod_num];
  int ret_p;
  for (int i = 0; i < prod_num; i++) {
    ret_p = pthread_create(&prod_threads[i], NULL, producer, (void*)&i);
    if (ret_p == -1) {
      perror("Error: failed to create thread!");
      exit(-1);
    }
  }
  pthread_t cons_threads[prod_con];
  int ret_c;
  for (int i = 0; i < prod_con; i++) {
    ret_c = pthread_create(&cons_threads[i], NULL, consumer, (void*)&i);
    if (ret_c == -1) {
      perror("Error: failed to create thread!");
      exit(-1);
    }
  }
  sleep(sleep_time);
  return (0);
}


