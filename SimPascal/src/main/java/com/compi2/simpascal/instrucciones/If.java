package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class If extends Instruccion {

    private final Instruccion condicion;
    private final LinkedList<Instruccion> instrucciones;
    private final LinkedList<Instruccion> elseIfs;
    private final Instruccion elseInstr;

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> elseIfs, Instruccion elseInstr, int linea, int col) {
        super(new Tipo(Dato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.elseIfs = elseIfs;
        this.elseInstr = elseInstr;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        boolean listo = false;
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getDato() != Dato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida", this.linea, this.columna);
        }

        var newTabla = new Tabla(tabla);
        newTabla.setNombre("Tabla If");
        if ((boolean) cond) {
            for (var i : this.instrucciones) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado != null) {
                    return resultado;
                }
            }
        } else {
            if (elseIfs != null) {
                for (var elseIf : elseIfs) {
                    var resultadoElseIf = elseIf.interpretar(arbol, newTabla);
                    if (resultadoElseIf instanceof Errores) {
                        return resultadoElseIf;
                    }

                    /*if (resultadoElseIf instanceof Break) {
                        listo = true;
                        break;
                    }*/
                    if (resultadoElseIf instanceof Boolean) {
                        listo = true;
                        resultadoElseIf = null;
                        return resultadoElseIf;
                    }
                    if (resultadoElseIf != null) {
                        return resultadoElseIf;
                    }
                }
            }
            if (elseInstr != null && !listo) {
                var resultadoElse = elseInstr.interpretar(arbol, newTabla);
                if (resultadoElse != null) {
                    return resultadoElse;
                }
            }

        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "if_" + arbol.getContador();
        String labels = nodeName + "[label=\"if\"]\n"
                + nodeName + "_condicion[label=\"condicion\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = "start -> statements\nstatements -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_condicion\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        ast += condicion.generarastCP(nodeName + "_condicion", arbol);
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        if (elseIfs != null) {
            String nodeIntermedio = nodeName;
            String nodeElseIf = "";
            for (var elseif : elseIfs) {
                nodeElseIf = nodeName + "_elseIf_" + arbol.getContador();
                labels += nodeElseIf + "[label=\"else if\"]\n";
                ast += nodeIntermedio + " -> " + nodeElseIf;
                ast += elseif.generarastCP(nodeElseIf, arbol);
                nodeIntermedio = nodeElseIf;
            }
            
        }
        if (elseInstr != null) {
            labels += nodeName + "_else[label=\"else\"]\n";
            ast += nodeName + " -> " + nodeName + "_else\n";
            ast += elseInstr.generarastCP(nodeName + "_else", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String aux = "";
        String nodeName = "if_" + arbol.getContador();
        String labels = nodeName + "[label=\"if\"]\n"
                + nodeName + "_condicion[label=\"condicion\"]\n"
                + nodeName + "_statements[label=\"statements\"]\n";
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "_condicion\n"
                + nodeName + " -> " + nodeName + "_statements\n";
        ast += condicion.generarastCP(nodeName + "_condicion", arbol);
        for (var instruccion : instrucciones) {
            ast += instruccion.generarastCP(nodeName + "_statements", arbol);
        }
        if (elseIfs != null) {
            String nodeIntermedio = nodeName;
            String nodeElseIf = "";
            for (var elseif : elseIfs) {
                nodeElseIf = nodeName + "_elseIf_" + arbol.getContador();
                labels += nodeElseIf + "[label=\"else if\"]\n";
                ast += nodeIntermedio + " -> " + nodeElseIf;
                ast += elseif.generarastCP(nodeElseIf, arbol);
                nodeIntermedio = nodeElseIf;
            }
            aux = nodeElseIf;
            
        }
        if (elseInstr != null) {
            labels += nodeName + "_else[label=\"else\"]\n";
            ast += aux + " -> " + nodeName + "_else\n";
            ast += elseInstr.generarastCP(nodeName + "_else", arbol);
        }
        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String aa = condicion.generarAA(padre, arbol, tabla);
        for (var instruccion: instrucciones) {
            aa += instruccion.generarAA(padre, arbol, tabla);
        }
        if (elseIfs != null) {
            for (var elseif : elseIfs) {
                aa += elseif.generarAA(padre, arbol, tabla);
            }
        }
        
        if (elseInstr != null) {
            aa += elseInstr.generarAA(padre, arbol, tabla);
        }
        
        return aa;
        
    }
}
