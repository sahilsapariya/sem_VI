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