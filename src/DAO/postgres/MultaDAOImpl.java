package DAO.postgres;

import Objetos.Multa;
import Paneles.AvisosEmergentes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: MultaDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class MultaDAOImpl{
    
    private Connection conexion;
    private DateTimeFormatter dateFormato = DateTimeFormatter.ofPattern("yyyy/MM/d H:mm:ss"); 

    public MultaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    
    private Multa convertir(ResultSet result) throws SQLException{
        Multa multa = null;
        
        String idMulta  = result.getString("id_multa");
        String idUsuario = result.getString("id_usuario");
        LocalDateTime fecha = LocalDateTime.parse(result.getString("fecha"));
        int valor = result.getInt("valor");
        String descripcion = result.getString("descripcion");
    
        multa = new Multa(idMulta, idUsuario, fecha, valor, descripcion);
        
        return multa;
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
    
    public void insertar(Multa e) {
        String INSERT = "INSERT INTO multa (id_multa, id_usuario, fecha, valor, descripcion) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdMulta());
            statement.setString(2, e.getIdUsuario());
            statement.setString(3, e.getFecha().format(dateFormato));
            statement.setString(4, Integer.toString(e.getValor()));
            statement.setString(5, e.getDescripcion());

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

    public void modificar(Multa e) {
        String UPDATE = "UPDATE multa SET id_usuario = ?, fecha = ?, valor = ?, descripcion = ? WHERE id_multa = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);                 
            statement.setString(1, e.getIdUsuario());
            statement.setString(2, e.getFecha().format(dateFormato));
            statement.setString(3, Integer.toString(e.getValor()));
            statement.setString(4, e.getDescripcion());
            statement.setString(5, e.getIdMulta());

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

    public void eliminar(Multa e) {
        String DELETE = "DELETE FROM multa WHERE id_multa = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdMulta());

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

    public List<Multa> obtenerTodos() {
        List<Multa> multas = new ArrayList<>();

        String GETALL = "SELECT id_multa, id_usuario, fecha, valor, descripcion FROM multa ORDER BY fecha ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                multas.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return multas;
    }

    public Multa obtener(String id) {
        Multa multa = null;

        String GETONE = "SELECT id_multa, id_usuario, fecha, valor, descripcion FROM multa WHERE id_multa = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                multa = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return multa;
    }
}
