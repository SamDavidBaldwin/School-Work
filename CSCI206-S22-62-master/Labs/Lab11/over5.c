#include <stdio.h>
#include <string.h>
int main(int argc, char** argv) {
    char holdArr[100];
	char acctNumberStr[10];

	printf("Enter your account number: ");
    scanf("%s", holdArr); //Scan into a larger array

    strcpy(acctNumberStr, holdArr); //Copy back into the smaller array

	printf("Account number: %s\n", acctNumberStr);

	return(0);
}
