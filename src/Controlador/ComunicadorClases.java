package Controlador;

/**
 * 
 * Archivo: ComunicadorClases.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * 
 */

/**
 * Esta es una interfaz que se implementa en una instancia superior, se debe sobreescribir el metodo abstracto de "mensaje" para que haga algo.
 * La instancia superior pasa su listener de es a una instancia interior, así la instancia interior puede mandar mensajes a la superior.
 * @author ihuntgore
 */
public interface ComunicadorClases {
    void mensaje(String mensaje);
}
