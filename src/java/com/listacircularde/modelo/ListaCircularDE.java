/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listacircularde.modelo;

import com.listaenlazada.modelo.Corredor;
import com.listase.excepciones.CorredorExcepcion;

import com.listase.modelo.NodoDE;
import java.io.Serializable;

/**
 *
 * @author carloaiza
 */
public class ListaCircularDE implements Serializable{
    private NodoDE cabeza;

    public NodoDE getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoDE cabeza) {
        this.cabeza = cabeza;
    }
    
    
    
     public void adicionarNodo(Corredor corredor) {
        if (cabeza == null) {
            cabeza = new NodoDE(corredor);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            
        } else {
            //Lamo a mi ayudante
           NodoDE temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoDE nodoInsertar = new NodoDE(corredor);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
        }
    }

    public void adicionarNodoAlInicio(Corredor corredor) {
        if (cabeza == null) {
             cabeza = new NodoDE(corredor);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            NodoDE temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoDE nodoInsertar = new NodoDE(corredor);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
           cabeza = cabeza.getAnterior();
        }
    }
    
    public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoDE temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=cabeza)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    
     public String listarCorredores(String listado) throws CorredorExcepcion
     {
        if (cabeza != null) {
            NodoDE temp = cabeza;
//            while (temp.getSiguiente() != cabeza) {
//                listado += temp.getDato() + "\n";
//                temp = temp.getSiguiente();
//            }
//            listado += temp.getDato() + "\n";
            do
            {
                listado += temp.getDato() + "\n";
                temp = temp.getSiguiente();
            }while(temp != cabeza);

            return listado;
        }
        throw new CorredorExcepcion(("No existen corredores en la lista"));
    }
    
    //Eliminar NOdo
     
    
}
