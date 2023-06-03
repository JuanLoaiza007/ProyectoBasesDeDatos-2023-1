package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EjemplarDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Ejemplar;
import java.sql.*;
import java.util.List;

public class EjemplarDaoTest {
    
    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        EjemplarDao daoInsertar = new EjemplarDao(conexionInsertar);

        Ejemplar ejemplarInsertar = new Ejemplar("978-0307476463", "2100", "Sala A", 1, 2, 3);
        daoInsertar.insertar(ejemplarInsertar);
        System.out.println("Ejemplar insertado correctamente.");
    }
    
    public static void pruebaModificar() {
        Connection conexionModificar = BibliotecaManager.iniciarConexion();
        EjemplarDao daoModificar = new EjemplarDao(conexionModificar);

        Ejemplar ejemplarModificar = new Ejemplar("978-0307476463", "2100", "Sala B", 4, 5, 6);
        daoModificar.modificar(ejemplarModificar);
        System.out.println("Ejemplar modificado correctamente.");
    }
    
    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        EjemplarDao daoObtener = new EjemplarDao(conexionObtener);

        List<Ejemplar> ejemplaresObtenidos = daoObtener.obtener("978-0307476463", "2100");
        if (!ejemplaresObtenidos.isEmpty()) {
            System.out.println("Ejemplares encontrados:");
            for (Ejemplar ejemplar : ejemplaresObtenidos) {
                System.out.println("ISBN: " + ejemplar.getIsbn());
                System.out.println("Nro Ejemplar: " + ejemplar.getNroEjemplar());
                System.out.println("Sala: " + ejemplar.getSala());
                System.out.println("Nro Pasillo: " + ejemplar.getNroPasillo());
                System.out.println("Estante: " + ejemplar.getEstante());
                System.out.println("Nro Cajón: " + ejemplar.getNroCajon());
                System.out.println();
            }
        } else {
            System.out.println("No se encontraron ejemplares con ese ISBN.");
        }
    }
    
    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        EjemplarDao daoEliminar = new EjemplarDao(conexionEliminar);

        Ejemplar ejemplarEliminar = new Ejemplar("978-0307476463", "2100", "", 0, 0, 0);
        daoEliminar.eliminar(ejemplarEliminar);
        System.out.println("Ejemplar eliminado correctamente.");
    }
    
    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        EjemplarDao daoObtenerTodos = new EjemplarDao(conexionObtenerTodos);

        List<Ejemplar> ejemplares = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de ejemplares:");
        for (Ejemplar ejemplar : ejemplares) {
            System.out.println("ISBN: " + ejemplar.getIsbn());
            System.out.println("Nro Ejemplar: " + ejemplar.getNroEjemplar());
            System.out.println("Sala: " + ejemplar.getSala());
            System.out.println("Nro Pasillo: " + ejemplar.getNroPasillo());
            System.out.println("Estante: " + ejemplar.getEstante());
            System.out.println("Nro Cajón: " + ejemplar.getNroCajon());
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
}

