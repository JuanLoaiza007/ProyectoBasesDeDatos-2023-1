package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorLibros.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.uPanelLibros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class uSubcontroladorLibros {
    
    protected uPanelLibros panel = new uPanelLibros();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorLibros(uPanelLibros panel){        
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelPrincipal);
        panel.addListenerSolicitar(oyenteSolicitar);
        panel.addListenerCancelar(oyenteCancelar);
        
        panel.modoPasivo();
        
    }
    
    
    // ------------------ METODOS ------------------
    private void recargarTodo(){
        panel.limpiarCampos();
        panel.reiniciarBoxes();
    }
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteMostrarPanelPrincipal = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelPrincipal");
        }
    };
    
    ActionListener oyenteSolicitar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoSolicitar();
        }
    };
    
    ActionListener oyenteCancelar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoPasivo();
            panel.limpiarCampos();
            panel.reiniciarBoxes();
        }
    };
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
