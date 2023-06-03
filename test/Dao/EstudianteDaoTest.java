package Dao;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: EstudianteDaoTest.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Estudiante;
import java.sql.SQLException;
import java.util.List;

public class EstudianteDaoTest {
    
    public static void main(String[] args) {        
        UsuarioDaoTest.pruebaInsertar();
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        UsuarioDaoTest.pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        EstudianteDao daoInsertar = new EstudianteDao(conexionInsertar);

        Estudiante estudianteInsertar = new Estudiante("2100", "Ingeniería de Sistemas", "Universidad del Valle");
        try{
            daoInsertar.insertar(estudianteInsertar);
        } catch (SQLException ex){
            System.out.println(ex);
        }
        
        System.out.println("Estudiante insertado correctamente.");
    }

    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        EstudianteDao daoModificar = new EstudianteDao(conexionModificar);

        Estudiante estudianteModificar = new Estudiante("2100", "Ingeniería Electrónica", "Universidad del Valle");
        daoModificar.modificar(estudianteModificar);

        System.out.println("Estudiante modificado correctamente.");
    }

    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        EstudianteDao daoObtener = new EstudianteDao(conexionObtener);

        Estudiante estudianteObtener = daoObtener.obtener("2100");
        if (estudianteObtener != null) {
            System.out.println("Estudiante encontrado:");
            System.out.println("ID Usuario: " + estudianteObtener.getIdUsuario());
            System.out.println("Carrera: " + estudianteObtener.getCarrera());
            System.out.println("Universidad: " + estudianteObtener.getUniversidad());
        } else {
            System.out.println("No se encontró ningún estudiante con ese ID Usuario.");
        }
    }

    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        EstudianteDao daoEliminar = new EstudianteDao(conexionEliminar);

        Estudiante estudianteEliminar = new Estudiante("2100", "", "");
        daoEliminar.eliminar(estudianteEliminar);
        System.out.println("Estudiante eliminado correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        EstudianteDao daoObtenerTodos = new EstudianteDao(conexionObtenerTodos);

        List<Estudiante> estudiantes = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de estudiantes:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println("ID Usuario: " + estudiante.getIdUsuario());
            System.out.println("Carrera: " + estudiante.getCarrera());
            System.out.println("Universidad: " + estudiante.getUniversidad());
            System.out.println();
        }
    }
}
