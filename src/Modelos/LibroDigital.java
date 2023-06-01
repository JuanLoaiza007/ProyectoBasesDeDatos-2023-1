package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroDigital.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class LibroDigital {
    private String isbn;
    private String direccionUrl;
    private int tamanioBytes;
    private String formato;

    public LibroDigital(String isbn, String direccionUrl, int tamanioBytes, String formato) {
        this.isbn = isbn;
        this.direccionUrl = direccionUrl;
        this.tamanioBytes = tamanioBytes;
        this.formato = formato;
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

    public int getTamanioBytes() {
        return tamanioBytes;
    }

    public void setTamanioBytes(int tamanioBytes) {
        this.tamanioBytes = tamanioBytes;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

}
