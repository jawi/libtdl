/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.Compiler;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.basic.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * A factory for creating {@link Compiler}, {@link Parser} or {@link Lexer}
 * instances.
 */
public final class TdlFactory {
    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlFactory} instance.
     */
    private TdlFactory() {
        // Nop
    }

    // METHODS

    /**
     * Factory method for creating a new {@link SemanticAnalyzer} instance based
     * on the given configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>;
     * @param problemReporter
     *            the problem reporter to report problems to, cannot be
     *            <code>null</code>.
     * @return a new validator instance, never <code>null</code>.
     */
    public static SemanticAnalyzer<?> createAnalyzer(TdlConfig config, TdlProblemReporter problemReporter) {
        TdlDialect dialect = config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlSemanticAnalyzer(config, problemReporter);
            case ADVANCED:
                return new AdvTdlSemanticAnalyzer(config, problemReporter);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Creates a {@link Compiler} instance, based on the given configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     * @return a new compiler instance, never <code>null</code>.
     */
    public static Compiler<?> createCompiler(TdlConfig config) {
        TdlDialect dialect = config.getDialect();
        switch (dialect) {
            case BASIC:
                return new BasicTdlCompiler(config);
            case ADVANCED:
                return new AdvTdlCompiler(config);
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }

    /**
     * Factory method for creating a new {@link Lexer} instance based on the
     * given configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     * @return a new {@link Lexer} instance, never <code>null</code>.
     */
    public static Lexer createLexer(TdlConfig config) {
        TdlDialect dialect = config.getDialect();
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
     * given configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     * @return a new {@link Parser} instance, never <code>null</code>.
     */
    public static Parser createParser(TdlConfig config) {
        TdlDialect dialect = config.getDialect();
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
     * Directly creates a parse tree from the given input using the given
     * configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>;
     * @param input
     *            the input to parse, cannot be <code>null</code>.
     * @return a {@link ParseTree}, never <code>null</code>.
     */
    public static ParseTree createParseTree(TdlConfig config, String input) {
        Lexer lexer = createLexer(config);
        lexer.setInputStream(new ANTLRInputStream(input));

        Parser parser = createParser(config);
        parser.setInputStream(new CommonTokenStream(lexer));

        TdlDialect dialect = config.getDialect();
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
     * Returns the parse tree from the given parser using the given
     * configuration.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>;
     * @param parser
     *            the input-parser to get the parse tree from, cannot be
     *            <code>null</code>.
     * @return a {@link ParseTree}, never <code>null</code>.
     */
    public static ParseTree getParseTree(TdlConfig config, Parser parser) {
        TdlDialect dialect = config.getDialect();
        switch (dialect) {
            case BASIC:
                return ((BasicTdlParser) parser).prog();
            case ADVANCED:
                return ((AdvTdlParser) parser).prog();
            default:
                throw new RuntimeException("Invalid/unknown dialect: " + dialect);
        }
    }
}
