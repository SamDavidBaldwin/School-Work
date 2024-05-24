/*
 * File name: fsm.c
 * Compile with: gcc -std=c99 fsm.c -o fsm
 *
 * This file contains a few finite state machines that recognize various
 * patterns.
 *
 * CSCI 206 exercises (student file)
 */
#include <stdio.h>
#include <stdbool.h>   // bool type
#include <stdlib.h>    // exit()
#include <string.h>

enum NUM_STATE {NON_DIGIT, DIGIT};  // states for a number
enum WORD_STATE {NON_LETTER, LETTER};

int const MAX_LENGTH = 16;   // max length of a int

bool my_isdigit(char);       // returns true if char is a digit, false otherwise
bool my_isalpha(char);
void parse_int(char *);      // parse int out of a string
void parse_word(char *);
int  grow_number(char *, char, int);   // increase the int by a digit
void end_number(char *, int);          // end of an int

int main(int argc, char *argv[]) {

  if (argc != 2) {
	fprintf(stderr, "Usage: %s string\n", argv[0]);
	exit(1);
  }

  //parse_int(argv[1]);
  parse_word(argv[1]);
  return 0;
}

bool my_isdigit(char c) {

  if (c >= '0' && c <= '9')
	return true;
  else
	return false;
}
bool my_isalpha(char c){
    if((c>= 65 && c<= 90)||(c>=97&&c<=122)){
        return true;
    }
    else{
        return false;
    }

}

void parse_word(char * s){
    enum WORD_STATE state = NON_LETTER;
    int len = strlen(s);
    char one_word[MAX_LENGTH+1];
    int letter_count = 0;

    for (int i = 0; i<len; i++){
        if (state == NON_LETTER){
            if(my_isalpha(s[i]) == true||my_isdigit(s[i])==true){
                state = LETTER;
                letter_count = grow_number(one_word, s[i], letter_count);
            }
        }
        else if (state == LETTER){
            if(my_isalpha(s[i]) == true||my_isdigit(s[i]) == true){
                letter_count = grow_number(one_word, s[i], letter_count);
            }
            else{
                end_number(one_word, letter_count);
                letter_count = 0;
                state = NON_LETTER;
            }
        }
        else{
            printf("error, non-existing state.\n");
        }
    }
        if(letter_count >0){
            one_word[letter_count] = 0;
            printf("one token : [%s]\n", one_word);
    }
}




void parse_int(char * s) {

  enum NUM_STATE state = NON_DIGIT;
  int  len = strlen(s);
  char one_number[MAX_LENGTH+1]; // used to store one integer
  int  digit_count = 0;          // how many digits in current int

  for (int i = 0; i < len; ++i) {
	if (state == NON_DIGIT) {
	  if (my_isdigit(s[i]) == true) {
		state = DIGIT;
		digit_count = grow_number(one_number, s[i], digit_count);
	  }
	} else if (state == DIGIT) {
	  if (my_isdigit(s[i]) == true) {
		digit_count = grow_number(one_number, s[i], digit_count);
	  } else { // end of a number
		end_number(one_number, digit_count);
		digit_count = 0;              // ready to start a new number
		state = NON_DIGIT;
	  }
	} else {
	  printf("error, non-existing state.\n");
	}
  } // end of for
  if (digit_count > 0) { // the very last token is int
	one_number[digit_count] = 0;  // terminate the string
	printf("one token : [%s]\n", one_number);	
  }
}

int grow_number(char *token, char ch, int cur_len) {

  if (cur_len < MAX_LENGTH) {
	token[cur_len] = ch;
  } else { // truncate the token
	token[cur_len] = 0;    // terminate the current one
	printf("one token : [%s]\n", token);
	cur_len = 0;              // ready to start a new token
	token[cur_len] = ch;  // the last char belongs to next int
  }
  cur_len ++;
  return cur_len;
}

void end_number(char *token, int cur_len) {

  token[cur_len] = 0;  // terminte the string
  printf("one token : [%s]\n", token);
}
