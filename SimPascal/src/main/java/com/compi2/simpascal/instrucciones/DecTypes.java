package com.compi2.simpascal.instrucciones;

import com.compi2.simpascal.instrucciones.simbolos.Arbol;
import com.compi2.simpascal.instrucciones.simbolos.Simbolo;
import com.compi2.simpascal.instrucciones.simbolos.Tabla;
import com.compi2.simpascal.instrucciones.tipos.*;
import com.compi2.simpascal.instrucciones.tipos.Tipo;
import java.util.LinkedList;

/**
 *
 * @author mgome
 */
public class DecTypes extends Instruccion {

    LinkedList<Simbolo> types;

    public DecTypes(LinkedList<Simbolo> types, int linea, int columna) {
        super(new Tipo(Dato.VOID), linea, columna);
        this.types = types;
    }

    @Override
    public Object interpretar(Arbol arbol, Tabla tabla) {

        for (var type : types) {
            if (arbol.getTypes().containsKey(type.getId())) {
                arbol.errores.add(new Errores("Semantico", "El tipo " + type.getId() + " ya existe", linea, columna));
            } else {
                var aux = type.getTipo();
                while (aux instanceof TypeP typ) {
                    var aux2 = arbol.getTypes().get(typ.getId());
                    typ.setTipo(aux2);
                    if (aux2 instanceof Tipo) {
                        aux = typ;
                        break;
                    } else {
                        aux = aux2;
                    }
                }
                type.setTipo(aux);
                arbol.getTypes().put(type.getId(), type.getTipo());
                tabla.typeNames.add(type.getId());
            }
        }
        return null;
    }

    @Override
    public String generarast(Arbol arbol) {
        String nodeName = "types_" + arbol.getContador();
        String labels = nodeName + "[label=\"types\"]\n";
        String ast = "start -> declaraciones\ndeclaraciones ->" + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        for (Simbolo type : types) {
            var aux = type.getTipo();
            if (aux instanceof TypeP typ) {
                tipoV = typ.getId();
            } else {
                tipoV = type.getTipo().getDato().name();
            }
            id = type.getId();
            i++;

            labels += nodeName + "_dec" + i + "[label=\"type\"]\n"
                    + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                    + nodeName + "_dec" + i + "_type[label=\"TYPE: " + tipoV + "\"]\n";
            ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                    + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n";
        }

        return labels + ast;
    }

    @Override
    public String generarastCP(String padre, Arbol arbol) {
        String nodeName = "types_" + arbol.getContador();
        String labels = nodeName + "[label=\"variables\"]\n"
                + nodeName + "declaraciones[label=declaraciones]\n";
        String ast = padre + " -> " + nodeName + "declaraciones\n"
                + nodeName + "declaraciones -> " + nodeName + "\n";
        String tipoV;
        String id;
        int i = 0;

        for (Simbolo type : types) {
            if (type.getTipo() instanceof TypeP typ) {
                tipoV = typ.getId();
                id = type.getId();
                i++;

                labels += nodeName + "_dec" + i + "[label=\"type\"]\n"
                        + nodeName + "_dec" + i + "_id[label=\"ID: " + id + "\"]\n"
                        + nodeName + "_dec" + i + "_type[label=\"TYPE: " + tipoV + "\"]\n";
                ast += nodeName + " -> " + nodeName + "_dec" + i + "\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_id\n"
                        + nodeName + "_dec" + i + " -> " + nodeName + "_dec" + i + "_type\n";
            }
        }

        return labels + ast;
    }

    @Override
    public String generarAA(String padre, Arbol arbol, Tabla tabla) {
        return "";
    }

}
