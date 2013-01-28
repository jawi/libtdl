/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

/**
 * Provides a common base class for all {@link AbstractDefinition}s, like
 * terms, ranges and so on.
 */
abstract class AbstractDefinition implements TermDefinition {
    // CONSTANTS

    protected static final long MASK_32BIT = (1L << 32) - 1L;

    // VARIABLES

    private final Type m_type;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AbstractDefinition} instance.
     */
    protected AbstractDefinition(Type type) {
        m_type = type;
    }

    // METHODS

    /**
     * @return the name of this term definition, never <code>null</code>.
     */
    @Override
    public abstract String getName();

    /**
     * @return the type of this definition.
     * @see Type
     */
    @Override
    public final Type getType() {
        return m_type;
    }

    /**
     * @return <code>true</code> if this is an edge definition,
     *         <code>false</code> otherwise.
     */
    @Override
    public final boolean isEdge() {
        return m_type == Type.EDGE;
    }

    /**
     * @return <code>true</code> if this is a range definition,
     *         <code>false</code> otherwise.
     */
    @Override
    public final boolean isRange() {
        return m_type == Type.RANGE;
    }

    /**
     * @return <code>true</code> if this is a term definition,
     *         <code>false</code> otherwise.
     */
    @Override
    public final boolean isTerm() {
        return m_type == Type.TERM;
    }

    /**
     * @return <code>true</code> if this is a timer definition,
     *         <code>false</code> otherwise.
     */
    @Override
    public final boolean isTimer() {
        return m_type == Type.TIMER;
    }
}
