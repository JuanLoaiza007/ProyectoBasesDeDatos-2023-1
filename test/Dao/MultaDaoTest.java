package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Multa;
import java.sql.*;
import java.util.List;

public class MultaDaoTest {
    
    private static Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            
    public static void main(String[] args) {
        
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }
    
    public static void pruebaInsertar() {
        Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        MultaDao daoInsertar = new MultaDao(conexionInsertar);

        Multa multaInsertar = new Multa("1", 1, fechaActual, 100, "Descripción de la multa");
        daoInsertar.insertar(multaInsertar);
        System.out.println("Multa insertada correctamente.");
    }

    public static void pruebaModificar() {
        Connection conexionModificar = BibliotecaManager.iniciarConexion();
        MultaDao daoModificar = new MultaDao(conexionModificar);

        Multa multaModificar = new Multa("1", 1, fechaActual, 200, "Nueva descripción de la multa");
        daoModificar.modificar(multaModificar);
        System.out.println("Multa modificada correctamente.");
    }

    public static void pruebaObtener() {
        Connection conexionObtener = BibliotecaManager.iniciarConexion();
        MultaDao daoObtener = new MultaDao(conexionObtener);

        List<Multa> multas = daoObtener.obtenerPorUsuario("1");
        System.out.println("Lista de multas:");
        for (Multa multa : multas) {
            System.out.println("ID Usuario: " + multa.getIdUsuario());
            System.out.println("Nro Consecutivo Prestamo: " + multa.getNroConsecutivoPrestamo());
            System.out.println("Fecha: " + multa.getFecha());
            System.out.println("Valor: " + multa.getValor());
            System.out.println("Descripción: " + multa.getDescripcion());
            System.out.println();
        }
    }

    public static void pruebaEliminar() {
        Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        MultaDao daoEliminar = new MultaDao(conexionEliminar);

        Multa multaEliminar = new Multa("1", 1, fechaActual, 0, "");
        daoEliminar.eliminar(multaEliminar);
        System.out.println("Multa eliminada correctamente.");
    }

    public static void pruebaObtenerTodos() {
        Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        MultaDao daoObtenerTodos = new MultaDao(conexionObtenerTodos);

        List<Multa> multas = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de multas:");
        for (Multa multa : multas) {
            System.out.println("ID Usuario: " + multa.getIdUsuario());
            System.out.println("Nro Consecutivo Prestamo: " + multa.getNroConsecutivoPrestamo());
            System.out.println("Fecha: " + multa.getFecha());
            System.out.println("Valor: " + multa.getValor());
            System.out.println("Descripción: " + multa.getDescripcion());
            System.out.println();
        }
    }
}
