package Modelos;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: GenerarMultasDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import java.sql.*;

public class GenerarMultasDao {
    
    private static Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

    public static void generarMultas(Connection conexion) {
        String SELECT = ""
                // Consulta para verificar prestamos retrasados que se devolvieron tarde
                + "(SELECT * "
                + "FROM prestamo p, prestamo_ejemplar pe, devuelve_usuario_ejemplar du "
                + "WHERE p.nro_consecutivo_prestamo = pe.nro_consecutivo_prestamo "
                + "AND du.isbn = pe.isbn "
                + "AND du.nro_ejemplar = pe.nro_ejemplar "
                + "AND pe.fecha_devolucion < du.fecha) "
                
                + "UNION "
                
                // Consulta para verificar prestamos retrasados que no han sido devueltos
                + "(SELECT * "
                + "FROM prestamo p "
                + "LEFT JOIN prestamo_ejemplar pe ON p.nro_consecutivo_prestamo = pe.nro_consecutivo_prestamo "
                + "LEFT JOIN devuelve_usuario_ejemplar d ON p.nro_consecutivo_prestamo = d.nro_consecutivo_prestamo "
                + "WHERE d.nro_consecutivo_prestamo IS NULL "
                + "AND pe.fecha_devolucion < ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(SELECT);
            statement.setTimestamp(1, fechaActual);

            result = statement.executeQuery();

            while (result.next()) {
                int nroConsecutivoPrestamo = result.getInt("nro_consecutivo_prestamo");
                String idUsuario = result.getString("id_usuario");

                if (!existeMulta(idUsuario, nroConsecutivoPrestamo, conexion)) {
                    Multa multa = new Multa(idUsuario, nroConsecutivoPrestamo, fechaActual, 40000, "Retraso en devolución");
                    insertarMulta(multa, conexion);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
            cerrarConexion(conexion);
        }
    }

    private static boolean existeMulta(String idUsuario, int nroConsecutivoPrestamo, Connection conexion) {
        String SELECT = "SELECT * FROM multa WHERE id_usuario = ? AND nro_consecutivo_prestamo = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(SELECT);
            statement.setString(1, idUsuario);
            statement.setInt(2, nroConsecutivoPrestamo);

            result = statement.executeQuery();

            return result.next();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarStatement(statement);
        }

        return false;
    }

    private static void insertarMulta(Multa multa, Connection conexion) {
        String INSERT = "INSERT INTO multa (id_usuario, nro_consecutivo_prestamo, fecha, valor, descripcion) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, multa.getIdUsuario());
            statement.setInt(2, multa.getNroConsecutivoPrestamo());
            statement.setTimestamp(3, multa.getFecha());
            statement.setInt(4, multa.getValor());
            statement.setString(5, multa.getDescripcion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la inserción");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private static void cerrarStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
