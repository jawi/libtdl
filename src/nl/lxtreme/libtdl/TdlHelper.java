/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import static nl.lxtreme.libtdl.grammar.TdlFactory.*;

import java.util.*;

import javax.swing.text.*;

import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Provides a parser/lexer-facade capable of handling both TDL dialects, useful
 * for front-ends, like editors.
 */
public final class TdlHelper {
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

        m_lexer = createLexer(m_config);
        m_parser = createParser(m_config);
        m_analyzer = createAnalyzer(m_config, m_problemReporter);

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

            TdlTokenType type = TdlTokenFactory.convertTokenType(m_config, ctoken.getType());

            tokens.add(new TdlTokenImpl(type, offset, length, ctoken.getText()));
        }

        return tokens;
    }

    /**
     * Returns the current context as parse tree.
     * 
     * @return a parse tree, never <code>null</code>.
     */
    private ParseTree getParseTree() {
        return TdlFactory.getParseTree(m_config, m_parser);
    }

    /**
     * Returns a token stream based on this lexer.
     * 
     * @return a new {@link TokenStream} for this lexer.
     */
    private TokenStream getTokenStream() {
        return new CommonTokenStream(m_lexer);
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
