package com.compi2.simpascal.interfaz;

import java.awt.Dimension;
import javax.swing.*;

public class TabButtonUI extends JButton {

    private final ImageIcon icon;
    private final ImageIcon hoveredIcon;
    private final ImageIcon clickedIcon;

    public TabButtonUI() {
        icon = new ImageIcon("./resources/tabButton.png");
        hoveredIcon = new ImageIcon("./resources/hoveredTabButton.png");
        clickedIcon = new ImageIcon("./resources/clickedTabButton.png");
        setIcon(icon);
        setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setPreferredSize(new Dimension(100, 30));
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                entered(evt);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                normal(evt);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                click(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                normal(evt);
            }
        });
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void entered(java.awt.event.MouseEvent evt) {
        setIcon(hoveredIcon);
        updateUI();
    }

    private void click(java.awt.event.MouseEvent evt) {
        setIcon(clickedIcon);
        updateUI();
    }

    private void normal(java.awt.event.MouseEvent evt) {
        setIcon(icon);
        updateUI();
    }

}
