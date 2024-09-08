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
    private String ast = "";
    private String nodeName = "";

    public DecConst(LinkedList<Simbolo> constantes, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.constantes = constantes;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        if (constantes.isEmpty()) {
            return new Errores("SEMANTICO", "Declarado bloque de constantes, pero vacío", this.linea, this.columna);
        }
        nodeName = "const_" + arbol.getContador();

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
            contador++;
            ast += nodeName + "[label=const]\n"
                    + "dec_const" + contador + "[label=declaracion]\n"
                    + "dec_const_id" + contador + "[label=ID]\n"
                    + "dec_const_s" + contador + "[label=\"=\"]\n"
                    + "dec_const_t" + contador + "[label=valor]\n"
                    + "dec_const_data" + contador + "[label=\"" + constante.getValor().toString() + "\"]\n"
                    + "dec_const_id_name" + contador + "[label=" + constante.getId() + "]\n";
            ast += nodeName + " -> dec_const" + contador + "\n"
                    + "dec_const" + contador + " -> dec_const_id" + contador + "\n"
                    + "dec_const" + contador + " -> dec_const_s" + contador + "\n"
                    + "dec_const" + contador + " -> dec_const_t" + contador + "\n"
                    + "dec_const_t" + contador + " -> dec_const_data" + contador + "\n"
                    + "dec_const_id" + contador + " -> dec_const_id_name" + contador + "\n\n";
        }

        return null;
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
