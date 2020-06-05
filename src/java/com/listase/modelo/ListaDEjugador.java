/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.UsuarioExcepcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marin
 */
public class ListaDEjugador {
    NodoJugador cabJugador;


    
    public ListaDEjugador(NodoJugador cabJugador) {
        this.cabJugador = cabJugador;
    }

    public ListaDEjugador() {
    }



    public NodoJugador getCabJugador() {
        return cabJugador;
    }

    public void setCabJugador(NodoJugador cabJugador) {
        this.cabJugador = cabJugador;
    }

   
    public void adicionarNodo(Usuario datoUsuario) {
        if (cabJugador == null) {
            cabJugador = new NodoJugador(datoUsuario);
        } else {
            //Llamo a mi ayudante
            NodoJugador temp = cabJugador;
            while (temp.getSigUsuario() != null) //Mientras que en siguiente exista algo
            {
                temp = temp.getSigUsuario();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSigUsuario(new NodoJugador(datoUsuario));
            temp.getSigUsuario().setAntUsuario(temp);

        }
    }

    public void adicionarNodoAlInicio(Usuario datoUsuario) {
        if (cabJugador == null) {
            cabJugador = new NodoJugador(datoUsuario);
        } else {
            NodoJugador temp = new NodoJugador(datoUsuario);
            temp.setSigUsuario(cabJugador);
            cabJugador.setAntUsuario(temp);
            cabJugador = temp;
        }
    }

    public void adicionarNodoPosicion(int posicion, Usuario dato) throws UsuarioExcepcion {
        if (cabJugador != null) {
            if (posicion == 1) {
                adicionarNodoAlInicio(dato);               
            } else {
                int cont = 1;
                NodoJugador temp = cabJugador;
                while (temp != null) {
                    if ((posicion - 1) == cont) {
                        NodoJugador nodoInsertar = new NodoJugador(dato);
                        nodoInsertar.setSigUsuario(temp.getSigUsuario());
                        temp.setSigUsuario(nodoInsertar);
                        if(nodoInsertar.getSigUsuario()!=null)
                            nodoInsertar.getSigUsuario().setAntUsuario(nodoInsertar);
                        nodoInsertar.setAntUsuario(temp);
                        break ;
                    }
                    temp = temp.getSigUsuario();
                    cont++;
                }
            }
        }
        else{
            throw new UsuarioExcepcion(("La lista está vacía"));
        }
    }

    public short contarNodos() {
        if (cabJugador == null) {
            return 0;
        } else {
            //llamar a mi ayudante
            NodoJugador temp = cabJugador;
            short cont = 1;
            while (temp.getSigUsuario()!= null) {
                temp = temp.getSigUsuario();
                cont++;
            }
            return cont;
        }
    }

    public String obtenerListadoJugadores() {

        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        return ListaDEjugador.this.listarJugadores("");
    }

    public String listarJugadores(String listado) {
        if (cabJugador != null) {
            NodoJugador temp = cabJugador;
            while (temp != null) {
                listado += temp.getDatoUsuario()+ "\n";
                temp = temp.getSigUsuario();

            }
            return listado;
        }
        return "No hay jugadores";
    }

    public List obtenerListaJugadores() {
        List<Corredor> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarCorredores(listado);
        return listado;
    }

    public void listarCorredores(List listado) {
        if (cabJugador != null) {
            NodoJugador temp = cabJugador;
            while (temp != null) {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDatoUsuario());
                temp = temp.getSigUsuario();
            }
        }

    }


    public void invertirLista() {
        if (cabJugador != null) {
            //Crear una lista temporal la cabeza de la lista temporal está vacía
            ListaDEjugador listaTemporal = new ListaDEjugador(cabJugador);
            // Llamo un ayudante
            NodoJugador temp = cabJugador;
            //Recorro la lista de principio a fin
            while (temp != null) {
                //Parado en cada nodo , se extrae la información y se
                // envía a la otra lista al inicio
                listaTemporal.adicionarNodoAlInicio(temp.getDatoUsuario());
                temp = temp.getSigUsuario();
            }
            //Igualo la cabeza de mi lista principal a la cabeza de la lista temporal
            cabJugador = listaTemporal.getCabJugador();
        }
    }


    public void eliminarJugador(String correo) throws UsuarioExcepcion {
        if (cabJugador != null) {
            if (cabJugador.getDatoUsuario().getCorreo().compareTo(correo)==0) {
                cabJugador = cabJugador.getSigUsuario();
                cabJugador.setAntUsuario(null);
                return;
            } else {
                NodoJugador temp = cabJugador;
                while (temp.getSigUsuario()!= null) {
                    if (temp.getSigUsuario().getDatoUsuario().getCorreo().compareTo(correo) == 0) {
                        //el que sigue es el que hay que eliminar
                        temp.setSigUsuario(temp.getSigUsuario().getSigUsuario());
                        if (temp.getSigUsuario()!= null) {
                            temp.getSigUsuario().setAntUsuario(temp);
                        }
                        return;
                    }
                    temp = temp.getSigUsuario();
                }

                throw new UsuarioExcepcion("El correo " + correo + " no existe en la lista");
            }
        }
        throw new UsuarioExcepcion("La lista de Usuarios está vacía");
    }

    public Usuario obtenerUsuario(String correo) throws UsuarioExcepcion {
        if (cabJugador != null) {
            if (cabJugador.getDatoUsuario().getCorreo().compareTo(correo) == 0) {
                return cabJugador.getDatoUsuario();
            } else {
                NodoJugador temp = cabJugador;
                while (temp != null) {
                    if (temp.getDatoUsuario().getCorreo().compareTo(correo) == 0) {
                        return temp.getDatoUsuario();
                    }
                    temp = temp.getSigUsuario();
                }

                throw new UsuarioExcepcion("El correo " + correo + " no existe en la lista");
            }
        }
        throw new UsuarioExcepcion("La lista de Jugadores está vacía");
    }

    public Usuario obtenerJugadorMasFichas() throws UsuarioExcepcion {
        if (cabJugador != null) {
            Usuario masFichas = cabJugador.getDatoUsuario();
            NodoJugador temp = cabJugador;
            while (temp != null) {
                if (temp.getDatoUsuario().getnFichas()< masFichas.getnFichas()) {
                    masFichas = temp.getDatoUsuario();
                }
                temp = temp.getSigUsuario();
            }

            return masFichas;

        }
        throw new UsuarioExcepcion("La lista de Jugadores está vacía");
    }

    public int obtenerPosicionCorredor(String correo) throws UsuarioExcepcion {
        if (cabJugador != null) {
            int cont = 1;
            NodoJugador temp = cabJugador;
            while (temp != null) {
                if (temp.getDatoUsuario().getCorreo().compareTo(correo) == 0) {
                    return cont;
                }
                temp = temp.getSigUsuario();
                cont++;
            }
            throw new UsuarioExcepcion("El correo ingresado no ");

        }
        throw new UsuarioExcepcion("La lista de Jugadores está vacía");
    }

    public void adicionarNodo(com.listaenlazada.modelo.Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
