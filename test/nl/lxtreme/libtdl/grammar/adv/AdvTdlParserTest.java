/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import junit.framework.*;
import nl.lxtreme.libtdl.grammar.adv.AdvTdlParser.ProgContext;

import org.antlr.v4.runtime.*;

/**
 * Test rig for {@link AdvTdlParser}.
 */
public class AdvTdlParserTest extends TestCase {
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
    };
    private static String[] EXPECTED = {
        "(prog (decl define (termDecl termA as mask = (number 0b11) , value = (number 0b10))) <EOF>)",
        "(prog (decl define (termDecl termA as (number 0b11) ^ (number 0b10))) <EOF>)",
        "(prog (decl define (timerDecl timer1 as (number 20) us)) <EOF>)",
        "(prog (decl define (rangeDecl range1 as (number 10) .. (number 20))) <EOF>)",
        "(prog (decl define (edgeDecl edge1 as (edgeTermDecl neither = (number 0x45)) , (edgeTermDecl rising = (number 0x12)) , (edgeTermDecl both = (number 0x34)) , (edgeTermDecl falling = (number 0x23)))) <EOF>)",
        "(prog (decl define (termDecl termA as mask = (number 1) , value = (number 2))) (decl define (termDecl termB as mask = (number 3) , value = (number 4))) <EOF>)",
        "(prog (stageDef stage (decNumber 1) : capture (termExpr (expr (expr a) & (expr b))) when (termExpr (expr a)) (whenClause goto next) else (termExpr (expr b)) (elseClause goto (decNumber 1))) <EOF>)",
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
        parser.setBuildParseTree(true);
        return parser;
    }
}
