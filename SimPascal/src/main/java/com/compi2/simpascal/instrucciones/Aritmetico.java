package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.operadores.OpAritmeticos;
import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;

/**
 *
 * @author mgome
 */
public class Aritmetico extends Instruccion {

    private Instruccion opL;
    private Instruccion opR;
    private Instruccion opU;
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
        if (this.opU != null) {
            var valueU = this.opU.interpretar(arbol, tabla);
            
            return this.negacion(valueU, arbol);

        } else {
            if (this.opL == null || this.opR == null) {
                arbol.errores.add(new Errores("SINTACTICO", "Nulos", linea, columna));
            } else {

                var valueL = this.opL.interpretar(arbol, tabla);
                var valueR = this.opR.interpretar(arbol, tabla);

                var typeL = this.opL.tipo.getDato();
                var typeR = this.opR.tipo.getDato();

                if (valueL == null || valueR == null) {
                    arbol.errores.add(new Errores("Semantico", "Operacion: Un elemento es nulo o no existe.", this.linea, this.columna));
                } else {

                    return switch (operacion) {
                        case SUMA ->
                            this.suma(valueL, valueR, typeL, typeR, arbol);
                        case RESTA ->
                            this.resta(valueL, valueR, typeL, typeR, arbol);
                        case MULTIPLICACION ->
                            this.multiplicacion(valueL, valueR, typeL, typeR, arbol);
                        case DIVISION ->
                            this.division(valueL, valueR, typeL, typeR, arbol);
                        default ->
                            arbol.errores.add(new Errores("Semantico", "Operador aritmetico invalido.", this.linea, this.columna));
                    };
                }
            }
        }
        return null;
    }

    public Object suma(Object opL, Object opR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
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
                        arbol.errores.add(new Errores("Semantico", "Suma erronea", this.linea, this.columna));
                    }
                }
            }
            case DECIMAL -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Suma erronea", this.linea, this.columna));
                    }
                }
            }
            case CADENA -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Suma erronea", this.linea, this.columna));
                    }
                }
            }
            case CARACTER -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Suma erronea", this.linea, this.columna));
                    }
                }
            }
            default -> {
                arbol.errores.add(new Errores("Semantico", "Suma erronea", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object resta(Object opL, Object opR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Resta erronea", this.linea, this.columna));
                    }
                }
            }
            case DECIMAL -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Resta erronea", this.linea, this.columna));
                    }
                }
            }
            case CARACTER -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (char) opL - (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL - (double) opR;
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Resta erronea", this.linea, this.columna));
                    }
                }
            }
            default -> {
                arbol.errores.add(new Errores("Semantico", "Resta erronea", this.linea, this.columna));

            }
        }
        return null;
    }

    public Object multiplicacion(Object opL, Object opR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Multiplicacion erronea", this.linea, this.columna));
                    }
                }
            }
            case DECIMAL -> {
                switch (typeR) {
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
                        arbol.errores.add(new Errores("Semantico", "Multiplicacion erronea", this.linea, this.columna));
                    }
                }
            }
            case CARACTER -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.ENTERO);
                        return (char) opL * (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL * (double) opR;
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Multiplicacion erronea", this.linea, this.columna));
                    }
                }
            }
            default -> {
                arbol.errores.add(new Errores("Semantico", "Multiplicacion erronea", this.linea, this.columna));

            }
        }
        return null;
    }

    public Object division(Object opL, Object opR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            arbol.errores.add(new Errores("Semantico", "Division por 0", this.linea, this.columna));
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
                        arbol.errores.add(new Errores("Semantico", "Division erronea", this.linea, this.columna));
                    }
                }
            }
            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            arbol.errores.add( new Errores("Semantico", "Division por 0", this.linea, this.columna));
                        }
                        return (double) opL / (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((double) opR == 0.0) {
                            arbol.errores.add( new Errores("Semantico", "Division por 0", this.linea, this.columna));
                        }
                        return (double) opL / (double) opR;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((char) opR == 0) {
                            arbol.errores.add( new Errores("Semantico", "Division por 0", this.linea, this.columna));
                        }
                        return (double) opL / (char) opR;
                    }
                    default -> {
                        arbol.errores.add( new Errores("Semantico", "Division erronea", this.linea, this.columna));
                    }
                }
            }
            case CARACTER -> {
                switch (typeR) {
                    case ENTERO -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        if ((int) opR == 0) {
                            arbol.errores.add( new Errores("Semantico", "Division por 0", this.linea, this.columna));
                        }
                        return (char) opL / (int) opR;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(Dato.DECIMAL);
                        return (char) opL / (double) opR;
                    }
                    default -> {
                        arbol.errores.add( new Errores("Semantico", "Division erronea", this.linea, this.columna));
                    }
                }
            }
            default -> {
                arbol.errores.add( new Errores("Semantico", "Division erronea", this.linea, this.columna));

            }
        }
        return null;
    }

    public Object negacion(Object unico, Arbol arbol) {
        var tipoU = this.opU.tipo.getDato();

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
                arbol.errores.add(new Errores("SEMANTICO", "Negacion erronea", this.linea, this.columna));
            }
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "op_arit" + arbol.getContador();
        String labels = nodeName + "[label=\"" + operacion.name() + "\"]\n";
        String ast = padre + " -> " + nodeName + "\n";
        if (opU == null) {
            ast += this.opL.generarastCP(nodeName, arbol);
            ast += this.opR.generarastCP(nodeName, arbol);
        } else {
            ast += this.opU.generarastCP(nodeName, arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = "";
        if (opU != null) {
            aa += opU.generarAA(padre, arbol, tabla);
        } else {
            aa += opL.generarAA(padre, arbol, tabla);
            aa += opR.generarAA(padre, arbol, tabla);
        }

        return aa;
    }
}
