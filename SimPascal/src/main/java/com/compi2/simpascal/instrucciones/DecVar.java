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
                arbol.errores.add(new Errores("SEMANTICO", "Declarado bloque de variables vacio.", this.linea, this.columna));
            } else {
                for (var variable : variables) {
                    if (variable.getValor() != null && variable.getValor() instanceof int[] rango) {
                        if (!(variable.getTipo() instanceof TypeP)) {
                            switch (variable.getTipo().getDato()) {
                                case ENTERO -> {
                                    variable.setValor(new int[rango[1]]);
                                    if (variable.getCategoria() == null) {
                                        variable.setCategoria("Variable");
                                    }
                                    variable.setAmbito(tabla.getNombre());
                                }
                                case DECIMAL -> {
                                    variable.setValor(new double[rango[1]]);
                                    if (variable.getCategoria() == null) {
                                        variable.setCategoria("Variable");
                                    }
                                    variable.setAmbito(tabla.getNombre());
                                }
                                case CARACTER -> {
                                    variable.setValor(new char[rango[1]]);
                                    if (variable.getCategoria() == null) {
                                        variable.setCategoria("Variable");
                                    }
                                    variable.setAmbito(tabla.getNombre());
                                }
                                default -> {
                                    arbol.errores.add(new Errores("SEMANTICO", "Tipo de dato incorrecto.", this.linea, this.columna));
                                }
                            }
                        }

                    } else {
                        if (variable.getTipo() instanceof TypeP type) {
                            if (arbol.hasType(type.getId())) {
                                type.setTipo(arbol.getTypes().get(type.getId()));
                                variable.setTipo(type);
                            }

                            boolean creacion = tabla.setVariable(variable);
                            if (!creacion) {
                                arbol.errores.add(new Errores("SEMANTICO", "La variable ya existe.", this.linea + contador, this.columna));
                            } else {
                                if (variable.getCategoria() == null) {
                                    variable.setCategoria("Variable");
                                }
                                variable.setAmbito(tabla.getNombre());
                            }
                        }
                    }
                }
            }
            return null;
        } else {
            if (variableU.getValor() != null && variableU.getValor() instanceof int[] rango) {
                switch (variableU.getTipo().getDato()) {
                    case ENTERO -> {

                        if (variableU.getCategoria() == null) {
                            variableU.setCategoria("Variable");
                        }
                        variableU.setAmbito(tabla.getNombre());
                        variableU.setValor(new int[rango[1]]);
                    }
                    case DECIMAL -> {
                        if (variableU.getCategoria() == null) {
                            variableU.setCategoria("Variable");
                        }
                        variableU.setAmbito(tabla.getNombre());
                        variableU.setValor(new double[rango[1]]);
                    }
                    case CARACTER -> {
                        if (variableU.getCategoria() == null) {
                            variableU.setCategoria("Variable");
                        }
                        variableU.setAmbito(tabla.getNombre());
                        variableU.setValor(new char[rango[1]]);
                    }
                    default -> {
                        arbol.errores.add(new Errores("SEMANTICO", "Tipo de dato incorrecto.", this.linea, this.columna));
                    }
                }

            }
            boolean creacion = tabla.setVariable(variableU);
            if (!creacion) {
                arbol.errores.add(new Errores("SEMANTICO", "La variable ya existe.", this.linea + contador, this.columna));
            } else {
                if (variableU.getCategoria() == null) {
                    variableU.setCategoria("Variable");
                }
                variableU.setAmbito(tabla.getNombre());
            }

            return null;
        }

    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "variables_" + arbol.getContador();
        String labels = nodeName + "[label=\"variables\"]\n";
        String ast = "start -> declaraciones\ndeclaraciones -> declaracionesN\ndeclaracionesN ->" + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        if (variableU != null) {
            if (variableU.getTipo() instanceof TypeP typ) {
                tipoV = typ.getId();
            } else {
                tipoV = variableU.getTipo().getDato().toString();
            }
            id = variableU.getId();
            labels += nodeName + "U_dec[label=\"variable\"]\n"
                    + nodeName + "U_dec_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "U_dec_type[label=\"TIPO: " + tipoV + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_id\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_type\n";
        } else {
            for (Simbolo variable : variables) {
                if (variable.getTipo() instanceof TypeP typ) {
                    tipoV = typ.getId();
                } else {
                    tipoV = variable.getTipo().getDato().toString();
                }
                id = variable.getId();
                i++;

                labels += nodeName + "_dec" + i + "[label=\"variable\"]\n"
                        + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                        + nodeName + "_dec" + i + "_type[label=\"TIPO: " + tipoV + "\"]\n";
                ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n";
            }

        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "variables_" + arbol.getContador();
        String labels = nodeName + "[label=\"variables\"]\n"
                + nodeName + "declaraciones[label=declaraciones]\n";
        String ast = padre + " -> " + nodeName + "declaraciones\n"
                + nodeName + "declaraciones -> " + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        if (variableU != null) {
            tipoV = variableU.getTipo().getDato().toString();
            id = variableU.getId();
            labels += nodeName + "U_dec[label=\"variable\"]\n"
                    + nodeName + "U_dec_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "U_dec_type[label=\"TIPO: " + tipoV + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_id\n"
                    + nodeName + "U_dec -> " + nodeName + "U_dec_type\n";
        }

        for (Simbolo variable : variables) {
            tipoV = variable.getTipo().getDato().toString();
            id = variable.getId();
            i++;

            labels += nodeName + "_dec" + i + "[label=\"variable\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "_dec" + i + "_type[label=\"TIPO: " + tipoV + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n";
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return "";
    }

}
