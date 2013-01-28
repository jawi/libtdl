/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import java.io.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides the definition of a trigger stage.
 */
class TriggerStage implements TdlWritable {
    // CONSTANTS

    private static final int STATENUM_MASK = 0xF;
    private static final int OCCURRENCE_MASK = 0x000FFFFF;

    // VARIABLES

    private final int m_index;

    private final TriggerStageAction m_action;
    private final TriggerSum m_capture;
    private final TriggerSum m_else;
    private final TriggerSum m_if;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerStage} instance.
     * 
     * @param index
     *            the index of this trigger stage, > 0;
     * @param ddrMode
     *            <code>true</code> if DDR-mode is enabled, <code>false</code>
     *            otherwise.
     */
    public TriggerStage(int index, boolean ddrMode) {
        m_index = (index - 1) & STATENUM_MASK;

        m_action = new TriggerStageAction();
        m_capture = new TriggerSum();
        m_else = new TriggerSum();
        m_if = new TriggerSum();
    }

    // METHODS

    /**
     * @return the action of this trigger stage
     */
    public TriggerStageAction getAction() {
        return m_action;
    }

    /**
     * @return the capture expression, never <code>null</code>.
     */
    public TriggerSum getCapture() {
        return m_capture;
    }

    /**
     * @return the else expression, never <code>null</code>.
     */
    public TriggerSum getElse() {
        return m_else;
    }

    /**
     * @return the if expression, never <code>null</code>.
     */
    public TriggerSum getIf() {
        return m_if;
    }

    /**
     * @return the index of this stage, > 0.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * @param stageIndex
     *            the index of the else stage, > 0.
     */
    public void setElseStage(int stageIndex) {
        m_action.setElseState((stageIndex - 1) & STATENUM_MASK);
    }

    /**
     * Sets the number of times the if-expression should be matched in order to
     * have a full hit.
     * 
     * @param count
     *            the occurrence count to set, > 0.
     */
    public void setOccurrenceCount(int count) {
        m_action.setOccurrenceCount(count & OCCURRENCE_MASK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("stage ").append(m_index + 1).append(":\n\t");
        sb.append("capture ").append(m_capture).append("\n\t");
        sb.append("when ").append(m_if).append(" ").append(m_action.toString()).append("\n\t");
        sb.append("else on ").append(m_else).append(" goto ").append(m_action.getElseState());
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        outputStream.writeSelect(getIndex());
        outputStream.writeData(m_action.encodeValue());
    }
}
