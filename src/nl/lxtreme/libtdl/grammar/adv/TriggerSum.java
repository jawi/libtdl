/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import static nl.lxtreme.libtdl.grammar.adv.SumPart.*;

import java.io.*;

import nl.lxtreme.libtdl.grammar.*;

import org.antlr.v4.runtime.*;

/**
 * Denotes a trigger sum.
 */
class TriggerSum implements TdlWritable {
    // CONSTANTS

    private static final String[] INPUT_NAMES = { "a", "b", "c", "range1", "d", "edge1", "e", "timer1", "f", "g", "h",
            "range2", "i", "edge2", "j", "timer2" };

    // VARIABLES

    private final Input[] m_inputs;
    private final InputPair[] m_inputPairs;
    private final MidPair[] m_midPairs;
    private final FinalPair m_finalPair;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerSum} instance.
     */
    public TriggerSum() {
        int length = INPUT_NAMES.length;

        m_inputs = new Input[length];
        for (int i = 0; i < length; i++) {
            m_inputs[i] = new Input(INPUT_NAMES[i]);
        }

        length >>= 1;

        m_inputPairs = new InputPair[length];
        for (int i = 0, j = 0; i < length; i++, j += 2) {
            m_inputPairs[i] = new InputPair(m_inputs[j], m_inputs[j + 1]);
        }

        length >>= 2;

        m_midPairs = new MidPair[length];
        for (int i = 0, j = 0; i < length; i++, j += 4) {
            m_midPairs[i] = new MidPair(i, m_inputPairs[j], m_inputPairs[j + 1], m_inputPairs[j + 2],
                    m_inputPairs[j + 3]);
        }

        m_finalPair = new FinalPair(m_midPairs[0], m_midPairs[1]);
    }

    // METHODS

    /**
     * Defines an input for this sum.
     * 
     * @param inputTree
     *            the input, e.g., a, b, timer1, and so on.
     * @return the index of the given input, >= 0 && < 16.
     */
    public SumPart defineInput(Token inputTree) {
        int idx = findInputIndex(inputTree.getText());
        if (idx < 0) {
            throw new RuntimeException("Invalid input: " + inputTree.getText());
        }

        Input input = m_inputs[idx];
        // Initialize this operator for use in this sum...
        input.init();

        return input;
    }

    /**
     * @return
     */
    public SumPart nop() {
        m_finalPair.setOperator(OP_NOP);
        return m_finalPair;
    }

    /**
     * @return
     */
    public SumPart any() {
        m_finalPair.setOperator(OP_ANY);
        return m_finalPair;
    }

    /**
     * Defines an operation on a lefthand and righthand side.
     * 
     * @param operator
     *            the operator to apply;
     * @param lhs
     *            the LHS-sum pair;
     * @param rhs
     *            the RHS-sum pair.
     * @return the new index, >= 0.
     */
    public SumPart defineOperator(Token operator, SumPart lhs, SumPart rhs) {
        AbstractSumPair part = getPart(lhs, rhs);

        part.setOperator(mapOperator(operator.getText()));

        return part;
    }

    /**
     * Inverts the given index.
     * 
     * @param index
     *            the index of the sum pair to invert.
     * @return the given index.
     */
    public SumPart invert(SumPart part) {
        part.invert();
        return part;
    }

    /**
     * Fills all missing operators for the mid and final pairs.
     */
    public void fillMissingOperators() {
        // Start at end, and work our way backwards...
        m_finalPair.fillMissingOperators();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (InputPair m_inputPair : m_inputPairs) {
            int op = m_inputPair.getOperator();
            if (op == OP_NOP) {
                continue;
            }

            sb.append(m_inputPair).append(" ");
        }
        for (MidPair m_midPair : m_midPairs) {
            int op = m_midPair.getOperator();
            if (op == OP_NOP) {
                continue;
            }

            sb.append(m_midPair).append(" ");
        }
        sb.append(m_finalPair);

        return sb.toString().trim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        outputStream.writeData(m_finalPair.encodeValue());
        outputStream.writeData((m_midPairs[1].encodeValue() << 16) | (m_midPairs[0].encodeValue()));
        outputStream.writeData((m_inputPairs[7].encodeValue() << 16) | (m_inputPairs[6].encodeValue()));
        outputStream.writeData((m_inputPairs[5].encodeValue() << 16) | (m_inputPairs[4].encodeValue()));
        outputStream.writeData((m_inputPairs[3].encodeValue() << 16) | (m_inputPairs[2].encodeValue()));
        outputStream.writeData((m_inputPairs[1].encodeValue() << 16) | (m_inputPairs[0].encodeValue()));
    }

    /**
     * @param text
     * @return
     */
    private int findInputIndex(String text) {
        if (text.startsWith("term")) {
            text = text.substring(4);
        }
        for (int i = 0; i < INPUT_NAMES.length; i++) {
            if (INPUT_NAMES[i].equalsIgnoreCase(text)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param lhs
     * @param rhs
     * @return
     */
    private AbstractSumPair getPart(SumPart lhs, SumPart rhs) {
        SumPart ptr1 = rhs;

        do {
            SumPart ptr2 = lhs;

            do {
                if (ptr2 == ptr1) {
                    return (AbstractSumPair) ptr2;
                }
                ptr2 = ptr2.getParent();
            } while (ptr2 != null);

            ptr1 = ptr1.getParent();
        } while (ptr1 != null);

        // Not found...?!
        throw new RuntimeException("Failed to find proper pair for " + lhs + " & " + rhs + "?!");
    }

    /**
     * Maps the operator type defined in the Tree to an internal operator value.
     * 
     * @param operatorType
     *            the ANTLR-assigned operator type to map.
     * @return the internal operator type.
     */
    private int mapOperator(String operator) {
        if ("&".equals(operator)) {
            return OP_AND;
        } else if ("|".equals(operator)) {
            return OP_OR;
        } else if ("^".equals(operator)) {
            return OP_XOR;
        }
        throw new RuntimeException("Invalid operator: " + operator);
    }
}
