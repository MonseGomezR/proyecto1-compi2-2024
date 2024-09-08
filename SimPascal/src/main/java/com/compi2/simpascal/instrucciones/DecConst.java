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
            return new Errores("SEMANTICO", "Declarado bloque de constantes, pero vacío", this.linea, this.columna);
        }

        int contador = 0;
        for (Simbolo constante : constantes) {
            if (constante.getValor() == null) {
                return new Errores("SEMANTICO", "Constante no puede ser vacía", this.linea, this.columna);
            }

            var valor = (Instruccion) constante.getValor();
            var valorInterpretado = valor.interpretar(arbol, tabla);

            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }

            constante.setValor(valorInterpretado);

            boolean creacion = tabla.setVariable(constante);

            if (!creacion) {
                return new Errores("SEMANTICO", "Constante ya existente", this.linea + contador, this.columna);
            }
        }

        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "constantes" + arbol.getContador();
        String labels = nodeName + "[label=\"constantes\"]\n";
        String ast = "start -> declaraciones\ndeclaraciones -> " + nodeName + "\n";
        String valorC ;
        String id;
        int i = 0;

        for (Simbolo constante : constantes) {
            id = constante.getId();
            var valor = constante.getValor();
            if(valor instanceof Nativo nat) {
                valorC = nat.valor.toString();
            }else {
                valorC = constante.getValor().toString();
            }
            i++;

            labels += nodeName + "_dec" + i + "[label=\"constante\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID\"]\n"
                    + nodeName + "_dec" + i + "_symbol[label=\"=\"]\n"
                    + nodeName + "_dec" + i + "_value[label=\"VALOR\"]\n"
                    + nodeName + "_dec" + i + "_valueV[label=\"" + valorC + "\"]\n"
                    + nodeName + "_dec" + i + "_name[label=\"" + id + "\"]\n\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_symbol\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_value\n"
                    + nodeName + "_dec" + i + "_value -> " + nodeName + "_dec" + i + "_valueV\n"
                    + nodeName + "_dec" + i + "_id -> " + nodeName + "_dec" + i + "_name\n";
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "constantes" + arbol.getContador();
        String labels = nodeName + "[label=\"constantes\"]\n"
                + padre + "declaraciones[label=declaraciones]\n";
        String ast = padre + " -> " + padre + "declaraciones\n" + padre + "declaraciones -> " + nodeName + "\n";
        String valorC;
        String id;
        int i = 0;

        for (Simbolo constante : constantes) {
            id = constante.getId();
            var valor = constante.getValor();
            if(valor instanceof Nativo nat) {
                valorC = nat.valor.toString();
            }else {
                valorC = constante.getValor().toString();
            }
            i++;

            labels += nodeName + "_dec" + i + "[label=\"constante\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID\"]\n"
                    + nodeName + "_dec" + i + "_symbol[label=\"=\"]\n"
                    + nodeName + "_dec" + i + "_value[label=\"VALOR\"]\n"
                    + nodeName + "_dec" + i + "_valueV[label=\"" + valorC + "\"]\n"
                    + nodeName + "_dec" + i + "_name[label=\"" + id + "\"]\n\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_symbol\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_value\n"
                    + nodeName + "_dec" + i + "_value -> " + nodeName + "_dec" + i + "_valueV\n"
                    + nodeName + "_dec" + i + "_id -> " + nodeName + "_dec" + i + "_name\n";
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
