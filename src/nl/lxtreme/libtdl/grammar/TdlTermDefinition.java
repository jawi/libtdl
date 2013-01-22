/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.io.*;

import nl.lxtreme.libtdl.*;

/**
 * Provides a term definition.
 */
public class TdlTermDefinition extends AbstractTdlDefinition {
    // CONSTANTS

    private static final int MAX_TERMS = 10;

    // VARIABLES

    private final int m_index;
    private final int m_value;
    private final int m_mask;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlTermDefinition} instance.
     */
    public TdlTermDefinition(String termName, long value, long mask) {
        this(toIndex(termName), value, mask);
    }

    /**
     * Creates a new {@link TdlTermDefinition} instance.
     */
    public TdlTermDefinition(int index, long value, long mask) {
        super(Type.TERM);

        m_index = index % MAX_TERMS;
        m_value = (int) (value & MASK_32BIT);
        m_mask = (int) (mask & MASK_32BIT);
    }

    // METHODS

    /**
     * Converts the given term name to a numeric index, for example, "termA"
     * =&gt;
     * 0, "termB" =&gt; 1, and so on.
     * 
     * @param termName
     *            the term name to convert.
     * @return the numeric index for the given term name, >= 0.
     */
    static int toIndex(String termName) {
        if ((termName == null) || "".equals(termName)) {
            throw new IllegalArgumentException("Term name cannot be null or empty!");
        }
        return termName.toLowerCase().charAt(termName.length() - 1) - 'a';
    }

    /**
     * @return the index of this term, >= 0 && < 10.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * @return the mask of this term, as 32-bit unsigned value.
     */
    public int getMask() {
        return m_mask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.format("term%c", ('A' + m_index));
    }

    /**
     * @return the value of this mask, as 32-bit unsigned value.
     */
    public int getValue() {
        return m_value;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s: (%x ^ %x)", getName(), getMask(), getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        int bitmask = 1;
        int lutvalue0 = 0;
        int lutvalue1 = 0;
        int lutvalue2 = 0;
        int lutvalue3 = 0;

        for (int i = 0; i < 16; i++) {
            if (((i ^ (m_value & 0xF)) & (m_mask & 0xF)) == 0) {
                lutvalue0 |= bitmask;
            }
            if (((i ^ ((m_value >> 4) & 0xF)) & ((m_mask >> 4) & 0xF)) == 0) {
                lutvalue0 |= (bitmask << 16);
            }
            if (((i ^ ((m_value >> 8) & 0xF)) & ((m_mask >> 8) & 0xF)) == 0) {
                lutvalue1 |= bitmask;
            }
            if (((i ^ ((m_value >> 12) & 0xF)) & ((m_mask >> 12) & 0xF)) == 0) {
                lutvalue1 |= (bitmask << 16);
            }
            if (((i ^ ((m_value >> 16) & 0xF)) & ((m_mask >> 16) & 0xF)) == 0) {
                lutvalue2 |= bitmask;
            }
            if (((i ^ ((m_value >> 20) & 0xF)) & ((m_mask >> 20) & 0xF)) == 0) {
                lutvalue2 |= (bitmask << 16);
            }
            if (((i ^ ((m_value >> 24) & 0xF)) & ((m_mask >> 24) & 0xF)) == 0) {
                lutvalue3 |= bitmask;
            }
            if (((i ^ ((m_value >> 28) & 0xF)) & ((m_mask >> 28) & 0xF)) == 0) {
                lutvalue3 |= (bitmask << 16);
            }
            bitmask <<= 1;
        }
        // Write data into LUT serial chain. MSB must goes in first. Total of
        // 128
        // bits.
        outputStream.writeSelect(0x20 + getIndex());
        outputStream.writeChain(lutvalue3);
        outputStream.writeChain(lutvalue2);
        outputStream.writeChain(lutvalue1);
        outputStream.writeChain(lutvalue0);
    }
}
