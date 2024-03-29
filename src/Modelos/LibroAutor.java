package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroAutor.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class LibroAutor {
    
    private String isbn;
    private String codigoAutor;
    
    public LibroAutor(String isbn, String codigoAutor) {
        this.isbn = isbn;
        this.codigoAutor = codigoAutor;
    }
    
    // getters y setters
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getCodigoAutor() {
        return codigoAutor;
    }
    
    public void setCodigoAutor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }
    
}