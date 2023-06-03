package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: AreaConocimientoDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import java.sql.*;
import java.util.List;
import Modelos.AreaConocimiento;

public class AreaConocimientoDaoTest {

    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        AreaConocimientoDao daoInsertar = new AreaConocimientoDao(conexionInsertar);

        AreaConocimiento areaInsertar = new AreaConocimiento("123", null, "Nombre", "Descripción");
        daoInsertar.insertar(areaInsertar);
        System.out.println("Área insertada correctamente.");
    }

    public static void pruebaModificar() {
        Connection conexionModificar = BibliotecaManager.iniciarConexion();
        AreaConocimientoDao daoModificar = new AreaConocimientoDao(conexionModificar);

        AreaConocimiento areaModificar = new AreaConocimiento("123", null, "Nuevo Nombre", "Nueva Descripción");
        daoModificar.modificar(areaModificar);
        System.out.println("Área modificada correctamente.");
    }

    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        AreaConocimientoDao daoObtener = new AreaConocimientoDao(conexionObtener);

        AreaConocimiento areaObtener = daoObtener.obtener("123");
        if (areaObtener != null) {
            System.out.println("Área encontrada:");
            System.out.println("Código de Área: " + areaObtener.getCodigoArea());
            System.out.println("Código de Área Padre: " + areaObtener.getCodigoAreaPadre());
            System.out.println("Nombre: " + areaObtener.getNombre());
            System.out.println("Descripción: " + areaObtener.getDescripcion());
        } else {
            System.out.println("No se encontró ningún área con ese código.");
        }
    }

    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        AreaConocimientoDao daoEliminar = new AreaConocimientoDao(conexionEliminar);

        AreaConocimiento areaEliminar = new AreaConocimiento("123", "", "", "");
        daoEliminar.eliminar(areaEliminar);
        System.out.println("Área eliminada correctamente.");
    }

    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        AreaConocimientoDao daoObtenerTodos = new AreaConocimientoDao(conexionObtenerTodos);

        List<AreaConocimiento> areas = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de áreas:");
        for (AreaConocimiento area : areas) {
            System.out.println("Código de Área: " + area.getCodigoArea());
            System.out.println("Código de Área Padre: " + area.getCodigoAreaPadre());
            System.out.println("Nombre: " + area.getNombre());
            System.out.println("Descripción: " + area.getDescripcion());
            System.out.println();
        }
    }
}

