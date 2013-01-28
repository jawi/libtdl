/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import java.io.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides a container for a number of {@link TriggerStage}s.
 */
class TriggerStages implements TdlWritable {
    // VARIABLES

    private final TriggerStage[] m_stages;
    private final boolean m_ddrMode;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerStages} instance.
     * 
     * @param maxStages
     *            the maximum number of stages, > 0;
     * @param ddrMode
     *            <code>true</code> if DDR mode is to be enabled,
     *            <code>false</code> otherwise.
     */
    public TriggerStages(int maxStages, boolean ddrMode) {
        m_stages = new TriggerStage[maxStages];
        m_ddrMode = ddrMode;
    }

    // METHODS

    /**
     * Adds a new trigger stage to this collection.
     * 
     * @param stage
     *            the trigger stage to add, cannot be <code>null</code>.
     */
    public void add(TriggerStage stage) {
        int idx = stage.getIndex();
        if (m_stages[idx] != null) {
            throw new IllegalStateException("Trying to overwrite stage: " + idx);
        }
        m_stages[idx] = stage;
    }

    /**
     * Creates a new trigger stage instance.
     * 
     * @param index
     *            the index of the stage to create, 1-based.
     * @return a new {@link TriggerStage} instance, never <code>null</code>.
     */
    public TriggerStage createTriggerStage(int index) {
        return new TriggerStage(index, m_ddrMode);
    }

    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        fillMissingStages();

        for (TriggerStage stage : m_stages) {
            stage.write(outputStream);
        }
    }

    /**
     * Returns the trigger stage with the given index.
     * 
     * @param index
     *            the index of the trigger stage to retrieve, >= 0.
     * @return a trigger stage, can be <code>null</code>.
     */
    final TriggerStage get(int index) {
        return m_stages[index];
    }

    /**
     * Fills the missing stages.
     */
    private void fillMissingStages() {
        for (int i = 0; i < m_stages.length; i++) {
            if (m_stages[i] == null) {
                m_stages[i] = new TriggerStage(i, m_ddrMode);
                m_stages[i].setStartCapture(true); // XXX is this correct?
            }
        }
    }
}
