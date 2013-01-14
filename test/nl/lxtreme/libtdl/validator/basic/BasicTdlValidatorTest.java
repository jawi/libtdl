/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.basic;

import junit.framework.*;
import nl.lxtreme.libtdl.grammar.basic.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test cases for {@link BasicTdlValidator}.
 */
public class BasicTdlValidatorTest extends TestCase {
    // CONSTANTS

    // @formatter:off
    private static String[] INPUTS = {
        "define termA as mask = 0b11, value = 0b10", 
        "define termA as 0b11 ^ 0b10",
        "define termA as mask = 1, value = 2\ndefine termB as mask = 3, value = 4",
        "stage 1: activate immediately, when a goto next", 
        "stage 1: activate on 1, when b start capture delay 5#", 
        "define termA as 1^2 define termA as 2^3",
    };
    // @formatter:on

    // METHODS

    /**
     * @throws Exception
     */
    public void testParserOk() throws Exception {
        for (String input : INPUTS) {
            BasicTdlValidator validator = new BasicTdlValidator();
            try {
                validator.visit(getParseTree(input));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param aInput
     * @param aExpected
     */
    private ProgContext getParseTree(final String input) {
        BasicTdlParser parser = createParser(input);
        return parser.prog();
    }

    /**
     * @param input
     * @return
     */
    private BasicTdlParser createParser(final String input) {
        BasicTdlLexer lexer = new BasicTdlLexer(new ANTLRInputStream(input));
        BasicTdlParser parser = new BasicTdlParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true);
        return parser;
    }
}
