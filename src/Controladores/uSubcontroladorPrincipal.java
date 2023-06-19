package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorPrincipal.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.UsuarioDao;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.uPanelPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class uSubcontroladorPrincipal {
   
    protected uPanelPrincipal panel = new uPanelPrincipal();
    protected ComunicadorClases decirAInstanciaSuperior;
        protected String idUsuarioActual;
        protected String idInterno;   
        protected Usuario usuarioActual = null;        
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorPrincipal(uPanelPrincipal panel){
        this.panel = panel;
        
        panel.addListenerLibros(oyenteMostrarPanelLibros);
        panel.addListenerEjemplares(oyenteMostrarPanelEjemplares);
        panel.addListenerLibrosDigitales(oyenteMostrarPanelLibrosDigitales); 
    }
    
    
    // ------------------ LISTENERS ------------------    
    ActionListener oyenteMostrarPanelLibros = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelLibros");
        }
    };
    
    ActionListener oyenteMostrarPanelEjemplares = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelEjemplares");
        }
    };
    
    ActionListener oyenteMostrarPanelLibrosDigitales = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelLibrosDigitales");
        }
    };
    
    public String idUsuarioActual(){
        try{
            java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

            UsuarioDao dao = new UsuarioDao(conexion);
            idUsuarioActual = dao.obtenerIdUsuarioActual(usuarioActual);
            
            System.out.println(idUsuarioActual);
            
            return idUsuarioActual;
                
        } catch (NullPointerException ex){
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");        
        }
        return null;     
    }    
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
