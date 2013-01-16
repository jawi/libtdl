/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.grammar.ProblemReporter.Marker;

/**
 * Provides a listener for problems in a TDL-snippet.
 */
public interface ProblemListener {
    // METHODS

    /**
     * Adds a given problem marker to this listener.
     * 
     * @param marker
     *            the problem marker to add, cannot be <code>null</code>.
     */
    void add(Marker marker);
}
