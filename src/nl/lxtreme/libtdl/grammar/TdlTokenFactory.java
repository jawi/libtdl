/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.TdlHelper.TdlTokenType;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

/**
 * Provides a factory for creating {@link TdlTokenType}.
 */
public class TdlTokenFactory {
    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlTokenFactory} instance.
     */
    public TdlTokenFactory() {
        // Nop
    }

    // METHODS

    /**
     * Converts a given token type (from ANTLR) to a more concrete
     * {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the ANTLR token type to convert.
     * @return the corresponding {@link TdlTokenType}, never <code>null</code>.
     */
    public static TdlTokenType convertTokenType(TdlConfig config, int tokenType) {
        TdlDialect dialect = config.getDialect();
        if (dialect == TdlDialect.ADVANCED) {
            return convertAdvTokenType(tokenType);
        } else if (dialect == TdlDialect.BASIC) {
            return convertBasicTokenType(tokenType);
        } else {
            throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Regards the given token type (from ANTLR) as being from the "advanced"
     * dialect and converts it to a generic {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the "advanced" token type to convert.
     * @return a {@link TdlTokenType} corresponding to the given token type,
     *         never <code>null</code>.
     */
    private static TdlTokenType convertAdvTokenType(int tokenType) {
        switch (tokenType) {
            case AdvTdlLexer.COMMENT:
                return TdlTokenType.COMMENT;

            case AdvTdlLexer.WS:
            case AdvTdlLexer.NL:
                return TdlTokenType.WS;

            case AdvTdlLexer.ASSIGN:
                return TdlTokenType.ASSIGN;

            case AdvTdlLexer.AND:
            case AdvTdlLexer.LPAREN:
            case AdvTdlLexer.RPAREN:
            case AdvTdlLexer.NOT:
            case AdvTdlLexer.OR:
            case AdvTdlLexer.XOR:
                return TdlTokenType.EXPRESSION;

            case AdvTdlLexer.BOTH:
            case AdvTdlLexer.CAPTURE:
            case AdvTdlLexer.CLEAR:
            case AdvTdlLexer.ELSE:
            case AdvTdlLexer.FALLING:
            case AdvTdlLexer.GOTO:
            case AdvTdlLexer.MASK:
            case AdvTdlLexer.NEITHER:
            case AdvTdlLexer.NEXT:
            case AdvTdlLexer.OCCURS:
            case AdvTdlLexer.ON:
            case AdvTdlLexer.RISING:
            case AdvTdlLexer.STAGE:
            case AdvTdlLexer.START:
            case AdvTdlLexer.STOP:
            case AdvTdlLexer.VALUE:
            case AdvTdlLexer.WHEN:
                return TdlTokenType.KEYWORD;

            case AdvTdlLexer.ANY:
            case AdvTdlLexer.EDGE_NAME:
            case AdvTdlLexer.NOP:
            case AdvTdlLexer.RANGE_NAME:
            case AdvTdlLexer.TERM_NAME:
            case AdvTdlLexer.TIMER_NAME:
                return TdlTokenType.TERM;

            case AdvTdlLexer.TIME_UNIT:
                return TdlTokenType.UNIT;

            case AdvTdlLexer.BIN_LITERAL:
            case AdvTdlLexer.HEX_LITERAL:
            case AdvTdlLexer.OCT_LITERAL:
            case AdvTdlLexer.DEC_LITERAL:
                return TdlTokenType.NUMERIC;

            default:
                return TdlTokenType.TEXT;
        }
    }

    /**
     * Regards the given token type (from ANTLR) as being from the "advanced"
     * dialect and converts it to a generic {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the "basic" token type to convert.
     * @return a {@link TdlTokenType} corresponding to the given token type,
     *         never <code>null</code>.
     */
    private static TdlTokenType convertBasicTokenType(int tokenType) {
        switch (tokenType) {
            case BasicTdlLexer.COMMENT:
                return TdlTokenType.COMMENT;

            case BasicTdlLexer.WS:
            case BasicTdlLexer.NL:
                return TdlTokenType.WS;

            case BasicTdlLexer.ASSIGN:
                return TdlTokenType.ASSIGN;

            case BasicTdlLexer.XOR:
            case BasicTdlLexer.NOT:
                return TdlTokenType.EXPRESSION;

            case BasicTdlLexer.ACTIVATE:
            case BasicTdlLexer.CAPTURE:
            case BasicTdlLexer.DELAY:
            case BasicTdlLexer.GOTO:
            case BasicTdlLexer.IMMEDIATELY:
            case BasicTdlLexer.LEVEL:
            case BasicTdlLexer.MASK:
            case BasicTdlLexer.NEXT:
            case BasicTdlLexer.ON:
            case BasicTdlLexer.STAGE:
            case BasicTdlLexer.START:
            case BasicTdlLexer.STOP:
            case BasicTdlLexer.VALUE:
            case BasicTdlLexer.WHEN:
                return TdlTokenType.KEYWORD;

            case BasicTdlLexer.TERM_NAME:
                return TdlTokenType.TERM;

            case BasicTdlLexer.TIME_UNIT:
            case BasicTdlLexer.SAMPLES:
                return TdlTokenType.UNIT;

            case BasicTdlLexer.BIN_LITERAL:
            case BasicTdlLexer.HEX_LITERAL:
            case BasicTdlLexer.OCT_LITERAL:
            case BasicTdlLexer.DEC_LITERAL:
                return TdlTokenType.NUMERIC;

            default:
                return TdlTokenType.TEXT;
        }
    }
}
