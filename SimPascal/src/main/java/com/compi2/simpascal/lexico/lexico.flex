package com.compi2.simpascal.lexico;

import com.compi2.simpascal.sintactico.sym;
import com.compi2.simpascal.instrucciones.Errores;
import java.util.LinkedList;
import java_cup.runtime.*;

%%


%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

%class Lexico
%public
%char
%unicode
%ignorecase
%cup
%line
%column

//Palabras Reservadas ---------------------------------------------------------------------------------
PR_ARRAY        =   "array"

PR_BEGIN        =   "begin"
PR_BOOLEAN      =   "boolean"

PR_CHAR         =   "char"
PR_CONST        =   "const"

PR_DO           =   "do"
PR_DOWNTO       =   "downto"

PR_ELSEIF         =   "else if"
PR_ELSE         =   "else"
PR_END          =   "end"

PR_FILE         =   "file"
PR_FOR          =   "for"
PR_FUNCTION     =   "function"

PR_GOTO         =   "goto"

PR_IF           =   "if"
PR_IN           =   "in"
PR_INTEGER      =   "integer"

PR_LABEL        =   "label"

PR_NIL          =   "nil"

PR_OF           =   "of"
PR_ON           =   "on"

PR_PACKED       =   "packed"
PR_PROCEDURE    =   "procedure"
PR_PROGRAM      =   "program"

PR_READLN       =   "readln"
PR_REAL         =   "real"
PR_RECORD       =   "record"
PR_REPEAT       =   "repeat"

PR_SET          =   "set"
PR_STRING       =   "string"

PR_THEN         =   "then"
PR_TO           =   "to"
PR_TYPE         =   "type"

PR_UNTIL        =   "until"

PR_VAR          =   "var"

PR_WHILE        =   "while"
PR_WITH         =   "with"
PR_WRITELN      =   "writeln"

//Secuencias de Escape---------------------------------------------------------------------------------
SALTO           =   \r|\n|\r\n
BLANCOS         =   [\ \r\t\f\n]+

//Comentarios------------------------------------------------------------------------------------------
COMEN_UL        =   "{".*"}"
COMEN_ML        =   "{"\*[\s\S]*?\*"}"

//Datos------------------------------------------------------------------------------------------------
ENTERO          =   [0-9]+
DECIMAL         =   [0-9]+"."[0-9]+
CARACTER        =   \'([^\n\\'])\' 
CADENA          =   [']([^\'])*[']
ID              =   [a-zA-Z0-9]+

//Operadores Aritmeticos-------------------------------------------------------------------------------
S_MAS           =   "+"
S_MENOS         =   "-"
S_MULTI         =   "*"
S_DIV           =   "div"
S_MOD           =   "mod"
S_ASIGNACION    =   ":="

//Operadores Relativos---------------------------------------------------------------------------------
S_IGUAL_A       =   "="
S_DIF_A         =   "<>"
S_MAY           =   ">"
S_MEN           =   "<"
S_IGUAL_MAY     =   "<="
S_IGUAL_MEN     =   ">="

//Operadores Logicos-----------------------------------------------------------------------------------
OL_OR           =   "or"
OL_AND          =   "and"
OL_NOT          =   "not"

//Signos de Agrupacion---------------------------------------------------------------------------------
O_LLAVE         =   "\["
C_LLAVE         =   "\]"
O_PAREN         =   "("
C_PAREN         =   ")"
O_CORCH         =   "{"
C_CORCH         =   "}"

//Simbolos varios--------------------------------------------------------------------------------------
PUNTO_COMA      =   ";"
COMA            =   ","
PUNTO           =   "."
DOS_PUNTOS      =   ":"


%%

<YYINITIAL> {PR_ARRAY}      { return new Symbol(sym.ARRAY, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_BEGIN}      { return new Symbol(sym.BEGIN, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_BOOLEAN}    { return new Symbol(sym.BOOLEAN, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_CHAR}       { return new Symbol(sym.CHAR, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_CONST}      { return new Symbol(sym.CONST, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_DO}         { return new Symbol(sym.DO, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_DOWNTO}     { return new Symbol(sym.DOWNTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_ELSEIF}     { return new Symbol(sym.ELSEIF, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_ELSE}       { return new Symbol(sym.ELSE, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_END}        { return new Symbol(sym.END, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_FILE}       { return new Symbol(sym.FILE, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_FOR}        { return new Symbol(sym.FOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_FUNCTION}   { return new Symbol(sym.FUNCTION, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_GOTO}       { return new Symbol(sym.GOTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_IF}         { return new Symbol(sym.IF, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_IN}         { return new Symbol(sym.IN, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_INTEGER}    { return new Symbol(sym.INTEGER, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_LABEL}      { return new Symbol(sym.LABEL, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_NIL}        { return new Symbol(sym.NIL, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_OF}         { return new Symbol(sym.OF, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_ON}         { return new Symbol(sym.ON, yyline, yycolumn, yytext()); }

<YYINITIAL> {PR_PACKED}     { return new Symbol(sym.PACKED, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_PROCEDURE}  { return new Symbol(sym.PROCEDURE, yyline, yycolumn, yytext());    }
<YYINITIAL> {PR_PROGRAM}    { return new Symbol(sym.PROGRAM, yyline, yycolumn, yytext());    }

<YYINITIAL> {PR_READLN}     { return new Symbol(sym.READLN, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_REAL}       { return new Symbol(sym.REAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {PR_RECORD}     { return new Symbol(sym.RECORD, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_REPEAT}     { return new Symbol(sym.REPEAT, yyline, yycolumn, yytext());   }

<YYINITIAL> {PR_SET}        { return new Symbol(sym.SET, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_STRING}     { return new Symbol(sym.STRING, yyline, yycolumn, yytext());   }

<YYINITIAL> {PR_THEN}       { return new Symbol(sym.THEN, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_TO}         { return new Symbol(sym.TO, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_TYPE}       { return new Symbol(sym.TYPE, yyline, yycolumn, yytext());   }

<YYINITIAL> {PR_UNTIL}      { return new Symbol(sym.UNTIL, yyline, yycolumn, yytext());   }

<YYINITIAL> {PR_VAR}        { return new Symbol(sym.VAR, yyline, yycolumn, yytext());   }

<YYINITIAL> {PR_WHILE}      { return new Symbol(sym.WHILE, yyline, yycolumn, yytext());   }
<YYINITIAL> {PR_WITH}       { return new Symbol(sym.WITH, yyline, yycolumn, yytext());     }
<YYINITIAL> {PR_WRITELN}    { return new Symbol(sym.WRITELN, yyline, yycolumn, yytext());    }


<YYINITIAL> {DECIMAL}       { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {ENTERO}        { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }
<YYINITIAL> {CARACTER}      { 
                                char c = yytext().charAt(1); 
                                return new Symbol(sym.CARACTER, yyline, yycolumn, c);
                            }
<YYINITIAL> {CADENA}        { String cadena = yytext();
                              cadena = cadena.substring(1, cadena.length()-1);
                              return new Symbol(sym.CADENA, yyline, yycolumn, cadena); }

<YYINITIAL> {O_LLAVE}    { return new Symbol(sym.L_LLAVE, yyline, yycolumn, yytext()); }
<YYINITIAL> {C_LLAVE}    { return new Symbol(sym.R_LLAVE, yyline, yycolumn, yytext()); }
<YYINITIAL> {O_PAREN}    { return new Symbol(sym.L_PAREN, yyline, yycolumn, yytext()); }
<YYINITIAL> {C_PAREN}    { return new Symbol(sym.R_PAREN, yyline, yycolumn, yytext()); }
<YYINITIAL> {O_CORCH}    { return new Symbol(sym.L_CORCH, yyline, yycolumn, yytext()); }
<YYINITIAL> {C_CORCH}    { return new Symbol(sym.R_CORCH, yyline, yycolumn, yytext()); }


<YYINITIAL> {PUNTO_COMA}    { return new Symbol(sym.PUNTOCOMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {COMA}          { return new Symbol(sym.COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO}         { return new Symbol(sym.PUNTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOS_PUNTOS}    { return new Symbol(sym.DOSPUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {S_MAS}         { return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_MENOS}       { return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_MULTI}       { return new Symbol(sym.MULTI, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_DIV}         { return new Symbol(sym.DIV, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_MOD}         { return new Symbol(sym.MOD, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_ASIGNACION}  { return new Symbol(sym.ASIGNACION, yyline, yycolumn, yytext()); }


<YYINITIAL> {S_IGUAL_A}     { return new Symbol(sym.IGUALA, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_DIF_A}       { return new Symbol(sym.DIFERENTEA, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_MAY}         { return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_MEN}         { return new Symbol(sym.MENOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_IGUAL_MAY}   { return new Symbol(sym.MAYORIGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {S_IGUAL_MEN}   { return new Symbol(sym.MENORIGUAL, yyline, yycolumn, yytext()); }

<YYINITIAL> {OL_OR}         { return new Symbol(sym.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {OL_AND}        { return new Symbol(sym.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {OL_NOT}        { return new Symbol(sym.NOT, yyline, yycolumn, yytext()); }


<YYINITIAL> {ID}            { return new Symbol(sym.ID, yyline, yycolumn, yytext()); }

<YYINITIAL> {BLANCOS}       { /*Ignorar*/ }
<YYINITIAL> {COMEN_UL}      { /*Ignorar*/ }
<YYINITIAL> {COMEN_ML}      { /*Ignorar*/ }
<YYINITIAL> {SALTO}         { /*Ignorar*/ }

<YYINITIAL> .               { listaErrores.add(new Errores("LEXICO", "El caracter " + yytext() + " NO pertenece al lenguaje", yyline, yycolumn));}