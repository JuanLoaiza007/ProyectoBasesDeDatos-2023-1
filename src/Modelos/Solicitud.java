package Modelos;

import java.sql.Timestamp;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Solicitud.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Solicitud {
    
    private int nroConsecutivoSolicitud;
    private String idUsuario;
    private String idEmpleado;
    private String isbn;
    private String titulo;
    private String descripcion;
    private Timestamp fecha;
    
    public Solicitud(int nroConsecutivoSolicitud, String idUsuario, String idEmpleado, String isbn, String titulo, String descripcion, Timestamp fecha) {
        this.nroConsecutivoSolicitud = nroConsecutivoSolicitud;
        this.idUsuario = idUsuario;
        this.idEmpleado = idEmpleado;
        this.isbn = isbn;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getNroConsecutivoSolicitud() {
        return nroConsecutivoSolicitud;
    }

    public void setNroConsecutivoSolicitud(int nroConsecutivoSolicitud) {
        this.nroConsecutivoSolicitud = nroConsecutivoSolicitud;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
