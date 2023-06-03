package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoEjemplarDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.PrestamoEjemplar;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class PrestamoEjemplarDaoTest {
    
    private static Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        Connection conexion = BibliotecaManager.iniciarConexion();
        PrestamoEjemplarDao dao = new PrestamoEjemplarDao(conexion);

        PrestamoEjemplar prestamo = new PrestamoEjemplar("1", "978-0307476463", "1", fechaActual);
        dao.insertar(prestamo);
        System.out.println("Prestamo insertado correctamente.");
    }
    
    public static void pruebaObtener() {
        Connection conexion = BibliotecaManager.iniciarConexion();
        PrestamoEjemplarDao dao = new PrestamoEjemplarDao(conexion);

        List<PrestamoEjemplar> prestamos = dao.obtener("1");
        if (!prestamos.isEmpty()) {
            System.out.println("Prestamos encontrados:");
            for (PrestamoEjemplar prestamo : prestamos) {
                System.out.println("Nro Consecutivo: " + prestamo.getNroConsecutivoPrestamo());
                System.out.println("ISBN: " + prestamo.getIsbn());
                System.out.println("Nro Ejemplar: " + prestamo.getNroEjemplar());
                System.out.println("Fecha Devolucion: " + prestamo.getFechaDevolucion());
                System.out.println();
            }
        } else {
            System.out.println("No se encontraron prestamos con ese ID.");
        }
    }
    
    public static void pruebaEliminar() {
        Connection conexion = BibliotecaManager.iniciarConexion();
        PrestamoEjemplarDao dao = new PrestamoEjemplarDao(conexion);

        PrestamoEjemplar prestamo = new PrestamoEjemplar("1", "978-0307476463", "1", fechaActual);
        dao.eliminar(prestamo);
        System.out.println("Prestamo eliminado correctamente.");
    }
    
    public static void pruebaObtenerTodos() {
        Connection conexion = BibliotecaManager.iniciarConexion();
        PrestamoEjemplarDao dao = new PrestamoEjemplarDao(conexion);

        List<PrestamoEjemplar> prestamos = dao.obtenerTodos();
        System.out.println("Lista de prestamos:");
        for (PrestamoEjemplar prestamo : prestamos) {
            System.out.println("Nro Consecutivo: " + prestamo.getNroConsecutivoPrestamo());
            System.out.println("ISBN: " + prestamo.getIsbn());
            System.out.println("Nro Ejemplar: " + prestamo.getNroEjemplar());
            System.out.println("Fecha Devolucion: " + prestamo.getFechaDevolucion());
            System.out.println();
        }
    }
}
