/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.io.*;
import java.util.*;

import nl.lxtreme.libtdl.*;

/**
 * Provides a default implementation for {@link ITdlStages}.
 */
public class TdlTriggerStages implements ITdlStages {
    // CONSTANTS

    private static final int STAGE_COUNT = 16;

    // VARIABLES

    private final ITdlTriggerStage[] m_stages;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlTriggerStages} instance.
     */
    public TdlTriggerStages() {
        m_stages = new ITdlTriggerStage[STAGE_COUNT];
    }

    // METHODS

    /**
     * Adds a new trigger stage to this collection.
     * 
     * @param stage
     *            the trigger stage to add, cannot be <code>null</code>.
     */
    public void add(ITdlTriggerStage stage) {
        int idx = stage.getIndex();
        if (m_stages[idx] != null) {
            throw new RuntimeException("Trying to overwrite stage: " + idx);
        }
        m_stages[idx] = stage;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<ITdlTriggerStage> iterator() {
        defineLastStage();
        List<ITdlTriggerStage> stages = Arrays.asList(m_stages);
        return stages.iterator();
    }

    /**
     * @see nl.lxtreme.libtdl.grammar.triggerstage.ITdlStages#size()
     */
    @Override
    public int size() {
        return STAGE_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        defineLastStage();

        // Pass 1: write all stages
        for (ITdlTriggerStage stage : m_stages) {
            if (stage != null) {
                stage.write(outputStream);
            }
        }

        // Pass 2: write all trigger sums of each stage
        for (ITdlTriggerStage stage : m_stages) {
            if (stage != null) {
                writeTriggerSums(outputStream, stage);
            }
        }
    }

    /**
     * Marks the last found stage as being 'last'.
     */
    private void defineLastStage() {
        ITdlTriggerStage lastStage = null;
        for (ITdlTriggerStage stage : m_stages) {
            if (stage != null) {
                lastStage = stage;
            }
        }

        if (lastStage != null) {
            ((TdlTriggerStage) lastStage).getAction().setLastState(true);
        }
    }

    /**
     * @param outputStream
     * @param stage
     * @throws IOException
     */
    private void writeTriggerSums(TdlOutputStream outputStream, ITdlTriggerStage stage) throws IOException {
        int offset = 0x40 + (stage.getIndex() * 4);

        outputStream.writeSelect(offset + 0);
        stage.getIf().write(outputStream);

        outputStream.writeSelect(offset + 1);
        stage.getElse().write(outputStream);

        outputStream.writeSelect(offset + 2);
        stage.getCapture().write(outputStream);
    }
}
