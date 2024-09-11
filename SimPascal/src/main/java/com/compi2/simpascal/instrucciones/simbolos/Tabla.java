package com.compi2.simpascal.instrucciones.simbolos;

import com.compi2.simpascal.instrucciones.Funcion;
import com.compi2.simpascal.instrucciones.Procedimiento;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class Tabla {

    private Tabla tablaPadre;
    private LinkedList<Tabla> tablasHijas;
    private HashMap<String, Object> tablaActual;
    private LinkedList<Simbolo> simbolos;
    public LinkedList<String> typeNames;
    private HashMap<String, Funcion> funciones;
    private HashMap<String, Procedimiento> procedimientos;

    private String nombre;

    public Tabla() {
        this.tablaActual = new HashMap<>();
        this.tablasHijas = new LinkedList<>();
        this.simbolos = new LinkedList<>();
        this.typeNames = new LinkedList<>();
        this.funciones = new HashMap<>();
        this.procedimientos = new HashMap<>();
        this.nombre = "";
    }

    public Tabla(Tabla tablaPadre) {
        this.tablaPadre = tablaPadre;
        this.tablaActual = new HashMap<>();
        this.funciones = new HashMap<>();
        this.procedimientos = new HashMap<>();
        this.simbolos = new LinkedList<>();
        this.nombre = "";
    }

    public LinkedList<Tabla> getTablasHijas() {
        return tablasHijas;
    }

    public void setTablasHijas(LinkedList<Tabla> tablasHijas) {
        this.tablasHijas = tablasHijas;
    }
    
    public void addTablaHija(Tabla tabla) {
        this.tablasHijas.add(tabla);
    }

    public Tabla getTablaPadre() {
        return this.tablaPadre;
    }

    public void setTablaPadre(Tabla tablaPadre) {
        this.tablaPadre = tablaPadre;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setVariable(Simbolo simbolo) {
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            this.simbolos.add(simbolo);
            return true;
        }
        return false;
    }

    public Simbolo getVariable(String id) {
        for (Tabla i = this; i != null; i = i.getTablaPadre()) {
            Simbolo busqueda = (Simbolo) i.tablaActual.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }
    

    public Funcion getFuncion(String id) {
        for (Tabla i = this; i != null; i = i.getTablaPadre()) {
            Funcion busqueda = i.funciones.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public boolean addFuncion(Funcion funcion) {
        if (this.funciones.containsKey(funcion.nombre)) {
            return false;
        }
        this.funciones.put(funcion.nombre, funcion);
        return true;
    }
    
    public Procedimiento getProcedimiento(String id) {
        for (Tabla i = this; i != null; i = i.getTablaPadre()) {
            Procedimiento busqueda = i.procedimientos.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public boolean addProcedimiento(Procedimiento procedimiento) {
        if (this.procedimientos.containsKey(procedimiento.nombre)) {
            return false;
        }
        this.procedimientos.put(procedimiento.nombre, procedimiento);
        return true;
    }

    public LinkedList<Simbolo> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(LinkedList<Simbolo> simbolos) {
        this.simbolos = simbolos;
    }
    
    
}
