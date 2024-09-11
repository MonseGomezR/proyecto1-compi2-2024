package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class For extends Instruccion {

    private final String variable;
    private final Instruccion tope;
    private final Instruccion asignacion;
    private final LinkedList<Instruccion> instrucciones;
    private boolean hayBreak;

    public For(String variable, Instruccion asignacion, Instruccion tope, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.variable = variable;
        this.asignacion = asignacion;
        this.tope = tope;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        hayBreak = false;

        var varFor = tabla.getVariable(variable);

        if (varFor == null) {
            arbol.errores.add(new Errores("Semantico", "La variable " + variable + " no existe.", linea, columna));
        } else {
            var asigFor = this.asignacion.interpretar(arbol, tabla);
            var topeFor = this.tope.interpretar(arbol, tabla);

            if (asigFor == null || topeFor == null) {
                arbol.errores.add(new Errores("Semantico", "El rango de For es invalido.", linea, columna));
            } else {
                if (varFor.getTipo().getDato() != Dato.ENTERO || tope.tipo.getDato() != Dato.ENTERO || (int) asigFor > (int) topeFor) {
                    arbol.errores.add(new Errores("a", "a", linea, columna));
                }
                
                for (var instruccion : this.instrucciones) {
                    instruccion.interpretar(arbol, tabla);
                }
            }

        }

        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "for_" + arbol.getContador();
        String labels = nodeName + "[label=\"for\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = "start -> statements\nstatements -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "for_" + arbol.getContador();
        String labels = nodeName + "[label=\"for\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }

        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        for (var instruccion : this.instrucciones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return "";
    }

}
