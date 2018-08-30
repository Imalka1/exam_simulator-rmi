/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import lk.ijse.exam.controller.StudentViewController;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.observer.Observer;
import lk.ijse.exam.utilities.CountdownTimerPanel;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentView extends javax.swing.JFrame implements Observer {

    private TestExamPanel testExamPanelForTest;
    private TestExamPanel testExamPanelForExam;
    private QuestionsPanel questionsPanel;
    private int id;
    private boolean btnTestEnability;
    private boolean btnExamEnability;
    private String[] dataForServer;
    private CountdownTimerPanel countdownTimerPanel;

    /**
     * Creates new form StudentView
     */
    public StudentView(int id) {
        initComponents();
        setSize(1920, 1080);
        getContentPane().setBackground(Color.white);
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
        this.id = id;
        customizeComponents();
        setDate();
        pnlLoad.setVisible(false);
        labelVisibleFalse();
        loadPanels();
        btnEnabilityFalse();
    }

    private void loadPanels() {
        testExamPanelForTest = new TestExamPanel(this);
        testExamPanelForTest.setPnlProperties(pnlLoad, pnlButtons,"Test");
        testExamPanelForExam = new TestExamPanel(this);
        testExamPanelForExam.setPnlProperties(pnlLoad, pnlButtons,"Exam");
        questionsPanel = QuestionsPanel.getInstance();
        pnlLoad.add(testExamPanelForTest);
        pnlLoad.add(testExamPanelForExam);
        pnlLoad.add(questionsPanel);
        pnlLoad.add(ResultsPanel.getInstance());
        loadStudentData();
        testExamPanelForTest.loadSemesters();
        try {
            UnicastRemoteObject.exportObject(this, 0);
            StudentViewController.getStudentSubject().registerObserver(this);
        } catch (RemoteException ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void customizeComponents() {
        Font font1 = new Font("Neuropol X Rg", 0, 35);
        Font font2 = new Font("Neuropol X Rg", 0, 30);
        Component[] components = pnlButtons.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel lbl = (JLabel) component;
                if (lbl.getName() != null && lbl.getName().equals("pnlButtons")) {
                    lbl.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            super.mouseEntered(e);
                            lbl.setForeground(new Color(255, 163, 25));
                            lbl.setFont(font1);
                            switch (lbl.getText()) {
                                case "Test": {
                                    labelVisibleFalse();
                                    lblTestDesc.setVisible(true);
                                }
                                break;
                                case "Exam": {
                                    labelVisibleFalse();
                                    lblExamDesc.setVisible(true);
                                }
                                break;
                                case "Quiz": {
                                    labelVisibleFalse();
                                    lblQuizDesc.setVisible(true);
                                }
                                break;
                                case "Notes": {
                                    labelVisibleFalse();
                                    lblNotesDesc.setVisible(true);
                                }
                                break;
                                case "Progress": {
                                    labelVisibleFalse();
                                    lblProgressDesc.setVisible(true);
                                }
                                break;
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            super.mouseExited(e);
                            lbl.setForeground(new Color(255, 153, 0));
                            lbl.setFont(font2);
                            labelVisibleFalse();
                        }
                    });
                }
            }
        }
    }
    
    private void labelVisibleFalse() {
        lblExamDesc.setVisible(false);
        lblTestDesc.setVisible(false);
        lblQuizDesc.setVisible(false);
        lblNotesDesc.setVisible(false);
        lblProgressDesc.setVisible(false);
    }

    private void setDate() {
        new Thread(() -> {
            while (true) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd   hh:mm:ss a");
                lblDate.setText(dateFormat.format(date));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
    public void setDataForServer(String[] dataForServer) {
        this.dataForServer = dataForServer;
    }

    public void setTimer(CountdownTimerPanel countdownTimerPanel) {
        this.countdownTimerPanel = countdownTimerPanel;
    }

    private void btnEnabilityFalse() {
        btnExamEnability = false;
        btnTestEnability = false;
    }

    private void loadStudentData() {
        try {
            StudentDTO studentDetails = StudentViewController.getStudentDetails(id);
            if (studentDetails != null) {
                lblName.setText(studentDetails.getName());
                lblEmail.setText(studentDetails.getEmail());
                lblBatch.setText(studentDetails.getBatchName());
                lblSemester.setText(studentDetails.getSemName());
                lblRegId.setText(studentDetails.getRid());
                testExamPanelForTest.setRegId(studentDetails.getRid());
                testExamPanelForExam.setRegId(studentDetails.getRid());
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlStudent = new javax.swing.JPanel();
        pnlTitleBar = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnTest = new javax.swing.JLabel();
        btnExam = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlLoad = new javax.swing.JPanel();
        pnlDetails = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblRegId = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblBatch = new javax.swing.JLabel();
        lblSemester = new javax.swing.JLabel();
        lblProgressDesc = new javax.swing.JLabel();
        lblNotesDesc = new javax.swing.JLabel();
        lblQuizDesc = new javax.swing.JLabel();
        lblExamDesc = new javax.swing.JLabel();
        lblTestDesc = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlStudent.setBackground(new java.awt.Color(255, 255, 255));
        pnlStudent.setLayout(null);

        pnlTitleBar.setBackground(new java.awt.Color(45, 45, 45));
        pnlTitleBar.setLayout(null);

        btnClose.setFont(new java.awt.Font("Neuropol X Rg", 1, 48)); // NOI18N
        btnClose.setForeground(new java.awt.Color(107, 107, 107));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setText("X");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });
        pnlTitleBar.add(btnClose);
        btnClose.setBounds(1820, 0, 100, 70);

        jLabel2.setFont(new java.awt.Font("Neuropol X Rg", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 107, 107));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        pnlTitleBar.add(jLabel2);
        jLabel2.setBounds(1740, 0, 80, 70);

        jLabel1.setFont(new java.awt.Font("Neuropol", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(107, 107, 107));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student's Session");
        pnlTitleBar.add(jLabel1);
        jLabel1.setBounds(0, 0, 290, 70);

        lblDate.setFont(new java.awt.Font("Berlin Sans FB", 0, 25)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 153, 0));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTitleBar.add(lblDate);
        lblDate.setBounds(1160, 0, 390, 70);

        btnLogout.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 153, 0));
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        pnlTitleBar.add(btnLogout);
        btnLogout.setBounds(1550, 0, 180, 70);

        pnlStudent.add(pnlTitleBar);
        pnlTitleBar.setBounds(0, 0, 1920, 70);

        pnlButtons.setBackground(new java.awt.Color(45, 45, 45));
        pnlButtons.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(255, 51, 0));
        jLabel9.setOpaque(true);
        pnlButtons.add(jLabel9);
        jLabel9.setBounds(370, 35, 10, 50);

        jLabel4.setBackground(new java.awt.Color(45, 45, 45));
        jLabel4.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Quiz");
        jLabel4.setName("pnlButtons"); // NOI18N
        jLabel4.setOpaque(true);
        pnlButtons.add(jLabel4);
        jLabel4.setBounds(0, 0, 370, 120);

        btnTest.setBackground(new java.awt.Color(45, 45, 45));
        btnTest.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnTest.setForeground(new java.awt.Color(255, 153, 0));
        btnTest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTest.setText("Test");
        btnTest.setName("pnlButtons"); // NOI18N
        btnTest.setOpaque(true);
        btnTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTestMouseClicked(evt);
            }
        });
        pnlButtons.add(btnTest);
        btnTest.setBounds(384, 0, 370, 120);

        btnExam.setBackground(new java.awt.Color(45, 45, 45));
        btnExam.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnExam.setForeground(new java.awt.Color(255, 153, 0));
        btnExam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExam.setText("Exam");
        btnExam.setName("pnlButtons"); // NOI18N
        btnExam.setOpaque(true);
        btnExam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExamMouseClicked(evt);
            }
        });
        pnlButtons.add(btnExam);
        btnExam.setBounds(768, 0, 384, 120);

        jLabel7.setBackground(new java.awt.Color(45, 45, 45));
        jLabel7.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Notes");
        jLabel7.setName("pnlButtons"); // NOI18N
        jLabel7.setOpaque(true);
        pnlButtons.add(jLabel7);
        jLabel7.setBounds(1166, 0, 370, 120);

        jLabel8.setBackground(new java.awt.Color(45, 45, 45));
        jLabel8.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Progress");
        jLabel8.setName("pnlButtons"); // NOI18N
        jLabel8.setOpaque(true);
        pnlButtons.add(jLabel8);
        jLabel8.setBounds(1550, 0, 370, 120);

        jLabel10.setBackground(new java.awt.Color(255, 51, 0));
        jLabel10.setOpaque(true);
        pnlButtons.add(jLabel10);
        jLabel10.setBounds(1540, 35, 10, 50);

        jLabel11.setBackground(new java.awt.Color(255, 51, 0));
        jLabel11.setOpaque(true);
        pnlButtons.add(jLabel11);
        jLabel11.setBounds(1150, 35, 10, 50);

        jLabel12.setBackground(new java.awt.Color(255, 51, 0));
        jLabel12.setOpaque(true);
        pnlButtons.add(jLabel12);
        jLabel12.setBounds(760, 35, 10, 50);

        pnlStudent.add(pnlButtons);
        pnlButtons.setBounds(0, 70, 1920, 120);

        pnlLoad.setBackground(new Color(0,0,0,0));
        pnlLoad.setLayout(null);
        pnlStudent.add(pnlLoad);
        pnlLoad.setBounds(-1, 70, 1920, 1010);

        pnlDetails.setBackground(new Color(0,0,0,0));
        pnlDetails.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 170, 45));
        jLabel3.setText("Name");
        pnlDetails.add(jLabel3);
        jLabel3.setBounds(40, 80, 190, 30);

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 170, 45));
        jLabel5.setText("Email");
        pnlDetails.add(jLabel5);
        jLabel5.setBounds(40, 150, 220, 30);

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 170, 45));
        jLabel6.setText("Batch");
        pnlDetails.add(jLabel6);
        jLabel6.setBounds(40, 220, 220, 30);

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 170, 45));
        jLabel13.setText("Semester");
        pnlDetails.add(jLabel13);
        jLabel13.setBounds(40, 290, 190, 30);

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 170, 45));
        jLabel14.setText("Registration ID");
        pnlDetails.add(jLabel14);
        jLabel14.setBounds(40, 360, 230, 30);

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 170, 45));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("->");
        pnlDetails.add(jLabel15);
        jLabel15.setBounds(310, 290, 48, 30);

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 170, 45));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("->");
        pnlDetails.add(jLabel16);
        jLabel16.setBounds(310, 360, 48, 30);

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 170, 45));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("->");
        pnlDetails.add(jLabel17);
        jLabel17.setBounds(310, 80, 48, 30);

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 170, 45));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("->");
        pnlDetails.add(jLabel18);
        jLabel18.setBounds(310, 150, 48, 30);

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 170, 45));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("->");
        pnlDetails.add(jLabel19);
        jLabel19.setBounds(310, 220, 48, 30);

        lblRegId.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblRegId.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblRegId);
        lblRegId.setBounds(440, 360, 770, 30);

        lblName.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblName);
        lblName.setBounds(440, 80, 770, 30);

        lblEmail.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblEmail);
        lblEmail.setBounds(440, 150, 770, 30);

        lblBatch.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblBatch.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblBatch);
        lblBatch.setBounds(440, 220, 770, 30);

        lblSemester.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblSemester.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblSemester);
        lblSemester.setBounds(440, 290, 770, 30);

        pnlStudent.add(pnlDetails);
        pnlDetails.setBounds(20, 290, 1340, 430);

        lblProgressDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblProgressDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblProgressDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgressDesc.setText("Determine your current progress via Progress Tab");
        pnlStudent.add(lblProgressDesc);
        lblProgressDesc.setBounds(1040, 190, 880, 70);

        lblNotesDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblNotesDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblNotesDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNotesDesc.setText("View Or Download Notes via Notes Tab");
        pnlStudent.add(lblNotesDesc);
        lblNotesDesc.setBounds(1170, 190, 700, 70);

        lblQuizDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblQuizDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblQuizDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuizDesc.setText("<html>Join Quiz Programmes via Quiz Tab<br/>[Questions will appear after it is generated by server]</html>");
        pnlStudent.add(lblQuizDesc);
        lblQuizDesc.setBounds(0, 190, 920, 100);

        lblExamDesc.setBackground(new Color(51, 51, 51));
        lblExamDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblExamDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblExamDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExamDesc.setText("<html>Do Your Exams via Exam Tab<br/>(An Exam will appear after it is generated by server)</html");
        lblExamDesc.setName("testCompo"); // NOI18N
        pnlStudent.add(lblExamDesc);
        lblExamDesc.setBounds(770, 190, 920, 100);

        lblTestDesc.setBackground(new Color(51, 51, 51,150));
        lblTestDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblTestDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblTestDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTestDesc.setText("Determine Your Knowledge Level via Test Tab");
        lblTestDesc.setName("testCompo"); // NOI18N
        pnlStudent.add(lblTestDesc);
        lblTestDesc.setBounds(380, 190, 860, 70);

        lblTitle.setFont(new java.awt.Font("Neuropol X Rg", 0, 50)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Enhance Your Knowledge via Exams Online (24 X 7)");
        pnlStudent.add(lblTitle);
        lblTitle.setBounds(0, 800, 1920, 150);

        lblDark.setBackground(new Color(0,0,0,170));
        lblDark.setOpaque(true);
        pnlStudent.add(lblDark);
        lblDark.setBounds(0, 190, 1920, 890);

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/macos_sierra_2-wallpaper-1920x1080.jpg"))); // NOI18N
        pnlStudent.add(lblImage);
        lblImage.setBounds(0, 0, 1920, 1080);

        getContentPane().add(pnlStudent);
        pnlStudent.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        try {
            questionsPanel.setVisible(false);
            if (dataForServer != null) {
                dataForServer[4] = "Unregister";
            }
            if (countdownTimerPanel != null) {
                countdownTimerPanel.stopTimer();
            }
            StudentViewController.getTeacherSubject().notifyObservers(1);
            StudentViewController.getStudentSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 22));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 20));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        new LoginForm();
        this.dispose();
        try {
            questionsPanel.setVisible(false);
            if (dataForServer != null) {
                dataForServer[4] = "Unregister";
            }
            if (countdownTimerPanel != null) {
                countdownTimerPanel.stopTimer();
            }
            StudentViewController.getTeacherSubject().notifyObservers(1);
            StudentViewController.getStudentSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTestMouseClicked
        btnEnabilityFalse();
        btnTestEnability = true;
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        testExamPanelForTest.setVisible(true);
        testExamPanelForTest.setValueCombo(lblSemester.getText());
    }//GEN-LAST:event_btnTestMouseClicked

    private void btnExamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseClicked
        btnEnabilityFalse();
        btnExamEnability = true;
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        testExamPanelForExam.setVisible(true);
        testExamPanelForExam.setValueCombo(lblSemester.getText());
    }//GEN-LAST:event_btnExamMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            questionsPanel.setVisible(false);
            if (dataForServer != null) {
                dataForServer[4] = "Unregister";
            }
            countdownTimerPanel.stopTimer();
            StudentViewController.getTeacherSubject().notifyObservers(1);
            StudentViewController.getStudentSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    @Override
    public void updateObservers(int value) throws Exception {
        testExamPanelForTest.loadSemesters();
    }

    @Override
    public String[] testsExamsStatus() throws Exception {
        if (btnTestEnability) {
            return testExamPanelForTest.testsExamsStatus();
        } else if (btnExamEnability) {
            return testExamPanelForExam.testsExamsStatus();
        }
        return null;
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new StudentView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnExam;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExamDesc;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNotesDesc;
    private javax.swing.JLabel lblProgressDesc;
    private javax.swing.JLabel lblQuizDesc;
    private javax.swing.JLabel lblRegId;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTestDesc;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlLoad;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JPanel pnlTitleBar;
    // End of variables declaration//GEN-END:variables

}
