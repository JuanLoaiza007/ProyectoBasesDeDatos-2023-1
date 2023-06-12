package Dao;

import Modelos.DevuelveUsuarioEjemplar;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DevuelveUsuarioEjemplarDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DevuelveUsuarioEjemplarDao {

    private Connection conexion;
    private DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("yyyy/MM/d H:mm:ss");

    public DevuelveUsuarioEjemplarDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private DevuelveUsuarioEjemplar convertir(ResultSet result) throws SQLException{
        DevuelveUsuarioEjemplar devolucion = null;
        
        String nroPrestamo = result.getString("nro_consecutivo_prestamo");
        String idUsuario = result.getString("id_usuario");
        String isbn = result.getString("isbn");
        String nroEjemplar = result.getString("nro_ejemplar");
        Timestamp fecha = result.getTimestamp("fecha");
    
        
        devolucion = new DevuelveUsuarioEjemplar(nroPrestamo, idUsuario, isbn, nroEjemplar, fecha);
        
        return devolucion;
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
    
    public void insertar(DevuelveUsuarioEjemplar e) {
//        devuelve_usuario_ejemplar (id_usuario, isbn, nro_ejemplar, fecha)
        String INSERT = "INSERT INTO devuelve_usuario_ejemplar (nro_consecutivo_prestamo, id_usuario, isbn, nro_ejemplar, fecha) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getNroConsecutivoPrestamo());
            statement.setString(2, e.getIdUsuario());
            statement.setString(3, e.getIsbn());
            statement.setString(4, e.getNroEjemplar());
            statement.setTimestamp(5, e.getFecha());
            

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            System.out.println("Error en insertar: " + ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<DevuelveUsuarioEjemplar> obtenerTodos() {
        List<DevuelveUsuarioEjemplar> devoluciones = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_prestamo, id_usuario, isbn, nro_ejemplar, fecha FROM devuelve_usuario_ejemplar ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                devoluciones.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println("Error en obtenerTodos: " + ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return devoluciones;
    }

    /**
     * Busca las devoluciones de un usuario especifico
     * @param id El id_usuario de quien se quieren conocer las devoluciones
     * @return Un ArrayList de objetos DevuelveUsuarioEjemplar asociadas al usuario solicitado
     */
    public List<DevuelveUsuarioEjemplar> obtener(String id) {
        List<DevuelveUsuarioEjemplar> devoluciones = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_prestamo, id_usuario, isbn, nro_ejemplar, fecha FROM devuelve_usuario_ejemplar WHERE id_usuario = ? ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                devoluciones.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println("Error en obtener: " + ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return devoluciones;
    }

}
