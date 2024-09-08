package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.operadores.OpLogicos;
import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.Dato;
import com.compi2.simpascal.instrucciones.tipos.Tipo;

/**
 *
 * @author mgome
 */
public class Logico extends Instruccion {

    private Instruccion cL;
    private Instruccion cR;
    private Instruccion cU;
    private final OpLogicos operacion;

    public Logico(Instruccion cL, Instruccion cR, OpLogicos operacion, int linea, int col) {
        super(new Tipo(Dato.BOOLEANO), linea, col);
        this.cL = cL;
        this.cR = cR;
        this.operacion = operacion;
    }

    public Logico(Instruccion cU, OpLogicos operacion, int linea, int col) {
        super(new Tipo(Dato.BOOLEANO), linea, col);
        this.operacion = operacion;
        this.cU = cU;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        Object conIzq = null, conDer = null, unica = null;
        if (this.cU != null) {
            unica = this.cU.interpretar(arbol, tabla);
            if (unica instanceof Errores) {
                return unica;
            }
        } else {
            conIzq = this.cL.interpretar(arbol, tabla);
            if (conIzq instanceof Errores) {
                return conIzq;
            }
            conDer = this.cR.interpretar(arbol, tabla);
            if (conDer instanceof Errores) {
                return conDer;
            }
        }

        return switch (operacion) {
            case OR ->
                (boolean) conIzq || (boolean) conDer;
            case AND ->
                (boolean) conIzq && (boolean) conDer;
            case XOR ->
                (boolean) conIzq ^ (boolean) conDer;
            case NOT ->
                !(boolean) unica;
            default ->
                new Errores("SEMANTICO", "Operador logico invalido", this.linea, this.columna);
        };
    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "op_logic" + arbol.getContador();
        String labels = nodeName + "[label=\"" + operacion.name() + "\"]\n";
        String ast = padre + " -> " + nodeName + "\n";
        if (cU == null) {
            ast += this.cL.generarastCP(nodeName, arbol);
            ast += this.cR.generarastCP(nodeName, arbol);
        } else {
            ast += this.cU.generarastCP(nodeName, arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        if (cU != null) {
            aa += cU.generarAA(padre, arbol, tabla);
        } else {
            aa += cL.generarAA(padre, arbol, tabla);
            aa += cR.generarAA(padre, arbol, tabla);

        }

        return aa;
    }

}
