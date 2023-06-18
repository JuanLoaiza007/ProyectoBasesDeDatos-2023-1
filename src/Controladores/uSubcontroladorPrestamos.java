package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: uSubcontroladorPrestamos.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.PrestamoDao;
import Modelos.Prestamo;
import Paneles.uPanelPrestamos;
import java.sql.Timestamp;
import java.util.List;

public final class uSubcontroladorPrestamos { 
    
    protected uPanelPrestamos panel = new uPanelPrestamos();
    protected String idInterno;
    protected ComunicadorClases decirAInstanciaSuperior;  
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorPrestamos(uPanelPrestamos panel){        
        this.panel = panel;      
        
        cargarRegistros();        
    }
    
    
    // ------------------ METODOS ------------------    
    public void cargarModoInicial(){
        panel.limpiarTabla();
        cargarRegistros();      
    }        

    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(Prestamo e){
        String nroConsecutivoPrestamo = e.getNroConsecutivoPrestamo();
        String idUsuario = e.getIdUsuario();
        String idEmpleado = e.getIdEmpleado();
        Timestamp fechaRealizacion = e.getFechaRealizacion();
        
        panel.nuevaFilaTabla(nroConsecutivoPrestamo, idUsuario, idEmpleado, fechaRealizacion);      
        
        
    }      
    
    /**
     * Carga los registros de prestamos que no han sido atendidos
     */
    public void cargarRegistros(){
        List<Prestamo> prestamos;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        PrestamoDao dao = new PrestamoDao(conexion);
        prestamos = dao.obtenerPorUsuario(idInterno);
        
        for(Prestamo solicitudActual: prestamos){
            if(solicitudActual.getIdEmpleado() != null)
                cargarObjetoEnTabla(solicitudActual);
        }
    }        
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
}

