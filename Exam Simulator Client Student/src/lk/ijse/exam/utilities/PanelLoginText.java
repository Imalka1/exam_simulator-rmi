/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.utilities;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Imalka Gunawardana
 */
public class PanelLoginText extends JPanel {

    private int height;
    private int width;
    private int x;
    private int y;

    public PanelLoginText() {
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        setPos();
    }

    public int getPosX() {
        return x;
    }

    public void setPos() {
        setLocation(x, y);
    }

    public void setMainPnlSize(int x, int y) {
        setSize(x, y);
    }

    public void setPnlSize(int x, int y) {
        width = x;
        height = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 90));
        g.fillRoundRect(0, 0, width, height, 30, 30);
    }
}
