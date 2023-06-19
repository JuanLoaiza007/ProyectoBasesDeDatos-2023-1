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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DevuelveUsuarioEjemplarDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaObtener();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoInsertar = new DevuelveUsuarioEjemplarDao(conexionInsertar);

        DevuelveUsuarioEjemplar devolucionInsertar = new DevuelveUsuarioEjemplar(11, "1", "978-0307476463", "1", new Timestamp(System.currentTimeMillis()));
        try {
            daoInsertar.insertar(devolucionInsertar);
        } catch (SQLException ex) {
            Logger.getLogger(DevuelveUsuarioEjemplarDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Devolución insertada correctamente.");
    }
    
    public static void pruebaObtener() {
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(DevuelveUsuarioEjemplarDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        DevuelveUsuarioEjemplarDao daoObtenerTodos = new DevuelveUsuarioEjemplarDao(conexionObtenerTodos);

        List<DevuelveUsuarioEjemplar> devoluciones;
        try {
            devoluciones = daoObtenerTodos.obtenerTodos();
            
            for (DevuelveUsuarioEjemplar devolucion : devoluciones) {
            System.out.println("ID Usuario: " + devolucion.getIdUsuario());
            System.out.println("ISBN: " + devolucion.getIsbn());
            System.out.println("Nro Ejemplar: " + devolucion.getNroEjemplar());
            System.out.println("Fecha: " + devolucion.getFecha());
            System.out.println();
        }
        } catch (SQLException ex) {
            Logger.getLogger(DevuelveUsuarioEjemplarDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Lista de devoluciones:");
        
    }
}

