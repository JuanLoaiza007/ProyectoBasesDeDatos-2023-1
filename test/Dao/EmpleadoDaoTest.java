package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: EmpleadoDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Empleado;
import java.util.List;

public class EmpleadoDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        EmpleadoDao daoInsertar = new EmpleadoDao(conexionInsertar);

        Empleado empleadoInsertar = new Empleado("2100", "Nombre Empleado", "Cargo Empleado");
        daoInsertar.insertar(empleadoInsertar);
        System.out.println("Empleado insertado correctamente.");
    }
    
    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        EmpleadoDao daoModificar = new EmpleadoDao(conexionModificar);

        Empleado empleadoModificar = new Empleado("2100", "Nuevo Nombre Empleado", "Nuevo Cargo Empleado");
        daoModificar.modificar(empleadoModificar);
        System.out.println("Empleado modificado correctamente.");
    }
    
    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        EmpleadoDao daoObtener = new EmpleadoDao(conexionObtener);

        Empleado empleadoObtener = daoObtener.obtener("2100");
        if (empleadoObtener != null) {
            System.out.println("Empleado encontrado:");
            System.out.println("ID: " + empleadoObtener.getIdEmpleado());
            System.out.println("Nombre: " + empleadoObtener.getNombre());
            System.out.println("Cargo: " + empleadoObtener.getCargo());
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }
    
    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        EmpleadoDao daoEliminar = new EmpleadoDao(conexionEliminar);

        Empleado empleadoEliminar = new Empleado("2100", "", "");
        daoEliminar.eliminar(empleadoEliminar);
        System.out.println("Empleado eliminado correctamente.");
    }
    
    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        EmpleadoDao daoObtenerTodos = new EmpleadoDao(conexionObtenerTodos);

        List<Empleado> empleados = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de empleados:");
        for (Empleado empleado : empleados) {
            System.out.println("ID: " + empleado.getIdEmpleado());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Cargo: " + empleado.getCargo());
            System.out.println();
        }
    }
        
}

