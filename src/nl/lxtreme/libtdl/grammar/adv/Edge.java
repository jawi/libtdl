/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import java.io.*;
import java.util.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides a range definition.
 */
class Edge extends AbstractDefinition {
    // CONSTANTS

    private static final int MAX_EDGES = 2;

    private static final int EDGE_RISE0 = 0x0A0A;
    private static final int EDGE_RISE1 = 0x00CC;
    private static final int EDGE_FALL0 = 0x5050;
    private static final int EDGE_FALL1 = 0x3300;
    private static final int EDGE_BOTH0 = (EDGE_RISE0 | EDGE_FALL0);
    private static final int EDGE_BOTH1 = (EDGE_RISE1 | EDGE_FALL1);
    private static final int EDGE_NEITHER0 = (~EDGE_BOTH0 & 0xFFFF);
    private static final int EDGE_NEITHER1 = (~EDGE_BOTH1 & 0xFFFF);

    private static final String RISING = "rising";
    private static final String FALLING = "falling";
    private static final String BOTH = "both";
    private static final String NONE = "none";

    // VARIABLES

    private final int m_index;

    private final int m_rising;
    private final int m_falling;
    private final int m_none;

    // CONSTRUCTORS

    /**
     * Creates a new {@link Edge} instance.
     * 
     * @param values
     */
    public Edge(String name, Map<String, Long> values) {
        this(toIndex(name), values);
    }

    /**
     * Creates a new {@link Edge} instance.
     */
    public Edge(int index, Map<String, Long> values) {
        super(Type.EDGE);

        m_index = index % MAX_EDGES;

        int both = getIntValue(values.get(BOTH));

        m_rising = getIntValue(values.get(RISING)) | both;
        m_falling = getIntValue(values.get(FALLING)) | both;
        m_none = getIntValue(values.get(NONE));
    }

    // METHODS

    /**
     * Converts the given edge name to a numeric index, for example, "edge1"
     * =&gt;
     * 0, "edge2" =&gt; 1, and so on.
     * 
     * @param edgeName
     *            the edge name to convert.
     * @return the numeric index for the given edge name, >= 0.
     */
    static int toIndex(String edgeName) {
        if ((edgeName == null) || "".equals(edgeName)) {
            throw new IllegalArgumentException("Edge name cannot be null or empty!");
        }
        return edgeName.charAt(edgeName.length() - 1) - '1';
    }

    /**
     * Returns the given {@link Long} as primitive integer, defaulting to 0 if
     * it
     * was <code>null</code>.
     * 
     * @param input
     *            the {@link Long} wrapper to convert to primitive, can be
     *            <code>null</code>.
     * @return an integer primitive for the given input, or 0L if the given
     *         input
     *         was <code>null</code>.
     */
    static int getIntValue(Long input) {
        if (input == null) {
            return 0;
        }
        return (int) (input.longValue() & MASK_32BIT);
    }

    /**
     * @return the index of this edge definition, either 0 or 1.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * @return the bitmask for detecting rising edges.
     */
    public int getRising() {
        return m_rising;
    }

    /**
     * @return the bitmask for detecting falling edges.
     */
    public int getFalling() {
        return m_falling;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.format("edge%d", (m_index + 1));
    }

    /**
     * @return the bitmask for detecting no edge.
     */
    public int getNone() {
        return m_none;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s: %d, %d, %d", getName(), m_rising, m_falling, m_none);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        outputStream.writeSelect(0x34 + getIndex());

        int data = 0;
        int bitmask = 0x80000000;

        for (int i = 0; i < 16; i++) {
            // Evaluate bit 1...
            if ((m_none & bitmask) != 0) {
                data |= EDGE_NEITHER1;
            } else {
                if ((m_rising & bitmask) != 0) {
                    data |= EDGE_RISE1;
                }
                if ((m_falling & bitmask) != 0) {
                    data |= EDGE_FALL1;
                }
            }
            bitmask >>>= 1;

            // Evaluate bit 0...
            if ((m_none & bitmask) != 0) {
                data |= EDGE_NEITHER0;
            } else {
                if ((m_rising & bitmask) != 0) {
                    data |= EDGE_RISE0;
                }
                if ((m_falling & bitmask) != 0) {
                    data |= EDGE_FALL0;
                }
            }
            bitmask >>>= 1;

            if ((i & 1) == 0) {
                data <<= 16;
            } else {
                outputStream.writeData(data); // 256 bits
                data = 0;
            }
        }
    }
}
