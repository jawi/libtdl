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
 * Provides a container for up to 16 {@link TriggerStage}s.
 */
class TriggerStages implements TdlWritable {
    // CONSTANTS

    private static final int CHAIN_OFFSET = 0x40;

    // VARIABLES

    private final TriggerStage[] m_stages;
    private final boolean m_ddrMode;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerStages} instance.
     * 
     * @param maxStages
     *            the maximum number of stages;
     * @param ddrMode
     *            <code>true</code> if DDR mode is enabled.
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
            throw new RuntimeException("Trying to overwrite stage: " + idx);
        }
        m_stages[idx] = stage;
    }

    /**
     * Creates a new {@link TriggerStage} instance.
     * 
     * @param index
     *            the index of the trigger stage, >= 0.
     * @return a new trigger stage instance, never <code>null</code>.
     */
    public TriggerStage createStage(int index) {
        return new TriggerStage(index, m_ddrMode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        defineLastStage();

        // Pass 1: write all stages
        for (TriggerStage stage : m_stages) {
            if (stage != null) {
                stage.write(outputStream);
            }
        }

        // Pass 2: write all trigger sums of each stage
        for (TriggerStage stage : m_stages) {
            if (stage != null) {
                writeTriggerSums(outputStream, stage);
            }
        }
    }

    /**
     * Ensures that at least one state is defined as 'last'; which is the last
     * defined stage. If at least one stage defines a 'set trigger' action, this
     * method will not do anything.
     */
    private void defineLastStage() {
        boolean triggerSet = false;

        TriggerStage lastStage = null;
        for (TriggerStage stage : m_stages) {
            if (stage != null) {
                triggerSet = stage.getAction().isSetTrigger();

                lastStage = stage;
            }
        }

        if ((lastStage != null) && !triggerSet) {
            lastStage.getAction().setEndState(true);
        }
    }

    /**
     * @param outputStream
     * @param stage
     * @throws IOException
     */
    private void writeTriggerSums(TdlOutputStream outputStream, TriggerStage stage) throws IOException {
        int offset = CHAIN_OFFSET + (stage.getIndex() * 4);

        outputStream.writeSelect(offset + 0);
        stage.getIf().write(outputStream);

        outputStream.writeSelect(offset + 1);
        stage.getElse().write(outputStream);

        outputStream.writeSelect(offset + 2);
        stage.getCapture().write(outputStream);
    }
}
