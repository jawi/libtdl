grammar AdvTdl;

@header {
/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;
}

@members {
    private int stageCount = 10;

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
    : DEFINE? ( termDecl | timerDecl | rangeDecl | edgeDecl ) WS*
    ;

termDecl
    : name=TERM_NAME ASSIGN
      ( ( MASK EQUALS_TO mask=number ) ',' ( VALUE EQUALS_TO value=number ) 
      | ( mask=number '^' value=number )
      )
    ;

timerDecl
    : name=TIMER_NAME ASSIGN
      ( value=number { validRange($value.text, 1, 0xFFFFFFFFL) }? unit=TIME_UNIT )
    ;

rangeDecl
    : name=RANGE_NAME ASSIGN
      ( lowerBound=number '..' upperBound=number ) { Long.decode($lowerBound.text) < Long.decode($upperBound.text) }?
    ;

edgeTermDecl
    : ( RISING | FALLING | BOTH | NEITHER ) EQUALS_TO number
    ;

edgeDecl
    : name=EDGE_NAME ASSIGN
      edgeTermDecl ( ',' edgeTermDecl )*
    ;

/* STAGE DEFINITION RULES */

stageDef
    : STAGE n=decNumber { validRange($n.text, 1, stageCount) }? ':'
      CAPTURE captureExpr=termExpr
      WHEN ifExpr=termExpr ( OCCURS occurrence=decNumber { validRange($occurrence.text, 1, 0xFFFFF) }? )? whenClause
      ELSE elseExpr=termExpr elseClause
    ;

whenClause
    : ( START | STOP | CLEAR ) TIMER_NAME
    | ( START | STOP ) CAPTURE
    | GOTO NEXT
    ;

elseClause
    : GOTO n=decNumber { validRange($n.text, 1, stageCount) }?
    ;

termExpr
    : NOP
    | ANY 
    | expr
    ;

expr
    : '(' expr ')'
    | expr '^' expr
    | expr '&' expr
    | expr '|' expr
    | '~' expr
    | ( TERM_NAME | TIMER_NAME | RANGE_NAME | EDGE_NAME )
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
RISING      : 'rising' ;
FALLING     : 'falling' ;
BOTH        : 'both' ;
NEITHER     : 'neither' ;
STAGE       : 'stage' ;
CAPTURE     : 'capture' ;
NOP         : 'nop' ;
ANY         : 'any' ;
WHEN        : 'when' ;
OCCURS      : 'occurs' ;
START       : 'start' ;
STOP        : 'stop' ;
CLEAR       : 'clear' ;
GOTO        : 'goto' ;
NEXT        : 'next' ;
ELSE        : 'else' ;

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

TIMER_NAME
    : 'timer' ('1' | '2')
    ;

RANGE_NAME
    : 'range' ('1' | '2')
    ;

EDGE_NAME
    : 'edge' ('1' | '2')
    ;

// EOF