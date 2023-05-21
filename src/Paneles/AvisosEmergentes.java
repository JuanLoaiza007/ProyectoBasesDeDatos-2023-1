package Paneles;

import javax.swing.JOptionPane;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: AvisosEmergentes.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class AvisosEmergentes {
    
    public static void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public static boolean preguntarYesOrNo(String mensaje){
        int respuesta = JOptionPane.showConfirmDialog(null,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
        
        if(respuesta == JOptionPane.YES_OPTION){
            return true;
        } else return false;
    }
}
