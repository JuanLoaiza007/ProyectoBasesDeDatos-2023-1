package Objetos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Libro.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Libro {
    
    private String isbn;
    private String codigoArea;
    private String codigoEditorial;
    private String idEmpleado;
    private String titulo;
    private String anioPublicacion;
    private int nroPaginas;
    
    public Libro(String isbn, String codigoArea, String codigoEditorial, String idEmpleado, String titulo, String anioPublicacion, int nroPaginas) {
        this.isbn = isbn;
        this.codigoArea = codigoArea;
        this.codigoEditorial = codigoEditorial;
        this.idEmpleado = idEmpleado;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.nroPaginas = nroPaginas;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getCodigoArea() {
        return codigoArea;
    }
    
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    public String getCodigoEditorial() {
        return codigoEditorial;
    }
    
    public void setCodigoEditorial(String codigoEditorial) {
        this.codigoEditorial = codigoEditorial;
    }
    
    public String getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAnioPublicacion() {
        return anioPublicacion;
    }
    
    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    
    public int getNroPaginas() {
        return nroPaginas;
    }
    
    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }
    
}
