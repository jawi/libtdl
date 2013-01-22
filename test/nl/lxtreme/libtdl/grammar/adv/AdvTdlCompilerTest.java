/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import java.io.*;

import junit.framework.*;
import nl.lxtreme.libtdl.*;

import org.antlr.v4.runtime.*;

/**
 * 
 */
public class AdvTdlCompilerTest extends TestCase {
    // INNER TYPES

    static class NullOutputStream implements TdlOutputStream {
        @Override
        public void writeSelect(int chainId) throws IOException {
            // Nop
        }

        @Override
        public void writeChain(int data) throws IOException {
            // Nop
        }
    }

    // METHODS

    /**
     * Tests that compiling a NOP works.
     */
    public void testNop() throws Exception {
        AdvTdlLexer lexer = new AdvTdlLexer(new ANTLRInputStream(
                "stage 1: capture ~(termA | timer1) when timer1 start timer1 else on termA goto 2"));
        AdvTdlParser parser = new AdvTdlParser(new CommonTokenStream(lexer));

        AdvTdlCompiler compiler = new AdvTdlCompiler(new NullOutputStream());
        compiler.visit(parser.stageDef());
    }
}
