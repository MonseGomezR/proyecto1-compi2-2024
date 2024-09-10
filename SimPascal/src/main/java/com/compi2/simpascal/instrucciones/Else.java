package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class Else extends Instruccion {

    private LinkedList<Instruccion> instrucciones;

    public Else(LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var newTabla = new Tabla(tabla);
        for (var i : this.instrucciones) {
            var resultado = i.interpretar(arbol, newTabla);
            /*if (resultado instanceof Break) {
                return resultado;
            }*//*
            if (i instanceof Return) {
                return resultado;
            }*/
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String labels = padre + "_statements[label=\"statements\"]\n";
        String ast = padre + " -> " + padre + "_statements\n";
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(padre + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        for (var instruccion : instrucciones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;
    }
}
