/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.io.*;

/**
 * Denotes an output stream for writing trigger definitions, expressions and
 * stages to.
 */
public interface TdlOutputStream {
    // METHODS

    /**
     * Selects which particular chain the succeeding data writes will go to.
     * 
     * @param chainId
     *            the 8-bit chain ID, >= 0 && < 255.
     * @throws IOException
     *             in case of I/O problems writing the chain ID.
     */
    void writeSelect(int chainId) throws IOException;

    /**
     * Writes data to an earlier selected chain.
     * 
     * @param data
     *            the 32-bit data to write.
     * @throws IOException
     *             in case of I/O problems writing to the chain.
     * @see {@link #writeSelect(int)}
     */
    void writeChain(int data) throws IOException;
}
