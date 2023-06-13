package Dao;

import Modelos.Autor;
import Modelos.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class LibroDao{
    
    private Connection conexion;

    public LibroDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Libro convertir(ResultSet result) throws SQLException{
        Libro libro = null;

        String isbn = result.getString("isbn");
        String codigoArea = result.getString("codigo_area");
        String codigoEditorial = result.getString("codigo_editorial");
        String titulo = result.getString("titulo");
        String anioPublicacion = result.getString("anio_publicacion");
        int nroPaginas = result.getInt("nro_paginas");
        
        libro = new Libro(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, nroPaginas);
        
        return libro;
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

    public void insertar(Libro e) throws SQLException {        
        String INSERT = "INSERT INTO libro (isbn, codigo_area, codigo_editorial, titulo, anio_publicacion, nro_paginas) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIsbn());
            statement.setString(2, e.getCodigoArea());
            statement.setString(3, e.getCodigoEditorial());
            statement.setString(4, e.getTitulo());
            statement.setString(5, e.getAnioPublicacion());
            statement.setInt(6, e.getNroPaginas());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la insercion");
            }
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void modificar(Libro e) throws SQLException {
        String UPDATE = "UPDATE libro SET codigo_area = ?, codigo_editorial = ?, titulo = ?, anio_publicacion = ?, nro_paginas = ? WHERE isbn = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);       
            statement.setString(1, e.getCodigoArea());
            statement.setString(2, e.getCodigoEditorial());
            statement.setString(3, e.getTitulo());
            statement.setString(4, e.getAnioPublicacion());
            statement.setInt(5, e.getNroPaginas());
            statement.setString(6, e.getIsbn());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya modificado el registro");
            }
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(Libro e) throws SQLException {
        String DELETE = "DELETE FROM libro WHERE isbn = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIsbn());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el registro");
            }
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<Libro> obtenerTodos() {
        List<Libro> libros = new ArrayList<>();

        String GETALL = "SELECT isbn, codigo_area, codigo_editorial, titulo, anio_publicacion, nro_paginas FROM libro ORDER BY titulo ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                libros.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return libros;
    }

    public Libro obtener(String id) {
        Libro libro = null;

        String GETONE = "SELECT isbn, codigo_area, codigo_editorial, titulo, anio_publicacion, nro_paginas FROM libro WHERE isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                libro = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return libro;
    }
    
    public List<Autor> otenerAutoresLibro(String isbn){
        java.util.List<Autor> autores = new ArrayList<>();
        
        String GETALL = "SELECT A.codigo_autor, A.primer_nombre, A.segundo_nombre, A.primer_apellido, A.segundo_apellido "
                + "FROM autor AS A, libro_autor AS LA "
                + "WHERE A.codigo_autor = LA.codigo_autor "
                + "AND LA.isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, isbn);
            result = statement.executeQuery();

            while (result.next()) {
                Autor autorActual;
                
                String codigoAutor = result.getString("codigo_autor");
                String primerNombre = result.getString("primer_nombre");
                String segundoNombre = result.getString("segundo_nombre");
                String primerApellido = result.getString("primer_apellido");
                String segundoApellido = result.getString("segundo_apellido");

                autorActual = new Autor(codigoAutor, primerNombre, segundoNombre, primerApellido, segundoApellido);
                autores.add(autorActual);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return autores;
        
    }
}
