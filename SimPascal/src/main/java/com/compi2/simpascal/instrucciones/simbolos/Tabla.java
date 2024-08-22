package com.compi2.simpascal.instrucciones.simbolos;

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

    private String nombre;

    public Tabla() {
        this.tablaActual = new HashMap<>();
        this.tablasSiguientes = new LinkedList<>();
        this.nombre = "";
    }

    public Tabla(Tabla tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
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
            /*Lista busqueda2 = this.listasActuales.get(simbolo.getId().toLowerCase());
            if (busqueda2 == null) {*/
                this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
                return true;
           // }
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

    /*public boolean setMetodo(Metodo metodo) {
        Metodo busqueda = this.metodosActuales.get(metodo.id.toLowerCase());
        if (busqueda == null) {
            this.metodosActuales.put(metodo.id.toLowerCase(), metodo);
            return true;
        }
        return false;
    }

    public Metodo getMetodo(String id) {
        for (TablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            Metodo busqueda = i.metodosActuales.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public boolean addList(Lista lista) {
        Lista busqueda = this.listasActuales.get(lista.getId().toLowerCase());
        Simbolo b = this.getVariable(lista.getId());
        if (b == null) {
            if (busqueda == null) {
                this.listasActuales.put(lista.getId().toLowerCase(), lista);
                return true;
            }
        }
        return false;
    }

    public Lista getList(String id) {
        for (TablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            Lista busqueda = i.listasActuales.get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }

    public boolean setStruct(String identificador, Struct struct) {
        if (this.listaStructs.containsKey(struct.getNombre().toLowerCase())) {
            return false;
        }
        this.listaStructs.put(struct.getNombre().toLowerCase(), struct);
        return true;
    }

    public Struct getStruct(String identificador) {
        for (TablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            Struct busqueda = i.listaStructs.get(identificador.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }*/
}
