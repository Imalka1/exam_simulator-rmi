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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Imalka Gunawardana
 */
public class OnlineCellRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JLabel label;

    public OnlineCellRenderer() {
        label = new JLabel();
        label.setText("0");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Consolas", 0, 25));
        label.setForeground(Color.white);
        label.setBackground(new Color(58, 58, 58));
    }

    @Override
    public Component getTableCellRendererComponent(JTable tblManageTest, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        label = (JLabel) arg1;
        return label;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tblManageTest, Object arg1, boolean arg2, int arg3, int arg4) {
        label = (JLabel) arg1;
        return label;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }
}
