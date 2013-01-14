/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import junit.framework.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test rig for {@link BasicTdlParser}.
 */
public class BasicTdlParserTest extends TestCase {
    // CONSTANTS

    // @formatter:off
    private static String[] INPUTS = {
        "define termA as mask = 0b11, value = 0b10", 
        "define termA as 0b11 ^ 0b10",
        "define termA as mask = 1, value = 2\ndefine termB as mask = 3, value = 4",
        "stage 1: activate immediately, when a goto next", 
        "stage 1: activate on 1, when b start capture delay 5#", 
    };
    private static String[] EXPECTED = {
        "(prog (decl define (termDecl termA as mask = (number 0b11) , value = (number 0b10))) <EOF>)",
        "(prog (decl define (termDecl termA as (number 0b11) ^ (number 0b10))) <EOF>)",
        "(prog (decl define (termDecl termA as mask = (number 1) , value = (number 2))) (decl define (termDecl termB as mask = (number 3) , value = (number 4))) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : activate (activeClause immediately) , when (expr a) (whenClause goto next)) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : activate (activeClause on (decNumber 1)) , when (expr b) (whenClause start capture delay (decNumber 5) #)) <EOF>)",
    };
    // @formatter:on

    // METHODS

    /**
     * @throws Exception
     */
    public void testParserOk() throws Exception {
        for (int i = 0; i < INPUTS.length; i++) {
            String input = INPUTS[i];
            String expected = EXPECTED[i];

            assertParseResult(input, expected);
        }
    }

    /**
     * @param aInput
     * @param aExpected
     */
    private void assertParseResult(final String input, final String expected) {
        BasicTdlParser parser = createParser(input);

        ProgContext result = parser.prog();
        assertEquals("Failed to parse " + input, expected, result.toStringTree(parser));
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
