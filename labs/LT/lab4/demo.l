letter [a-zA-Z]
digit [0-9]

start \/\*
end \*\/

%{
    int otherchars = 0;
    int nline = 0;    
    
%}

%%
{start}.*?{end} ;
. otherchars++;
%%

int yywrap(void) {
    return 1;
}

int main(void) {
    yylex();

    printf("otherchars = %d\n", otherchars);
    return 0;
}