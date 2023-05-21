package Objetos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Profesor.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Profesor {
    
    private String idUsuario;
    private String idProfesor;
    private String titulo;
    private String dependencia;
    
    public Profesor(String idUsuario, String idProfesor, String titulo, String dependencia) {
        this.idUsuario = idUsuario;
        this.idProfesor = idProfesor;
        this.titulo = titulo;
        this.dependencia = dependencia;
    }
    
    // getters y setters
    
    public String getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getIdProfesor() {
        return idProfesor;
    }
    
    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDependencia() {
        return dependencia;
    }
    
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }
    
}
