package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class WriteLn extends Instruccion {

    private final LinkedList<Instruccion> expresiones;
    private String ast;
    private String temp;

    public WriteLn(LinkedList<Instruccion> expresiones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        ast = "";
        temp = "writeln" + arbol.getContador();
        for (Instruccion ins : expresiones) {
            var resultado = ins.interpretar(arbol, tabla);
            if (resultado != null) {
                if (resultado instanceof Errores) {
                    return resultado;
                }
                ast += ins.generarastCP(temp);
            }
        }
        return null;
    }

    @Override
    public String generarast() {
        return "start -> statements\n" +
                temp + "[label=writeln]\n" + 
                "statements->" + temp + "\n" + ast;
    }

    @Override
    public String generarastCP(String padre) {
        return padre + " -> statements\n" +
                temp + "[label=writeln]\n" + 
                "statements->" + temp + "\n" + ast;
    }
}
