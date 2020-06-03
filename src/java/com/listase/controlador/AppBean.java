/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listacircularde.modelo.ListaCircularDE;
import com.listaenlazada.controlador.CorredorFacade;
import com.listaenlazada.controlador.util.JsfUtil;
import com.listaenlazada.modelo.Corredor;
import com.listase.modelo.NodoDE;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.DiagramModel;

/**
 *
 * @author Marin
 */
@Named(value = "appBean")
@ApplicationScoped
public class AppBean {
    
    private String correoTurno="estebanmarin17@hotmail.com";    
    private int cont=0;   
    
    private List<Corredor> listadoCorredores;
    @EJB
    private CorredorFacade connCorredor;
    
    private Corredor corredorSeleccionado;
    
    private ListaCircularDE listaCircularCorredores;
    
    private NodoDE ayudante;
    
    private boolean verInicio=true;
    
    private DefaultDiagramModel model;
    
    /**
     * Creates a new instance of AppBean
     */
    public AppBean() {
    }
    
    @PostConstruct
    public void inicializar()
    {
            model = new DefaultDiagramModel();
            model.setMaxConnections(-1);
            model.setConnectionsDetachable(false);
            
            Element elementA = new Element("Toma todo", "10em", "6em");
            elementA.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
            Element elementB = new Element("Toma uno", "20em", "18em");
            elementB.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementC = new Element("Toma dos", "30em", "18em");
            elementC.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
            
            Element elementD = new Element("Todos ponen", "60em", "30em");
            elementD.addEndPoint(new DotEndPoint(EndPointAnchor.ASSIGN));
        
            Element elementE = new Element("Pon uno", "40em", "18em");
            elementE.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
        
            Element elementF = new Element("Pon dos", "50em", "18em");
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
            
            
        listadoCorredores = connCorredor.findAll();
        listaCircularCorredores = new ListaCircularDE();
        //recorrer el listado y envio el Corredor a la lista SE
        for(Corredor inf:listadoCorredores)
        {
            listaCircularCorredores.adicionarNodo(inf);
        }
        
        ayudante = listaCircularCorredores.getCabeza();
        corredorSeleccionado = ayudante.getDato();       
        
    }

    public boolean isVerInicio() {
        return verInicio;
    }

    public void setVerInicio(boolean verInicio) {
        this.verInicio = verInicio;
    }
    
    

    public Corredor getCorredorSeleccionado() {
        return corredorSeleccionado;
    }

    public void setCorredorSeleccionado(Corredor corredorSeleccionado) {
        this.corredorSeleccionado = corredorSeleccionado;
    }

    public ListaCircularDE getListaCircularCorredores() {
        return listaCircularCorredores;
    }

    public void setListaCircularCorredores(ListaCircularDE listaCircularCorredores) {
        this.listaCircularCorredores = listaCircularCorredores;
    }
    
    

    public List<Corredor> getListadoCorredores() {
        return listadoCorredores;
    }

    public void setListadoCorredores(List<Corredor> listadoCorredores) {
        this.listadoCorredores = listadoCorredores;
    }

    
    
    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
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
    
    public void aumentarContador(String correo)
    {
        switch(correo)
        {
            case "estebanmarin17@hotmail.com":
                correoTurno= "consulta@hotmail.com";
                break;
            default:
                correoTurno= "estebanmarin17@hotmail.com";
        }
        
        cont++;
    }
    
    public boolean validarTurno(String correo)
    {
        if(correo.equals(correoTurno))
        {
            return true;
        }
        return false;
    }
    
    
    public void pasarTingo()
    {        
       if(!verInicio)
       {
            ayudante = ayudante.getSiguiente();
            corredorSeleccionado = ayudante.getDato();
       }
       
    }
    
    public void controlarCiclo()
    {
        //False fue por que va a parar
        if(!verInicio)
        {
            //Eliminaría el niño . Valido lo seleccionado
            for(Corredor inf: listadoCorredores)
            {
                if(inf.getCodigo() == corredorSeleccionado.getCodigo())
                {
                    listadoCorredores.remove(inf);
                    break;
                }
            }    
            
            if(listadoCorredores.size()==1)
            {
                JsfUtil.addSuccessMessage("Ha ganado "+listadoCorredores.get(0));
            }
            
        }    
        verInicio = !verInicio;
    }
    
    
}
