package Modelo;

import java.sql.Connection;
import BDManager.BibliotecaManager;
import Objetos.Ubicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubmodeloUbicaciones.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class SubmodeloUbicaciones {
    public SubmodeloUbicaciones(){
    }
    
    public List<Ubicacion> getUbicaciones() {
        List<Ubicacion> ubicaciones = new ArrayList<>();
        
        Connection connection = BibliotecaManager.iniciarConexion();        
        PreparedStatement statement;
        
        try {
            
            statement = connection.prepareStatement("SELECT * FROM ubicacion");
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                //ubicacion (id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon) 
                String id_ubicacion = result.getString("id_ubicacion");
                String nombre_sala = result.getString("nombre_sala");
                int nro_pasillo = Integer.parseInt(result.getString("nro_pasillo"));
                int estante = Integer.parseInt(result.getString("estante"));
                int nro_cajon = Integer.parseInt(result.getString("nro_cajon"));
                
                Ubicacion ubicacionActual = new Ubicacion(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
                ubicaciones.add(ubicacionActual);
                
            }
        } catch(Exception e){
            System.out.println("Error desde getUsuarios(): " + e);
            return null;
        }
        
        BibliotecaManager.detenerConexion(connection);
        
        return ubicaciones;
    }
    
    public List<Ubicacion> getUbicacion(String parametro){  
        List<Ubicacion> ubicaciones = new ArrayList<>();
        
        Connection connection = BibliotecaManager.iniciarConexion();  
        PreparedStatement statement;
        
        String id_ubicacion = null;
        String nombre_sala = null;
        int nro_pasillo = -1;
        int estante = -1;
        int nro_cajon = -1;
        
        try {
            statement = connection.prepareStatement("SELECT * FROM ubicacion WHERE id_ubicacion = ?");
            statement.setString(1, parametro);            

            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                //ubicacion (id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon) 
                id_ubicacion = result.getString("id_ubicacion");
                nombre_sala = result.getString("nombre_sala");
                nro_pasillo = Integer.parseInt(result.getString("nro_pasillo"));
                estante = Integer.parseInt(result.getString("estante"));
                nro_cajon = Integer.parseInt(result.getString("nro_cajon"));
                
                Ubicacion ubicacionActual = new Ubicacion(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
                
                ubicaciones.add(ubicacionActual);
                
            }
        } catch(Exception e){
            System.out.println("Error desde getUsuarios(): " + e);
        }       
        
        System.out.println("Consultas encontradas por id: " + ubicaciones.size() + "\n");
        
        if(ubicaciones.size() < 1){
            try {
            statement = connection.prepareStatement("SELECT * FROM ubicacion WHERE nombre_sala = ?");
            statement.setString(1, parametro);

            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                //ubicacion (id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon) 
                id_ubicacion = result.getString("id_ubicacion");
                nombre_sala = result.getString("nombre_sala");
                nro_pasillo = Integer.parseInt(result.getString("nro_pasillo"));
                estante = Integer.parseInt(result.getString("estante"));
                nro_cajon = Integer.parseInt(result.getString("nro_cajon"));
                
                Ubicacion ubicacionActual = new Ubicacion(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
                ubicaciones.add(ubicacionActual);
                System.out.println(ubicacionActual);
                
            }
            
        } catch(Exception e){
            System.out.println("Error desde getUsuarios(): " + e);
        }
            System.out.println("Consultas encontradas por nombre: " + ubicaciones.size() + "\n");
        }
    
    BibliotecaManager.detenerConexion(connection);
    
    return ubicaciones;
    
    }
}
