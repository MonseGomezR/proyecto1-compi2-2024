package com.compi2.simpascal.instrucciones.tipos;

/**
 *
 * @author mgome
 */
public class TypeP extends Tipo{
    private String id;
    private Tipo tipo;

    
    public TypeP(String id) {
        super(null);
        this.id = id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
        this.setDato(tipo.getDato());
    }


    
    
}
