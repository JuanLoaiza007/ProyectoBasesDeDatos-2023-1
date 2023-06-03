package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DevuelveUsuarioEjemplarDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.DevuelveUsuarioEjemplar;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class DevuelveUsuarioEjemplarDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoInsertar = new DevuelveUsuarioEjemplarDao(conexionInsertar);

        DevuelveUsuarioEjemplar devolucionInsertar = new DevuelveUsuarioEjemplar("1", "978-0307476463", "1", new Timestamp(System.currentTimeMillis()));
        daoInsertar.insertar(devolucionInsertar);
        System.out.println("Devolución insertada correctamente.");
    }
    
    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoObtener = new DevuelveUsuarioEjemplarDao(conexionObtener);

        List<DevuelveUsuarioEjemplar> devoluciones = daoObtener.obtener("1");
        if (!devoluciones.isEmpty()) {
            System.out.println("Devoluciones encontradas:");
            for (DevuelveUsuarioEjemplar devolucion : devoluciones) {
                System.out.println("ID Usuario: " + devolucion.getIdUsuario());
                System.out.println("ISBN: " + devolucion.getIsbn());
                System.out.println("Nro Ejemplar: " + devolucion.getNroEjemplar());
                System.out.println("Fecha: " + devolucion.getFecha());
                System.out.println();
            }
        } else {
            System.out.println("No se encontraron devoluciones para ese ID de usuario.");
        }
    }
    
    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoEliminar = new DevuelveUsuarioEjemplarDao(conexionEliminar);

        DevuelveUsuarioEjemplar devolucionEliminar = new DevuelveUsuarioEjemplar("1", "1234567890", "A1", new Timestamp(System.currentTimeMillis()));
        daoEliminar.eliminar(devolucionEliminar);
        System.out.println("Devolución eliminada correctamente.");
    }
    
    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoObtenerTodos = new DevuelveUsuarioEjemplarDao(conexionObtenerTodos);

        List<DevuelveUsuarioEjemplar> devoluciones = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de devoluciones:");
        for (DevuelveUsuarioEjemplar devolucion : devoluciones) {
            System.out.println("ID Usuario: " + devolucion.getIdUsuario());
            System.out.println("ISBN: " + devolucion.getIsbn());
            System.out.println("Nro Ejemplar: " + devolucion.getNroEjemplar());
            System.out.println("Fecha: " + devolucion.getFecha());
            System.out.println();
        }
    }
}

