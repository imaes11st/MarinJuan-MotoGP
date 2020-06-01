/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listaenlazada.controlador.CorredorFacade;
import com.listase.excepciones.CorredorExcepcion;
//import com.listase.modelo.Infante;
import com.listaenlazada.modelo.Corredor;
import com.listase.modelo.ListaSE;
import com.listase.modelo.Nodo;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
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
@Named(value = "sesionCorredor")
@SessionScoped
public class SesionCorredor implements Serializable {
    private ListaSE listaCorredores;
    private Corredor corredor;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private Nodo ayudante;   
    private String textoVista="Gráfico";
    
    private List<Corredor> listadoCorredores;
    
    private DefaultDiagramModel model;
    
    private short codigoEliminar;
    
    private ControladorLocalidades controlLocalidades;
    
    private String codigoDeptoSel;
    
    private short corredorSeleccionado;
    
    private Corredor corredorDiagrama;
    
    @EJB
    private CorredorFacade connCorredor;
    
    /**
     * Creates a new instance of SesionInfante
     */
    public SesionCorredor() {        
    }
    
    @PostConstruct
    public void inicializar()
    {
        controlLocalidades = new ControladorLocalidades();
        //inicializando el combo en el primer depto
        codigoDeptoSel = controlLocalidades.getDepartamentos().get(0).getCodigo();
        
        listaCorredores = new ListaSE();

        listadoCorredores=connCorredor.findAll();
        //recorrer el listado y envio el infante a laista SE
        for(Corredor cor:listadoCorredores)
        {
            listaCorredores.adicionarNodo(cor);
        }
        
        //LLenado de la bds
//        listaInfantes.adicionarNodo(new Infante("Carlitos",(short) 1, (byte)2, true,
//                controlLocalidades.getCiudades().get(0).getNombre()));
//        listaInfantes.adicionarNodo(new Infante("Juanita",(short) 2, (byte)3, false,
//        controlLocalidades.getCiudades().get(3).getNombre()));
//        listaInfantes.adicionarNodo(new Infante("Martina",(short) 3, (byte)1,false,
//        controlLocalidades.getCiudades().get(1).getNombre()));
//        listaInfantes.adicionarNodoAlInicio(new Infante("Mariana",(short) 4, (byte)5,false,
//        controlLocalidades.getCiudades().get(2).getNombre()));
        if(listaCorredores.getCabeza()!=null)
        {
            ayudante = listaCorredores.getCabeza();
            corredor = ayudante.getDato();     
        }
        else
        {
            corredor = new Corredor();
        }
        //Me llena el objeto List para la tabla
        //listadoCorredores = listaCorredores.obtenerListaCorredores();
        pintarLista();
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
        inicializar();
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
    
    public ListaSE getListaCorredores() {
        return listaCorredores;
    }

    public void setListaCorredores(ListaSE listaCorredores) {
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
        //Guardo en bds
        connCorredor.create(corredor);
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
            Nodo temp = listaCorredores.getCabeza();
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
                connCorredor.remove(listaCorredores.obtenerCorredor(codigoEliminar));
                listaCorredores.eliminarCorredor(codigoEliminar);
                //Eliminamos de bds
                
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
    
    
    
}
