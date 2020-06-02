/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.CorredorExcepcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.listaenlazada.modelo.Corredor;
/**
 *
 * @author Marin
 */
public class ListaSE implements Serializable{
    private Nodo cabeza;
    
    public ListaSE() {
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    
    public void adicionarNodo(Corredor corredor)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(corredor);
        }
        else
        {
            //Lamo a mi ayudante
            Nodo temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new Nodo(corredor));
        }
        
    }
    
    public void adicionarNodoAlInicio(Corredor corredor)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(corredor);
        }
        else
        {
            Nodo temp= new Nodo(corredor);
            temp.setSiguiente(cabeza);
            cabeza= temp;
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
            Nodo temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String obtenerListadoCorredores()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
         return listarCorredores("");
    }
    
    public String listarCorredores(String listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay Corredores";
    }
    
    
     public List obtenerListaCorredores()
    {
        List<Corredor> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarCorredores(listado);
        return listado;
    }
    
    public void listarCorredores(List listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();                
            }            
        }
        
    }
    
    public float promediarEdades()
    {
        int sumaEdades= 0;
        int contador=0;
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {          
                //sumaEdades= sumaEdades+ temp.getDato().getEdad();
                sumaEdades += temp.getDato().getEdad();
                contador++;
                temp=temp.getSiguiente();                
            }   
            return sumaEdades/(float) contador;
        }
        return 0;
        
    }
    
    
    public void invertirLista()
    {
        if(cabeza!=null)
        {
            //Crear una lista temporal la cabeza de la lista temporal está vacía
            ListaSE listaTemporal = new ListaSE();
            // Llamo un ayudante
            Nodo temp= cabeza;
            //Recorro la lista de principio a fin
            while(temp!=null)
            {         
               //Parado en cada nodo , se extrae la información y se
                // envía a la otra lista al inicio
                listaTemporal.adicionarNodoAlInicio(temp.getDato());
                temp=temp.getSiguiente();                
            }   
            //Igualo la cabeza de mi lista principal a la cabeza de la lista temporal
            cabeza= listaTemporal.getCabeza();
        }
    }
    
    public short contarCorredoresxGenero(boolean genero)
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            Nodo temp= cabeza;
            short cont=0;
            while(temp!=null)
            {
                if(temp.getDato().getGenero()==genero)
                {
                  cont++;   
                }                
                temp=temp.getSiguiente();
                
            }
            return cont;
        }
    }
    /*
    Receta para eliminar un niño 
 Primero, preguntar el código del niño que desea eliminar.
segundo, preguntar si la cabeza tiene algo, si la cabeza 
es el código a eliminar digo que cabeza=cabeza.siguiente si,no llamó al ayudante 
    y le digo que recorra la lista preguntando si el que sigue existe, si lo que 
    hay en el siguiente es lo que se desea eliminar, 
    le digo al ayudante que en siguiente coloque lo que tiene el siguiente en siguiente 
*/
    public void eliminarCorredor(short codigo ) throws CorredorExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {
                cabeza=cabeza.getSiguiente();
                return;
            }
            else
            {
                Nodo temp=cabeza;
                while(temp.getSiguiente()!=null)
                {
                    if(temp.getSiguiente().getDato().getCodigo()== codigo)
                    {
                        //el que sigue es el que hay que eliminar
                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
                        return;
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new CorredorExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new CorredorExcepcion("La lista de corredores está vacía");
    }
    
    
     public Corredor obtenerCorredor(short codigo ) throws CorredorExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getCodigo()==codigo)
            {                
                return cabeza.getDato();
            }
            else
            {
                Nodo temp=cabeza;
                while(temp!=null)
                {
                    if(temp.getDato().getCodigo()== codigo)
                    {                                                
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new CorredorExcepcion("El código "+codigo +" no existe en la lista");
            }
        }
        throw new CorredorExcepcion("La lista de corredores está vacía");
    }
     //
      public int obtenerPosicionCorredor(short codigo) throws CorredorExcepcion {
        if (cabeza != null) {
            int cont = 1;
            Nodo temp = cabeza;
            while (temp != null) {
                if (temp.getDato().getCodigo() == codigo) {
                    return cont;
                }
                temp = temp.getSiguiente();
                cont++;
            }
            throw new CorredorExcepcion("El código ingresado no ");

        }
        throw new CorredorExcepcion("La lista de corredores está vacía");
    }
        public void adicionarNodoPosicion(int posicion, Corredor dato) throws CorredorExcepcion {
        if (cabeza != null) {
            if (posicion == 1) {
                adicionarNodoAlInicio(dato);               
            } else {
                int cont = 1;
                Nodo temp = cabeza;
                while (temp != null) {
                    if ((posicion - 1) == cont) {
                        Nodo nodoInsertar = new Nodo(dato);
                        nodoInsertar.setSiguiente(temp.getSiguiente());
                        temp.setSiguiente(nodoInsertar);
                        if(nodoInsertar.getSiguiente()!=null)
                            nodoInsertar.getSiguiente().setAnterior(nodoInsertar);
                        nodoInsertar.setAnterior(temp);
                        break ;
                    }
                    temp = temp.getSiguiente();
                    cont++;
                }
            }
        }
        else{
            throw new CorredorExcepcion(("La lista está vacía"));
        }
    }   
    
}
