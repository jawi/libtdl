/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.ProblemReporter.Marker;

/**
 * Provides a view for listing problems.
 */
public class TdlProblemView extends JTable implements DocumentListener, PropertyChangeListener {
    // INNER TYPES

    static class TdlProblemViewModel extends DefaultTableModel {
        // METHODS

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 1) {
                return Integer.class;
            } else if (columnIndex == 2) {
                return ProblemReporter.Type.class;
            } else {
                return String.class;
            }
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) {
                return "Description";
            } else if (columnIndex == 1) {
                return "Line";
            } else if (columnIndex == 2) {
                return "Type";
            }
            return super.getColumnName(columnIndex);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        /**
         * Sets the row data.
         *
         * @param markers
         *            the list with markers that should be set as row data,
         *            cannot be <code>null</code>.
         */
        @SuppressWarnings("unchecked")
        public void setRowData(List<Marker> markers) {
            dataVector.clear();

            for (Marker marker : markers) {
                Vector<Object> row = new Vector<Object>();
                row.add(marker.getDescription());
                row.add(marker.getLine());
                row.add(marker.getType());

                dataVector.add(row);
            }

            fireTableDataChanged();
        }
    }

    final class ProblemClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            try {
                if (event.getClickCount() == 2) {
                    int line = (Integer) getValueAt(getSelectedRow(), 1);
                    int offset = -1, length = -1;

                    List<Marker> markers = m_document.getProblemMarkers(line);
                    for (Marker marker : markers) {
                        offset = marker.getOffset();
                        length = marker.getLength();
                        if ((offset >= 0) && (length >= 0)) {
                            break;
                        }
                    }
                    if ((offset >= 0) && (length >= 0)) {
                        m_editorPane.setCaretPosition(offset + length + 1);
                        m_editorPane.select(offset, offset + length + 1);
                        m_editorPane.requestFocusInWindow();
                        m_editorPane.scrollRectToVisible(m_editorPane.modelToView(offset));
                    }
                }
            } catch (BadLocationException exception) {
                // Too bad, consider this is a best-effort thing...
            }
        }
    }

    // VARIABLES

    private final JEditorPane m_editorPane;
    private volatile TdlDocument m_document;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlProblemView} instance.
     */
    public TdlProblemView(JEditorPane editorPane) {
        super(new TdlProblemViewModel());

        m_editorPane = editorPane;
        m_editorPane.addPropertyChangeListener("document", this);

        m_document = (TdlDocument) editorPane.getDocument();
        m_document.addDocumentListener(this);

        setAutoCreateRowSorter(true);
        setAutoResizeMode(AUTO_RESIZE_NEXT_COLUMN);

        addMouseListener(new ProblemClickListener());
    }

    // METHODS

    @Override
    public void addNotify() {
        super.addNotify();

        fillTable(m_document.getAllProblemMarkers());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        fillTable(m_document.getAllProblemMarkers());
    }

    @Override
    public TdlProblemViewModel getModel() {
        return (TdlProblemViewModel) super.getModel();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        fillTable(m_document.getAllProblemMarkers());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (m_document != null) {
            m_document.removeDocumentListener(this);
        }

        m_document = (TdlDocument) evt.getNewValue();
        m_document.addDocumentListener(this);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        fillTable(m_document.getAllProblemMarkers());
    }

    private void fillTable(List<Marker> markers) {
        TdlProblemViewModel model = getModel();
        model.setRowData(markers);
    }
}
