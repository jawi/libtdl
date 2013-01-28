/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import junit.framework.*;
import nl.lxtreme.libtdl.*;

import org.antlr.v4.runtime.*;

/**
 * Provides some test cases for {@link AdvTdlCompiler}.
 */
public class AdvTdlCompilerTest extends TestCase {
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

    // METHODS

    /**
     * Tests that compiling a ANY works.
     */
    public void testAny() throws Exception {
        TriggerSum sum = compileTermExpr("any");
        assertEquals("final:any", sum.toString());
    }

    /**
     * Tests that compiling a single input works.
     */
    public void testInput() throws Exception {
        TriggerSum sum = compileTermExpr("edge1");
        assertEquals("edge1 mid1:or final:or", sum.toString());
    }

    /**
     * Tests that compiling a inversion of a mid-pair works.
     */
    public void testInvertEndPair() throws Exception {
        TriggerSum sum = compileTermExpr("~(termA ^ timer2)");
        assertEquals("a timer2 mid1:or mid2:or final:xnor", sum.toString());
    }

    /**
     * Tests that compiling a inversion of a mid-pair works.
     */
    public void testInvertInput() throws Exception {
        TriggerSum sum = compileTermExpr("~termA | timer1");
        assertEquals("!a timer1 mid1:or final:or", sum.toString());
    }

    /**
     * Tests that compiling a inversion of a mid-pair works.
     */
    public void testInvertMidPair() throws Exception {
        TriggerSum sum = compileTermExpr("~(termA | timer1)");
        assertEquals("a timer1 mid1:nor final:or", sum.toString());
    }

    /**
     * Tests that compiling a NOP works.
     */
    public void testNop() throws Exception {
        TriggerSum sum = compileTermExpr("nop");
        assertEquals("final:nop", sum.toString());
    }

    /**
     * Compiles a given "term expr" input.
     * 
     * @param input
     *            the input to compile, can be <code>null</code>.
     * @return the compiler instance after compilation of the given input, never
     *         <code>null</code>.
     */
    private TriggerSum compileTermExpr(String input) throws Exception {
        AdvTdlLexer lexer = new AdvTdlLexer(new ANTLRInputStream(input));
        AdvTdlParser parser = new AdvTdlParser(new CommonTokenStream(lexer));

        TriggerSum result = new TriggerSum();

        AdvTdlCompiler compiler = new AdvTdlCompiler(new StubConfig());
        compiler.setSum(result);
        compiler.visit(parser.termExpr());

        return result;
    }
}
