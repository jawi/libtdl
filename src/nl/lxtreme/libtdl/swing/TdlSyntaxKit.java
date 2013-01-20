/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import static javax.swing.text.JTextComponent.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides a custom syntax kit for highlighting TDL constructs.
 */
public class TdlSyntaxKit extends DefaultEditorKit implements ViewFactory {
    // INNER TYPES

    /**
     * Provides an auto-indent functionality: when the user presses enter on a
     * line that is already indented, the next line will be indented as well.
     */
    static final class AutoIndentAction extends TextAction {
        // CONSTRUCTORS

        /**
         * Creates a new {@link AutoIndentAction} instance.
         */
        public AutoIndentAction() {
            super("AUTO_INDENT");
        }

        // METHODS

        @Override
        public void actionPerformed(ActionEvent event) {
            JTextComponent editor = getTextComponent(event);
            if (editor != null) {
                TdlDocument document = (TdlDocument) editor.getDocument();

                int indentLevel = document.getIndentLevel(editor.getCaretPosition());
                StringBuilder sb = new StringBuilder();
                while (indentLevel-- > 0) {
                    sb.append(' ');
                }

                editor.replaceSelection("\n" + sb.toString());
            }
        }
    }

    // CONSTANTS

    /** The MIME type to use for editors that want to use this syntax kit. */
    public static final String MIME_TYPE = "text/tdl";

    private static final String KEYMAP_NAME = "tdlSynKM";

    // VARIABLES

    private final TdlHelper m_lexer;
    private final TdlStyleManager m_styleManager;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlSyntaxKit} instance for the given token source.
     * 
     * @param lexer
     *            the token source/lexer to use, cannot be <code>null</code>.
     */
    public TdlSyntaxKit(TdlHelper lexer) {
        m_lexer = lexer;
        m_styleManager = new TdlStyleManager();
    }

    // METHODS

    /**
     * Installs this editor kit.
     */
    public static void install() {
        JEditorPane.registerEditorKitForContentType(MIME_TYPE, TdlSyntaxKit.class.getCanonicalName(),
                TdlSyntaxKit.class.getClassLoader());
    }

    /**
     * Uninstalls this editor kit.
     */
    public static void uninstall() {
        JEditorPane.registerEditorKitForContentType(MIME_TYPE, null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewFactory getViewFactory() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View create(Element element) {
        return new TdlSyntaxView(element, m_styleManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document createDefaultDocument() {
        return new TdlDocument(m_lexer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void install(final JEditorPane editorPane) {
        super.install(editorPane);

        // Create the ruler...
        editorPane.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent event) {
                if (event.getChanged() == editorPane) {
                    boolean parentChanged = (event.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) != 0;
                    if (parentChanged) {
                        JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class,
                                editorPane);
                        if (scrollPane != null) {
                            scrollPane.setRowHeaderView(new TdlRulerView(editorPane));
                        }
                    }
                }
            }
        });

        // Set the default font...
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);

        editorPane.setFont(font);
        editorPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        Keymap child = addKeymap(KEYMAP_NAME, getKeymap(DEFAULT_KEYMAP));
        fillKeymap(child);

        editorPane.setKeymap(child);
    }

    /**
     * @param keymap
     *            the {@link Keymap} to fill, cannot be <code>null</code>.
     */
    private void fillKeymap(Keymap keymap) {
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke("ENTER"), new AutoIndentAction());
    }
}
