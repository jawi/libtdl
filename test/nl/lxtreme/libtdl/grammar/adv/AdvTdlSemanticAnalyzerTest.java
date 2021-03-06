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
 * Test cases for {@link AdvTdlSemanticAnalyzer}.
 */
public class AdvTdlSemanticAnalyzerTest extends BaseTdlTestCase {
    // INNER TYPES

    static class StubConfig implements TdlConfig {
        // METHODS

        @Override
        public TdlDialect getDialect() {
            return TdlDialect.ADVANCED;
        }

        @Override
        public int getMaxStages() {
            return 10;
        }

        @Override
        public int getMaxChannels() {
            return 16;
        }

        @Override
        public boolean isDdrMode() {
            return false;
        }
    }

    // CONSTANTS

    // @formatter:off
    private static String[] VALID_INPUTS = {
        "termA := mask = 0b11, value = 0b10", 
        "termA := 0b11 ^ 0b10",
        "timer1 := 20us", 
        "range1 := 10..20",
        "edge1 := neither = 0x45, rising = 0x12, both = 0x34, falling = 0x23",
        "a := mask = 1, value = 2\ntermB := mask = 3, value = 4",
        "termA := 2^1 b := 2^0 stage 1: capture a & b when a goto next else on b goto 1",
    };
    private static String[] INVALID_INPUTS = {
        "termA := 2^1 stage 1: capture terma when a goto next else on b goto 1",
        "a := 1^2 termA := 2^3",
        "a := 2^1 stage 1: capture a when a start capture else on any goto 1\nstage 1: capture a when a start capture else on any goto 1",
        "a := 2^1 stage 1: capture a when a occurs 1048576 start capture else on any goto 1",
        "a := 2^1 stage 1: capture a when a occurs 1 start capture else on any goto 2",
        "range1 := 1..1",
    };
    private static String[][] INVALID_RESULTS = {
        { "termB is not declared" },
        { "term termA already declared" },
        { "stage 1 already defined" },
        { "invalid occurrence count" },
        { "undefined stage: 2" },
        { "lower bound should be less than upper bound" },
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
        return createParser(input).prog();
    }

    private AdvTdlSemanticAnalyzer createValidator() {
        return new AdvTdlSemanticAnalyzer(new StubConfig(), getProblemReporter());
    }

    private AdvTdlParser createParser(final String input) {
        AdvTdlLexer lexer = new AdvTdlLexer(new ANTLRInputStream(input));
        AdvTdlParser parser = new AdvTdlParser(new CommonTokenStream(lexer));

        initializeProblemReporter(lexer, parser);

        return parser;
    }
}
