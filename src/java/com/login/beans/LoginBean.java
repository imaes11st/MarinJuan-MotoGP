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
        this.verPanelLogin = false;
    }
    
    //Metodo para cambiar el valor de la variable y ocultar o mostrar el panel
    public void habilitarOdeshabilitarLogin(){
        
        verPanelLogin =! verPanelLogin;
    }
}
