package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PrestamoDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Prestamo;
import java.sql.Timestamp;
import java.util.List;

public class PrestamoDaoTest {
    
    private static Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        PrestamoDao daoInsertar = new PrestamoDao(conexionInsertar);

        Prestamo prestamoInsertar = new Prestamo(11, "1", "1", fechaActual);
        daoInsertar.insertar(prestamoInsertar);
        System.out.println("Prestamo insertado correctamente.");
    }
    
    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        PrestamoDao daoModificar = new PrestamoDao(conexionModificar);

        Prestamo prestamoModificar = new Prestamo(11, "1", "1", fechaActual);
        daoModificar.modificar(prestamoModificar);
        System.out.println("Prestamo modificado correctamente.");
    }
    
    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        PrestamoDao daoObtener = new PrestamoDao(conexionObtener);

        Prestamo prestamoObtener = daoObtener.obtener("11");
        if (prestamoObtener != null) {
            System.out.println("Prestamo encontrado:");
            System.out.println("Nro Consecutivo: " + prestamoObtener.getNroConsecutivoPrestamo());
            System.out.println("ID Usuario: " + prestamoObtener.getIdUsuario());
            System.out.println("ID Empleado: " + prestamoObtener.getIdEmpleado());
            System.out.println("Fecha Realización: " + prestamoObtener.getFechaRealizacion());
        } else {
            System.out.println("No se encontró ningún prestamo con ese Nro Consecutivo.");
        }
    }
    
    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        PrestamoDao daoEliminar = new PrestamoDao(conexionEliminar);

        Prestamo prestamoEliminar = new Prestamo(11, "", "", null);
        daoEliminar.eliminar(prestamoEliminar);
        System.out.println("Prestamo eliminado correctamente.");
    }
    
    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        PrestamoDao daoObtenerTodos = new PrestamoDao(conexionObtenerTodos);

        List<Prestamo> prestamos = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de prestamos:");
        for (Prestamo prestamo : prestamos) {
            System.out.println("Nro Consecutivo: " + prestamo.getNroConsecutivoPrestamo());
            System.out.println("ID Usuario: " + prestamo.getIdUsuario());
            System.out.println("ID Empleado: " + prestamo.getIdEmpleado());
            System.out.println("Fecha Realización: " + prestamo.getFechaRealizacion());
            System.out.println();
        }
    }
}
