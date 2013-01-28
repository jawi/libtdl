/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.io.*;

import nl.lxtreme.libtdl.grammar.Compiler;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * Compiles a valid snippet of TDL to a bitstream.
 */
public class TdlCompiler {
    // VARIABLES

    private final TdlConfig m_config;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlCompiler} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     */
    public TdlCompiler(TdlConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_config = config;
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
    public void compile(String input, OutputStream outputStream) throws IOException {
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
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlLexer(new ANTLRInputStream(input));
            case ADVANCED:
                return new AdvTdlLexer(new ANTLRInputStream(input));
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
    protected Parser createParser(Lexer lexer) {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlParser(new CommonTokenStream(lexer));
            case ADVANCED:
                return new AdvTdlParser(new CommonTokenStream(lexer));
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Factory method for creating a new {@link Compiler} instance based on the
     * used dialect.
     * 
     * @return a new {@link Compiler} instance, never <code>null</code>.
     */
    protected Compiler<?> createCompiler() {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlCompiler(m_config);
            case ADVANCED:
                return new AdvTdlCompiler(m_config);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Returns the current context as parse tree.
     * 
     * @return a parse tree, never <code>null</code>.
     */
    protected ParseTree getParseTree(Parser parser) {
        TdlDialect dialect = m_config.getDialect();
        switch (dialect) {
            case BASIC:
                return ((BasicTdlParser) parser).prog();
            case ADVANCED:
                return ((AdvTdlParser) parser).prog();
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
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
    private void compile(ParseTree tree, OutputStream outputStream) throws IOException {
        final Compiler<?> comp = createCompiler();
        comp.visit(tree);
        comp.write(outputStream);
    }
}
