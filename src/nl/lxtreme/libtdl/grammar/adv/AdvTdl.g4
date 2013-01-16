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

import nl.lxtreme.libtdl.grammar.*;
}

/* PARSER RULES */

prog
    : ( decl | stageDef )* EOF!
    ;

/* DECLARATION RULES */

decl
    : ( termDecl | timerDecl | rangeDecl | edgeDecl ) WS*
    ;

termDecl
    : name=TERM_NAME ASSIGN
      ( ( MASK EQUALS_TO mask=number ) ',' ( VALUE EQUALS_TO value=number )
      | ( MASK EQUALS_TO number ) { notifyErrorListeners("missing term value"); } 
      | ( mask=number '^' value=number )
      | ( number ) { notifyErrorListeners("missing term value"); }
      | { notifyErrorListeners("missing mask and value"); }
      )
    ;

timerDecl
    : name=TIMER_NAME ASSIGN
      ( value=number unit=TIME_UNIT
      | number { notifyErrorListeners("missing time unit"); }
      | { notifyErrorListeners("missing time value and unit"); }
      )
    ;

rangeDecl
    : name=RANGE_NAME ASSIGN
      ( lowerBound=number '..' upperBound=number
      | number '..' { notifyErrorListeners("missing upper bound"); }
      | number { notifyErrorListeners("missing upper bound"); }
      | { notifyErrorListeners("invalid range definition, needs lower and upper bound"); }
      )
    ;

edgeTermDecl
    : ( RISING | FALLING | BOTH | NEITHER ) EQUALS_TO 
      ( mask=number
      | { notifyErrorListeners("missing edge value"); }
      )
    ;

edgeDecl
    : name=EDGE_NAME ASSIGN
      ( ( terms+=edgeTermDecl ( ',' terms+=edgeTermDecl )* )
      | { notifyErrorListeners("invalid edge definition, needs at least one edge term declaration"); }
      )
    ;

/* STAGE DEFINITION RULES */

stageDef
    : ( STAGE n=decNumber ':'
      | STAGE decNumber { notifyErrorListeners("missing colon"); }
      | STAGE { notifyErrorListeners("missing stage ID"); }
      )
      ( CAPTURE captureExpr=termExpr
      | CAPTURE { notifyErrorListeners("missing capture expression"); }
      | { notifyErrorListeners("missing capture clause"); }
      )
      ( WHEN ifExpr=termExpr ( OCCURS occurrence=decNumber )? whenAction
      | WHEN termExpr ( OCCURS decNumber )? { notifyErrorListeners("missing when action"); }
      | WHEN ( OCCURS decNumber )? { notifyErrorListeners("missing when expression"); }
      | { notifyErrorListeners("missing when clause"); }
      )
      ( ELSE ON elseExpr=termExpr elseAction
      | ELSE ON termExpr { notifyErrorListeners("missing else action"); }
      | ELSE ON { notifyErrorListeners("missing else expression"); }
      | ELSE { notifyErrorListeners("missing on"); }
      | { notifyErrorListeners("missing else clause"); }
      )
    ;

whenAction
    : ( START | STOP | CLEAR ) timer=TIMER_NAME
    | ( START | STOP ) CAPTURE
    | ( GOTO NEXT
      | GOTO { notifyErrorListeners("missing next"); }
      )
    ;

elseAction
    : GOTO n=decNumber
    | GOTO { notifyErrorListeners("missing level ID"); }
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
    | term=( TERM_NAME | TIMER_NAME | RANGE_NAME | EDGE_NAME )
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

ASSIGN      : ':=' ;
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
ON          : 'on' ;

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