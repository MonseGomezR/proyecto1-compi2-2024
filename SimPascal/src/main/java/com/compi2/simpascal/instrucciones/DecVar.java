package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;
import static com.compi2.simpascal.instrucciones.tipos.Dato.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class DecVar extends Instruccion {

    private final LinkedList<Simbolo> variables;
    private Simbolo variableU;

    public DecVar(LinkedList<Simbolo> variables, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.variables = variables;
    }

    public DecVar(Simbolo variableU, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.variableU = variableU;
        this.variables = new LinkedList<>();
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        int contador = 0;

        if (variableU == null) {
            if (variables.isEmpty()) {
                return new Errores("SEMANTICO", "Declarado bloque de variables, pero vacío", this.linea, this.columna);
            }

            for (var variable : variables) {
                if (variable.getValor() != null && variable.getValor() instanceof int[] rango) {
                    switch (variable.getTipo().getDato()) {
                        case ENTERO ->
                            variable.setValor(new int[rango[1]]);
                        case DECIMAL ->
                            variable.setValor(new double[rango[1]]);
                        case CARACTER ->
                            variable.setValor(new char[rango[1]]);
                        default -> {
                            return new Errores("SEMANTICO", "Tipo de dato incorrecto", this.linea, this.columna);
                        }
                    }

                }

                boolean creacion = tabla.setVariable(variable);

                if (!creacion) {
                    return new Errores("SEMANTICO", "Variable ya existente", this.linea + contador, this.columna);
                }

            }
            return null;
        } else {
            if (variableU.getValor() != null && variableU.getValor() instanceof int[] rango) {
                switch (variableU.getTipo().getDato()) {
                    case ENTERO ->
                        variableU.setValor(new int[rango[1]]);
                    case DECIMAL ->
                        variableU.setValor(new double[rango[1]]);
                    case CARACTER ->
                        variableU.setValor(new char[rango[1]]);
                    default -> {
                        return new Errores("SEMANTICO", "Tipo de dato incorrecto", this.linea, this.columna);
                    }
                }

            }

            boolean creacion = tabla.setVariable(variableU);

            if (!creacion) {
                return new Errores("SEMANTICO", "Variable ya existente", this.linea + contador, this.columna);
            }

            return null;
        }

    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "variables_" + arbol.getContador();
        String labels = nodeName + "[label=\"variables\"]\n";
        String ast = "start -> declaraciones\ndeclaraciones -> " + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        if (variableU != null) {
            tipoV = variableU.getTipo().getDato().toString();
            id = variableU.getId();
            labels += nodeName + "U_dec[label=\"variable\"]\n"
                    + nodeName + "U_dec_id[label=\"ID\"]\n"
                    + nodeName + "U_dec_symbol[label=\":\"]\n"
                    + nodeName + "U_dec_type[label=\"TIPO\"]\n"
                    + nodeName + "U_dec_value[label=\"" + tipoV + "\"]\n"
                    + nodeName + "U_dec_name[label=\"" + id + "\"]\n\n";
            ast += nodeName + " -> " + nodeName + "_dec\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_id\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_symbol\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_type\n"
                    + nodeName + "U_dec_type -> " + nodeName + "U_dec_value\n"
                    + nodeName + "U_dec_id -> " + nodeName + "U_dec_name\n";
        } else {
            for (Simbolo variable : variables) {
                tipoV = variable.getTipo().getDato().toString();
                id = variable.getId();
                i++;

                labels += nodeName + "_dec" + i + "[label=\"variable\"]\n"
                        + nodeName + "_dec" + i + "_id[label=\"ID\"]\n"
                        + nodeName + "_dec" + i + "_symbol[label=\":\"]\n"
                        + nodeName + "_dec" + i + "_type[label=\"TIPO\"]\n"
                        + nodeName + "_dec" + i + "_value[label=\"" + tipoV + "\"]\n"
                        + nodeName + "_dec" + i + "_name[label=\"" + id + "\"]\n\n";
                ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_symbol\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n"
                        + nodeName + "_dec" + i + "_type -> " + nodeName + "_dec" + i + "_value\n"
                        + nodeName + "_dec" + i + "_id -> " + nodeName + "_dec" + i + "_name\n";
            }

        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "variables_" + arbol.getContador();
        String labels = nodeName + "[label=\"variables\"]\n"
                + padre + "declaraciones[label=declaraciones]\n";
        String ast = padre + " -> " + padre + "declaraciones\n" + padre + "declaraciones -> " + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        if (variableU != null) {
            tipoV = variableU.getTipo().getDato().toString();
            id = variableU.getId();
            labels += nodeName + "U_dec[label=\"variable\"]\n"
                    + nodeName + "U_dec_id[label=\"ID\"]\n"
                    + nodeName + "U_dec_symbol[label=\":\"]\n"
                    + nodeName + "U_dec_type[label=\"TIPO\"]\n"
                    + nodeName + "U_dec_value[label=\"" + tipoV + "\"]\n"
                    + nodeName + "U_dec_name[label=\"" + id + "\"]\n\n";
            ast += nodeName + " -> " + nodeName + "_dec\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_id\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_symbol\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_type\n"
                    + nodeName + "U_dec_type -> " + nodeName + "U_dec_value\n"
                    + nodeName + "U_dec_id -> " + nodeName + "U_dec_name\n";
        }

        for (Simbolo variable : variables) {
            tipoV = variable.getTipo().getDato().toString();
            id = variable.getId();
            i++;

            labels += nodeName + "_dec" + i + "[label=\"variable\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID\"]\n"
                    + nodeName + "_dec" + i + "_symbol[label=\":\"]\n"
                    + nodeName + "_dec" + i + "_type[label=\"TIPO\"]\n"
                    + nodeName + "_dec" + i + "_value[label=\"" + tipoV + "\"]\n"
                    + nodeName + "_dec" + i + "_name[label=\"" + id + "\"]\n\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_symbol\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n"
                    + nodeName + "_dec" + i + "_type -> " + nodeName + "_dec" + i + "_value\n"
                    + nodeName + "_dec" + i + "_id -> " + nodeName + "_dec" + i + "_name\n";
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
