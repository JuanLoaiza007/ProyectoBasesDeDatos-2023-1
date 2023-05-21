package Controlador;

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

import Modelo.*;
import Paneles.*;

public class SubcontroladorAvanzado {
    
    protected SubmodeloAvanzado submodelo = new SubmodeloAvanzado();
    protected PanelAvanzado panel = new PanelAvanzado();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorAvanzado(SubmodeloAvanzado submodelo, PanelAvanzado panel){
        this.submodelo = submodelo;
        this.panel = panel;
        
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }

}

