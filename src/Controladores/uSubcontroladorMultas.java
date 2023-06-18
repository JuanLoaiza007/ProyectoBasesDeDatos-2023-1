package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorMultas.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.MultaDao;
import Modelos.Multa;
import Paneles.uPanelMultas;
import java.sql.Timestamp;
import java.util.List;

public final class uSubcontroladorMultas { 
    
    protected uPanelMultas panel = new uPanelMultas();
    protected String idInterno;
    protected ComunicadorClases decirAInstanciaSuperior;
      
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorMultas(uPanelMultas panel){        
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
    public void cargarObjetoEnTabla(Multa e){
        String idUsuario = e.getIdUsuario();
        int nroConsecutivoPrestamo = e.getNroConsecutivoPrestamo();
        Timestamp fecha = e.getFecha();
        int valor = e.getValor();
        String descripcion = e.getDescripcion();
        
        panel.nuevaFilaTabla(idUsuario, nroConsecutivoPrestamo, fecha, valor, descripcion);      
        
        
    }      
    
    /**
     * Carga los registros de multas que no han sido atendidos
     */
    public void cargarRegistros(){
        List<Multa> multas;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        MultaDao dao = new MultaDao(conexion);
        multas = dao.obtenerPorUsuario(idInterno);
        
        for(Multa multaActual: multas){
                cargarObjetoEnTabla(multaActual);
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

