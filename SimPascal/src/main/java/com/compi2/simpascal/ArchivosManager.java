package com.compi2.simpascal;

import com.compi2.simpascal.interfaz.Tab;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mgome
 */
public class ArchivosManager {

    private String nombreArchivo;
    private String contenidoArchivo;
    public File ultimoFile;

    public String[] cargar() throws FileNotFoundException {
        nombreArchivo = "";
        contenidoArchivo = "";
        File file;

        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            file = jfc.getSelectedFile();
            ultimoFile = file;
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

    public void guardar(JFrame frame, File archivo, String contenido) {
        if (archivo == null) {
            guardarComo(frame, contenido, archivo);
        } else {
            escribirArchivo(frame, archivo, contenido);
        }
    }
    
    public void guardarComo(JFrame frame, String contenido, File nombre) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        fileChooser.setSelectedFile(nombre);
        
        int userSelection = fileChooser.showSaveDialog(frame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File archivoActual = fileChooser.getSelectedFile(); 
            
            if (archivoActual.exists()) {
                int overwriteOption = JOptionPane.showConfirmDialog(
                        frame,
                        "El archivo ya existe. ¿Deseas sobrescribirlo?",
                        "Confirmar sobrescritura",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                
                if (overwriteOption != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            escribirArchivo(frame, archivoActual, contenido);
        }
    }
    
    private void escribirArchivo(JFrame frame, File file, String contenido) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(contenido);
            writer.flush();
            JOptionPane.showMessageDialog(frame, "Archivo guardado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
