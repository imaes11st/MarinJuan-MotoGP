/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.excepciones.CorredorExcepcion;
//import com.listase.modelo.Infante;
import com.listaenlazada.modelo.Corredor;
import com.listase.modelo.ListaDE;
import com.listase.modelo.ListaSE;
import com.listase.modelo.Nodo;
import com.listase.modelo.NodoDE;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author carloaiza
 */
@Named(value = "sesionCorredorDE")
@SessionScoped
public class SesionCorredorDE implements Serializable {
    private ListaDE listaCorredores;
    private Corredor corredor;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private NodoDE ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoCorredores;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short corredorSeleccionado;
    
    private Corredor corredorDiagrama;
    
    private int posicionCorredor;
    
    private String opcionElegida="1";
    
    private int numeroPosiciones=1;
    
    /**
     * Creates a new instance of SesionInfante
     */
    public SesionCorredorDE() {        
    }
    
    @PostConstruct
    private void inicializar()
    {
        controlLocalidades = new ControladorLocalidades();
        //inicializando el combo en el primer depto
        codigoDeptoSel = controlLocalidades.getDepartamentos().get(0).getCodigo();
        
        listaCorredores = new ListaDE();        
        //LLenado de la bds
//        listaInfantes.adicionarNodo(new Infante("Carlitos",(short) 1, (byte)2, true,
//                controlLocalidades.getCiudades().get(0).getNombre()));
//        listaInfantes.adicionarNodo(new Infante("Juanita",(short) 2, (byte)3, false,
//        controlLocalidades.getCiudades().get(3).getNombre()));
//        listaInfantes.adicionarNodo(new Infante("Martina",(short) 3, (byte)1,false,
//        controlLocalidades.getCiudades().get(1).getNombre()));
//        listaInfantes.adicionarNodoAlInicio(new Infante("Mariana",(short) 4, (byte)5,false,
//        controlLocalidades.getCiudades().get(2).getNombre()));
//        ayudante = listaInfantes.getCabeza();
//        infante = ayudante.getDato();     
        //Me llena el objeto List para la tabla
        listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
        
        
   }

    public String getOpcionElegida() {
        return opcionElegida;
    }

    public void setOpcionElegida(String opcionElegida) {
        this.opcionElegida = opcionElegida;
    }

    public int getNumeroPosiciones() {
        return numeroPosiciones;
    }

    public void setNumeroPosiciones(int numeroPosiciones) {
        this.numeroPosiciones = numeroPosiciones;
    }
    
    

    public int getPosicionCorredor() {
        return posicionCorredor;
    }

    public void setPosicionCorredor(int posicionCorredor) {
        this.posicionCorredor = posicionCorredor;
    }
    
    

    public Corredor getCorredorDiagrama() {
        return corredorDiagrama;
    }

    public void setCorredorDiagrama(Corredor corredorDiagrama) {
        this.corredorDiagrama = corredorDiagrama;
    }
    
    

    public short getCorredorSeleccionado() {
        return corredorSeleccionado;
    }

    public void setCorredorSeleccionado(short corredorSeleccionado) {
        this.corredorSeleccionado = corredorSeleccionado;
    }
    
    

    public String getCodigoDeptoSel() {
        return codigoDeptoSel;
    }

    public void setCodigoDeptoSel(String codigoDeptoSel) {
        this.codigoDeptoSel = codigoDeptoSel;
    }

    
    
    public ControladorLocalidades getControlLocalidades() {
        return controlLocalidades;
    }

    public void setControlLocalidades(ControladorLocalidades controlLocalidades) {
        this.controlLocalidades = controlLocalidades;
    }
     
    
    
    public DiagramModel getModel() {
        return model;
    }
     
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }

    public short getCodigoEliminar() {
        return codigoEliminar;
    }

    public void setCodigoEliminar(short codigoEliminar) {
        this.codigoEliminar = codigoEliminar;
    }

    
    
    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }

    
    
    public List getListadoCorredores() {
        return listadoCorredores;
    }

    public void setListadoCorredores(List listadoCorredores) {
        this.listadoCorredores = listadoCorredores;
    }
    
    

    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

  
    
    

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }
    
    public ListaDE getListaCorredores() {
        return listaCorredores;
    }

    public void setListaCorredores(ListaDE listaCorredores) {
        this.listaCorredores = listaCorredores;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }
    
    
    
    public void guardarCorredor()
    {
        //obtiene el consecutivo
        corredor.setCodigo((short)(listaCorredores.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaCorredores.adicionarNodoAlInicio(corredor);
        }
        else
        {
            listaCorredores.adicionarNodo(corredor);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El corredor se ha guardado exitosamente");
        
    }
    
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        corredor = new Corredor();
    }
    
    public void irAnterior()
    {
        if(ayudante.getAnterior()!=null)
        {
            ayudante = ayudante.getAnterior();
            corredor = ayudante.getDato();
        }        
    }
    
    
    public void irSiguiente()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            corredor = ayudante.getDato();
        }        
    }
    
    public void irPrimero()
    {
        if(listaCorredores.getCabeza()!=null)
        {
            ayudante = listaCorredores.getCabeza();
            corredor = ayudante.getDato();
            
        }
        else
        {
            corredor = new Corredor();
        }
        listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
             
    }
    
    public void irUltimo()
    {
        if(listaCorredores.getCabeza()!=null)
        {            
            while(ayudante.getSiguiente()!=null)
            {
                ayudante = ayudante.getSiguiente();
            }
            corredor=ayudante.getDato();
        }
    }
    
    public void cambiarVistaCorredores()
    {
        if(textoVista.compareTo("Tabla")==0)
        {
            textoVista = "Gráfico";
        }
        else
        {
            textoVista = "Tabla";
        }
    }
    
    public void invertirLista(){
        //Invierte la lista
        listaCorredores.invertirLista();
        irPrimero();
    }
    
    
    public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaCorredores.getCabeza() != null) {
            //llamo a mi ayudante
            NodoDE temp = listaCorredores.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo()+" "+
                        temp.getDato().getNombre(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
           
            //Pinta las flechas            
            for(int i=0; i < model.getElements().size() -1; i++)
            {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1), 
                        model.getElements().get(i+1).getEndPoints().get(0), "Sig"));
                
                
                model.connect(createConnection(model.getElements().get(i+1).getEndPoints().get(2), 
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }
            
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        corredorSeleccionado = Short.valueOf(id.replaceAll("frmCorredor:diagrama-", ""));
        
    }

    public void eliminarCorredor()
    {
        if(codigoEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaCorredores.eliminarCorredor(codigoEliminar);
                irPrimero();
                JsfUtil.addSuccessMessage("Corredor "+codigoEliminar +" eliminado.");
            }
            catch(CorredorExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+codigoEliminar+ " no es válido");
        }
    }
    
    
    public void obtenerCorredorDiagrama()
    {
        try {
            corredorDiagrama = listaCorredores.obtenerCorredor(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void obtenerCorredorMenor()
    {
        try {
            corredorDiagrama = listaCorredores.obtenerCorredorMenorEdad();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void obtenerPosicionCorredor()
    {
        try {
            posicionCorredor = listaCorredores.obtenerPosicionCorredor(corredorSeleccionado);
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    
    public void enviarAlFinal()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Corredor corTemporal = listaCorredores.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredores.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al final
            listaCorredores.adicionarNodo(corTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void enviarAlInicio()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Corredor corTemporal = listaCorredores.obtenerCorredor(corredorSeleccionado);
            // Eliminar el nodo
            listaCorredores.eliminarCorredor(corredorSeleccionado);
            // Adicionarlo al inicio
            listaCorredores.adicionarNodoAlInicio(corTemporal);
            
            pintarLista();
        } catch (CorredorExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void cambiarPosicion()
    {
        boolean bandera=false;
        int posicionFinal=0;
        switch(opcionElegida)
        {
            //Ganar
            case "1":
                if(numeroPosiciones <= (posicionCorredor-1) )
                {
                    bandera=true;
                    posicionFinal = posicionCorredor - numeroPosiciones;
                }
                break;
            //Perder
            case "0":
                if(numeroPosiciones <= (listaCorredores.contarNodos()-posicionCorredor))
                {
                    bandera=true;
                    posicionFinal = posicionCorredor + numeroPosiciones;
                }
                break;
        }
        
        if(bandera)
        {
            try {
                //Realizaria la función de insertar
                Corredor datosCorredor = listaCorredores.obtenerCorredor(corredorSeleccionado);
                // cambia la cantidad de infantes
                listaCorredores.eliminarCorredor(corredorSeleccionado);
                listaCorredores.adicionarNodoPosicion(posicionFinal, datosCorredor);
                irPrimero();
                JsfUtil.addSuccessMessage("Se ha realizado el cambio");
                
                
            } catch (CorredorExcepcion ex) {
               JsfUtil.addErrorMessage(ex.getMessage());
            }
            
        }
        else
        {
            JsfUtil.addErrorMessage("El número de posiciones no es válido para el corredor dado");
        }
    }
    
    
}
