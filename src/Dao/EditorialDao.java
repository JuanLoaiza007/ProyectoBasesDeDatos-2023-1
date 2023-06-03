package Dao;

import Modelos.Editorial;
import Paneles.AvisosEmergentes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EditorialDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EditorialDao {

    private Connection conexion;

    public EditorialDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Editorial convertir(ResultSet result) throws SQLException{
        Editorial editorial = null;
        
        String codigoEditorial = result.getString("codigo_editorial");
        String nombre = result.getString("nombre");
        String paginaWeb = result.getString("pagina_web");
        String paisOrigen = result.getString("pais_origen");
        
        editorial = new Editorial(codigoEditorial, nombre, paginaWeb, paisOrigen);
        
        return editorial;
    }
    
    /**
     * Cierra la conexion que se le pase como parametro
     * @param conexion (Connection) La conexion a cerrar
     */
    private void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    /**
     * Cierra el statement que se le pase como parametro
     * @param statement (PreparedStatement) el Statement a cerrar
     */
    private void cerrarStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void insertar(Editorial e) {
        // editorial (codigo_editorial, nombre, pagina_web, pais_origen) 
        String INSERT = "INSERT INTO editorial (codigo_editorial, nombre, pagina_web, pais_origen) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getCodigoEditorial());
            statement.setString(2, e.getNombre());
            statement.setString(3, e.getPaginaWeb());
            statement.setString(4, e.getPaisOrigen());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            AvisosEmergentes.mostrarMensaje("" + ex.getErrorCode());
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void modificar(Editorial e) {
        String UPDATE = "UPDATE editorial SET nombre = ?, pagina_web = ?, pais_origen = ? WHERE codigo_editorial = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);          
            statement.setString(1, e.getNombre());
            statement.setString(2, e.getPaginaWeb());
            statement.setString(3, e.getPaisOrigen());
            statement.setString(4, e.getCodigoEditorial());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya modificado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(Editorial e) {
        String DELETE = "DELETE FROM editorial WHERE codigo_editorial = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getCodigoEditorial());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<Editorial> obtenerTodos() {
        List<Editorial> editoriales = new ArrayList<>();

        String GETALL = "SELECT codigo_editorial, nombre, pagina_web, pais_origen FROM editorial ORDER BY nombre ASC";
        
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                editoriales.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return editoriales;
    }

    public Editorial obtener(String id) {
        Editorial editorial = null;

        String GETONE = "SELECT codigo_editorial, nombre, pagina_web, pais_origen FROM editorial WHERE codigo_editorial = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                editorial = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return editorial;
    }   

}
