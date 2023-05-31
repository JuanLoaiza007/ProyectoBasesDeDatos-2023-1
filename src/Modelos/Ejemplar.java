package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: Ejemplar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class Ejemplar {
    
    private String isbn;
    private String nroEjemplar;
    private String sala;
    private int nroPasillo;
    private int estante;
    private int nroCajon;

    public Ejemplar(String isbn, String nroEjemplar, String sala, int nroPasillo, int estante, int nroCajon) {
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.sala = sala;
        this.nroPasillo = nroPasillo;
        this.estante = estante;
        this.nroCajon = nroCajon;
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

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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
