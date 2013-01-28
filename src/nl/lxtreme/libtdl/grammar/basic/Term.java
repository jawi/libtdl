/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

/**
 * Provides a term definition.
 */
class Term {
    // CONSTANTS

    private static final long MASK_32BIT = (1L << 32) - 1L;

    private static final int MAX_TERMS = 4;

    // VARIABLES

    private final int m_index;
    private final int m_value;
    private final int m_mask;

    // CONSTRUCTORS

    /**
     * Creates a new {@link Term} instance.
     */
    public Term(String termName, long value, long mask) {
        this(toIndex(termName), value, mask);
    }

    /**
     * Creates a new {@link Term} instance.
     */
    public Term(int index, long value, long mask) {
        m_index = index % MAX_TERMS;
        m_value = (int) (value & MASK_32BIT);
        m_mask = (int) (mask & MASK_32BIT);
    }

    // METHODS

    /**
     * Converts the given term name to a numeric index, for example, "termA"
     * =&gt; 0, "termB" =&gt; 1, and so on.
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
    public String getName() {
        return String.format("term%c", ('A' + m_index));
    }

    /**
     * @return the value of this mask, as 32-bit unsigned value.
     */
    public int getValue() {
        return m_value;
    }
}
