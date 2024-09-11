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
public class LlamadaFunc extends Instruccion {

    private final String id;
    private final LinkedList<Instruccion> parametros;
    private Object[] valores;

    public LlamadaFunc(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.valores = new Object[parametros.size()];
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        int contador = 0;
        var funcion = arbol.getFuncion(this.id);
        if (funcion == null) {
            arbol.errores.add(new Errores("SEMANTICO", "La funcion " + this.id + " no existe.", this.linea, this.columna));
        } else {
            var tablaLlamada = new Tabla(tabla);
            tabla.addTablaHija(tablaLlamada);
            tablaLlamada.setNombre("funcion_" + this.id + "_" + arbol.getContador());

            if (funcion.parametros.size() != this.parametros.size()) {
                arbol.errores.add(new Errores("Semantico", "Los parametros recibidos son incorrectos", this.linea, this.columna));
            }

            for (var parametro : this.parametros) {
                var idFP = (String) funcion.parametros.get(contador).get("id");
                var valorP = parametro.interpretar(arbol, tabla);
                var tipoFP = (Tipo) funcion.parametros.get(contador).get("tipo");

                if (valorP != null) {
                    valores[contador] = valorP;

                    Simbolo s = new Simbolo(tipoFP, idFP, valorP, true);
                    s.setCategoria("Parametro");
                    s.setAmbito(tablaLlamada.getNombre());

                    var parametroD = new DecVar(s, this.linea, this.columna);
                    parametroD.interpretar(arbol, tablaLlamada);

                    if (s.getTipo().getDato() != this.parametros.get(contador).tipo.getDato()) {
                        arbol.errores.add(new Errores("Semantico", "El tipo de dato de los parametros no coincide.", this.linea, this.columna));
                    }
                    contador++;
                }

            }
            Simbolo s = new Simbolo(funcion.tipo, this.id, null, true);
            s.setCategoria("Return");
            s.setAmbito(tablaLlamada.getNombre());
            var paramReturn = new DecVar(s, this.linea, this.columna);
            paramReturn.interpretar(arbol, tablaLlamada);

            var resultadoFuncion = funcion.interpretar(arbol, tablaLlamada);
            this.tipo = funcion.tipo;
            return resultadoFuncion;

        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        return "";
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "acceso_funcion_" + id + "_" + arbol.getContador();
        String label = nodeName + "[label=\"funcion_" + id + "\"]\n"
                + nodeName + "params[label=parametros]\n";
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "params\n";

        for (int i = 0; i < this.parametros.size(); i++) {
            var parametro = parametros.get(i);
            var idP = (String) arbol.getFuncion(this.id).parametros.get(i).get("id");
            label += nodeName + "params_" + idP + "[label=\"id:" + idP + "\"]\n";
            ast += nodeName + "params -> " + nodeName + "params_" + idP + "\n";
            ast += parametro.generarastCP(nodeName + "params_" + idP, arbol);
        }
        return label + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String nodeName = "f_" + id + "_" + arbol.getContadorAA();
        String labels = nodeName + "[label=\"Funcion: " + id + "(";
        for (int i = 0; i < parametros.size(); i++) {
            var valor = valores[i];
            labels += valor.toString();
            if (i != parametros.size() - 1) {
                labels += ", ";
            }
        }
        labels += ")\"]\n";
        String ast = padre + " -> " + nodeName + "\n";

        var funcion = arbol.getFuncion(this.id);
        ast += funcion.generarAA(nodeName, arbol, tabla);

        return labels + ast;
    }
}
