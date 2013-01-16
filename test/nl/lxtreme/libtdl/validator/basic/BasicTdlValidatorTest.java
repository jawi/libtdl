/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.basic;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.basic.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test cases for {@link BasicTdlValidator}.
 */
public class BasicTdlValidatorTest extends BaseTdlTestCase {
    // CONSTANTS

    // @formatter:off
    private static String[] VALID_INPUTS = {
        "termA := mask = 0b11, value = 0b10", 
        "terma := 0b11 ^ 0b10",
        "a := mask = 1, value = 2 termB := mask = 3, value = 4",
        "a := 2^1 stage 1: activate immediately, when a goto next", 
        "a := 2^2 b := 2^0 stage 1: activate on level 1, when b start capture delay 5#", 
    };
    private static String[] INVALID_INPUTS = {
        "stage 1: activate immediately, when b goto next",
        "a := 2^1 stage 5: activate immediately, when a goto next",
        "a := 2^2 stage 1: activate on level 5, when a start capture",
        "a := 2^3 stage 1: activate on level 1, when a start capture delay 65536#",
        "a := 2^4 termA := 2^3",
        "a := 2^5 stage 1: activate on level 1, when a start capture stage 1: activate immediately, when a start capture",
    };
    private static String[][] INVALID_RESULTS = {
        { "termB is not declared" },
        { "invalid stage ID" },
        { "invalid level ID" },
        { "invalid delay value" },
        { "term termA already declared" },
        { "stage 1 already defined" },
    };
    // @formatter:on

    // METHODS

    /**
     * Tests that valid TDL snippets yield no semantic markers.
     */
    public void testValidateCorrectInput() throws Exception {
        for (String tdl : VALID_INPUTS) {
            clearMarkers();

            createValidator().visit(getParseTree(tdl));

            assertMarkerCount("Input: " + tdl, 0);
        }
    }

    /**
     * Tests that invalid TDL snippets yield semantic markers.
     */
    public void testValidateIncorrectInput() throws Exception {
        for (int i = 0; i < INVALID_INPUTS.length; i++) {
            String tdl = INVALID_INPUTS[i];

            clearMarkers();

            createValidator().visit(getParseTree(tdl));

            assertMarkers("Input: " + tdl, INVALID_RESULTS[i]);
        }
    }

    private ProgContext getParseTree(final String input) {
        BasicTdlParser parser = createParser(input);
        return parser.prog();
    }

    private BasicTdlValidator createValidator() {
        return new BasicTdlValidator(getProblemReporter());
    }

    private BasicTdlParser createParser(final String input) {
        BasicTdlLexer lexer = new BasicTdlLexer(new ANTLRInputStream(input));
        BasicTdlParser parser = new BasicTdlParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true);

        initializeProblemReporter(lexer, parser);

        return parser;
    }
}
