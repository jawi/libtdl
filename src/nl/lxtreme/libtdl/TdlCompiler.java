/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import static nl.lxtreme.libtdl.grammar.TdlFactory.*;

import java.io.*;

import nl.lxtreme.libtdl.grammar.*;

import org.antlr.v4.runtime.tree.*;

/**
 * Compiles a valid snippet of TDL to a bitstream.
 */
public class TdlCompiler {
    // VARIABLES

    private final TdlConfig m_config;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlCompiler} instance.
     * 
     * @param config
     *            the configuration to use, cannot be <code>null</code>.
     */
    public TdlCompiler(TdlConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        m_config = config;
    }

    // METHODS

    /**
     * Compiles the given input as bitstream and writes this to the given output
     * stream.
     * <p>
     * This method assumes/expects the given input to be syntactically and
     * semantically correct! There is no additional validation/error checking
     * performed.
     * </p>
     * 
     * @param input
     *            the TDL input to compile, cannot be <code>null</code>;
     * @param outputStream
     *            the output stream to write the compiled bitstream to, cannot
     *            be <code>null</code>.
     * @throws IOException
     *             in case of I/O problems compiling the input text.
     */
    public void compile(String input, OutputStream outputStream) throws IOException {
        ParseTree tree = TdlFactory.createParseTree(m_config, input);

        Compiler<?> comp = createCompiler(m_config);
        comp.visit(tree);
        comp.write(outputStream);
    }
}
