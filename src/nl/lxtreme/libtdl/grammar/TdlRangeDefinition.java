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
 * Provides a range definition.
 */
public class TdlRangeDefinition extends AbstractTdlDefinition {
    // CONSTANTS

    private static final int MAX_RANGES = 2;

    private static final int RANGE_XOR0 = 0xAAAA;
    private static final int RANGE_XOR1 = 0x5555;
    private static final int RANGE_NOP = 0xFFFF;

    // VARIABLES

    private final int m_index;
    private final int m_lower;
    private final int m_upper;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlRangeDefinition} instance.
     */
    public TdlRangeDefinition(String name, long lower, long upper) {
        this(toIndex(name), lower, upper);
    }

    /**
     * Creates a new {@link TdlRangeDefinition} instance.
     */
    public TdlRangeDefinition(int index, long lower, long upper) {
        super(Type.RANGE);

        m_index = index % MAX_RANGES;
        m_lower = (int) (lower & MASK_32BIT);
        m_upper = (int) (upper & MASK_32BIT);
    }

    // METHODS

    /**
     * Converts the given range name to a numeric index, for example, "range1"
     * =&gt; 0, "range2" =&gt; 1, and so on.
     * 
     * @param rangeName
     *            the range name to convert.
     * @return the numeric index for the given range name, >= 0.
     */
    static int toIndex(String rangeName) {
        if ((rangeName == null) || "".equals(rangeName)) {
            throw new IllegalArgumentException("Range name cannot be null or empty!");
        }
        return rangeName.charAt(rangeName.length() - 1) - '1';
    }

    /**
     * @return the index of this range, either 0 or 1.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * @return the lower bound of this term, as 32-bit unsigned value.
     */
    public int getLowerBound() {
        return m_lower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.format("range%d", (m_index + 1));
    }

    /**
     * @return the upper bound of this mask, as 32-bit unsigned value.
     */
    public int getUpperBound() {
        return m_upper;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s: [%d..%d]", getName(), getLowerBound(), getUpperBound());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        writeRange(outputStream, (getIndex() * MAX_RANGES), getLowerBound(), (int) MASK_32BIT);
        writeRange(outputStream, (getIndex() * MAX_RANGES) + 1, getUpperBound(), (int) MASK_32BIT);
    }

    /**
     * @param outputStream
     *            the output stream to write to;
     * @param rangeSelect
     *            0=range1-lower, 1=range1-upper, 2=range2-lower,
     *            3=range2-upper;
     * @param target
     *            lower/upper range value;
     * @param mask
     *            which bits participate in the range compare.
     * @throws IOException
     *             in case of I/O problems.
     */
    private void writeRange(TdlOutputStream outputStream, int rangeSelect, int target, int mask) throws IOException {
        outputStream.writeSelect(0x30 + (rangeSelect & 3));

        // Prepare target value...
        int value;
        if ((rangeSelect & 1) != 0) {
            value = ~target; // upper range target
        } else {
            value = ~(target - 1); // lower range value
        }

        // Push MSB of target into bit 31...
        value <<= (32 - Integer.bitCount(mask));

        // Generate & program LUT values. Total of 512 bits.
        int lutvalue = 0;
        for (int i = 0; i < 16; i++) {
            if (((mask >> 31) & 1) == 0) {
                lutvalue = RANGE_NOP;
            } else {
                lutvalue = (((value >> 31) & 1) != 0) ? RANGE_XOR1 : RANGE_XOR0;
                value <<= 1;
            }

            mask <<= 1;
            lutvalue <<= 16;

            if (((mask >> 31) & 1) == 0) {
                lutvalue |= RANGE_NOP;
            } else {
                lutvalue |= (((value >> 31) & 1) != 0) ? RANGE_XOR1 : RANGE_XOR0;
                value <<= 1;
            }

            mask <<= 1;

            outputStream.writeChain(lutvalue);
        }
    }
}
