/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

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
    public void report(int line, int column, Type type, Category category, String description, Exception cause) {
        Marker marker = new Marker(line, column, type, category, description, cause);
        List<ProblemListener> listeners = getListeners();
        for (ProblemListener listener : listeners) {
            listener.add(marker);
        }
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet ambigAlts,
            ATNConfigSet configs) {
        int line = -1;
        int column = -1;
        String details = "Ambiguity found [" + startIndex + ".." + stopIndex + "]";
        Marker marker = new Marker(line, column, Type.WARNING, Category.AMBIGUITY, details);

        List<ProblemListener> listeners = getListeners();
        for (ProblemListener listener : listeners) {
            listener.add(marker);
        }
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
            ATNConfigSet configs) {
        // Nop; our grammar shouldn't be that difficult that StrongLA cannot
        // cope with it...
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, ATNConfigSet configs) {
        // Nop; our grammar shouldn't be that difficult that StrongLA cannot
        // cope with it...
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        Marker marker = new Marker(line, charPositionInLine, Type.ERROR, Category.SYNTAX, msg, e);

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
