package com.compi2.simpascal.interfaz;

import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author mgome
 */

public class Tab {
    private String contenidoTextArea;
    private String nombreArchivo;
    private JButton botonAsignado;
    private File direccion;

    public Tab() {
        this("", "", null);
    }
    
    public Tab(String contenidoTextArea, String nombreArchivo, JButton botonAsignado) {
        this.contenidoTextArea = contenidoTextArea;
        this.nombreArchivo = nombreArchivo;
        this.botonAsignado = botonAsignado;
    }

    public String getContenidoTextArea() {
        return contenidoTextArea;
    }

    public void setContenidoTextArea(String contenidoTextArea) {
        this.contenidoTextArea = contenidoTextArea;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public JButton getBotonAsignado() {
        return botonAsignado;
    }

    public void setBotonAsignado(JButton botonAsignado) {
        this.botonAsignado = botonAsignado;
    }
    
    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }
    
    
}
