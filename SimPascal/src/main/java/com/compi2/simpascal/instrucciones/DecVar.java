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
    private String ast = "";
    private String nodeName = "";

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
            nodeName = "var_" + arbol.getContador();
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

                contador++;
                ast += nodeName + "[label=var]\n"
                        + "dec_var" + contador + "[label=declaracion]\n"
                        + "dec_var_id" + contador + "[label=ID]\n"
                        + "dec_var_s" + contador + "[label=\":\"]\n"
                        + "dec_var_t" + contador + "[label=tipo]\n"
                        + "dec_var_data_name" + contador + "[label=" + variable.getTipo().getDato().toString() + "]\n"
                        + "dec_var_id_name" + contador + "[label=" + variable.getId() + "]\n";

                ast += nodeName + " -> dec_var" + contador + "\n"
                        + "dec_var" + contador + " -> dec_var_id" + contador + "\n"
                        + "dec_var" + contador + " -> dec_var_s" + contador + "\n"
                        + "dec_var" + contador + " -> dec_var_t" + contador + "\n"
                        + "dec_var_t" + contador + " -> dec_var_data_name" + contador + "\n"
                        + "dec_var_id" + contador + " -> dec_var_id_name" + contador + "\n\n";
            }
            return null;
        } else {
            nodeName = "var_" + arbol.getContador();
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

            contador++;
            ast += nodeName + "[label=var]\n"
                    + "dec_var" + contador + "[label=declaracion]\n"
                    + "dec_var_id" + contador + "[label=ID]\n"
                    + "dec_var_s" + contador + "[label=\":\"]\n"
                    + "dec_var_t" + contador + "[label=tipo]\n"
                    + "dec_var_data_name" + contador + "[label=" + variableU.getTipo().getDato().toString() + "]\n"
                    + "dec_var_id_name" + contador + "[label=" + variableU.getId() + "]\n";

            ast += nodeName + " -> dec_var" + contador + "\n"
                    + "dec_var" + contador + " -> dec_var_id" + contador + "\n"
                    + "dec_var" + contador + " -> dec_var_s" + contador + "\n"
                    + "dec_var" + contador + " -> dec_var_t" + contador + "\n"
                    + "dec_var_t" + contador + " -> dec_var_data_name" + contador + "\n"
                    + "dec_var_id" + contador + " -> dec_var_id_name" + contador + "\n\n";
            return null;
        }

    }

    @Override
    public String generarast() {
        return "start -> declaraciones\ndeclaraciones -> " + nodeName + "\n" + ast;
    }

    @Override
    public String generarastCP(String padre) {
        return padre + " -> " + nodeName + "\n" + ast;
    }

}
