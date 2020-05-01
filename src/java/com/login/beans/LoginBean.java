/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author marin
 */
@Named(value = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {
    // Atributo para ocultar/visualizar panel login
    
    private boolean verPanelLogin;
    //Atributo para capturar el texto para mostrar el saludo
    private String  textoSaludo="";
    //Atributo contador para los saludos
    private short contadorSaludos=0;

    public short getContadorSaludos() {
        return contadorSaludos;
    }

    public void setContadorSaludos(short contadorSaludos) {
        this.contadorSaludos = contadorSaludos;
    }

    public LoginBean(boolean verPanelLogin) {
        this.textoSaludo = "";
        this.verPanelLogin = verPanelLogin;
    }

    public String getTextoSaludo() {
        return textoSaludo;
    }

    public void setTextoSaludo(String textoSaludo) {
        this.textoSaludo = textoSaludo;
    }
    
    public boolean isVerPanelLogin() {
        return verPanelLogin;
    }

    public void setVerPanelLogin(boolean verPanelLogin) {
        this.verPanelLogin = verPanelLogin;
    }

    @Override
    public String toString() {
        return "LoginBean{" + "verPanelLogin=" + verPanelLogin + '}';
    }
    
    /**
     * Creates a new instance of loginBean
     */
    public LoginBean() {
        this.textoSaludo = "";
        this.verPanelLogin = false;
    }
    
    public void aumentarContadorSaludos(){
        contadorSaludos++;
    }
            
    
    
    //Metodo para cambiar el valor de la variable y ocultar o mostrar el panel
    public void habilitarOdeshabilitarLogin(){
        
        verPanelLogin =! verPanelLogin;
        
    }
}
