/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/*************/
/* USER CODE */
/*************/
import java_cup.runtime.*;

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/
   
/*****************************************************/ 
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/ 
%class Lexer

/********************************************************************/
/* The current line number can be accessed with the variable yyline */
/* and the current column number with the variable yycolumn.        */
/********************************************************************/
%line
%column

/*******************************************************************************/
/* Note that this has to be the EXACT same name of the class the CUP generates */
/*******************************************************************************/
%cupsym TokenNames

/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup

/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/   
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied verbatim (letter to letter) into the Lexer class code.     */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                                                          */  
/*****************************************************************************/   
%{
	/*********************************************************************************/
	/* Create a new java_cup.runtime.Symbol with information about the current token */
	/*********************************************************************************/
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}
	/*******************************************/
	/* Enable line number extraction from main */
	/*******************************************/
	public int getLine() { return yyline + 1; } 
	public int getCharPos() { return yycolumn;   }
	/**********************************************/
	/* Enable token position extraction from main */
	/**********************************************/
	public int getTokenStartPosition() { return yycolumn + 1; } 

	/*******************************************/
	/**************Paresrs**********************/
	/*******************************************/
	private Integer parseInteger(String num) {
    	int parsedInt = Integer.parseInt(num);
    	if (num.matches("0+[1-9][0-9]*")) {
        	return -1;
    	} else if (parsedInt > 32767) {
        	return -1;
    	}
    		return parsedInt;
	}
%}

/***********************/
/* MACRO DECALARATIONS */
/***********************/
LineTerminator	= \r|\n|\r\n
WhiteSpace	= {LineTerminator} | [ \t]
INTEGER		= 0 | [1-9][0-9]*
LETTER 		= [a-zA-Z]
DIGIT 		= [0-9]
ID		= {LETTER}({DIGIT}|{LETTER})*
LPAREN 		= \(
RPAREN 		= \)
LBRACK 		= \[
RBRACK 		= \]
LBRACE 		= \{
RBRACE 		= \}
NIL 		= nil
PLUS 		= \+
MINUS 		= -
TIMES 		= \*
DIVIDE 		= "/"
COMMA 		= ,
DOT 		= \.
SEMICOLON 	= ;
TYPE_INT 	= int
TYPE_VOID 	= void
ASSIGN 		= :=
EQ 		= =
LT 		= "<"
GT 		= ">"
ARRAY 		= array
CLASS 		= class
EXTENDS		= extends
RETURN 		= return
WHILE 		= while
IF 		= if
NEW 		= new
STRING 		= \"{LETTER}*\"
TYPE_STRING 	= string
COMMENT_CHAR 	= {LPAREN}| {RPAREN}| {LBRACK}| {RBRACK}|		 							{LBRACE}| {RBRACE}| {DIGIT}| {LETTER}| \t| \?| \!| {PLUS}| {MINUS}| {DOT}| {SEMICOLON}| " "
COMMENT_CHAR_WITH_LINETERMINATORS 	= {COMMENT_CHAR} | {LineTerminator}
NO_COMMENT_CHAR = {ASSIGN}| {EQ}| {LT}| {GT}| {COMMA}
ALL_COMMENT_CHARS = {COMMENT_CHAR}| {TIMES}| {DIVIDE}
COMMENT_CHAR_AFT = {COMMENT_CHAR}| {TIMES}
COMMENT_CHAR_WOT = {COMMENT_CHAR}| {DIVIDE}
TYPE_ONE_COMMENT = {DIVIDE}{DIVIDE}{ALL_COMMENT_CHARS}*{LineTerminator}
TYPE_TWO_CONTENT = ({COMMENT_CHAR_WITH_LINETERMINATORS}+{COMMENT_CHAR_WOT}*{TIMES}*{COMMENT_CHAR_AFT}*{COMMENT_CHAR_WITH_LINETERMINATORS}+)*
TYPE_TWO_COMMENT = {DIVIDE}{TIMES}+{TYPE_TWO_CONTENT}*{TIMES}+{DIVIDE}
TYPE_TWO_ERROR_ONE = {DIVIDE}{TIMES}+{TYPE_TWO_CONTENT}*{NO_COMMENT_CHAR}+{TYPE_TWO_CONTENT}*{TIMES}+{DIVIDE}
TYPE_TWO_ERROR_TWO = {DIVIDE}{TIMES}+{TYPE_TWO_CONTENT}*{NO_COMMENT_CHAR}*{TYPE_TWO_CONTENT}*
TYPE_ONE_ERROR = {DIVIDE}{DIVIDE}({ALL_COMMENT_CHARS}*{NO_COMMENT_CHAR}+{ALL_COMMENT_CHARS}*)*{LineTerminator}
LEADING_ZERO = 0+{INTEGER}

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

<YYINITIAL> {
{TYPE_ONE_COMMENT}   { /* just skip what was found, do nothing */ }
{TYPE_ONE_ERROR}     { return symbol(TokenNames.error);}
{TYPE_TWO_COMMENT}   { /* just skip what was found, do nothing */ }
{TYPE_TWO_ERROR_ONE} { return symbol(TokenNames.error);}
{TYPE_TWO_ERROR_TWO} { return symbol(TokenNames.error);}
{LEADING_ZERO} { return symbol(TokenNames.error);}

{LPAREN} { return symbol(TokenNames.LPAREN);}
{RPAREN} { return symbol(TokenNames.RPAREN);}
{LBRACK} { return symbol(TokenNames.LBRACK);}
{RBRACK} { return symbol(TokenNames.RBRACK);}
{LBRACE} { return symbol(TokenNames.LBRACE);}
{RBRACE} { return symbol(TokenNames.RBRACE);}
{NIL}    { return symbol(TokenNames.NIL);}
{PLUS}   { return symbol(TokenNames.PLUS);}
{MINUS}  { return symbol(TokenNames.MINUS);}
{TIMES}	 { return symbol(TokenNames.TIMES);}
{DIVIDE} { return symbol(TokenNames.DIVIDE);}
{COMMA}  { return symbol(TokenNames.COMMA);}
{DOT}    { return symbol(TokenNames.DOT);}
{SEMICOLON}   { return symbol(TokenNames.SEMICOLON);}
{TYPE_INT}    { return symbol(TokenNames.TYPE_INT);}
{TYPE_VOID}   { return symbol(TokenNames.TYPE_VOID);}
{TYPE_STRING} { return symbol(TokenNames.TYPE_STRING);}
{ASSIGN}  { return symbol(TokenNames.ASSIGN);}
{EQ}      { return symbol(TokenNames.EQ);}
{LT}	  { return symbol(TokenNames.LT);}
{GT}	  { return symbol(TokenNames.GT);}
{ARRAY}   { return symbol(TokenNames.ARRAY);}
{CLASS}   { return symbol(TokenNames.CLASS);}
{EXTENDS} { return symbol(TokenNames.EXTENDS);}
{RETURN}  { return symbol(TokenNames.RETURN);}
{WHILE}   { return symbol(TokenNames.WHILE);}
{IF}	  { return symbol(TokenNames.IF);}
{NEW}	  { return symbol(TokenNames.NEW);}
{INTEGER} { if(parseInteger(yytext())==-1) {return symbol(TokenNames.error);} else { return symbol(TokenNames.INT, parseInteger(yytext()));}}
{ID}	  { return symbol(TokenNames.ID, new String( yytext()));}
{STRING}  { return symbol(TokenNames.STRING, new String( yytext()));}
{WhiteSpace} { /* just skip what was found, do nothing */ }
<<EOF>>	  { return symbol(TokenNames.EOF);}
[^]      { return symbol(TokenNames.error);} 
}
