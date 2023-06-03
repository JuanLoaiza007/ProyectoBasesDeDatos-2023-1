package Modelos;

import java.sql.Timestamp;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Prestamo.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Prestamo {
    
    private String nroConsecutivoPrestamo;
    private String idUsuario;
    private String idEmpleado;
    private Timestamp fechaRealizacion;
    
    public Prestamo(String nroConsecutivoPrestamo, String idUsuario, String idEmpleado, Timestamp fechaRealizacion) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
        this.idUsuario = idUsuario;
        this.idEmpleado = idEmpleado;
        this.fechaRealizacion = fechaRealizacion;
    }
    
    // getters y setters
    
    public String getNroConsecutivoPrestamo() {
        return nroConsecutivoPrestamo;
    }
    
    public void setNroConsecutivoPrestamo(String nroConsecutivoPrestamo) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Timestamp getFechaRealizacion() {
        return fechaRealizacion;
    }
    
    public void setFechaRealizacion(Timestamp fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    
}
