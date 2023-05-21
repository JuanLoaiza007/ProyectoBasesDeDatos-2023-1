package Controlador;

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

import Modelo.*;
import Vista.*;
import Paneles.*;
import BDManager.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDashboardAdminEmpleado implements ComunicadorClases{
    
    protected ModeloDashboardAdminEmpleado modelo = new ModeloDashboardAdminEmpleado();
    protected VistaDashboardAdminEmpleado vista = new VistaDashboardAdminEmpleado();
    
    public ControladorDashboardAdminEmpleado(ModeloDashboardAdminEmpleado modelo, VistaDashboardAdminEmpleado vista){
        this.modelo = modelo;
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
        ModeloLogin nuevoModelo = new ModeloLogin();
        VistaIngresarRegistrarse nuevaVista = new VistaIngresarRegistrarse();
        vista.dispose();
        
        ControladorLogin nuevoControlador = new ControladorLogin(nuevoModelo, nuevaVista);
    }
    
    public void cambiarAPanelUbicaciones(){
        SubmodeloUbicaciones submodelo = new SubmodeloUbicaciones();
        PanelUbicaciones panel = new PanelUbicaciones();
        SubcontroladorUbicaciones subcontrolador = new SubcontroladorUbicaciones(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEditoriales(){
        SubmodeloEditoriales submodelo = new SubmodeloEditoriales();
        PanelEditoriales panel = new PanelEditoriales();
        SubcontroladorEditoriales subcontrolador = new SubcontroladorEditoriales(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelAutores(){
        SubmodeloAutores submodelo = new SubmodeloAutores();
        PanelAutores panel = new PanelAutores();
        SubcontroladorAutores subcontrolador = new SubcontroladorAutores(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelLibros(){
        SubmodeloLibros submodelo = new SubmodeloLibros();
        PanelLibros panel = new PanelLibros();
        SubcontroladorLibros subcontrolador = new SubcontroladorLibros(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEjemplares(){
        SubmodeloEjemplares submodelo = new SubmodeloEjemplares();
        PanelEjemplares panel = new PanelEjemplares();
        SubcontroladorEjemplares subcontrolador = new SubcontroladorEjemplares(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelLibrosDigitales(){
        SubmodeloLibrosDigitales submodelo = new SubmodeloLibrosDigitales();
        PanelLibrosDigitales panel = new PanelLibrosDigitales();
        SubcontroladorLibrosDigitales subcontrolador = new SubcontroladorLibrosDigitales(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelAreasConocimiento(){
        SubmodeloAreasConocimiento submodelo = new SubmodeloAreasConocimiento();
        PanelAreasConocimiento panel = new PanelAreasConocimiento();
        SubcontroladorAreasConocimiento subcontrolador = new SubcontroladorAreasConocimiento(submodelo, panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    ActionListener oyenteAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            SubmodeloAdministrar submodelo = new SubmodeloAdministrar();
            PanelAdministrar panel = new PanelAdministrar();
            SubcontroladorAdministrar subcontrolador = new SubcontroladorAdministrar(submodelo, panel);
            
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
