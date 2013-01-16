/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

/**
 * Provides a generic callback for reporting problems with a TDL-snippet.
 */
public interface ProblemReporter {
    // INNER TYPES

    /**
     * The problem category.
     */
    public static enum Category {
        SYNTAX, SEMANTIC, AMBIGUITY, OTHER;
    }

    /**
     * The problem type.
     */
    public static enum Type {
        WARNING, ERROR;
    }

    /**
     * Provides a marker containing all context for the problem.
     */
    public static final class Marker {
        // VARIABLES

        private final int m_line;
        private final int m_column;
        private final Type m_type;
        private final Category m_category;
        private final String m_description;
        private final Exception m_cause;

        // CONSTRUCTORS

        /**
         * Creates a new {@link Marker} instance.
         */
        public Marker(int line, int column, Type type, Category category, String description) {
            this(line, column, type, category, description, null);
        }

        /**
         * Creates a new {@link Marker} instance.
         */
        public Marker(int line, int column, Type type, Category category, String description, Exception cause) {
            m_line = line;
            m_column = column;
            m_type = type;
            m_category = category;
            m_description = description;
            m_cause = cause;
        }

        // METHODS

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj == null) || !(obj instanceof Marker)) {
                return false;
            }

            Marker other = (Marker) obj;
            if (m_column != other.m_column) {
                return false;
            }
            if (m_line != other.m_line) {
                return false;
            }
            if (m_type != other.m_type) {
                return false;
            }
            if (m_category != other.m_category) {
                return false;
            }
            if (m_description == null) {
                if (other.m_description != null) {
                    return false;
                }
            } else if (!m_description.equals(other.m_description)) {
                return false;
            }
            return true;
        }

        /**
         * @return the category of this marker, never <code>null</code>.
         */
        public Category getCategory() {
            return m_category;
        }

        /**
         * @return the (optional) cause, can be <code>null</code>.
         */
        public Exception getCause() {
            return m_cause;
        }

        /**
         * @return the column index (1-based) or <tt>-1</tt> if no column index
         *         is available.
         */
        public int getColumn() {
            return m_column;
        }

        /**
         * @return the description of this problem, never <code>null</code>.
         */
        public String getDescription() {
            return m_description;
        }

        /**
         * @return the line number (1-based) or <tt>-1</tt> if no line number is
         *         available.
         */
        public int getLine() {
            return m_line;
        }

        /**
         * @return the type of this problem, never <code>null</code>.
         */
        public Type getType() {
            return m_type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = (prime * result) + ((m_category == null) ? 0 : m_category.hashCode());
            result = (prime * result) + m_column;
            result = (prime * result) + ((m_description == null) ? 0 : m_description.hashCode());
            result = (prime * result) + m_line;
            result = (prime * result) + ((m_type == null) ? 0 : m_type.hashCode());
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("boxing")
        public String toString() {
            if ((m_line > 0) && (m_column > 0)) {
                return String.format("%s %s at %d:%d: %s", m_category, m_type, m_line, m_column, m_description);
            }
            return String.format("%s %s: %s", m_category, m_type, m_description);
        }
    }

    // METHODS

    /**
     * @param aListener
     *            the listener to add, cannot be <code>null</code>.
     */
    void addListener(ProblemListener aListener);

    /**
     * Reports a problem in the snippet at the given location.
     * 
     * @param line
     *            the line number (1-based) on which the problem is found. A
     *            value of <tt>-1</tt> indicates that no line number is
     *            available;
     * @param column
     *            the column number (1-based) on which the problem is found. A
     *            value of <tt>-1</tt> indicates that no column is available;
     * @param type
     *            the type of the problem, whether it is an warning or error;
     * @param category
     *            the category of the problem;
     * @param description
     *            a plain-text description of the problem;
     * @param cause
     *            the (optional) cause of the problem, can be <code>null</code>.
     */
    void report(int line, int column, Type type, Category category, String description, Exception cause);

    /**
     * @param aListener
     *            the listener to remove, cannot be <code>null</code>.
     */
    void removeListener(ProblemListener aListener);
}
