/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileSystemView;
import sun.swing.WindowsPlacesBar;

/**
 *
 * @author Imalka Gunawardana
 */
public class SubjectsPanel extends javax.swing.JPanel {

    private static SubjectsPanel subjectsPanel;
    private JPanel pnlLoad;
    private JPanel pnlButtons;
    private int id;

    /**
     * Creates new form SubjectsPanel
     */
    private SubjectsPanel() {
        initComponents();
        setSize(1920, 1010);
        setVisible(false);
        btnCopyLeft.setOpaque(true);
        btnCopyLeft.setContentAreaFilled(false);
        lblCopyLeft.setBackground(new Color(58, 58, 58));
        btnCopyLeft.setForeground(new Color(255, 165, 76));
        btnCopyRight.setOpaque(true);
        btnCopyRight.setContentAreaFilled(false);
        lblCopyRight.setBackground(new Color(58, 58, 58));
        btnCopyRight.setForeground(new Color(255, 165, 76));
    }

    public static SubjectsPanel getInstance() {
        if (subjectsPanel == null) {
            subjectsPanel = new SubjectsPanel();
        }
        return subjectsPanel;
    }

    public void setFile() {
        try {
            Properties properties = new Properties();
            File file = new File("settings/ServerFilePath.properties");
            FileReader inputStream = new FileReader(file.getAbsolutePath());
            properties.load(inputStream);
            File serverFile = new File(properties.getProperty("filePath") + "\\T" + id);
            class SingleRootFileSystemView extends FileSystemView {

                File root;
                File[] roots = new File[1];

                public SingleRootFileSystemView(File path) {
                    super();

                    try {
                        root = path.getCanonicalFile();
                        roots[0] = root;
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }

                    if (!root.isDirectory()) {
                        String message = root + " is not a directory";
                        throw new IllegalArgumentException(message);
                    }
                }

                @Override
                public File createNewFolder(File containingDir) {
                    File folder = new File(containingDir, "New Folder");
                    folder.mkdir();
                    return folder;
                }

                @Override
                public File getDefaultDirectory() {
                    return root;
                }

                @Override
                public File getHomeDirectory() {
                    return root;
                }

                @Override
                public File[] getRoots() {
                    return roots;
                }
            }
            flcServer.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            flcClient.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            FileSystemView fv = new SingleRootFileSystemView(serverFile);
            flcServer.setFileSystemView(fv);
            flcServer.setCurrentDirectory(serverFile);
            flcServer.setMultiSelectionEnabled(true);
            updateFileChoosers();
        } catch (Exception ex) {
            Logger.getLogger(SubjectsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFileChoosers() {
        //flcServer.updateUI();
        Component[] componentServer = flcServer.getComponents();
        for (int i = 0; i < componentServer.length; i++) {
            Component comp = flcServer.getComponent(i);
            if (comp instanceof WindowsPlacesBar) {
                WindowsPlacesBar b = (WindowsPlacesBar) comp;
                b.setVisible(false);
            }
            if (comp instanceof JToolBar) {
                JToolBar b = (JToolBar) comp;
                Component[] components1 = b.getComponents();
                components1[2].setEnabled(false);
                components1[5].setEnabled(false);
            }
        }
        Component[] componentsClient = flcClient.getComponents();
        for (int i = 0; i < componentsClient.length; i++) {
            Component comp = flcClient.getComponent(i);
            if (comp instanceof JToolBar) {
                JToolBar b = (JToolBar) comp;
                Component[] components1 = b.getComponents();
                components1[5].setEnabled(false);
            }
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

        flcClient = new javax.swing.JFileChooser();
        flcServer = new javax.swing.JFileChooser();
        btnBackPage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        btnCopyLeft = new javax.swing.JButton();
        btnCopyRight = new javax.swing.JButton();
        lblCopyLeft = new javax.swing.JLabel();
        lblCopyRight = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setLayout(null);
        add(flcClient);
        flcClient.setBounds(30, 280, 730, 530);

        flcServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flcServerActionPerformed(evt);
            }
        });
        add(flcServer);
        flcServer.setBounds(1160, 280, 730, 530);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        add(jPanel1);
        jPanel1.setBounds(30, 280, 730, 530);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        add(jPanel2);
        jPanel2.setBounds(1160, 280, 730, 530);

        jLabel1.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Teacher");
        add(jLabel1);
        jLabel1.setBounds(30, 220, 730, 37);

        jLabel2.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Server");
        add(jLabel2);
        jLabel2.setBounds(1160, 220, 730, 37);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/Sort Left_48px.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(800, 570, 50, 50);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/ijse/exam/asset/Sort Right_48px.png"))); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(1070, 490, 50, 50);

        lblDivision.setFont(new java.awt.Font("Neuropol X Rg", 0, 60)); // NOI18N
        lblDivision.setForeground(new java.awt.Color(255, 102, 51));
        lblDivision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivision.setText("Manage Question Papers");
        add(lblDivision);
        lblDivision.setBounds(0, 20, 1920, 61);

        btnCopyLeft.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        btnCopyLeft.setText("Copy To Teacher");
        btnCopyLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyLeftActionPerformed(evt);
            }
        });
        add(btnCopyLeft);
        btnCopyLeft.setBounds(850, 570, 220, 50);

        btnCopyRight.setFont(new java.awt.Font("Consolas", 0, 22)); // NOI18N
        btnCopyRight.setText("Copy To Server");
        btnCopyRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyRightActionPerformed(evt);
            }
        });
        add(btnCopyRight);
        btnCopyRight.setBounds(850, 490, 220, 50);

        lblCopyLeft.setOpaque(true);
        add(lblCopyLeft);
        lblCopyLeft.setBounds(850, 570, 220, 50);

        lblCopyRight.setOpaque(true);
        add(lblCopyRight);
        lblCopyRight.setBounds(850, 490, 220, 50);

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

    private void flcServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flcServerActionPerformed

    }//GEN-LAST:event_flcServerActionPerformed

    private void btnCopyRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyRightActionPerformed
        if (flcServer.getSelectedFile() != null) {
            String url1 = flcServer.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
            String url2 = flcClient.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
            String[] split1 = url1.split("\\\\");
            String[] split2 = url2.split("\\\\");
            if (split1[split1.length - 1].equals(split2[split2.length - 1])) {
                if (flcServer.getSelectedFile().listFiles() != null) {
                    for (File file : flcServer.getSelectedFile().listFiles()) {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    }
                } else {
                    for (File file : flcServer.getSelectedFiles()) {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    }
                }
                try {
                    final Path srcdir = Paths.get(flcClient.getSelectedFile().getAbsolutePath());
                    final Path dstdir = Paths.get(flcServer.getSelectedFile().getAbsolutePath());
                    if (flcClient.getSelectedFile().isDirectory() && flcServer.getSelectedFile().isDirectory()) {
                        for (final Path path : Files.newDirectoryStream(srcdir)) {
                            Files.copy(path, dstdir.resolve(path.getFileName()));
                        }
                    } 
                } catch (IOException ex) {
                    Logger.getLogger(SubjectsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "File path doesn't match");
            }
            updateFileChoosers();
        }
    }//GEN-LAST:event_btnCopyRightActionPerformed

    private void btnCopyLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyLeftActionPerformed
        if (flcServer.getSelectedFile() != null) {
            String url1 = flcClient.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
            String url2 = flcServer.getSelectedFile().getAbsolutePath().replace("\\", "\\\\");
            String[] split1 = url1.split("\\\\");
            String[] split2 = url2.split("\\\\");
            if (split1[split1.length - 1].equals(split2[split2.length - 1])) {
                if (flcClient.getSelectedFile().listFiles() != null) {
                    for (File file : flcClient.getSelectedFile().listFiles()) {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    }
                } else {
                    for (File file : flcClient.getSelectedFiles()) {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    }
                }
                try {
                    final Path srcdir = Paths.get(flcServer.getSelectedFile().getAbsolutePath());
                    final Path dstdir = Paths.get(flcClient.getSelectedFile().getAbsolutePath());
                    if (flcClient.getSelectedFile().isDirectory() && flcServer.getSelectedFile().isDirectory()) {
                        for (final Path path : Files.newDirectoryStream(srcdir)) {
                            Files.copy(path, dstdir.resolve(path.getFileName()));
                        }
                    } 
                } catch (IOException ex) {
                    Logger.getLogger(SubjectsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "File path doesn't match");
            }
            updateFileChoosers();
        }
    }//GEN-LAST:event_btnCopyLeftActionPerformed

    public void setPnlProperties(JPanel pnlLoad, JPanel pnlButtons, int id) {
        this.pnlLoad = pnlLoad;
        this.pnlButtons = pnlButtons;
        this.id = id;
        setFile();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JButton btnCopyLeft;
    private javax.swing.JButton btnCopyRight;
    private javax.swing.JFileChooser flcClient;
    private javax.swing.JFileChooser flcServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCopyLeft;
    private javax.swing.JLabel lblCopyRight;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblImage;
    // End of variables declaration//GEN-END:variables
}
