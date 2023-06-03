package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: AutorDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Autor;
import java.util.List;

public class AutorDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        AutorDao daoInsertar = new AutorDao(conexionInsertar);

        Autor autorInsertar = new Autor("11", "1er Nombre", "2do Nombre", "1er Apellido", "2do Apellido");
        daoInsertar.insertar(autorInsertar);
        System.out.println("Autor insertado correctamente.");
    }

    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        AutorDao daoModificar = new AutorDao(conexionModificar);

        Autor autorModificar = new Autor("11", "N1er Nombre", "N2doo Nombre", "N1er Apellido", "N2do Apellido");
        daoModificar.modificar(autorModificar);
        System.out.println("Autor modificado correctamente.");
    }

    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        AutorDao daoObtener = new AutorDao(conexionObtener);

        Autor autorObtener = daoObtener.obtener("11");
        if (autorObtener != null) {
            System.out.println("Autor encontrado:");
            System.out.println("Código: " + autorObtener.getCodigoAutor());
            System.out.println("Primer Nombre: " + autorObtener.getPrimerNombre());
            System.out.println("Segundo Nombre: " + autorObtener.getSegundoNombre());
            System.out.println("Primer Apellido: " + autorObtener.getPrimerApellido());
            System.out.println("Segundo Apellido: " + autorObtener.getSegundoApellido());
        } else {
            System.out.println("No se encontró ningún autor con ese código.");
        }
    }

    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        AutorDao daoEliminar = new AutorDao(conexionEliminar);

        Autor autorEliminar = new Autor("11", "", "", "", "");
        daoEliminar.eliminar(autorEliminar);
        System.out.println("Autor eliminado correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        AutorDao daoObtenerTodos = new AutorDao(conexionObtenerTodos);

        List<Autor> autores = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de autores:");
        for (Autor autor : autores) {
            System.out.println("Código: " + autor.getCodigoAutor());
            System.out.println("Primer Nombre: " + autor.getPrimerNombre());
            System.out.println("Segundo Nombre: " + autor.getSegundoNombre());
            System.out.println("Primer Apellido: " + autor.getPrimerApellido());
            System.out.println("Segundo Apellido: " + autor.getSegundoApellido());
            System.out.println();
        }
    }
    
}
