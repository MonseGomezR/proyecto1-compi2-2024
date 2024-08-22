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

    public Nativo(Object valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.valor = analizarValor(tipo, valor);
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        return this.valor;
    }

    @Override
    public String generarast(Arbol arbol, String anterior) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
}
