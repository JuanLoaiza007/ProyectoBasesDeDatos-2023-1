package BasesDeDatos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: postgres.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuariosManager {
    
    private static String postgres_user = "postgres";
    private static String postgres_password = "postgres";
    private static String host = "localhost";
    private static String puerto = "5432";
    private static String nombreBaseDatos = "usuarios";
    private static Connection connection;   
    
    public static Connection iniciarConexion() {      
        try {       
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"
                            + host + ":"
                            + puerto +"/"
                            + nombreBaseDatos,
                    postgres_user,
                    postgres_password);
        } catch (Exception e) {
            System.out.println("Error conectando a la base de datos " + nombreBaseDatos + ": \n"
                    + e.getMessage());
            return null;
        }
        return connection;
    }  
    
    public static void detenerConexion(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando la conexion " + conexion + ": \n"
                    + ex.getMessage());
        }
    }  
}
