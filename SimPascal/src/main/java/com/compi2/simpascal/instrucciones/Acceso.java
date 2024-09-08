package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;

/**
 *
 * @author mgome
 */
public class Acceso extends Instruccion {

    private final String id;
    private final Instruccion index;

    public Acceso(String id, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.id = id;
        this.index = null;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        if (index == null) {
            var valor = tabla.getVariable(this.id);
            if (valor == null) {
                return new Errores("SEMANTICO", "La variable no existe", this.linea, this.columna);
            }
            this.tipo.setTipo(valor.getTipo().getDato());

            if (valor.getValor() instanceof char[] valorN) {
                String temp = new String(valorN);
                return temp;
            }

            return valor.getValor();
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {

        String nodeName = "acceso" + arbol.getContador();
        String labels = nodeName + "[label=\"acceso\"]\n"
                + nodeName + "_" + this.id + "[label =\"id:" + this.id + "\"]\n";
        
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_" + this.id  + "\n";

        return labels + ast;

    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return "";
    }

}
