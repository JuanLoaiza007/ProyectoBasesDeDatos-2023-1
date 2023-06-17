package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SolicitudDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Modelos.Solicitud;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDao {

    private Connection conexion;

    public SolicitudDao(Connection conexion) {
        this.conexion = conexion;
    }

    private Solicitud convertir(ResultSet result) throws SQLException {
        int nroConsecutivoSolicitud = result.getInt("nro_consecutivo_solicitud");
        String idUsuario = result.getString("id_usuario");
        String idEmpleado = result.getString("id_empleado");
        String isbn = result.getString("isbn");
        String titulo = result.getString("titulo");
        String descripcion = result.getString("descripcion");
        Timestamp fecha = result.getTimestamp("fecha");

        return new Solicitud(nroConsecutivoSolicitud, idUsuario, idEmpleado, isbn, titulo, descripcion, fecha);
    }

    private void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void cerrarStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void insertar(Solicitud solicitud) throws SQLException {
        String INSERT = "INSERT INTO solicitud (nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setInt(1, solicitud.getNroConsecutivoSolicitud());
            statement.setString(2, solicitud.getIdUsuario());
            statement.setString(3, solicitud.getIdEmpleado());
            statement.setString(4, solicitud.getIsbn());
            statement.setString(5, solicitud.getTitulo());
            statement.setString(6, solicitud.getDescripcion());
            statement.setTimestamp(7, solicitud.getFecha());

            if (statement.executeUpdate() == 0) {
                System.out.println("Error al insertar registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }
    
    public void modificar(Solicitud solicitud) throws SQLException{
        String UPDATE = "UPDATE solicitud SET id_usuario = ?, id_empleado = ?, isbn = ?, titulo = ?, descripcion = ?, fecha = ? WHERE nro_consecutivo_solicitud = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, solicitud.getIdUsuario());
            statement.setString(2, solicitud.getIdEmpleado());
            statement.setString(3, solicitud.getIsbn());
            statement.setString(4, solicitud.getTitulo());
            statement.setString(5, solicitud.getDescripcion());
            statement.setTimestamp(6, solicitud.getFecha());
            statement.setInt(7, solicitud.getNroConsecutivoSolicitud());

            if (statement.executeUpdate() == 0) {
                System.out.println("Error al actualizar el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(Solicitud solicitud) throws SQLException{
        String DELETE = "DELETE FROM solicitud WHERE nro_consecutivo_solicitud = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, solicitud.getNroConsecutivoSolicitud());

            if (statement.executeUpdate() == 0) {
                System.out.println("Error al eliminar el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }
    
    public List<Solicitud> obtenerTodos() {
        List<Solicitud> solicitudes = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha FROM solicitud";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                solicitudes.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return solicitudes;
    }
    
    public int obtenerUltimoNroConsecutivo() {
        String GET_MAX_NRO_CONSECUTIVO = "SELECT MAX(nro_consecutivo_solicitud) FROM solicitud";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GET_MAX_NRO_CONSECUTIVO);
            result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return 0;
    }

    public Solicitud obtenerPorNroConsecutivo(String nroConsecutivoSolicitud) {
        String GET_BY_NRO_CONSECUTIVO = "SELECT nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha FROM solicitud WHERE nro_consecutivo_solicitud = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GET_BY_NRO_CONSECUTIVO);
            statement.setString(1, nroConsecutivoSolicitud);
            result = statement.executeQuery();

            if (result.next()) {
                return convertir(result);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return null;
    }
}

