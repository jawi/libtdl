/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.validator;

import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Category;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Type;

import org.antlr.v4.runtime.*;

/**
 * Provide some utility methods used during validation.
 */
public final class ValidationUtil {
    // CONSTRUCTORS

    /**
     * Creates a new {@link ValidationUtil} instance.
     */
    private ValidationUtil() {
        // Not used.
    }

    // METHODS

    /**
     * Parses a given text as number, recognizing numbers in the form of:
     * <ul>
     * <li><tt>0b101</tt> or <tt>0B101</tt> for binary numbers;</li>
     * <li><tt>0x10</tt> or <tt>0x20</tt> for hexadecimal numbers;</li>
     * <li><tt>0123</tt> for octal numbers.</li>
     * <li><tt>123</tt> for decimal numbers.</li>
     * </ul>
     * 
     * @param text
     *            the text to parse as number, may be <code>null</code> or
     *            empty.
     * @return the parsed number as long value, or -1L if the given text was
     *         <code>null</code> or empty.
     */
    public static Long decode(String text) {
        if ((text != null) && !"".equals(text.trim())) {
            try {
                int idx;
                int radix;

                if (text.startsWith("0b") || text.startsWith("0B")) {
                    idx = 2;
                    radix = 2;
                } else if (text.startsWith("0x") || text.startsWith("0X")) {
                    idx = 2;
                    radix = 16;
                } else if (text.startsWith("0") && (text.length() > 1)) {
                    idx = 1;
                    radix = 8;
                } else {
                    idx = 0;
                    radix = 10;
                }

                return Long.valueOf(Long.parseLong(text.substring(idx), radix));
            } catch (NumberFormatException exception) {
                // Ignore...
            }
        }

        return null;
    }

    /**
     * Validates whether a given value falls inside the given range.
     * 
     * @param value
     * @param lowerBound
     *            the lower bound (inclusive!) of the range that is valid;
     * @param upperBound
     *            the upper bound (inclusive!) of the range that is valid;
     * @return <code>true</code> if the value is inside the defined range,
     *         <code>false</code> if it is outside.
     */
    public static boolean inRange(long value, long lowerBound, long upperBound) {
        return ((value >= lowerBound) && (value <= upperBound));
    }

    /**
     * Validates whether a given context denotes a numeric value in the given
     * range. In case the value falls outside the defined range, a problem will
     * be added to the given problem reporter.
     * 
     * @param ctx
     *            the parser context that denotes the numeric value to test;
     * @param lower
     *            the lower bound (inclusive!) of the range that is valid;
     * @param upper
     *            the upper bound (inclusive!) of the range that is valid;
     * @param msg
     *            the message to add as problem marker in case the value is
     *            invalid;
     * @param reporter
     *            the problem reporter to add the marker to.
     * @return the parsed numeric value of the given parser context, or
     *         <code>null</code> in case it did not denote a valid numeric
     *         value (not inside the given range, or otherwise invalid).
     */
    public static Long validateValue(ParserRuleContext ctx, long lower, long upper, String msg, ProblemReporter reporter) {
        Long value = decode(ctx.getText());
        if (value == null) {
            return null;
        }

        if (!inRange(value.longValue(), lower, upper)) {
            int line = ctx.getStart().getLine();
            int column = ctx.getStart().getCharPositionInLine();

            reporter.report(line, column, Type.ERROR, Category.SEMANTIC, msg, null);
            return null;
        }

        return value;
    }
}
