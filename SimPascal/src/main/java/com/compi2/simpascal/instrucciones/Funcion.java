package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Simbolo;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class Funcion extends Subprograma {

    private String nodeName;
    private Simbolo retorno;
    private final Tipo tipoRetorno;

    public Funcion(String nombre, Tipo tipoRetorno, LinkedList<HashMap> parametros, LinkedList<Instruccion> declaraciones, LinkedList<Instruccion> cuerpo, int linea, int columna) {
        super(nombre, parametros, declaraciones, cuerpo, tipoRetorno, linea, columna);
        nodeName = "";
        this.tipoRetorno = tipoRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        nodeName = "funcion_" + nombre + "_" + arbol.getContador();

        if (!declaraciones.isEmpty()) {
            for (Instruccion declaracion : declaraciones) {
                declaracion.interpretar(arbol, tabla);
            }
        }

        for (int i = 0; i < statements.size(); i++) {
            var instruccion = statements.get(i);
            if (instruccion != null) {
                var resultado = instruccion.interpretar(arbol, tabla);
                if (resultado instanceof Errores) {
                    return resultado;
                }
            }
        }

        retorno = tabla.getVariable(nombre);

        if (retorno.getTipo() != this.tipoRetorno) {
            return new Errores("Semantico", "Tipo incorrecto", linea, columna);
        }

        if (retorno.getValor() != null) {
            return retorno.getValor();
        } else {
            return "null";
        }

    }

    @Override
    public String generarast() {
        String ast = "start -> funciones\nfunciones -> funciones -> " + nodeName + "\n";
        for (Instruccion declaracion : declaraciones) {
            ast += declaracion.generarastCP(nodeName);

        }
        for (Instruccion instruccion : statements) {
            ast += instruccion.generarastCP(nodeName);

        }
        ast += "return_" + nodeName + "[label=return]\n"
                + "retorno_" + nodeName + "_" + retorno.toString() + "[label=\"" + retorno.toString() + "\"]\n";
        ast += "return_" + nodeName + " -> retorno_" + nodeName + "_" + retorno.toString();
        return ast;
    }

    @Override
    public String generarastCP(String padre) {
        return "";
    }
}
