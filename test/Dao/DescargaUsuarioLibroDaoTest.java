package Dao;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: DescargaUsuarioLibroDaoTest.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.DescargaUsuarioLibro;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class DescargaUsuarioLibroDaoTest {

    public static void main(String[] args) {
        pruebaInsertar();
        pruebaObtenerTodos();
        pruebaObtenerPorUsuario();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        DescargaUsuarioLibroDao daoInsertar = new DescargaUsuarioLibroDao(conexionInsertar);

        DescargaUsuarioLibro descargaInsertar = new DescargaUsuarioLibro("978-0307476463", "http://falselibros.com/libro1", "1", Timestamp.valueOf(LocalDateTime.now()), "192.168.0.1");
        daoInsertar.insertar(descargaInsertar);
        System.out.println("Descarga de usuario y libro insertada correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        DescargaUsuarioLibroDao daoObtenerTodos = new DescargaUsuarioLibroDao(conexionObtenerTodos);

        List<DescargaUsuarioLibro> descargas = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de descargas de usuario y libro:");
        for (DescargaUsuarioLibro descarga : descargas) {
            System.out.println("ISBN: " + descarga.getIsbn());
            System.out.println("Dirección URL: " + descarga.getDireccionUrl());
            System.out.println("ID de Usuario: " + descarga.getIdUsuario());
            System.out.println("Fecha: " + descarga.getFecha());
            System.out.println("Dirección IP: " + descarga.getDireccionIp());
            System.out.println();
        }
    }

    public static void pruebaObtenerPorUsuario() {
        java.sql.Connection conexionObtenerPorUsuario = BibliotecaManager.iniciarConexion();
        DescargaUsuarioLibroDao daoObtenerPorUsuario = new DescargaUsuarioLibroDao(conexionObtenerPorUsuario);

        List<DescargaUsuarioLibro> descargas = daoObtenerPorUsuario.obtener("usuario1");
        System.out.println("Lista de descargas de usuario y libro para el usuario1:");
        for (DescargaUsuarioLibro descarga : descargas) {
            System.out.println("ISBN: " + descarga.getIsbn());
            System.out.println("Dirección URL: " + descarga.getDireccionUrl());
            System.out.println("ID de Usuario: " + descarga.getIdUsuario());
            System.out.println("Fecha: " + descarga.getFecha());
            System.out.println("Dirección IP: " + descarga.getDireccionIp());
            System.out.println();
        }
    }
}