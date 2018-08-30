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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import lk.ijse.exam.controller.StudentPanelController;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.view.StudentPanel;
import lk.ijse.exam.view.TeacherPanel;

/**
 *
 * @author Imalka Gunawardana
 */
public class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private JButton button;
    private int value;
    private JTable tblStudent;
    private boolean btnEnable;
    private StudentPanel studentPanel;
    private TeacherPanel teacherPanel;
    private DefaultTableModel dtm;

    public ButtonRenderer() {
    }

    public ButtonRenderer(String type, JTable tblStudent, JPanel panel) {
        button = new JButton();
        if (panel instanceof StudentPanel) {
            studentPanel = (StudentPanel) panel;
        } else if (panel instanceof TeacherPanel) {
            teacherPanel = (TeacherPanel) panel;
        }
        dtm = (DefaultTableModel) tblStudent.getModel();
        button.setText(type);
        button.setFont(new Font("Consolas", 0, 22));
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBackground(new Color(58, 58, 58));
        button.setForeground(new Color(255, 165, 76));
        if (button.getText().equals("Update")) {
            button.setEnabled(false);
            button.setForeground(new Color(204, 204, 204));
        }
        if (button.getText().equals("Delete")) {
            button.setEnabled(false);
            button.setForeground(new Color(204, 204, 204));
        }
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (tblStudent.getSelectedRow() > -1) {
//                    if (button.getText().equals("Update") && button.isEnabled()) {
//                        try {
//                            StudentDTO studentDTO = new StudentDTO();
//                            studentDTO.setOldBatchName(tblStudent.getValueAt(tblStudent.getSelectedRow(), 4).toString());
//                            studentDTO.setOldCatName(tblStudent.getValueAt(tblStudent.getSelectedRow(), 6).toString());
//                            studentDTO.setOldEmail(tblStudent.getValueAt(tblStudent.getSelectedRow(), 3).toString());
//                            studentDTO.setOldRid(tblStudent.getValueAt(tblStudent.getSelectedRow(), 1).toString());
//                            studentDTO.setOldSemName(tblStudent.getValueAt(tblStudent.getSelectedRow(), 5).toString());
//                            String[] textData = studentPanel.getTextData();
//                            studentDTO.setName(textData[0]);
//                            studentDTO.setEmail(textData[1]);
//                            studentDTO.setRid(textData[2]);
//                            studentDTO.setBatchName(textData[3]);
//                            studentDTO.setSemName(textData[4]);
//                            studentDTO.setCatName(textData[5]);
//                            StudentPanelController.updateStudent(studentDTO);
//                        } catch (Exception ex) {
//                            Logger.getLogger(ButtonRenderer.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
                    if (button.getText().equals("Reserved") && button.isEnabled()) {/*Reserved to Reserve*/
                        studentPanel.setTableRowNumber(-1);
                        button.setText("Reserve");
                        button.setForeground(new Color(255, 165, 76));
                        JButton buttonUpd = (JButton) tblStudent.getValueAt(tblStudent.getSelectedRow(), 7);
                        JButton buttonDel = (JButton) tblStudent.getValueAt(tblStudent.getSelectedRow(), 8);
                        buttonUpd.setEnabled(false);
                        buttonUpd.setForeground(new Color(204, 204, 204));
                        dtm.setValueAt(buttonUpd, tblStudent.getSelectedRow(), 7);
                        buttonDel.setEnabled(false);
                        buttonDel.setForeground(new Color(204, 204, 204));
                        dtm.setValueAt(buttonDel, tblStudent.getSelectedRow(), 8);
                        setPanels(-1, "");
                        try {
                            StudentPanelController.getAdminReservation().release("Student", tblStudent.getValueAt(tblStudent.getSelectedRow(), 1).toString());
                        } catch (Exception ex) {
                            Logger.getLogger(ButtonRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (button.getText().equals("Reserve")) {/*All to Reserve*/
                        for (int i = 0; i < tblStudent.getRowCount(); i++) {
                            JButton buttonRes = (JButton) tblStudent.getValueAt(i, 0);
                            JButton buttonUpd = (JButton) tblStudent.getValueAt(i, 7);
                            JButton buttonDel = (JButton) tblStudent.getValueAt(i, 8);
                            if (buttonRes.isEnabled() && buttonRes.getText().equals("Reserved")) {
                                buttonRes.setText("Reserve");
                                buttonRes.setForeground(new Color(255, 165, 76));
                                dtm.setValueAt(buttonRes, i, 0);
                                buttonUpd.setEnabled(false);
                                buttonUpd.setForeground(new Color(204, 204, 204));
                                dtm.setValueAt(buttonUpd, i, 7);
                                buttonDel.setEnabled(false);
                                buttonDel.setForeground(new Color(204, 204, 204));
                                dtm.setValueAt(buttonDel, i, 8);
                                setPanels(-1, "");
                                try {
                                    StudentPanelController.getAdminReservation().release("Student", tblStudent.getValueAt(i, 1).toString());
                                } catch (Exception ex) {
                                    Logger.getLogger(ButtonRenderer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        /*Reserve to Reserved*/
                        studentPanel.setTableRowNumber(tblStudent.getSelectedRow());
                        button.setText("Reserved");
                        button.setForeground(new Color(255, 51, 0));
                        button.setEnabled(true);
                        JButton buttonUpd = (JButton) tblStudent.getValueAt(tblStudent.getSelectedRow(), 7);
                        JButton buttonDel = (JButton) tblStudent.getValueAt(tblStudent.getSelectedRow(), 8);
                        buttonUpd.setEnabled(true);
                        buttonUpd.setForeground(new Color(255, 165, 76));
                        dtm.setValueAt(buttonUpd, tblStudent.getSelectedRow(), 7);
                        buttonDel.setEnabled(true);
                        buttonDel.setForeground(new Color(255, 51, 0));
                        dtm.setValueAt(buttonDel, tblStudent.getSelectedRow(), 8);
                        setPanels(tblStudent.getSelectedRow(), tblStudent.getValueAt(tblStudent.getSelectedRow(), 1).toString());
                        try {
                            StudentPanelController.getAdminReservation().reserve("Student", tblStudent.getValueAt(tblStudent.getSelectedRow(), 1).toString());
                        } catch (Exception ex) {
                            Logger.getLogger(ButtonRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }

    private void setPanels(int val, String text) {
        if (studentPanel != null) {
            studentPanel.setTextToFields(val);
            studentPanel.setStudentId(text);
        } else if (teacherPanel != null) {
            teacherPanel.setTextToFields(val);
            teacherPanel.setTeacherId(text);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable tblStudent, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        button = (JButton) arg1;
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tblStudent, Object arg1, boolean arg2, int arg3, int arg4) {
        button = (JButton) arg1;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button;
    }
}
