/*
 * Implements a dictionary's functionality
 * <Your Name>
 */

#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <strings.h>

#include "dictionary.h"

// Maximum number of words in the dictionary
#define N 150000
#define BINS 27

// array of dictionary words
char words[BINS][N][LENGTH+1];

// number of words in the dictionary
int num_words = 0;
// Returns true if word is in dictionary else false
bool check(const char *word)
{
    //Gets the hash of the word
    int hold = hash(word);
    //For all entries in that value of the hash function checks if the word is equal 
    for(int i = 0; i< num_words; i++){
        if (strcasecmp(word, words[hold][i])==0){
            return true;
        }
    }
    return false;
}

// Loads dictionary into memory, returning true if successful else false
bool load(const char *dictionary_filename)
{
    FILE* fd;       
    fd = fopen(dictionary_filename, "r");

    if(fd == NULL){
        return false;
    }
    int index;
    char hold[LENGTH+1];
    //Read the word into a temporary array
    while(fgets(hold, LENGTH+1,fd)!=0){
        if(strlen(hold)==1){
           continue;
       }

        hold[strlen(hold)-1] = 0;
        index = hash(hold);
        strcpy(words[index][num_words], hold);
        num_words++; 
    }
    fclose(fd);
    return true;
}

// Return the number of words stored in the dictionary.
unsigned int size(void)
{
    return num_words;

}

unsigned int hash(const char * word){
    int value;
    value = word[0];
    if(value >= 97 && value <= 122){
        value -=97;
    }
    else if (value>= 65 && value <= 90){
        value -= 65;
    }
    else{
        value = 27;
    }

        return value;
}

