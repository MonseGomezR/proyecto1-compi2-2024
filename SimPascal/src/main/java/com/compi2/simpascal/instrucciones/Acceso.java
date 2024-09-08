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
    private String ast;
    private String nodeName;
    private final Instruccion index;

    public Acceso(String id, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.id = id;
        this.index = null;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        ast = "";
        if (index == null) {
            var valor = tabla.getVariable(this.id);
            if (valor == null) {
                return new Errores("SEMANTICO", "La variable no existe", this.linea, this.columna);
            }
            this.tipo.setTipo(valor.getTipo().getDato());

            nodeName = "acceso" + arbol.getContador();

            if (valor.getValor() instanceof char[] valorN) {
                String temp = new String(valorN);
                ast += nodeName + " -> acc" + valor.getId() + "\nacc" + valor.getId() + " -> \"" + temp + "\"\n\n";
                return temp;
            }

            ast += nodeName + "_" + valor.getId() + "[label =\"id:" + valor.getId() + "\"]\n";
            ast += nodeName + "_valor[label=\"" + valor.getValor() + "\"]\n";

            ast += nodeName + " -> " + nodeName + "_" + valor.getId() + "\n";
            ast += nodeName + "_" + valor.getId() + " -> " + nodeName + "_valor\n\n";

            return valor.getValor();
        }
        return null;
    }

    @Override
    public String generarast() {
        return "";
    }

    @Override
    public String generarastCP(String padre) {
        if (!"".equals(nodeName)) {
            return nodeName + "[label = acceso]\n" + padre + " -> " + nodeName + "\n" + ast;
        } else {
            return "";
        }

    }

}
