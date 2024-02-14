# Lab9 | Remove the left factoring

**Program 1:**

Program to remove left factoring

```c
#include<stdio.h>
#include<string.h>
int main()
{
    char gram[20],part1[20],part2[20],modifiedGram[20],newGram[20],tempGram[20];
    int i,j=0,k=0,l=0,pos;
    printf("Enter Production : A->");
    gets(gram);
    for(i=0;gram[i]!='|';i++,j++)
        part1[j]=gram[i];
    part1[j]='\0';
    for(j=++i,i=0;gram[j]!='\0';j++,i++)
        part2[i]=gram[j];
    part2[i]='\0';
    for(i=0;i<strlen(part1)||i<strlen(part2);i++){
        if(part1[i]==part2[i]){
            modifiedGram[k]=part1[i];
            k++;
            pos=i+1;
        }
    }
    for(i=pos,j=0;part1[i]!='\0';i++,j++){
        newGram[j]=part1[i];
    }
    newGram[j++]='|';
    for(i=pos;part2[i]!='\0';i++,j++){
        newGram[j]=part2[i];
    }
    modifiedGram[k]='X';
    modifiedGram[++k]='\0';
    newGram[j]='\0';
    printf("\nGrammar Without Left Factoring : : \n");
    printf(" A->%s",modifiedGram);
    printf("\n X->%s\n",newGram);
}
```


**Program 2:**

Removes the indirect left factoring problem

```c
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

struct production {
    int length;
    char prod[10][10];
} typedef production;

struct grammer {
    int number;
    production nonT[26];
} typedef grammer;

grammer gram;

void printProd(production *);
void printGram();

int getF(char *str) {
    int i = 1;
    while (i < strlen(str) && str[i - 1] != '>') i++;
    while (i < strlen(str) && str[i] == ' ') i++;
    return i;
}

void setProd(char *str) {
    production *produce = &gram.nonT[str[0] - 'A'];
    int prod = 0, j;
    for (int i = getF(str); i < strlen(str); i++) {
        for (j = 0; i < strlen(str) && str[i] != '|'; i++)
            if (str[i] != ' ')
                produce->prod[prod][j++] = str[i];
        produce->prod[prod++][j++] = '\0';
    }
    produce->length = prod;
}

void copy(char *src, char *dest, int i) {
    int j = i + 1;
    do {
        dest[j - i - 1] = src[j];
    } while (src[j++] != '\0');
    if (dest[0] == '\0') {
        dest[0] = 'e';
        dest[1] = '\0';
    }
}

bool factorProd(production *prod) {
    bool prev[10] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    bool next[10] = {0};
    char c[128] = {0}, ch;
    int j = 0, maxx;
    while (true) {
        maxx = 0;
        for (int i = 0; i < 10; i++)
            if (prev[i] && strlen(prod->prod[i]) > j)
                c[prod->prod[i][j]]++;

        for (int i = 0; i < 128; i++) {
            if (c[i] > maxx) maxx = c[i], ch = i;
            c[i] = 0;
        }

        if (maxx < 2) break;

        for (int i = 0; i < 10; i++)
            prev[i] = j < strlen(prod->prod[i]) && prod->prod[i][j] == ch;
        j++;
    }

    if (j == 0) return false;

    int newNonT = 0, p_no = 0;
    while (gram.nonT[newNonT].length != 0) newNonT++;

    production *newP = &gram.nonT[newNonT];

    for (int i = 0; i < 10; i++)
        if (prev[i] && prod->prod[i][0] != '\0') {
            copy(prod->prod[i], newP->prod[p_no], j - 1);
            prod->prod[i][j] = newNonT + 'A';
            prod->prod[i][j + 1] = '\0';
            p_no++;
        }
    gram.number++;
    newP->length = p_no;

    int i = 0, num = 0;
    while (prev[i] == false) i++;
    for (i++; i < 10; i++)
        if (prev[i] && prod->prod[i][0] != '\0')
            prod->prod[i][0] = '\0', num++;

    prod->length -= num;
    return true;
}

void leftFactor() {
    for (int i = 0; i < 26; i++) {
        if (gram.number == 26) {
            printf("No new production can be formed.\n");
            return;
        }
        if (gram.nonT[i].length >= 2)
            while (factorProd(&gram.nonT[i])) {
                printf("\n\nAfter left factoring production from %c:", i + 'A');
                printGram();
            }
    }
}

int main() {
    char str[100];
    printf("Enter the number of productions: ");
    scanf("%d", &(gram.number));
    printf("\nEnter productions in the format:\n");
    printf("A -> aAb | aAab | e\n");
    printf("(Spaces will be skipped, e is null)\n");
    char c;
    for (int i = 0; i < gram.number; i++) {
        scanf("%c", &c);
        scanf("%[^\n]s", str);
        setProd(str);
    }
    printf("\nInput Grammer:");
    printGram();

    leftFactor();
    printf("\n\nFinally... ... ... ...");
    printGram();
    printf("\n");
    return 0;
}

void printProd(production *prod) {
    printf("%s ", prod->prod[0]);
    for (int i = 1; i < 10; i++)
        if (prod->prod[i][0] != '\0')
            printf("| %s ", prod->prod[i]);
}

void printGram() {
    for (int i = 0; i < 26; i++)
        if (gram.nonT[i].length) {
            printf("\n%c -> ", i + 'A');
            printProd(&gram.nonT[i]);
        }
}
```

**Output:**

```
Enter the number of productions: 1                                         

Enter productions in the format:
A -> aAb | aAab | e
(Spaces will be skipped, e is null)
A -> abc | ac | ab | xy | be | abd

Input Grammer:
A -> abc | ac | ab | xy | be | abd 

After left factoring production from A:
A -> abB | ac | xy | be 
B -> c | e | d 

After left factoring production from A:
A -> aC | xy | be 
B -> c | e | d 
C -> bB | c 

Finally... ... ... ...
A -> aC | xy | be 
B -> c | e | d 
C -> bB | c 
```