/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import static java.awt.RenderingHints.*;
import static nl.lxtreme.libtdl.swing.TdlStyleManager.*;

import java.awt.*;
import java.util.List;

import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.Position.Bias;

import nl.lxtreme.libtdl.ProblemReporter.Marker;
import nl.lxtreme.libtdl.TdlHelper.TdlToken;
import nl.lxtreme.libtdl.TdlHelper.TdlTokenType;
import nl.lxtreme.libtdl.swing.TdlStyleManager.TokenStyle;

/**
 * Provides a custom view for representing TDL constructs.
 */
public class TdlSyntaxView extends PlainView {
    // CONSTANTS

    private static final BasicStroke ERROR_STROKE = new BasicStroke(1.0f /* width */, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND);

    // VARIABLES

    private final TdlStyleManager m_styleManager;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlSyntaxView} for the given element.
     * 
     * @param element
     *            the root element of this view, cannot be <code>null</code>;
     * @param styleManager
     *            the style manager to use, cannot be <code>null</code>.
     */
    public TdlSyntaxView(Element element, TdlStyleManager styleManager) {
        super(element);
        m_styleManager = styleManager;
    }

    // METHODS

    /**
     * Overridden in order to return the {@link TdlDocument} immediately.
     */
    @Override
    public TdlDocument getDocument() {
        return (TdlDocument) super.getDocument();
    }

    /**
     * Overridden in order to return the tooltip text for any problem markers
     * (if any).
     */
    @Override
    public String getToolTipText(float x, float y, Shape allocation) {
        TdlDocument document = getDocument();

        int pos = viewToModel(x, y, allocation, new Bias[] { Bias.Forward });

        Element element = document.getParagraphElement(pos);
        if (element != null) {
            StringBuilder sb = new StringBuilder();

            int startPos = element.getStartOffset();
            int endPos = element.getEndOffset();

            List<TdlToken> tokens = document.getTokens(startPos, endPos);
            for (TdlToken token : tokens) {
                int startIdx = token.getStartOffset();
                int endIdx = token.getStopOffset();

                // narrow down to the requested location...
                if ((pos >= startIdx) && (pos <= endIdx)) {
                    List<Marker> markers = token.getMarkers();
                    if (!markers.isEmpty()) {
                        for (Marker marker : markers) {
                            sb.append(marker.toString()).append("\n");
                        }
                    } else if (token.getType() == TdlTokenType.TERM) {
                        String termDefinition = document.getTermDefinition(token.getText());
                        if (termDefinition != null) {
                            sb.append(termDefinition);
                        }
                    }
                }
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        }

        return super.getToolTipText(x, y, allocation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int drawSelectedText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
        Graphics2D canvas = (Graphics2D) g.create();
        try {
            if (p0 == p1) {
                return x;
            }
            return drawText(canvas, x, y, p0, p1, true /* selected */);
        } finally {
            canvas.dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
        Graphics2D canvas = (Graphics2D) g.create();
        try {
            if (p0 == p1) {
                return x;
            }
            return drawText(canvas, x, y, p0, p1, false /* selected */);
        } finally {
            canvas.dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateDamage(DocumentEvent changeEvent, Shape shape, ViewFactory factory) {
        super.updateDamage(changeEvent, shape, factory);

        Container parent = getContainer();
        if (parent != null) {
            if (shape != null) {
                Rectangle bounds = shape.getBounds();
                parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            } else {
                parent.repaint();
            }
        }
    }

    /**
     * Creates the rendering hints for this view.
     */
    private RenderingHints createRenderingHints() {
        RenderingHints hints = new RenderingHints(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC);
        hints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        hints.put(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY);
        hints.put(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY);
        hints.put(KEY_RENDERING, VALUE_RENDER_QUALITY);
        return hints;
    }

    /**
     * @param canvas
     *            the canvas to paint on;
     * @param x
     *            the starting X coordinate >= 0;
     * @param y
     *            the starting Y coordinate >= 0;
     * @param startPos
     *            the beginning position in the model >= 0;
     * @param endPos
     *            the ending position in the model >= 0;
     * @param selected
     *            <code>true</code> if the text should be drawn in selected
     *            style, <code>false</code> to draw it normally.
     * @return the X location of the end of the range >= 0.
     */
    private int drawText(Graphics2D canvas, int x, int y, int startPos, int endPos, boolean selected) {
        final TdlDocument doc = getDocument();
        final List<TdlToken> tokens = doc.getTokens(startPos, endPos);

        try {
            Segment segment = getLineBuffer();
            int pos = startPos;

            for (TdlToken token : tokens) {
                int offset = token.getStartOffset();
                int length = token.getLength();

                if (offset > pos) {
                    doc.getText(pos, offset - pos, segment);
                    // System.out.println("1. DRAW @ " + x + "," + y + " [" +
                    // segment + "]");
                    x = drawToken(canvas, segment, x, y, pos, getRenderer(token));
                }

                doc.getText(offset, length, segment);
                // System.out.println("2. DRAW @ " + x + "," + y + " [" +
                // segment + "]");
                x = drawToken(canvas, segment, x, y, pos, getRenderer(token));

                pos = offset + length;
            }

            if (pos < endPos) {
                // Draw all unrecognized text in the default style...
                doc.getText(pos, endPos - pos, segment);
                // System.out.println("3. DRAW @ " + x + "," + y + ": [" +
                // segment + "]");
                x = drawToken(canvas, segment, x, y, pos, getDefaultStyle());
            }
        } catch (BadLocationException e) {
            // Ignore this...
        }

        return x;
    }

    /**
     * @param graphics
     * @param segment
     * @param x
     * @param y
     * @param startOffset
     * @param style
     * @return
     */
    private int drawToken(Graphics2D graphics, Segment segment, int x, int y, int startOffset, TokenStyle style) {
        int fontStyle = style.getFontStyle();
        Color color = style.getColor();

        Font font = graphics.getFont().deriveFont(fontStyle);
        graphics.setFont(font);
        graphics.setColor(color);

        FontMetrics fm = graphics.getFontMetrics();
        int w = Utilities.getTabbedTextWidth(segment, fm, 0, this, startOffset);

        int result = Utilities.drawTabbedText(segment, x, y, graphics, this, startOffset);
        if ((fontStyle & ERROR_STYLE) != 0) {
            graphics.setColor(Color.RED);
            graphics.setStroke(ERROR_STROKE);
            graphics.setRenderingHints(createRenderingHints());

            int waveY = y + 1;
            for (int waveX = x; waveX < (x + w); waveX += 4) {
                if ((waveX + 2) <= ((x + w) - 1)) {
                    graphics.drawLine(waveX, waveY + 2, waveX + 2, waveY);
                }
                if ((waveX + 4) <= ((x + w) - 1)) {
                    graphics.drawLine(waveX + 3, waveY + 1, waveX + 4, waveY + 2);
                }
            }
        }

        return result;
    }

    /**
     * Returns the default rendering style.
     * 
     * @return the default token style, never <code>null</code>.
     */
    private TokenStyle getDefaultStyle() {
        return m_styleManager.getDefaultStyle();
    }

    /**
     * Returns the renderer for rendering a particular token.
     * 
     * @param token
     *            the token to return a renderer for, cannot be
     *            <code>null</code>.
     * @return a token renderer, never <code>null</code>.
     */
    private TokenStyle getRenderer(TdlToken token) {
        TokenStyle style = m_styleManager.getStyle(token.getType());
        if (!token.getMarkers().isEmpty()) {
            style = style.derive(style.getFontStyle() | ERROR_STYLE);
        }
        return style;
    }
}
