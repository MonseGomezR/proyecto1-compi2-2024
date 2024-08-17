package com.compi2.simpascal.interfaz;

import java.awt.Color;

/**
 *
 * @author mgome
 */
public class TextAreaPanel extends javax.swing.JPanel {

    public TextAreaPanel() {
        initComponents();

        scrollPane.getViewport().setOpaque(false);
        scrollPane.setViewportBorder(null);
        //scrollPane.setBackground(null); // Prueba esta línea
        scrollPane.setBackground(new Color(70,70,70));
        scrollPane.setForeground(new Color(150,150,150));

        textArea.setForeground(new Color(150,150,150));
        textArea.setBackground(new Color(81, 81, 81));

        NumeroLinea nl = new NumeroLinea(textArea);
        nl.setOpaque(true);
        nl.setForeground(new Color(150,150,150));
        scrollPane.setRowHeaderView(nl);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(81, 81, 81));

        scrollPane.setBorder(null);

        textArea.setBackground(new java.awt.Color(81, 81, 81));
        textArea.setBorder(null);
        textArea.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        textArea.setForeground(new java.awt.Color(0, 153, 102));
        scrollPane.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    public javax.swing.JTextPane textArea;
    // End of variables declaration//GEN-END:variables
}
