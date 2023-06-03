package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SolicitudDao.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */


import BasesDeDatos.BibliotecaManager;
import Modelos.Solicitud;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class SolicitudDaoTest {
    
    private static Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        SolicitudDao daoInsertar = new SolicitudDao(conexionInsertar);

        Solicitud solicitudInsertar = new Solicitud("2100", "1", "1", "isbn", "titulo", "descripcion", fechaActual);
        daoInsertar.insertar(solicitudInsertar);
        System.out.println("Solicitud insertada correctamente.");
    }

    public static void pruebaModificar() {
        Connection conexionModificar = BibliotecaManager.iniciarConexion();
        SolicitudDao daoModificar = new SolicitudDao(conexionModificar);

        Solicitud solicitudModificar = new Solicitud("2100", "1", "1", "isbn_modificado", "titulo_modificado", "descripcion_modificada", fechaActual);
        daoModificar.modificar(solicitudModificar);
        System.out.println("Solicitud modificada correctamente.");
    }

    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        SolicitudDao daoObtener = new SolicitudDao(conexionObtener);

        Solicitud solicitudObtener = daoObtener.obtenerPorNroConsecutivo("2100");
        if (solicitudObtener != null) {
            System.out.println("Solicitud encontrada:");
            System.out.println("Nro. Consecutivo: " + solicitudObtener.getNroConsecutivoSolicitud());
            System.out.println("ID Usuario: " + solicitudObtener.getIdUsuario());
            System.out.println("ID Empleado: " + solicitudObtener.getIdEmpleado());
            System.out.println("ISBN: " + solicitudObtener.getIsbn());
            System.out.println("Título: " + solicitudObtener.getTitulo());
            System.out.println("Descripción: " + solicitudObtener.getDescripcion());
            System.out.println("Fecha: " + solicitudObtener.getFecha());
        } else {
            System.out.println("No se encontró ninguna solicitud con ese Nro. Consecutivo.");
        }
    }

    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        SolicitudDao daoEliminar = new SolicitudDao(conexionEliminar);

        daoEliminar.eliminar("2100");
        System.out.println("Solicitud eliminada correctamente.");
    }

    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        SolicitudDao daoObtenerTodos = new SolicitudDao(conexionObtenerTodos);

        List<Solicitud> solicitudes = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de solicitudes:");
        for (Solicitud solicitud : solicitudes) {
            System.out.println("Nro. Consecutivo: " + solicitud.getNroConsecutivoSolicitud());
            System.out.println("ID Usuario: " + solicitud.getIdUsuario());
            System.out.println("ID Empleado: " + solicitud.getIdEmpleado());
            System.out.println("ISBN: " + solicitud.getIsbn());
            System.out.println("Título: " + solicitud.getTitulo());
            System.out.println("Descripción: " + solicitud.getDescripcion());
            System.out.println("Fecha: " + solicitud.getFecha());
            System.out.println();
        }
    }
}
