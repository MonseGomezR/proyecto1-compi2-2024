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

    private Tabla tablaAnterior;
    private LinkedList<Tabla> tablasSiguientes;
    private HashMap<String, Object> tablaActual;
    private HashMap<String, Funcion> funciones;
    private HashMap<String, Procedimiento> procedimientos;

    private String nombre;

    public Tabla() {
        this.tablaActual = new HashMap<>();
        this.tablasSiguientes = new LinkedList<>();
        this.funciones = new HashMap<>();
        this.procedimientos = new HashMap<>();
        this.nombre = "";
    }

    public Tabla(Tabla tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.funciones = new HashMap<>();
        this.procedimientos = new HashMap<>();
        this.nombre = "";
    }

    public LinkedList<Tabla> getTablasSiguientes() {
        return tablasSiguientes;
    }

    public void setTablasSiguientes(LinkedList<Tabla> tablasSiguientes) {
        this.tablasSiguientes = tablasSiguientes;
    }

    public Tabla getTablaAnterior() {
        return this.tablaAnterior;
    }

    public void setTablaAnterior(Tabla tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
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
            return true;
        }
        return false;
    }

    public Simbolo getVariable(String id) {
        for (Tabla i = this; i != null; i = i.getTablaAnterior()) {
            Simbolo busqueda = (Simbolo) i.tablaActual.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public Funcion getFuncion(String id) {
        for (Tabla i = this; i != null; i = i.getTablaAnterior()) {
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
        for (Tabla i = this; i != null; i = i.getTablaAnterior()) {
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
}
