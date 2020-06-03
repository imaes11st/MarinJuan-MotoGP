/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaspirinola.modelo;
import com.listase.excepciones.DatoPirinolaExcepcion;
import java.io.Serializable;

/**
 *
 * @author Marin
 */
public class ListaCircularPirinola implements Serializable {
    private NodoPirinola cabeza;
    

    public NodoPirinola getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoPirinola cabeza) {
        this.cabeza = cabeza;
    }
        
     public void adicionarNodo(DatoPirinola dato) {
        if (cabeza == null) {
            cabeza = new NodoPirinola(dato);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
            
        } else {
            //Lamo a mi ayudante
           NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(dato);
           temp.setSiguiente(nodoInsertar);
           nodoInsertar.setAnterior(temp);           
           nodoInsertar.setSiguiente(cabeza);
           cabeza.setAnterior(nodoInsertar);
        }
    }

    public void adicionarNodoAlInicio(DatoPirinola dato) {
        if (cabeza == null) {
             cabeza = new NodoPirinola(dato);
            ///Hago los enlaces circulares
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            NodoPirinola temp= cabeza.getAnterior();
           //temp= temp.getAnterior();
           NodoPirinola nodoInsertar = new NodoPirinola(dato);
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
            NodoPirinola temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=cabeza)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String listarCorredores(String listado) throws DatoPirinolaExcepcion
     {
        if (cabeza != null) {
            NodoPirinola temp = cabeza;
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
        throw new DatoPirinolaExcepcion(("No existen corredores en la lista"));
    }
    
    //Eliminar NOdo 
}

