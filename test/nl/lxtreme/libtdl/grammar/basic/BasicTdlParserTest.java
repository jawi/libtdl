/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test rig for {@link BasicTdlParser}.
 */
public class BasicTdlParserTest extends BaseTdlTestCase {
    // CONSTANTS

    // @formatter:off
    private static String[] VALID_INPUTS = {
        "termA := mask = 0b11, value = 0b10", 
        "termA := 0b11 ^ 0b10",
        "termA := mask = 1, value = 2  termB := mask = 3, value = 4",
        "stage 1: activate immediately, when a goto next", 
        "stage 1: activate on level 1, when b start capture delay 5#", 
    };
    private static String[] VALID_RESULTS = {
        "(prog (decl (termDecl termA := mask = (number 0b11) , value = (number 0b10))) <EOF>)",
        "(prog (decl (termDecl termA := (number 0b11) ^ (number 0b10))) <EOF>)",
        "(prog (decl (termDecl termA := mask = (number 1) , value = (number 2))) (decl (termDecl termB := mask = (number 3) , value = (number 4))) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : activate (activeClause immediately) , when (termExpr (expr a)) (whenAction goto next)) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : activate (activeClause on level (decNumber 1)) , when (termExpr (expr b)) (whenAction start capture delay (decNumber 5) #)) <EOF>)",
    };
    private static final String[] INVALID_INPUTS = {
        "termA",
        "termA := ",
        "termA := mask = 0b11",
        "termA := 0b11",
        "a := 2^1 stage 1: activate on level 1, when a start capture delay 5",
        "a := 2^2 stage 1: activate on level 1, when a delay 5#",
        "a := 2^3 stage 1: activate on level 1, when start capture",
        "a := 2^4 stage 1: activate on level 1, when",
        "a := 2^5 stage 1: activate on level 1 when",
        "a := 2^6 stage 1: activate on level 1",
        "a := 2^7 stage 1: activate on level",
        "a := 2^8 stage 1: activate on ",
        "a := 2^9 stage 1: activate ",
        "a := 2^10 stage 1 ",
        "a := 2^10 stage",
    };
    private static final String[][] INVALID_RESULTS = {
        { "missing ':=' at '<EOF>'", "missing mask and value" }, 
        { "missing mask and value" }, 
        { "missing term value" }, 
        { "missing term value" },
        { "missing delay unit" },
        { "missing when action", "extraneous input 'delay'" },
        { "missing when expression", "extraneous input 'start'" },
        { "missing when expression" },
        { "missing comma", "missing when expression" },
        { "missing comma", "missing when clause" },
        { "missing level ID", "missing comma", "missing when clause" },
        { "missing level", "missing comma", "missing when clause" },
        { "missing on level", "missing comma", "missing when clause" },
        { "missing colon", "missing activate clause", "missing when clause" },
        { "missing stage ID", "missing activate clause", "missing when clause" },
    };
    // @formatter:on

    // METHODS

    /**
     * Tests that the parser is capable of handling invalid snippets of TDL, and
     * returns the correct error messages.
     */
    public void testParseInvalidInputs() throws Exception {
        for (int i = 0; i < INVALID_INPUTS.length; i++) {
            String input = INVALID_INPUTS[i];
            String[] expected = INVALID_RESULTS[i];

            assertInvalidParseResult(input, expected);
        }
    }

    /**
     * Tests that the parser is capable of handling valid snippets of TDL.
     */
    public void testParseValidInputs() throws Exception {
        for (int i = 0; i < VALID_INPUTS.length; i++) {
            String input = VALID_INPUTS[i];
            String expected = VALID_RESULTS[i];

            assertValidParseResult(input, expected);
        }
    }

    /**
     * @param aInput
     * @param aExpected
     */
    private void assertInvalidParseResult(final String input, final String[] expected) {
        BasicTdlParser parser = createParser(input);

        clearMarkers();

        ProgContext result = parser.prog();
        assertNotNull(result);

        assertMarkers("Input: " + input, expected);
    }

    /**
     * @param aInput
     * @param aExpected
     */
    private void assertValidParseResult(final String input, final String expected) {
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

        initializeProblemReporter(lexer, parser);

        return parser;
    }
}
