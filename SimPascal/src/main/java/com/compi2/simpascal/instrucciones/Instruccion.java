package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.*;


/**
 *
 * @author mgome
 */
public abstract class Instruccion {

    public Tipo tipo;
    public int linea;
    public int columna;

    public Instruccion(Tipo tipo, int linea, int columna) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public abstract Object interpretar(Arbol arbol, Tabla tabla);
    public abstract String generarast(Arbol arbol);
    public abstract String generarastCP(String padre, Arbol arbol);
    public abstract String generarAA(String padre, Arbol arbol, Tabla tabla);

}