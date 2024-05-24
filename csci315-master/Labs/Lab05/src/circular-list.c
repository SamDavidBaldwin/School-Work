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
#include "circular-list.h" 
#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

pthread_mutex_t lock;
sem_t empty;
sem_t full;

int circular_list_create(struct circular_list *l, int size) {
  l->buffer = calloc(size, sizeof(item));
  l->start = -1;
  l->end = -1;
  l->elems = 0;
  l->size = size;
  pthread_mutex_init(&lock, NULL);
  sem_init(&empty, 0, size);
  sem_init(&full, 0, 0);
  return 0;
}

int circular_list_insert(struct circular_list *l, item i) {
    if(l->elems < l->size){
        sem_wait(&empty);
        pthread_mutex_lock(&lock);
        if(l->elems == 0){ 
            l->start = l->end = 0;
            l->buffer[0] = i;
        }
        else{ 
            l->end = (l->end + 1) % l->size;
            l->buffer[l->end] = i;
        }
        l->elems += 1;
        pthread_mutex_unlock(&lock);
        sem_post(&full);
    }
    else{
        printf("List is full, cannot add element \n");
        pthread_mutex_unlock(&lock);
        return -1;
    }
    return 0;
}

int circular_list_remove(struct circular_list *l, item *i) {
    pthread_mutex_lock(&lock);
    sem_wait(&full);
    if(l->elems == 0){
        perror("The list is empty");
        return -1;
    }
    
    *i = l->buffer[l->start];
    if(l->end == l->start){
        l->start = -1;
        l->end = -1;
    }
    l->buffer[l->start]= 0;
    l->start = (l->start + 1) % l->size;
    l->elems -= 1;
    
    pthread_mutex_unlock(&lock);
    sem_post(&empty);
    return 0;
}
