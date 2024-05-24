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

// array of dictionary words
char words[N][LENGTH+1];

// number of words in the dictionary
int num_words = 0;

// Returns true if word is in dictionary else false
bool check(const char *word)
{
    for(int i = 0; i < num_words; i++){
        if(strcasecmp(word, words[i])==0){
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
    //While the file exists
    if(fd == NULL){
        return false;
    }

    int i = 0;
    while(fgets(words[i], LENGTH+1, fd)!=0){
        if(strlen(words[i])==1){
            continue;
        }
        //Remove the trailing character 
        words[i][strlen(words[i])-1] = 0;
        i++;
    }
    num_words = i;
    fclose(fd);
    return true;
}

// Return the number of words stored in the dictionary.
unsigned int size(void)
{
    return num_words;

}
