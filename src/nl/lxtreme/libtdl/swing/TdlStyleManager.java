/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import static nl.lxtreme.libtdl.grammar.TdlHelper.TdlTokenType.*;

import java.awt.*;
import java.util.*;

import nl.lxtreme.libtdl.grammar.TdlHelper.TdlTokenType;

/**
 * Denotes a manager for keeping token styles.
 */
public class TdlStyleManager {
    // INNER TYPES

    /**
     * Represents how a token should be rendered.
     */
    public static interface TokenStyle {
        // METHODS

        /**
         * @param altColor
         * @return
         */
        TokenStyle derive(Color altColor);

        /**
         * @param altStyle
         * @return
         */
        TokenStyle derive(int altStyle);

        /**
         * @return the color to render the token in, may be <code>null</code> to
         *         use
         *         the default color.
         */
        Color getColor();

        /**
         * @return the font style to use, as used by {@link Font}.
         */
        int getFontStyle();
    }

    /**
     * Represents a default implementation of {@link TokenStyle}.
     */
    public static class TokenStyleImpl implements TokenStyle {
        // VARIABLES

        private final Color m_color;
        private final int m_fontStyle;

        // CONSTRUCTORS

        /**
         * Creates a new {@link TokenStyleImpl} instance.
         */
        public TokenStyleImpl(Color color) {
            this(color, false /* bold */, false /* italic */);
        }

        /**
         * Creates a new {@link TokenStyleImpl} instance.
         */
        public TokenStyleImpl(Color color, boolean bold, boolean italic) {
            m_color = color;

            int fontStyle = 0;
            if (bold) {
                fontStyle |= Font.BOLD;
            }
            if (italic) {
                fontStyle |= Font.ITALIC;
            }
            m_fontStyle = fontStyle;
        }

        /**
         * Creates a new {@link TokenStyleImpl} instance.
         */
        public TokenStyleImpl(Color color, int fontStlye) {
            m_color = color;
            m_fontStyle = fontStlye;
        }

        // METHODS

        @Override
        public TokenStyle derive(Color altColor) {
            return new TokenStyleImpl(altColor, m_fontStyle);
        }

        @Override
        public TokenStyle derive(int altStyle) {
            return new TokenStyleImpl(m_color, altStyle);
        }

        @Override
        public Color getColor() {
            return m_color;
        }

        @Override
        public int getFontStyle() {
            return m_fontStyle;
        }
    }

    // CONSTANTS

    public static final int ERROR_STYLE = 0x08;

    // VARIABLES

    private final Map<Integer, TokenStyle> m_styles;
    private final Font m_editorFont;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlStyleManager}.
     */
    public TdlStyleManager() {
        m_styles = new HashMap<Integer, TokenStyle>();

        Color lead = new Color(25, 25, 25);
        Color iron = new Color(76, 76, 76);
        Color midnight = new Color(0, 0, 192);
        Color maroon = new Color(128, 0, 64);
        Color aluminium = new Color(153, 153, 153);

        // define(new TokenStyleImpl(Color.RED, ERROR_STYLE), ERROR_TOKEN);

        define(new TokenStyleImpl(aluminium, Font.ITALIC), COMMENT);
        define(new TokenStyleImpl(iron), NUMERIC, UNIT, EXPRESSION);
        define(new TokenStyleImpl(midnight, Font.PLAIN), TERM);
        define(new TokenStyleImpl(maroon, Font.BOLD), KEYWORD);
        define(new TokenStyleImpl(lead), TEXT);

        m_editorFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    }

    // METHODS

    /**
     * Defines a token style for one or more token types.
     * 
     * @param style
     * @param tokenTypes
     */
    public void define(TokenStyle style, TdlTokenType... tokenTypes) {
        for (TdlTokenType type : tokenTypes) {
            m_styles.put(type.ordinal(), style);
        }
    }

    /**
     * Returns the default token style.
     * 
     * @return the default style to render text in, never <code>null</code>.
     */
    public TokenStyle getDefaultStyle() {
        return getStyle(TEXT);
    }

    /**
     * Returns the font for the editor.
     * 
     * @return the font used in the editor, never <code>null</code>.
     */
    public Font getEditorFont() {
        return m_editorFont;
    }

    /**
     * Returns the token style for a particular type of token.
     * 
     * @param token
     *            the token to get the style for, cannot be <code>null</code>.
     * @return a token style to render text in, never <code>null</code>.
     */
    public TokenStyle getStyle(TdlTokenType tokenType) {
        TokenStyle style = m_styles.get(tokenType.ordinal());
        if (style == null) {
            // fall back to default style...
            style = getDefaultStyle();
        }
        return style;
    }
}
