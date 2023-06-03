package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: LibroDigitalDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.LibroDigital;
import java.sql.Connection;
import java.util.List;

public class LibroDigitalDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        LibroDigitalDao daoInsertar = new LibroDigitalDao(conexionInsertar);

        LibroDigital libroInsertar = new LibroDigital("978-0307476463", "http://milibro_ejemplo.com/libro", 1024, "PDF");
        daoInsertar.insertar(libroInsertar);
        System.out.println("Libro insertado correctamente.");
    }

    public static void pruebaModificar() {
        Connection conexionModificar = BibliotecaManager.iniciarConexion();
        LibroDigitalDao daoModificar = new LibroDigitalDao(conexionModificar);

        LibroDigital libroModificar = new LibroDigital("978-0307476463", "http://milibro_ejemplo.com/libro", 2048, "EPUB");
        daoModificar.modificar(libroModificar);
        System.out.println("Libro modificado correctamente.");
    }

    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        LibroDigitalDao daoObtener = new LibroDigitalDao(conexionObtener);

        LibroDigital libroObtener = daoObtener.obtener("978-0307476463", "http://milibro_ejemplo.com/libro");
        if (libroObtener != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ISBN: " + libroObtener.getIsbn());
            System.out.println("Dirección URL: " + libroObtener.getDireccionUrl());
            System.out.println("Tamaño en bytes: " + libroObtener.getTamanioBytes());
            System.out.println("Formato: " + libroObtener.getFormato());
        } else {
            System.out.println("No se encontró ningún libro con ese ISBN y dirección URL.");
        }
    }

    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        LibroDigitalDao daoEliminar = new LibroDigitalDao(conexionEliminar);

        LibroDigital libroEliminar = new LibroDigital("978-0307476463", "http://milibro_ejemplo.com/libro", 0, "");
        daoEliminar.eliminar(libroEliminar);
        System.out.println("Libro eliminado correctamente.");
    }

    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        LibroDigitalDao daoObtenerTodos = new LibroDigitalDao(conexionObtenerTodos);

        List<LibroDigital> libros = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de libros:");
        for (LibroDigital libro : libros) {
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Dirección URL: " + libro.getDireccionUrl());
            System.out.println("Tamaño en bytes: " + libro.getTamanioBytes());
            System.out.println("Formato: " + libro.getFormato());
            System.out.println();
        }
    }
}
