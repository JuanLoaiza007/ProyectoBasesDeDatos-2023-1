package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EditorialDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Editorial;
import java.util.List;

public class EditorialDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        EditorialDao daoInsertar = new EditorialDao(conexionInsertar);

        Editorial editorialInsertar = new Editorial("11", "Editorial Insertar", "www.editorialinsertar.com", "País Insertar");
        daoInsertar.insertar(editorialInsertar);
        System.out.println("Editorial insertada correctamente.");
    }

    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        EditorialDao daoModificar = new EditorialDao(conexionModificar);

        Editorial editorialModificar = new Editorial("11", "Editorial Modificada", "www.editorialmodificada.com", "País Modificado");
        daoModificar.modificar(editorialModificar);
        System.out.println("Editorial modificada correctamente.");
    }

    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        EditorialDao daoObtener = new EditorialDao(conexionObtener);

        Editorial editorialObtener = daoObtener.obtener("11");
        if (editorialObtener != null) {
            System.out.println("Editorial encontrada:");
            System.out.println("Código: " + editorialObtener.getCodigoEditorial());
            System.out.println("Nombre: " + editorialObtener.getNombre());
            System.out.println("Página web: " + editorialObtener.getPaginaWeb());
            System.out.println("País de origen: " + editorialObtener.getPaisOrigen());
        } else {
            System.out.println("No se encontró ninguna editorial con ese código.");
        }
    }

    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        EditorialDao daoEliminar = new EditorialDao(conexionEliminar);

        Editorial editorialEliminar = new Editorial("11", "", "", "");
        daoEliminar.eliminar(editorialEliminar);
        System.out.println("Editorial eliminada correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        EditorialDao daoObtenerTodos = new EditorialDao(conexionObtenerTodos);

        List<Editorial> editoriales = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de editoriales:");
        for (Editorial editorial : editoriales) {
            System.out.println("Código: " + editorial.getCodigoEditorial());
            System.out.println("Nombre: " + editorial.getNombre());
            System.out.println("Página web: " + editorial.getPaginaWeb());
            System.out.println("País de origen: " + editorial.getPaisOrigen());
            System.out.println();
        }
    }

}
