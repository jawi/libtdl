/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

/**
 * Denotes a final pair for a trigger sum.
 */
class FinalPair extends AbstractSumPair {
    // CONSTANTS

    // @formatter:off                                NOP     ANY     AND    NAND      OR     NOR     XOR    NXOR       A       B
    protected static final int[] FINAL_VALUE =  { 0x0000, 0xFFFF, 0x0008, 0x0007, 0x000E, 0x0001, 0x0006, 0x0009, 0x0002, 0x0004 };
    // @formatter:on

    // VARIABLES

    private final MidPair m_input1;
    private final MidPair m_input2;

    // CONSTRUCTORS

    /**
     * Creates a new {@link FinalPair} instance.
     */
    public FinalPair(MidPair... midPairs) {
        m_input1 = midPairs[0];
        m_input2 = midPairs[1];

        m_input1.setFinal(this);
        m_input2.setFinal(this);
    }

    // METHODS

    /**
     * Encodes this final pair into a single 32-bit value.
     * 
     * @return an encoded value.
     */
    public int encodeValue() {
        return FINAL_VALUE[m_op];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillMissingOperators() {
        // First call the same operation on the mid-pairs...
        m_input1.fillMissingOperators();
        m_input2.fillMissingOperators();

        // ...then decide what operator we're going to be...
        if (m_op == OP_NOP) {
            if ((m_input1.getOperator() != OP_NOP) || (m_input2.getOperator() != OP_NOP)) {
                m_op = OP_OR;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SumPart getParent() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("final:%s", getOperatorName());
    }
}
