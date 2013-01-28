/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.swing;

import java.beans.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

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

    // VARIABLES

    private volatile TdlDocument m_document;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlProblemView} instance.
     */
    public TdlProblemView(JEditorPane editorPane) {
        super(new TdlProblemViewModel());

        editorPane.addPropertyChangeListener("document", this);

        m_document = (TdlDocument) editorPane.getDocument();
        m_document.addDocumentListener(this);

        setAutoCreateRowSorter(true);
        setAutoResizeMode(AUTO_RESIZE_NEXT_COLUMN);
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

    /**
     * @param markers
     */
    private void fillTable(List<Marker> markers) {
        TdlProblemViewModel model = getModel();
        model.setRowData(markers);
    }
}
