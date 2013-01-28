/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.*;

import org.antlr.v4.runtime.tree.*;

/**
 * Provides a common interface for all semantic analyzers.
 */
public interface SemanticAnalyzer<T> extends ParseTreeVisitor<T> {
    // METHODS

    /**
     * Adds a new term definition listener.
     * 
     * @param listener
     *            the listener to add, cannot be <code>null</code>.
     */
    void addTermDefinitionListener(TermDefinitionListener listener);

    /**
     * Removes an existing term definition listener.
     * 
     * @param listener
     *            the listener to remove, cannot be <code>null</code>.
     */
    void removeTermDefinitionListener(TermDefinitionListener listener);

    /**
     * Resets this analyzer to its initial state.
     */
    void reset();
}
