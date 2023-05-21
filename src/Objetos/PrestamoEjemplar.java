package Objetos;

import java.time.LocalDateTime;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoEjemplar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PrestamoEjemplar {
    
    private Prestamo prestamo;
    private Ejemplar ejemplar;
    private LocalDateTime fechaDevolucion;
    
    public PrestamoEjemplar(Prestamo prestamo, Ejemplar ejemplar, LocalDateTime fechaDevolucion) {
        this.prestamo = prestamo;
        this.ejemplar = ejemplar;
        this.fechaDevolucion = fechaDevolucion;
    }
    
    // getters y setters
    
    public Prestamo getPrestamo() {
        return prestamo;
    }
    
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    public Ejemplar getEjemplar() {
        return ejemplar;
    }
    
    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
    
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
}
