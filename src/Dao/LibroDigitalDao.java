package Dao;

import Modelos.LibroDigital;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroDigitalDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class LibroDigitalDao {

    private Connection conexion;

    public LibroDigitalDao(Connection conexion) {
        this.conexion = conexion;
    }

    private LibroDigital convertir(ResultSet result) throws SQLException {
        String isbn = result.getString("isbn");
        String direccionUrl = result.getString("direccion_url");
        int tamanioBytes = result.getInt("tamanio_bytes");
        String formato = result.getString("formato");

        return new LibroDigital(isbn, direccionUrl, tamanioBytes, formato);
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

    public void insertar(LibroDigital libroDigital) throws SQLException {
        String INSERT = "INSERT INTO libro_digital (isbn, direccion_url, tamanio_bytes, formato) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, libroDigital.getIsbn());
            statement.setString(2, libroDigital.getDireccionUrl());
            statement.setInt(3, libroDigital.getTamanioBytes());
            statement.setString(4, libroDigital.getFormato());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya guardado la inserción");
            }

        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void modificar(LibroDigital libroDigital) throws SQLException {
        String UPDATE = "UPDATE libro_digital SET tamanio_bytes = ?, formato = ? WHERE isbn = ? AND direccion_url = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);            
            statement.setInt(1, libroDigital.getTamanioBytes());
            statement.setString(2, libroDigital.getFormato());
            statement.setString(3, libroDigital.getIsbn());
            statement.setString(4, libroDigital.getDireccionUrl());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya modificado el registro");
            }

        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public void eliminar(LibroDigital libroDigital) throws SQLException {
        String DELETE = "DELETE FROM libro_digital WHERE isbn = ? AND direccion_url = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, libroDigital.getIsbn());
            statement.setString(2, libroDigital.getDireccionUrl());

            if (statement.executeUpdate() == 0) {
                System.out.println("Es posible que no se haya eliminado el registro");
            }

        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
    }

    public List<LibroDigital> obtenerTodos() {
        List<LibroDigital> librosDigitales = new ArrayList<>();

        String GETALL = "SELECT isbn, direccion_url, tamanio_bytes, formato FROM libro_digital ORDER BY isbn ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                librosDigitales.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return librosDigitales;
    }

    public List<LibroDigital> obtener(String isbn) {
        List<LibroDigital> librosDigitales = new ArrayList<>();

        String GETALL = "SELECT isbn, direccion_url, tamanio_bytes, formato FROM libro_digital WHERE isbn = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(GETALL);
            statement.setString(1, isbn);
            result = statement.executeQuery();
            /*

            if (result.next()) {
                //librosDigitales = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese ISBN y dirección URL");
            }*/
            
            while (result.next()) {
                librosDigitales.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return librosDigitales;
    }
}
