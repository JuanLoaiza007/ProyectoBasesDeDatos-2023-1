package Dao;

import Modelos.Multa;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: MultaDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class MultaDao {
    
    private Connection conexion;  

    public MultaDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Multa convertir(ResultSet result) throws SQLException {
        Multa multa = null;
        
        String idUsuario = result.getString("id_usuario");
        String nroConsecutivoPrestamo = result.getString("nro_consecutivo_prestamo");
        Timestamp fecha = result.getTimestamp("fecha");
        int valor = result.getInt("valor");
        String descripcion = result.getString("descripcion");
    
        multa = new Multa(idUsuario, nroConsecutivoPrestamo, fecha, valor, descripcion);
        
        return multa;
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

    public void insertar(Multa multa) {
        String INSERT = "INSERT INTO multa (id_usuario, nro_consecutivo_prestamo, fecha, valor, descripcion) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, multa.getIdUsuario());
            statement.setString(2, multa.getNroConsecutivoPrestamo());
            statement.setTimestamp(3, multa.getFecha());
            statement.setInt(4, multa.getValor());
            statement.setString(5, multa.getDescripcion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la inserción");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }
    }
    
    public void modificar(Multa multa) {
        String UPDATE = "UPDATE multa SET valor = ?, descripcion = ? WHERE id_usuario = ? AND nro_consecutivo_prestamo = ? AND fecha = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setInt(1, multa.getValor());
            statement.setString(2, multa.getDescripcion());
            statement.setString(3, multa.getIdUsuario());
            statement.setString(4, multa.getNroConsecutivoPrestamo());
            statement.setTimestamp(5, multa.getFecha());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya actualizado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }
    }
    
        public void eliminar(Multa multa) {
        String DELETE = "DELETE FROM multa WHERE id_usuario = ? AND nro_consecutivo_prestamo = ? AND fecha = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, multa.getIdUsuario());
            statement.setString(2, multa.getNroConsecutivoPrestamo());
            statement.setTimestamp(3, multa.getFecha());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }
    }

    public List<Multa> obtenerTodos() {
        List<Multa> multas = new ArrayList<>();

        String GETALL = "SELECT id_usuario, nro_consecutivo_prestamo, fecha, valor, descripcion FROM multa ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                multas.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }

        return multas;
    }

    public List<Multa> obtenerPorUsuario(String idUsuario) {
        List<Multa> multas = new ArrayList<>();

        String GET_BY_USER = "SELECT id_usuario, nro_consecutivo_prestamo, fecha, valor, descripcion FROM multa WHERE id_usuario = ? ORDER BY fecha DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GET_BY_USER);
            statement.setString(1, idUsuario);
            result = statement.executeQuery();

            while (result.next()) {
                multas.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }

        return multas;
    }
}


