/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.util.*;

import javax.swing.text.*;

import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

import org.antlr.v4.runtime.*;

/**
 * Provides a lexer-facade capable of handling both TDL dialects.
 */
public class TdlLexer {
    // INNER TYPES

    /**
     * Abstracts the tokens used by ANTLR and makes them easier to use in
     * combination with {@link Document}s.
     */
    public static interface TdlToken {
        // METHODS

        /**
         * @return the length of the text of this token, >= 0.
         */
        int getLength();

        /**
         * @return the offset of this token in the document, >= 0.
         */
        int getOffset();

        /**
         * @return the exact type of this token, never <code>null</code>.
         */
        TdlTokenType getType();
    }

    /**
     * Abstract away the various details on token types.
     */
    public static enum TdlTokenType {
        /** Comments */
        COMMENT,
        /** Whitespace */
        WS,
        /** All kinds of numeric values */
        NUMERIC,
        /** Assignment operator */
        ASSIGN,
        /** Various keywords */
        KEYWORD,
        /** Term (incl. range, timer and edge) */
        TERM,
        /** Logic expression (incl. NOP/ANY) */
        EXPRESSION,
        /** Unit (time, sample) */
        UNIT,
        /** Unspecified text */
        TEXT;
    }

    /**
     * Default implementation of {@link TdlToken}.
     */
    static final class TdlTokenImpl implements TdlToken {
        // VARIABLES

        private final int m_offset;
        private final int m_length;
        private final TdlTokenType m_type;

        // CONSTRUCTORS

        /**
         * Creates a new {@link TdlTokenImpl} instance.
         */
        public TdlTokenImpl(TdlTokenType type, int offset, int length) {
            m_type = type;
            m_offset = offset;
            m_length = length;
        }

        // METHODS

        @Override
        public int getLength() {
            return m_length;
        }

        @Override
        public int getOffset() {
            return m_offset;
        }

        @Override
        public TdlTokenType getType() {
            return m_type;
        }

        @Override
        public String toString() {
            return "token[" + m_type + "][" + m_offset + ", " + m_length + "]";
        }
    }

    // CONSTANTS

    private static final int EOF = Recognizer.EOF;

    // VARIABLES

    private final TdlDialect m_dialect;
    private final TdlProblemReporter m_problemReporter;
    private final Lexer m_lexer;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlLexer} instance.
     * 
     * @param dialect
     *            the TDL dialect to use, cannot be <code>null</code>.
     */
    public TdlLexer(TdlDialect dialect) {
        this(dialect, null);
    }

    /**
     * Creates a new {@link TdlLexer} instance.
     * 
     * @param dialect
     *            the TDL dialect to use, cannot be <code>null</code>;
     * @param input
     *            the input to parse, can be <code>null</code>.
     */
    public TdlLexer(TdlDialect dialect, CharStream input) {
        if (dialect == null) {
            throw new IllegalArgumentException("Invalid dialect: cannot be null!");
        }

        m_dialect = dialect;
        m_lexer = createLexer(input);

        m_problemReporter = new TdlProblemReporter();
        m_problemReporter.installOn(m_lexer);
    }

    // METHODS

    /**
     * @param listener
     *            the listener to add, cannot be <code>null</code>.
     */
    public void addProblemListener(ProblemListener listener) {
        m_problemReporter.addListener(listener);
    }

    /**
     * Returns the dialect used by this lexer.
     * 
     * @return the dialect, never <code>null</code>.
     */
    public TdlDialect getDialect() {
        return m_dialect;
    }

    /**
     * Returns a token stream based on this lexer.
     * 
     * @return a new {@link TokenStream} for this lexer.
     */
    public TokenStream getTokenStream() {
        return new CommonTokenStream(m_lexer);
    }

    /**
     * @param listener
     *            the listener to remove, cannot be <code>null</code>.
     */
    public void removeProblemListener(ProblemListener listener) {
        m_problemReporter.removeListener(listener);
    }

    /**
     * Resets this lexer to its initial state.
     */
    public void reset() {
        m_lexer.reset();
    }

    /**
     * Sets the input character stream for this lexer.
     * 
     * @param input
     *            the new input stream, never <code>null</code>.
     */
    public void setInput(CharStream input) {
        m_lexer.setInputStream(input);
    }

    /**
     * @param input
     *            the text to split into tokens.
     * @return a list of {@link TdlToken}s, never <code>null</code>.
     */
    public List<TdlToken> tokenize(String input) {
        return tokenize(new ANTLRInputStream(input));
    }

    /**
     * Factory method for creating a new {@link Lexer} instance based on the
     * used dialect.
     * 
     * @param input
     *            the character stream to create a lexer for, cannot be
     *            <code>null</code>.
     * @return a new {@link Lexer} instance, never <code>null</code>.
     */
    protected Lexer createLexer(CharStream input) {
        switch (m_dialect) {
            case BASIC:
                return new BasicTdlLexer(input);
            case ADVANCED:
                return new AdvTdlLexer(input);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }

    /**
     * @param input
     *            the text to split into tokens.
     * @return a list of {@link TdlToken}s, never <code>null</code>.
     */
    protected List<TdlToken> tokenize(CharStream input) {
        setInput(input);

        List<TdlToken> tokens = new ArrayList<TdlToken>();

        Token token;
        while ((token = m_lexer.nextToken()).getType() != EOF) {
            CommonToken ctoken = (CommonToken) token;

            int startIdx = ctoken.getStartIndex();
            int stopIdx = ctoken.getStopIndex();

            int offset = startIdx;
            int length = (stopIdx + 1) - startIdx;

            TdlTokenType type = convertTokenType(ctoken.getType());

            tokens.add(new TdlTokenImpl(type, offset, length));
        }

        return tokens;
    }

    /**
     * Converts a given token type (from ANTLR) to a more concrete
     * {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the ANTLR token type to convert.
     * @return the corresponding {@link TdlTokenType}, never <code>null</code>.
     */
    private TdlTokenType convertTokenType(int tokenType) {
        if (m_dialect == TdlDialect.ADVANCED) {
            switch (tokenType) {
                case AdvTdlLexer.COMMENT:
                    return TdlTokenType.COMMENT;

                case AdvTdlLexer.WS:
                case AdvTdlLexer.NL:
                    return TdlTokenType.WS;

                case AdvTdlLexer.ASSIGN:
                    return TdlTokenType.ASSIGN;

                case AdvTdlLexer.AND:
                case AdvTdlLexer.ANY:
                case AdvTdlLexer.LPAREN:
                case AdvTdlLexer.RPAREN:
                case AdvTdlLexer.NOP:
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

                case AdvTdlLexer.EDGE_NAME:
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
        } else if (m_dialect == TdlDialect.BASIC) {
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
        } else {
            throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }
}
