/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.util.*;

import javax.swing.text.*;

import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Provides a parser/lexer-facade capable of handling both TDL dialects, useful
 * for front-ends, like editors.
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
         * @return the offset this token starts in the document, >= 0.
         */
        int getStartOffset();

        /**
         * @return the offset this token stops in the document, >= 0.
         */
        int getStopOffset();

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
            m_offset = token.getStartOffset();
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
        public int getStartOffset() {
            return m_offset;
        }

        @Override
        public int getStopOffset() {
            return m_offset + m_length;
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

    // VARIABLES

    private final TdlConfig m_config;
    private final TdlProblemReporter m_problemReporter;
    private final Lexer m_lexer;
    private final Parser m_parser;
    private final SemanticAnalyzer<?> m_analyzer;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlHelper} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     */
    public TdlHelper(TdlConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_config = config;

        m_problemReporter = new TdlProblemReporter();

        m_lexer = createLexer();
        m_parser = createParser();
        m_analyzer = createAnalyzer();

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
     * @param listener
     *            the listener to add, cannot be <code>null</code>.
     */
    public void addTermDeclarationListener(TermDefinitionListener listener) {
        m_analyzer.addTermDefinitionListener(listener);
    }

    /**
     * @param listener
     *            the listener to remove, cannot be <code>null</code>.
     */
    public void removeProblemListener(ProblemListener listener) {
        m_problemReporter.removeListener(listener);
    }

    /**
     * @param listener
     *            the listener to remove, cannot be <code>null</code>.
     */
    public void removeTermDeclarationListener(TermDefinitionListener listener) {
        m_analyzer.removeTermDefinitionListener(listener);
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

        m_analyzer.visit(getParseTree());
    }

    /**
     * Factory method for creating a new {@link Lexer} instance based on the
     * used dialect.
     * 
     * @return a new {@link Lexer} instance, never <code>null</code>.
     */
    protected Lexer createLexer() {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlLexer(null);
            case ADVANCED:
                return new AdvTdlLexer(null);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Factory method for creating a new {@link Parser} instance based on the
     * used dialect.
     * 
     * @return a new {@link Parser} instance, never <code>null</code>.
     */
    protected Parser createParser() {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlParser(null);
            case ADVANCED:
                return new AdvTdlParser(null);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Factory method for creating a new validator instance based on the
     * used dialect.
     * 
     * @return a new validator instance, never <code>null</code>.
     */
    protected SemanticAnalyzer<?> createAnalyzer() {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlSemanticAnalyzer(m_config, m_problemReporter);
            case ADVANCED:
                return new AdvTdlSemanticAnalyzer(m_config, m_problemReporter);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Returns the current context as parse tree.
     * 
     * @return a parse tree, never <code>null</code>.
     */
    protected ParseTree getParseTree() {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return ((BasicTdlParser) m_parser).prog();
            case ADVANCED:
                return ((AdvTdlParser) m_parser).prog();
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
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
        m_analyzer.reset();
    }

    /**
     * @param input
     *            the text to split into tokens.
     * @return a list of {@link TdlToken}s, never <code>null</code>.
     */
    protected List<TdlToken> tokenize(CharStream input) {
        setInput(input);
        reset();

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
     * Regards the given token type (from ANTLR) as being from the "advanced"
     * dialect and converts it to a generic {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the "advanced" token type to convert.
     * @return a {@link TdlTokenType} corresponding to the given token type,
     *         never <code>null</code>.
     */
    private TdlTokenType convertAdvTokenType(int tokenType) {
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
    private TdlTokenType convertBasicTokenType(int tokenType) {
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

    /**
     * Converts a given token type (from ANTLR) to a more concrete
     * {@link TdlTokenType}.
     * 
     * @param tokenType
     *            the ANTLR token type to convert.
     * @return the corresponding {@link TdlTokenType}, never <code>null</code>.
     */
    private TdlTokenType convertTokenType(int tokenType) {
        TdlDialect dialect = m_config.getDialect();
        if (dialect == TdlDialect.ADVANCED) {
            return convertAdvTokenType(tokenType);
        } else if (dialect == TdlDialect.BASIC) {
            return convertBasicTokenType(tokenType);
        } else {
            throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Sets the input character stream for this lexer.
     * 
     * @param input
     *            the new input stream, never <code>null</code>.
     */
    private void setInput(CharStream input) {
        m_lexer.setInputStream(input);
    }
}
