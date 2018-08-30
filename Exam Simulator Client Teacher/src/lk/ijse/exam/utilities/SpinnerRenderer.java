/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Imalka Gunawardana
 */
public class SpinnerRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JSpinner spnCount;
    private int value;

    public SpinnerRenderer(String type) {
        spnCount = new JSpinner();
        SpinnerModel spm = null;
        Component c = null;
        if (type.equals("Count")) {
            spm = new SpinnerNumberModel(1, 1, null, 1);
            spnCount.setModel(spm);
            c = spnCount.getEditor().getComponent(0);
            c.setForeground(Color.white);
        } else if (type.equals("Hours")) {
            spm = new SpinnerNumberModel(1, 0, 12, 1);
            spnCount.setModel(spm);
            c = spnCount.getEditor().getComponent(0);
            c.setForeground(new Color(204, 204, 204));
        } else if (type.equals("Minutes")) {
            spm = new SpinnerNumberModel(0, 0, 59, 1);
            spnCount.setModel(spm);
            c = spnCount.getEditor().getComponent(0);
            c.setForeground(new Color(204, 204, 204));
        }
        spnCount.setBorder(null);
        c.setBackground(new Color(58, 58, 58));
        c.setFont(new Font("Consolas", 0, 25));
    }

    @Override
    public Component getTableCellRendererComponent(JTable tblManageTest, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        if (arg2) {

        } else {

        }
        spnCount = (JSpinner) arg1;
        spnCount.setValue(spnCount.getValue());
        return spnCount;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tblManageTest, Object arg1, boolean arg2, int arg3, int arg4) {
        if (arg2) {

        } else {

        }
        spnCount = (JSpinner) arg1;
        spnCount.setValue(spnCount.getValue());
        return spnCount;
    }

    @Override
    public Object getCellEditorValue() {
        return spnCount;
    }
}
