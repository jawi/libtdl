/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

/**
 * Denotes a pair in this trigger sum.
 */
abstract class AbstractSumPair implements ITdlSumPart {
    // VARIABLES

    protected int m_op;

    // METHODS

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        m_op = OP_NOP;
    }

    /**
     * Called to fill in the missing operator on this sum pair and any of its
     * children (possibly recursive).
     */
    public abstract void fillMissingOperators();

    /**
     * Returns the type of operator for this pair.
     * 
     * @return the internal type of operator, >= OP_NOP && < OP_B.
     */
    public int getOperator() {
        return m_op;
    }

    /**
     * Inverts the operator of this pair.
     */
    @Override
    public void invert() {
        m_op = invertOperator(m_op);
    }

    /**
     * Sets the operator of this input.
     * 
     * @param operatorType
     *            the internal type of operator to set.
     */
    public void setOperator(int operatorType) {
        if ((m_op == OP_NOP) || (m_op == operatorType)) {
            m_op = operatorType;
        } else {
            throw new RuntimeException("Attempt to overwrite operator for: " + this);
        }
    }

    /**
     * Returns a string name for the operator of this pair.
     * 
     * @return a human-readable name for this operator, never <code>null</code>.
     */
    protected final String getOperatorName() {
        switch (m_op) {
            case OP_AND:
                return "and";

            case OP_NAND:
                return "nand";

            case OP_OR:
                return "or";

            case OP_NOR:
                return "nor";

            case OP_XOR:
                return "xor";

            case OP_NXOR:
                return "xnor";

            case OP_A:
                return "a";

            case OP_B:
                return "b";

            case OP_ANY:
                return "any";

            case OP_NOP:
                return "nop";

            default:
                return "???(" + m_op + ")";
        }
    }

    /**
     * Inverts a given internal operator type.
     * 
     * @param operatorType
     *            the internal operator type to invert.
     * @return the inverted operator type.
     */
    private int invertOperator(int operatorType) {
        switch (operatorType) {
            case OP_AND:
                return OP_NAND;

            case OP_OR:
                return OP_NOR;

            case OP_XOR:
                return OP_NXOR;

            default:
                return operatorType;
        }
    }
}
