/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class While extends Instruccion {

    private final Instruccion condicion;
    private final LinkedList<Instruccion> instrucciones;
    private boolean hayBreak;

    public While(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        hayBreak = false;

        var cond = this.condicion.interpretar(arbol, tabla);
        
        if (cond == null || this.condicion.tipo.getDato() != Dato.BOOLEANO) {
            arbol.errores.add(new Errores("Semantico", "While: La condicion debe devolver un valor booleano.", this.linea, this.columna));
        }


        for (Instruccion instruccion : instrucciones) {
            instruccion.interpretar(arbol, tabla);
        }

        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "while" + arbol.getContador();
        String labels = nodeName + "[label=while]\n"
                + nodeName + "_condicion[label=\"condicion\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = "start -> statements\nstatements -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_condicion\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        ast += condicion.generarastCP(nodeName + "_condicion", arbol);
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "while" + arbol.getContador();
        String labels = nodeName + "[label=while]\n"
                + nodeName + "_condicion[label=\"condicion\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_condicion\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        ast += condicion.generarastCP(nodeName + "_condicion", arbol);
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        aa += condicion.generarAA(padre, arbol, tabla);
        for (var instruccion : instrucciones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;

    }
}
