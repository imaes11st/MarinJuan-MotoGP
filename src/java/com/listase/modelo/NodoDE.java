/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listaenlazada.modelo.Corredor;

/**
 *
 * @author Marin
 */
public class NodoDE {
    private Corredor dato;
    private NodoDE siguiente;
    private NodoDE anterior;

    public NodoDE(Corredor dato) {
        this.dato = dato;
    }

    public Corredor getDato() {
        return dato;
    }

    public void setDato(Corredor dato) {
        this.dato = dato;
    }

    public NodoDE getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDE siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDE getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDE anterior) {
        this.anterior = anterior;
    }
    
    
    
}
