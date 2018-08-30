/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.ijse.exam.controller.TestExamPanelController;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.observer.Observer;
import lk.ijse.exam.utilities.ButtonRenderer;
import lk.ijse.exam.utilities.ComboBoxRenderer;
import lk.ijse.exam.utilities.OnlineCellRenderer;
import lk.ijse.exam.utilities.SpinnerRenderer;

/**
 *
 * @author Imalka Gunawardana
 */
public class TestExamPanel extends javax.swing.JPanel {

    private static TestExamPanel testExamPanel;
    private JPanel pnlLoad;
    private JPanel pnlButtons;
    private int id;
    private String btnType;
    private String teacherName;

    /**
     * Creates new form TestExamPanel
     */
    public TestExamPanel() {
        initComponents();
        setSize(1920, 1010);
        setVisible(false);
    }

    private void customizeTables() {

        class CustomizeTable extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component;
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("Consolas", 0, 22));
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

        tblQuestion.setDefaultRenderer(Object.class, new CustomizeTable());
        tblQuestion.getTableHeader().setDefaultRenderer(new CustomizeTableHeader());
        tblQuestion.setRowHeight(55);
        tblQuestion.setShowGrid(true);
        tblQuestion.setOpaque(false);
        scrQuestion.getViewport().setBackground(new Color(58, 58, 58));
        scrQuestion.setBorder(null);
    }

    private void customizeSubjectsTable() {

        customizeTables();

        tblQuestion.getColumn("Question Paper").setCellRenderer(new ComboBoxRenderer(tblQuestion, id, btnType));
        tblQuestion.getColumn("Question Paper").setCellEditor(new ComboBoxRenderer(tblQuestion, id, btnType));
        tblQuestion.getColumn("Start/Stop").setCellRenderer(new ButtonRenderer(tblQuestion, btnType));
        tblQuestion.getColumn("Start/Stop").setCellEditor(new ButtonRenderer(tblQuestion, btnType));
        tblQuestion.getColumn("Count").setCellRenderer(new SpinnerRenderer("Count"));
        tblQuestion.getColumn("Count").setCellEditor(new SpinnerRenderer("Count"));
        tblQuestion.getColumn("Hours").setCellRenderer(new SpinnerRenderer("Hours"));
        tblQuestion.getColumn("Hours").setCellEditor(new SpinnerRenderer("Hours"));
        tblQuestion.getColumn("Minutes").setCellRenderer(new SpinnerRenderer("Minutes"));
        tblQuestion.getColumn("Minutes").setCellEditor(new SpinnerRenderer("Minutes"));
        tblQuestion.getColumn("Online").setCellRenderer(new OnlineCellRenderer());
        tblQuestion.getColumnModel().getColumn(0).setPreferredWidth(500);
        tblQuestion.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblQuestion.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblQuestion.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblQuestion.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblQuestion.getColumnModel().getColumn(5).setPreferredWidth(20);
        tblQuestion.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblQuestion.getColumnModel().getColumn(7).setPreferredWidth(200);
    }

    public void setPnlProperties(JPanel pnlLoad, JPanel pnlButtons,int id,String type) {
        this.id = id;
        try {
            teacherName = TestExamPanelController.getTeacherName(id);
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pnlLoad = pnlLoad;
        this.pnlButtons = pnlButtons;
        if (type.equals("Exam")) {
            lblDivision.setText("Exam Division");
            btnType = "Exam";
            customizeSubjectsTable();
            loadSubjects();
        } else if (type.equals("Test")) {
            lblDivision.setText("Test Division");
            btnType = "Test";
            customizeSubjectsTable();
            loadSubjects();
        }
    }

    private void loadSubjects() {
        try {
            DefaultTableModel modelQuestion = (DefaultTableModel) tblQuestion.getModel();
            modelQuestion.setRowCount(0);
            List<SemesterSubjectDTO> loadSubjects = TestExamPanelController.loadSubjects(id);
            if (loadSubjects != null) {
                for (SemesterSubjectDTO loadSubject : loadSubjects) {
                    SemesterSubjectDTO semesterSubjectDTO = new SemesterSubjectDTO();
                    semesterSubjectDTO.setTid(id);
                    semesterSubjectDTO.setSubjectName(loadSubject.getSubjectName());
                    ComboBoxRenderer rendererQuestion = new ComboBoxRenderer(tblQuestion, id, btnType);
                    ArrayList<String> data2 = new ArrayList<>();
                    data2.add("Random Paper(Single)");
                    data2.add("Random Paper(Multi)");
                    if (btnType.equals("Test")) {
                        PaperDTO paperDTO = new PaperDTO();
                        paperDTO.setTid(id);
                        paperDTO.setUrl(loadSubject.getSemesterName() + "/" + btnType + "/" + loadSubject.getSubjectName());
                        List<String> modelQuestionPapers = TestExamPanelController.getQuestionPapers(paperDTO);
                        for (String modelQuestionPaper : modelQuestionPapers) {
                            data2.add(modelQuestionPaper);
                        }
                    }
                    rendererQuestion.setData(data2);
                    Object[] ob1 = {loadSubject.getSubjectName(), loadSubject.getSemesterName(), rendererQuestion.getCellEditorValue(), new SpinnerRenderer("Count").getCellEditorValue(), new SpinnerRenderer("Hours").getCellEditorValue(), new SpinnerRenderer("Minutes").getCellEditorValue(), new OnlineCellRenderer().getCellEditorValue(), new ButtonRenderer(tblQuestion, btnType).getCellEditorValue()};
                    modelQuestion.addRow(ob1);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateOnlineStatus() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblQuestion.getModel();
            ArrayList<Observer> studentObservers = TestExamPanelController.getStudentSubject().getObservers();
            for (int i = 0; i < tblQuestion.getRowCount(); i++) {
                JLabel label = (JLabel) tblQuestion.getValueAt(i, 6);
                label.setText("0");
                model.setValueAt(label, i, 6);
                JButton button = (JButton) tblQuestion.getValueAt(i, 7);
                button.setEnabled(false);
                button.setForeground(new Color(204, 204, 204));
                model.setValueAt(button, i, 7);
            }
            for (Observer studentObserver : studentObservers) {
                String[] testsExamsStatus = studentObserver.testsExamsStatus();
                LinkedHashSet<String> set1 = new LinkedHashSet<>();
                for (String testsExamsStatu : testsExamsStatus) {
                    set1.add(testsExamsStatu);
                }
                for (int i = 0; i < tblQuestion.getRowCount(); i++) {
                    String[] tableElements = new String[5];
                    tableElements[0] = btnType;
                    tableElements[1] = tblQuestion.getValueAt(i, 1).toString();
                    tableElements[2] = teacherName;
                    tableElements[3] = tblQuestion.getValueAt(i, 0).toString();
                    tableElements[4] = "Register";
                    LinkedHashSet<String> set2 = new LinkedHashSet<>();
                    for (String tableElement : tableElements) {
                        set2.add(tableElement);
                    }
                    if (set1.equals(set2)) {
                        JLabel label = (JLabel) tblQuestion.getValueAt(i, 6);
                        label.setText((Integer.parseInt(label.getText()) + 1) + "");
                        model.setValueAt(label, i, 6);
                        JButton button = (JButton) tblQuestion.getValueAt(i, 7);
                        button.setEnabled(true);
                        button.setForeground(new Color(255, 165, 76));
                        model.setValueAt(button, i, 7);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrQuestion = new javax.swing.JScrollPane();
        tblQuestion = new javax.swing.JTable();
        btnBackPage = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setLayout(null);

        tblQuestion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Semester", "Question Paper", "Count", "Hours", "Minutes", "Online", "Start/Stop"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuestion.getTableHeader().setReorderingAllowed(false);
        tblQuestion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuestionMouseClicked(evt);
            }
        });
        scrQuestion.setViewportView(tblQuestion);
        if (tblQuestion.getColumnModel().getColumnCount() > 0) {
            tblQuestion.getColumnModel().getColumn(0).setResizable(false);
            tblQuestion.getColumnModel().getColumn(1).setResizable(false);
            tblQuestion.getColumnModel().getColumn(2).setResizable(false);
            tblQuestion.getColumnModel().getColumn(3).setResizable(false);
            tblQuestion.getColumnModel().getColumn(4).setResizable(false);
            tblQuestion.getColumnModel().getColumn(5).setResizable(false);
            tblQuestion.getColumnModel().getColumn(6).setResizable(false);
            tblQuestion.getColumnModel().getColumn(7).setResizable(false);
        }

        add(scrQuestion);
        scrQuestion.setBounds(30, 140, 1860, 670);

        btnBackPage.setFont(new java.awt.Font("Consolas", 0, 40)); // NOI18N
        btnBackPage.setForeground(new java.awt.Color(255, 153, 51));
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
        lblDivision.setText("Test Division");
        add(lblDivision);
        lblDivision.setBounds(490, 20, 940, 61);

        lblTitle.setFont(new java.awt.Font("Neuropol X Rg", 0, 45)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Broadcast with a click via Exams Online (24 X 7)");
        add(lblTitle);
        lblTitle.setBounds(0, 830, 1920, 150);

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

    private void tblQuestionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuestionMouseClicked

    }//GEN-LAST:event_tblQuestionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrQuestion;
    private javax.swing.JTable tblQuestion;
    // End of variables declaration//GEN-END:variables

}
