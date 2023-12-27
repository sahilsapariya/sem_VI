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