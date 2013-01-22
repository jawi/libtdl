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

import nl.lxtreme.libtdl.*;

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

    private final TdlStyleManager m_styleManager;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlSyntaxKit} instance.
     */
    public TdlSyntaxKit() {
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
        return new TdlDocument(TdlDialect.ADVANCED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void install(final JEditorPane editorPane) {
        final ToolTipManager tooltipMgr = ToolTipManager.sharedInstance();

        // Enable tool tips on the editor pane...
        tooltipMgr.registerComponent(editorPane);

        // Set the default font...
        editorPane.setFont(m_styleManager.getEditorFont());
        editorPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        Keymap child = addKeymap(KEYMAP_NAME, getKeymap(DEFAULT_KEYMAP));
        fillKeymap(child);

        editorPane.setKeymap(child);

        // Create the ruler dynamically when the editor is embedded inside a
        // scroll pane...
        editorPane.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent event) {
                if (event.getChanged() == editorPane) {
                    boolean parentChanged = (event.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) != 0;
                    if (parentChanged) {
                        Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, editorPane);
                        if (scrollPane != null) {
                            TdlRulerView rulerView = new TdlRulerView(editorPane);
                            // Enable tool tips on the ruler...
                            tooltipMgr.registerComponent(rulerView);

                            ((JScrollPane) scrollPane).setRowHeaderView(rulerView);
                        }
                    }
                }
            }
        });
    }

    /**
     * @param keymap
     *            the {@link Keymap} to fill, cannot be <code>null</code>.
     */
    private void fillKeymap(Keymap keymap) {
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke("ENTER"), new AutoIndentAction());
    }
}
