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
import lk.ijse.exam.controller.TeacherViewController;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.observer.Observer;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherView extends javax.swing.JFrame implements Observer {

    private TestExamPanel testExamPanelForTest;
    private TestExamPanel testExamPanelForExam;
    private SubjectsPanel subjectsPanel;
    private int id;

    /**
     * Creates new form TeacherView
     */
    public TeacherView(int id) {
        initComponents();
        setSize(1920, 1080);
        getContentPane().setBackground(Color.white);
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
        this.id = id;
        pnlLoad.setVisible(false);
        customizeComponents();
        setDate();
        labelVisibleFalse();
        loadPanels();
    }

    private void loadPanels() {
        testExamPanelForTest = new TestExamPanel();
        testExamPanelForTest.setPnlProperties(pnlLoad, pnlButtons, id, "Test");
        testExamPanelForExam = new TestExamPanel();
        testExamPanelForExam.setPnlProperties(pnlLoad, pnlButtons, id, "Exam");
        SubjectsPanel.getInstance().setPnlProperties(pnlLoad, pnlButtons, id);
        pnlLoad.add(testExamPanelForTest);
        pnlLoad.add(testExamPanelForExam);
        pnlLoad.add(SubjectsPanel.getInstance());
        try {
            UnicastRemoteObject.exportObject(this, 0);
            TeacherViewController.getTeacherSubject().registerObserver(this);
            loadTeacherData();
        } catch (RemoteException ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    private void loadTeacherData() {
        try {
            TeacherDTO teacherDetails = TeacherViewController.getTeacherDetails(id);
            if (teacherDetails != null) {
                lblName.setText(teacherDetails.getName());
                lblEmail.setText(teacherDetails.getEmail());
            }
        } catch (Exception ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
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

        pnlTeacher = new javax.swing.JPanel();
        pnlTitleBar = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnTest = new javax.swing.JLabel();
        btnExam = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSubjects = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlLoad = new javax.swing.JPanel();
        pnlDetails = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblExamDesc = new javax.swing.JLabel();
        lblTestDesc = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlTeacher.setLayout(null);

        pnlTitleBar.setBackground(new java.awt.Color(45, 45, 45));
        pnlTitleBar.setLayout(null);

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

        jLabel1.setFont(new java.awt.Font("Neuropol", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(107, 107, 107));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Teacher's Session");
        pnlTitleBar.add(jLabel1);
        jLabel1.setBounds(0, 0, 290, 70);

        lblDate1.setFont(new java.awt.Font("Berlin Sans FB", 0, 25)); // NOI18N
        lblDate1.setForeground(new java.awt.Color(255, 153, 0));
        lblDate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTitleBar.add(lblDate1);
        lblDate1.setBounds(1160, 0, 390, 70);

        pnlTeacher.add(pnlTitleBar);
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

        btnSubjects.setBackground(new java.awt.Color(45, 45, 45));
        btnSubjects.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnSubjects.setForeground(new java.awt.Color(255, 153, 0));
        btnSubjects.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubjects.setText("Subjects");
        btnSubjects.setName("pnlButtons"); // NOI18N
        btnSubjects.setOpaque(true);
        btnSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubjectsMouseClicked(evt);
            }
        });
        pnlButtons.add(btnSubjects);
        btnSubjects.setBounds(1550, 0, 370, 120);

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

        pnlTeacher.add(pnlButtons);
        pnlButtons.setBounds(0, 70, 1920, 120);

        pnlLoad.setBackground(new Color(0,0,0,0));
        pnlLoad.setLayout(null);
        pnlTeacher.add(pnlLoad);
        pnlLoad.setBounds(-1, 70, 1920, 1010);

        pnlDetails.setBackground(new Color(0,0,0,0));
        pnlDetails.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 170, 45));
        jLabel3.setText("Name");
        pnlDetails.add(jLabel3);
        jLabel3.setBounds(40, 80, 190, 30);

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 170, 45));
        jLabel13.setText("Email");
        pnlDetails.add(jLabel13);
        jLabel13.setBounds(40, 150, 220, 30);

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 170, 45));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("->");
        pnlDetails.add(jLabel19);
        jLabel19.setBounds(310, 80, 48, 30);

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 25)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 170, 45));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("->");
        pnlDetails.add(jLabel20);
        jLabel20.setBounds(310, 150, 48, 30);

        lblName.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblName);
        lblName.setBounds(440, 80, 770, 30);

        lblEmail.setFont(new java.awt.Font("Consolas", 0, 25)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 170, 45));
        pnlDetails.add(lblEmail);
        lblEmail.setBounds(440, 150, 770, 30);

        pnlTeacher.add(pnlDetails);
        pnlDetails.setBounds(20, 400, 1340, 340);

        lblTitle.setFont(new java.awt.Font("Neuropol X Rg", 0, 45)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Broadcast Your Questionnaires via Exams Online (24 X 7)");
        pnlTeacher.add(lblTitle);
        lblTitle.setBounds(0, 800, 1920, 150);

        lblExamDesc.setBackground(new Color(51, 51, 51));
        lblExamDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblExamDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblExamDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExamDesc.setText("Broadcast Your Exam Papers via Exam Tab");
        lblExamDesc.setName("testCompo"); // NOI18N
        pnlTeacher.add(lblExamDesc);
        lblExamDesc.setBounds(770, 190, 920, 100);

        lblTestDesc.setBackground(new Color(51, 51, 51,150));
        lblTestDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblTestDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblTestDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTestDesc.setText("Broadcast Your Test Papers via Test Tab");
        lblTestDesc.setName("testCompo"); // NOI18N
        pnlTeacher.add(lblTestDesc);
        lblTestDesc.setBounds(380, 190, 860, 70);

        lblDark.setBackground(new Color(0,0,0,170));
        lblDark.setOpaque(true);
        pnlTeacher.add(lblDark);
        lblDark.setBounds(0, 190, 1920, 890);

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/macos_sierra_2-wallpaper-1920x1080.jpg"))); // NOI18N
        pnlTeacher.add(lblImage);
        lblImage.setBounds(0, 0, 1920, 1080);

        getContentPane().add(pnlTeacher);
        pnlTeacher.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        new LoginForm();
        this.dispose();
        try {
            TeacherViewController.getTeacherSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 22));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 20));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        try {
            TeacherViewController.getTeacherSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTestMouseClicked
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        testExamPanelForTest.setVisible(true);
    }//GEN-LAST:event_btnTestMouseClicked

    private void btnExamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExamMouseClicked
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        testExamPanelForExam.setVisible(true);
    }//GEN-LAST:event_btnExamMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            TeacherViewController.getTeacherSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(TeacherView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectsMouseClicked
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        SubjectsPanel.getInstance().setVisible(true);
    }//GEN-LAST:event_btnSubjectsMouseClicked

    @Override
    public void updateObservers(int value) throws Exception {
        testExamPanelForTest.updateOnlineStatus();
        testExamPanelForExam.updateOnlineStatus();
    }

    @Override
    public String[] testsExamsStatus() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TeacherView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnExam;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnSubjects;
    private javax.swing.JLabel btnTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExamDesc;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTestDesc;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlLoad;
    private javax.swing.JPanel pnlTeacher;
    private javax.swing.JPanel pnlTitleBar;
    // End of variables declaration//GEN-END:variables

}
