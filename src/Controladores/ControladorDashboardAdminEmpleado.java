package Controladores;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: ControladorDashboardAdminEmpleado.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.PanelLibros;
import Paneles.PanelSolicitudes;
import Paneles.PanelAvanzado;
import Paneles.PanelAutores;
import Paneles.PanelEditoriales;
import Paneles.PanelEjemplares;
import Paneles.PanelUbicaciones;
import Paneles.PanelAdministrar;
import Paneles.PanelLibrosDigitales;
import Paneles.PanelAreasConocimiento;
import Vista.VistaDashboardAdminEmpleado;
import Vista.VistaIngresarRegistrarse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDashboardAdminEmpleado implements ComunicadorClases{
    
    protected VistaDashboardAdminEmpleado vista = new VistaDashboardAdminEmpleado();
    
    public ControladorDashboardAdminEmpleado(VistaDashboardAdminEmpleado vista){
        this.vista = vista;       
        
        // Añadir controladores
        vista.addActionAdministrar(oyenteAdministrar);
        vista.addActionSolicitudes(oyenteSolicitudes);
        vista.addActionAvanzado(oyenteAvanzado);
        vista.addActionSalir(oyenteSalir);
        
        vista.doClickAdministrar();
        
        // Configurar vista para mostrar
        vista.setLocationRelativeTo(null);
        vista.setResizable(true);
        vista.setVisible(true);
    }
    
    public ComunicadorClases getComunicadorClases(){
        return this;
    }
    
    public void cambiarALogin(){
        VistaIngresarRegistrarse nuevaVista = new VistaIngresarRegistrarse();
        vista.dispose();
        
        ControladorLogin nuevoControlador = new ControladorLogin(nuevaVista);
    }
    
    public void cambiarAPanelUbicaciones(){
        PanelUbicaciones panel = new PanelUbicaciones();
        SubcontroladorUbicaciones subcontrolador = new SubcontroladorUbicaciones(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEditoriales(){
        PanelEditoriales panel = new PanelEditoriales();
        SubcontroladorEditoriales subcontrolador = new SubcontroladorEditoriales(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelAutores(){
        PanelAutores panel = new PanelAutores();
        SubcontroladorAutores subcontrolador = new SubcontroladorAutores(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelLibros(){        
        PanelLibros panel = new PanelLibros();
        SubcontroladorLibros subcontrolador = new SubcontroladorLibros(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEjemplares(){
        PanelEjemplares panel = new PanelEjemplares();
        SubcontroladorEjemplares subcontrolador = new SubcontroladorEjemplares(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelLibrosDigitales(){
        PanelLibrosDigitales panel = new PanelLibrosDigitales();
        SubcontroladorLibrosDigitales subcontrolador = new SubcontroladorLibrosDigitales( panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelAreasConocimiento(){
        PanelAreasConocimiento panel = new PanelAreasConocimiento();
        SubcontroladorAreasConocimiento subcontrolador = new SubcontroladorAreasConocimiento(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    ActionListener oyenteAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            PanelAdministrar panel = new PanelAdministrar();
            SubcontroladorAdministrar subcontrolador = new SubcontroladorAdministrar(panel);
            
            subcontrolador.setListener(getComunicadorClases());
            
            vista.cambiarPanel(subcontrolador.getPanel());
        }
    };
    
    ActionListener oyenteSolicitudes = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            PanelSolicitudes panel = new PanelSolicitudes();
            vista.cambiarPanel(panel);
        }
    };
    
    ActionListener oyenteAvanzado = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            PanelAvanzado panel = new PanelAvanzado();
            vista.cambiarPanel(panel);
        }
    };
    
    ActionListener oyenteSalir = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarALogin();
        }
        
    };
    
    // Capturador de solicitudes de controladores internos
    @Override
    public void mensaje(String solicitud) {
        String opcion = solicitud;

        switch (opcion) {
            case "SolicitudMostrarPanelAdministrar":
                vista.doClickAdministrar();
                break;
            case "SolicitudMostrarPanelSolicitudes":
                break;
            case "SolicitudMostrarPanelAvanzado":
                break;
            case "SolicitudMostrarPanelUbicaciones":
                cambiarAPanelUbicaciones();
                break;
            case "SolicitudMostrarPanelEditoriales":
                cambiarAPanelEditoriales();
                break;
            case "SolicitudMostrarPanelAutores":
                cambiarAPanelAutores();
                break;
            case "SolicitudMostrarPanelLibros":
                cambiarAPanelLibros();
                break;
            case "SolicitudMostrarPanelEjemplares":
                cambiarAPanelEjemplares();
                break;
            case "SolicitudMostrarPanelLibrosDigitales":
                cambiarAPanelLibrosDigitales();
                break;
            case "SolicitudMostrarPanelAreasConocimiento":
                cambiarAPanelAreasConocimiento();
                break;
            default:
                System.out.println("Solicitud " + solicitud + " imposible de atender");
                break;
        }
    }
}
