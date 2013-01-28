/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.io.*;

/**
 * Denotes an output stream for writing trigger definitions, expressions and
 * stages to.
 */
public interface TdlOutputStream extends Closeable {
    // METHODS

    /**
     * Writes the opcode for which succeeding written data is meant.
     * 
     * @param opcode
     *            the 8-bit opcode, >= 0 && < 255.
     * @throws IOException
     *             in case of I/O problems writing the opcode.
     */
    void writeSelect(int opcode) throws IOException;

    /**
     * Writes data for an earlier selected opcode.
     * 
     * @param data
     *            the 32-bit data to write.
     * @throws IOException
     *             in case of I/O problems writing the data.
     * @see {@link #writeSelect(int)}
     */
    void writeData(int data) throws IOException;
}
