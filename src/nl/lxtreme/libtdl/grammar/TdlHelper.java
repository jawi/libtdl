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

import nl.lxtreme.libtdl.grammar.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;
import nl.lxtreme.libtdl.validator.adv.*;
import nl.lxtreme.libtdl.validator.basic.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Provides a parser/lexer-facade capable of handling both TDL dialects, useful
 * for front-end editors.
 */
public class TdlHelper {
    // INNER TYPES

    /**
     * Abstracts the tokens used by ANTLR and makes them easier to use in
     * combination with {@link Document}s.
     */
    public static interface TdlToken {
        // METHODS

        /**
         * @return a shallow clone of this token, never <code>null</code>.
         */
        TdlToken clone();

        /**
         * @return the length of the text of this token, >= 0.
         */
        int getLength();

        /**
         * @return a list of problem markers, never <code>null</code>.
         */
        List<Marker> getMarkers();

        /**
         * @return the offset of this token in the document, >= 0.
         */
        int getOffset();

        /**
         * @return the text for this token in the document, never
         *         <code>null</code>.
         */
        String getText();

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
        private final String m_text;
        private final TdlTokenType m_type;
        private final List<Marker> m_markers;

        // CONSTRUCTORS

        /**
         * Creates a new {@link TdlTokenImpl} instance as copy of the given
         * token, except for its problem markers.
         */
        public TdlTokenImpl(TdlToken token) {
            m_offset = token.getOffset();
            m_length = token.getLength();
            m_text = token.getText();
            m_type = token.getType();
            m_markers = new ArrayList<Marker>();
        }

        /**
         * Creates a new {@link TdlTokenImpl} instance.
         */
        public TdlTokenImpl(TdlTokenType type, int offset, int length, String text) {
            m_offset = offset;
            m_length = length;
            m_text = text;
            m_type = type;
            m_markers = new ArrayList<Marker>();
        }

        // METHODS

        @Override
        public TdlToken clone() {
            return new TdlTokenImpl(this);
        }

        @Override
        public int getLength() {
            return m_length;
        }

        @Override
        public List<Marker> getMarkers() {
            return m_markers;
        }

        @Override
        public int getOffset() {
            return m_offset;
        }

        @Override
        public String getText() {
            return m_text;
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
    private static final ProblemReporter NULL_REPORTER = new ProblemReporter() {
        @Override
        public void addListener(ProblemListener aListener) {
            // Nop
        }

        @Override
        public void removeListener(ProblemListener aListener) {
            // Nop
        }

        @Override
        public void report(Marker marker) {
            // Nop
        }
    };

    // VARIABLES

    private final TdlDialect m_dialect;
    private final TdlProblemReporter m_problemReporter;
    private final Lexer m_lexer;
    private final Parser m_parser;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlHelper} instance.
     * 
     * @param dialect
     *            the TDL dialect to use, cannot be <code>null</code>.
     */
    public TdlHelper(TdlDialect dialect) {
        if (dialect == null) {
            throw new IllegalArgumentException("Invalid dialect: cannot be null!");
        }

        m_dialect = dialect;
        m_lexer = createLexer();
        m_parser = createParser();

        m_problemReporter = new TdlProblemReporter();
        m_problemReporter.installOn(m_lexer, m_parser);
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
     * Returns the definition for the term with the given name,
     * 
     * @param name
     *            the name of the term to get the definition for, cannot be
     *            <code>null</code>.
     * @return the definition for the given term, or <code>null</code> if no
     *         definition was found.
     */
    public String getTermDefinition(String name) {
        reset();

        AbstractParseTreeVisitor<?> validator = createValidator(NULL_REPORTER);
        if (validator instanceof AdvTdlValidator) {
            return ((AdvTdlValidator) validator).getTermDefinition(name);
        } else if (validator instanceof BasicTdlValidator) {
            return ((BasicTdlValidator) validator).getTermDefinition(name);
        }

        return null;
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
     * @param listener
     *            the listener to remove, cannot be <code>null</code>.
     */
    public void removeProblemListener(ProblemListener listener) {
        m_problemReporter.removeListener(listener);
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
     * Validates the current token stream and reports any errors to the
     * contained problem reporter.
     */
    public void validate() {
        reset();

        createValidator(m_problemReporter);
    }

    /**
     * Factory method for creating a new {@link Lexer} instance based on the
     * used dialect.
     * 
     * @return a new {@link Lexer} instance, never <code>null</code>.
     */
    protected Lexer createLexer() {
        switch (m_dialect) {
            case BASIC:
                return new BasicTdlLexer(null);
            case ADVANCED:
                return new AdvTdlLexer(null);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }

    /**
     * Factory method for creating a new {@link Parser} instance based on the
     * used dialect.
     * 
     * @return a new {@link Parser} instance, never <code>null</code>.
     */
    protected Parser createParser() {
        switch (m_dialect) {
            case BASIC:
                return new BasicTdlParser(null);
            case ADVANCED:
                return new AdvTdlParser(null);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }

    /**
     * Creates the validator for the current dialect and returns it after having
     * visited it.
     * 
     * @return the visited validator.
     */
    protected AbstractParseTreeVisitor<?> createValidator(ProblemReporter problemReporter) {
        ParserRuleContext context;
        AbstractParseTreeVisitor<? extends AbstractParseTreeVisitor<?>> visitor;

        switch (m_dialect) {
            case BASIC:
                context = ((BasicTdlParser) m_parser).prog();
                visitor = new BasicTdlValidator(problemReporter);
                break;
            case ADVANCED:
                context = ((AdvTdlParser) m_parser).prog();
                visitor = new AdvTdlValidator(problemReporter);
                break;
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
        visitor.visit(context);
        return visitor;
    }

    /**
     * Returns a token stream based on this lexer.
     * 
     * @return a new {@link TokenStream} for this lexer.
     */
    protected TokenStream getTokenStream() {
        return new CommonTokenStream(m_lexer);
    }

    /**
     * Resets this lexer to its initial state.
     */
    protected void reset() {
        m_lexer.reset();
        m_parser.setInputStream(getTokenStream());
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

            tokens.add(new TdlTokenImpl(type, offset, length, ctoken.getText()));
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
