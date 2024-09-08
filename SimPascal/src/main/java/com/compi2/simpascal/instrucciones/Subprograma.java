package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Subprograma extends Instruccion {
    public String nombre;
    public LinkedList<HashMap> parametros;
    protected LinkedList<Instruccion> statements; 
    protected LinkedList<Instruccion> declaraciones; 

    public Subprograma(String nombre, LinkedList<HashMap> parametros, LinkedList<Instruccion> declaraciones, LinkedList<Instruccion> statements, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.nombre = nombre;
        this.parametros = parametros;
        this.statements = statements;
        this.declaraciones = declaraciones;
    }

    @Override
    public abstract Object interpretar(Arbol arbol, Tabla tabla);

    @Override
    public abstract String generarast(Arbol arbol);

    @Override
    public abstract String generarastCP(String padre, Arbol arbol);
   
}