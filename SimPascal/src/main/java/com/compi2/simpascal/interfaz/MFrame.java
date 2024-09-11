package com.compi2.simpascal.interfaz;

import com.compi2.simpascal.ArchivosManager;
import com.compi2.simpascal.DotToImage;
import com.compi2.simpascal.instrucciones.*;
import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.lexico.Lexico;
import com.compi2.simpascal.sintactico.parser;
import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.TypeP;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mgome
 */
public class MFrame extends javax.swing.JFrame {

    ArchivosManager am;
    DotToImage dti;
    Tabla tabla;
    LinkedList<Errores> errores;
    Arbol ast;
    Lexico s;
    parser p;
    String lastAST = "";
    String lastAA = "";
    int contador = 0;

    public MFrame() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
        consolePanel2.jTextPane1.setForeground(Color.white);
        am = new ArchivosManager();
        dti = new DotToImage();
        analizar1.setContentAreaFilled(false);
        analizarT.setContentAreaFilled(false);
        guardar.setContentAreaFilled(false);
        guardarc.setContentAreaFilled(false);
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
        menuEditor = new javax.swing.JPanel();
        analizar1 = new javax.swing.JButton();
        analizarT = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        guardarc = new javax.swing.JButton();
        simbolos = new javax.swing.JPanel();
        tablaSimbolos1 = new com.compi2.simpascal.interfaz.TablaSimbolos();
        tipos = new javax.swing.JPanel();
        tablaTipos1 = new com.compi2.simpascal.interfaz.TablaTipos();
        erroresP = new javax.swing.JPanel();
        tablaErrores1 = new com.compi2.simpascal.interfaz.TablaErrores();
        menuBar = new javax.swing.JPanel();
        editorBtn = new javax.swing.JButton();
        tablasBtn = new javax.swing.JButton();
        tablatBtn = new javax.swing.JButton();
        astBtn = new javax.swing.JButton();
        aaBtn = new javax.swing.JButton();
        tablaeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(50, 50, 50));
        panelBorder1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 32, 30), 5, true));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelBorder1.add(titleBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        card.setLayout(new java.awt.CardLayout());

        editor.setBackground(new java.awt.Color(50, 50, 50));
        editor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tituloConsola.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
        tituloConsola.setForeground(new java.awt.Color(18, 175, 177));
        tituloConsola.setText("Consola");
        editor.add(tituloConsola, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, -1));
        editor.add(tabPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 990, 370));
        editor.add(consolePanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1080, 180));

        menuEditor.setOpaque(false);

        analizar1.setBackground(new java.awt.Color(255, 255, 255));
        analizar1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        analizar1.setForeground(new java.awt.Color(18, 175, 177));
        analizar1.setText("Analizar(1)");
        analizar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 175, 177), 3, true));
        analizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analizar1.setFocusPainted(false);
        analizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizar1ActionPerformed(evt);
            }
        });

        analizarT.setBackground(new java.awt.Color(255, 255, 255));
        analizarT.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        analizarT.setForeground(new java.awt.Color(18, 175, 177));
        analizarT.setText("Analizar(T)");
        analizarT.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 175, 177), 3, true));
        analizarT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analizarT.setFocusPainted(false);
        analizarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTActionPerformed(evt);
            }
        });

        guardar.setBackground(new java.awt.Color(255, 255, 255));
        guardar.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        guardar.setForeground(new java.awt.Color(18, 175, 177));
        guardar.setText("Guardar");
        guardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 175, 177), 3, true));
        guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardar.setFocusPainted(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        guardarc.setBackground(new java.awt.Color(255, 255, 255));
        guardarc.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        guardarc.setForeground(new java.awt.Color(18, 175, 177));
        guardarc.setText("Guardar como...");
        guardarc.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 175, 177), 3, true));
        guardarc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarc.setFocusPainted(false);
        guardarc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuEditorLayout = new javax.swing.GroupLayout(menuEditor);
        menuEditor.setLayout(menuEditorLayout);
        menuEditorLayout.setHorizontalGroup(
            menuEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(analizar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(analizarT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarc, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
        );
        menuEditorLayout.setVerticalGroup(
            menuEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(analizarT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardarc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        editor.add(menuEditor, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 80, -1, -1));

        card.add(editor, "editor");

        javax.swing.GroupLayout simbolosLayout = new javax.swing.GroupLayout(simbolos);
        simbolos.setLayout(simbolosLayout);
        simbolosLayout.setHorizontalGroup(
            simbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simbolosLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(tablaSimbolos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        simbolosLayout.setVerticalGroup(
            simbolosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simbolosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(tablaSimbolos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        card.add(simbolos, "simbolos");

        javax.swing.GroupLayout tiposLayout = new javax.swing.GroupLayout(tipos);
        tipos.setLayout(tiposLayout);
        tiposLayout.setHorizontalGroup(
            tiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tiposLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(tablaTipos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        tiposLayout.setVerticalGroup(
            tiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tiposLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(tablaTipos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        card.add(tipos, "tipos");

        javax.swing.GroupLayout erroresPLayout = new javax.swing.GroupLayout(erroresP);
        erroresP.setLayout(erroresPLayout);
        erroresPLayout.setHorizontalGroup(
            erroresPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(erroresPLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(tablaErrores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        erroresPLayout.setVerticalGroup(
            erroresPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(erroresPLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(tablaErrores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        card.add(erroresP, "erroresP");

        panelBorder1.add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1080, 580));

        menuBar.setBackground(new java.awt.Color(153, 153, 153));
        menuBar.setOpaque(false);

        editorBtn.setBackground(new java.awt.Color(88, 88, 88));
        editorBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        editorBtn.setForeground(new java.awt.Color(255, 255, 255));
        editorBtn.setText("Editor");
        editorBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        editorBtn.setBorderPainted(false);
        editorBtn.setContentAreaFilled(false);
        editorBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editorBtn.setFocusPainted(false);
        editorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editorBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editorBtnMouseExited(evt);
            }
        });
        editorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorBtnActionPerformed(evt);
            }
        });

        tablasBtn.setBackground(new java.awt.Color(88, 88, 88));
        tablasBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        tablasBtn.setForeground(new java.awt.Color(255, 255, 255));
        tablasBtn.setText("Tabla de Simbolos");
        tablasBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        tablasBtn.setBorderPainted(false);
        tablasBtn.setContentAreaFilled(false);
        tablasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablasBtn.setFocusPainted(false);
        tablasBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablasBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tablasBtnMouseExited(evt);
            }
        });
        tablasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablasBtnActionPerformed(evt);
            }
        });

        tablatBtn.setBackground(new java.awt.Color(88, 88, 88));
        tablatBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        tablatBtn.setForeground(new java.awt.Color(255, 255, 255));
        tablatBtn.setText("Tabla de Tipos");
        tablatBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        tablatBtn.setBorderPainted(false);
        tablatBtn.setContentAreaFilled(false);
        tablatBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablatBtn.setFocusPainted(false);
        tablatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablatBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tablatBtnMouseExited(evt);
            }
        });
        tablatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablatBtnActionPerformed(evt);
            }
        });

        astBtn.setBackground(new java.awt.Color(88, 88, 88));
        astBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        astBtn.setForeground(new java.awt.Color(255, 255, 255));
        astBtn.setText("Abrir Arbol AST");
        astBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        astBtn.setBorderPainted(false);
        astBtn.setContentAreaFilled(false);
        astBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        astBtn.setFocusPainted(false);
        astBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                astBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                astBtnMouseExited(evt);
            }
        });
        astBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                astBtnActionPerformed(evt);
            }
        });

        aaBtn.setBackground(new java.awt.Color(88, 88, 88));
        aaBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        aaBtn.setForeground(new java.awt.Color(255, 255, 255));
        aaBtn.setText("Abrir Arbol de A");
        aaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        aaBtn.setBorderPainted(false);
        aaBtn.setContentAreaFilled(false);
        aaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aaBtn.setFocusPainted(false);
        aaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aaBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aaBtnMouseExited(evt);
            }
        });
        aaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aaBtnActionPerformed(evt);
            }
        });

        tablaeBtn.setBackground(new java.awt.Color(88, 88, 88));
        tablaeBtn.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        tablaeBtn.setForeground(new java.awt.Color(255, 255, 255));
        tablaeBtn.setText("Tabla de Errores");
        tablaeBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 88, 88), 4));
        tablaeBtn.setBorderPainted(false);
        tablaeBtn.setContentAreaFilled(false);
        tablaeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaeBtn.setFocusPainted(false);
        tablaeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaeBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tablaeBtnMouseExited(evt);
            }
        });
        tablaeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablaeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarLayout.createSequentialGroup()
                .addComponent(editorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tablasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tablatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tablaeBtn)
                .addGap(10, 10, 10)
                .addComponent(astBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(aaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(astBtn)
                        .addComponent(tablaeBtn))
                    .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tablasBtn)
                        .addComponent(tablatBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(aaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editorBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        panelBorder1.add(menuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1080, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizar1ActionPerformed

        DefaultTableModel modelS = (DefaultTableModel) tablaSimbolos1.tablaS.getModel();
        modelS.setRowCount(0);
        DefaultTableModel modelT = (DefaultTableModel) tablaTipos1.tablaS.getModel();
        modelT.setRowCount(0);
        DefaultTableModel modelE = (DefaultTableModel) tablaErrores1.tablaS.getModel();
        modelE.setRowCount(0);
        try {
            errores = new LinkedList<>();
            String texto = tabPanel2.textAreaPanel1.textArea.getText();
            s = new Lexico(new BufferedReader(new StringReader(texto)));
            p = new parser(s);
            var resultado = p.parse();
            String astD = "digraph " + p.programName + "{\n rankdir=TB\n"
                    + "nodesep=0.1\n"
                    + "ranksep=0.4\n"
                    + "node [style=filled, fontsize=10, width=1, height=.3, color=aquamarine]";
            String AA = "digraph " + p.programName + "AA{\n";
            if (resultado != null) {
                ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                ast.setConsola("");
                tabla = new Tabla();
                tabla.setNombre("GLOBAL");
                ast.setTablaGlobal(tabla);

                for (var a : ast.getInstrucciones()) {
                    if (a != null) {
                        if (a instanceof Funcion f) {
                            if (!tabla.addFuncion(f)) {
                                errores.add(new Errores("Semantico", "La funcion ya esta declarada.", f.linea, f.columna));
                            } else {
                                ast.addFunciones(f);
                            }
                        } else if (a instanceof Procedimiento pr) {
                            if (!tabla.addProcedimiento(pr)) {
                                errores.add(new Errores("Semantico", "El procedimiento ya esta declarado.", pr.linea, pr.columna));
                            } else {
                                ast.addProcedimiento(pr);
                            }
                        } else {
                            var as = a.interpretar(ast, tabla);

                            if (as instanceof Errores e) {
                                errores.add(e);
                            }
                            AA += a.generarAA("start", ast, tabla);
                        }

                        astD += a.generarast(ast);

                    }

                }
            }

            errores.addAll(s.listaErrores);
            errores.addAll(p.listaErrores);
            errores.addAll(ast.errores);

            String erroresText = "";
            if (errores.isEmpty()) {
                consolePanel2.jTextPane1.setText("No se encontraron errores en el archivo " + tabPanel2.actual.name + " analizado");
            } else {
                for (var i : errores) {
                    System.out.println(i);
                    erroresText += i + "\n";
                }
                consolePanel2.jTextPane1.setText(erroresText);
            }

            String[] lineas = astD.split("\n");
            Set<String> lineasUnicas = new LinkedHashSet<>(Arrays.asList(lineas));
            var r = String.join("\n", lineasUnicas);

            lastAST = r + "\n}\n\n";
            System.out.println(r + "\n}\n\n");

            String[] lineas2 = AA.split("\n");
            Set<String> lineasUnicas2 = new LinkedHashSet<>(Arrays.asList(lineas2));
            var r2 = String.join("\n", lineasUnicas2);

            System.out.println(r2 + "\n}");
            lastAA = r2 + "\n}";

            llenarTS(modelS);
            llenarTT(modelT);
            llenarTE(modelE, errores);

        } catch (Exception ex) {
            errores.addAll(s.listaErrores);
            errores.addAll(p.listaErrores);
            errores.addAll(ast.errores);
            String erroresText = "";
            for (var i : errores) {
                System.out.println(i);
                erroresText += i + "\n";
            }
            llenarTE(modelE, errores);
            consolePanel2.jTextPane1.setText(erroresText);
        }
    }//GEN-LAST:event_analizar1ActionPerformed

    public void llenarTS(DefaultTableModel model) {
        for (var simbolo : tabla.getSimbolos()) {
            if (simbolo.getTipo() instanceof TypeP type) {
                model.addRow(new Object[]{contador, simbolo.getId(), simbolo.getCategoria(), type.getId(), "", simbolo.getAmbito()});
            } else {
                model.addRow(new Object[]{contador, simbolo.getId(), simbolo.getCategoria(), simbolo.getTipo().getDato().name(), "", simbolo.getAmbito()});
            }
            contador++;
        }
        for (var typeName : tabla.typeNames) {
            if(ast.getTypes().get(typeName) instanceof TypeP typ) {
                model.addRow(new Object[]{contador, typeName, "Type", typ.getId(), "", tabla.getNombre()});
            }else {
                model.addRow(new Object[]{contador, typeName, "Type", ast.getTypes().get(typeName).getDato().name(), "", tabla.getNombre()});
            }
            contador++;
        }

        for (var i : tabla.getTablasHijas()) {
            for (var simbolo : i.getSimbolos()) {
                model.addRow(new Object[]{contador, simbolo.getId(), simbolo.getCategoria(), simbolo.getTipo().getDato().name(), "", simbolo.getAmbito()});
                contador++;
            }
        }

        for (var fun : ast.getFunciones()) {
            model.addRow(new Object[]{contador, fun.nombre, "Funcion", fun.tipo.getDato().name(), fun.parametros.size(), tabla.getNombre()});
            contador++;
        }

        for (var pro : ast.getProcedimientos()) {
            model.addRow(new Object[]{contador, pro.nombre, "Funcion", "VOID", pro.parametros.size(), tabla.getNombre()});
            contador++;
        }

    }

    public void llenarTT(DefaultTableModel model) {
        for (var simbolo : tabla.getSimbolos()) {
            model.addRow(new Object[]{contador, simbolo.getId(), simbolo.getCategoria(), simbolo.getTipo().getDato().name(), "", "", simbolo.getAmbito()});
            contador++;
        }
        for (var i : tabla.getTablasHijas()) {
            for (var simbolo : i.getSimbolos()) {
                model.addRow(new Object[]{contador, simbolo.getId(), simbolo.getCategoria(), simbolo.getTipo().getDato().name(), "", "", simbolo.getAmbito()});
                contador++;
            }
        }

        for (var fun : ast.getFunciones()) {
            model.addRow(new Object[]{contador, fun.nombre, "Funcion", fun.tipo.getDato().name(), fun.parametros.size(), "", tabla.getNombre()});
            contador++;
        }

        for (var pro : ast.getProcedimientos()) {
            model.addRow(new Object[]{contador, pro.nombre, "Funcion", "VOID", pro.parametros.size(), "", tabla.getNombre()});
            contador++;
        }

    }

    public void llenarTE(DefaultTableModel model, LinkedList<Errores> errores) {
        contador = 0;
        for (var errore : errores) {
            model.addRow(new Object[]{contador, errore.getTipo(), errore.getDesc(), errore.getLinea(), errore.getColumna()});
            contador++;
        }

    }

    private void analizarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarTActionPerformed
        consolePanel2.jTextPane1.setText("");
        for (Tab archivo : tabPanel2.archivosAbiertos) {
            try {
                if (archivo.getNombreArchivo().equals(tabPanel2.actual.name)) {
                    archivo.setContenidoTextArea(tabPanel2.textAreaPanel1.textArea.getText());
                }
                errores = new LinkedList<>();
                String texto = archivo.getContenidoTextArea();
                s = new Lexico(new BufferedReader(new StringReader(texto)));
                p = new parser(s);
                var resultado = p.parse();
                String astD = "digraph " + p.programName + "{\n rankdir=TB\n"
                        + "nodesep=0.1\n"
                        + "ranksep=0.4\n"
                        + "node [style=filled, fontsize=10, width=1, height=.3, color=aquamarine]";
                String AA = "digraph " + p.programName + "AA{\n";
                if (resultado != null) {
                    ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                    ast.setConsola("");
                    tabla = new Tabla();
                    tabla.setNombre("GLOBAL");
                    ast.setTablaGlobal(tabla);

                    for (var a : ast.getInstrucciones()) {
                        if (a != null) {
                            if (a instanceof Funcion f) {
                                if (!tabla.addFuncion(f)) {
                                    errores.add(new Errores("Semantico", "La funcion ya esta declarada.", f.linea, f.columna));
                                } else {
                                    ast.addFunciones(f);
                                }
                            } else if (a instanceof Procedimiento pr) {
                                if (!tabla.addProcedimiento(pr)) {
                                    errores.add(new Errores("Semantico", "El procedimiento ya esta declarado.", pr.linea, pr.columna));
                                } else {
                                    ast.addProcedimiento(pr);
                                }
                            } else {
                                var as = a.interpretar(ast, tabla);

                                if (as instanceof Errores e) {
                                    errores.add(e);
                                }
                                AA += a.generarAA("start", ast, tabla);
                            }

                            astD += a.generarast(ast);

                        }

                    }
                }

                errores.addAll(s.listaErrores);
                errores.addAll(p.listaErrores);
                errores.addAll(ast.errores);

                String erroresText = "";

                consolePanel2.jTextPane1.setText(consolePanel2.jTextPane1.getText() + "ARCHIVO: " + archivo.getNombreArchivo() + "----\nNo se encontraron errores en el archivo " + archivo.getNombreArchivo() + " analizado\n\n");

                if (!errores.isEmpty()) {
                    for (var i : errores) {
                        System.out.println(i);
                        erroresText += i + "\n";
                    }
                    consolePanel2.jTextPane1.setText(consolePanel2.jTextPane1.getText() + "ARCHIVO: " + archivo.getNombreArchivo() + "----\n" + erroresText);
                }

                String[] lineas = astD.split("\n");
                Set<String> lineasUnicas = new LinkedHashSet<>(Arrays.asList(lineas));
                var r = String.join("\n", lineasUnicas);

                System.out.println(r + "\n}\n\n");

                String[] lineas2 = AA.split("\n");
                Set<String> lineasUnicas2 = new LinkedHashSet<>(Arrays.asList(lineas2));
                var r2 = String.join("\n", lineasUnicas2);

                System.out.println(r2 + "\n}");

            } catch (Exception ex) {
                ex.printStackTrace();
                errores.addAll(s.listaErrores);
                errores.addAll(p.listaErrores);
                errores.addAll(ast.errores);
                String erroresText = "";
                for (var i : errores) {
                    System.out.println(i);
                    erroresText += i + "\n";
                }
                consolePanel2.jTextPane1.setText(consolePanel2.jTextPane1.getText() + "ARCHIVO: " + archivo.getNombreArchivo() + "----\n" + erroresText);
            }
        }
    }//GEN-LAST:event_analizarTActionPerformed

    private void guardarcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarcActionPerformed
        String contenido = tabPanel2.textAreaPanel1.textArea.getText();
        String nombre = tabPanel2.actual.name;
        am.guardarComo(this, contenido, new File(nombre));
    }//GEN-LAST:event_guardarcActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String contenido = tabPanel2.textAreaPanel1.textArea.getText();
        for (int i = 0; i < tabPanel2.archivosAbiertos.size(); i++) {
            Tab aux = tabPanel2.archivosAbiertos.get(i);
            if (aux.getBotonAsignado() == tabPanel2.actual.getMainButton()) {
                am.guardar(this, aux.getDireccion(), contenido);
                break;
            }
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void editorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorBtnActionPerformed
        CardLayout cl = (CardLayout) card.getLayout();
        cl.show(card, "editor");
    }//GEN-LAST:event_editorBtnActionPerformed

    private void tablasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablasBtnActionPerformed
        tablaSimbolos1.tituloArchivo.setText(tabPanel2.actual.name);
        CardLayout cl = (CardLayout) card.getLayout();
        cl.show(card, "simbolos");
    }//GEN-LAST:event_tablasBtnActionPerformed

    private void tablatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablatBtnActionPerformed
        tablaTipos1.tituloArchivo.setText(tabPanel2.actual.name);
        CardLayout cl = (CardLayout) card.getLayout();
        cl.show(card, "tipos");
    }//GEN-LAST:event_tablatBtnActionPerformed

    private void astBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_astBtnActionPerformed
        if (!lastAST.equals("")) {
            dti.generar(tabPanel2.actual.name, lastAST);
        }
    }//GEN-LAST:event_astBtnActionPerformed

    private void aaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aaBtnActionPerformed
        if (!lastAA.equals("")) {
            dti.generar(tabPanel2.actual.name, lastAA);
        }
    }//GEN-LAST:event_aaBtnActionPerformed

    private void editorBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorBtnMouseEntered
        editorBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_editorBtnMouseEntered

    private void editorBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorBtnMouseExited
        editorBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_editorBtnMouseExited

    private void tablasBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablasBtnMouseEntered
        tablasBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_tablasBtnMouseEntered

    private void tablasBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablasBtnMouseExited
        tablasBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_tablasBtnMouseExited

    private void tablatBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatBtnMouseEntered
        tablatBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_tablatBtnMouseEntered

    private void tablatBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatBtnMouseExited
        tablatBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_tablatBtnMouseExited

    private void astBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_astBtnMouseEntered
        astBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_astBtnMouseEntered

    private void astBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_astBtnMouseExited
        astBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_astBtnMouseExited

    private void aaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aaBtnMouseEntered
        aaBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_aaBtnMouseEntered

    private void aaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aaBtnMouseExited
        aaBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_aaBtnMouseExited

    private void tablaeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablaeBtnActionPerformed
        tablaErrores1.tituloArchivo.setText(tabPanel2.actual.name);
        CardLayout cl = (CardLayout) card.getLayout();
        cl.show(card, "erroresP");
    }//GEN-LAST:event_tablaeBtnActionPerformed

    private void tablaeBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaeBtnMouseEntered
        tablaeBtn.setContentAreaFilled(true);
    }//GEN-LAST:event_tablaeBtnMouseEntered

    private void tablaeBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaeBtnMouseExited
        tablaeBtn.setContentAreaFilled(false);
    }//GEN-LAST:event_tablaeBtnMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aaBtn;
    private javax.swing.JButton analizar1;
    private javax.swing.JButton analizarT;
    private javax.swing.JButton astBtn;
    private javax.swing.JPanel card;
    private com.compi2.simpascal.interfaz.ConsolePanel consolePanel2;
    private javax.swing.JPanel editor;
    private javax.swing.JButton editorBtn;
    private javax.swing.JPanel erroresP;
    private javax.swing.JButton guardar;
    private javax.swing.JButton guardarc;
    private javax.swing.JPanel menuBar;
    private javax.swing.JPanel menuEditor;
    private com.compi2.simpascal.interfaz.PanelBorder panelBorder1;
    private javax.swing.JPanel simbolos;
    private com.compi2.simpascal.interfaz.TabPanel tabPanel2;
    private com.compi2.simpascal.interfaz.TablaErrores tablaErrores1;
    private com.compi2.simpascal.interfaz.TablaSimbolos tablaSimbolos1;
    private com.compi2.simpascal.interfaz.TablaTipos tablaTipos1;
    private javax.swing.JButton tablaeBtn;
    private javax.swing.JButton tablasBtn;
    private javax.swing.JButton tablatBtn;
    private javax.swing.JPanel tipos;
    private com.compi2.simpascal.interfaz.TitleBar titleBar1;
    private javax.swing.JLabel tituloConsola;
    // End of variables declaration//GEN-END:variables
}
