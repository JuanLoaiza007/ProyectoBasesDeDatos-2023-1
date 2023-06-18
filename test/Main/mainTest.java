package Main;

import BasesDeDatos.*;
import Controladores.*;
import Modelos.*;
import Vista.*;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: mainTest.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

        
public class mainTest {
    public static void main(String[] args) {       
        VistaDashboardAdminEmpleado nuevaVista = new VistaDashboardAdminEmpleado();
        ControladorDashboardAdminEmpleado nuevoControlador = new ControladorDashboardAdminEmpleado(nuevaVista);
        
        nuevoControlador.setIdInterno("admin");
        
        nuevoControlador.cambiarAPanelMultas();
        
        GenerarMultasDao.generarMultas(BibliotecaManager.iniciarConexion());
    }
}
