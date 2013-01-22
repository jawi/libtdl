/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import org.antlr.v4.runtime.*;

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

        private final int m_offset;
        private final int m_length;
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
        Marker(int offset, int length, int line, int column, Type type, Category category, String description,
                Exception cause) {
            m_offset = offset;
            m_length = length;
            m_line = line;
            m_column = column;
            m_type = type;
            m_category = category;
            m_description = description;
            m_cause = cause;
        }

        /**
         * Creates a new {@link Marker} instance.
         */
        Marker(Type type, Category category, String description) {
            this(-1, -1, -1, -1, type, category, description, null);
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
            if (m_offset != other.m_offset) {
                return false;
            }
            if (m_length != other.m_length) {
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
         * @return the column on which the problem occurred, or <tt>-1</tt> if
         *         no column is available.
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
         * @return the length or <tt>-1</tt> if no length is available.
         */
        public int getLength() {
            return m_length;
        }

        /**
         * @return the line on which the problem occurred, or <tt>-1</tt> if no
         *         line is available.
         */
        public int getLine() {
            return m_line;
        }

        /**
         * @return the offset from the start or <tt>-1</tt> if no offset is
         *         available.
         */
        public int getOffset() {
            return m_offset;
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
            result = (prime * result) + m_offset;
            result = (prime * result) + m_length;
            result = (prime * result) + m_line;
            result = (prime * result) + m_column;
            result = (prime * result) + ((m_category == null) ? 0 : m_category.hashCode());
            result = (prime * result) + ((m_description == null) ? 0 : m_description.hashCode());
            result = (prime * result) + ((m_type == null) ? 0 : m_type.hashCode());
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("boxing")
        public String toString() {
            if ((m_line >= 0) && (m_column >= 0)) {
                return String.format("%s %s: (%d..%d) %s", m_category, m_type, m_line, m_column, m_description);
            }
            return String.format("%s %s: %s", m_category, m_type, m_description);
        }
    }

    public static final class MarkerBuilder {
        // VARIABLES

        private int m_offset = -1;
        private int m_length = -1;
        private int m_line = -1;
        private int m_column = -1;
        private Category m_category = Category.OTHER;
        private Type m_type = Type.WARNING;
        private String m_description = "";
        private Exception m_cause = null;

        // METHODS

        /**
         * @return
         */
        public Marker build() {
            return new Marker(m_offset, m_length, m_line, m_column, m_type, m_category, m_description, m_cause);
        }

        /**
         * @param offset
         * @param length
         * @param line
         * @param column
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setLocation(int offset, int length, int line, int column) {
            m_offset = offset;
            m_length = length;
            m_line = line;
            m_column = column;
            return this;
        }

        /**
         * @param context
         *            the context to use for the location information.
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setLocation(ParserRuleContext context) {
            m_offset = context.getStart().getStartIndex();
            m_length = context.getStop().getStopIndex() - m_offset;
            m_line = context.getStart().getLine();
            m_column = context.getStart().getCharPositionInLine();
            return this;
        }

        /**
         * @param cause
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setCause(Exception cause) {
            m_cause = cause;
            return this;
        }

        /**
         * @param description
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setDescription(String description) {
            m_description = description;
            return this;
        }

        /**
         * @param category
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setCategory(Category category) {
            m_category = category;
            return this;
        }

        /**
         * @param type
         * @return this builder, never <code>null</code>.
         */
        public MarkerBuilder setType(Type type) {
            m_type = type;
            return this;
        }
    }

    // METHODS

    /**
     * @param aListener
     *            the listener to add, cannot be <code>null</code>.
     */
    void addListener(ProblemListener aListener);

    /**
     * @param aListener
     *            the listener to remove, cannot be <code>null</code>.
     */
    void removeListener(ProblemListener aListener);

    /**
     * Reports a problem in the snippet.
     * 
     * @param marker
     *            the marker to report, cannot be <code>null</code>.
     */
    void report(Marker marker);
}
