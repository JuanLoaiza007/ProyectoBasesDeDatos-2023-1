package Objetos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: ProfesorAreaConocimiento.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class ProfesorAreaConocimiento {
    
    private String idUsuario;
    private String idProfesor;
    private String codigoArea;
    
    public ProfesorAreaConocimiento(String idUsuario, String idProfesor, String codigoArea) {
        this.idUsuario = idUsuario;
        this.idProfesor = idProfesor;
        this.codigoArea = codigoArea;
    }

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
    
    public String getCodigoArea() {
        return codigoArea;
    }
    
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
}