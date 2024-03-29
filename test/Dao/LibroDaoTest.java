package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Autor;
import Modelos.Libro;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibroDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
        pruebaObtenerAutoresLibro();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        LibroDao daoInsertar = new LibroDao(conexionInsertar);

        Libro libroInsertar = new Libro("1234567890", "1", "1", "Título", "2023", 100);
        try {
            daoInsertar.insertar(libroInsertar);
        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Libro insertado correctamente.");
    }

    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        LibroDao daoModificar = new LibroDao(conexionModificar);

        Libro libroModificar = new Libro("1234567890", "2", "2", "Nuevo Título", "2024", 200);
        try {
            daoModificar.modificar(libroModificar);
        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Libro modificado correctamente.");
    }

    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        LibroDao daoObtener = new LibroDao(conexionObtener);

        Libro libroObtener = daoObtener.obtener("1234567890");
        if (libroObtener != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ISBN: " + libroObtener.getIsbn());
            System.out.println("Código Área: " + libroObtener.getCodigoArea());
            System.out.println("Código Editorial: " + libroObtener.getCodigoEditorial());
            System.out.println("Título: " + libroObtener.getTitulo());
            System.out.println("Año de Publicación: " + libroObtener.getAnioPublicacion());
            System.out.println("Número de Páginas: " + libroObtener.getNroPaginas());
        } else {
            System.out.println("No se encontró ningún libro con ese ISBN.");
        }
    }

    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        LibroDao daoEliminar = new LibroDao(conexionEliminar);

        Libro libroEliminar = new Libro("1234567890", "", "", "", "", 0);
        try {
            daoEliminar.eliminar(libroEliminar);
        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Libro eliminado correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        LibroDao daoObtenerTodos = new LibroDao(conexionObtenerTodos);

        List<Libro> libros = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de libros:");
        for (Libro libro : libros) {
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Código Área: " + libro.getCodigoArea());
            System.out.println("Código Editorial: " + libro.getCodigoEditorial());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Año de Publicación: " + libro.getAnioPublicacion());
            System.out.println("Número de Páginas: " + libro.getNroPaginas());
            System.out.println();
        }
    }
   
    public static void pruebaObtenerAutoresLibro() {
        String isbn = "978-0307476463";

        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        LibroDao libroDao = new LibroDao(conexion);

        List<Autor> autores = libroDao.otenerAutoresLibro(isbn);

        if (!autores.isEmpty()) {
            System.out.println("Autores del libro con ISBN " + isbn + ":");
            for (Autor autor : autores) {
                System.out.println("Nombre: " + autor.getPrimerNombre() + " " + autor.getSegundoNombre() + " " + autor.getPrimerApellido() + " " + autor.getSegundoApellido());
            }
        } else {
            System.out.println("El libro con ISBN " + isbn + " no tiene autores asociados.");
        }
    }
}
