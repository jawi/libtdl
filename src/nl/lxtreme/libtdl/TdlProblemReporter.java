/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.*;

/**
 * Provides an adapter/bridge for the ANTLRv4 {@link BaseErrorListener} to let
 * it use generic {@link ProblemListener}s to forward its problems to.
 */
public class TdlProblemReporter implements ANTLRErrorListener, ProblemReporter {
    // VARIABLES

    private final List<ProblemListener> m_listeners;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlProblemReporter} instance.
     */
    public TdlProblemReporter() {
        m_listeners = new ArrayList<ProblemListener>();
    }

    // METHODS

    @Override
    public synchronized void addListener(ProblemListener aListener) {
        if (!m_listeners.contains(aListener)) {
            m_listeners.add(aListener);
        }
    }

    /**
     * Removes all existing listeners from this reporter.
     */
    public synchronized void clearListeners() {
        m_listeners.clear();
    }

    /**
     * Installs this adapter on the given recognizer, replacing all of its error
     * listeners with this adapter.
     *
     * @param recognizers
     *            the array with recognizers (= parsers and/or lexers) to
     *            install this adapter for, cannot be <code>null</code> or an
     *            empty array.
     * @throws IllegalArgumentException
     *             in case the given recognizers array was invalid.
     */
    public void installOn(Recognizer<?, ?>... recognizers) {
        if ((recognizers == null) || (recognizers.length < 1)) {
            throw new IllegalArgumentException("Need at least one recognizer to install on!");
        }
        for (Recognizer<?, ?> recognizer : recognizers) {
            recognizer.removeErrorListeners();
            recognizer.addErrorListener(this);
        }
    }

    @Override
    public synchronized void removeListener(ProblemListener aListener) {
        if (m_listeners.contains(aListener)) {
            m_listeners.remove(aListener);
        }
    }

    @Override
    public void report(Marker marker) {
        List<ProblemListener> listeners = getListeners();
        for (ProblemListener listener : listeners) {
            listener.add(marker);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean unknown,
            BitSet ambigAlts, ATNConfigSet configs) {
        MarkerBuilder builder = new MarkerBuilder();
        Marker marker = builder.setCategory(Category.AMBIGUITY).setType(Type.WARNING)
                .setLocation(startIndex, stopIndex - startIndex, -1, -1)
                .setDescription("Ambiguity found [" + startIndex + ".." + stopIndex + "]").build();

        List<ProblemListener> listeners = getListeners();
        for (ProblemListener listener : listeners) {
            listener.add(marker);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet alts,
            ATNConfigSet configs) {
        // Nop; our grammar shouldn't be that difficult that StrongLA cannot
        // cope with it...
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int unknown,
            ATNConfigSet configs) {
        // Nop; our grammar shouldn't be that difficult that StrongLA cannot
        // cope with it...
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        int offset = -1;
        int length = -1;
        if (offendingSymbol instanceof Token) {
            offset = ((Token) offendingSymbol).getStartIndex();
            length = ((Token) offendingSymbol).getStopIndex() - offset;
        }

        MarkerBuilder builder = new MarkerBuilder();
        Marker marker = builder.setCategory(Category.SYNTAX).setType(Type.ERROR)
                .setLocation(offset, length, line, charPositionInLine).setDescription(msg).setCause(e).build();

        List<ProblemListener> listeners = getListeners();
        for (ProblemListener listener : listeners) {
            listener.add(marker);
        }
    }

    /**
     * @return a new list of problem reporters, never <code>null</code>.
     */
    private synchronized List<ProblemListener> getListeners() {
        return new ArrayList<ProblemListener>(m_listeners);
    }
}
