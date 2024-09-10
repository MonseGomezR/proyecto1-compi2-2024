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
    
    public Procedimiento(String nombre, LinkedList<HashMap> parametros, LinkedList<Instruccion> declaraciones, LinkedList<Instruccion> statements, Tipo tipo, int linea, int columna) {
        super(nombre, parametros, declaraciones, statements, tipo, linea, columna);
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        
        if(!declaraciones.isEmpty()) {
            for (Instruccion declaracion : declaraciones) {
                declaracion.interpretar(arbol, tabla);
            }
        }
        for (Instruccion instruccion : statements) {
            instruccion.interpretar(arbol, tabla);
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "procedimiento_" + nombre;
        String labels = nodeName + "[label=\"procedure " + nombre + "\"]\n"
                + nodeName + "_parametros[label=parametros]\n"
                + nodeName + "_declaraciones[label=declaraciones]\n"
                + nodeName + "_statements[label=statements]\n";

        String ast = "start -> procedimientos\nprocedimientos -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_parametros\n"
                + nodeName + " -> " + nodeName + "_declaraciones\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        
        for (int i = 0; i < this.parametros.size(); i++) {
            var id = parametros.get(i).get("id");
            var tipoP = (Tipo) parametros.get(i).get("tipo");
            
            labels += nodeName + "_parametro_id[label=\"" + id.toString() + "\"]\n"
                    + nodeName + "_parametro_tipo[label=\"" + tipoP.getDato().name() + "\"]\n";
            ast += nodeName + "_parametros -> " + nodeName + "_parametro_id\n";
            
        }

        for (Instruccion declaracion : declaraciones) {
            ast += declaracion.generarastCP(nodeName + "_declaraciones", arbol);
        }

        for (Instruccion instruccion : statements) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        return "";
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        for (Instruccion instruccion : statements) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;
    }
    
}
