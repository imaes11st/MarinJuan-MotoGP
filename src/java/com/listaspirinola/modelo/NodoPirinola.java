/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaspirinola.modelo;

import com.listaenlazada.modelo.Usuario;

/**
 *
 * @author Marin
 */
public class NodoPirinola {
    
    private Usuario dato;
    private NodoPirinola siguiente;
    private NodoPirinola anterior;
    

    public NodoPirinola(Usuario dato) {
        this.dato = dato;
       
    }

    NodoPirinola(DatoPirinola dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }

    public NodoPirinola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPirinola siguiente) {
        this.siguiente = siguiente;
    }

    public NodoPirinola getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoPirinola anterior) {
        this.anterior = anterior;
    }
    
}
