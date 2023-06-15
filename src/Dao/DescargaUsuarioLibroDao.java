package Dao;

import Modelos.DescargaUsuarioLibro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DescargaUsuarioLibroDao.java
 * Licencia: GNU-GPL
 * @version 1.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DescargaUsuarioLibroDao {

    private Connection conexion;

    public DescargaUsuarioLibroDao(Connection conexion) {
        this.conexion = conexion;
    }

    private DescargaUsuarioLibro convertir(ResultSet result) throws SQLException {
        DescargaUsuarioLibro descarga = null;

        String isbn = result.getString("isbn");
        String direccionUrl = result.getString("direccion_url");
        String idUsuario = result.getString("id_usuario");
        Timestamp fecha = result.getTimestamp("fecha");
        String direccionIp = result.getString("direccion_ip");

        descarga = new DescargaUsuarioLibro(isbn, direccionUrl, idUsuario, fecha, direccionIp);

        return descarga;
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

    public void insertar(DescargaUsuarioLibro e) {
        String INSERT = "INSERT INTO descarga_usuario_libro (isbn, direccion_url, id_usuario, fecha, direccion_ip) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getDireccionUrl());
            statement.setString(3, e.getIdUsuario());
            statement.setTimestamp(4, e.getFecha());
            statement.setString(5, e.getDireccionIp());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en funcion insertar()");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<DescargaUsuarioLibro> obtenerTodos() {
        List<DescargaUsuarioLibro> descargas = new ArrayList<>();

        String GETALL = "SELECT isbn, direccion_url, id_usuario, fecha, direccion_ip "
                + "FROM descarga_usuario_libro "
                + "ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                descargas.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return descargas;
    }

    /**
     * Busca las descargas de un usuario especifico
     * @param id El id_usuario de quien se quieren conocer las descargas
     * @return Un ArrayList de objetos DescargaUsuarioLibro asociadas al usuario solicitado
     */
    public List<DescargaUsuarioLibro> obtener(String id) {
        List<DescargaUsuarioLibro> descargas = new ArrayList<>();

        String GETALL = "SELECT isbn, direccion_url, id_usuario, fecha, direccion_ip "
                + "FROM descarga_usuario_libro "
                + "WHERE id_usuario = ?"
                + "ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                descargas.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return descargas;
    }
}
