/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

/**
 * Denotes a single input, e.g., a term, range detector, timer or edge detector.
 */
class Input implements SumPart {
    // VARIABLES

    private volatile InputPair m_inputPair;

    private final String m_name;

    private boolean m_invert;
    private boolean m_used;

    // CONSTRUCTORS

    /**
     * Creates a new {@link Input} instance.
     */
    public Input(String name) {
        m_name = name;
    }

    // METHODS

    /**
     * {@inheritDoc}
     */
    @Override
    public SumPart getParent() {
        return m_inputPair;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        m_invert = false;
        m_used = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invert() {
        m_invert = !m_invert;
    }

    /**
     * Returns whether or not this part is logically inverted.
     * 
     * @return <code>true</code> if this part of the sum is inverted,
     *         <code>false</code> otherwise.
     */
    public boolean isInverted() {
        return m_invert;
    }

    /**
     * @return the used
     */
    public boolean isUsed() {
        return m_used;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return (m_invert ? "!" : "").concat(m_name);
    }

    /**
     * @param inputPair
     *            the inputPair to set
     */
    void setInputPair(InputPair inputPair) {
        m_inputPair = inputPair;
    }
}
