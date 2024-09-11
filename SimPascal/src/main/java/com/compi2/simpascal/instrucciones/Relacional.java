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
        var valueL = this.cL.interpretar(arbol, tabla);
        var valueR = this.cR.interpretar(arbol, tabla);

        var typeL = this.cL.tipo.getDato();
        var typeR = this.cR.tipo.getDato();

        if (valueL == null || valueR == null) {
            arbol.errores.add(new Errores("SEMANTICO", "Una condicion es nula.", this.linea, this.columna));
        } else {
            return switch (operacion) {
                case IGUALA ->
                    this.igualA(valueL, valueR, typeL, typeR, arbol);
                case DIFERENTEA ->
                    this.diferenteA(valueL, valueR, typeL, typeR, arbol);
                case MAYOR ->
                    this.mayor(valueL, valueR, typeL, typeR, arbol);
                case MENOR ->
                    this.menor(valueL, valueR, typeL, typeR, arbol);
                case MENORIGUAL ->
                    this.menorIgual(valueL, valueR, typeL, typeR, arbol);
                case MAYORIGUAL ->
                    this.mayorIgual(valueL, valueR, typeL, typeR, arbol);
                default ->
                    arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            };
        }
        return null;
    }

    public Object igualA(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL == (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL == (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL == (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL == 1;
                        } else {
                            return (int) valueL == 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL == (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL == (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL == (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL == 1;
                        } else {
                            return (double) valueL == 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR == 1;
                        } else {
                            return (int) valueR == 0;
                        }
                    }

                    case BOOLEANO -> {
                        return (boolean) valueL == (boolean) valueR;
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object diferenteA(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL != (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL != (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL != (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL != 1;
                        } else {
                            return (int) valueL != 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL != (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL != (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL != (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL != 1;
                        } else {
                            return (double) valueL != 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR != 1;
                        } else {
                            return (int) valueR != 0;
                        }
                    }

                    case BOOLEANO -> {
                        return (boolean) valueL != (boolean) valueR;
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object menor(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL < (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL < (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL < (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL < 1;
                        } else {
                            return (int) valueL < 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL < (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL < (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL < (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL < 1;
                        } else {
                            return (double) valueL < 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR < 1;
                        } else {
                            return (int) valueR < 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object mayor(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL > (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL > (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL > (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL > 1;
                        } else {
                            return (int) valueL > 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL > (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL > (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL > (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL > 1;
                        } else {
                            return (double) valueL > 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR > 1;
                        } else {
                            return (int) valueR > 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object menorIgual(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL <= (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL <= (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL <= (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL <= 1;
                        } else {
                            return (int) valueL <= 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL <= (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL <= (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL <= (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL <= 1;
                        } else {
                            return (double) valueL <= 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR <= 1;
                        } else {
                            return (int) valueR <= 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
    }

    public Object mayorIgual(Object valueL, Object valueR, Dato typeL, Dato typeR, Arbol arbol) {
        switch (typeL) {
            case ENTERO -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (int) valueL > (int) valueR;
                    }
                    case DECIMAL -> {
                        return (int) valueL > (double) valueR;
                    }
                    case CARACTER -> {
                        return (int) valueL > (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (int) valueL > 1;
                        } else {
                            return (int) valueL > 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }

            case DECIMAL -> {
                switch (typeR) {
                    case ENTERO -> {
                        return (double) valueL >= (int) valueR;
                    }
                    case DECIMAL -> {
                        return (double) valueL >= (double) valueR;
                    }
                    case CARACTER -> {
                        return (double) valueL >= (char) valueR;
                    }
                    case BOOLEANO -> {
                        if ((boolean) valueR) {
                            return (double) valueL >= 1;
                        } else {
                            return (double) valueL >= 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            case BOOLEANO -> {
                switch (typeR) {
                    case ENTERO -> {
                        if ((boolean) valueL) {
                            return (int) valueR >= 1;
                        } else {
                            return (int) valueR >= 0;
                        }
                    }
                    default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
                    }
                }
            }
            default -> {
                        arbol.errores.add(new Errores("Semantico", "Relacional Invalido", this.linea, this.columna));
            }
        }
        return null;
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
