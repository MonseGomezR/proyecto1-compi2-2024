package com.compi2.simpascal.instrucciones.simbolos;

import com.compi2.simpascal.instrucciones.tipos.*;

/**
 *
 * @author mgome
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    private String categoria;
    private String ambito;
    private boolean mutable;

    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }
    

    public Simbolo(Tipo tipo, String id, Object valor, boolean mutable) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.mutable = mutable;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    public boolean getMutable() {
        return mutable;
    }

    public String getCategoria() {
        return categoria;
    }
    
    

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    
    
}
