package Controlador;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: ControladorLogin.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BDManager.*;
import java.sql.Connection;
import Modelo.*;
import Vista.*;
import Paneles.*;
import java.sql.SQLException;

public class ControladorLogin implements ComunicadorClases{
    
    protected ModeloLogin modelo = new ModeloLogin();
    protected VistaIngresarRegistrarse vista = new VistaIngresarRegistrarse();
    protected Connection connection;
    
    public ControladorLogin(ModeloLogin modelo, VistaIngresarRegistrarse vista){
        this.modelo = modelo;
        this.vista = vista;
        
        // CargarBase de datos
        connection = UsuariosManager.iniciarConexion();
        
        // Añadir controladores
        
        
        // Cargar panel inicial
        cambiarALogin();
        
        // Configurar vista para mostrar
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);       
    }
    
    public void cambiarALogin(){
        SubmodeloIngresar submodelo = new SubmodeloIngresar();
        PanelIngresar panel = new PanelIngresar();
        SubcontroladorIngresar subcontrolador = new SubcontroladorIngresar(submodelo, panel);

        subcontrolador.setListener(this);
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarARegistrarse(){
        SubmodeloRegistrarse submodelo = new SubmodeloRegistrarse();
        PanelRegistrarse panel = new PanelRegistrarse();
        SubcontroladorRegistrarse subcontrolador = new SubcontroladorRegistrarse(submodelo, panel);
        
        subcontrolador.setListener(this);
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarADashboard(){
        vista.dispose();
        
        ModeloDashboardAdminEmpleado nuevoModelo = new ModeloDashboardAdminEmpleado();
        VistaDashboardAdminEmpleado nuevaVista = new VistaDashboardAdminEmpleado();
        ControladorDashboardAdminEmpleado nuevoControlador = new ControladorDashboardAdminEmpleado(nuevoModelo, nuevaVista);
        
        UsuariosManager.detenerConexion(connection);
    }
    
    // Capturador de solicitudes de controladores internos
    @Override
    public void mensaje(String solicitud) {
        String opcion = solicitud;

        switch (opcion) {
            case"SolicitudCambioVistaDashboard":
                cambiarADashboard();            
            break;
            case"SolicitudCambioPanelIngresar":
                cambiarALogin();            
            break;
            case"SolicitudCambioPanelRegistrarse":
                cambiarARegistrarse();            
            break;
            default:
                System.out.println("Solicitud " + solicitud + " imposible de atender");
                break;
        }
    }

}
