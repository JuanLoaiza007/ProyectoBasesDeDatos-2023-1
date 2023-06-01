package Modelos;

import java.time.LocalDateTime;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DevuelveUsuarioEjemplar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DevuelveUsuarioEjemplar {
    
    private String idUsuario;
    private String isbn;
    private String nroEjemplar;
    private LocalDateTime fecha;

    public DevuelveUsuarioEjemplar(String idUsuario, String isbn, String nroEjemplar, LocalDateTime fecha) {
        this.idUsuario = idUsuario;
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNroEjemplar() {
        return nroEjemplar;
    }

    public void setNroEjemplar(String nroEjemplar) {
        this.nroEjemplar = nroEjemplar;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
}
