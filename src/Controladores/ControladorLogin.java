package Controladores;

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

import Paneles.PanelRegistrarse;
import Paneles.PanelIngresar;
import Vista.*;

public class ControladorLogin implements ComunicadorClases{
    
    protected VistaIngresarRegistrarse vista = new VistaIngresarRegistrarse();    
    
    /**
     * Constructor de la clase
     * @param vista Una vista de tipo JFrame
     */
    public ControladorLogin(VistaIngresarRegistrarse vista){
        this.vista = vista;
        
        // Cargar panel inicial
        cambiarALogin();
        
        // Configurar vista para mostrar
        vista.setLocationRelativeTo(null);
        vista.setResizable(false);
        vista.setVisible(true);   
    }
    
    
    // ------------------ METODOS ------------------
    public void cambiarALogin(){
        PanelIngresar panel = new PanelIngresar();
        SubcontroladorIngresar subcontrolador = new SubcontroladorIngresar(panel);

        subcontrolador.setListener(this);
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarARegistrarse(){
        PanelRegistrarse panel = new PanelRegistrarse();
        SubcontroladorRegistrarse subcontrolador = new SubcontroladorRegistrarse(panel);
        
        subcontrolador.setListener(this);
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarADashboardUsuario(String id){
        vista.dispose();
                
        VistaDashboardUsuario nuevaVista = new VistaDashboardUsuario();
        ControladorDashboardUsuario nuevoControlador = new ControladorDashboardUsuario(nuevaVista);
        
        nuevoControlador.setIdInterno(id);
    }
    
    public void cambiarADashboardEmpleado(String id){
        vista.dispose();
                
        VistaDashboardAdminEmpleado nuevaVista = new VistaDashboardAdminEmpleado();
        ControladorDashboardAdminEmpleado nuevoControlador = new ControladorDashboardAdminEmpleado(nuevaVista);
        nuevoControlador.setIdInterno(id);
        
        nuevoControlador.comprobarRol();
    }
    
    public String obtenerIdUsuarioEmpleado(String cadena) {
        int indiceArroba = cadena.indexOf("@");
        if (indiceArroba >= 0 && indiceArroba < cadena.length() - 1) {
            return cadena.substring(indiceArroba + 1);
        }
        return "";
    }
    
    // ------------ MANEJADOR DE SOLICITUDES DE SUBCONTROLADORES -------------
    @Override
    public void mensaje(String solicitud) {
        String opcion = solicitud;

        switch (opcion) {
            case "SolicitudCambioPanelIngresar":
                cambiarALogin();
                break;
            case "SolicitudCambioPanelRegistrarse":
                cambiarARegistrarse();
                break;
            default:
                if (opcion.contains("SolicitudCambioVistaDashboardUsuario")) {
                    String id = obtenerIdUsuarioEmpleado(opcion);
                    cambiarADashboardUsuario(id);
                } else if (opcion.contains("SolicitudCambioVistaDashboardEmpleado")) {
                    String id = obtenerIdUsuarioEmpleado(opcion);
                    cambiarADashboardEmpleado(id);
                } else {
                    System.out.println("Solicitud " + opcion + " imposible de atender");
                }

                break;
        }
    }
}
