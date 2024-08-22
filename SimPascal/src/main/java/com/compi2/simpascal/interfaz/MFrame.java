package com.compi2.simpascal.interfaz;

import com.compi2.simpascal.instrucciones.Errores;
import com.compi2.simpascal.instrucciones.Instruccion;
import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.lexico.Lexico;
import com.compi2.simpascal.sintactico.parser;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgome
 */
public class MFrame extends javax.swing.JFrame {

    Tabla tabla;
    LinkedList<Errores> errores;
    Arbol ast;
    
    public MFrame() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.compi2.simpascal.interfaz.PanelBorder();
        titleBar1 = new com.compi2.simpascal.interfaz.TitleBar();
        card = new javax.swing.JPanel();
        editor = new javax.swing.JPanel();
        tituloConsola = new javax.swing.JLabel();
        tabPanel2 = new com.compi2.simpascal.interfaz.TabPanel();
        consolePanel2 = new com.compi2.simpascal.interfaz.ConsolePanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(50, 50, 50));
        panelBorder1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 32, 30), 5, true));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBorder1.add(titleBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        card.setLayout(new java.awt.CardLayout());

        editor.setBackground(new java.awt.Color(50, 50, 50));
        editor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloConsola.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
        tituloConsola.setForeground(new java.awt.Color(18, 175, 177));
        tituloConsola.setText("Consola");
        editor.add(tituloConsola, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));
        editor.add(tabPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));
        editor.add(consolePanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 1000, 130));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        editor.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, -1, -1));

        card.add(editor, "card2");

        panelBorder1.add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1020, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            errores = new LinkedList<>();
            String texto = tabPanel2.textAreaPanel1.textArea.getText();
            Lexico s = new Lexico(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            tabla = new Tabla();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            ast.setTablaGlobal(tabla);
            
            for(var a : ast.getInstrucciones()) {
                
                var as = a.interpretar(ast, tabla);
                if (as instanceof Errores e) {
                    errores.add(e);
                }
            }
            
            errores.addAll(s.listaErrores);
            errores.addAll(p.listaErrores);
            
            for (var i : errores) {
                System.out.println(i);
            }

        } catch (Exception ex) {
            Logger.getLogger(MFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card;
    private com.compi2.simpascal.interfaz.ConsolePanel consolePanel2;
    private javax.swing.JPanel editor;
    private javax.swing.JButton jButton1;
    private com.compi2.simpascal.interfaz.PanelBorder panelBorder1;
    private com.compi2.simpascal.interfaz.TabPanel tabPanel2;
    private com.compi2.simpascal.interfaz.TitleBar titleBar1;
    private javax.swing.JLabel tituloConsola;
    // End of variables declaration//GEN-END:variables
}
