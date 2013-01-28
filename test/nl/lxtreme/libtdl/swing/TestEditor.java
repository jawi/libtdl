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

import javax.swing.*;

import nl.lxtreme.libtdl.*;

/**
 * 
 */
public class TestEditor extends JFrame {
    // CONSTRUCTORS

    /**
     * 
     */
    public TestEditor() {
        super("TDL editor");

        setPreferredSize(new Dimension(640, 480));

        TdlSyntaxKit syntaxKit = new TdlSyntaxKit();

        final JEditorPane editor = new JEditorPane();
        editor.setEditorKit(syntaxKit);

        // @formatter:off
        String text =
            "timer1 := 20ns\n" + 
            "a := mask = 0b11, value = 0x12\n" + 
            "\n" + 
            "stage 1: \n" + 
            "\tcapture a\n" + 
            "\twhen a start capture\n" + 
            "\telse on any goto 2\n" + 
            "\n" + 
            "c := 10000 ^ 01000\n" + 
            "edge1 := falling = 0x1\n" + 
            "\n" + 
            "// this is comment\n" + 
            "stage 2:\n" + 
            "  capture termD\n" + 
            "  when edge1 start timer1\n" + 
            "  else on any goto 1\n";
        // @formatter:on

        editor.setText(text);

        JComboBox dialect = new JComboBox(new TdlDialect[] { TdlDialect.BASIC, TdlDialect.ADVANCED });
        dialect.setSelectedIndex(1);
        dialect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();

                TdlDocument doc = (TdlDocument) editor.getDocument();
                doc.setDialect((TdlDialect) cb.getSelectedItem());

                doc.reparse();
            }
        });

        TdlProblemView problemView = new TdlProblemView(editor);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(new JScrollPane(editor));
        splitPane.setBottomComponent(new JScrollPane(problemView));
        splitPane.setDividerLocation(300);

        JPanel cp = new JPanel(new BorderLayout());
        cp.add(dialect, BorderLayout.NORTH);
        cp.add(splitPane, BorderLayout.CENTER);

        getContentPane().add(cp);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestEditor editor = new TestEditor();
                editor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                editor.pack();
                editor.setVisible(true);
            }
        });
    }
}
