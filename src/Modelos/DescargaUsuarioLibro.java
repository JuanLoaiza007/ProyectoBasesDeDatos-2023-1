package Modelos;

import java.time.LocalDateTime;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DescargaUsuarioLibro.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DescargaUsuarioLibro {

    private String isbn;
    private String direccionUrl;
    private String idUsuario;
    private LocalDateTime fecha;
    private String direccionIp;

    public DescargaUsuarioLibro(String isbn, String direccionUrl, String idUsuario, LocalDateTime fecha, String direccionIp) {
        this.isbn = isbn;
        this.direccionUrl = direccionUrl;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.direccionIp = direccionIp;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }
}
