package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.*;
import com.compi2.simpascal.instrucciones.tipos.*;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class LlamadaProc extends Instruccion{
    
    
    private final String id;
    private final LinkedList<Instruccion> parametros;
    private Object[] valores;


    public LlamadaProc(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.valores = new Object[parametros.size()];
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {
        var procedimiento = arbol.getProcedimiento(this.id);
        if (procedimiento == null) {
            return new Errores("SEMANTICO", "El procedimiento no existente", this.linea, this.columna);
        }

        var tablaLlamada = new Tabla(tabla);
        tablaLlamada.setNombre("procedure" + this.id + "_" + arbol.getContador());

        if (procedimiento.parametros.size() != this.parametros.size()) {
            return new Errores("SEMANTICO", "Parametros incorrectos", this.linea, this.columna);
        }

        for (int i = 0; i < this.parametros.size(); i++) {
            var idFP = (String) procedimiento.parametros.get(i).get("id");
            var valorP = this.parametros.get(i).interpretar(arbol, tabla);
            var tipoFP = (Tipo) procedimiento.parametros.get(i).get("tipo");
            if (valorP instanceof Errores) {
                return valorP;
            }
            valores[i] = valorP;

            Simbolo s = new Simbolo(tipoFP, idFP, valorP, true);

            var parametro = new DecVar(s, this.linea, this.columna);
            var resultado = parametro.interpretar(arbol, tablaLlamada);

            if (resultado instanceof Errores) {
                return resultado;
            }

            if (s.getTipo().getDato() != this.parametros.get(i).tipo.getDato()) {
                return new Errores("SEMANTICO", "Error en tipo de parametros", this.linea, this.columna);
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
        String nodeName = "acceso_procedure_" + id + "_" + arbol.getContador();
        String label = nodeName + "[label=\"procedure_" + id + "\"]\n"
                + nodeName + "params[label=parametros]\n";
        String ast = padre + " -> " + nodeName + "\n"
                + nodeName + " -> " + nodeName + "params\n";
                
        for (int i = 0; i < this.parametros.size(); i++) {
            var parametro = parametros.get(i);
            var idP = (String) arbol.getProcedimiento(this.id).parametros.get(i).get("id");
            label += nodeName + "params_" + idP + "[label=\"id:" + idP + "\"]\n";
            ast += nodeName + "params -> " + nodeName + "params_" + idP + "\n";
            ast += parametro.generarastCP(nodeName + "params_" + idP, arbol);
        }
        return label + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        String nodeName = "p_" + id + "_" + arbol.getContadorAA();
        String labels = nodeName + "[label=\"Procedure: " + id + "(";
        for(int i = 0; i < parametros.size(); i++) {
            var valor = valores[i];
            labels += valor.toString();
            if(i != parametros.size() - 1) {
                labels += ", ";
            }
        }
        labels += ")\"]\n";
        String ast = padre + " -> " + nodeName + "\n";
        
        var procedure = tabla.getProcedimiento(this.id);
        ast += procedure.generarAA(nodeName, arbol, tabla);

        return labels +  ast;
    }
    
}
