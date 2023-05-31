package Controlador;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorRegistrarse.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class SubcontroladorRegistrarse {
    
    protected PanelRegistrarse panel = new PanelRegistrarse();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorRegistrarse(PanelRegistrarse panel){
        this.panel = panel;
        
        panel.addActionRegistrar(oyenteRegistrar);
        panel.addActionVolver(oyenteVolver);
        panel.addActionBoxTipoUsuario(oyenteTipoUsuario);
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }

    ActionListener oyenteRegistrar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Accion de registrar");
        }
    };
    
    ActionListener oyenteVolver = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            decirAInstanciaSuperior.mensaje("SolicitudCambioPanelIngresar");
        }
    };
    
    /**
     * Carga solo los productos que pertenecen al proveedor elegido
     */
    ActionListener oyenteTipoUsuario = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                JComboBox box = (JComboBox) evt.getSource();
                String tipoUsuarioSeleccionado = box.getSelectedItem().toString(); 
                
                if(tipoUsuarioSeleccionado == "Estudiante"){
                    panel.setAtributosEstudiante();
                }
                else if(tipoUsuarioSeleccionado == "Profesor"){
                    panel.setAtributosProfesor();
                }
                
            } catch(NullPointerException e) {
                //  Este proveedor aun no tiene productos para ofrecer
            }
        } 
    };

}
