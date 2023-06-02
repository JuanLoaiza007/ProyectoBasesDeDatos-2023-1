package Dao;

import BasesDeDatos.BibliotecaManager;
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
        //String idProfesor  = result.getString("id_profesor");
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
//        profesor (id_usuario, id_profesor, titulo, dependencia)
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

        String GETALL = "SELECT id_usuario, titulo, dependencia FROM profesor ORDER BY id_usuario ASC";

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
    public static void main(String[] args) {
        Connection conexion = BibliotecaManager.iniciarConexion();
        ProfesorDao dao = new ProfesorDao(conexion);
        /*
        Profesor profesor = new Profesor("25","G.O.A.T CR7","Departamento de Fútbol");
        dao.modificar(profesor);
        */
        /*
        String id = "25"; 
        Profesor profesor = dao.obtener(id);
        if (profesor != null) {
            System.out.println("ID empleado: " + profesor.getIdUsuario());
            System.out.println("Nombre: " + profesor.getTitulo());
            System.out.println("Cargo: " + profesor.getDependencia());
        } else {
            // No se encontró un registro con el ID especificado
            System.out.println("No se encontró un registro con el ID especificado");
        }
        */
        
        List<Profesor> profesor = dao.obtenerTodos();
        for (Profesor profesores : profesor) {
            System.out.println(profesores.getTitulo());
        }
        
    }

}