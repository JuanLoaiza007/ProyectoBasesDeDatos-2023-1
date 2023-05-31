package Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DescargaUsuarioLibro.java
 * Licencia: GNU-GPL
 * @version 1.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DescargaUsuarioLibro {
    
    private String idDescarga;
    private String isbn;
    private String direccionUrl;
    private String idUsuario;
    private String direccionIp;
    private LocalDate fecha;
    private LocalTime hora;

    public DescargaUsuarioLibro(String idDescarga, String isbn, String direccionUrl, String idUsuario, String direccionIp, LocalDate fecha, LocalTime hora) {
        this.idDescarga = idDescarga;
        this.isbn = isbn;
        this.direccionUrl = direccionUrl;
        this.idUsuario = idUsuario;
        this.direccionIp = direccionIp;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getIdDescarga() {
        return idDescarga;
    }

    public void setIdDescarga(String idDescarga) {
        this.idDescarga = idDescarga;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDireccionUrl() {
        return direccionUrl;
    }

    public void setDireccionUrl(String direccionUrl) {
        this.direccionUrl = direccionUrl;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
      
}
