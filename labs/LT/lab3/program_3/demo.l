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