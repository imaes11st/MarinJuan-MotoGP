/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

/**
 *
 * @author esteb
 */
public class NodoJugador {
    Usuario datoUsuario;
    NodoJugador sigUsuario;
    NodoJugador antUsuario;

    public NodoJugador(Usuario datoUsuario, NodoJugador sigUsuario, NodoJugador antUsuario) {
        this.datoUsuario = datoUsuario;
        this.sigUsuario = sigUsuario;
        this.antUsuario = antUsuario;
    }

    
    public NodoJugador(Usuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    
    public Usuario getDatoUsuario() {
        return datoUsuario;
    }

    public void setDatoUsuario(Usuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    public NodoJugador getSigUsuario() {
        return sigUsuario;
    }

    public void setSigUsuario(NodoJugador sigUsuario) {
        this.sigUsuario = sigUsuario;
    }

    public NodoJugador getAntUsuario() {
        return antUsuario;
    }

    public void setAntUsuario(NodoJugador antUsuario) {
        this.antUsuario = antUsuario;
    }
    
    
}
