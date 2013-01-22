/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

import nl.lxtreme.libtdl.ProblemReporter.Marker;

/**
 * Provides a ruler component for showing the line numbers and other kind of
 * annotations.
 */
public class TdlRulerView extends JComponent implements PropertyChangeListener, DocumentListener {
    // CONSTANTS

    private static final int MARGIN_LEFT = 6;
    private static final int MARGIN_RIGHT = 2;

    // VARIABLES

    private final JEditorPane m_editorPane;

    private volatile int m_lineCount;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlRulerView} instance.
     * 
     * @param editorPane
     *            the editor pane this ruler belongs to;
     * @param styleManager
     *            the style manager to obtain the L&F details from.
     */
    public TdlRulerView(JEditorPane editorPane) {
        m_editorPane = editorPane;
        m_lineCount = -1;
    }

    // METHODS

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        super.addNotify();

        Border border = m_editorPane.getBorder();
        if (border != null) {
            setBorder(border);
        }

        m_editorPane.addPropertyChangeListener(this);

        int lineCount = 0;
        Document document = getDocument();
        if (document != null) {
            document.addDocumentListener(this);
            lineCount = getLineCount(document);
        }

        updateLineCount(lineCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changedUpdate(DocumentEvent event) {
        updateDimensions(event.getDocument());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUpdate(DocumentEvent event) {
        updateDimensions(event.getDocument());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        String name = event.getPropertyName();
        if ("document".equals(name)) {
            Document oldDocument = (Document) event.getOldValue();
            oldDocument.removeDocumentListener(this);

            Document newDocument = (Document) event.getNewValue();
            newDocument.addDocumentListener(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        m_editorPane.removePropertyChangeListener(this);
        Document document = getDocument();
        if (document != null) {
            document.removeDocumentListener(this);
        }

        super.removeNotify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUpdate(DocumentEvent event) {
        updateDimensions(event.getDocument());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics canvas) {
        Rectangle clip = canvas.getClipBounds();
        Insets insets = getInsets();

        // clear bare minimum...
        canvas.setColor(getBackground());
        canvas.fillRect(clip.x, clip.y, clip.width, clip.height);

        int w = getWidth();

        // draw a hairline at the right side...
        canvas.setColor(new Color(0xee, 0xee, 0xee));
        canvas.drawLine(w - 1, clip.y, w - 1, clip.y + clip.height);

        // draw the actual line numbers...
        canvas.setColor(Color.GRAY);
        canvas.setFont(m_editorPane.getFont());

        TdlDocument document = getDocument();

        FontMetrics fm = canvas.getFontMetrics();

        int ch = fm.getHeight();
        int lc = m_lineCount;

        int lineNum = (clip.y / ch);
        int y = insets.top + (lineNum * ch) + fm.getAscent();

        for (; lineNum < lc; y += ch, lineNum++) {
            String text = Integer.toString(lineNum + 1);

            int x = w - fm.stringWidth(text) - MARGIN_RIGHT - insets.right;

            if (document.hasProblemMarkers(lineNum + 1)) {
                canvas.setColor(Color.RED);
            } else {
                canvas.setColor(Color.GRAY);
            }

            canvas.drawString(text, x, y);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToolTipText(MouseEvent event) {
        TdlDocument document = getDocument();

        // convert mouse Y-coordinates to a line number
        FontMetrics fm = m_editorPane.getFontMetrics(m_editorPane.getFont());
        int charHeight = fm.getHeight();

        int lineNo = (event.getY() / charHeight) + 1;

        if (document.hasProblemMarkers(lineNo)) {
            StringBuilder tt = new StringBuilder();
            for (Marker m : document.getProblemMarkers(lineNo)) {
                tt.append(m.toString()).append("\n");
            }
            return tt.toString();
        }

        return super.getToolTipText(event);
    }

    /**
     * Returns the document.
     * 
     * @return the document being edited, never <code>null</code>.
     */
    private TdlDocument getDocument() {
        return (TdlDocument) m_editorPane.getDocument();
    }

    /**
     * Returns the number of lines in the given document.
     * 
     * @param document
     *            the document to count the lines of, cannot be
     *            <code>null</code>.
     * @return a line count, >= 0.
     */
    private int getLineCount(Document document) {
        return document.getDefaultRootElement().getElementCount();
    }

    /**
     * Called when the document changes, allowing us to determine whether the
     * number of lines changed or not.
     */
    private void updateDimensions(Document document) {
        updateLineCount(getLineCount(document));
    }

    /**
     * Updates the dimensions of this component according to the given line
     * count.
     * 
     * @param newLineCount
     *            the new line count, >= 0.
     */
    private void updateLineCount(int newLineCount) {
        if (m_lineCount != newLineCount) {
            // Update dimensions...
            m_lineCount = newLineCount;

            Insets insets = getInsets();
            Dimension editorDims = m_editorPane.getPreferredSize();

            // Always follow the height of the editor itself...
            int height = editorDims.height;

            FontMetrics fm = m_editorPane.getFontMetrics(m_editorPane.getFont());
            int charWidth = fm.charWidth('8');

            // determine how many digits should be presented at max...
            int d = Math.max(1, (int) Math.log10(m_lineCount) + 1);
            int width = (d * charWidth) + MARGIN_LEFT + MARGIN_RIGHT + insets.left + insets.right;

            setPreferredSize(new Dimension(width, height));

            repaint(50L);
        }
    }
}
