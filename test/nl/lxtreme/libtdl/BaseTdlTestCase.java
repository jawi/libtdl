/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.util.*;

import junit.framework.*;
import nl.lxtreme.libtdl.ProblemReporter.Marker;

import org.antlr.v4.runtime.*;

/**
 * Provides a base class for testing TDL parsers/lexers.
 */
public abstract class BaseTdlTestCase extends TestCase implements ProblemListener {
    // VARIABLES

    private final TdlProblemReporter m_problemReporter = new TdlProblemReporter();
    private final List<Marker> m_markers = new ArrayList<Marker>();

    // METHODS

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(Marker marker) {
        m_markers.add(marker);
        System.out.printf("%s%n", marker);
    }

    /**
     * Clears all problem markers.
     */
    protected final void clearMarkers() {
        m_markers.clear();
    }

    /**
     * @return the current problem reporter, never <code>null</code>.
     */
    protected final TdlProblemReporter getProblemReporter() {
        return m_problemReporter;
    }

    protected final void initializeProblemReporter(Recognizer<?, ?>... recognizers) {
        m_problemReporter.clearListeners();
        m_problemReporter.addListener(this);
        m_problemReporter.installOn(recognizers);
    }

    protected final void assertMarkers(String... messages) {
        assertMarkers("Unexpected markers!", messages);
    }

    protected final void assertMarkers(String message, String... messages) {
        int expectedCount = messages.length;
        assertMarkerCount(message + " => " + Arrays.toString(messages) + "; " + m_markers, expectedCount);

        for (int idx = 0; idx < expectedCount; idx++) {
            String marker = m_markers.get(idx).toString();
            assertTrue(message + "\n(" + marker + ")", marker.contains(messages[idx]));
        }
    }

    protected final void assertMarkerCount(int expected) {
        assertMarkerCount("Unexpected number of markers,", expected);
    }

    protected final void assertMarkerCount(String message, int expected) {
        assertEquals(message, expected, m_markers.size());
    }

}
