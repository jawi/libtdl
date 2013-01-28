/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

/**
 * Denotes an input pair, where two inputs are "paired" by means of an
 * operation, such as AND, OR.
 */
class InputPair extends AbstractSumPair {
    // CONSTANTS

    // @formatter:off                              NOP     ANY     AND    NAND      OR     NOR     XOR    NXOR       A       B
    private static final int[] PAIR_VALUE =   { 0x0000, 0xFFFF, 0x8000, 0x7FFF, 0xF888, 0x0777, 0x7888, 0x8777, 0x8888, 0xF000 };
    // @formatter:on

    // VARIABLES

    private volatile MidPair m_midPair;

    private final Input m_inputA;
    private final Input m_inputB;

    // CONSTRUCTORS

    /**
     * Creates a new {@link InputPair} instance.
     */
    public InputPair(Input inputA, Input inputB) {
        m_inputA = inputA;
        m_inputB = inputB;

        m_inputA.setInputPair(this);
        m_inputB.setInputPair(this);
    }

    // METHODS

    /**
     * Encodes this input pair as single 32-bit value.
     * 
     * @return an integer representation of this input pair.
     */
    public int encodeValue() {
        return getPairValue(m_op, m_inputA.isInverted(), m_inputB.isInverted());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillMissingOperators() {
        if (m_op == OP_NOP) {
            if (m_inputA.isUsed() && !m_inputB.isUsed()) {
                m_op = OP_A;
            } else if (!m_inputA.isUsed() && m_inputB.isUsed()) {
                m_op = OP_B;
            } else if (m_inputA.isUsed() && m_inputB.isUsed()) {
                m_op = OP_OR;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SumPart getParent() {
        return m_midPair;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (m_op == OP_A) {
            return String.format("%s", m_inputA);
        } else if (m_op == OP_B) {
            return String.format("%s", m_inputB);
        } else if ((m_op == OP_NOP) || (m_op == OP_ANY)) {
            return String.format("%s_%s:%s", m_inputA, m_inputB, getOperatorName());
        }
        return String.format("%s %s %s", m_inputA, getOperatorName(), m_inputB);
    }

    /**
     * @param midPair
     *            the mid pair to which this input pair belongs.
     */
    void setMidPair(MidPair midPair) {
        m_midPair = midPair;
    }

    /**
     * Encodes the given operator type into a single value.
     * 
     * @param operatorType
     *            the operator to apply;
     * @param invertA
     *            <code>true</code> if input A is inverted;
     * @param invertB
     *            <code>true</code> if input B is inverted.
     * @return the encoded pair value.
     */
    private int getPairValue(int operatorType, boolean invertA, boolean invertB) {
        final int flipA[] = { 0, 0, 0, 0, 0, 0, 0, 0x08, 0x07, 0, 0, 0, 0, 0, 0, 0x0F };

        int value = PAIR_VALUE[operatorType];
        if (invertA) {
            value = (flipA[(value >> 12) & 0xF] << 12) | (flipA[(value >> 8) & 0xF] << 8)
                    | (flipA[(value >> 4) & 0xF] << 4) | (flipA[value & 0xF]);
        }
        if (invertB) {
            value = ((value & 0xF) << 12) | ((value >> 4) & 0xF00) | ((value >> 8) & 0xF0) | ((value >> 12) & 0xF);
        }
        return value;
    }
}
