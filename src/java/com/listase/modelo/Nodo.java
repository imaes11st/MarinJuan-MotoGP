/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;
import com.listaenlazada.modelo.Corredor;

/**
 *
 * @author Marin
 */
public class Nodo implements Serializable{
    private Corredor dato;
    private Nodo siguiente;
    private Nodo anterior;
    
    
    public Nodo(Corredor dato) {
        this.dato = dato;
    }

    public Corredor getDato() {
        return dato;
    }

    public void setDato(Corredor dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
    
    
    
    
}
