package Modelos;

import java.sql.Timestamp;

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
    private int nroConsecutivoPrestamo;
    private Timestamp fecha;
    private int valor;
    private String descripcion;

    public Multa(String idUsuario, int nroConsecutivoPrestamo, Timestamp fecha, int valor, String descripcion) {
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

    public int getNroConsecutivoPrestamo() {
        return nroConsecutivoPrestamo;
    }

    public void setNroConsecutivoPrestamo(int nroConsecutivoPrestamo) {
        this.nroConsecutivoPrestamo = nroConsecutivoPrestamo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
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
