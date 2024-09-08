package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;

/**
 *
 * @author mgome
 */
public class AsignacionVar extends Instruccion {

    public String id;
    public Instruccion exp;
    private Instruccion index;

    public AsignacionVar(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    public AsignacionVar(String id, Instruccion exp, Instruccion index, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
        this.index = index;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var variable = tabla.getVariable(id);

        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no exitente", this.linea, this.columna);
        }
        if (!variable.getMutable()) {
            return new Errores("SEMANTICO", "La variable es constante", this.linea, this.columna);
        }

        var newValor = exp.interpretar(arbol, tabla);

        if (newValor instanceof Errores) {
            return newValor;
        } else if (newValor == null) {
            return new Errores("SEMANTICO.", "Error de Asignacion.", linea, columna);
        }

        var tipoVariable = variable.getTipo().getDato();
        var tipoNewValor = exp.tipo.getDato();

        if (variable.getValor() instanceof char[] variableVector) {
            if (index == null) {
                var vv = (String) newValor;
                if (tipoNewValor.equals(Dato.CADENA)) {
                    if (vv.length() == variableVector.length) {
                        for (int i = 0; i < variableVector.length; i++) {
                            variableVector[i] = vv.charAt(i);
                        }
                    }
                    variable.setValor(variableVector);
                    return null;
                } else {
                    if (tipoVariable != tipoNewValor) {
                        return new Errores("SEMANTICO", "Tipos erroneos en asignacion", this.linea, this.columna);
                    }
                }
            }

            return null;
        } else {

            if (tipoVariable != tipoNewValor) {
                if (tipoVariable == Dato.BOOLEANO && tipoNewValor == Dato.ENTERO) {
                    newValor = !newValor.equals(0);
                } else {
                    return new Errores("SEMANTICO", "Tipos erroneos en asignacion", this.linea, this.columna);
                }
            }
            variable.setValor(newValor);
            return null;
        }
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "asignacion" + arbol.getContador();
        String labels = nodeName + "[label=\"asignacion\"]\n"
                + nodeName + "_id[label=ID]\n"
                + nodeName + "_symbol[label=\":=\"]\n"
                + nodeName + "_value[label=\"NUEVO VALOR\"]\n"
                + nodeName + "_name[label=\"" + this.id + "\"]\n";

        String ast = "start -> statements\nstatements -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_id\n"
                + nodeName + " -> " + nodeName + "_symbol\n"
                + nodeName + " -> " + nodeName + "_value\n"
                + nodeName + "_id -> " + nodeName + "_name\n";
        ast += exp.generarastCP(nodeName + "_value", arbol);

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "asignacion" + arbol.getContador();
        String labels = nodeName + "[label=\"asignacion\"]\n"
                + nodeName + "_id[label=ID]\n"
                + nodeName + "_symbol[label=\":=\"]\n"
                + nodeName + "_value[label=\"NUEVO VALOR\"]\n"
                + nodeName + "_name[label=\"" + this.id + "\"]\n";

        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_id\n"
                + nodeName + " -> " + nodeName + "_symbol\n"
                + nodeName + " -> " + nodeName + "_value\n"
                + nodeName + "_id -> " + nodeName + "_name\n";
        ast += exp.generarastCP(nodeName + "_value", arbol);

        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return exp.generarAA(padre, arbol, tabla);
    }
}
