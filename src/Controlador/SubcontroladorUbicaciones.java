package Controlador;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorUbicaciones.java
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
import Objetos.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubcontroladorUbicaciones {
    
    protected SubmodeloUbicaciones submodelo = new SubmodeloUbicaciones();
    protected PanelUbicaciones panel = new PanelUbicaciones();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorUbicaciones(SubmodeloUbicaciones submodelo, PanelUbicaciones panel){
        this.submodelo = submodelo;
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
        panel.addListenerBuscarUbicacion(oyenteBuscarUbicacion);
        
        cargarUbicaciones();
        
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    public void cargarUbicaciones(){
        List<Ubicacion> ubicaciones = submodelo.getUbicaciones();
        
        for(Ubicacion ubicacionActual: ubicaciones){
            String id_ubicacion = ubicacionActual.getIdUbicacion();
            String nombre_sala = ubicacionActual.getNombreSala();
            String nro_pasillo = Integer.toString(ubicacionActual.getNroPasillo());
            String estante = Integer.toString(ubicacionActual.getEstante());
            String nro_cajon = Integer.toString(ubicacionActual.getNroCajon());
            
            panel.nuevaFilaTabla(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
        }
    }
    
    public void buscarUbicacion(){
        panel.limpiarTabla();
        
        
        String parametro = panel.getTxtfBuscar().getText();
        
        if(!parametro.isEmpty()){
            panel.limpiarTabla();
            List<Ubicacion> ubicaciones = submodelo.getUbicacion(parametro);
            
            for(Ubicacion ubicacionActual: ubicaciones){
                String id_ubicacion = ubicacionActual.getIdUbicacion();
                String nombre_sala = ubicacionActual.getNombreSala();
                String nro_pasillo = Integer.toString(ubicacionActual.getNroPasillo());
                String estante = Integer.toString(ubicacionActual.getEstante());
                String nro_cajon = Integer.toString(ubicacionActual.getNroCajon());

                panel.nuevaFilaTabla(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
            }
        } else {
            cargarUbicaciones();
        }
    }
    
    ActionListener oyenteMostrarPanelAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAdministrar");
        }
    };
    
    ActionListener oyenteBuscarUbicacion = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarUbicacion();
        }
    };

}
