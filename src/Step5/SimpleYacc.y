%{
// ��� ���������� ����������� � ����� GPPGParser, �������������� ����� ������, ������������ �������� gppg
    public Parser(AbstractScanner<int, LexLocation> scanner) : base(scanner) { }
%}

%output = SimpleYacc.cs

%namespace SimpleParser

%token BEGIN END CYCLE INUM RNUM ID ASSIGN SEMICOLON WHILE DO REPEAT UNTIL FOR TO WRITE RBRACKET LBRACKET IF THEN ELSE COMMA VAR

%%

progr   : block
		;

stlist	: statement 
		| stlist SEMICOLON statement 
		;

idlist  : ident
        | idlist COMMA ident
        ;

statement: assign
		| block  
		| cycle  
		| while
        | var
		| repeat
        | for
        | write
        | if
		;

ident 	: ID 
		;

var     : VAR idlist
        ;
	
assign 	: ident ASSIGN expr 
		;

expr	: ident  
		| INUM 
		;

block	: BEGIN stlist END 
		;

while   : WHILE expr DO statement
		;

repeat  : REPEAT stlist UNTIL expr
		;

for     : FOR assign TO expr DO statement
        ;
        
write   : WRITE LBRACKET expr RBRACKET
        ;

if      : IF expr THEN statement 
        | IF expr THEN statement ELSE statement
        ;

cycle	: CYCLE expr statement 
		;
	
%%
