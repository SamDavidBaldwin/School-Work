#include <stdio.h>
#include <stdlib.h>
struct course_t {
    int  ID;    
    int  enrollment;   
    char title[32];    
    char prof[32];    
};
 
 struct course_node_t {
     int                 ID;
     int                 enrollment;
     char*               title;
     char*               prof;                             
     struct course_node_t*    next;
 };

int main(void){
    struct course_t* csci206_s21;
    csci206_s21 = (struct course_t*)malloc(sizeof(struct course_t));
    struct course_node_t* one_course_node;
    one_course_node = (struct course_node_t*)malloc(sizeof(struct course_node_t));

    printf("%ld\n", sizeof(*csci206_s21));
    printf("%ld\n", sizeof(*one_course_node));
    return 0;
};
