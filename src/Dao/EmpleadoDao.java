package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Empleado;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: EmpleadoDao.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EmpleadoDao{
    
    private Connection conexion;

    public EmpleadoDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Empleado convertir(ResultSet result) throws SQLException{
        Empleado empleado = null;
        
        String idEmpleado = result.getString("id_empleado");
        String nombre = result.getString("nombre");
        String cargo = result.getString("cargo");
        
        empleado = new Empleado(idEmpleado, nombre, cargo);
        
        return empleado;
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

    public void insertar(Empleado e) {
        String INSERT = "INSERT INTO empleado (id_empleado, nombre, cargo) VALUES (?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdEmpleado());
            statement.setString(2, e.getNombre());
            statement.setString(3, e.getCargo());

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

    public void modificar(Empleado e) {
        String UPDATE = "UPDATE empleado SET nombre = ?, cargo = ? WHERE id_empleado = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);          
            statement.setString(1, e.getNombre());
            statement.setString(2, e.getCargo());
            statement.setString(3, e.getIdEmpleado());

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

    public void eliminar(Empleado e) {
        String DELETE = "DELETE FROM empleado WHERE id_empleado = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdEmpleado());

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
   
    public List<Empleado> obtenerTodos() {
        List<Empleado> empleados = new ArrayList<>();

        String GETALL = "SELECT id_empleado, nombre, cargo FROM empleado ORDER BY id_empleado ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                empleados.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return empleados;
    }

    public Empleado obtener(String id) {
        Empleado empleado = null;

        String GETONE = "SELECT id_empleado, nombre, cargo FROM empleado WHERE id_empleado = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                empleado = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return empleado;
    }
    
    public static void main(String[] args) {
        Connection conexion = BibliotecaManager.iniciarConexion();
        EmpleadoDao dao = new EmpleadoDao(conexion);
        /*
        Empleado empleado = new Empleado("20","Ronaldo","Nazario");
        dao.eliminar(empleado);
        */
        
        String id = "21"; 
        Empleado empleado = dao.obtener(id);
        if (empleado != null) {
            System.out.println("ID empleado: " + empleado.getIdEmpleado());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Cargo: " + empleado.getCargo());
        } else {
            // No se encontró un registro con el ID especificado
            System.out.println("No se encontró un registro con el ID especificado");
        }
        
        /*
        List<Empleado> empleado = dao.obtenerTodos();
        for (Empleado empleados : empleado) {
            System.out.println(empleados.getCargo());
        }
        */
    }
}
