package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.operadores.OpAritmeticos;
import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.*;

/**
 *
 * @author mgome
 */
public class Aritmetico extends Instruccion {

    private Instruccion opL;
    private Instruccion opR;
    private Instruccion opU;
    private String ast, temp, temp2;
    private final OpAritmeticos operacion;

    public Aritmetico(Instruccion opU, OpAritmeticos operacion, int linea, int columna) {
        super(new Tipo(Dato.ENTERO), linea, columna);
        this.operacion = operacion;
        this.opU = opU;
    }

    public Aritmetico(Instruccion opL, Instruccion opR, OpAritmeticos operacion, int linea, int col) {
        super(new Tipo(Dato.ENTERO), linea, col);
        this.opL = opL;
        this.opR = opR;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        ast = "";
        temp = "aritmetico" + arbol.getContador();
        temp2 = temp;
        Object izq = null, der = null, unico = null;

        if (this.opU != null) {
            unico = this.opU.interpretar(arbol, tabla);
            if (unico instanceof Errores) {
                return unico;
            }

            ast += this.opU.generarastCP(temp);
        } else {
            if (this.opL == null || this.opR == null) {
                return new Errores("SINTACTICO", "Nulos", linea, columna);
            }
            izq = this.opL.interpretar(arbol, tabla);
            if (izq instanceof Errores) {
                return izq;
            }
            der = this.opR.interpretar(arbol, tabla);
            if (der instanceof Errores) {
                return der;
            }

            ast += this.opL.generarastCP(temp);
            ast += this.opR.generarastCP(temp);
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(izq, der);
            case RESTA ->
                this.resta(izq, der);
            case MULTIPLICACION ->
                this.multiplicacion(izq, der);
            case DIVISION ->
                this.division(izq, der);
            case NEGACION ->
                this.negacion(unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }

    public Object suma(Object opL, Object opR) {
        var tipoL = this.opL.tipo.getDato();
        var tipoR = this.opR.tipo.getDato();

        temp += "[label=\"+\"]";

        switch (tipoL) {
            case ENTERO -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        Object resultado = (int) opL + (int) opR;
                        System.out.println((int) opL + " + " + (int) opR + " = " + resultado.toString());

                        return (int) opL + (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (int) opL + (double) opR;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (int) opL + (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL + (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL + (double) opR;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL + (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case CADENA -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case CADENA -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case BOOLEANO -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (char) opL + (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL + (double) opR;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.CADENA);
                        return opL.toString() + opR.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
            }
        }
    }

    public Object resta(Object opL, Object opR) {
        var tipoL = this.opL.tipo.getDato();
        var tipoR = this.opR.tipo.getDato();

        temp += "[label=\"-\"]";
        switch (tipoL) {
            case ENTERO -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (int) opL - (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (int) opL - (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (int) opL - (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL - (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL - (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL - (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (char) opL - (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL - (double) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);

            }
        }
    }

    public Object multiplicacion(Object opL, Object opR) {
        var tipoL = this.opL.tipo.getDato();
        var tipoR = this.opR.tipo.getDato();

        temp += "[label=\"*\"]";
        switch (tipoL) {
            case ENTERO -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (int) opL * (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (int) opL * (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (int) opL * (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL * (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL * (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (double) opL * (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (char) opL * (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL * (double) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);

            }
        }
    }

    public Object division(Object opL, Object opR) {
        var tipoL = this.opL.tipo.getDato();
        var tipoR = this.opR.tipo.getDato();

        switch (tipoL) {
            case ENTERO -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            return new Errores("SEMANTICO", "Division por 0", this.linea, this.columna);
                        }
                        double r = (int) opL / (int) opR;
                        return r;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (int) opL / (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (int) opL / (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            return new Errores("SEMANTICO", "Division por 0", this.linea, this.columna);
                        }
                        return (double) opL / (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((double) opR == 0.0) {
                            return new Errores("SEMANTICO", "Division por 0", this.linea, this.columna);
                        }
                        return (double) opL / (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((char) opR == 0) {
                            return new Errores("SEMANTICO", "Division por 0", this.linea, this.columna);
                        }
                        return (double) opL / (char) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            return new Errores("SEMANTICO", "Division por 0", this.linea, this.columna);
                        }
                        return (char) opL / (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL / (double) opR;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);

            }
        }
    }

    public Object negacion(Object unico) {
        var tipoU = this.opU.tipo.getDato();
        
        temp += "[label=\"-\"]";
        switch (tipoU) {
            case ENTERO -> {
                this.tipo.setTipo(Dato.ENTERO);
                return (int) unico * -1;
            }
            case DECIMAL -> {
                this.tipo.setTipo(Dato.DECIMAL);
                return (double) unico * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.columna);
            }
        }
    }

    @Override
    public String generarast() {
        return "";
    }

    @Override
    public String generarastCP(String padre) {
        return temp + "\n" + padre + " -> " + temp2 + "\n" + ast;
    }
}
