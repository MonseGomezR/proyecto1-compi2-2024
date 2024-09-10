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
        var asigFor = this.asignacion.interpretar(arbol, tabla);
        var topeFor = this.tope.interpretar(arbol, tabla);

        if (varFor.getTipo().getDato() != Dato.ENTERO || tope.tipo.getDato() != Dato.ENTERO || (int) varFor.getValor() > (int) topeFor) {
            return new Errores("a", "a", linea, columna);
        }

        int varInt = (int) asigFor;
        int topeInt = (int) topeFor;
        varFor.setValor(varInt);

        while (varInt != topeInt) {
            for (var instruccion : this.instrucciones) {
                var resultado = instruccion.interpretar(arbol, tabla);
                if (resultado instanceof Errores) {
                    return resultado;
                }
                /*if (resultado instanceof Break || resultado instanceof Continue) {
                    if (resultado instanceof Break) {
                        hayBreak = true;
                        break;
                    } else if (resultado instanceof Continue) {
                        break;
                    }
                }*/
            }
            if (hayBreak) {
                break;
            }

            varInt++;
            varFor.setValor(varInt);
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
