package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorUsuarios.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.PanelUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubcontroladorUsuarios {
    
    protected PanelUsuarios panel = new PanelUsuarios();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorUsuarios(PanelUsuarios panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAvanzado);
        
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    ActionListener oyenteMostrarPanelAvanzado = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAvanzado");
        }
    };
}
