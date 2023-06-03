package Dao;

import Modelos.AreaConocimiento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: AreaConocimientoDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class AreaConocimientoDao{

    private Connection conexion;

    public AreaConocimientoDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private AreaConocimiento convertir(ResultSet result) throws SQLException{
        AreaConocimiento areaConocimiento = null;
        
        String codigoArea = result.getString("codigo_area");
        String codigoAreaPadre = result.getString("codigo_area_padre");
        String nombre = result.getString("nombre");
        String descripcion = result.getString("descripcion");
        
        areaConocimiento = new AreaConocimiento(codigoArea, codigoAreaPadre, nombre, descripcion);
        
        return areaConocimiento;
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
    
    public void insertar(AreaConocimiento e) {
        String INSERT = "INSERT INTO area_conocimiento (codigo_area, codigo_area_padre, nombre, descripcion) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getCodigoArea());
            statement.setString(2, e.getCodigoAreaPadre());
            statement.setString(3, e.getNombre());
            statement.setString(4, e.getDescripcion());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }
    
    public void modificar(AreaConocimiento e) {
       
        String UPDATE = "UPDATE area_conocimiento SET codigo_area_padre = ?, nombre = ?, descripcion = ? WHERE codigo_area = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, e.getCodigoAreaPadre());
            statement.setString(2, e.getNombre());
            statement.setString(3, e.getDescripcion());
            statement.setString(4, e.getCodigoArea());

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
    
    public void eliminar(AreaConocimiento e) {
        String DELETE = "DELETE FROM area_conocimiento WHERE codigo_area = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getCodigoArea());

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
    
    public List<AreaConocimiento> obtenerTodos() {
        List<AreaConocimiento> areasConocimiento = new ArrayList<>();

        String GETALL = "SELECT codigo_area, codigo_area_padre, nombre, descripcion FROM area_conocimiento ORDER BY nombre ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                areasConocimiento.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return areasConocimiento;
    }
    
    public AreaConocimiento obtener(String id) {
        AreaConocimiento areaConocimiento = null;

        String GETONE = "SELECT codigo_area, codigo_area_padre, nombre, descripcion FROM area_conocimiento WHERE codigo_area = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                areaConocimiento = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return areaConocimiento;
    }

}