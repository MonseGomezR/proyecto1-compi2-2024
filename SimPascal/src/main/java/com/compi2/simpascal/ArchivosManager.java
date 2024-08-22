package com.compi2.simpascal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author mgome
 */

public class ArchivosManager {

    private String nombreArchivo;
    private String contenidoArchivo;

    public String[] cargar() throws FileNotFoundException {

        nombreArchivo = "";
        contenidoArchivo = "";
        File file;

        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            file = jfc.getSelectedFile();
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    contenidoArchivo += sc.nextLine();
                    contenidoArchivo += "\n";
                }
            }

            nombreArchivo = file.getName();
            JOptionPane.showMessageDialog(null, "Archivo cargado con exito");
            String[] archivo = {nombreArchivo, contenidoArchivo};
            return archivo;
        }
        return null;
    }

    public void guardar(String na, String ca) throws IOException {
        nombreArchivo = na;
        contenidoArchivo = ca;

        final JFileChooser jfc = new JFileChooser() {
            @Override
            public void approveSelection() {
                if (getSelectedFile().isFile()) {
                } else {
                    super.approveSelection();
                }
            }
        };
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showSaveDialog(null);

        String ruta = jfc.getSelectedFile().getAbsolutePath();
        File archivo = new File(ruta + "\\\\" + nombreArchivo);
        if (archivo.exists()) {
        } else {
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(contenidoArchivo);
            }
        }
    }
}
