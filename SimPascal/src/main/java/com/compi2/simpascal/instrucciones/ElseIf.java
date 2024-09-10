package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class ElseIf extends Instruccion {

    private final Instruccion condicion;
    private final LinkedList<Instruccion> instrucciones;

    public ElseIf(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getDato() != Dato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida", this.linea, this.columna);
        }

        if ((boolean) cond) {
            var newTabla = new Tabla(tabla);
            for (var i : this.instrucciones) {
                var resultado = i.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    return resultado;
                }

                /*if (resultado instanceof Break) {
                    return resultado;
                }*/
                if (resultado != null) {
                    return resultado;
                }

                return true;
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String labels = padre + "_condicion[label=\"condicion\"]\n"
                + padre + "_statements[label=\"statements\"]\n";
        String ast = padre + " -> " + padre + "_condicion\n"
                + padre + " -> " + padre + "_statements\n";
        ast += condicion.generarastCP(padre + "_condicion", arbol);
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(padre + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = condicion.generarAA(padre, arbol, tabla);
        for (var instruccion: instrucciones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;
    }
}
