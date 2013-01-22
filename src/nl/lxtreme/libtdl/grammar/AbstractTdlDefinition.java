/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.*;

/**
 * Provides a common base class for all {@link AbstractTdlDefinition}s, like
 * terms,
 * ranges and so on.
 */
abstract class AbstractTdlDefinition implements TdlWritable, TdlDefinition {
    // INNER TYPES

    /**
     * Denotes a type of definition.
     */
    public static enum Type {
        TERM, TIMER, RANGE, EDGE;
    }

    // CONSTANTS

    protected static final long MASK_32BIT = (1L << 32) - 1L;

    // VARIABLES

    private final Type m_type;

    // CONSTRUCTORS

    /**
     * Creates a new {@link AbstractTdlDefinition} instance.
     */
    protected AbstractTdlDefinition(Type type) {
        m_type = type;
    }

    // METHODS

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String getName();

    /**
     * {@inheritDoc}
     */
    @Override
    public final Type getType() {
        return m_type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEdge() {
        return m_type == Type.EDGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isRange() {
        return m_type == Type.RANGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isTerm() {
        return m_type == Type.TERM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isTimer() {
        return m_type == Type.TIMER;
    }
}
