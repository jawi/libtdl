/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import junit.framework.*;

import org.antlr.v4.runtime.*;

/**
 * Provides some test cases for {@link BasicTdlCompiler}.
 */
public class BasicTdlCompilerTest extends TestCase {
    // METHODS

    /**
     * Tests that compiling a single trigger stage works.
     */
    public void testSimpleStageOk() throws Exception {
        TriggerStage stage = compile("termA := 0xF^0x01 stage 1: activate immediately, when A start capture delay 20#",
                0);

        assertEquals(0, stage.getIndex());
        assertEquals(0, stage.getActivationLevel());
        assertEquals(0xF, stage.getMask());
        assertEquals(0x1, stage.getValue());
        assertEquals(20, stage.getDelay());
        assertTrue(stage.isStartCapture());
    }

    /**
     * Tests that compiling a stage with an inverted input works.
     */
    public void testInvertedInput() throws Exception {
        TriggerStage stage = compile(
                "termA := 0xF^0x01 stage 1: activate immediately, when ~A start capture delay 20#", 0);

        assertEquals(0, stage.getIndex());
        assertEquals(0, stage.getActivationLevel());
        assertEquals(0xF, stage.getMask());
        assertEquals(~0x1, stage.getValue());
        assertEquals(20, stage.getDelay());
        assertTrue(stage.isStartCapture());
    }

    /**
     * Tests that compiling a stage with an inverted input works.
     */
    public void testAltStage() throws Exception {
        TriggerStage stage = compile("termA := 0xF^0x01 stage 2: activate on level 2, when A goto next", 1);

        assertEquals(1, stage.getIndex());
        assertEquals(2, stage.getActivationLevel());
        assertEquals(0xF, stage.getMask());
        assertEquals(0x1, stage.getValue());
        assertEquals(0, stage.getDelay());
        assertFalse(stage.isStartCapture());
    }

    /**
     * Compiles a given input.
     * 
     * @param input
     *            the input to compile, can be <code>null</code>.
     * @return the compiler instance after compilation of the given input, never
     *         <code>null</code>.
     */
    private TriggerStage compile(String input, int stageIndex) throws Exception {
        BasicTdlLexer lexer = new BasicTdlLexer(new ANTLRInputStream(input));
        BasicTdlParser parser = new BasicTdlParser(new CommonTokenStream(lexer));

        BasicTdlCompiler compiler = new BasicTdlCompiler(4, false);
        compiler.visit(parser.prog());

        return compiler.getStages().get(stageIndex);
    }

}
