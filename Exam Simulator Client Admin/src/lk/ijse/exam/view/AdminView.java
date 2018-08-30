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
import lk.ijse.exam.controller.AdminViewController;
import lk.ijse.exam.observer.Observer;

/**
 *
 * @author Imalka Gunawardana
 */
public class AdminView extends javax.swing.JFrame implements Observer {

    private TeacherPanel teacherPanel;
    private StudentPanel studentPanel;

    /**
     * Creates new form AdminView
     */
    public AdminView() {
        initComponents();
        setSize(1920, 1080);
        getContentPane().setBackground(Color.white);
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
        setDate();
        loadPanels();
        pnlLoad.setVisible(false);
        panelVisibleFalse();
        customizeComponents();
    }

    private void loadPanels() {
        labelVisibleFalse();
        teacherPanel = TeacherPanel.getInstance();
        studentPanel = StudentPanel.getInstance();
        teacherPanel.setPnlProperties(pnlLoad, pnlButtons);
        studentPanel.setPnlProperties(pnlLoad, pnlButtons);
        pnlLoad.add(teacherPanel);
        pnlLoad.add(studentPanel);
        try {
            UnicastRemoteObject.exportObject(this, 0);
            AdminViewController.getAdminSubject().registerObserver(this);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Panels make invisible*/
    private void panelVisibleFalse() {
        teacherPanel.setVisible(false);
        studentPanel.setVisible(false);
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
                    Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
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
                                case "Teachers": {
                                    labelVisibleFalse();
                                    lblTeacherDesc.setVisible(true);
                                }
                                break;
                                case "Students": {
                                    labelVisibleFalse();
                                    lblStudentDesc.setVisible(true);
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

    /*Linked with customizeComponents*/
    private void labelVisibleFalse() {
        lblStudentDesc.setVisible(false);
        lblTeacherDesc.setVisible(false);
    }
    
    @Override
    public void updateObservers(int value) throws Exception {
        if (value == 0) {
            studentPanel.updateTableReservation();
            teacherPanel.updateTableReservation();
        } else if (value == 1) {
            studentPanel.updateTable();
            studentPanel.updateTableReservation();
        }
    }

    @Override
    public String[] testsExamsStatus() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAdmin = new javax.swing.JPanel();
        pnlTitleBar = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnTeacher = new javax.swing.JLabel();
        btnStudent = new javax.swing.JLabel();
        btnSubject = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlLoad = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblStudentDesc = new javax.swing.JLabel();
        lblTeacherDesc = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlAdmin.setBackground(new java.awt.Color(255, 255, 255));
        pnlAdmin.setLayout(null);

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
        jLabel1.setText("Admin's Session");
        pnlTitleBar.add(jLabel1);
        jLabel1.setBounds(0, 0, 270, 70);

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

        pnlAdmin.add(pnlTitleBar);
        pnlTitleBar.setBounds(0, 0, 1920, 70);

        pnlButtons.setBackground(new java.awt.Color(45, 45, 45));
        pnlButtons.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(255, 51, 0));
        jLabel9.setOpaque(true);
        pnlButtons.add(jLabel9);
        jLabel9.setBounds(480, 30, 10, 50);

        btnTeacher.setBackground(new java.awt.Color(45, 45, 45));
        btnTeacher.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnTeacher.setForeground(new java.awt.Color(255, 153, 0));
        btnTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTeacher.setText("Teachers");
        btnTeacher.setName("pnlButtons"); // NOI18N
        btnTeacher.setOpaque(true);
        btnTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTeacherMouseClicked(evt);
            }
        });
        pnlButtons.add(btnTeacher);
        btnTeacher.setBounds(0, 0, 480, 120);

        btnStudent.setBackground(new java.awt.Color(45, 45, 45));
        btnStudent.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnStudent.setForeground(new java.awt.Color(255, 153, 0));
        btnStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnStudent.setText("Students");
        btnStudent.setName("pnlButtons"); // NOI18N
        btnStudent.setOpaque(true);
        btnStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentMouseClicked(evt);
            }
        });
        pnlButtons.add(btnStudent);
        btnStudent.setBounds(490, 0, 480, 120);

        btnSubject.setBackground(new java.awt.Color(45, 45, 45));
        btnSubject.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        btnSubject.setForeground(new java.awt.Color(255, 153, 0));
        btnSubject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubject.setText("Subjects");
        btnSubject.setName("pnlButtons"); // NOI18N
        btnSubject.setOpaque(true);
        btnSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubjectMouseClicked(evt);
            }
        });
        pnlButtons.add(btnSubject);
        btnSubject.setBounds(980, 0, 480, 120);

        jLabel7.setBackground(new java.awt.Color(45, 45, 45));
        jLabel7.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Settings");
        jLabel7.setName("pnlButtons"); // NOI18N
        jLabel7.setOpaque(true);
        pnlButtons.add(jLabel7);
        jLabel7.setBounds(1470, 0, 450, 120);

        jLabel11.setBackground(new java.awt.Color(255, 51, 0));
        jLabel11.setOpaque(true);
        pnlButtons.add(jLabel11);
        jLabel11.setBounds(1460, 30, 10, 50);

        jLabel12.setBackground(new java.awt.Color(255, 51, 0));
        jLabel12.setOpaque(true);
        pnlButtons.add(jLabel12);
        jLabel12.setBounds(970, 30, 10, 50);

        pnlAdmin.add(pnlButtons);
        pnlButtons.setBounds(0, 70, 1920, 120);

        pnlLoad.setBackground(new Color(0,0,0,0));
        pnlLoad.setLayout(null);
        pnlAdmin.add(pnlLoad);
        pnlLoad.setBounds(-1, 70, 1920, 1010);

        lblTitle.setFont(new java.awt.Font("Neuropol X Rg", 0, 50)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Configure Server Exams Online (24 X 7)");
        pnlAdmin.add(lblTitle);
        lblTitle.setBounds(0, 800, 1920, 150);

        lblStudentDesc.setBackground(new Color(51, 51, 51,150));
        lblStudentDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblStudentDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblStudentDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStudentDesc.setText("Manage Students via Students Tab");
        lblStudentDesc.setName("testCompo"); // NOI18N
        pnlAdmin.add(lblStudentDesc);
        lblStudentDesc.setBounds(490, 190, 650, 70);

        lblTeacherDesc.setFont(new java.awt.Font("Neuropol X Rg", 0, 24)); // NOI18N
        lblTeacherDesc.setForeground(new java.awt.Color(255, 181, 71));
        lblTeacherDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeacherDesc.setText("Manage Teachers via Teachers tab");
        pnlAdmin.add(lblTeacherDesc);
        lblTeacherDesc.setBounds(0, 190, 650, 70);

        lblDark.setBackground(new Color(0,0,0,170));
        lblDark.setOpaque(true);
        pnlAdmin.add(lblDark);
        lblDark.setBounds(0, 190, 1920, 890);

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/macos_sierra_2-wallpaper-1920x1080.jpg"))); // NOI18N
        pnlAdmin.add(lblImage);
        lblImage.setBounds(0, 0, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        try {
            AdminViewController.getAdminReservation().release("Student", studentPanel.getStudentId());
            AdminViewController.getAdminReservation().release("Teacher", teacherPanel.getTeacherId());
            AdminViewController.getAdminSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        try {
            AdminViewController.getAdminReservation().release("Student", studentPanel.getStudentId());
            AdminViewController.getAdminReservation().release("Teacher", teacherPanel.getTeacherId());
            AdminViewController.getAdminSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LoginForm();
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 22));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        btnLogout.setFont(new Font("Neuropol X Rg", 0, 20));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseClicked
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        panelVisibleFalse();
        studentPanel.setVisible(true);
    }//GEN-LAST:event_btnStudentMouseClicked

    private void btnSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubjectMouseClicked
//        pnlLoad.setVisible(true);
//        pnlButtons.setVisible(false);
    }//GEN-LAST:event_btnSubjectMouseClicked

    private void btnTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTeacherMouseClicked
        pnlLoad.setVisible(true);
        pnlButtons.setVisible(false);
        panelVisibleFalse();
        teacherPanel.setVisible(true);
    }//GEN-LAST:event_btnTeacherMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            AdminViewController.getAdminReservation().release("Student", studentPanel.getStudentId());
            AdminViewController.getAdminReservation().release("Teacher", teacherPanel.getTeacherId());
            AdminViewController.getAdminSubject().unregisterObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    

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
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnStudent;
    private javax.swing.JLabel btnSubject;
    private javax.swing.JLabel btnTeacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblStudentDesc;
    private javax.swing.JLabel lblTeacherDesc;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlLoad;
    private javax.swing.JPanel pnlTitleBar;
    // End of variables declaration//GEN-END:variables

}
