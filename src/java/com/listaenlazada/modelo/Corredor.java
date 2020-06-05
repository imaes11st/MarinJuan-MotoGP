/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaenlazada.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esteb
 */
@Entity
@Table(name = "corredor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corredor.findAll", query = "SELECT c FROM Corredor c")
    , @NamedQuery(name = "Corredor.findByCodigo", query = "SELECT c FROM Corredor c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Corredor.findByNombre", query = "SELECT c FROM Corredor c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Corredor.findByEdad", query = "SELECT c FROM Corredor c WHERE c.edad = :edad")
    , @NamedQuery(name = "Corredor.findByCiudadNacimiento", query = "SELECT c FROM Corredor c WHERE c.ciudadNacimiento = :ciudadNacimiento")
    , @NamedQuery(name = "Corredor.findByGenero", query = "SELECT c FROM Corredor c WHERE c.genero = :genero")})
public class Corredor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Short codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private short edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genero")
    private boolean genero;

    public Corredor() {
    }

    public Corredor(Short codigo) {
        this.codigo = codigo;
    }

    public Corredor(Short codigo, String nombre, short edad, String ciudadNacimiento, boolean genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudadNacimiento = ciudadNacimiento;
        this.genero = genero;
    }

    public Short getCodigo() {
        return codigo;
    }

    public void setCodigo(Short codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corredor)) {
            return false;
        }
        Corredor other = (Corredor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
