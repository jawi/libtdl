/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.io.*;

import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Compiles a valid snippet of TDL to a bitstream.
 */
public class TdlCompiler {
    // VARIABLES

    private final TdlDialect m_dialect;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlCompiler} instance.
     */
    public TdlCompiler(TdlDialect dialect) {
        if (dialect == null) {
            throw new IllegalArgumentException("Dialect cannot be null!");
        }
        m_dialect = dialect;
    }

    // METHODS

    /**
     * Compiles the given input as bitstream and writes this to the given output
     * stream.
     * 
     * @param input
     *            the TDL input to compile, cannot be <code>null</code>;
     * @param outputStream
     *            the output stream to write the compiled bitstream to, cannot
     *            be <code>null</code>.
     * @throws IOException
     *             in case of I/O problems compiling the input text.
     */
    public void compile(String input, TdlOutputStream outputStream) throws IOException {
        Lexer lexer = createLexer(input);
        Parser parser = createParser(lexer);

        ParseTree tree = getParseTree(parser);

        compile(tree, outputStream);
    }

    /**
     * Factory method for creating a new {@link Lexer} instance based on the
     * used dialect.
     * 
     * @return a new {@link Lexer} instance, never <code>null</code>.
     */
    protected Lexer createLexer(String input) {
        switch (m_dialect) {
            case BASIC:
                return new BasicTdlLexer(new ANTLRInputStream(input));
            case ADVANCED:
                return new AdvTdlLexer(new ANTLRInputStream(input));
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
    protected Parser createParser(Lexer lexer) {
        switch (m_dialect) {
            case BASIC:
                return new BasicTdlParser(new CommonTokenStream(lexer));
            case ADVANCED:
                return new AdvTdlParser(new CommonTokenStream(lexer));
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }

    /**
     * Returns the current context as parse tree.
     * 
     * @return a parse tree, never <code>null</code>.
     */
    protected ParseTree getParseTree(Parser parser) {
        switch (m_dialect) {
            case BASIC:
                return ((BasicTdlParser) parser).prog();
            case ADVANCED:
                return ((AdvTdlParser) parser).prog();
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
        }
    }

    /**
     * Compiles the given parse tree and writes the result to the given output
     * stream.
     * 
     * @param tree
     *            the parse tree to compile, cannot be <code>null</code>;
     * @param outputStream
     *            the output stream to write the compiled bitstream to, cannot
     *            be <code>null</code>.
     * @throws IOException
     *             in case of I/O problems writing to the given output stream.
     */
    private void compile(ParseTree tree, TdlOutputStream outputStream) throws IOException {
        try {
            switch (m_dialect) {
                case BASIC:
                    new BasicTdlCompiler(outputStream).visit(tree);
                    break;
                case ADVANCED:
                    new AdvTdlCompiler(outputStream).visit(tree);
                    break;
                default:
                    throw new RuntimeException("Invalid/unknown dialect: " + m_dialect);
            }
        } catch (RuntimeException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof IOException) {
                throw (IOException) cause;
            } else {
                throw exception;
            }
        }
    }
}
