/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Denotes a definition of term, range, edge or timer.
 */
interface TermDefinition extends TdlWritable {
    // INNER TYPES

    /**
     * Denotes a type of definition.
     */
    public static enum Type {
        TERM, TIMER, RANGE, EDGE;
    }

    // METHODS

    /**
     * @return the name of this term definition, never <code>null</code>.
     */
    String getName();

    /**
     * @return the type of this definition.
     * @see Type
     */
    Type getType();

    /**
     * @return <code>true</code> if this is an edge definition,
     *         <code>false</code> otherwise.
     */
    boolean isEdge();

    /**
     * @return <code>true</code> if this is a range definition,
     *         <code>false</code> otherwise.
     */
    boolean isRange();

    /**
     * @return <code>true</code> if this is a term definition,
     *         <code>false</code> otherwise.
     */
    boolean isTerm();

    /**
     * @return <code>true</code> if this is a timer definition,
     *         <code>false</code> otherwise.
     */
    boolean isTimer();

}