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
public class WriteLn extends Instruccion {

    private final LinkedList<Instruccion> expresiones;

    public WriteLn(LinkedList<Instruccion> expresiones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        for (Instruccion expresion : expresiones) {
            var resultado = expresion.interpretar(arbol, tabla);
            if (resultado != null) {
                if (resultado instanceof Errores e) {
                    arbol.errores.add(e);
                }
            }
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "writeln" + arbol.getContador();
        String labels = nodeName + "[label=writeln]\n";
        String ast = "start -> statements\nstatements->" + nodeName + "\n";

        for (Instruccion expresion : expresiones) {
            ast += expresion.generarastCP(nodeName, arbol);
        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "writeln" + arbol.getContador();
        String labels = nodeName + "[label=writeln]\n";
        String ast = padre + " -> " + nodeName + "\n";

        for (Instruccion expresion : expresiones) {
            ast += expresion.generarastCP(nodeName, arbol);
        }

        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        for (Instruccion instruccion : expresiones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;
    }
}
