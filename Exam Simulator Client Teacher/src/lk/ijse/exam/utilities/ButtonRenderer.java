/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Imalka Gunawardana
 */
public class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JButton button;
    private int value;
    private JTable tblQuestion;
    private boolean btnEnable;

    public ButtonRenderer(JTable tblQuestion, String btnType) {
        button = new JButton();
        button.setText("Start " + btnType);
        this.tblQuestion = tblQuestion;
        button.setFont(new Font("Consolas", 0, 25));
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBackground(new Color(58, 58, 58));
        button.setForeground(new Color(255, 165, 76));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (btnEnable) {
                    button.setText("Start " + btnType);
                    button.setForeground(new Color(255, 165, 76));
                    btnEnable = false;
                } else {
                    button.setText("Stop " + btnType);
                    button.setForeground(new Color(255, 51, 0));
                    btnEnable = true;
                }
                Object valueAt = tblQuestion.getValueAt(tblQuestion.getSelectedRow(), 3);
                JSpinner js = (JSpinner) valueAt;
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable tblManageTest, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        button = (JButton) arg1;
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tblManageTest, Object arg1, boolean arg2, int arg3, int arg4) {
        button = (JButton) arg1;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button;
    }
}
