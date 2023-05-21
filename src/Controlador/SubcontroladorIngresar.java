package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: SubcontroladorIngresar.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Modelo.*;
import Paneles.*;

public class SubcontroladorIngresar {
    
    protected SubmodeloIngresar modelo = new SubmodeloIngresar();
    protected PanelIngresar panel = new PanelIngresar();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorIngresar(SubmodeloIngresar modelo, PanelIngresar panel){
        this.modelo = modelo;
        this.panel = panel;
        
        panel.addActionIngresar(oyenteIngresar);
        panel.addActionRegistrarse(oyenteRegistrarse);
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }

    ActionListener oyenteIngresar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            decirAInstanciaSuperior.mensaje("SolicitudCambioVistaDashboard");
        }
    };
    
    ActionListener oyenteRegistrarse = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            decirAInstanciaSuperior.mensaje("SolicitudCambioPanelRegistrarse");
        }
    };
}
