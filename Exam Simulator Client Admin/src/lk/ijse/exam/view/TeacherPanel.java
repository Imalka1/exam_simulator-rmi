/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.ijse.exam.controller.StudentPanelController;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.utilities.ButtonRenderer;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherPanel extends javax.swing.JPanel {

    private static TeacherPanel teacherPanel;
    private JPanel pnlLoad;
    private JPanel pnlButtons;
    private String teaId;

    /**
     * Creates new form TeacherPanel
     */
    private TeacherPanel() {
        initComponents();
        setSize(1920, 1010);
        setVisible(false);
        customizeStudentsTable();
    }

    public static TeacherPanel getInstance() {
        if (teacherPanel == null) {
            teacherPanel = new TeacherPanel();
        }
        return teacherPanel;
    }

    private void customizeTables() {

        class CustomizeTable extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component;
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("Consolas", 0, 23));
                component.setForeground(Color.white);
                component.setBackground(new Color(58, 58, 58));
                if (isSelected) {
                    component.setBackground(new Color(229, 148, 0));
                }
                return component;
            }
        }

        class CustomizeTableHeader extends DefaultTableCellRenderer {

            private Component component;

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setForeground(new Color(255, 153, 51));
                component.setBackground(new Color(81, 81, 81));
                component.setFont(new Font("Neuropol X Rg", 1, 13));
                return component;
            }
        }

        tblTeacher.setDefaultRenderer(Object.class, new CustomizeTable());
        tblTeacher.getTableHeader().setDefaultRenderer(new CustomizeTableHeader());
        tblTeacher.setRowHeight(55);
        tblTeacher.setShowGrid(true);
        tblTeacher.setOpaque(false);
        scrStudent.getViewport().setBackground(new Color(58, 58, 58));
        scrStudent.setBorder(null);

        tblSubject.setDefaultRenderer(Object.class, new CustomizeTable());
        tblSubject.getTableHeader().setDefaultRenderer(new CustomizeTableHeader());
        tblSubject.setRowHeight(55);
        tblSubject.setShowGrid(true);
        tblSubject.setOpaque(false);
        scrSubject.getViewport().setBackground(new Color(58, 58, 58));
        scrSubject.setBorder(null);
    }

    private void customizeStudentsTable() {
        customizeTables();
        tblTeacher.getColumn("Reserve").setCellRenderer(new ButtonRenderer("Reserve", tblTeacher, this));
        tblTeacher.getColumn("Reserve").setCellEditor(new ButtonRenderer("Reserve", tblTeacher, this));
        tblTeacher.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", tblTeacher, this));
        tblTeacher.getColumn("Update").setCellEditor(new ButtonRenderer("Update", tblTeacher, this));
        tblTeacher.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", tblTeacher, this));
        tblTeacher.getColumn("Delete").setCellEditor(new ButtonRenderer("Delete", tblTeacher, this));
        tblTeacher.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblTeacher.getColumnModel().getColumn(1).setPreferredWidth(15);
        tblTeacher.getColumnModel().getColumn(2).setPreferredWidth(400);
        tblTeacher.getColumnModel().getColumn(3).setPreferredWidth(400);
        tblTeacher.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblTeacher.getColumnModel().getColumn(5).setPreferredWidth(20);
        //loadTable();
    }

    private void loadTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblTeacher.getModel();
            List<StudentDTO> allStudents = StudentPanelController.getAllStudents();
            dtm.setRowCount(0);
            for (StudentDTO allStudent : allStudents) {
                Object ob[] = {new ButtonRenderer("Reserve", tblTeacher, this).getCellEditorValue(), "S" + allStudent.getStid(), allStudent.getName(), allStudent.getEmail(), allStudent.getBatchName(), allStudent.getSemName(), new ButtonRenderer("Update", tblTeacher, this).getCellEditorValue(), new ButtonRenderer("Delete", tblTeacher, this).getCellEditorValue()};
                dtm.addRow(ob);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTableReservation() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblTeacher.getModel();
            Map<String, List<String>> reservations = StudentPanelController.getAdminReservation().getReservations();
            Color color = new Color(255, 51, 0);
            for (int i = 0; i < tblTeacher.getRowCount(); i++) {
                JButton btn = (JButton) tblTeacher.getValueAt(i, 0);
                if (btn.getForeground().getRGB() != color.getRGB()) {
                    JButton buttonRes = (JButton) tblTeacher.getValueAt(i, 0);
                    buttonRes.setText("Reserve");
                    buttonRes.setEnabled(true);
                    buttonRes.setForeground(new Color(255, 165, 76));
                    tblTeacher.setValueAt(buttonRes, i, 0);
                }
            }
            for (String reservation : reservations.get("Teacher")) {
                for (int i = 0; i < tblTeacher.getRowCount(); i++) {
                    JButton btn = (JButton) tblTeacher.getValueAt(i, 0);
                    if (btn.getForeground().getRGB() != color.getRGB()) {
                        if (tblTeacher.getValueAt(i, 1).equals(reservation)) {
                            JButton buttonRes = (JButton) tblTeacher.getValueAt(i, 0);
                            buttonRes.setText("Reserved");
                            buttonRes.setEnabled(false);
                            buttonRes.setForeground(new Color(204, 204, 204));
                            tblTeacher.setValueAt(buttonRes, i, 0);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTextToFields(int val) {
        if (val == -1) {
            txtName.setText("");
            txtEmail.setText("");
            cmbBatch.setSelectedItem("");
            cmbSemester.setSelectedItem("");
        } else {
            txtName.setText(tblTeacher.getValueAt(val, 2).toString());
            txtEmail.setText(tblTeacher.getValueAt(val, 3).toString());
            cmbBatch.setSelectedItem(tblTeacher.getValueAt(val, 4).toString());
            cmbSemester.setSelectedItem(tblTeacher.getValueAt(val, 5).toString());
        }
    }

    public void setPnlProperties(JPanel pnlLoad, JPanel pnlButtons) {
        this.pnlLoad = pnlLoad;
        this.pnlButtons = pnlButtons;
    }

    public void setTeacherId(String teaId) {
        this.teaId = teaId;
    }

    public String getTeacherId() {
        return teaId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBackPage = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        cmbSemester = new javax.swing.JComboBox<>();
        cmbBatch = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        scrStudent = new javax.swing.JScrollPane();
        tblTeacher = new javax.swing.JTable();
        scrSubject = new javax.swing.JScrollPane();
        tblSubject = new javax.swing.JTable();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setLayout(null);

        btnBackPage.setFont(new java.awt.Font("Berlin Sans FB", 0, 30)); // NOI18N
        btnBackPage.setForeground(new java.awt.Color(204, 204, 204));
        btnBackPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/Left_96px.png"))); // NOI18N
        btnBackPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackPageMouseClicked(evt);
            }
        });
        add(btnBackPage);
        btnBackPage.setBounds(20, 20, 250, 70);

        lblDivision.setFont(new java.awt.Font("Neuropol X Rg", 0, 60)); // NOI18N
        lblDivision.setForeground(new java.awt.Color(255, 102, 51));
        lblDivision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivision.setText("Manage Teachers");
        add(lblDivision);
        lblDivision.setBounds(0, 20, 1920, 61);

        pnlData.setOpaque(false);
        pnlData.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");
        pnlData.add(jLabel1);
        jLabel1.setBounds(30, 30, 80, 35);

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email");
        pnlData.add(jLabel2);
        jLabel2.setBounds(30, 90, 90, 35);

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        pnlData.add(jLabel3);
        jLabel3.setBounds(610, 30, 130, 35);

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Re-enter Password");
        pnlData.add(jLabel4);
        jLabel4.setBounds(610, 90, 260, 35);

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Subject");
        pnlData.add(jLabel5);
        jLabel5.setBounds(1410, 90, 120, 35);

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Semester");
        pnlData.add(jLabel6);
        jLabel6.setBounds(1410, 30, 140, 35);

        txtEmail.setBackground(new java.awt.Color(63, 63, 63));
        txtEmail.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        pnlData.add(txtEmail);
        txtEmail.setBounds(130, 90, 390, 35);

        txtName.setBackground(new java.awt.Color(63, 63, 63));
        txtName.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        pnlData.add(txtName);
        txtName.setBounds(130, 30, 390, 35);

        cmbSemester.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pnlData.add(cmbSemester);
        cmbSemester.setBounds(1550, 30, 340, 35);

        cmbBatch.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pnlData.add(cmbBatch);
        cmbBatch.setBounds(1550, 90, 340, 35);

        jPasswordField1.setBackground(new java.awt.Color(63, 63, 63));
        jPasswordField1.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        pnlData.add(jPasswordField1);
        jPasswordField1.setBounds(870, 90, 440, 35);

        jPasswordField2.setBackground(new java.awt.Color(63, 63, 63));
        jPasswordField2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        pnlData.add(jPasswordField2);
        jPasswordField2.setBounds(870, 30, 440, 35);

        add(pnlData);
        pnlData.setBounds(0, 140, 1920, 150);

        tblTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reserve", "Teacher ID", "Name", "Email", "Update", "Delete"
            }
        ));
        scrStudent.setViewportView(tblTeacher);

        add(scrStudent);
        scrStudent.setBounds(30, 370, 1860, 330);

        tblSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Semester", "Subject"
            }
        ));
        scrSubject.setViewportView(tblSubject);

        add(scrSubject);
        scrSubject.setBounds(30, 760, 1860, 220);

        lblDark.setBackground(new Color(0,0,0,170));
        lblDark.setOpaque(true);
        add(lblDark);
        lblDark.setBounds(0, 0, 1920, 1010);

        lblImage.setBackground(new java.awt.Color(255, 255, 255));
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/macos_sierra_2-wallpaper-1920x1080.jpg"))); // NOI18N
        add(lblImage);
        lblImage.setBounds(0, -70, 1920, 1080);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackPageMouseClicked
        pnlLoad.setVisible(false);
        pnlButtons.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackPageMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JComboBox<String> cmbBatch;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel pnlData;
    private javax.swing.JScrollPane scrStudent;
    private javax.swing.JScrollPane scrSubject;
    private javax.swing.JTable tblSubject;
    private javax.swing.JTable tblTeacher;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
