package DAO;

import Objetos.DescargaUsuarioLibro;
import Paneles.AvisosEmergentes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DescargaUsuarioLibroDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class DescargaUsuarioLibroDAOImpl {

    private Connection conexion;
    private DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("yyyy/MM/d");
    private DateTimeFormatter timeFormato = DateTimeFormatter.ofPattern("H:mm:ss");

    public DescargaUsuarioLibroDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    
    private DescargaUsuarioLibro convertir(ResultSet result) throws SQLException{
        DescargaUsuarioLibro descargaUsuarioLibro = null;
        
        String idDescarga = result.getString("id_descarga");
        String isbn = result.getString("isbn");
        String direccionUrl = result.getString("direccion_url");
        String idUsuario = result.getString("id_usuario");
        String direccionIp = result.getString("direccion_ip");
        LocalDate fecha = LocalDate.parse(result.getString("fecha"), dateFormato);
        LocalTime hora = LocalTime.parse(result.getString("hora"), timeFormato);
        
        descargaUsuarioLibro = new DescargaUsuarioLibro(idDescarga, isbn, direccionUrl, idUsuario, direccionIp, fecha, hora);
        
        return descargaUsuarioLibro;
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
    
    public void insertar(DescargaUsuarioLibro e) {
        //        descarga_usuario_libro (id_descarga, isbn, direccion_url, id_usuario, direccion, direccion_ip, fecha, hora)
        String INSERT = "INSERT INTO descarga_usuario_libro (id_descarga, isbn, direccion_url, id_usuario, direccion_ip, fecha, hora) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdDescarga());
            statement.setString(2, e.getIsbn());
            statement.setString(3, e.getDireccionUrl());
            statement.setString(4, e.getIdUsuario());
            statement.setString(5, e.getDireccionIp());
            statement.setString(6, e.getFecha().format(dateFormato));
            statement.setString(7, e.getHora().format(timeFormato));

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

    public void modificar(DescargaUsuarioLibro e) {
        String UPDATE = "UPDATE descarga_usuario_libro SET isbn = ?, direccion_url = ?, id_usuario = ?, direccion_ip = ?, fecha = ?, hora = ? WHERE id_descarga = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, e.getIdDescarga());
            statement.setString(2, e.getIsbn());
            statement.setString(3, e.getDireccionUrl());
            statement.setString(4, e.getIdUsuario());
            statement.setString(5, e.getDireccionIp());
            statement.setString(6, e.getFecha().format(dateFormato));
            statement.setString(7, e.getHora().format(timeFormato));

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

    public void eliminar(DescargaUsuarioLibro e) {
        String DELETE = "DELETE FROM descarga_usuario_libro WHERE id_descarga = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdDescarga());

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

    public List<DescargaUsuarioLibro> obtenerTodos() {
        List<DescargaUsuarioLibro> descargasUsuarioLibro = new ArrayList<>();

        String GETALL = "SELECT id_descarga, isbn, direccion_url, id_usuario, direccion_ip, fecha, hora FROM descarga_usuario_libro ORDER BY id_descarga ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                descargasUsuarioLibro.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return descargasUsuarioLibro;
    }

    public DescargaUsuarioLibro obtener(String id) {
        DescargaUsuarioLibro descarga = null;

        String GETONE = "SELECT id_descarga, isbn, direccion_url, id_usuario, direccion_ip, fecha, hora FROM descarga_usuario_libro WHERE id_descarga = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                descarga = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return descarga;
    }

}
