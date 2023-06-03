package Dao;

import Modelos.LibroAutor;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroAutorDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class LibroAutorDAOImpl{
    
    private Connection conexion;

    public LibroAutorDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    
    private LibroAutor convertir(ResultSet result) throws SQLException{
        LibroAutor libroAutor = null;
        
        String isbn = result.getString("isbn");
        String codigoAutor  = result.getString("codigo_autor");
        
        libroAutor = new LibroAutor(isbn, codigoAutor);
        
        return libroAutor;
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

    public void insertar(LibroAutor e) {
        String INSERT = "INSERT INTO libro_autor (isbn, codigo_autor) VALUES (?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getCodigoAutor());


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

    public void modificar(LibroAutor antiguo, LibroAutor nuevo) {
        eliminar(antiguo);
        insertar(nuevo);
    }

    public void eliminar(LibroAutor e) {
        String DELETE = "DELETE FROM libro_autor WHERE isbn = ? AND codigo_autor = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getCodigoAutor());

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

    public List<LibroAutor> obtenerTodos() {
        List<LibroAutor> librosAutoress = new ArrayList<>();

        String GETALL = "SELECT isbn, codigo_autor FROM libro_autor ORDER BY isbn, codigo_autor ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                librosAutoress.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return librosAutoress;
    }
    

    /**
     * Busqueda por Isbn
     * @param id El isbn del Libro
     * @return Un ArrayList con los autores de ese isbn
     */
    public List<LibroAutor> obtener(String id) {
        List<LibroAutor> libroAutor = new ArrayList<>();

        String GETONE = "SELECT isbn, codigo_autor FROM librosAutores WHERE isbn = ? ORDER BY codigo_autor ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                libroAutor.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return libroAutor;
    }
    
}
