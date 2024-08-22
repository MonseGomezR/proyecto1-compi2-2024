package com.compi2.simpascal.interfaz;

//import com.compi1.javacraft.Simbolo;
//import com.compi1.javacraft.TablaSimbolos;
import java.awt.BorderLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mgome
 */
public class TablaSimbolosPanel extends javax.swing.JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public TablaSimbolosPanel() {
        initComponents();
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Nombre", "Tipo", "Valor", "Linea", "Columna"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /*public void actualizarTabla(TablaSimbolos tablaSimbolos) {
        tableModel.setRowCount(0);

        HashMap<String, Object> tablaActual = tablaSimbolos.getTablaActual();
        for (String nombre : tablaActual.keySet()) {
            Simbolo simbolo = (Simbolo) tablaActual.get(nombre);
            String tipo = simbolo.getTipo().getTipo().toString();
            String valorS = " ";
            
            if(simbolo.getValor() instanceof Object[] vector) {
                valorS += Arrays.toString(vector);
                tableModel.addRow(new Object[]{nombre, tipo, valorS});
            } else {
                var valor = simbolo.getValor().toString();
                tableModel.addRow(new Object[]{nombre, tipo, valor});
            }
        }
    }
    
    public boolean isArrayOfType(Object obj, Class<?> clazz) {
        if (obj == null) {
            return false;
        }

        return obj.getClass().isArray() && obj.getClass().getComponentType().equals(clazz);
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
