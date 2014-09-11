/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test rig for {@link AdvTdlParser}.
 */
public class AdvTdlParserTest extends BaseTdlTestCase {
    // CONSTANTS

    // @formatter:off
    private static final String[] VALID_INPUTS = {
        "termA := mask = 0b11, value = 0b10",
        "termA := 0b11 ^ 0b10",
        "timer1 := 20us",
        "range1 := 10..20",
        "edge1 := neither = 0x45, rising = 0x12, both = 0x34, falling = 0x23",
        "termA := mask = 1, value = 2\ntermB := mask = 3, value = 4",
        "stage 1: capture a & b when a goto next else on b goto 1",
    };
    private static final String[] VALID_RESULTS = {
        "(prog (decl (termDecl termA := mask = (number 0b11) , value = (number 0b10))) <EOF>)",
        "(prog (decl (termDecl termA := (number 0b11) ^ (number 0b10))) <EOF>)",
        "(prog (decl (timerDecl timer1 := (number 20) us)) <EOF>)",
        "(prog (decl (rangeDecl range1 := (number 10) .. (number 20))) <EOF>)",
        "(prog (decl (edgeDecl edge1 := (edgeTermDecl neither = (number 0x45)) , (edgeTermDecl rising = (number 0x12)) , (edgeTermDecl both = (number 0x34)) , (edgeTermDecl falling = (number 0x23)))) <EOF>)",
        "(prog (decl (termDecl termA := mask = (number 1) , value = (number 2))) (decl (termDecl termB := mask = (number 3) , value = (number 4))) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : capture (termExpr (expr (expr a) & (expr b))) when (termExpr (expr a)) (whenAction goto next) else on (termExpr (expr b)) (elseAction goto (decNumber 1))) <EOF>)",
    };
    private static final String[] INVALID_INPUTS = {
        "termA",
        "termA := ",
        "termA := mask = 0b11",
        "termA := 0b11",
        "timer1 := 20",
        "timer1 := ",
        "range1 := 10..",
        "range1 := 10",
        "range1 := ",
        "edge1 := neither = ",
        "edge1 :=",
        "a := 2^1 stage 1: capture when a start capture else on any goto 1",
        "a := 2^1 stage 1: when a start capture else on any goto 1",
        "a := 2^1 stage 1: capture a when a else on any goto 1",
        "a := 2^1 stage 1: capture a when start capture else on any goto 1",
        "a := 2^1 stage 1: capture a when a start capture else on any",
        "a := 2^1 stage 1: capture a when a start capture else on goto 1",
        "a := 2^1 stage 1: capture a when a start capture else",
        "a := 2^1 stage 1: capture a when a start capture",
    };
    private static final String[][] INVALID_RESULTS = {
        { "missing ASSIGN at '<EOF>'", "missing mask and value" },
        { "missing mask and value" },
        { "missing term value" },
        { "missing term value" },
        { "missing time unit" },
        { "missing time value and unit" },
        { "missing upper bound" },
        { "missing upper bound" },
        { "invalid range definition, needs lower and upper bound" },
        { "missing edge value" },
        { "invalid edge definition, needs at least one edge term declaration" },
        { "missing capture expression" },
        { "missing capture clause" },
        { "missing when action" },
        { "missing when expression", "missing else clause", "extraneous input 'start'" },
        { "missing else action" },
        { "missing else expression", "extraneous input 'goto'" },
        { "missing on" },
        { "missing else clause" },
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
        AdvTdlParser parser = createParser(input);

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
        AdvTdlParser parser = createParser(input);

        ProgContext result = parser.prog();
        assertEquals("Failed to parse " + input, expected, result.toStringTree(parser));
    }

    /**
     * @param input
     * @return
     */
    private AdvTdlParser createParser(final String input) {
        AdvTdlLexer lexer = new AdvTdlLexer(new ANTLRInputStream(input));
        AdvTdlParser parser = new AdvTdlParser(new CommonTokenStream(lexer));

        initializeProblemReporter(lexer, parser);

        return parser;
    }
}
