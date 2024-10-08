package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Simbolo;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class DecConst extends Instruccion {

    private final LinkedList<Simbolo> constantes;

    public DecConst(LinkedList<Simbolo> constantes, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.constantes = constantes;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        if (constantes.isEmpty()) {
            arbol.errores.add(new Errores("Semantico", "Bloque de constantes vacio.", this.linea, this.columna));
        } else {
            int contador = 0;
            for (Simbolo constante : constantes) {
                if (constante.getValor() == null) {
                    arbol.errores.add(new Errores("Semantico", "No se puede declarar una constante vacia.", this.linea, this.columna));
                } else {
                    var valor = (Instruccion) constante.getValor();
                    var valorInterpretado = valor.interpretar(arbol, tabla);

                    if (valorInterpretado != null) {
                        constante.setValor(valorInterpretado);

                        boolean creacion = tabla.setVariable(constante);

                        if (!creacion) {
                            arbol.errores.add(new Errores("Semantico", "La constante ya existe.", this.linea + contador, this.columna));
                        }else {
                            constante.setCategoria("Constante");
                            constante.setAmbito(tabla.getNombre());
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "constantes" + arbol.getContador();
        String labels = nodeName + "[label=\"constantes\"]\n";
        String ast = "start -> declaraciones\ndeclaraciones -> declaracionesN\n declaracionesN -> " + nodeName + "\n";
        String valorC;
        String id;
        int i = 0;

        for (Simbolo constante : constantes) {
            id = constante.getId();
            var valor = constante.getValor();
            if (valor instanceof Nativo nat) {
                valorC = nat.valor.toString();
            } else {
                valorC = constante.getValor().toString();
            }
            i++;

            labels += nodeName + "_dec" + i + "[label=\"constante\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "_dec" + i + "_value[label=\"VALOR: " + valorC + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_value\n";
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "constantes" + arbol.getContador();
        String labels = nodeName + "[label=\"constantes\"]\n"
                + nodeName + "declaraciones[label=declaraciones]\n";
        String ast = padre + " -> " + nodeName + "declaraciones\n"
                + nodeName + "declaraciones -> " + nodeName + "\n";
        String valorC;
        String id;
        int i = 0;

        for (Simbolo constante : constantes) {
            id = constante.getId();
            var valor = constante.getValor();
            if (valor instanceof Nativo nat) {
                valorC = nat.valor.toString();
            } else {
                valorC = constante.getValor().toString();
            }
            i++;

            labels += nodeName + "_dec" + i + "[label=\"constante\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "_dec" + i + "_value[label=\"VALOR: " + valorC + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_value\n";
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return "";
    }
}
