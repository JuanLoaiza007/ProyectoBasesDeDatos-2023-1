package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.AreaConocimiento;
import Modelos.Profesor;
import java.util.List;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: ProfesorDaoTest.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class ProfesorDaoTest {
    
    public static void main(String[] args) {
        UsuarioDaoTest.pruebaInsertar();
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        UsuarioDaoTest.pruebaEliminar();
        pruebaObtenerTodos();
        pruebaObtenerAreasProfesor();
    }
    
    public static void pruebaInsertar(){
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        ProfesorDao daoInsertar = new ProfesorDao(conexionInsertar);

        Profesor profesorInsertar = new Profesor("2100", "Dr.", "Departamento A");
        try{
            daoInsertar.insertar(profesorInsertar);
            System.out.println("Profesor insertado correctamente.");
        } catch (java.sql.SQLException ex){
            System.out.println(ex);
        }
    }
    
    public static void pruebaModificar(){
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        ProfesorDao daoModificar = new ProfesorDao(conexionModificar);

        Profesor profesorModificar = new Profesor("2100", "Ph.D.", "Departamento B");
        daoModificar.modificar(profesorModificar);
        System.out.println("Profesor modificado correctamente.");
    }
    
    public static void pruebaObtener(){
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        ProfesorDao daoObtener = new ProfesorDao(conexionObtener);

        Profesor profesorObtener = daoObtener.obtener("2100");
        if (profesorObtener != null) {
            System.out.println("Profesor encontrado:");
            System.out.println("ID: " + profesorObtener.getIdUsuario());
            System.out.println("Título: " + profesorObtener.getTitulo());
            System.out.println("Dependencia: " + profesorObtener.getDependencia());
        } else {
            System.out.println("No se encontró ningún profesor con ese ID.");
        }
    }
    
    public static void pruebaEliminar(){
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        ProfesorDao daoEliminar = new ProfesorDao(conexionEliminar);

        Profesor profesorEliminar = new Profesor("2100", "", "");
        daoEliminar.eliminar(profesorEliminar);
        System.out.println("Profesor eliminado correctamente.");
    }
    
    public static void pruebaObtenerTodos(){
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        ProfesorDao daoObtenerTodos = new ProfesorDao(conexionObtenerTodos);

        List<Profesor> profesores = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de profesores:");
        for (Profesor profesor : profesores) {
            System.out.println("ID: " + profesor.getIdUsuario());
            System.out.println("Título: " + profesor.getTitulo());
            System.out.println("Dependencia: " + profesor.getDependencia());
            System.out.println();
        }
    }
    
    public static void pruebaObtenerAreasProfesor(){
        String id_usuario = "1";    
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        ProfesorDao dao = new ProfesorDao(conexion);
            
        List<AreaConocimiento> areas = dao.otenerAreasProfesor(id_usuario);
        
        System.out.println("Las areas encontradas para el profesor " + id_usuario + " son:");        
        for (AreaConocimiento areaActual : areas) {
            System.out.println(areaActual.getCodigoArea() + " " + areaActual.getNombre());
        }
        System.out.println();
    }
        
}
