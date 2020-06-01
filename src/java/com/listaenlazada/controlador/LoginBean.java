/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaenlazada.controlador;

import com.listaenlazada.controlador.util.JsfUtil;
import com.listaenlazada.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Marin
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private String correo="estebanmarin17@hotmail.com";
    private String contrasenia="123456";
    private Usuario usuarioAutenticado;
    @EJB
    private UsuarioFacade connUsuario;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
    
    public String ingresar()
    {
        Usuario usuencontrado = connUsuario.find(correo);
        if(usuencontrado!=null)
        {
            if(usuencontrado.getContrasenia().equals(contrasenia))
            {
                usuarioAutenticado = usuencontrado;
                return "corredores";
            }
        }
        JsfUtil.addErrorMessage("Datos erróneos");        
        return null;
    }
    
}
