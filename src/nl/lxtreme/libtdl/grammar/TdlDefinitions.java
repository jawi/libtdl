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
 * Mutable implementation of {@link ITdlDefinitions}.
 */
public class TdlDefinitions implements ITdlDefinitions {
    // CONSTANTS

    // 10 terms, 2 ranges, 2 edges and 2 timers
    private static final int DEF_COUNT = 16;

    private static final int TERM_OFFSET = 0;
    private static final int EDGE_OFFSET = 10;
    private static final int RANGE_OFFSET = 12;
    private static final int TIMER_OFFSET = 14;

    // VARIABLES

    private final AbstractTdlDefinition[] m_entries;

    // CONSTRUCTORS

    /**
     * Creates a new, empty, {@link TdlDefinitions} instance.
     */
    public TdlDefinitions() {
        m_entries = new AbstractTdlDefinition[DEF_COUNT];
    }

    // METHODS

    /**
     * Adds a new edge definition to this collection.
     * 
     * @param name
     * @param values
     */
    public void addEdgeDefinition(String name, Map<String, Long> values) {
        TdlEdgeDefinition edgeDef = new TdlEdgeDefinition(name, values);

        int idx = EDGE_OFFSET + edgeDef.getIndex();
        if (m_entries[idx] != null) {
            throw new IllegalArgumentException("Edge definition " + name + " already exists!");
        }

        m_entries[idx] = edgeDef;
    }

    /**
     * Adds a new range definition to this collection.
     * 
     * @param name
     * @param lower
     * @param upper
     */
    public void addRangeDefinition(String name, long lower, long upper) {
        TdlRangeDefinition rangeDef = new TdlRangeDefinition(name, lower, upper);

        int idx = RANGE_OFFSET + rangeDef.getIndex();
        if (m_entries[idx] != null) {
            throw new IllegalArgumentException("Range definition " + name + " already exists!");
        }

        m_entries[idx] = rangeDef;
    }

    /**
     * Adds a new term definition to this collection.
     * 
     * @param name
     * @param value
     * @param mask
     */
    public void addTermDefinition(String name, long value, long mask) {
        TdlTermDefinition termDef = new TdlTermDefinition(name, value, mask);

        int idx = TERM_OFFSET + termDef.getIndex();
        if (m_entries[idx] != null) {
            throw new IllegalArgumentException("Term definition " + name + " already exists!");
        }

        m_entries[idx] = termDef;
    }

    /**
     * Adds a new timer definition to this collection.
     * 
     * @param name
     * @param time
     * @param unit
     */
    public void addTimerDefinition(String name, long time, String unit) {
        TdlTimerDefinition timerDef = new TdlTimerDefinition(name, time, unit);

        int idx = TIMER_OFFSET + timerDef.getIndex();
        if (m_entries[idx] != null) {
            throw new IllegalArgumentException("Timer definition " + name + " already exists!");
        }
        m_entries[idx] = timerDef;
    }

    /**
     * @param name
     *            the name of the definition to retrieve, cannot be
     *            <code>null</code>.
     * @return the term definition with the given name, or <code>null</code> if
     *         not found/defined.
     */
    public TdlDefinition get(String name) {
        for (TdlDefinition entry : m_entries) {
            if (entry == null) {
                continue;
            }
            if (name.equals(entry.getName())) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Returns an iterator on the current set of {@link AbstractTdlDefinition}s.
     * 
     * @return a new {@link Iterator} instance, never <code>null</code>.
     */
    @Override
    public Iterator<AbstractTdlDefinition> iterator() {
        fillDummyEntries();
        List<AbstractTdlDefinition> values = Arrays.asList(m_entries);
        return values.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return DEF_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        fillDummyEntries();
        for (AbstractTdlDefinition definition : m_entries) {
            definition.write(outputStream);
        }
    }

    /**
     * Creates a dummy entry for the given index.
     * 
     * @param index
     *            the index of the dummy entry.
     * @return a dummy entry, never <code>null</code>.
     */
    private AbstractTdlDefinition createDummyEntry(int index) {
        if (index < EDGE_OFFSET) {
            return new TdlTermDefinition(index, 0, 0);
        } else if (index < RANGE_OFFSET) {
            return new TdlEdgeDefinition(index - EDGE_OFFSET, Collections.<String, Long> emptyMap());
        } else if (index < TIMER_OFFSET) {
            return new TdlRangeDefinition(index - RANGE_OFFSET, 0, 0);
        } else {
            return new TdlTimerDefinition(index - TIMER_OFFSET, 0, "ns");
        }
    }

    /**
     * Fills all empty places in this container with dummy values.
     */
    private void fillDummyEntries() {
        for (int i = 0; i < DEF_COUNT; i++) {
            if (m_entries[i] == null) {
                m_entries[i] = createDummyEntry(i);
            }
        }
    }
}
