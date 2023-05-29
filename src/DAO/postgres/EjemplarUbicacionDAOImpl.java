package DAO.postgres;

import Objetos.Editorial;
import Objetos.EjemplarUbicacion;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EjemplarUbicacionDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EjemplarUbicacionDAOImpl {
    
    private Connection conexion;

    public EjemplarUbicacionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    
    private EjemplarUbicacion convertir(ResultSet result) throws SQLException{
        EjemplarUbicacion ejemplarUbicacion = null;
        
        String isbn = result.getString("isbn");
        String nroEjemplar = result.getString("nro_ejemplar");
        String idUbicacion = result.getString("id_ubicacion");
        
        ejemplarUbicacion = new EjemplarUbicacion(isbn, nroEjemplar, idUbicacion);
        
        return ejemplarUbicacion;
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

    public void insertar(EjemplarUbicacion e) {
        String INSERT = "INSERT INTO ejemplar_ubicacion (isbn, nro_ejemplar, id_ubicacion) VALUES (?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getNroEjemplar());
            statement.setString(3, e.getIdUbicacion());            

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

    public void modificar(EjemplarUbicacion e) {
        eliminar(e); // Se elimina el registro porque se modificará su PK
        insertar(e); // Se inserta uno nuevo
    }

    public void eliminar(EjemplarUbicacion e) {
        String DELETE = "DELETE FROM ejemplar_ubicacion WHERE isbn = ? AND nro_ejemplar = ? AND id_ubicacion = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIsbn());
            statement.setString(1, e.getNroEjemplar());
            statement.setString(1, e.getIdUbicacion());

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

    public List<EjemplarUbicacion> obtenerTodos() {
        List<EjemplarUbicacion> ejemplarUbicacion = new ArrayList<>();

        String GETALL = "SELECT isbn, nro_ejemplar, id_ubicacion FROM ejemplar_ubicacion ORDER BY isbn, nro_ejemplar, id_ubicacion ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                ejemplarUbicacion.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return ejemplarUbicacion;
        
    }

    public EjemplarUbicacion obtener(String id) {
        EjemplarUbicacion ejemplarUbicacion = null;

        String GETONE = "SELECT isbn, nro_ejemplar, id_ubicacion FROM ejemplar_ubicacion WHERE isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                ejemplarUbicacion = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return ejemplarUbicacion;        
    }

}
