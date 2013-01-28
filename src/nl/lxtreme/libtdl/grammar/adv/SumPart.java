/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

/**
 * Denotes a part of a trigger sum, that can be an input or an operation.
 */
interface SumPart {
    // CONSTANTS

    static final int OP_NOP = 0;
    static final int OP_ANY = 1;
    static final int OP_AND = 2;
    static final int OP_NAND = 3;
    static final int OP_OR = 4;
    static final int OP_NOR = 5;
    static final int OP_XOR = 6;
    static final int OP_NXOR = 7;
    static final int OP_A = 8;
    static final int OP_B = 9;

    // METHODS

    /**
     * @return the parent for this sum part, can be <code>null</code> if there's
     *         no parent.
     */
    SumPart getParent();

    /**
     * Initializes this sum part.
     */
    void init();

    /**
     * Inverts this part of the sum.
     */
    void invert();
}
