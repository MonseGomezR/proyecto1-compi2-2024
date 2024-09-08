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

    private Simbolo retorno;
    private final Tipo tipoRetorno;

    public Funcion(String nombre, Tipo tipoRetorno, LinkedList<HashMap> parametros, LinkedList<Instruccion> declaraciones, LinkedList<Instruccion> cuerpo, int linea, int columna) {
        super(nombre, parametros, declaraciones, cuerpo, tipoRetorno, linea, columna);
        this.tipoRetorno = tipoRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
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
    public String generarast(Arbol arbol) {
        String nodeName = "funcion_" + nombre;
        String labels = nodeName + "[label=\"function " + nombre + "\"]\n"
                + nodeName + "_parametros[label=parametros]\n"
                + nodeName + "_declaraciones[label=declaraciones]\n"
                + nodeName + "_statements[label=statements]\n"
                + nodeName + "_tipo[label=returnType]\n"
                + nodeName + "_tipoV[label=\"" + this.tipoRetorno.getDato().name() + "\"]\n";

        String ast = "start -> funciones\nfunciones -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_parametros\n"
                + nodeName + " -> " + nodeName + "_declaraciones\n"
                + nodeName + " -> " + nodeName + "_statements\n"
                + nodeName + " -> " + nodeName + "_tipo\n"
                + nodeName + "_tipo -> " + nodeName + "_tipoV\n";

        for (int i = 0; i < parametros.size(); i++) {
            var idP = parametros.get(i).get("id");
            var tipoP = (Tipo) parametros.get(i).get("tipo");

            labels += nodeName + "_parametro" + i + "[label=parametro]\n"
                    + nodeName + "_parametro" + i + "_id[label=\"" + idP.toString() + "\"]\n"
                    + nodeName + "_parametro" + i + "_tipo[label=\"" + tipoP.getDato().name() + "\"]\n";
            ast += nodeName + "_parametros -> " + nodeName + "_parametro" + i + "\n"
                    + nodeName + "_parametro" + i + " -> " + nodeName + "_parametro" + i + "_id\n"
                    + nodeName + "_parametro" + i + " -> " + nodeName + "_parametro" + i + "_tipo\n";
        }

        for (Instruccion declaracion : declaraciones) {
            ast += declaracion.generarastCP(nodeName + "_declaraciones", arbol);
        }

        for (Instruccion instruccion : statements) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        return "";
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        for (Instruccion instruccion : statements) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        return aa;
    }
}
