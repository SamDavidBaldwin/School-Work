#ifndef STRNODE_H_
#define STRNODE_H_
struct strnode{
    char* str;
    int length;
    struct strnode *next;
};
#endif
struct strnode *strnode_create(char *s, int length);
