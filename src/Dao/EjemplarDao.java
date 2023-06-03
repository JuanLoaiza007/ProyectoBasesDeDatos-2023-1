package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Ejemplar;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: EjemplarDao.java
 Licencia: GNU-GPL
 * @version 1.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EjemplarDao{
    
    private Connection conexion;

    public EjemplarDao(Connection conexion) {
        this.conexion = conexion;
    }

    private Ejemplar convertir(ResultSet result) throws SQLException{
        Ejemplar ejemplar = null;
        
        String isbn = result.getString("isbn");
        String nroEjemplar = result.getString("nro_ejemplar");
        String sala  = result.getString("sala");
        int nroPasillo  = result.getInt("nro_pasillo");
        int estante  = result.getInt("estante");
        int nroCajon  = result.getInt("nro_cajon");  
        
        
        ejemplar = new Ejemplar(isbn, nroEjemplar, sala, nroPasillo, estante, nroCajon);
        
        return ejemplar;
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
    
    public void insertar(Ejemplar e) { 
        String INSERT = "INSERT INTO ejemplar (isbn, nro_ejemplar, sala, nro_pasillo, estante, nro_cajon) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getNroEjemplar());
            statement.setString(3, e.getSala());
            statement.setString(4, Integer.toString(e.getNroPasillo()));
            statement.setString(5, Integer.toString(e.getEstante()));
            statement.setString(6, Integer.toString(e.getNroCajon()));

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

    public void modificar(Ejemplar e) {
        String UPDATE = "UPDATE ejemplar SET sala = ? , nro_pasillo = ? , estante = ? , nro_cajon = ?  WHERE isbn = ? AND nro_ejemplar = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);         
            statement.setString(1, e.getSala());
            statement.setString(2, Integer.toString(e.getNroPasillo()));
            statement.setString(3, Integer.toString(e.getEstante()));
            statement.setString(4, Integer.toString(e.getNroCajon()));
            statement.setString(5, e.getIsbn());
            statement.setString(6, e.getNroEjemplar());
            

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

    public void eliminar(Ejemplar e) {
        
        String DELETE = "DELETE FROM ejemplar WHERE isbn = ? AND nro_ejemplar = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getNroEjemplar());

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

    public List<Ejemplar> obtenerTodos() {
        List<Ejemplar> ejemplares = new ArrayList<>();

        String GETALL = "SELECT isbn, nro_ejemplar, sala, nro_pasillo, estante, nro_cajon FROM ejemplar ORDER BY isbn, nro_ejemplar ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                ejemplares.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return ejemplares;
    }

    public List<Ejemplar> obtener(String isbn) {
        List<Ejemplar> ejemplares = new ArrayList<>();

        String GETALL = "SELECT isbn, nro_ejemplar, sala, nro_pasillo, estante, nro_cajon FROM ejemplar WHERE isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                ejemplares.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return ejemplares;
    }
}
