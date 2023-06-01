package Modelos;

import java.time.LocalDateTime;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Multa.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Multa {

    private String idUsuario;
    private String nroConsecutivoPrestamo;
    private LocalDateTime fecha;
    private int valor;
    private String descripcion;

    public Multa(String idUsuario, String nroConsecutivoPrestamo, LocalDateTime fecha, int valor, String descripcion) {
        this.idUsuario = idUsuario;
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
        this.fecha = fecha;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNroConsecutivoPrestamo() {
        return nroConsecutivoPrestamo;
    }

    public void setNroConsecutivoPrestamo(String nroConsecutivoPrestamo) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
