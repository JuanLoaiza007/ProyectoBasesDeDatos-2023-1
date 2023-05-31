package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Ubicacion.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Ubicacion {
    
    private String idUbicacion;
    private String nombreSala;
    private int nroPasillo;
    private int estante;
    private int nroCajon;

    public Ubicacion(String idUbicacion, String nombreSala, int nroPasillo, int estante, int nroCajon) {
        this.idUbicacion = idUbicacion;
        this.nombreSala = nombreSala;
        this.nroPasillo = nroPasillo;
        this.estante = estante;
        this.nroCajon = nroCajon;
    }
    
    public String getIdUbicacion() {
        return idUbicacion;
    }
    
    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    
    public String getNombreSala() {
        return nombreSala;
    }
    
    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    public int getNroPasillo() {
        return nroPasillo;
    }
    
    public void setNroPasillo(int nroPasillo) {
        this.nroPasillo = nroPasillo;
    }
    
    public int getEstante() {
        return estante;
    }
    
    public void setEstante(int estante) {
        this.estante = estante;
    }
    
    public int getNroCajon() {
        return nroCajon;
    }
    
    public void setNroCajon(int nroCajon) {
        this.nroCajon = nroCajon;
    }
    
}
