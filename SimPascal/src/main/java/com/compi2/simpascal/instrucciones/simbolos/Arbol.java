package com.compi2.simpascal.instrucciones.simbolos;

import com.compi2.simpascal.instrucciones.Errores;
import com.compi2.simpascal.instrucciones.Funcion;
import com.compi2.simpascal.instrucciones.Instruccion;
import com.compi2.simpascal.instrucciones.Procedimiento;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author mgome
 */
public class Arbol {

    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private Tabla tablaGlobal;
    public LinkedList<Errores> errores;
    private LinkedList<Funcion> funciones;
    private LinkedList<Procedimiento> procedimientos;
    private final Map<String, Tipo> types;
    public int contador;
    public int contadorAA;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new Tabla();
        this.errores = new LinkedList<>();
        this.funciones = new LinkedList<>();
        this.procedimientos = new LinkedList<>();
        this.types = new HashMap<>();
        this.contador = 0;
        this.contadorAA = 0;
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

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public LinkedList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Funcion> funciones) {
        this.funciones = funciones;
    }

    public void addFunciones(Funcion funcion) {
        this.funciones.add(funcion);
    }

    public Funcion getFuncion(String id) {
        for (var f : this.funciones) {
            if (f.nombre.equalsIgnoreCase(id)) {
                return f;
            }
        }
        return null;
    }
    
    public LinkedList<Procedimiento> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(LinkedList<Procedimiento> procedimientos) {
        this.procedimientos = procedimientos;
    }

    public void addProcedimiento(Procedimiento procedimiento) {
        this.procedimientos.add(procedimiento);
    }

    public Procedimiento getProcedimiento(String id) {
        for (var p : this.procedimientos) {
            if (p.nombre.equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public int getContador() {
        this.contador++;
        return this.contador;

    }
    
    public int getContadorAA() {
        this.contadorAA++;
        return this.contadorAA;

    }

    public Map<String, Tipo> getTypes() {
        return types;
    }
    
    public boolean hasType (String id) {
        return types.containsKey(id);
    }
    
    
}
