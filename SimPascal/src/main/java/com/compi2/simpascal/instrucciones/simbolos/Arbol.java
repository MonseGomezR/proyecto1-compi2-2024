package com.compi2.simpascal.instrucciones.simbolos;

import com.compi2.simpascal.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private Tabla tablaGlobal;
    public LinkedList<Error> errores;
    private LinkedList<Instruccion> funciones;
    public int contador;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new Tabla();
        this.errores = new LinkedList<>();
        this.funciones = new LinkedList<>();
        this.contador = 0;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public Tabla getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(Tabla tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Error> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Error> errores) {
        this.errores = errores;
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }

    public void addFunciones(Instruccion funcion) {
        this.funciones.add(funcion);
    }

    /*public Instruccion getFuncion(String id) {
        for (var i : this.funciones) {
            if (i instanceof Metodo metodo) {
                if (metodo.id.equalsIgnoreCase(id)) {
                    return i;
                }
            }
        }
        return null;
    }*/

    public int getContador() {
        this.contador++;
        return this.contador;

    }
}
