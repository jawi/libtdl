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

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.TdlHelper.TdlToken;
import nl.lxtreme.libtdl.TdlHelper.TdlTokenType;
import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides a custom {@link Document} for handling TDL texts.
 */
public class TdlDocument extends PlainDocument {
    // INNER TYPES

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
     * Provides an implementation of {@link TdlConfig} that is mutable.
     */
    static class MutableConfig implements TdlConfig {
        // VARIABLES

        private TdlDialect m_dialect = TdlDialect.ADVANCED;
        private int m_maxStages = 10;
        private int m_maxChannels = 32;
        private boolean m_ddrMode = false;

        // METHODS

        @Override
        public TdlDialect getDialect() {
            return m_dialect;
        }

        @Override
        public int getMaxChannels() {
            return m_maxChannels;
        }

        @Override
        public int getMaxStages() {
            return m_maxStages;
        }

        @Override
        public boolean isDdrMode() {
            return m_ddrMode;
        }

        public void setDdrMode(boolean ddrMode) {
            m_ddrMode = ddrMode;
        }

        public void setDialect(TdlDialect dialect) {
            if (dialect == null) {
                throw new IllegalArgumentException("Dialect cannot be null!");
            }
            m_dialect = dialect;
        }

        public void setMaxChannels(int maxChannels) {
            if (maxChannels <= 0) {
                throw new IllegalArgumentException("Max channels should be greater than 0!");
            }
            if (maxChannels >= 32) {
                throw new IllegalArgumentException("Max channels should be less than 32!");
            }
            m_maxChannels = maxChannels;
        }

        public void setMaxStages(int maxStages) {
            if (maxStages <= 0) {
                throw new IllegalArgumentException("Max stages should be greater than 0!");
            }
            m_maxStages = maxStages;
        }
    }

    /**
     * Small container for collecting problem markers.
     */
    static class ProblemCollector implements ProblemListener {
        // VARIABLES

        private final Map<Integer, List<Marker>> m_markers = new HashMap<Integer, List<Marker>>();

        // METHODS

        @Override
        public void add(Marker marker) {
            List<Marker> markers = m_markers.get(marker.getLine());
            if (markers == null) {
                markers = new ArrayList<Marker>();
                m_markers.put(marker.getLine(), markers);
            }
            markers.add(marker);
        }

        public void clear() {
            m_markers.clear();
        }

        public List<Marker> getAllMarkers() {
            List<Marker> result = new ArrayList<Marker>();
            for (List<Marker> markers : m_markers.values()) {
                result.addAll(markers);
            }
            return result;
        }

        public List<Marker> getMarkers(int lineNo) {
            List<Marker> markers = m_markers.get(lineNo);
            if (markers == null) {
                return Collections.emptyList();
            }
            return new ArrayList<Marker>(markers);
        }

        public boolean hasMarkers(int lineNo) {
            return m_markers.containsKey(lineNo);
        }
    }

    /**
     * Small container for collecting term definitions.
     */
    static class TermDefinitionCollector implements TermDefinitionListener {
        // VARIABLES

        private final Map<String, String> m_definitions = new HashMap<String, String>();

        // METHODS

        /**
         * Clears all definitions from this collector.
         */
        public void clear() {
            m_definitions.clear();
        }

        /**
         * @param name
         * @return the definition for the term with the given name, or
         *         <code>null</code> if not defined.
         */
        public String getDefinition(String name) {
            String def = m_definitions.get(Util.normalizeName(name));
            if (def == null) {
                return null;
            }
            return def;
        }

        @Override
        public void termDeclared(String name, String definition) {
            m_definitions.put(name, definition);
        }
    }

    /**
     * Facade for throttling the number of parsing actions when typing into the
     * document.
     */
    final class ThrottlingDocumentParser implements ActionListener {
        // CONSTANTS

        /**
         * Delay between consecutive edits in milliseconds where edits are added
         * together. If the delay is greater than this, then separate undo
         * operations are done, otherwise they are combined.
         */
        public static final int IDLE_DELAY_MS = 300;

        // VARIABLES

        private final javax.swing.Timer m_timer;

        // CONSTRUCTORS

        /**
         * Creates a new {@link ThrottlingDocumentParser} instance.
         */
        public ThrottlingDocumentParser() {
            m_timer = new Timer(IDLE_DELAY_MS, this);
            m_timer.setInitialDelay(0);
            m_timer.setRepeats(false);
            m_timer.setCoalesce(true);
        }

        // METHODS

        @Override
        public void actionPerformed(ActionEvent event) {
            parseText();
        }

        public void scheduleParse() {
            if (m_timer.isRunning()) {
                m_timer.restart();
            } else {
                m_timer.start();
            }
        }
    }

    // CONSTANTS

    private static final int DEFAULT_TAB_SIZE = 4;

    // VARIABLES

    private final MutableConfig m_config;
    private final ProblemCollector m_problems;
    private final TermDefinitionCollector m_definitions;
    private final ThrottlingDocumentParser m_parser;
    private final UndoManager m_undoMgr;

    private volatile List<TdlToken> m_tokens;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlDocument} instance for the given dialect.
     */
    public TdlDocument() {
        m_config = new MutableConfig();
        m_definitions = new TermDefinitionCollector();
        m_problems = new ProblemCollector();
        m_undoMgr = new CompoundUndoManager();
        m_parser = new ThrottlingDocumentParser();

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
     * Returns all available problem markers.
     * 
     * @return a list with all problem markers.
     */
    public List<Marker> getAllProblemMarkers() {
        readLock();
        try {
            return m_problems.getAllMarkers();
        } finally {
            readUnlock();
        }
    }

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
     * Returns the problem markers at the given line.
     * 
     * @param lineNo
     *            the line number to get the problem markers for.
     * @return the list with problem markers, never <code>null</code>.
     */
    public List<Marker> getProblemMarkers(int lineNo) {
        readLock();
        try {
            return m_problems.getMarkers(lineNo);
        } finally {
            readUnlock();
        }
    }

    /**
     * Returns the definition for the term with the given name,
     * 
     * @param name
     *            the name of the term to get the definition for, cannot be
     *            <code>null</code>.
     * @return the definition for the given term, or <code>null</code> if no
     *         definition was found.
     */
    public String getTermDefinition(String name) {
        return m_definitions.getDefinition(name);
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
        final List<Marker> markers;

        readLock();
        try {
            tokens = (m_tokens == null) ? null : new ArrayList<TdlToken>(m_tokens);
            markers = m_problems.getAllMarkers();
        } finally {
            readUnlock();
        }

        if (tokens == null) {
            return Collections.emptyList();
        }

        List<TdlToken> result = new ArrayList<TdlToken>();

        for (int i = 0; i < tokens.size(); i++) {
            TdlToken token = tokens.get(i);

            int startIdx = token.getStartOffset();
            int endIdx = token.getStopOffset();

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
     * Returns whether or not the given line number contains any problem
     * markers.
     * 
     * @param lineNo
     *            the (1-based) line number to test.
     * @return <code>true</code> if there are problem markers on the given line,
     *         <code>false</code> otherwise.
     */
    public boolean hasProblemMarkers(int lineNo) {
        readLock();
        try {
            return m_problems.hasMarkers(lineNo);
        } finally {
            readUnlock();
        }
    }

    /**
     * Reparses the entire text of the current document.
     */
    public void reparse() {
        parseText();
    }

    /**
     * Sets whether or not DDR mode is to be used.
     * 
     * @param ddrMode
     *            <code>true</code> if DDR mode should be enabled,
     *            <code>false</code> otherwise.
     */
    public void setDdrMode(boolean ddrMode) {
        m_config.setDdrMode(ddrMode);
        m_parser.scheduleParse();
    }

    /**
     * Sets the dialect for this document.
     * 
     * @param dialect
     *            the dialect to set, cannot be <code>null</code>.
     */
    public void setDialect(TdlDialect dialect) {
        m_config.setDialect(dialect);
        m_parser.scheduleParse();
    }

    /**
     * Sets the maximum number of channels.
     * 
     * @param maxChannels
     *            the maximum number of channels, > 0 && < 32.
     */
    public void setMaxChannels(int maxChannels) {
        m_config.setMaxChannels(maxChannels);
        m_parser.scheduleParse();
    }

    /**
     * Sets the maximum number of stages.
     * 
     * @param maxStages
     *            the maximum number of trigger stages supported, > 0 && < 32.
     */
    public void setMaxStages(int maxStages) {
        m_config.setMaxStages(maxStages);
        m_parser.scheduleParse();
    }

    /**
     * Parses the current text of this document, tokenizes it and validates it.
     */
    final void parseText() {
        try {
            String text;
            int p0 = 0, p1;

            readLock();
            try {
                p1 = getLength();
                text = getText(p0, p1);
            } finally {
                readUnlock();
            }

            writeLock();
            try {
                m_problems.clear();
                m_definitions.clear();

                TdlHelper helper = new TdlHelper(m_config);
                helper.addProblemListener(m_problems);
                helper.addTermDeclarationListener(m_definitions);

                m_tokens = helper.tokenize(text);

                helper.validate();
            } finally {
                writeUnlock();
            }

            fireChangedUpdate(new DefaultDocumentEvent(p0, p1 - p0, DocumentEvent.EventType.CHANGE));
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
