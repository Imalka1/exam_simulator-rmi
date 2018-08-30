/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.ijse.exam.controller.TestExamPanelController;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Imalka Gunawardana
 */
public class TestExamPanel extends javax.swing.JPanel {

    private static TestExamPanel testPanel;
    private JPanel pnlLoad;
    private JPanel pnlButtons;
    private boolean generateType;
    private String type;
    private String[] dataForServer;
    private StudentView studentView;

    /**
     * Creates new form TestPanel
     */
    public TestExamPanel(StudentView studentView) {
        initComponents();
        setSize(1920, 1010);
        setVisible(false);
        dataForServer = new String[5];
        this.studentView = studentView;
        customizeSubjectsTable();
        editorCombos();
        customizeSpinners();
        QuestionsPanel questionTestPanel = QuestionsPanel.getInstance();
        questionTestPanel.setVisible(false);
    }

    private void customizeSubjectsTable() {
        class CustomizeTable extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component;
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("Consolas", 0, 25));
                component.setForeground(Color.white);
                component.setBackground(new Color(58, 58, 58));
                if (isSelected) {
                    component.setBackground(new Color(229, 148, 0));
                }
                return component;
            }
        }

        tblSubjects.setDefaultRenderer(Object.class, new CustomizeTable());
        tblSubjects.setRowHeight(70);
        tblSubjects.setShowHorizontalLines(true);
        tblSubjects.setShowGrid(false);
        tblSubjects.setTableHeader(null);
        tblSubjects.setFont(new Font("", 0, 20));
        tblSubjects.setOpaque(false);
        paneSubjects.getViewport().setBackground(new Color(58, 58, 58));
        paneSubjects.setBorder(null);
    }
    
    private void customizeSpinners(){
        Component c1 = spnCount.getEditor().getComponent(0);
        c1.setForeground(new Color(204, 204, 204));
        c1.setBackground(new Color(58, 58, 58));
        Component c2 = spnHour.getEditor().getComponent(0);
        c2.setForeground(new Color(204, 204, 204));
        c2.setBackground(new Color(58, 58, 58));
        Component c3 = spnMinute.getEditor().getComponent(0);
        c3.setForeground(new Color(204, 204, 204));
        c3.setBackground(new Color(58, 58, 58));
    }

    private void editorCombos() {
        cmbSemesters.setEnabled(false);
        cmbTeachers.setEnabled(false);
        cmbCategory.setEnabled(false);
        cmbSemesters.setEditable(true);
        cmbSemesters.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbSemesters.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbSemesters.getEditor().getEditorComponent()).setOpaque(true);
        cmbSemesters.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!cmbSemesters.getSelectedItem().equals("Select Semester")) {
                        lblNext2.setForeground(new Color(255, 165, 76));
                        cmbCategory.setEnabled(true);
                        lblCategory.setForeground(new Color(255, 165, 76));
                        dataForServer[1] = cmbSemesters.getSelectedItem().toString().trim();
                    }
                    e.consume();
                }
            }
        });

        cmbCategory.setEditable(true);
        cmbCategory.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbCategory.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbCategory.getEditor().getEditorComponent()).setOpaque(true);
        cmbCategory.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!cmbCategory.getSelectedItem().equals("Select Category")) {
                        lblNext3.setForeground(new Color(255, 165, 76));
                        cmbTeachers.setEnabled(true);
                        lblTeacher.setForeground(new Color(255, 165, 76));
                        loadTeachers(cmbCategory.getSelectedItem().toString());
                    }
                    e.consume();
                }
            }
        });

        cmbTeachers.setEditable(true);
        cmbTeachers.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbTeachers.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbTeachers.getEditor().getEditorComponent()).setOpaque(true);
        cmbTeachers.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!cmbTeachers.getSelectedItem().equals("Select Teacher")) {
                        tblSubjects.setEnabled(true);
                        try {
                            SemesterSubjectDTO semesterSubjectDTO = new SemesterSubjectDTO();
                            semesterSubjectDTO.setSemesterName(cmbSemesters.getSelectedItem().toString());
                            semesterSubjectDTO.setTeacherName(cmbTeachers.getSelectedItem().toString());
                            if (type.equals("Test")) {
                                List<SubjectDTO> loadSubjects = TestExamPanelController.loadSubjects(semesterSubjectDTO);
                                DefaultTableModel defaultTableModel = (DefaultTableModel) tblSubjects.getModel();
                                defaultTableModel.setRowCount(0);
                                if (loadSubjects != null) {
                                    for (SubjectDTO loadSubject : loadSubjects) {
                                        Object[] ob = {" " + loadSubject.getSubName()};
                                        defaultTableModel.addRow(ob);
                                    }
                                }
                            } else {
                                List<SubjectDTO> loadSubjects = TestExamPanelController.loadSubjects(semesterSubjectDTO);
                                DefaultTableModel defaultTableModel = (DefaultTableModel) tblSubjects.getModel();
                                defaultTableModel.setRowCount(0);
                                if (loadSubjects != null) {
                                    for (SubjectDTO loadSubject : loadSubjects) {
                                        Object[] ob = {" " + loadSubject.getSubName()};
                                        defaultTableModel.addRow(ob);
                                    }
                                }
                            }
                            dataForServer[2] = cmbTeachers.getSelectedItem().toString().trim();
                        } catch (Exception ex) {
                            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    e.consume();
                }
            }
        });

        cmbPapers.setEditable(true);
        cmbPapers.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbPapers.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbPapers.getEditor().getEditorComponent()).setOpaque(true);
        cmbPapers.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    if (!cmbCategory.getSelectedItem().equals("Select Category")) {
//                        lblNext3.setForeground(new Color(255, 165, 76));
//                        cmbTeachers.setEnabled(true);
//                        lblTeacher.setForeground(new Color(255, 165, 76));
//                        loadTeachers(cmbCategory.getSelectedItem().toString());
//                    }
                    e.consume();
                }
            }
        });
        AutoCompleteDecorator.decorate(cmbTeachers);
        AutoCompleteDecorator.decorate(cmbSemesters);
        AutoCompleteDecorator.decorate(cmbCategory);
        AutoCompleteDecorator.decorate(cmbPapers);
    }
    
    private void selectionEnability(boolean enability) {
        cmbSemesters.setEnabled(enability);
        cmbTeachers.setEnabled(enability);
        cmbCategory.setEnabled(enability);
        spnCount.setEnabled(enability);
        tblSubjects.setEnabled(enability);
    }

    public void setPnlProperties(JPanel pnlLoad, JPanel pnlButtons, String type) {
        this.pnlLoad = pnlLoad;
        this.pnlButtons = pnlButtons;
        this.type = type;
        if (type.equals("Exam")) {
            resetPanelToExam();
        } else if (type.equals("Test")) {
            resetPanelToTest();
        }
        dataForServer[0] = type;
    }

    public void loadSemesters() {
        try {
            List<String> loadSemesters = TestExamPanelController.getSemesters();
            cmbSemesters.removeAllItems();
            cmbSemesters.addItem("Select Semester");
            for (String loadSemester : loadSemesters) {
                cmbSemesters.addItem(loadSemester);
            }
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTeachers(String id) {
        try {
            List<TeacherDTO> loadTeachers = TestExamPanelController.loadTeachers(id);
            cmbTeachers.removeAllItems();
            cmbTeachers.addItem("Select Teacher");
            cmbTeachers.setSelectedItem("Select Teacher");
            if (loadTeachers != null) {
                for (TeacherDTO loadTeacher : loadTeachers) {
                    cmbTeachers.addItem(loadTeacher.getName());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadCategories(String id) {
        try {
            List<SubjectDTO> loadCategories = TestExamPanelController.loadCategories(id);
            cmbCategory.removeAllItems();
            cmbCategory.addItem("Select Category");
            cmbCategory.setSelectedItem("Select Category");
            if (loadCategories != null) {
                for (SubjectDTO loadCategory : loadCategories) {
                    cmbCategory.addItem(loadCategory.getSubName());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRegId(String id) {
        loadCategories(id);
    }

    public void setValueCombo(String val) {
        cmbSemesters.setSelectedItem(val);
        dataForServer[1] = val.trim();
    }

    private void resetPanelToExam() {
        setVisibility(false);
        cmbCategory.setLocation(260, cmbCategory.getY());
        lblCategory.setLocation(260, lblCategory.getY());
        lblNext3.setLocation(900, lblNext2.getY());
        cmbTeachers.setLocation(1200, cmbTeachers.getY());
        lblTeacher.setLocation(1200, lblTeacher.getY());
        lblLeft.setLocation(1100, 645);
        btnStart.setLocation(1300, 640);
        btnStart.setText("Start Exam");
        lblDivision.setText("Exam Division");
        cmbCategory.setSelectedItem("Select Category");
        cmbTeachers.setSelectedItem("Select Teacher");
        DefaultTableModel model = (DefaultTableModel) tblSubjects.getModel();
        model.setRowCount(0);
        cmbCategory.setEnabled(true);
        cmbTeachers.setEnabled(false);
    }

    private void resetPanelToTest() {
        setVisibility(true);
        cmbCategory.setLocation(930, cmbCategory.getY());
        lblCategory.setLocation(930, lblCategory.getY());
        lblNext3.setLocation(1380, lblNext2.getY());
        cmbTeachers.setLocation(1490, cmbTeachers.getY());
        lblTeacher.setLocation(1490, lblTeacher.getY());
        lblLeft.setLocation(1060, 750);
        btnStart.setLocation(1220, 750);
        btnStart.setText("Start Test");
        lblDivision.setText("Test Division");
        editorCombos();
        DefaultTableModel model = (DefaultTableModel) tblSubjects.getModel();
        model.setRowCount(0);
    }

    private void setVisibility(boolean visibility) {
        btnGenByStudent.setVisible(visibility);
        btnGenByTeacher.setVisible(visibility);
        lblCommon.setVisible(visibility);
        pnlPaperSelect.setVisible(visibility);
        cmbSemesters.setVisible(visibility);
        cmbPapers.setVisible(visibility);
        lblSemester.setVisible(visibility);
        lblNext2.setVisible(visibility);
    }

    public String[] testsExamsStatus() {
        return dataForServer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneSubjects = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        btnStart = new javax.swing.JLabel();
        cmbTeachers = new javax.swing.JComboBox<>();
        cmbSemesters = new javax.swing.JComboBox<>();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lblLeft = new javax.swing.JLabel();
        btnGenByTeacher = new javax.swing.JLabel();
        btnGenByStudent = new javax.swing.JLabel();
        btnBackPage = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblTeacher = new javax.swing.JLabel();
        lblSemester = new javax.swing.JLabel();
        lblNext3 = new javax.swing.JLabel();
        lblNext2 = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        pnlPaperSelect = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        spnHour = new javax.swing.JSpinner();
        spnMinute = new javax.swing.JSpinner();
        lblTime2 = new javax.swing.JLabel();
        spnCount = new javax.swing.JSpinner();
        lblQuestionsCount = new javax.swing.JLabel();
        lblNext4 = new javax.swing.JLabel();
        cmbPapers = new javax.swing.JComboBox<>();
        lblQuestionsPaper = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCommon = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        tblSubjects.setForeground(new java.awt.Color(58, 58, 58));
        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubjects.getTableHeader().setReorderingAllowed(false);
        tblSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectsMouseClicked(evt);
            }
        });
        paneSubjects.setViewportView(tblSubjects);
        if (tblSubjects.getColumnModel().getColumnCount() > 0) {
            tblSubjects.getColumnModel().getColumn(0).setResizable(false);
        }

        add(paneSubjects);
        paneSubjects.setBounds(50, 430, 930, 530);

        btnStart.setBackground(new Color(58,58,58));
        btnStart.setFont(new java.awt.Font("Neuropol X Rg", 0, 32)); // NOI18N
        btnStart.setForeground(new java.awt.Color(204, 204, 204));
        btnStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStart.setText("Start Test");
        btnStart.setOpaque(true);
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartMouseClicked(evt);
            }
        });
        add(btnStart);
        btnStart.setBounds(1220, 750, 490, 100);

        cmbTeachers.setBackground(new Color(58,58,58));
        cmbTeachers.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        cmbTeachers.setForeground(new java.awt.Color(204, 204, 204));
        cmbTeachers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Teacher" }));
        add(cmbTeachers);
        cmbTeachers.setBounds(1490, 190, 410, 40);

        cmbSemesters.setBackground(new Color(58,58,58));
        cmbSemesters.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        cmbSemesters.setForeground(new java.awt.Color(204, 204, 204));
        add(cmbSemesters);
        cmbSemesters.setBounds(520, 190, 310, 40);

        cmbCategory.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        cmbCategory.setForeground(new java.awt.Color(204, 204, 204));
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        add(cmbCategory);
        cmbCategory.setBounds(930, 190, 450, 40);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Neuropol X Rg", 0, 35)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Subjects");
        add(jLabel4);
        jLabel4.setBounds(50, 350, 930, 80);

        lblLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/Sort Right_96px.png"))); // NOI18N
        add(lblLeft);
        lblLeft.setBounds(1070, 750, 90, 100);

        btnGenByTeacher.setBackground(new Color(58,58,58));
        btnGenByTeacher.setFont(new java.awt.Font("Neuropol X Rg", 0, 22)); // NOI18N
        btnGenByTeacher.setForeground(new java.awt.Color(204, 204, 204));
        btnGenByTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGenByTeacher.setText("Generate By Teacher");
        btnGenByTeacher.setOpaque(true);
        btnGenByTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenByTeacherMouseClicked(evt);
            }
        });
        add(btnGenByTeacher);
        btnGenByTeacher.setBounds(20, 230, 400, 60);

        btnGenByStudent.setBackground(new Color(58,58,58));
        btnGenByStudent.setFont(new java.awt.Font("Neuropol X Rg", 0, 22)); // NOI18N
        btnGenByStudent.setForeground(new java.awt.Color(204, 204, 204));
        btnGenByStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGenByStudent.setText("Generate By Student");
        btnGenByStudent.setOpaque(true);
        btnGenByStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenByStudentMouseClicked(evt);
            }
        });
        add(btnGenByStudent);
        btnGenByStudent.setBounds(20, 130, 400, 60);

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

        lblCategory.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(204, 204, 204));
        lblCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategory.setText("Subject Category");
        add(lblCategory);
        lblCategory.setBounds(930, 140, 450, 50);

        lblTeacher.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblTeacher.setForeground(new java.awt.Color(204, 204, 204));
        lblTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeacher.setText("Teacher");
        add(lblTeacher);
        lblTeacher.setBounds(1490, 140, 410, 50);

        lblSemester.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblSemester.setForeground(new java.awt.Color(204, 204, 204));
        lblSemester.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSemester.setText("Semester");
        add(lblSemester);
        lblSemester.setBounds(520, 140, 310, 50);

        lblNext3.setBackground(new Color(0,0,0,70));
        lblNext3.setFont(new java.awt.Font("Neuropol", 0, 40)); // NOI18N
        lblNext3.setForeground(new java.awt.Color(204, 204, 204));
        lblNext3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNext3.setText("->");
        add(lblNext3);
        lblNext3.setBounds(1380, 190, 100, 40);

        lblNext2.setBackground(new Color(0,0,0,70));
        lblNext2.setFont(new java.awt.Font("Neuropol", 0, 40)); // NOI18N
        lblNext2.setForeground(new java.awt.Color(204, 204, 204));
        lblNext2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNext2.setText("->");
        add(lblNext2);
        lblNext2.setBounds(830, 190, 100, 40);

        lblDivision.setFont(new java.awt.Font("Neuropol X Rg", 0, 60)); // NOI18N
        lblDivision.setForeground(new java.awt.Color(255, 102, 51));
        lblDivision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivision.setText("Test Division");
        add(lblDivision);
        lblDivision.setBounds(980, 330, 940, 61);

        pnlPaperSelect.setBackground(new Color(58,58,58,150));
        pnlPaperSelect.setLayout(null);

        lblTime.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblTime.setForeground(new java.awt.Color(204, 204, 204));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("Time");
        pnlPaperSelect.add(lblTime);
        lblTime.setBounds(490, 170, 120, 50);

        lblTime1.setFont(new java.awt.Font("Neuropol X Rg", 0, 13)); // NOI18N
        lblTime1.setForeground(new java.awt.Color(204, 204, 204));
        lblTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime1.setText("Hours");
        pnlPaperSelect.add(lblTime1);
        lblTime1.setBounds(610, 140, 80, 30);

        spnHour.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        spnHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 1));
        pnlPaperSelect.add(spnHour);
        spnHour.setBounds(610, 170, 80, 50);

        spnMinute.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        spnMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        pnlPaperSelect.add(spnMinute);
        spnMinute.setBounds(720, 170, 80, 50);

        lblTime2.setFont(new java.awt.Font("Neuropol X Rg", 0, 13)); // NOI18N
        lblTime2.setForeground(new java.awt.Color(204, 204, 204));
        lblTime2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime2.setText("Minutes");
        pnlPaperSelect.add(lblTime2);
        lblTime2.setBounds(720, 140, 80, 30);

        spnCount.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        spnCount.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        pnlPaperSelect.add(spnCount);
        spnCount.setBounds(200, 170, 100, 50);

        lblQuestionsCount.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblQuestionsCount.setForeground(new java.awt.Color(204, 204, 204));
        lblQuestionsCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestionsCount.setText("Count");
        pnlPaperSelect.add(lblQuestionsCount);
        lblQuestionsCount.setBounds(70, 170, 130, 50);

        lblNext4.setBackground(new Color(0,0,0,70));
        lblNext4.setFont(new java.awt.Font("Neuropol", 0, 48)); // NOI18N
        lblNext4.setForeground(new java.awt.Color(204, 204, 204));
        lblNext4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNext4.setText("->");
        pnlPaperSelect.add(lblNext4);
        lblNext4.setBounds(350, 170, 120, 50);

        cmbPapers.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        cmbPapers.setForeground(new java.awt.Color(204, 204, 204));
        cmbPapers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Random Paper" }));
        pnlPaperSelect.add(cmbPapers);
        cmbPapers.setBounds(270, 70, 410, 40);

        lblQuestionsPaper.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblQuestionsPaper.setForeground(new java.awt.Color(204, 204, 204));
        lblQuestionsPaper.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestionsPaper.setText("Question Paper");
        pnlPaperSelect.add(lblQuestionsPaper);
        lblQuestionsPaper.setBounds(270, 20, 410, 50);

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(":");
        pnlPaperSelect.add(jLabel2);
        jLabel2.setBounds(690, 170, 30, 50);

        add(pnlPaperSelect);
        pnlPaperSelect.setBounds(990, 430, 920, 260);

        lblCommon.setFont(new java.awt.Font("Neuropol X Rg", 0, 90)); // NOI18N
        lblCommon.setForeground(new java.awt.Color(204, 204, 204));
        lblCommon.setText("}");
        add(lblCommon);
        lblCommon.setBounds(450, 135, 50, 140);

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

    private void tblSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseClicked
        tblSubjects.repaint();
        paneSubjects.repaint();
        btnStart.setForeground(new Color(255, 165, 76));
        dataForServer[3] = tblSubjects.getValueAt(tblSubjects.getSelectedRow(), 0).toString().trim();
        cmbPapers.removeAllItems();
        cmbPapers.addItem("Random Paper");
        if (!generateType) {
            try {
                PaperDTO paperDTO = new PaperDTO();
                paperDTO.setTeacherName(cmbTeachers.getSelectedItem().toString());
                paperDTO.setUrl(cmbSemesters.getSelectedItem().toString() + "/Test/" + tblSubjects.getValueAt(tblSubjects.getSelectedRow(), 0).toString().trim());
                List<String> modelQuestionPapers = TestExamPanelController.getModelQuestionPapers(paperDTO);
                for (String modelQuestionPaper : modelQuestionPapers) {
                    cmbPapers.addItem(modelQuestionPaper);
                }
                cmbPapers.setSelectedItem("Random Paper");
            } catch (Exception ex) {
                Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblSubjectsMouseClicked

    private void btnStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartMouseClicked
        if (tblSubjects.getSelectedRow() > -1) {
            QuestionsPanel questionTestPanel = QuestionsPanel.getInstance();
            this.setVisible(false);
            questionTestPanel.setVisible(true);
            questionTestPanel.setBackPage(this);
            questionTestPanel.resetAll();
            questionTestPanel.setGenerateType(generateType);
            dataForServer[4] = "Remove";
            studentView.setDataForServer(dataForServer);
            questionTestPanel.setDataForServer(dataForServer);
            questionTestPanel.setStudentView(studentView);
            questionTestPanel.clearTime();
            ResultsPanel.getInstance().setTestExamPanel(this, dataForServer);
            if (!generateType && type.equals("Test")) {
                PaperDTO paperDTO = new PaperDTO();
                questionTestPanel.pnlWaitingVisibility(generateType);
                paperDTO.setQuestionsCount(Integer.parseInt(spnCount.getValue().toString()));
                paperDTO.setSemester(cmbSemesters.getSelectedItem().toString());
                paperDTO.setTeacherName(cmbTeachers.getSelectedItem().toString());
                paperDTO.setUrl(cmbSemesters.getSelectedItem().toString() + "/Test/" + tblSubjects.getValueAt(tblSubjects.getSelectedRow(), 0).toString());
                paperDTO.setTime(spnHour.getValue() + ":" + spnMinute.getValue());
                questionTestPanel.startQuestions(paperDTO);
            } else if (generateType && type.equals("Test")) {
                questionTestPanel.pnlWaitingVisibility(true);
                try {
                    dataForServer[4] = "Register";
                    questionTestPanel.setDataForServer(dataForServer);
                    TestExamPanelController.getTeacherSubject().notifyObservers(1);
                } catch (Exception ex) {
                    Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (type.equals("Exam")) {
                questionTestPanel.pnlWaitingVisibility(true);
                try {
                    dataForServer[4] = "Register";
                    questionTestPanel.setDataForServer(dataForServer);
                    TestExamPanelController.getTeacherSubject().notifyObservers(1);
                } catch (Exception ex) {
                    Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnStartMouseClicked

    private void btnGenByStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenByStudentMouseClicked
        btnGenByStudent.setForeground(new Color(255, 165, 76));
        btnGenByTeacher.setForeground(new Color(204, 204, 204));
        lblNext2.setForeground(new Color(204, 204, 204));
        lblNext3.setForeground(new Color(204, 204, 204));
        lblCommon.setForeground(new Color(255, 165, 76));
        lblTeacher.setForeground(new Color(204, 204, 204));
        lblSemester.setForeground(new Color(255, 165, 76));
        lblCategory.setForeground(new Color(204, 204, 204));
        btnStart.setForeground(new Color(204, 204, 204));
        generateType = false;
        DefaultTableModel model = (DefaultTableModel) tblSubjects.getModel();
        model.setRowCount(0);
        selectionEnability(true);
        cmbSemesters.setEnabled(true);
        cmbTeachers.setEnabled(false);
        cmbCategory.setEnabled(false);
        tblSubjects.setEnabled(false);
    }//GEN-LAST:event_btnGenByStudentMouseClicked

    private void btnGenByTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenByTeacherMouseClicked
        btnGenByStudent.setForeground(new Color(204, 204, 204));
        lblNext2.setForeground(new Color(255, 165, 76));
        lblNext3.setForeground(new Color(204, 204, 204));
        btnGenByTeacher.setForeground(new Color(255, 165, 76));
        lblCommon.setForeground(new Color(255, 165, 76));
        lblSemester.setForeground(new Color(255, 165, 76));
        lblTeacher.setForeground(new Color(204, 204, 204));
        lblCategory.setForeground(new Color(255, 165, 76));
        btnStart.setForeground(new Color(255, 165, 76));
        tblSubjects.getSelectionModel().clearSelection();
        DefaultTableModel model = (DefaultTableModel) tblSubjects.getModel();
        model.setRowCount(0);
        generateType = true;
        selectionEnability(false);
        cmbCategory.setEnabled(true);
    }//GEN-LAST:event_btnGenByTeacherMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JLabel btnGenByStudent;
    private javax.swing.JLabel btnGenByTeacher;
    private javax.swing.JLabel btnStart;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbPapers;
    private javax.swing.JComboBox<String> cmbSemesters;
    private javax.swing.JComboBox<String> cmbTeachers;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCommon;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblNext2;
    private javax.swing.JLabel lblNext3;
    private javax.swing.JLabel lblNext4;
    private javax.swing.JLabel lblQuestionsCount;
    private javax.swing.JLabel lblQuestionsPaper;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTeacher;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JLabel lblTime2;
    private javax.swing.JScrollPane paneSubjects;
    private javax.swing.JPanel pnlPaperSelect;
    private javax.swing.JSpinner spnCount;
    private javax.swing.JSpinner spnHour;
    private javax.swing.JSpinner spnMinute;
    private javax.swing.JTable tblSubjects;
    // End of variables declaration//GEN-END:variables

}
