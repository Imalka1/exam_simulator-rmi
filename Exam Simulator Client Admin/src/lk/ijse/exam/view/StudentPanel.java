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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lk.ijse.exam.controller.StudentPanelController;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.utilities.ButtonRenderer;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentPanel extends javax.swing.JPanel {

    private static StudentPanel studentPanel;
    private JasperReport jasperReport;
    private JPanel pnlLoad;
    private JPanel pnlButtons;
    private String studId;
    private String teaId;
    private int rowNumber = -1;

    /**
     * Creates new form StudentPanel
     */
    private StudentPanel() {
        initComponents();
        setSize(1920, 1010);
        setVisible(false);
        loadJasper();
        customizeStudentsTable();
        editorCombos();
        customizeButtons();
        pnlReport.setVisible(false);
    }

    public static StudentPanel getInstance() {
        if (studentPanel == null) {
            studentPanel = new StudentPanel();
        }
        return studentPanel;
    }

    private void loadJasper() {
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(StudentPanel.class.getResourceAsStream("/lk/ijse/exam/jasper/reports/StudentData2.jasper"));
        } catch (JRException ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Customize Buttons*/
    private void customizeButtons(){
        btnAdd.setOpaque(true);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBackground(new Color(58, 58, 58));
        btnAdd.setForeground(new Color(255, 165, 76));
        btnPrint.setOpaque(true);
        btnPrint.setContentAreaFilled(false);
        btnPrint.setBackground(new Color(58, 58, 58));
        btnPrint.setForeground(new Color(255, 165, 76));
    }

    /*Customize Combo boxes*/
    private void editorCombos() {
        loadData();
        cmbBatch.setEditable(true);
        cmbBatch.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbBatch.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbBatch.getEditor().getEditorComponent()).setOpaque(true);
        cmbBatch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!cmbBatch.getSelectedItem().equals("Select Semester")) {

                    }
                    e.consume();
                }
            }
        });

        cmbSemester.setEditable(true);
        cmbSemester.getEditor().getEditorComponent().setBackground(new Color(58, 58, 58));
        cmbSemester.getEditor().getEditorComponent().setForeground(new Color(204, 204, 204));
        ((JTextField) cmbSemester.getEditor().getEditorComponent()).setOpaque(true);
        cmbSemester.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!cmbSemester.getSelectedItem().equals("Select Semester")) {

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
                    if (!cmbCategory.getSelectedItem().equals("Select Semester")) {

                    }
                    e.consume();
                }
            }
        });
        AutoCompleteDecorator.decorate(cmbBatch);
        AutoCompleteDecorator.decorate(cmbSemester);
    }

    /*Load Data to combo boxes*/
    private void loadData() {
        try {
            List<String> batches = StudentPanelController.getBatches();
            cmbBatch.removeAllItems();
            for (String batch : batches) {
                cmbBatch.addItem(batch);
            }
            cmbBatch.setSelectedIndex(cmbBatch.getItemCount() - 1);
            List<String> semesters = StudentPanelController.getSemesters();
            cmbSemester.removeAllItems();
            for (String semester : semesters) {
                cmbSemester.addItem(semester);
            }
            List<String> categories = StudentPanelController.getCategories();
            cmbCategory.removeAllItems();
            for (String category : categories) {
                cmbCategory.addItem(category);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Customize Tables and Headers*/
    private void customizeTables() {

        class CustomizeTable extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component;
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("Consolas", 0, 20));
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

        tblStudent.setDefaultRenderer(Object.class, new CustomizeTable());
        tblStudent.getTableHeader().setDefaultRenderer(new CustomizeTableHeader());
        tblStudent.setRowHeight(45);
        tblStudent.setShowGrid(true);
        tblStudent.setOpaque(false);
        scrStudent.getViewport().setBackground(new Color(58, 58, 58));
        scrStudent.setBorder(null);
    }

    /*Customize Tables Columns with renderes and editor*/
    private void customizeStudentsTable() {

        customizeTables();

        tblStudent.getColumn("Reserve").setCellRenderer(new ButtonRenderer("Reserve", tblStudent, this));
        tblStudent.getColumn("Reserve").setCellEditor(new ButtonRenderer("Reserve", tblStudent, this));
        tblStudent.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", tblStudent, this));
        tblStudent.getColumn("Update").setCellEditor(new ButtonRenderer("Update", tblStudent, this));
        tblStudent.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", tblStudent, this));
        tblStudent.getColumn("Delete").setCellEditor(new ButtonRenderer("Delete", tblStudent, this));
        tblStudent.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblStudent.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblStudent.getColumnModel().getColumn(2).setPreferredWidth(340);
        tblStudent.getColumnModel().getColumn(3).setPreferredWidth(340);
        tblStudent.getColumnModel().getColumn(4).setPreferredWidth(5);
        tblStudent.getColumnModel().getColumn(5).setPreferredWidth(15);
        tblStudent.getColumnModel().getColumn(6).setPreferredWidth(25);
        tblStudent.getColumnModel().getColumn(7).setPreferredWidth(5);
        tblStudent.getColumnModel().getColumn(8).setPreferredWidth(5);
        loadTable();
    }

    /*Load Table Data*/
    private void loadTable() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblStudent.getModel();
            List<StudentDTO> allStudents = StudentPanelController.getAllStudents();
            dtm.setRowCount(0);
            tblStudent.removeEditor();
            tblStudent.getColumn("Reserve").setCellRenderer(new ButtonRenderer("Reserve", tblStudent, this));
            tblStudent.getColumn("Reserve").setCellEditor(new ButtonRenderer("Reserve", tblStudent, this));
            tblStudent.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", tblStudent, this));
            tblStudent.getColumn("Update").setCellEditor(new ButtonRenderer("Update", tblStudent, this));
            tblStudent.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", tblStudent, this));
            tblStudent.getColumn("Delete").setCellEditor(new ButtonRenderer("Delete", tblStudent, this));
            for (StudentDTO allStudent : allStudents) {
                Object ob[] = {new ButtonRenderer("Reserve", tblStudent, this).getCellEditorValue(), allStudent.getRid(), allStudent.getName(), allStudent.getEmail(), allStudent.getBatchName(), allStudent.getSemName(), allStudent.getCatName(), new ButtonRenderer("Update", tblStudent, this).getCellEditorValue(), new ButtonRenderer("Delete", tblStudent, this).getCellEditorValue()};
                dtm.addRow(ob);
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Manage textfields*/
    public void setTextToFields(int val) {
        if (val == -1) {
            txtStudentId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            cmbBatch.setSelectedIndex(cmbBatch.getItemCount() - 1);
            cmbSemester.setSelectedIndex(0);
            cmbCategory.setSelectedIndex(0);
        } else {
            txtStudentId.setText(tblStudent.getValueAt(val, 1).toString());
            txtName.setText(tblStudent.getValueAt(val, 2).toString());
            txtEmail.setText(tblStudent.getValueAt(val, 3).toString());
            cmbBatch.setSelectedItem(tblStudent.getValueAt(val, 4).toString());
            cmbSemester.setSelectedItem(tblStudent.getValueAt(val, 5).toString());
            cmbCategory.setSelectedItem(tblStudent.getValueAt(val, 6).toString());
        }
    }
    
    public void updateTableReservation() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblStudent.getModel();
            Map<String, List<String>> reservations = StudentPanelController.getAdminReservation().getReservations();
            Color color = new Color(255, 51, 0);
            for (int i = 0; i < tblStudent.getRowCount(); i++) {
                JButton btn = (JButton) tblStudent.getValueAt(i, 0);
                if (rowNumber != i) {
                    JButton buttonRes = (JButton) tblStudent.getValueAt(i, 0);
                    buttonRes.setText("Reserve");
                    buttonRes.setEnabled(true);
                    buttonRes.setForeground(new Color(255, 165, 76));
                    tblStudent.setValueAt(buttonRes, i, 0);
                    JButton buttonUpd = (JButton) tblStudent.getValueAt(i, 7);
                    JButton buttonDel = (JButton) tblStudent.getValueAt(i, 8);
                    buttonUpd.setEnabled(false);
                    buttonUpd.setForeground(new Color(204, 204, 204));
                    dtm.setValueAt(buttonUpd, i, 7);
                    buttonDel.setEnabled(false);
                    buttonDel.setForeground(new Color(204, 204, 204));
                    dtm.setValueAt(buttonDel, i, 8);
                } else {
                    JButton buttonRes = (JButton) tblStudent.getValueAt(i, 0);
                    buttonRes.setText("Reserved");
                    buttonRes.setEnabled(true);
                    buttonRes.setForeground(new Color(255, 51, 0));
                    tblStudent.setValueAt(buttonRes, i, 0);
                    JButton buttonUpd = (JButton) tblStudent.getValueAt(i, 7);
                    JButton buttonDel = (JButton) tblStudent.getValueAt(i, 8);
                    buttonUpd.setEnabled(true);
                    buttonUpd.setForeground(new Color(255, 165, 76));
                    dtm.setValueAt(buttonUpd, i, 7);
                    buttonDel.setEnabled(true);
                    buttonDel.setForeground(new Color(255, 51, 0));
                    dtm.setValueAt(buttonDel, i, 8);
                }
            }
            for (String reservation : reservations.get("Student")) {
                for (int i = 0; i < tblStudent.getRowCount(); i++) {
                    JButton btn = (JButton) tblStudent.getValueAt(i, 0);
                    if (rowNumber != i) {
                        if (tblStudent.getValueAt(i, 1).equals(reservation)) {
                            JButton buttonRes = (JButton) tblStudent.getValueAt(i, 0);
                            buttonRes.setText("Reserved");
                            buttonRes.setEnabled(false);
                            buttonRes.setForeground(new Color(204, 204, 204));
                            tblStudent.setValueAt(buttonRes, i, 0);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTableRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setPnlProperties(JPanel pnlLoad, JPanel pnlButtons) {
        this.pnlLoad = pnlLoad;
        this.pnlButtons = pnlButtons;
    }

    public void setStudentId(String studId) {
        this.studId = studId;
    }

    public String getStudentId() {
        return studId;
    }

    public void updateTable() {
        loadTable();
        updateTableReservation();
    }

    public String[] getTextData() {
        String[] data = {txtName.getText(), txtEmail.getText(), txtStudentId.getText(), cmbBatch.getSelectedItem().toString(), cmbSemester.getSelectedItem().toString(), cmbCategory.getSelectedItem().toString()};
        return data;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDivision = new javax.swing.JLabel();
        btnBackPage = new javax.swing.JLabel();
        pnlStudent = new javax.swing.JPanel();
        pnlData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStudentId = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        cmbSemester = new javax.swing.JComboBox<>();
        cmbBatch = new javax.swing.JComboBox<>();
        txtPassword2 = new javax.swing.JPasswordField();
        txtPassword1 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        scrStudent = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlReport = new javax.swing.JPanel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setLayout(null);

        lblDivision.setFont(new java.awt.Font("Neuropol X Rg", 0, 60)); // NOI18N
        lblDivision.setForeground(new java.awt.Color(255, 102, 51));
        lblDivision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivision.setText("Manage Students");
        add(lblDivision);
        lblDivision.setBounds(0, 20, 1920, 61);

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

        pnlStudent.setOpaque(false);
        pnlStudent.setLayout(null);

        pnlData.setOpaque(false);
        pnlData.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student ID");
        pnlData.add(jLabel1);
        jLabel1.setBounds(30, 140, 160, 35);

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email");
        pnlData.add(jLabel2);
        jLabel2.setBounds(30, 80, 90, 35);

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        pnlData.add(jLabel3);
        jLabel3.setBounds(1190, 20, 130, 35);

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Re-enter Password");
        pnlData.add(jLabel4);
        jLabel4.setBounds(1190, 80, 260, 35);

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Batch");
        pnlData.add(jLabel5);
        jLabel5.setBounds(610, 20, 120, 35);

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Category");
        pnlData.add(jLabel6);
        jLabel6.setBounds(610, 140, 140, 35);

        txtStudentId.setBackground(new java.awt.Color(63, 63, 63));
        txtStudentId.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtStudentId.setForeground(new java.awt.Color(255, 255, 255));
        txtStudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIdActionPerformed(evt);
            }
        });
        pnlData.add(txtStudentId);
        txtStudentId.setBounds(190, 140, 330, 35);

        txtEmail.setBackground(new java.awt.Color(63, 63, 63));
        txtEmail.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        pnlData.add(txtEmail);
        txtEmail.setBounds(130, 80, 390, 35);

        txtName.setBackground(new java.awt.Color(63, 63, 63));
        txtName.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        pnlData.add(txtName);
        txtName.setBounds(130, 20, 390, 35);

        cmbCategory.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pnlData.add(cmbCategory);
        cmbCategory.setBounds(750, 140, 340, 35);

        cmbSemester.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pnlData.add(cmbSemester);
        cmbSemester.setBounds(750, 80, 340, 35);

        cmbBatch.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pnlData.add(cmbBatch);
        cmbBatch.setBounds(750, 20, 340, 35);

        txtPassword2.setBackground(new java.awt.Color(63, 63, 63));
        txtPassword2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtPassword2.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassword2ActionPerformed(evt);
            }
        });
        pnlData.add(txtPassword2);
        txtPassword2.setBounds(1450, 80, 440, 35);

        txtPassword1.setBackground(new java.awt.Color(63, 63, 63));
        txtPassword1.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtPassword1.setForeground(new java.awt.Color(255, 255, 255));
        pnlData.add(txtPassword1);
        txtPassword1.setBounds(1450, 20, 440, 35);

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Name");
        pnlData.add(jLabel8);
        jLabel8.setBounds(30, 20, 80, 35);

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Semester");
        pnlData.add(jLabel9);
        jLabel9.setBounds(610, 80, 140, 35);

        pnlStudent.add(pnlData);
        pnlData.setBounds(0, 10, 1920, 190);

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reserve", "Student ID", "Name", "Email", "Batch", "Semester", "Category", "Update", "Delete"
            }
        ));
        tblStudent.getTableHeader().setReorderingAllowed(false);
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        scrStudent.setViewportView(tblStudent);
        if (tblStudent.getColumnModel().getColumnCount() > 0) {
            tblStudent.getColumnModel().getColumn(0).setResizable(false);
            tblStudent.getColumnModel().getColumn(1).setResizable(false);
            tblStudent.getColumnModel().getColumn(2).setResizable(false);
            tblStudent.getColumnModel().getColumn(3).setResizable(false);
            tblStudent.getColumnModel().getColumn(4).setResizable(false);
            tblStudent.getColumnModel().getColumn(5).setResizable(false);
            tblStudent.getColumnModel().getColumn(6).setResizable(false);
            tblStudent.getColumnModel().getColumn(7).setResizable(false);
            tblStudent.getColumnModel().getColumn(8).setResizable(false);
        }

        pnlStudent.add(scrStudent);
        scrStudent.setBounds(30, 290, 1860, 580);

        btnAdd.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        btnAdd.setText("Add Student");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlStudent.add(btnAdd);
        btnAdd.setBounds(1320, 210, 230, 50);

        btnPrint.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        pnlStudent.add(btnPrint);
        btnPrint.setBounds(1590, 210, 230, 50);

        jLabel7.setBackground(new java.awt.Color(58, 58, 58));
        jLabel7.setOpaque(true);
        pnlStudent.add(jLabel7);
        jLabel7.setBounds(1320, 210, 230, 50);

        jLabel10.setBackground(new java.awt.Color(58, 58, 58));
        jLabel10.setOpaque(true);
        pnlStudent.add(jLabel10);
        jLabel10.setBounds(1590, 210, 230, 50);

        add(pnlStudent);
        pnlStudent.setBounds(0, 110, 1920, 900);

        pnlReport.setOpaque(false);
        pnlReport.setLayout(new java.awt.CardLayout());
        add(pnlReport);
        pnlReport.setBounds(0, 110, 1920, 900);

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
        if (!pnlReport.isVisible()) {
            pnlLoad.setVisible(false);
            pnlButtons.setVisible(true);
            this.setVisible(false);
//            try {
//                StudentPanelController.getAdminReservation().release("Student", getStudentId());
//            } catch (Exception ex) {
//                Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            pnlStudent.setVisible(true);
            pnlReport.setVisible(false);
        }
    }//GEN-LAST:event_btnBackPageMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtPassword2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassword2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassword2ActionPerformed

    private void txtStudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIdActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(txtName.getText());
            studentDTO.setPassword(txtPassword1.getText());
            studentDTO.setEmail(txtEmail.getText());
            studentDTO.setRid(txtStudentId.getText());
            studentDTO.setBatchName(cmbBatch.getSelectedItem().toString());
            studentDTO.setSemName(cmbSemester.getSelectedItem().toString());
            studentDTO.setCatName(cmbCategory.getSelectedItem().toString());
            StudentPanelController.saveStudent(studentDTO);
            for (int i = 0; i < tblStudent.getRowCount(); i++) {
                DefaultTableModel dtm = (DefaultTableModel) tblStudent.getModel();
                JButton button = (JButton) tblStudent.getValueAt(i, 0);
                if (button.getText().equals("Reserved")) {
                    studentPanel.setTableRowNumber(-1);
                    button.setText("Reserve");
                    button.setForeground(new Color(255, 165, 76));
                    JButton buttonUpd = (JButton) tblStudent.getValueAt(i, 7);
                    JButton buttonDel = (JButton) tblStudent.getValueAt(i, 8);
                    buttonUpd.setEnabled(false);
                    buttonUpd.setForeground(new Color(204, 204, 204));
                    dtm.setValueAt(buttonUpd, tblStudent.getSelectedRow(), 7);
                    buttonDel.setEnabled(false);
                    buttonDel.setForeground(new Color(204, 204, 204));
                    dtm.setValueAt(buttonDel, tblStudent.getSelectedRow(), 8);
                    studentPanel.setTextToFields(-1);
                    studentPanel.setStudentId("");
                    try {
                        StudentPanelController.getAdminReservation().release("Student", tblStudent.getValueAt(tblStudent.getSelectedRow(), 1).toString());
                    } catch (Exception ex) {
                        Logger.getLogger(ButtonRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            StudentPanelController.getAdminSubject().notifyObservers(1);
        } catch (Exception ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            boolean enable = false;
            for (int i = 0; i < tblStudent.getRowCount(); i++) {
                JButton btn = (JButton) tblStudent.getValueAt(i, 0);
                if (btn.isEnabled() && btn.getText().equals("Reserved")) {
                    enable = true;
                    break;
                } else {
                    enable = false;
                }
            }
            if (enable) {
                Map<String, Object> data = new HashMap<>();
                data.put("Registration ID", txtStudentId.getText());
                data.put("Name", txtName.getText());
                data.put("Batch", cmbBatch.getSelectedItem());
                data.put("Category", cmbCategory.getSelectedItem());
                data.put("Email", txtEmail.getText());
                // data.put("logo", ClassLoader.getSystemResourceAsStream("/lk/ijse/exam/asset/download.jpg"));
                JasperPrint filledReport = JasperFillManager.fillReport(jasperReport, data, new JREmptyDataSource());
                JRViewer jRViewer = new JRViewer(filledReport);
                jRViewer.setOpaque(true);
                jRViewer.setBackground(Color.WHITE);
                pnlStudent.setVisible(false);
                pnlReport.removeAll();
                pnlReport.add(jRViewer);
                pnlReport.setVisible(true);
            }
        } catch (JRException ex) {
            Logger.getLogger(StudentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked

    }//GEN-LAST:event_tblStudentMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cmbBatch;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlReport;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JScrollPane scrStudent;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword1;
    private javax.swing.JPasswordField txtPassword2;
    private javax.swing.JTextField txtStudentId;
    // End of variables declaration//GEN-END:variables

}
