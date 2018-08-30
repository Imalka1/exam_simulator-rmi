/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import lk.ijse.exam.controller.QuestionsPanelController;
import lk.ijse.exam.controller.TestExamPanelController;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.utilities.AnswerPanel;
import lk.ijse.exam.utilities.CountdownTimerPanel;

/**
 *
 * @author Imalka Gunawardana
 */
public class QuestionsPanel extends javax.swing.JPanel {

    private static QuestionsPanel testQuestionsPanel;
    private TestExamPanel testExamPanel;
    private Thread thread;
    private PaperDTO questionsObj;
    private JPanel pnlLoad;
    private String[] dataForServer;
    private boolean type;
    private CountdownTimerPanel timer;
    private StudentView studentView;
    private String currentQuestion;
    private HashMap<String, String> questions;
    private HashMap<String, Integer> answersCountWithNo;
    private HashMap<String, List<String>> answersWithNo;
    private HashMap<String, List<AnswerPanel>> holdAnswers;

    /**
     * Creates new form QuestionTestPanel
     */
    private QuestionsPanel() {
        initComponents();
        txtQuestion.setEditable(false);
        txtQuestion.setText("");
        setSize(1920, 1010);
        setVisible(false);
        customizeQuestionsTable();
        waitingServer();
    }

    public static QuestionsPanel getInstance() {
        if (testQuestionsPanel == null) {
            testQuestionsPanel = new QuestionsPanel();
        }
        return testQuestionsPanel;
    }

    private void waitingServer() {
        thread = new Thread(() -> {
            while (true) {
                lblDot1.setVisible(false);
                lblDot2.setVisible(false);
                lblDot3.setVisible(false);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblDot1.setVisible(true);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblDot2.setVisible(true);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblDot3.setVisible(true);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    private DefaultTableCellRenderer customizeTable() {
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
        return new CustomizeTable();
    }

    private void customizeQuestionsTable() {
        tblQuestions.setDefaultRenderer(Object.class, customizeTable());
        tblQuestions.setRowHeight(70);
        tblQuestions.setShowHorizontalLines(true);
        tblQuestions.setShowGrid(false);
        tblQuestions.setTableHeader(null);
        tblQuestions.setFont(new Font("", 0, 20));
        tblQuestions.setOpaque(false);
        scrQuestions.getViewport().setBackground(new Color(58, 58, 58));
        scrQuestions.setBorder(null);
    }

    public void startQuestions(PaperDTO paperDTO) {
        try {
            txtQuestion.setText("");
            questionsObj = QuestionsPanelController.generateQuestionsByStudent(paperDTO);
            questions = (HashMap) questionsObj.getQuestionWithNo();
            answersCountWithNo = questionsObj.getAnswersCountWithNo();
            holdAnswers = new HashMap<>();
            Set<String> keySet = answersCountWithNo.keySet();
            for (String key : keySet) {
                List<AnswerPanel> answerPanels = new LinkedList<>();
                Integer get = answersCountWithNo.get(key);
                for (int i = 0; i < get; i++) {
                    answerPanels.add(new AnswerPanel((char) (65 + i)));
                }
                holdAnswers.put(key, answerPanels);
            }
            answersWithNo = questionsObj.getAnswersWithNo();
            AnswerPanel.setAnswersWithNo(answersWithNo);
            List<String> questionNo = questionsObj.getQuestionNo();
            DefaultTableModel model = (DefaultTableModel) tblQuestions.getModel();
            model.setRowCount(0);
            for (String string : questionNo) {
                Object[] obj = {" " + string};
                model.addRow(obj);
            }
            String[] time = paperDTO.getTime().split(":");
            timer = new CountdownTimerPanel(this, time);
            pnlTimer.removeAll();
            pnlTimer.add(timer);
        } catch (Exception ex) {
            Logger.getLogger(QuestionsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pnlWaitingVisibility(boolean btnVisibility) {
        pnlDisplay.setVisible(btnVisibility);
        lblDisplay.setText("Waiting for Server");
        if (btnVisibility) {
            thread.resume();
        } else {
            thread.suspend();
        }
    }
    
    /*To set answer panel*/
    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void resetAll() {
        DefaultTableModel model = (DefaultTableModel) tblQuestions.getModel();
        model.setRowCount(0);
        txtQuestion.setText("");
        pnlAnswers.removeAll();
        pnlAnswers.repaint();
        lblQuestionNo.setText("");
    }

    public void clearTime() {
        pnlTimer.removeAll();
    }

    public void setDisplayText() {
        lblDisplay.setText("Waiting for Results");
    }

    public void setBackPage(TestExamPanel testExamPanel) {
        this.testExamPanel = testExamPanel;
    }

    public void setDataForServer(String[] dataForServer) {
        this.dataForServer = dataForServer;
    }

    public void setStudentView(StudentView studentView) {
        this.studentView = studentView;
    }

    public void setGenerateType(boolean type) {
        this.type = type;
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
        txtQuestion = new javax.swing.JTextArea();
        scrQuestions = new javax.swing.JScrollPane();
        tblQuestions = new javax.swing.JTable();
        pnlDisplay = new javax.swing.JPanel();
        lblDisplay = new javax.swing.JLabel();
        lblDot1 = new javax.swing.JLabel();
        lblDot2 = new javax.swing.JLabel();
        lblDot3 = new javax.swing.JLabel();
        pnlAnswers = new javax.swing.JPanel();
        pnlTimer = new javax.swing.JPanel();
        btnBackPage = new javax.swing.JLabel();
        lblQuestionNo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDark = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setLayout(null);

        txtQuestion.setEditable(false);
        txtQuestion.setBackground(new Color(58,58,58));
        txtQuestion.setColumns(20);
        txtQuestion.setFont(new java.awt.Font("Consolas", 0, 23)); // NOI18N
        txtQuestion.setForeground(new java.awt.Color(255, 255, 255));
        txtQuestion.setRows(5);
        scrQuestion.setViewportView(txtQuestion);

        add(scrQuestion);
        scrQuestion.setBounds(330, 160, 1560, 550);

        tblQuestions.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQuestions.getTableHeader().setReorderingAllowed(false);
        tblQuestions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuestionsMouseClicked(evt);
            }
        });
        scrQuestions.setViewportView(tblQuestions);
        if (tblQuestions.getColumnModel().getColumnCount() > 0) {
            tblQuestions.getColumnModel().getColumn(0).setResizable(false);
        }

        add(scrQuestions);
        scrQuestions.setBounds(30, 160, 290, 550);

        pnlDisplay.setBackground(new Color(0,0,0,0));
        pnlDisplay.setOpaque(false);
        pnlDisplay.setLayout(null);

        lblDisplay.setFont(new java.awt.Font("Neuropol X Rg", 0, 30)); // NOI18N
        lblDisplay.setForeground(new java.awt.Color(204, 204, 204));
        lblDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisplay.setText("Waiting for Server");
        pnlDisplay.add(lblDisplay);
        lblDisplay.setBounds(0, 20, 460, 40);

        lblDot1.setFont(new java.awt.Font("Neuropol X Rg", 1, 80)); // NOI18N
        lblDot1.setForeground(new java.awt.Color(204, 204, 204));
        lblDot1.setText(".");
        pnlDisplay.add(lblDot1);
        lblDot1.setBounds(470, -20, 30, 80);

        lblDot2.setFont(new java.awt.Font("Neuropol X Rg", 1, 80)); // NOI18N
        lblDot2.setForeground(new java.awt.Color(204, 204, 204));
        lblDot2.setText(".");
        pnlDisplay.add(lblDot2);
        lblDot2.setBounds(510, -20, 40, 80);

        lblDot3.setFont(new java.awt.Font("Neuropol X Rg", 1, 80)); // NOI18N
        lblDot3.setForeground(new java.awt.Color(204, 204, 204));
        lblDot3.setText(".");
        pnlDisplay.add(lblDot3);
        lblDot3.setBounds(550, -20, 40, 80);

        add(pnlDisplay);
        pnlDisplay.setBounds(750, 30, 650, 80);

        pnlAnswers.setBackground(new java.awt.Color(58, 58, 58));
        pnlAnswers.setLayout(new java.awt.GridLayout(1, 0));
        add(pnlAnswers);
        pnlAnswers.setBounds(328, 720, 1560, 280);

        pnlTimer.setBackground(new java.awt.Color(58, 58, 58));
        pnlTimer.setLayout(null);
        add(pnlTimer);
        pnlTimer.setBounds(30, 720, 290, 90);

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

        lblQuestionNo.setFont(new java.awt.Font("Neuropol X Rg", 0, 20)); // NOI18N
        lblQuestionNo.setForeground(new java.awt.Color(204, 204, 204));
        lblQuestionNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(lblQuestionNo);
        lblQuestionNo.setBounds(260, 110, 1630, 50);
        add(jLabel3);
        jLabel3.setBounds(1510, 920, 360, 50);

        jLabel4.setFont(new java.awt.Font("Neuropol X Rg", 0, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Questions Queue");
        add(jLabel4);
        jLabel4.setBounds(30, 110, 290, 50);
        add(jLabel1);
        jLabel1.setBounds(30, 920, 520, 50);

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
        this.setVisible(false);
        testExamPanel.setVisible(true);
        dataForServer[4] = "Unregister";
        if (timer != null) {
            timer.stopTimer();
        }
        try {
            TestExamPanelController.getTeacherSubject().notifyObservers(1);
        } catch (Exception ex) {
            Logger.getLogger(TestExamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackPageMouseClicked

    private void tblQuestionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuestionsMouseClicked
        if (tblQuestions.getSelectedRow() > -1) {
            lblQuestionNo.setText(tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim());
            String question = (String) questions.get(tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim());
            txtQuestion.setText(question);
            DefaultCaret caret = (DefaultCaret) txtQuestion.getCaret();
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

            int rowCount = (answersCountWithNo.get(tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim()) / 4);
            if ((answersCountWithNo.get(tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim()) % 4) != 0) {
                rowCount++;
            }
            GridLayout layout = (GridLayout) pnlAnswers.getLayout();
            pnlAnswers.removeAll();
            layout.setRows(rowCount);
            layout.setColumns(4);
            List<AnswerPanel> get = holdAnswers.get(tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim());
            for (AnswerPanel answerPanel : get) {
                pnlAnswers.add(answerPanel);
            }
            pnlAnswers.repaint();
            currentQuestion = tblQuestions.getValueAt(tblQuestions.getSelectedRow(), 0).toString().trim();
        }
    }//GEN-LAST:event_tblQuestionsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackPage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDark;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblDot1;
    private javax.swing.JLabel lblDot2;
    private javax.swing.JLabel lblDot3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblQuestionNo;
    private javax.swing.JPanel pnlAnswers;
    private javax.swing.JPanel pnlDisplay;
    private javax.swing.JPanel pnlTimer;
    private javax.swing.JScrollPane scrQuestion;
    private javax.swing.JScrollPane scrQuestions;
    private javax.swing.JTable tblQuestions;
    private javax.swing.JTextArea txtQuestion;
    // End of variables declaration//GEN-END:variables

}
