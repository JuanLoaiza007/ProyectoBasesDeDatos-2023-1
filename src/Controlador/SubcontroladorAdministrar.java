package Controlador;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorAdministrar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Modelo.*;
import Paneles.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubcontroladorAdministrar {
    
    protected SubmodeloAdministrar submodelo = new SubmodeloAdministrar();
    protected PanelAdministrar panel = new PanelAdministrar();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorAdministrar(SubmodeloAdministrar submodelo, PanelAdministrar panel){
        this.submodelo = submodelo;
        this.panel = panel;
        
        panel.addListenerUbicaciones(oyenteMostrarPanelUbicaciones);
        panel.addListenerEditoriales(oyenteMostrarPanelEditoriales);
        panel.addListenerAutores(oyenteMostrarPanelAutores);
        panel.addListenerLibros(oyenteMostrarPanelLibros);
        panel.addListenerEjemplares(oyenteMostrarPanelEjemplares);
        panel.addListenerLibrosDigitales(oyenteMostrarPanelLibrosDigitales);
        panel.addListenerAreasConocimiento(oyenteMostrarPanelAreasConocimiento);
        
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    ActionListener oyenteMostrarPanelUbicaciones = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelUbicaciones");
        }
    };
    
    ActionListener oyenteMostrarPanelEditoriales = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelEditoriales");
        }
    };
    
    ActionListener oyenteMostrarPanelAutores = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAutores");
        }
    };
    
    ActionListener oyenteMostrarPanelLibros = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelLibros");
        }
    };
    
    ActionListener oyenteMostrarPanelEjemplares = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelEjemplares");
        }
    };
    
    ActionListener oyenteMostrarPanelLibrosDigitales = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelLibrosDigitales");
        }
    };
    
    ActionListener oyenteMostrarPanelAreasConocimiento = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAreasConocimiento");
        }
    };

}
