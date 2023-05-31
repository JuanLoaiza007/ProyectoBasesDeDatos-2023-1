package DAO;

import java.sql.*;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DAO.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

/**
 * Interfaz padre de DAO, sirve para heredar y generar automaticamente los metodos abstractos a otras clases.
 * Tiene las funciones principales que se usan en un CRUD.
 * @param <Objeto> El objeto 
 * @param <pkTipoDato> El tipo de dato de la Primary Key: String, Int, Long, etc...
 */

public interface Template<Objeto, pkTipoDato>{
    
    void insertar(Objeto e);
    
    void modificar (Objeto e);
    
    void eliminar (Objeto e);
    
    List<Objeto> obtenerTodos();
    
    Objeto obtener(pkTipoDato id);

}
