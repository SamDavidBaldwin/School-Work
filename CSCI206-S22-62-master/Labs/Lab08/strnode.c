#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "strnode.h"
struct strnode *strnode_create(char *s, int length){
    struct strnode* strnode_new;
    strnode_new = (struct strnode *)malloc(sizeof(struct strnode));
    strnode_new->str = (char*)malloc(strlen(s)+1);
    strcpy(strnode_new->str,s);
    strnode_new->length = length;
    strnode_new->next = NULL;
    return strnode_new;
}

