package Dao;

import Modelos.AreaConocimiento;
import Modelos.Profesor;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: ProfesorDao.java
 Licencia: GNU-GPL
 * @version 1.0.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class ProfesorDao{
    
    private Connection conexion;

    public ProfesorDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Profesor convertir(ResultSet result) throws SQLException{
        Profesor profesor = null;
        
        String idUsuario = result.getString("id_usuario");
        String titulo = result.getString("titulo");
        String dependencia = result.getString("dependencia");
        
        profesor = new Profesor(idUsuario, titulo, dependencia);
        
        return profesor;
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

    public void insertar(Profesor e) {
        String INSERT = "INSERT INTO profesor (id_usuario, titulo, dependencia) VALUES (?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdUsuario());
            statement.setString(2, e.getTitulo());
            statement.setString(3, e.getDependencia());

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

    public void modificar(Profesor e) {
        String UPDATE = "UPDATE profesor SET titulo = ?, dependencia = ? WHERE id_usuario = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);          
            statement.setString(1, e.getTitulo());
            statement.setString(2, e.getDependencia());
            statement.setString(3, e.getIdUsuario());

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

    public void eliminar(Profesor e) {
        String DELETE = "DELETE FROM profesor WHERE id_usuario = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdUsuario());

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

    public List<Profesor> obtenerTodos() {
        List<Profesor> profesores = new ArrayList<>();

        String GETALL = "SELECT id_usuario, titulo, dependencia FROM profesor ORDER BY dependencia ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                profesores.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return profesores;
    }

    public Profesor obtener(String id) {
        Profesor profesor = null;

        String GETONE = "SELECT id_usuario, titulo, dependencia FROM profesor WHERE id_usuario = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                profesor = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return profesor;
    }
    
    public List<AreaConocimiento> otenerAreasProfesor(String id){
        java.util.List<AreaConocimiento> areas = new ArrayList<>();
        
        String GETALL = "SELECT A.codigo_area, A.codigo_area_padre, A.nombre, A.descripcion "
                + "FROM profesor AS P, profesor_area_conocimiento AS PA, area_conocimiento A "
                + "WHERE P.id_usuario = PA.id_usuario "
                + "AND PA.codigo_area = A.codigo_area "
                + "AND P.id_usuario = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, id);
            result = statement.executeQuery();

            while (result.next()) {
                AreaConocimiento areaActual;
                
                String codigoArea = result.getString("codigo_area");
                String codigoAreaPadre = result.getString("codigo_area_padre");
                String nombre = result.getString("nombre");
                String descripcion = result.getString("descripcion");

                areaActual = new AreaConocimiento(codigoArea, codigoAreaPadre, nombre, descripcion);
                areas.add(areaActual);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return areas;
        
    }    
}