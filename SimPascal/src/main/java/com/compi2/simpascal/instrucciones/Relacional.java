package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.operadores.OpRelacionales;
import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;

/**
 *
 * @author mgome
 */
public class Relacional extends Instruccion {

    private final Instruccion cL;
    private final Instruccion cR;
    private final OpRelacionales operacion;

    public Relacional(Instruccion cL, Instruccion cR, OpRelacionales operacion, int linea, int col) {
        super(new Tipo(Dato.BOOLEANO), linea, col);
        this.cL = cL;
        this.cR = cR;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        if (this.cL == null || this.cR == null) {
            return new Errores("SINTACTICO", "Nulos", linea, columna);
        }

        var valueL = this.cL.interpretar(arbol, tabla);
        var valueR = this.cR.interpretar(arbol, tabla);

        var typeL = this.cL.tipo.getDato();
        var typeR = this.cR.tipo.getDato();

        if (valueL == null) {
            return new Errores("SEMANTICO", "Un elemento es nulo", this.linea, this.columna);
        }
        if (valueR == null) {
            return new Errores("SEMANTICO", "Un elemento es nulo", this.linea, this.columna);
        }

        if (valueL instanceof Errores) {
            return valueL;
        }
        if (valueR instanceof Errores) {
            return valueR;
        }

        return switch (operacion) {
            case IGUALA ->
                this.igualA(valueL, valueR, typeL, typeR);
            case DIFERENTEA ->
                this.diferenteA(valueL, valueR, typeL, typeR);
            case MAYOR ->
                this.mayor(valueL, valueR, typeL, typeR);
            case MENOR ->
                this.menor(valueL, valueR, typeL, typeR);
            case MENORIGUAL ->
                this.menorIgual(valueL, valueR, typeL, typeR);
            case MAYORIGUAL ->
                this.mayorIgual(valueL, valueR, typeL, typeR);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object igualA(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL == (int) valueR;
                    case DECIMAL ->
                        (int) valueL == (double) valueR;
                    case CARACTER ->
                        (int) valueL == (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL == (int) valueR;
                    case DECIMAL ->
                        (double) valueL == (double) valueR;
                    case CARACTER ->
                        (int) valueL == (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case BOOLEANO ->
                switch (typeR) {
                    case BOOLEANO ->
                        (boolean) valueL == (boolean) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL == (int) valueR;
                    case DECIMAL ->
                        (char) valueL == (double) valueR;
                    case CARACTER ->
                        (char) valueL == (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CADENA ->
                switch (typeR) {
                    case CADENA ->
                        valueL.toString().equalsIgnoreCase(valueR.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object diferenteA(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL != (int) valueR;
                    case DECIMAL ->
                        (int) valueL != (double) valueR;
                    case CARACTER ->
                        (int) valueL != (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL != (int) valueR;
                    case DECIMAL ->
                        (double) valueL != (double) valueR;
                    case CARACTER ->
                        (int) valueL != (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case BOOLEANO ->
                switch (typeR) {
                    case BOOLEANO ->
                        (boolean) valueL != (boolean) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL != (int) valueR;
                    case DECIMAL ->
                        (char) valueL != (double) valueR;
                    case CARACTER ->
                        (char) valueL != (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CADENA ->
                switch (typeR) {
                    case CADENA ->
                        !valueL.toString().equalsIgnoreCase(valueR.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object menor(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL < (int) valueR;
                    case DECIMAL ->
                        (int) valueL < (double) valueR;
                    case CARACTER ->
                        (int) valueL < (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL < (int) valueR;
                    case DECIMAL ->
                        (double) valueL < (double) valueR;
                    case CARACTER ->
                        (int) valueL < (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL < (int) valueR;
                    case DECIMAL ->
                        (char) valueL < (double) valueR;
                    case CARACTER ->
                        (char) valueL < (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object mayor(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL > (int) valueR;
                    case DECIMAL ->
                        (int) valueL > (double) valueR;
                    case CARACTER ->
                        (int) valueL > (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL > (int) valueR;
                    case DECIMAL ->
                        (double) valueL > (double) valueR;
                    case CARACTER ->
                        (int) valueL > (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL > (int) valueR;
                    case DECIMAL ->
                        (char) valueL > (double) valueR;
                    case CARACTER ->
                        (char) valueL > (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object menorIgual(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL <= (int) valueR;
                    case DECIMAL ->
                        (int) valueL <= (double) valueR;
                    case CARACTER ->
                        (int) valueL <= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL <= (int) valueR;
                    case DECIMAL ->
                        (double) valueL <= (double) valueR;
                    case CARACTER ->
                        (int) valueL <= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL <= (int) valueR;
                    case DECIMAL ->
                        (char) valueL <= (double) valueR;
                    case CARACTER ->
                        (char) valueL <= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public Object mayorIgual(Object valueL, Object valueR, Dato typeL, Dato typeR) {
        return switch (typeL) {
            case ENTERO ->
                switch (typeR) {
                    case ENTERO ->
                        (int) valueL >= (int) valueR;
                    case DECIMAL ->
                        (int) valueL >= (double) valueR;
                    case CARACTER ->
                        (int) valueL >= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (typeR) {
                    case ENTERO ->
                        (double) valueL >= (int) valueR;
                    case DECIMAL ->
                        (double) valueL >= (double) valueR;
                    case CARACTER ->
                        (int) valueL >= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            case CARACTER ->
                switch (typeR) {
                    case ENTERO ->
                        (char) valueL >= (int) valueR;
                    case DECIMAL ->
                        (char) valueL >= (double) valueR;
                    case CARACTER ->
                        (char) valueL >= (char) valueR;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.columna);
        };
    }

    public String generarast(Arbol arbol, String anterior) {

        return "";

    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "op_rel" + arbol.getContador();
        String labels = nodeName + "[label=\"" + operacion.name() + "\"]\n";
        String ast = padre + " -> " + nodeName + "\n";
        ast += this.cL.generarastCP(nodeName, arbol);
        ast += this.cR.generarastCP(nodeName, arbol);
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        aa += cL.generarAA(padre, arbol, tabla);
        aa += cR.generarAA(padre, arbol, tabla);

        return aa;
    }
}
