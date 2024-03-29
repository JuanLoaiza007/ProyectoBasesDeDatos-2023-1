package Modelos;

import java.sql.Timestamp;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoEjemplar.java
 * Licencia: GNU-GPL
 * @version 1.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PrestamoEjemplar {
    
    private int nroConsecutivoPrestamo;
    private String isbn;
    private String nroEjemplar;
    private Timestamp fechaDevolucion;

    public PrestamoEjemplar(int nroConsecutivoPrestamo, String isbn, String nroEjemplar, Timestamp fechaDevolucion) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getNroConsecutivoPrestamo() {
        return nroConsecutivoPrestamo;
    }

    public void setNroConsecutivoPrestamo(int nroConsecutivoPrestamo) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
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

    public Timestamp getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Timestamp fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
}
