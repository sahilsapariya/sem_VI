%{
void yyerror(char *s);
int yylex();

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>

int yydebug = 1;

void print(int num);
int symbols[52];
int symbolVal(char symbol);
void updateSymbolVal(char symbol, int val);
%}

%union { int num; char id; }
%start line
%token exit_command
%token equality
%token <num> number
%token <id> identifier
%type <num> line bool_exp arit_exp term factor 
%type <id> assignment

%%

line:   line bool_exp ';'   { print($2); }
    | bool_exp ';'      { print($1); }
    | line assignment ';'   {;}
    | assignment ';'    {;}
    | line equality ';' {;}
    | equality ';'      {;}
    | line exit_command { printf("Goodbye\n"); exit(EXIT_SUCCESS); }
    | exit_command      { printf("Goodbye\n"); exit(EXIT_SUCCESS); }
    ;

bool_exp:   arit_exp            { $$ = $1; }
        | bool_exp '<' arit_exp     { $$ = $1 < $3; }
        | bool_exp '>' '=' arit_exp { $$ = $1 >= $4; }
        | bool_exp '>' arit_exp     { $$ = $1 > $3; }
        | bool_exp '<' '=' arit_exp { $$ = $1 <= $4; }
        | bool_exp '=' '=' arit_exp { $$ = $1 == $4; }
        | bool_exp '!' '=' arit_exp { $$ = $1 != $4; }
        | '!' bool_exp          { $$ = !$2; }
        | bool_exp '&' '&' arit_exp { $$ = $1 && $4; }
        | bool_exp '|' '|' arit_exp { $$ = $1 || $4; }
        | '+' '+' identifier        { updateSymbolVal($3, symbolVal($3) + 1); $$ = symbolVal($3); }
        | '-' '-' identifier        { updateSymbolVal($3, symbolVal($3) - 1); $$ = symbolVal($3); }
        ;

arit_exp:   term            { $$ = $1; }
        | arit_exp '+' term { $$ = $1 + $3; }
        | arit_exp '-' term { $$ = $1 - $3; }
        ;

term:   factor          { $$ = $1;  }
        | term '*' factor   { $$ = $1 * $3;  }
        | term '/' factor   { $$ = $1 / $3;  }
    | term '^' factor   { $$ = pow($1, $3); }
    | term '%' factor   { $$ = $1 % $3; }
    | '-' factor        { $$ = $2 * -1; }
    ;

factor: number          { $$ = $1; }
    | identifier        { $$ = symbolVal($1); }
        | '(' bool_exp ')'  { $$ = $2; }
    ;

assignment: identifier '=' bool_exp { updateSymbolVal($1, $3); printf("> %c <- %i\n> ", $1, $3); } ;

%%

void print(int num) {
    printf("> Result: %i\n< ", num);
}

int computeSymbolIndex(char token) {
    int idx = -1;
    if (islower(token)) {
        idx = token - 'a' + 26;
    }
    else if (isupper(token)) {
        idx = token - 'A';
    }
    return idx;
}

/* Returns the value of a given symbol */
int symbolVal(char symbol) {
    int bucket = computeSymbolIndex(symbol);
    return symbols[bucket];
}

void updateSymbolVal(char symbol, int val) {
    int bucket = computeSymbolIndex(symbol);
    symbols[bucket] = val;
}

int main(void) {
    printf("Calculator made with lex and yacc\n");
    printf("Usage: <Arithmetic or boolean expression> <semicolon>\n");
    printf("e.g: 2+2;\n\n< ");
    return yyparse();
}

void yyerror(char *s) { fprintf(stderr, "Error: %s\n", s); }