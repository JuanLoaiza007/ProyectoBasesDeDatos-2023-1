package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorAvanzado.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.AvisosEmergentes;
import Paneles.PanelAvanzado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubcontroladorAvanzado {
    
    protected PanelAvanzado panel = new PanelAvanzado();
    protected String idInterno;
    protected ComunicadorClases decirAInstanciaSuperior;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorAvanzado(PanelAvanzado panel){        
        this.panel = panel;
        
        panel.addListenerUsuarios(oyenteMostrarPanelUsuarios);        
        panel.addListenerEmpleados(oyenteMostrarPanelEmpleados);      
        panel.addListenerCambiarContrasena(oyenteCambiarContrasena);
    }
    
    // ------------------ METODOS ------------------
    public void comprobarAcceso(){
        if(idInterno.equals("admin")){           
            panel.habilitarVerEmpleados();
        }
        else{
            panel.deshabilitarVerEmpleados();
        }
    }
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteMostrarPanelUsuarios = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelUsuarios");
        }
    };
    
    ActionListener oyenteMostrarPanelEmpleados = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelEmpleados");
        }
    };
    
    ActionListener oyenteCambiarContrasena = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            AvisosEmergentes.mostrarMensaje("Esta opción aún está en desarrollo, consulte a su administrador \n"
                    + " de bases de datos para hacer el cambio de contraseña manualmente.");
        }
    };
   

    // ------------------ SETTERS Y GETTERS  ------------------
     public String getIdInterno() {    
        return idInterno;
    }
     
    public void setIdInterno(String idInterno) {    
        this.idInterno = idInterno;
    }

    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}

