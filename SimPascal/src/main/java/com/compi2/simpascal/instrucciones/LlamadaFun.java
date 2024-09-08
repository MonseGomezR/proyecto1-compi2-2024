/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Simbolo;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class LlamadaFun extends Instruccion {

    private final String id;
    private final LinkedList<Instruccion> parametros;

    public LlamadaFun(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var funcion = arbol.getFuncion(this.id);
        if (funcion == null) {
            return new Errores("SEMANTICO", "La funcion no existente", this.linea, this.columna);
        }

        var tablaLlamada = new Tabla(tabla);
        tablaLlamada.setNombre("funcion_" + this.id + "_" + arbol.getContador());

        if (funcion.parametros.size() != this.parametros.size()) {
            return new Errores("SEMANTICO", "Parametros incorrectos", this.linea, this.columna);
        }

        for (int i = 0; i < this.parametros.size(); i++) {
            var idFP = (String) funcion.parametros.get(i).get("id");
            var valorP = this.parametros.get(i).interpretar(arbol, tabla);
            var tipoFP = (Tipo) funcion.parametros.get(i).get("tipo");
            if (valorP instanceof Errores) {
                return valorP;
            }

            Simbolo s = new Simbolo(tipoFP, idFP, valorP, true);

            var parametro = new DecVar(s, this.linea, this.columna);
            var resultado = parametro.interpretar(arbol, tablaLlamada);

            if (resultado instanceof Errores) {
                return resultado;
            }

            if (s.getTipo().getDato() != this.parametros.get(i).tipo.getDato()) {
                return new Errores("SEMANTICO", "Error en tipo de parametros", this.linea, this.columna);
            }
        }
        Simbolo s = new Simbolo(funcion.tipo, this.id, null, true);
        var paramReturn = new DecVar(s, this.linea, this.columna);
        paramReturn.interpretar(arbol, tablaLlamada);

        var resultadoFuncion = funcion.interpretar(arbol, tablaLlamada);
        this.tipo = funcion.tipo;
        return resultadoFuncion;

    }

    @Override
    public String generarast() {
        return "";
    }

    @Override
    public String generarastCP(String padre) {
        return "";
    }

}
