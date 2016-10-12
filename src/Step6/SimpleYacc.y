%{
// Эти объявления добавляются в класс GPPGParser, представляющий собой парсер, генерируемый системой gppg
    public BlockNode root; // Корневой узел синтаксического дерева 
    public Parser(AbstractScanner<ValueType, LexLocation> scanner) : base(scanner) { }
%}

%output = SimpleYacc.cs

%union { 
			public double dVal; 
			public int iVal; 
			public string sVal; 
			public Node nVal;
			public ExprNode eVal;
			public StatementNode stVal;
			public BlockNode blVal;
       }

%using ProgramTree;

%namespace SimpleParser

%token BEGIN END CYCLE ASSIGN SEMICOLON DO WHILE REPEAT UNTIL FOR TO WRITE LBRACKET RBRACKET
%token <iVal> INUM 
%token <dVal> RNUM 
%token <sVal> ID

%type <eVal> expr ident 
%type <stVal> assign statement cycle while repeat for write
%type <blVal> stlist block

%%

progr   : block { root = $1; }
		;

stlist	: statement 
			{ 
				$$ = new BlockNode($1); 
			}
		| stlist SEMICOLON statement 
			{ 
				$1.Add($3); 
				$$ = $1; 
			}
		;

statement: assign { $$ = $1; }
		| block   { $$ = $1; }
		| cycle   { $$ = $1; }
        | while   { $$ = $1; }
        | repeat  { $$ = $1; }
        | for     { $$ = $1; }
        | write   { $$ = $1; }
	;

ident 	: ID { $$ = new IdNode($1); }	
		;
	
assign 	: ident ASSIGN expr { $$ = new AssignNode($1 as IdNode, $3); }
		;

expr	: ident  { $$ = $1 as IdNode; }
		| INUM { $$ = new IntNumNode($1); }
		;

block	: BEGIN stlist END { $$ = $2; }
		;

cycle	: CYCLE expr statement { $$ = new CycleNode($2, $3); }
		;

while   : WHILE expr DO statement { $$ = new WhileNode($2, $4); }
        ;

repeat  : REPEAT stlist UNTIL expr { $$ = new RepeatNode($4, $2); }
        ;

for     : FOR assign TO expr DO statement { $$ = new ForNode($2 as AssignNode, $4, $6); }
        ;

write   : WRITE LBRACKET expr RBRACKET { $$ = new WriteNode($3); }
        ;
	
%%

