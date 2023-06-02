package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Libro;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: LibroDao.java
 Licencia: GNU-GPL
 * @version 1.0.1
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

    public void insertar(Libro e) {        
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
            statement.setString(6, Integer.toString(e.getNroPaginas()));

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

    public void modificar(Libro e) {
        String UPDATE = "UPDATE libro SET codigo_area = ?, codigo_editorial = ?, titulo = ?, anio_publicacion = ?, nro_paginas = ? WHERE isbn = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);       
            statement.setString(1, e.getCodigoArea());
            statement.setString(2, e.getCodigoEditorial());
            statement.setString(3, e.getTitulo());
            statement.setString(4, e.getAnioPublicacion());
            statement.setString(5, Integer.toString(e.getNroPaginas()));
            statement.setString(6, e.getIsbn());

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

    public void eliminar(Libro e) {
        String DELETE = "DELETE FROM libro WHERE isbn = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIsbn());

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
    
    public static void main(String[] args) {
        Connection conexion = BibliotecaManager.iniciarConexion();
        LibroDao dao = new LibroDao(conexion);
        /*
        Libro libro = new Libro("978-9875805174", "1", "4", "Bestiario", "1951", 177);
        dao.eliminar(libro);
        */
        /*
        String id = "978-9507317181"; 
        Libro libro = dao.obtener(id);
        if (libro != null) {
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Nombre: " + libro.getCodigoArea());
            System.out.println("Código editorial: " + libro.getCodigoEditorial());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Año de publicación: " + libro.getAnioPublicacion());
            System.out.println("Número de páginas: " + libro.getNroPaginas());
        } else {
            // No se encontró un registro con el ID especificado
            System.out.println("No se encontró un registro con el ID especificado");
        }
        */
        
        List<Libro> libro = dao.obtenerTodos();
        for (Libro libros : libro) {
            System.out.println(libros.getIsbn());
        }
        
    }

}

