/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class ReadLn extends Instruccion{

    
    private final LinkedList<String> expresiones;
    
    public ReadLn(LinkedList<String> expresiones, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        for (var expresion : expresiones) {
            var resultado = tabla.getVariable(expresion);
            if(resultado == null) {
                arbol.errores.add(new Errores("Semantico", "El simbolo " + expresion + "no existe.", linea, columna));
            }
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "readln" + arbol.getContador();
        String labels = nodeName + "[label=readln]\n";
        String ast = "start -> statements\nstatements->" + nodeName + "\n";

        for (String expresion : expresiones) {
            labels += nodeName + expresion + "[label=\"" + expresion + "\"]\n";
            ast += nodeName + " -> " + expresion + "\n";
        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "readln" + arbol.getContador();
        String labels = nodeName + "[label=readLn]\n";
        String ast = padre + " -> " + nodeName + "\n";

        for (String expresion : expresiones) {
            labels += nodeName + expresion + "[label=\"" + expresion + "\"]\n";
            ast += nodeName + " -> " + expresion + "\n";
        }

        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return "";
    }
    
}
