package DAO.postgres;

import Objetos.Ubicacion;
import Paneles.AvisosEmergentes;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: UbicacionDAOImpl.java
 * Licencia: GNU-GPL
 * @version 1.1
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */
public class UbicacionDAOImpl {

    private Connection conexion;

    public UbicacionDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Convierte un ResultSet a un objeto de tipo Ubicacion
     * @param result El ResultSet a convertir
     * @return ubicacion La Ubicacion equivalente
     * @throws SQLException Cualquier excepcion de postgres
     */
    private Ubicacion convertir(ResultSet result) throws SQLException {
        Ubicacion ubicacion = null;

        String id_ubicacion = result.getString("id_ubicacion");
        String nombre_sala = result.getString("nombre_sala");
        int nro_pasillo = result.getInt("nro_pasillo");
        int estante = result.getInt("estante");
        int nro_cajon = result.getInt("nro_cajon");

        ubicacion = new Ubicacion(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);

        return ubicacion;
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

    /**
     * Inserta un nuevo registro a la tabla, usa un PreparedStatement interno
     * @param e (Ubicacion) La ubicacion a insertar en la base de datos
     */
    public void insertar(Ubicacion e) {
        String INSERT = "INSERT INTO ubicacion (id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, e.getIdUbicacion());
            statement.setString(2, e.getNombreSala());
            statement.setInt(3, e.getNroPasillo());
            statement.setInt(4, e.getEstante());
            statement.setInt(5, e.getNroCajon());

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

    /**
     * Modifica un registro de la tabla, usa un PreparedStatement interno
     * @param e (Ubicacion) La ubicacion a modificar en la base de datos, usa su Id para reconocer pero no modifica su Id
     */
    public void modificar(Ubicacion e) {
        String UPDATE = "UPDATE ubicacion SET nombre_sala = ?, nro_pasillo = ?, estante = ?, nro_cajon = ? WHERE id_ubicacion = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(UPDATE);
            statement.setString(1, e.getNombreSala());
            statement.setInt(2, e.getNroPasillo());
            statement.setInt(3, e.getEstante());
            statement.setInt(4, e.getNroCajon());
            statement.setString(5, e.getIdUbicacion());

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

    /**
     * Elimina un registro de la tabla, usa un PreparedStatement interno
     * @param e (Ubicacion) La ubicacion a eliminar en la base de datos, usa su Id para reconocer pero no modifica su Id
     */
    public void eliminar(Ubicacion e) {
        String DELETE = "DELETE FROM ubicacion WHERE id_ubicacion = ?";

        PreparedStatement statement = null;

        try {
            statement = conexion.prepareStatement(DELETE);
            statement.setString(1, e.getIdUbicacion());

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

    /**
     * Obtiene un ArrayList de todas las ubicaciones registradas en la base de datos
     * @return ubicaciones (List(Ubicaciones)) La lista de todos los registros; null si no hay registros
     */
    public List<Ubicacion> obtenerTodos() {
        List<Ubicacion> ubicaciones = new ArrayList<>();

        String GETALL = "SELECT id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon FROM ubicacion ORDER BY id_ubicacion ASC";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETALL);
            result = statement.executeQuery();

            while (result.next()) {
                ubicaciones.add(convertir(result));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }

        return ubicaciones;
    }

    /**
     * Obtiene una ubicacion especifica
     * @param id (String) El id del a ubicacion a buscar
     * @return ubicacion (Ubicacion) La ubicacion si existe; null si no existe
     */
    public Ubicacion obtener(String id) {
        Ubicacion ubicacion = null;

        String GETONE = "SELECT id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon FROM ubicacion WHERE id_ubicacion = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            statement = conexion.prepareStatement(GETONE);
            statement.setString(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                ubicacion = convertir(result);
            } else {
                System.out.println("No se ha encontrado un registro con ese Id");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(statement);
        }
        return ubicacion;
    }
}