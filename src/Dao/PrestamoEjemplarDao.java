package Dao;

import Modelos.PrestamoEjemplar;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoEjemplarDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PrestamoEjemplarDao {
    
    private Connection conexion;

    public PrestamoEjemplarDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private PrestamoEjemplar convertir(ResultSet result) throws SQLException{
        PrestamoEjemplar prestamoEjemplar = null;
        
        
        int nroConsecutivoPrestamo = result.getInt("nro_consecutivo_prestamo");
        String isbn = result.getString("isbn");
        String nroEjemplar = result.getString("nro_ejemplar");
        Timestamp fechaDevolucion = result.getTimestamp("fecha_devolucion");
       
        
        prestamoEjemplar = new PrestamoEjemplar(nroConsecutivoPrestamo, isbn, nroEjemplar, fechaDevolucion);
        
        return prestamoEjemplar;
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

    public void insertar(PrestamoEjemplar e) {
        String INSERT = "INSERT INTO prestamo_ejemplar (nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setInt(1, e.getNroConsecutivoPrestamo());
            statement.setString(2, e.getIsbn());
            statement.setString(3, e.getNroEjemplar());
            statement.setTimestamp(4, e.getFechaDevolucion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate key value violates unique constraint")) {
                AvisosEmergentes.mostrarMensaje("Ya existe un registro con este id");
            } else if (ex.getMessage().contains("violates foreign key constraint")) {
                AvisosEmergentes.mostrarMensaje("No puedes referenciar otro registro que no existe");
            } else
                System.out.println(ex.getMessage());
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(PrestamoEjemplar e) {
        String DELETE = "DELETE FROM prestamo_ejemplar WHERE nro_consecutivo_prestamo = ? AND isbn = ? AND nro_ejemplar = ? AND fecha_devolucion = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setInt(1, e.getNroConsecutivoPrestamo());
            statement.setString(2, e.getIsbn());
            statement.setString(3, e.getNroEjemplar());
            statement.setTimestamp(4, e.getFechaDevolucion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el registro");
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en modificar");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<PrestamoEjemplar> obtenerTodos() {
        List<PrestamoEjemplar> prestamosEjemplares = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion FROM prestamo_ejemplar ORDER BY fecha_devolucion DESC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                prestamosEjemplares.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en obtener todos");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return prestamosEjemplares;
    }

    /**
     * Obtener por nroConsecutivoPrestamo
     * @param id nroConsecutivoPrestamo
     * @return Los prestamoEjemplar de ese nroConsecutivoPrestamo
     */
    public List<PrestamoEjemplar> obtener(String id) {
        List<PrestamoEjemplar> prestamosEjemplares = new ArrayList<>();

        String GETALL = "SELECT nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion FROM prestamo_ejemplar WHERE nro_consecutivo_prestamo = ? ORDER BY fecha_devolucion DESC";
        
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                prestamosEjemplares.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex + " - Error en obtener");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return prestamosEjemplares;
    }
}
