grammar BasicTdl;

@header {
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;
}

@members {
    private int stageCount = 4;

    private static boolean validRange(String text, long lowerBound, long upperBound) {
      long result = -1L;
      try {
        result = Long.decode(text);
      } catch (NumberFormatException ignored) {
        // Ignore invalid numbers...
      }
      return ( result >= lowerBound && result <= upperBound );
    }
    
    public void setStageCount(int stages) {
      stageCount = stages;
    }
}

/* PARSER RULES */

prog
    : ( decl | stageDef )* EOF!
    ;

/* DECLARATION RULES */

decl
    : DEFINE? ( termDecl ) WS*
    ;

termDecl
    : name=TERM_NAME ASSIGN
      ( ( MASK EQUALS_TO mask=number ) ',' ( VALUE EQUALS_TO value=number ) 
      | ( mask=number '^' value=number )
      )
    ;

/* STAGE DEFINITION RULES */

stageDef
    : STAGE n=decNumber { validRange($n.text, 1, stageCount) }? ':'
      ACTIVATE activeClause ','
      WHEN expr whenClause
    ;

activeClause
    : ( ON n=decNumber { validRange($n.text, 1, stageCount-1) }? )
    | IMMEDIATELY
    ;

whenClause
    : START CAPTURE ( DELAY n=decNumber { validRange($n.text, 1, 0xFFFF) }? '#' )?
    | GOTO NEXT
    ;

expr
    : '~' expr
    | TERM_NAME
    ;

/* SUPPORTING PARSER RULES */

number
    : ( BIN_LITERAL | OCT_LITERAL | HEX_LITERAL | DEC_LITERAL )
    ;
    
decNumber
    : DEC_LITERAL
    ;

/* LEXER RULES */

// Merely used for informational purposes; no real value
COMMENT
    :   '//' ~('\n' | '\r')* ('\r' | '\n') -> skip
    ;
    
// Used for readability; no real value
WS
    :   (' ' | '\t' | '\r' | '\n')+ -> skip
    ;

DEFINE      : 'define' ;
ASSIGN      : 'as' ;
EQUALS_TO   : '=' ;
MASK        : 'mask' ;
VALUE       : 'value' ;
STAGE       : 'stage' ;
CAPTURE     : 'capture' ;
WHEN        : 'when' ;
START       : 'start' ;
STOP        : 'stop' ;
GOTO        : 'goto' ;
NEXT        : 'next' ;
ACTIVATE    : 'activate' ;
ON          : 'on' ;
IMMEDIATELY : 'immediately' ;
DELAY       : 'delay' ;


fragment
BIN_DIGIT
    : '0'..'1'
    ;

BIN_LITERAL
    : '0' ('b' | 'B') BIN_DIGIT+
    ;

fragment
HEX_DIGIT
    : '0'..'9' | 'a'..'f' | 'A'..'F'
    ;

HEX_LITERAL
    : '0' ('x' | 'X') HEX_DIGIT+
    ;
    
fragment
OCT_DIGIT
    : '0'..'7'
    ;

OCT_LITERAL
    : '0' OCT_DIGIT+
    ;    

fragment
DEC_DIGIT
    : '0'..'9'
    ;

DEC_LITERAL
    : '0'
    | '1'..'9' DEC_DIGIT*
    ;

TIME_UNIT
    : ('n' | 'u' | 'm')? 's'
    ;

TERM_NAME
    : 'term'? ('a'..'j' | 'A'..'J')
    ;

// EOF