package com.compi2.simpascal.interfaz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author mgome
 */
public class MenuLateral extends javax.swing.JPanel {
    
    public MenuLateral() {
        initComponents();
        
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gd = new GradientPaint(0, 0, Color.decode("#3C3D37"), 0, getHeight(), Color.decode("#3C3D37"));
        g2.setPaint(gd);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintChildren(g);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuButton = new javax.swing.JButton();
        reportesButton = new javax.swing.JButton();

        setForeground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1080, 30));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1080, 30));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuButton.setBackground(new java.awt.Color(207, 113, 107));
        menuButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        menuButton.setForeground(new java.awt.Color(255, 255, 255));
        menuButton.setText("1");
        menuButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        menuButton.setContentAreaFilled(false);
        menuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuButton.setFocusPainted(false);
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });
        add(menuButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 30));

        reportesButton.setBackground(new java.awt.Color(207, 113, 107));
        reportesButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        reportesButton.setForeground(new java.awt.Color(255, 255, 255));
        reportesButton.setText("2");
        reportesButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        reportesButton.setContentAreaFilled(false);
        reportesButton.setFocusPainted(false);
        add(reportesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 200, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton menuButton;
    public javax.swing.JButton reportesButton;
    // End of variables declaration//GEN-END:variables
}
