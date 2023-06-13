package Controladores;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: ControladorDashboardUsuario.java
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

public class ControladorDashboardUsuario implements ComunicadorClases{
    
    protected VistaDashboardUsuario vista = new VistaDashboardUsuario();
    private String id;
    
    public ControladorDashboardUsuario(VistaDashboardUsuario vista){
        this.vista = vista;       
        
        // Añadir controladores
        vista.addActionPrincipal(oyentePrincipal);
        vista.addActionSolicitudes(oyenteSolicitudes);
        vista.addActionAvanzado(oyenteAvanzado);
        vista.addActionSalir(oyenteSalir);
        
        vista.doClickPrincipal();
        
        // Configurar vista para mostrar
        vista.setLocationRelativeTo(null);
        vista.setResizable(true);
        vista.setVisible(true);
    }
    
    public ComunicadorClases getComunicadorClases(){
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    public void cambiarALogin(){
        VistaIngresarRegistrarse nuevaVista = new VistaIngresarRegistrarse();
        vista.dispose();
        
        ControladorLogin nuevoControlador = new ControladorLogin(nuevaVista);
    }    
    
    public void cambiarAPanelLibros(){        
        uPanelLibros panel = new uPanelLibros();
        uSubcontroladorLibros subcontrolador = new uSubcontroladorLibros(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelEjemplares(){
        uPanelEjemplares panel = new uPanelEjemplares();
        uSubcontroladorEjemplares subcontrolador = new uSubcontroladorEjemplares(panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    public void cambiarAPanelLibrosDigitales(){
        PanelLibrosDigitales panel = new PanelLibrosDigitales();
        SubcontroladorLibrosDigitales subcontrolador = new SubcontroladorLibrosDigitales( panel);
        
        subcontrolador.setListener(getComunicadorClases());
        
        vista.cambiarPanel(subcontrolador.getPanel());
    }
    
    ActionListener oyentePrincipal = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            uPanelPrincipal panel = new uPanelPrincipal();
            uSubcontroladorPrincipal subcontrolador = new uSubcontroladorPrincipal(panel);
            
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
            uPanelAvanzado panel = new uPanelAvanzado();
            uSubcontroladorAvanzado subcontrolador = new uSubcontroladorAvanzado(panel);
            
            subcontrolador.setListener(getComunicadorClases());
            
            vista.cambiarPanel(subcontrolador.getPanel());
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
        System.out.println("Capture algo: " + solicitud);
        String opcion = solicitud;

        switch (opcion) {
            case "SolicitudMostraruPanelPrincipal":
                vista.doClickPrincipal();
                break;
            case "SolicitudMostraruPanelPrestamo":
                vista.doClickPrestamo();
                break;
            case "SolicitudMostraruPanelAvanzado":
                vista.doClickAvanzado();
                break;
            case "SolicitudMostraruPanelLibros":
                cambiarAPanelLibros();
                break;
            case "SolicitudMostraruPanelEjemplares":
                cambiarAPanelEjemplares();
                break;
            case "SolicitudMostraruPanelLibrosDigitales":
                cambiarAPanelLibrosDigitales();
                break;
            default:
                System.out.println("Solicitud " + solicitud + " imposible de atender");
                break;
        }
    }
}
