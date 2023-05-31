package Dao;

import Modelos.Estudiante;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: EstudianteDao.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class EstudianteDao{
    
    private Connection conexion;

    public EstudianteDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Estudiante convertir(ResultSet result) throws SQLException{
        Estudiante estudiante = null;
        
        String idUsuario = result.getString("id_usuario");
        String idEstudiante  = result.getString("id_estudiante");
        String carrera = result.getString("carrera");
        String universidad = result.getString("universidad");
        
        estudiante = new Estudiante(idUsuario, idEstudiante, carrera, universidad);
        
        return estudiante;
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

    public void insertar(Estudiante e) {
//        estudiante (id_usuario, id_estudiante, carrera, universidad)
        String INSERT = "INSERT INTO estudiante (id_usuario, id_estudiante, carrera, universidad) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdUsuario());
            statement.setString(2, e.getIdEstudiante());
            statement.setString(3, e.getCarrera());
            statement.setString(4, e.getUniversidad());

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

    public void modificar(Estudiante e) {
        String UPDATE = "UPDATE estudiante SET carrera = ?, universidad = ? WHERE id_estudiante = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);          
            statement.setString(1, e.getIdEstudiante());
            statement.setString(2, e.getCarrera());
            statement.setString(3, e.getUniversidad());
            statement.setString(4, e.getIdUsuario());

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

    public void eliminar(Estudiante e) {
        String DELETE = "DELETE FROM estudiante WHERE id_estudiante = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdEstudiante());

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

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();

        String GETALL = "SELECT id_usuario, id_estudiante, carrera, universidad FROM estudiante ORDER BY id_estudiante ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                estudiantes.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return estudiantes;
    }

    public Estudiante obtener(String id) {
        Estudiante estudiante = null;

        String GETONE = "SELECT id_usuario, id_estudiante, carrera, universidad FROM estudiante WHERE id_estudiante = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                estudiante = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return estudiante;
    }

}
