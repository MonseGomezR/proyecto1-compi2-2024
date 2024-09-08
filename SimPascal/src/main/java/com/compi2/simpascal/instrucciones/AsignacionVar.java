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
    private String ast, temp;

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
        temp = "asignacion" + arbol.getContador();
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
                    ast = temp + "_id_" + variable.getId() + "[label=ID]\n"
                            + temp + "_simbolo_" + variable.getId() + "[label=\":=\"]\n"
                            + temp + "_vid_" + variable.getId() + "[label=\"" + variable.getId() + "\"]\n"
                            + temp + "_valor_" + variable.getId() + "[label=\"valor\"]\n";
                    ast += temp + " -> " + temp + "_id_" + variable.getId() + "\n"
                            + temp + "_id_" + variable.getId() + " -> " + temp + "_vid_" + variable.getId() + "\n"
                            + temp + " -> " + temp + "_simbolo_" + variable.getId() + "\n"
                            + temp + " -> " + temp + "_valor_" + variable.getId() + "\n";
                    ast += exp.generarastCP(temp + "_valor_" + variable.getId());
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
            ast = temp + "_id_" + variable.getId() + "[label=ID]\n"
                    + temp + "_simbolo_" + variable.getId() + "[label=\":=\"]\n"
                    + temp + "_vid_" + variable.getId() + "[label=\"" + variable.getId() + "\"]\n"
                    + temp + "_valor_" + variable.getId() + "[label=\"valor\"]\n";
            ast += temp + " -> " + temp + "_id_" + variable.getId() + "\n"
                    + temp + "_id_" + variable.getId() + " -> " + temp + "_vid_" + variable.getId() + "\n"
                    + temp + " -> " + temp + "_simbolo_" + variable.getId() + "\n"
                    + temp + " -> " + temp + "_valor_" + variable.getId() + "\n";
            ast += exp.generarastCP(temp + "_valor_" + variable.getId());
            return null;
        }
    }

    @Override
    public String generarast() {
        return "start -> statements\n" + temp + "[label=asignacion]\nstatements -> " + temp + "\n" + ast;
    }

    @Override
    public String generarastCP(String padre) {
        return temp + "[label=asignacion]\n" + padre + " -> " + temp + "\n" + ast;
    }
}
