/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

/**
 * Provides a callback for listening to declarations of terms (= edges, plain
 * terms, ranges & timers).
 */
public interface TermDefinitionListener {
    // METHODS

    /**
     * Called for each declared term in a TDL snippet.
     * 
     * @param definition
     *            the declared term, never <code>null</code>.
     */
    void termDeclared(String name, String definition);
}
