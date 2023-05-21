package Main;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: main.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Modelo.*;
import Vista.*;
import Controlador.*;
import BasesDeDatos.UsuariosManager;

        
public class main {
    public static void main(String[] args) {       
        ModeloLogin modelo = new ModeloLogin();
        VistaIngresarRegistrarse vista = new VistaIngresarRegistrarse();
        ControladorLogin controlador = new ControladorLogin(modelo, vista);
    }
}
