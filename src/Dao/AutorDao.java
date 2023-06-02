package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Autor;
import Paneles.AvisosEmergentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: AutorDao.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class AutorDao {

    private Connection conexion;

    public AutorDao(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Autor convertir(ResultSet result) throws SQLException{
        Autor autor = null;
        
        String codigoAutor = result.getString("codigo_autor");
        String primerNombre = result.getString("primer_nombre");
        String segundoNombre = result.getString("segundo_nombre");
        String primerApellido = result.getString("primer_apellido");
        String segundoApellido = result.getString("segundo_apellido");
        
        autor = new Autor(codigoAutor, primerNombre, segundoNombre, primerApellido, segundoApellido);
        
        return autor;
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
    
    public void insertar(Autor e) {
        //        autor (codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido) 
        String INSERT = "INSERT INTO autor (codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getCodigoAutor());
            statement.setString(2, e.getPrimerNombre());
            statement.setString(3, e.getSegundoNombre());
            statement.setString(4, e.getPrimerApellido());
            statement.setString(5, e.getSegundoApellido());

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
  
    public void modificar(Autor e) {
        String UPDATE = "UPDATE autor SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ? WHERE codigo_autor = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);            
            statement.setString(1, e.getPrimerNombre());
            statement.setString(2, e.getSegundoNombre());
            statement.setString(3, e.getPrimerApellido());
            statement.setString(4, e.getSegundoApellido());
            statement.setString(5, e.getCodigoAutor());

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
   
    public void eliminar(Autor e) {
        String DELETE = "DELETE FROM autor WHERE codigo_autor = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getCodigoAutor());

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
    
    public List<Autor> obtenerTodos() {
        List<Autor> autores = new ArrayList<>();

        String GETALL = "SELECT codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido FROM autor ORDER BY codigo_autor ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                autores.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return autores;
    }

    public Autor obtener(String id) {
        Autor autor = null;

        String GETONE = "SELECT codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido FROM autor WHERE codigo_autor = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                autor = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return autor;
    }
    public static void main(String[] args) {
        Connection conexion = BibliotecaManager.iniciarConexion();
        AutorDao dao = new AutorDao(conexion);
        /*
        Autor autor = new Autor("50","Ronaldo","de","Assis","Moreira");
        dao.eliminar(autor);
        */
        /*
        String id = "51"; 
        Autor autor = dao.obtener(id);
        if (autor != null) {
            System.out.println("Código de autor: " + autor.getCodigoAutor());
            System.out.println("Primer nombre: " + autor.getPrimerNombre());
            System.out.println("Segundo nombre: " + autor.getSegundoNombre());
            System.out.println("Primer apellido: " + autor.getPrimerApellido());
            System.out.println("Segundo apellido: " + autor.getSegundoApellido());            
        } else {
            // No se encontró un registro con el ID especificado
            System.out.println("No se encontró un registro con el ID especificado");
        }
        */
        
        List<Autor> autor = dao.obtenerTodos();
        for (Autor autores : autor) {
            System.out.println(autores.getPrimerApellido());
        }
    }

}