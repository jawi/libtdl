/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import java.awt.event.*;
import java.util.*;

import javax.swing.Timer;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.ProblemReporter.Marker;
import nl.lxtreme.libtdl.grammar.TdlHelper.TdlToken;
import nl.lxtreme.libtdl.grammar.TdlHelper.TdlTokenType;

/**
 * Provides a custom {@link Document} for handling TDL texts.
 */
public class TdlDocument extends PlainDocument {
    // INNER TYPES

    /**
     * Facade for throttling the number of parsing actions when typing into the
     * document.
     */
    final class DocumentParser implements ActionListener {
        // CONSTANTS

        /**
         * Delay between consecutive edits in milliseconds where edits are added
         * together. If the delay is greater than this, then separate undo
         * operations are done, otherwise they are combined.
         */
        public static final int IDLE_DELAY_MS = 300;

        // VARIABLES

        private final javax.swing.Timer m_timer;
        private volatile long m_startMillis = System.currentTimeMillis();

        // CONSTRUCTORS

        /**
         * Creates a new {@link DocumentParser} instance.
         */
        public DocumentParser() {
            m_timer = new Timer(IDLE_DELAY_MS, this);
            m_timer.setRepeats(false);
            m_timer.setCoalesce(true);
        }

        // METHODS

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("PARSE EVENT!");
            doParseText(true /* includeValidation */);
            m_startMillis = System.currentTimeMillis();
        }

        /**
         * Parses the current contents of this document and creates a list of
         * tokens out of it.
         */
        public void parseText() {
            long now = System.currentTimeMillis();
            if ((now - m_startMillis) > IDLE_DELAY_MS) {
                doParseText(false /* includeValidation */);
            }
            m_startMillis = now;
        }

        public void scheduleParse() {
            if (m_timer.isRunning()) {
                m_timer.restart();
            } else {
                m_timer.start();
            }
        }
    }

    /**
     * Provides a more usable undo manager, based on code from <a
     * href="http://jsyntaxpane.googlecode.com">JSyntaxKit</a>.
     */
    static class CompoundUndoManager extends UndoManager {
        // CONSTANTS

        /**
         * Delay between consecutive edits in milliseconds where edits are added
         * together. If the delay is greater than this, then separate undo
         * operations are done, otherwise they are combined.
         */
        public static final int IDLE_DELAY_MS = 500;

        // VARIABLES

        private final Object m_lock = new Object();

        private long m_startMillis;
        private CompoundEdit m_compoundEdit;

        // METHODS

        @Override
        public boolean addEdit(UndoableEdit aEdit) {
            synchronized (m_lock) {
                long now = System.currentTimeMillis();

                if (m_compoundEdit == null) {
                    m_compoundEdit = new CompoundEdit();
                }

                m_compoundEdit.addEdit(aEdit);

                if ((now - m_startMillis) > IDLE_DELAY_MS) {
                    commitCompoundEdit();
                }

                m_startMillis = now;
            }
            return true;
        }

        @Override
        public boolean canRedo() {
            commitCompoundEdit();
            return super.canRedo();
        }

        @Override
        public boolean canUndo() {
            commitCompoundEdit();
            return super.canUndo();
        }

        @Override
        public void discardAllEdits() {
            synchronized (m_lock) {
                m_compoundEdit = null;
            }
            super.discardAllEdits();
        }

        @Override
        public void redo() throws CannotRedoException {
            commitCompoundEdit();
            super.redo();
        }

        @Override
        public void undo() throws CannotUndoException {
            commitCompoundEdit();
            super.undo();
        }

        /**
         * Commits an ongoing edit.
         */
        private void commitCompoundEdit() {
            synchronized (m_lock) {
                if (m_compoundEdit != null) {
                    m_compoundEdit.end();
                    super.addEdit(m_compoundEdit);
                    m_compoundEdit = null;
                }
            }
        }
    }

    /**
     * Small container for collecting problem markers.
     */
    static class ProblemCollector implements ProblemListener {
        // VARIABLES

        private final List<Marker> m_markers = new ArrayList<Marker>();

        // METHODS

        public void clear() {
            m_markers.clear();
        }

        public List<Marker> getMarkers() {
            return new ArrayList<Marker>(m_markers);
        }

        @Override
        public void add(Marker marker) {
            System.out.printf("%s%n", marker);
            m_markers.add(marker);
        }
    }

    // CONSTANTS

    private static final int DEFAULT_TAB_SIZE = 4;

    // VARIABLES

    private final TdlHelper m_tdlHelper;
    private final ProblemCollector m_problems;
    private final DocumentParser m_parser;
    private final UndoManager m_undoMgr;

    private List<TdlToken> m_tokens;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlDocument} instance for the given lexer.
     * 
     * @param lexer
     *            the lexer to use, cannot be <code>null</code>.
     */
    public TdlDocument(TdlHelper lexer) {
        m_tdlHelper = lexer;
        m_problems = new ProblemCollector();
        m_undoMgr = new CompoundUndoManager();

        m_parser = new DocumentParser();

        m_tdlHelper.addProblemListener(m_problems);

        // Common initialization...
        putProperty(PlainDocument.tabSizeAttribute, 4);

        addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent event) {
                UndoableEdit edit = event.getEdit();
                if (edit.isSignificant()) {
                    if (m_undoMgr != null) {
                        m_undoMgr.addEdit(edit);
                    }
                }
            }
        });
    }

    // METHODS

    /**
     * Returns the number of spaces at the beginning of the line denoted by the
     * given caret position.
     * 
     * @param position
     *            the caret position to determine the indentation level of.
     * @return the number of spaces at the beginning of the line, >= 0.
     */
    public int getIndentLevel(int position) {
        Element element = getParagraphElement(position);
        if (element == null) {
            return 0;
        }

        int start = element.getStartOffset();
        int end = element.getEndOffset();

        int spaceCount = 0;

        // Take a look at the actual contents of the line, if it ends with a
        // colon (that can only occur after a stage declaration), we indent a
        // level deeper...
        List<TdlToken> tokens = getTokens(start, end);
        if (!tokens.isEmpty()) {
            boolean onlyWS = true;
            for (int i = 0, size = tokens.size(); i < size; i++) {
                TdlToken token = tokens.get(i);
                if (token.getType() == TdlTokenType.WS) {
                    // spaceCount++;
                } else {
                    onlyWS = false;
                    spaceCount = 0;
                }
            }

            if (onlyWS) {
                // line is entirely empty...
                spaceCount = 0;
            }
        }

        return spaceCount;
    }

    /**
     * Returns the line number for the given position in the document.
     * 
     * @param position
     *            a document position, >= 0.
     * @return a line number, >= 0.
     */
    public int getLineNumber(int position) {
        return getDefaultRootElement().getElementIndex(position);
    }

    /**
     * @return the problem markers, never <code>null</code>.
     */
    public Collection<Integer> getProblemMarkerLines() {
        List<Marker> markers = m_problems.getMarkers();
        Set<Integer> result = new HashSet<Integer>();
        for (Marker marker : markers) {
            result.add(marker.getLine());
        }
        return result;
    }

    /**
     * Returns a subset of (parsed) tokens.
     * 
     * @param startPos
     *            the start position of the first token to return;
     * @param endPos
     *            the end position of the last token to return.
     * @return a list of tokens, never <code>null</code>.
     */
    public List<TdlToken> getTokens(int startPos, int endPos) {
        final List<TdlToken> tokens;
        synchronized (this) {
            tokens = (m_tokens == null) ? null : new ArrayList<TdlToken>(m_tokens);
        }
        if (tokens == null) {
            return Collections.emptyList();
        }

        List<TdlToken> result = new ArrayList<TdlToken>();

        List<Marker> markers = m_problems.getMarkers();

        for (int i = 0; i < tokens.size(); i++) {
            TdlToken token = tokens.get(i);

            int startIdx = token.getOffset();
            int endIdx = startIdx + token.getLength();

            if ((startIdx >= startPos) && (endIdx <= endPos)) {
                TdlToken newToken = token.clone();

                for (Marker marker : markers) {
                    int mStartIdx = marker.getOffset();
                    int mEndIdx = mStartIdx + marker.getLength();

                    if ((mStartIdx >= startIdx) && (mEndIdx <= endIdx)) {
                        newToken.getMarkers().add(marker);
                    }
                }

                result.add(newToken);
            }
        }

        return result;
    }

    /**
     * @param includeValidation
     *            <code>true</code> to include validation of the text,
     *            <code>false</code> to only split it up in tokens.
     */
    final void doParseText(boolean includeValidation) {
        try {
            String text = getText(0, getLength());

            synchronized (this) {
                if (includeValidation) {
                    m_problems.clear();
                }

                m_tokens = m_tdlHelper.tokenize(text);

                if (includeValidation) {
                    m_tdlHelper.validate();
                }
            }
        } catch (BadLocationException exception) {
            // Ignore
        }
    }

    @Override
    protected void fireChangedUpdate(DocumentEvent event) {
        m_parser.scheduleParse();
        super.fireChangedUpdate(event);
    }

    @Override
    protected void fireInsertUpdate(DocumentEvent event) {
        m_parser.scheduleParse();
        super.fireInsertUpdate(event);
    }

    @Override
    protected void fireRemoveUpdate(DocumentEvent event) {
        m_parser.scheduleParse();
        super.fireRemoveUpdate(event);
    }

    @Override
    protected void fireUndoableEditUpdate(UndoableEditEvent event) {
        m_parser.scheduleParse();
        super.fireUndoableEditUpdate(event);
    }

    /**
     * @return a tab size, in spaces, > 0.
     */
    protected final int getTabSize() {
        Integer tabSize = (Integer) getProperty(PlainDocument.tabSizeAttribute);
        if (tabSize == null) {
            return DEFAULT_TAB_SIZE;
        }
        return tabSize.intValue();
    }
}