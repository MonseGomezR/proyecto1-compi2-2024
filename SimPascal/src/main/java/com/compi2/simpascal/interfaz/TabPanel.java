package com.compi2.simpascal.interfaz;

//import com.compi1.javacraft.archivos.ArchivoManager;
import com.compi2.simpascal.ArchivosManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author mgome
 */
public class TabPanel extends javax.swing.JPanel {

    public static Color estadoNormal = new Color(18, 175, 177);
    public static Color seleccionado = new Color(50, 90, 90);
    public static Color estadoHover = new Color(0, 142, 144);
    public static Color estadoClick = new Color(227, 102, 121);

    public List<Tab> archivosAbiertos;
    private TabButton anterior;
    public TabButton actual;

    public TabPanel() {
        archivosAbiertos = new ArrayList<>();

        initComponents();

        TabButton tb = new TabButton("prueba1.jc");
        archivosAbiertos.add(new Tab("Este es un archivo de prueba", "archivoDePrueba.jc", tb.getMainButton()));

        tb.getMainButton().addActionListener((ActionEvent evt) -> {
            tabSeleccionado(tb);
        });

        tb.getCloseButton().addActionListener((ActionEvent evt) -> {
            tabCerrado(tb);
        });

        anterior = actual;
        actual = tb;

        actual.setBorder(new LineBorder(seleccionado, 2, true));
        actual.setBackground(seleccionado);
        actual.getMainButton().setForeground(Color.white);
        actual.getCloseButton().setForeground(Color.white);
        actual.colorSeleccionado = true;

        textAreaPanel1.textArea.setText(archivosAbiertos.get(0).getContenidoTextArea());

        internalPanel.add(tb, 0);

        nuevoArchivo.setBackground(estadoNormal);

        setBackground(new Color(0, 0, 0, 0));
        internalPanel.setOpaque(false);
        internalPanel.setBackground(new Color(0, 0, 0, 0));

        scrollPane.setOpaque(false);
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.getViewport().setOpaque(false);

        scrollPane.getViewport().setBorder(null);
        scrollPane.setViewportBorder(null);
        setOpaque(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        internalPanel = new javax.swing.JPanel();
        nuevoArchivo = new javax.swing.JButton();
        textAreaPanel1 = new com.compi2.simpascal.interfaz.TextAreaPanel();

        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        internalPanel.setBackground(new java.awt.Color(0, 0, 0));
        internalPanel.setOpaque(false);
        internalPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        nuevoArchivo.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        nuevoArchivo.setForeground(new java.awt.Color(255, 255, 255));
        nuevoArchivo.setText("+");
        nuevoArchivo.setBorder(null);
        nuevoArchivo.setBorderPainted(false);
        nuevoArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevoArchivo.setFocusPainted(false);
        nuevoArchivo.setPreferredSize(new java.awt.Dimension(30, 30));
        nuevoArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuevoArchivoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuevoArchivoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nuevoArchivoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nuevoArchivoMouseReleased(evt);
            }
        });
        nuevoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoArchivoActionPerformed(evt);
            }
        });
        internalPanel.add(nuevoArchivo);

        scrollPane.setViewportView(internalPanel);

        textAreaPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 175, 177), 3, true));
        textAreaPanel1.setPreferredSize(new java.awt.Dimension(1000, 360));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(textAreaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(textAreaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoArchivoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoArchivoMouseEntered
        nuevoArchivo.setBackground(estadoHover);
    }//GEN-LAST:event_nuevoArchivoMouseEntered

    private void nuevoArchivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoArchivoMouseExited
        nuevoArchivo.setBackground(estadoNormal);
    }//GEN-LAST:event_nuevoArchivoMouseExited

    private void nuevoArchivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoArchivoMousePressed
        nuevoArchivo.setBackground(estadoClick);
    }//GEN-LAST:event_nuevoArchivoMousePressed

    private void nuevoArchivoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoArchivoMouseReleased
        nuevoArchivo.setBackground(estadoNormal);
    }//GEN-LAST:event_nuevoArchivoMouseReleased

    private void nuevoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoArchivoActionPerformed
        try {
            agregarNuevoArchivo();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nuevoArchivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel internalPanel;
    private javax.swing.JButton nuevoArchivo;
    private javax.swing.JScrollPane scrollPane;
    public com.compi2.simpascal.interfaz.TextAreaPanel textAreaPanel1;
    // End of variables declaration//GEN-END:variables

    public void agregarNuevoArchivo() throws FileNotFoundException {
        ArchivosManager cr;
        String fileName = "";
        Tab newTab = new Tab();
        boolean cancelar = false;
        String[] options = {"Crear", "Abrir"};

        int selection = JOptionPane.showOptionDialog(null, "Crear archivo o abrir uno existente?", "Sim-Pascal", 0, 2, null, options, options[0]);

        for (Tab archivosAbierto : archivosAbiertos) {
            if (archivosAbierto.getBotonAsignado() == actual.getMainButton()) {
                archivosAbierto.setContenidoTextArea(textAreaPanel1.textArea.getText());
                break;
            }
        }

        switch (selection) {
            case 0 -> {
                fileName = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                try {
                    if (!fileName.equals("")) {
                        fileName += ".pas";
                        for (Tab archivosAbierto : archivosAbiertos) {
                            if (archivosAbierto.getBotonAsignado() == actual.getMainButton()) {
                                archivosAbierto.setContenidoTextArea(textAreaPanel1.textArea.getText());
                                break;
                            }
                        }
                        newTab.setContenidoTextArea("");
                    } else {
                        cancelar = true;
                    }
                } catch (Exception e) {
                    cancelar = true;
                }
            }
            case 1 -> {
                cr = new ArchivosManager();
                String[] archivo = cr.cargar();
                if (archivo != null) {

                    fileName = archivo[0];
                    for (Tab archivoAbierto : archivosAbiertos) {
                        if (archivoAbierto.getNombreArchivo().equals(fileName)) {
                            JOptionPane.showMessageDialog(null, "El archivo ya está abierto");
                            cancelar = true;
                        }
                    }
                    for (Tab archivosAbierto : archivosAbiertos) {
                        if (archivosAbierto.getBotonAsignado() == actual.getMainButton()) {
                            archivosAbierto.setContenidoTextArea(textAreaPanel1.textArea.getText());
                            break;
                        }
                    }
                    newTab.setContenidoTextArea(archivo[1]);

                } else {
                    cancelar = true;
                }
            }
            default ->
                cancelar = true;
        }

        if (!cancelar) {
            if (!textAreaPanel1.textArea.isEditable()) {
                textAreaPanel1.textArea.setEditable(true);
                textAreaPanel1.textArea.setEnabled(true);
            }
            TabButton nuevoBoton = new TabButton(fileName);
            nuevoBoton.setButtonText(fileName);
            newTab.setNombreArchivo(fileName);
            newTab.setBotonAsignado(nuevoBoton.getMainButton());

            nuevoBoton.getMainButton().addActionListener((ActionEvent evt) -> {
                tabSeleccionado(nuevoBoton);
            });

            nuevoBoton.getCloseButton().addActionListener((ActionEvent evt) -> {
                tabCerrado(nuevoBoton);
            });

            tabSeleccionado(nuevoBoton);

            textAreaPanel1.textArea.setText(newTab.getContenidoTextArea());
            if (!archivosAbiertos.isEmpty()) {

                internalPanel.add(nuevoBoton, archivosAbiertos.size());
            } else {

                internalPanel.add(nuevoBoton, 0);
            }
            archivosAbiertos.add(newTab);
            internalPanel.revalidate();
            internalPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Cancelado");
        }
    }

    public void tabSeleccionado(TabButton tabButton) {
        for (int i = 0; i < archivosAbiertos.size(); i++) {
            Tab aux = archivosAbiertos.get(i);
            if (aux.getBotonAsignado() == tabButton.getMainButton()) {
                for (Tab archivosAbierto : archivosAbiertos) {
                    if (archivosAbierto.getBotonAsignado() == actual.getMainButton()) {
                        archivosAbierto.setContenidoTextArea(textAreaPanel1.textArea.getText());
                        break;
                    }
                }
                textAreaPanel1.textArea.setText(aux.getContenidoTextArea());
                break;
            }
        }

        anterior = actual;
        actual = tabButton;

        anterior.setBackground(estadoNormal);
        anterior.setBorder(null);
        anterior.getMainButton().setForeground(Color.white);
        anterior.getCloseButton().setForeground(Color.white);
        anterior.colorSeleccionado = false;

        actual.setBorder(new LineBorder(seleccionado, 2, true));
        actual.setBackground(seleccionado);
        actual.getMainButton().setForeground(Color.white);
        actual.getCloseButton().setForeground(Color.white);
        actual.colorSeleccionado = true;
    }

    public void tabCerrado(TabButton tabButton) {
        if (tabButton == actual) {
            for (int i = 0; i < archivosAbiertos.size(); i++) {
                Tab aux = archivosAbiertos.get(i);
                if (aux.getBotonAsignado() == tabButton.getMainButton()) {
                    archivosAbiertos.remove(i);

                    if (anterior != null) {
                        actual = anterior;
                        actual.setBorder(new LineBorder(seleccionado, 2, true));
                        actual.setBackground(seleccionado);
                        actual.getMainButton().setForeground(Color.white);
                        actual.getCloseButton().setForeground(Color.white);
                        actual.colorSeleccionado = true;

                        for (int j = 0; j < archivosAbiertos.size(); j++) {
                            aux = archivosAbiertos.get(j);
                            if (aux.getBotonAsignado() == actual.getMainButton()) {
                                textAreaPanel1.textArea.setText(aux.getContenidoTextArea());
                                break;
                            }
                        }
                    } else {
                        textAreaPanel1.textArea.setText("");
                        actual = null;

                    }

                    if (archivosAbiertos.isEmpty()) {
                        textAreaPanel1.textArea.setText("");
                        textAreaPanel1.textArea.setEditable(false);
                        textAreaPanel1.textArea.setEnabled(false);
                    }

                    break;
                }
            }
        } else {
            for (int i = 0; i < archivosAbiertos.size(); i++) {
                Tab aux = archivosAbiertos.get(i);
                if (aux.getBotonAsignado() == tabButton.getMainButton()) {
                    archivosAbiertos.remove(i);
                }
            }
        }
    }
}
