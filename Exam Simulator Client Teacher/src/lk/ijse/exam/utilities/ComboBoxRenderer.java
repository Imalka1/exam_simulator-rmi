/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import lk.ijse.exam.controller.TestExamPanelController;
import lk.ijse.exam.dto.PaperDTO;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Imalka Gunawardana
 */
public class ComboBoxRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JComboBox cmbPapers;
    private int tid;

    public ComboBoxRenderer(JTable tblQuestion, int id, String type) {
        cmbPapers = new JComboBox();
        cmbPapers.setFont(new Font("Consolas", 0, 23));
        AutoCompleteDecorator.decorate(cmbPapers);
        cmbPapers.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        ((JTextField) cmbPapers.getEditor().getEditorComponent()).setOpaque(true);
        cmbPapers.getEditor().getEditorComponent().setForeground(Color.WHITE);
        cmbPapers.setBorder(null);
        cmbPapers.setOpaque(true);
        cmbPapers.removeAllItems();

        cmbPapers.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JSpinner spinner = (JSpinner) tblQuestion.getValueAt(tblQuestion.getSelectedRow(), 3);
                    if (cmbPapers.getSelectedItem().equals("Random Paper(Single)") || cmbPapers.getSelectedItem().equals("Random Paper(Multi)")) {
                        spinner.setEnabled(true);
                        spinner.setValue(1);
                        tblQuestion.setVisible(false);
                        tblQuestion.setVisible(true);
                    } else {
                        try {
                            spinner.setEnabled(false);
                            PaperDTO paperDTO = new PaperDTO();
                            paperDTO.setTid(id);
                            paperDTO.setUrl(tblQuestion.getValueAt(tblQuestion.getSelectedRow(), 1).toString() + "/" + type + "/" + tblQuestion.getValueAt(tblQuestion.getSelectedRow(), 0).toString() + "/" + cmbPapers.getSelectedItem().toString());
                            int questionsCount = TestExamPanelController.getQuestionsCount(paperDTO);
                            spinner.setValue(questionsCount);
                            tblQuestion.setVisible(false);
                            tblQuestion.setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(ComboBoxRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    e.consume();
                }
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable tblManageTest, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        cmbPapers = (JComboBox) arg1;
        return cmbPapers;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tblManageTest, Object arg1, boolean arg2, int arg3, int arg4) {
        cmbPapers = (JComboBox) arg1;
        return cmbPapers;
    }

    public void setData(ArrayList<String> dataSet) {
        cmbPapers.removeAllItems();
        for (String data : dataSet) {
            cmbPapers.addItem(data);
        }
        cmbPapers.setSelectedItem("Random Paper(Single)");
    }

    @Override
    public Object getCellEditorValue() {
        return cmbPapers;
    }
}
