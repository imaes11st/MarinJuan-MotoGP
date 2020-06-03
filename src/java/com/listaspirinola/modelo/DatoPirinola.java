/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaspirinola.modelo;

/**
 *
 * @author Marin
 */
public class DatoPirinola {
    
    private String cantidad;
    private String texto;
    private boolean ganaPierde;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isGanaPierde() {
        return ganaPierde;
    }

    public void setGanaPierde(boolean ganaPierde) {
        this.ganaPierde = ganaPierde;
    }
    
    
}
