/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import java.util.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Container for all term definitions.
 */
class Definitions {
    // VARIABLES

    private final Map<String, Term> m_terms;

    // CONSTRUCTORS

    /**
     * Creates a new {@link Definitions} instance.
     */
    public Definitions() {
        m_terms = new HashMap<String, Term>();
    }

    // METHODS

    /**
     * Adds a new term definition to this container.
     * 
     * @param name
     *            the name of the term, like "a" or "termB";
     * @param value
     *            the 32-bit value of the term;
     * @param mask
     *            the 32-bit mask of the term.
     */
    public void addTermDefinition(String name, Long value, Long mask) {
        Term term = new Term(name, value, mask);
        if (m_terms.put(term.getName(), term) != null) {
            throw new IllegalStateException("Duplicate term: " + name);
        }
    }

    /**
     * Returns the term with the given name.
     * 
     * @param name
     *            the name of the term to retrieve, cannot be <code>null</code>.
     * @return the term with the given name, or <code>null</code> if no such
     *         term exists.
     */
    public Term getTerm(String name) {
        name = Util.normalizeName(name);
        return m_terms.get(name);
    }
}
