package Objetos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Estudiante.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Estudiante {
    
    private String idUsuario;
    private String idEstudiante;
    private String carrera;
    private String universidad;
    
    public Estudiante(String idUsuario, String idEstudiante, String carrera, String universidad) {
        this.idUsuario = idUsuario;
        this.idEstudiante = idEstudiante;
        this.carrera = carrera;
        this.universidad = universidad;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    public String getCarrera() {
        return carrera;
    }
    
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public String getUniversidad() {
        return universidad;
    }
    
    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    
}
