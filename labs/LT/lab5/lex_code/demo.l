%{
#include <stdio.h>
%}

%%

int|float|char|double|main|return      { printf("Keyword: %s\n", yytext); }
[a-zA-Z_][a-zA-Z0-9_]*     { printf("Identifier: %s\n", yytext); }
[+\-*/=]                   { printf("Operator: %s\n", yytext); }
[0-9]+                     { printf("Constant: %s\n", yytext); }
[;,\(\)\{\}]               { printf("Separator: %s\n", yytext); }

[ \t\n]                    ; // Ignore whitespace

.                          { /* Ignore unrecognized characters */ }

%%

int yywrap(void) {
    return 1;
}

int main() {
    yylex();
    return 0;
}
