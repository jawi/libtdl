/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import java.io.*;
import java.util.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Denotes a set of {@link TermDefinition}s.
 */
class Definitions implements TdlWritable {
    // CONSTANTS

    // 10 terms, 2 ranges, 2 edges and 2 timers
    private static final int DEF_COUNT = 16;

    private static final int TERM_OFFSET = 0;
    private static final int EDGE_OFFSET = 10;
    private static final int RANGE_OFFSET = 12;
    private static final int TIMER_OFFSET = 14;

    // VARIABLES

    private final TermDefinition[] m_entries;

    // CONSTRUCTORS

    /**
     * Creates a new, empty, {@link Definitions} instance.
     */
    public Definitions() {
        m_entries = new TermDefinition[DEF_COUNT];
    }

    // METHODS

    /**
     * Adds a new edge definition to this collection.
     * 
     * @param name
     * @param values
     */
    public void addEdgeDefinition(String name, Map<String, Long> values) {
        Edge edgeDef = new Edge(name, values);

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
        Range rangeDef = new Range(name, lower, upper);

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
        Term termDef = new Term(name, value, mask);

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
        Timer timerDef = new Timer(name, time, unit);

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
    public TermDefinition get(String name) {
        name = Util.normalizeName(name);
        for (TermDefinition entry : m_entries) {
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
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        fillDummyEntries();
        for (TermDefinition definition : m_entries) {
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
    private TermDefinition createDummyEntry(int index) {
        if (index < EDGE_OFFSET) {
            return new Term(index, 0, 0);
        } else if (index < RANGE_OFFSET) {
            return new Edge(index - EDGE_OFFSET, Collections.<String, Long> emptyMap());
        } else if (index < TIMER_OFFSET) {
            return new Range(index - RANGE_OFFSET, 0, 0);
        } else {
            return new Timer(index - TIMER_OFFSET, 0, "ns");
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
