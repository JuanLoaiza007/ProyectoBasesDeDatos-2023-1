package Modelos;

import java.sql.Timestamp;

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
    
    private int nroConsecutivoPrestamo;
    private String idUsuario;
    private String isbn;
    private String nroEjemplar;
    private Timestamp fecha;

    public DevuelveUsuarioEjemplar(int nroConsecutivoPrestamo, String idUsuario, String isbn, String nroEjemplar, Timestamp fecha) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
        this.idUsuario = idUsuario;
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.fecha = fecha;
    }

    public int getNroConsecutivoPrestamo() {
        return nroConsecutivoPrestamo;
    }

    public void setNroConsecutivoPrestamo(int nroConsecutivoPrestamo) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
}
