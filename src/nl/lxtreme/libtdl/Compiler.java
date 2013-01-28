/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.io.*;

import org.antlr.v4.runtime.tree.*;

/**
 * Provides a compiler for a particular dialect.
 */
public interface Compiler<T> extends ParseTreeVisitor<T> {
    // METHODS

    /**
     * Writes the compiled bitstream to the given output stream.
     * 
     * @param outputStream
     *            the output stream to write to, cannot be <code>null</code>.
     * @throws IOException
     *             in case of I/O problems writing to the given I/O stream.
     */
    void write(OutputStream outputStream) throws IOException;
}
