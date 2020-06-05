/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listaspirinola.modelo;
import com.listaenlazada.controlador.UsuarioController;
import com.listaenlazada.controlador.UsuarioFacade;
import com.listaenlazada.controlador.util.JsfUtil;
import com.listaenlazada.modelo.Usuario;
import com.listase.modelo.ListaDEjugador;
import com.listase.modelo.NodoJugador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
/**
 *
 * @author Marin
 */
public class PirinolaApp {
    
   private String correoTurno="estebanmarin17@hotmail.com";    
    private int fichasJuego;   
    private int nTurnos;
    
    private List<Usuario> listadoUsuarios;
    @EJB
    private UsuarioFacade connUsuario;
    
    private NodoJugador jugadorActual;
    
    private Usuario usuarioSeleccionado;
    
    private ListaDEjugador listaCircularJugador;
    
    private NodoJugador ayudante;
    
    private boolean verInicio=true;
    
    private DefaultDiagramModel model;
    
    public PirinolaApp() {
        fichasJuego = 2;
        nTurnos = 20;
        
        listaCircularJugador = new ListaDEjugador();

        cargarJugadores();

     //  correoTurno = UsuarioController.getUsuarios().get(0).getCorreo();
        
        jugadorActual = listaCircularJugador.getCabJugador();
        
        pintarJugadores();
    }
    
    @PostConstruct
    public void inicializar()
    {
            model = new DefaultDiagramModel();
            model.setMaxConnections(-1);
            model.setConnectionsDetachable(false);
            
            Element elementA = new Element("Toma todo", "600px", "500px");
            elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
            Element elementB = new Element("Toma uno", "500px", "40px");
            elementB.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementC = new Element("Toma dos", "400px", "400px");
            elementC.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
            
            Element elementD = new Element("Todos ponen", "500px", "100px");
            elementD.addEndPoint(new DotEndPoint(EndPointAnchor.ASSIGN));
        
            Element elementE = new Element("Pon uno", "100px", "500px");
            elementE.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementF = new Element("Pon dos", "100px", "300px");
            elementF.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
            model.addElement(elementA);
            model.addElement(elementB);
            model.addElement(elementC);
            model.addElement(elementD);
            model.addElement(elementE);
            model.addElement(elementF);
        
            model.connect(new Connection(elementA.getEndPoints().get(0), elementB.getEndPoints().get(0)));        
            model.connect(new Connection(elementA.getEndPoints().get(0), elementC.getEndPoints().get(0)));
            model.connect(new Connection(elementB.getEndPoints().get(0), elementD.getEndPoints().get(0)));
            model.connect(new Connection(elementC.getEndPoints().get(0), elementE.getEndPoints().get(0)));
            model.connect(new Connection(elementF.getEndPoints().get(0), elementE.getEndPoints().get(0)));        
            model.connect(new Connection(elementF.getEndPoints().get(0), elementD.getEndPoints().get(0)));
            
            
        listadoUsuarios = (List<Usuario>) connUsuario;
        //recorrer el listado y envio el Corredor a la lista SE
        for(Usuario user:listadoUsuarios)
        {
            listaCircularJugador.adicionarNodo(user);
        }
        
        ayudante = listaCircularJugador.getCabJugador();
     //   usuarioSeleccionado = ayudante.getDatoUsuario();       
        
    }
    
    private void cargarJugadores(){
        listaCircularJugador = new ListaDEjugador();
        listadoUsuarios = connUsuario.findAll();
        for (Usuario usuario : listadoUsuarios) {
            listadoUsuarios.add(
                    new Usuario(usuario.getCorreo(), usuario.getNombreCompleto(), usuario.getContrasenia())
            );
        }
        
        if(listaCircularJugador.getCabJugador()!=null)
        {
            ayudante = listaCircularJugador.getCabJugador();
         //   usuarioSeleccionado = ayudante.getDatoUsuario();     
        }
        else
        {
            usuarioSeleccionado = new Usuario();
        }
    }

    public boolean isVerInicio() {
        return verInicio;
    }

    public void setVerInicio(boolean verInicio) {
        this.verInicio = verInicio;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public ListaDEjugador getListaCircularJugador() {
        return listaCircularJugador;
    }

    public void setListaCircularJugador(ListaDEjugador listaCircularJugador) {
        this.listaCircularJugador = listaCircularJugador;
    }

    public int getFichasJuego() {
        return fichasJuego;
    }

    public void setFichasJuego(int fichasJuego) {
        this.fichasJuego = fichasJuego;
    }

    public List<Usuario> getListadoUsuarios() {
        return listadoUsuarios;
    }

    public void setListadoUsuarios(List<Usuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }
    

    public String getCorreoTurno() {
        return correoTurno;
    }

    public void setCorreoTurno(String correoTurno) {
        this.correoTurno = correoTurno;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public NodoJugador getAyudante() {
        return ayudante;
    }

    public void setAyudante(NodoJugador ayudante) {
        this.ayudante = ayudante;
    }
    

    public void pintarJugadores() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaCircularJugador.getCabJugador() != null) {
            //llamo a mi jugadorActual
            NodoJugador temp = listaCircularJugador.getCabJugador();

            /**
             * variables para la posicion de los elementos en el diagrama:
             * 
             *      'posY' y 'posX': indican el centro del circulo
             *      'numElementos': indica el total de los puntos que se van a distribuir
             *      'angle': indica el ángulo en el que se dibujará el punto
             *      'cont': indica el número 
             */
            double posX;
            double posY;
            int numElementos = listaCircularJugador.contarNodos();
            double angle;
            int cont = 0;//
            
            
            //recorro la lista de principio a fin
            do {
                

                //calculando la posición del elemento:
                angle = (2 * Math.PI * cont) / numElementos;
                
                //para acomodar el primer jugador a 90 grados de la horizontal:
                angle += (1.5 * Math.PI);

                posX = 35 + (15 * Math.cos(angle));
                posY = 15 + (15 * Math.sin(angle));
                cont++;
                
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDatoUsuario().getNombreCompleto()+ ": " + temp.getDatoUsuario().getnFichas(),
                        posX + "em", posY + "em");
                ele.setId(String.valueOf(temp.getDatoUsuario().getNombreCompleto()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));

                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));

                //si es el primer elemento, añadirlo a la clase css que lo pone morado.
                if (temp == listaCircularJugador.getCabJugador()) {
                    ele.setStyleClass("ui-diagram-primero");
                }

                model.addElement(ele);

                temp = temp.getSigUsuario();

            } while (temp != listaCircularJugador.getCabJugador());

            //Pinta las flechas            
            for (int i = 0; i < model.getElements().size() - 1; i++) {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1),
                        model.getElements().get(i + 1).getEndPoints().get(0), "Sig"));

                model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(2),
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }
            
            //pone un elemento en el centro del diagrama con las fichas en la caja
            model.addElement(new Element("Fichas en juego: " + this.fichasJuego, 35 + "em", 15 + "em"));
        }
    }
    
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }
    
    public void aumentarContador(String correo)
    {
        switch(correo)
        {
            case "estebanmarin17@hotmail.com":
                correoTurno= "ejemplo@ejemplo.com";
                break;
            default:
                correoTurno= "estebanmarin17@hotmail.com";
        }
        
        fichasJuego++;
    }
    
    public boolean validarTurno(String correo)
    {
        return correo.equals(correoTurno);
    }
    
    
    public void pasarTingo()
    {        
       if(!verInicio)
       {
            ayudante = ayudante.getSigUsuario();
           // usuarioSeleccionado = ayudante.getDatoUsuario();
       }
       
    }
    
    public void controlarCiclo()
    {
        //False fue por que va a parar
        if(!verInicio)
        {
            //Eliminaría el niño . Valido lo seleccionado
            for(Usuario inf: listadoUsuarios)
            {
                if(inf.getCorreo().compareTo(usuarioSeleccionado.getCorreo()) == 0)
                {
                    listadoUsuarios.remove(inf);
                    break;
                }
            }    
            
            if(listadoUsuarios.size()==1)
            {
                JsfUtil.addSuccessMessage("Ha ganado "+listadoUsuarios.get(0));
            }
            
        }    
        verInicio = !verInicio;
    }
    
}

