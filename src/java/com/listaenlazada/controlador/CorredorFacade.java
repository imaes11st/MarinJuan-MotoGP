/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaenlazada.controlador;

import com.listaenlazada.modelo.Corredor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esteb
 */
@Stateless
public class CorredorFacade extends AbstractFacade<Corredor> {

    @PersistenceContext(unitName = "listasprogram2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorredorFacade() {
        super(Corredor.class);
    }
    
}
