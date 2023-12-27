# Lab 3 | Lex programs

## Regular expression examples


| Type                        | Regular Expression     |
|-----------------------------|------------------------|
| An integer                  | `[1-9][0-9]*`          |
| A word                      | `[a-zA-Z]+`            |
| A (possibly) signed integer | `[-+]?[1-9][0-9]*`     |
| A floating point number     | `[0-9]*\.[0-9]+`       |



## Lex code for Letters recognition

**Code:**
```lex
letter[A-Z a-z]
%%
{letter}+   printf("Letter is %s\n", yytext);
.           printf("Unrecognized character\n");
%%

int yywrap(void) {
    return 1;
}

int main(void) {
    yylex();
    return 0;
}
```

**Output:**

```
sahil
Letter is sahil

s
Letter is s

8
Unrecognized character

03sahil
Unrecognized character
Unrecognized character
Letter is sahil
```


## Lex code for counting chars, words and new lines

**Code:**
```lex
%{
    int nchar, nword, nline;
%}

%%
\n  { nline++; nchar++;}
[^ \t\n]+   { nword++; nchar += yyleng;}
.   { nchar++;}
%%

int yywrap(void) {
    return 1;
}

int main (void) {
    yylex();
    printf("Characters: %d\nWords: %d\nNew Lines: %d", nchar, nword, nline);
    
    return 0;
}
```

**Output:**

```
Hi!     this is the program 
to calculate the        characters,
words   and     new lines of the input   
texts
and below is the result
Characters: 118
Words: 22
New Lines: 5
```


## Lex code for checking the variable validity

**Code:**

```lex
letter[a-z A-Z]
digit[0-9]

%{
    int ans = 0;
%}

%%
"_"*{letter}+   ans = 1;
"_"+{digit}+ ans = 1;
"_"*{letter}+{digit}*   ans = 1;
"_"+{letter}*{digit}* ans = 1;
.   ans = 0;
%%

int yywrap(void) {
    return 1;
}

int main(void) {
    yylex();
    if(ans == 1) printf("Valid\n");
    else printf("Invalid\n");

    return 0;
}
```

**Output:**

```
variable
Valid

03
Invalid

_01_
Valid

_
Valid

$.variable
Invalid
```