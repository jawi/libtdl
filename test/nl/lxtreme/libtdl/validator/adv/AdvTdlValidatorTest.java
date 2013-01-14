/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator.adv;

import junit.framework.*;
import nl.lxtreme.libtdl.grammar.adv.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test cases for {@link AdvTdlValidator}.
 */
public class AdvTdlValidatorTest extends TestCase {
    // CONSTANTS

    // @formatter:off
    private static String[] INPUTS = {
        "define termA as mask = 0b11, value = 0b10", 
        "define termA as 0b11 ^ 0b10",
        "define timer1 as 20us", 
        "define range1 as 10..20",
        "define edge1 as neither = 0x45, rising = 0x12, both = 0x34, falling = 0x23",
        "define termA as mask = 1, value = 2\ndefine termB as mask = 3, value = 4",
        "stage 1: capture a & b when a goto next else b goto 1",
        "define termA as 1^2 define termA as 2^3",
    };
    // @formatter:on

    // METHODS

    /**
     * @throws Exception
     */
    public void testParserOk() throws Exception {
        for (String input : INPUTS) {
            AdvTdlValidator validator = new AdvTdlValidator();
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
        AdvTdlParser parser = createParser(input);
        return parser.prog();
    }

    /**
     * @param input
     * @return
     */
    private AdvTdlParser createParser(final String input) {
        AdvTdlLexer lexer = new AdvTdlLexer(new ANTLRInputStream(input));
        AdvTdlParser parser = new AdvTdlParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true);
        return parser;
    }
}
