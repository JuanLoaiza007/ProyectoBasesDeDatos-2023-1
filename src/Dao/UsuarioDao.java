package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: UsuarioDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    
    private Connection conexion;

    public UsuarioDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Usuario convertir(ResultSet result) throws SQLException {
        String idUsuario = result.getString("id_usuario");
        String nombre = result.getString("nombre");
        String telefono = result.getString("telefono");
        String direccion = result.getString("direccion");
        String email = result.getString("email");
        String password = result.getString("password");
        
        return new Usuario(idUsuario, nombre, telefono, direccion, email, password);
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

    public void insertar(Usuario usuario) throws SQLException {
        String INSERT = "INSERT INTO usuario (id_usuario, nombre, telefono, direccion, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, usuario.getIdUsuario());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getTelefono());
            statement.setString(4, usuario.getDireccion());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getPassword());   

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la inserción");
            }
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }
    
    public void modificar(Usuario usuario) throws SQLException {
        String UPDATE = "UPDATE usuario SET nombre = ?, telefono = ?, direccion = ?, email = ?, password = ? WHERE id_usuario = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getTelefono());
            statement.setString(3, usuario.getDireccion());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getPassword());
            statement.setString(6, usuario.getIdUsuario());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya actualizado el usuario");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(Usuario usuario) throws SQLException {
        String DELETE_ESTUDIANTE = "DELETE FROM estudiante WHERE id_usuario = ?"; 
        String DELETE_USUARIO = "DELETE FROM usuario WHERE id_usuario = ?";
        String DELETE_PROFESOR = "DELETE FROM profesor WHERE id_usuario = ?";

        PreparedStatement statementEstudiante = null;
        PreparedStatement statementProfesor = null;
        PreparedStatement statementUsuario = null;        

        try {
            statementProfesor = conexion.prepareStatement(DELETE_PROFESOR);
            statementProfesor.setString(1, usuario.getIdUsuario());
            statementEstudiante = conexion.prepareStatement(DELETE_ESTUDIANTE);
            statementEstudiante.setString(1, usuario.getIdUsuario());
            statementUsuario = conexion.prepareStatement(DELETE_USUARIO);
            statementUsuario.setString(1, usuario.getIdUsuario());
                        
            
            if (statementProfesor.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el profesor");
            } 

            if (statementEstudiante.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el eliminar");
            }             

            if (statementUsuario.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el usuario");
            }             

        } catch (SQLException ex) {
            System.out.println(ex);
            AvisosEmergentes.mostrarMensaje("Es posible que el usuario que desea borrar tenga dependencias "
                    + "sin borrar aún (ej.multas). Asegurese de eliminarlas antes de eliminar el usuario");
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statementUsuario);
        }        
    }

    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        String GETALL = "SELECT id_usuario, nombre, telefono, direccion, email, password FROM usuario";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                usuarios.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return usuarios;
    }

    public Usuario obtenerPorId(String id) {
        String GET_BY_ID = "SELECT id_usuario, nombre, telefono, direccion, email, password FROM usuario WHERE id_usuario = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GET_BY_ID);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return convertir(result);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return null;
    }
}

