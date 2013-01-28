/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import nl.lxtreme.libtdl.grammar.*;

import org.antlr.v4.runtime.*;

/**
 * Denotes an expression with terms.
 */
class TermExpression {
    // VARIABLES

    private boolean m_invert;
    private String m_name;

    // METHODS

    /**
     * Defines the actual term used.
     * 
     * @param token
     *            the token representing the term, cannot be <code>null</code>.
     */
    public void defineTerm(Token token) {
        m_name = Util.normalizeName(token.getText());
    }

    /**
     * Returns the term name.
     * 
     * @return the name, never <code>null</code>.
     */
    public String getName() {
        return m_name;
    }

    /**
     * Inverts the term in this expression.
     */
    public void invert() {
        m_invert = !m_invert;
    }

    /**
     * Returns whether or not the term in this expression is inverted.
     * 
     * @return <code>true</code> if the value is to be inverted.
     */
    public boolean isInverted() {
        return m_invert;
    }
}
