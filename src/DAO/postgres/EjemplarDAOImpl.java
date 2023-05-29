package DAO.postgres;

import Objetos.Ejemplar;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EjemplarDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EjemplarDAOImpl{
    
    private Connection conexion;

    public EjemplarDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    private Ejemplar convertir(ResultSet result) throws SQLException{
        Ejemplar ejemplar = null;
        
        String isbn = result.getString("isbn");
        String nroEjemplar = result.getString("nro_ejemplar");
        
        ejemplar = new Ejemplar(isbn, nroEjemplar);
        
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
        String INSERT = "INSERT INTO ejemplar (isbn, nro_ejemplar) VALUES (?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getNroEjemplar());

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
        eliminar(e); // Se elimina porque se necesita cambiar su clave primaria 
        
        insertar(e); // Se inserta uno nuevo con la nueva PK;
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

        String GETALL = "SELECT isbn, nro_ejemplar FROM ejemplar ORDER BY isbn, nro_ejemplar ASC";

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

    public Ejemplar obtener(String isbn) {
        Ejemplar ejemplar = null;

        String GETONE = "SELECT isbn, nro_ejemplar FROM ejemplar WHERE isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, isbn);

            if (result.next()) {
                ejemplar = convertir(result);
            } else {
                System.out.println("No se ha encontrado un ejemplar de ese isbn");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return ejemplar;
    }

}
