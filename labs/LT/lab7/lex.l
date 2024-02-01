%{
#include "y.tab.h"
void yyerror(char* s);
%}

%%
"exit"      { return exit_command; }
[0-9]+      { yylval.num = atoi(yytext); return number; }
[a-zA-Z][_a-zA-Z0-9]*    { yylval.id = yytext[0]; return identifier; }
[ \t\n]     ;
[-+*/()^%;] { return yytext[0]; }
[<>=!&|]    { return yytext[0]; }
.       { ECHO; yyerror("Unexpected character"); }
%%

int yywrap(void) { return 1; }