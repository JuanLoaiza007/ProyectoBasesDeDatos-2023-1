package Dao;

import Modelos.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PrestamoDao{
    
    private Connection conexion;

    public PrestamoDao(Connection conexion) {
        this.conexion = conexion;
    }

    
    private Prestamo convertir(ResultSet result) throws SQLException{
        Prestamo prestamo = null;
        
        String nroConsecutivoPrestamo = result.getString("nro_consecutivo_prestamo");
        String idUsuario = result.getString("id_usuario");
        String idEmpleado = result.getString("id_empleado");
        Timestamp fechaRealizacion = result.getTimestamp("fecha_realizacion");      
        
        prestamo = new Prestamo(nroConsecutivoPrestamo, idUsuario, idEmpleado, fechaRealizacion);
        
        return prestamo;
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
    
    public void insertar(Prestamo e) {
        String INSERT = "INSERT INTO prestamo (nro_consecutivo_prestamo, id_usuario, id_empleado, fecha_realizacion) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getNroConsecutivoPrestamo());
            statement.setString(2, e.getIdUsuario());
            statement.setString(3, e.getIdEmpleado());
            statement.setTimestamp(4, e.getFechaRealizacion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en insertar");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }
    
    public void modificar(Prestamo e) {
        String UPDATE = "UPDATE prestamo SET id_usuario = ?, id_empleado = ?, fecha_realizacion = ? WHERE nro_consecutivo_prestamo = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);       
            statement.setString(1, e.getIdUsuario());
            statement.setString(2, e.getIdEmpleado());
            statement.setTimestamp(3, e.getFechaRealizacion());
            statement.setString(4, e.getNroConsecutivoPrestamo());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya modificado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en modificar");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(Prestamo e) {
        String DELETE = "DELETE FROM prestamo WHERE nro_consecutivo_prestamo = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getNroConsecutivoPrestamo());

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

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> prestamos = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_prestamo, id_usuario, id_empleado, fecha_realizacion FROM prestamo ORDER BY fecha_realizacion DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                prestamos.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return prestamos;
    }

    public Prestamo obtener(String id) {
        Prestamo prestamo = null;

        String GETONE = "SELECT nro_consecutivo_prestamo, id_usuario, id_empleado, fecha_realizacion FROM prestamo WHERE nro_consecutivo_prestamo = ? ORDER BY fecha_realizacion DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                prestamo = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return prestamo;
    }

}
