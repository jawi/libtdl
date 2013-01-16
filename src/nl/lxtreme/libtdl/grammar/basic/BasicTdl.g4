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

import nl.lxtreme.libtdl.grammar.*;
}

@parser::members {
}

/* PARSER RULES */

prog
    : ( decl | stageDef )* EOF!
    ;

/* DECLARATION RULES */

decl
    : termDecl WS*
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

/* STAGE DEFINITION RULES */

stageDef
    : ( STAGE n=decNumber ':'
      | STAGE decNumber { notifyErrorListeners("missing colon"); }
      | STAGE { notifyErrorListeners("missing stage ID"); }
      )
      ( ACTIVATE activeClause ','
      | ACTIVATE activeClause { notifyErrorListeners("missing comma"); }
      | ACTIVATE { notifyErrorListeners("missing activate clause"); }
      | { notifyErrorListeners("missing activate clause"); }
      )
      ( WHEN expr whenAction
      | WHEN expr { notifyErrorListeners("missing when action"); }
      | WHEN { notifyErrorListeners("missing when expression"); }
      | { notifyErrorListeners("missing when clause"); }
      )
    ;

activeClause
    : ( ON LEVEL n=decNumber
      | ON LEVEL { notifyErrorListeners("missing level ID"); }
      | ON { notifyErrorListeners("missing level"); }
      | { notifyErrorListeners("missing on level"); } 
      )
    | IMMEDIATELY
    ;

whenAction
    : START CAPTURE 
      ( DELAY n=decNumber '#'
      | DELAY decNumber { notifyErrorListeners("missing delay unit"); }
      )?
    | ( GOTO NEXT
      | GOTO { notifyErrorListeners("missing next"); }
      )
    ;

expr
    : ( '~' expr
      | '~' '~' expr { notifyErrorListeners("missing next"); }
      )
    | term=TERM_NAME
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
STAGE       : 'stage' ;
CAPTURE     : 'capture' ;
WHEN        : 'when' ;
START       : 'start' ;
STOP        : 'stop' ;
GOTO        : 'goto' ;
NEXT        : 'next' ;
ACTIVATE    : 'activate' ;
ON          : 'on' ;
LEVEL       : 'level' ;
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