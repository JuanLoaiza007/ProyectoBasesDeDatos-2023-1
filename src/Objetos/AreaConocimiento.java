package Objetos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: AreaConocimiento.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class AreaConocimiento {
    
    private String codigoArea;
    private String codigoAreaPadre;
    private String nombre;
    private String descripcion;
    
    public AreaConocimiento(String codigoArea, String codigoAreaPadre, String nombre, String descripcion) {
        this.codigoArea = codigoArea;
        this.codigoAreaPadre = codigoAreaPadre;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }    
    
    public String getCodigoArea() {
        return codigoArea;
    }
    
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    public String getCodigoAreaPadre() {
        return codigoAreaPadre;
    }
    
    public void setCodigoAreaPadre(String codigoAreaPadre) {
        this.codigoAreaPadre = codigoAreaPadre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
