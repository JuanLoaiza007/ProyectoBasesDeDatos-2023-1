package Controladores;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: ControladorDashboardAdminEmpleado.java
 Licencia: GNU-GPL
 * @version 1.0.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Paneles.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDashboardAdminEmpleado implements ComunicadorClases{

    protected VistaDashboardAdminEmpleado vista = new VistaDashboardAdminEmpleado();
    private String idInterno;
    
    /**
     * Constructor de la clase
     * @param vista Una vista de tipo JFrame
     */
    public ControladorDashboardAdminEmpleado(VistaDashboardAdminEmpleado vista){
        this.vista = vista;      

        
        // Añadir controladores
        vista.addActionAdministrar(oyenteAdministrar);
        vista.addActionPrestamos(oyentePrestamos);
        vista.addActionDevoluciones(oyenteDevoluciones);
        vista.addActionSolicitudes(oyenteSolicitudes);
        vista.addActionMultas(oyenteMultas);
        vista.addActionAvanzado(oyenteAvanzado);
        vista.addActionSalir(oyenteSalir);
        
        vista.doClickAdministrar();
        
        // Configurar vista para mostrar
        vista.setLocationRelativeTo(null);
        vista.setResizable(true);
        vista.setVisible(true);
    }
    
    
    // ------------------ METODOS ------------------
    public void cambiarALogin(){
        VistaIngresarRegistrarse nuevaVista = new VistaIngresarRegistrarse();
        vista.dispose();
        
        ControladorLogin nuevoControlador = new ControladorLogin(nuevaVista);
    }    
    
    public void cambiarAPanelAdministrar() {
        PanelAdministrar panel = new PanelAdministrar();
        SubcontroladorAdministrar subcontrolador = new SubcontroladorAdministrar(panel);

        subcontrolador.setListener(getComunicadorClases());

        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelPrestamos() {
        PanelPrestamos panel = new PanelPrestamos();
        SubcontroladorPrestamos subcontrolador = new SubcontroladorPrestamos(panel);

        subcontrolador.setIdInterno(idInterno);

        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelDevoluciones() {
        //    (nro_consecutivo_prestamo, id_usuario, isbn, nro_ejemplar, fecha)
        PanelDevoluciones panel = new PanelDevoluciones();
        SubcontroladorDevoluciones subcontrolador = new SubcontroladorDevoluciones(panel);

        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelSolicitudes() {
        PanelSolicitudes panel = new PanelSolicitudes();
        SubcontroladorSolicitudes subcontrolador = new SubcontroladorSolicitudes(panel);

        subcontrolador.setIdInterno(idInterno);

        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelMultas(){
        PanelMultas panel = new PanelMultas();
        SubcontroladorMultas subcontrolador = new SubcontroladorMultas(panel);

        subcontrolador.setListener(getComunicadorClases());

        vista.cambiarPanel(subcontrolador.getPanel());
    }

    public void cambiarAPanelAvanzado() {
        PanelAvanzado panel = new PanelAvanzado();
        SubcontroladorAvanzado subcontrolador = new SubcontroladorAvanzado(panel);

        subcontrolador.setListener(getComunicadorClases());
        subcontrolador.setIdInterno(idInterno);
        subcontrolador.comprobarAcceso();

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
    
    public void cambiarAPanelLibrosAutores(){
        PanelLibrosAutores panel = new PanelLibrosAutores();
        SubcontroladorLibrosAutores subcontrolador = new SubcontroladorLibrosAutores(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelDescargas(){
        PanelDescargas panel = new PanelDescargas();
        SubcontroladorDescargas subcontrolador = new SubcontroladorDescargas(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelAreasConocimiento(){
        PanelAreasConocimiento panel = new PanelAreasConocimiento();
        SubcontroladorAreasConocimiento subcontrolador = new SubcontroladorAreasConocimiento(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelUsuarios(){
        PanelUsuarios panel = new PanelUsuarios();
        SubcontroladorUsuarios subcontrolador = new SubcontroladorUsuarios(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEmpleados(){
        PanelEmpleados panel = new PanelEmpleados();
        SubcontroladorEmpleados subcontrolador = new SubcontroladorEmpleados(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarACambiarContrasena(){
        PanelCambiarContrasena panel = new PanelCambiarContrasena();
        SubcontroladorCambiarContrasena subcontrolador = new SubcontroladorCambiarContrasena(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        subcontrolador.cargarEmpleado(idInterno);
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void comprobarRol(){
        if(idInterno.equals("admin")){
            vista.setRol("Administrador");
        }
        else{
            vista.setRol("Empleado");
        }
    }
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelAdministrar();
        }
    };
    
    ActionListener oyentePrestamos = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelPrestamos();
        }
    };
    
    ActionListener oyenteDevoluciones = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelDevoluciones();
        }
    };
    
    ActionListener oyenteSolicitudes = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelSolicitudes();
        }
    };
    
    ActionListener oyenteMultas = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelMultas();
        }
    };
    
    ActionListener oyenteAvanzado = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarAPanelAvanzado();
        }
    };
    
    ActionListener oyenteSalir = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarALogin();
        }
        
    };    

    
    // ------------ MANEJADOR DE SOLICITUDES DE SUBCONTROLADORES -------------
    @Override
    public void mensaje(String solicitud) {
        System.out.println("Capture algo: " + solicitud);
        String opcion = solicitud;

        switch (opcion) {
            case "SolicitudMostrarPanelAdministrar":
                vista.doClickAdministrar();
                break;
            case "SolicitudMostrarPanelSolicitudes":
                vista.doClickSolicitudes();
                break;
            case "SolicitudMostrarPanelAvanzado":
                vista.doClickAvanzado();
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
            case "SolicitudMostrarPanelLibrosAutores":
                cambiarAPanelLibrosAutores();
                break;
            case "SolicitudMostrarPanelDescargas":
                cambiarAPanelDescargas();
                break;
            case "SolicitudMostrarPanelAreasConocimiento":
                cambiarAPanelAreasConocimiento();
                break;
            case "SolicitudMostrarPanelUsuarios":
                cambiarAPanelUsuarios();
                break;
            case "SolicitudMostrarPanelEmpleados":
                cambiarAPanelEmpleados();
                break;
            case "SolicitudMostrarVistaLogin": 
                cambiarALogin();
                break;
            case "SolicitudMostrarPanelCambiarContrasena":
                cambiarACambiarContrasena();
                break;
            default:
                System.out.println("Solicitud " + solicitud + " imposible de atender");
                break;
        }
    }
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
    
    public ComunicadorClases getComunicadorClases(){
        return this;
    } 
}
