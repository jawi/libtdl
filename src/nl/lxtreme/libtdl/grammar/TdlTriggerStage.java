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
 * Provides the definition of a trigger stage.
 */
public class TdlTriggerStage implements ITdlTriggerStage {
    // CONSTANTS

    private static final int STATENUM_MASK = 0xF;
    private static final int OCCURRENCE_MASK = 0x000FFFFF;

    // VARIABLES

    private final int m_index;
    private final boolean m_basic;

    private final TdlTriggerStageAction m_action;
    private final TdlTriggerSum m_capture;
    private final TdlTriggerSum m_else;
    private final TdlTriggerSum m_if;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlTriggerStage} instance.
     * 
     * @param index
     *            the index of this trigger stage, > 0;
     * @param basic
     *            <code>true</code> to denote that this trigger stage is a
     *            "basic"
     *            trigger stage, <code>false</code> to denote it as an advanced
     *            trigger stage.
     */
    public TdlTriggerStage(int index, boolean basic) {
        m_index = (index - 1) & STATENUM_MASK;
        m_basic = basic;

        m_action = new TdlTriggerStageAction();
        m_capture = new TdlTriggerSum();
        m_else = new TdlTriggerSum();
        m_if = new TdlTriggerSum();
    }

    // METHODS

    /**
     * @return the action of this trigger stage
     */
    public TdlTriggerStageAction getAction() {
        return m_action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TdlTriggerSum getCapture() {
        return m_capture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TdlTriggerSum getElse() {
        return m_else;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TdlTriggerSum getIf() {
        return m_if;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return m_index;
    }

    /**
     * @return the basic
     */
    public boolean isBasic() {
        return m_basic;
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
        outputStream.writeChain(m_action.encodeValue());
    }
}
