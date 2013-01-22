/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.util.*;

/**
 * Denotes a mid-pair for a trigger sum.
 */
public class TdlMidPair extends AbstractSumPair {
    // CONSTANTS

    // @formatter:off                              NOP     ANY     AND    NAND      OR     NOR     XOR    NXOR       A       B
    private static final int[] MIDDLE_VALUE = { 0x0000, 0xFFFF, 0x8000, 0x7FFF, 0xFFFE, 0x0001, 0x0116, 0xFEE9, 0xEEEE, 0xFFF0 };
    // @formatter:on

    private static final int INPUT_COUNT = 4;

    // VARIABLES

    private volatile TdlFinalPair m_final;

    private final int m_index;
    private final TdlInputPair[] m_inputs;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlMidPair} instance.
     */
    public TdlMidPair(int index, TdlInputPair... inputs) {
        m_index = index;
        m_inputs = Arrays.copyOf(inputs, INPUT_COUNT);
        for (TdlInputPair input : m_inputs) {
            input.setMidPair(this);
        }
    }

    // METHODS

    /**
     * Encodes this mid-pair into a single 32-bit value.
     * 
     * @return an encoded representation of this mid-pair.
     */
    public int encodeValue() {
        return MIDDLE_VALUE[m_op];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillMissingOperators() {
        boolean anyInputDefined = false;
        // First call the same operation on the input-pairs...
        for (int i = 0; i < INPUT_COUNT; i++) {
            m_inputs[i].fillMissingOperators();
            if (m_inputs[i].getOperator() != OP_NOP) {
                anyInputDefined = true;
            }
        }

        // ...then decide what operator we're going to be...
        if ((m_op == OP_NOP) && anyInputDefined) {
            m_op = OP_OR;
        } else if ((m_op == OP_AND) || (m_op == OP_NAND)) {
            // make sure none of the 'undefined' inputs cause this AND operation
            // to be
            // stuck at zero...
            int searchedOp = (m_op == OP_AND) ? OP_NOP : OP_ANY;
            int forcedOp = (m_op == OP_AND) ? OP_ANY : OP_NOP;

            for (int i = 0; i < INPUT_COUNT; i++) {
                if (m_inputs[i].getOperator() == searchedOp) {
                    m_inputs[i].setOperator(forcedOp);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITdlSumPart getParent() {
        return m_final;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("mid%d:%s", (m_index + 1), getOperatorName());
    }

    /**
     * @param finalPair
     *            the final pair to set
     */
    void setFinal(TdlFinalPair finalPair) {
        m_final = finalPair;
    }
}
