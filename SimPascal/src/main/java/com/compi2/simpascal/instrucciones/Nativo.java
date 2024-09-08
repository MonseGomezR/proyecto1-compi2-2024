package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;

/**
 *
 * @author mgome
 */
public class Nativo extends Instruccion{

    public Object valor;
    private String temp;

    public Nativo(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = analizarValor(tipo, valor);
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        temp = "nativo" + arbol.getContador();
        return this.valor;
    }

    @Override
    public String generarast() {
        return "";
    }
    
    private Object analizarValor(Tipo tipo, Object value) {
        if (tipo.getDato()== Dato.CADENA && value instanceof String) {
            return interpretEscapeSequences((String) value);
        }
        return value;
    }
    
    private String interpretEscapeSequences(String input) {
        return input.replace("\\n", "\n")
                .replace("\\\"", "\"")
                .replace("\\t", "\t")
                .replace("\\'", "'")
                .replace("\\\\", "\\");
    }

    @Override
    public String generarastCP(String padre) {
        return temp + "[label =\"" + valor.toString() + "\"]\n" + padre + " -> " + temp + "\n";
    }
}
