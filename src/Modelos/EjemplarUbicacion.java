package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EjemplarUbicacion.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EjemplarUbicacion {
    
    private String isbn;
    private String nroEjemplar;
    private String idUbicacion;
    
    public EjemplarUbicacion(String isbn, String nroEjemplar, String idUbicacion) {
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.idUbicacion = idUbicacion;
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
    
    public String getIdUbicacion() {
        return idUbicacion;
    }
    
    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    
}
