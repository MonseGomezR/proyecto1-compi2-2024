/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class Procedimiento extends Subprograma {

    private String nodeName;
    
    public Procedimiento(String nombre, LinkedList<HashMap> parametros, LinkedList<Instruccion> declaraciones, LinkedList<Instruccion> statements, Tipo tipo, int linea, int columna) {
        super(nombre, parametros, declaraciones, statements, tipo, linea, columna);
        nodeName = "";
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        nodeName = "procedimiento_" + nombre + "_" + arbol.getContador();
        if(!declaraciones.isEmpty()) {
            for (Instruccion declaracion : declaraciones) {
                declaracion.interpretar(arbol, tabla);
            }
        }
        for (Instruccion instruccion : statements) {
            instruccion.interpretar(arbol, tabla);
            instruccion.generarastCP(nodeName);
        }
        return null;
    }

    @Override
    public String generarast() {
        String ast = "start -> procedimientos\nprocedimientos -> procedimientos -> " + nodeName + "\n";
        for (Instruccion instruccion : statements) {
            ast += instruccion.generarastCP(nodeName);
        }
        return ast;
    }

    @Override
    public String generarastCP(String padre) {
        return "";
    }
}
