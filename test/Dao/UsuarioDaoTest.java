package Dao;

import BasesDeDatos.BibliotecaManager;
import Modelos.Usuario;
import java.util.List;

public class UsuarioDaoTest {
    
    public static void main(String[] args) {
        pruebaInsertar();
        pruebaModificar();
        pruebaObtener();
        pruebaEliminar();
        pruebaObtenerTodos();
    }

    public static void pruebaInsertar() {
        java.sql.Connection conexionInsertar = BibliotecaManager.iniciarConexion();
        UsuarioDao daoInsertar = new UsuarioDao(conexionInsertar);

        Usuario usuarioInsertar = new Usuario("2100", "Nombre", "123456789", "Dirección", "exemplo_email@example.com", "password");
        daoInsertar.insertar(usuarioInsertar);
        System.out.println("Usuario insertado correctamente.");
    }

    public static void pruebaModificar() {
        java.sql.Connection conexionModificar = BibliotecaManager.iniciarConexion();
        UsuarioDao daoModificar = new UsuarioDao(conexionModificar);

        Usuario usuarioModificar = new Usuario("2100", "Nuevo Nombre", "987654321", "Nueva Dirección", "nuevo_exemplo@example.com", "nuevo_password");
        daoModificar.modificar(usuarioModificar);
        System.out.println("Usuario modificado correctamente.");
    }

    public static void pruebaObtener() {
        java.sql.Connection conexionObtener = BibliotecaManager.iniciarConexion();
        UsuarioDao daoObtener = new UsuarioDao(conexionObtener);

        Usuario usuarioObtener = daoObtener.obtenerPorId("2100");
        if (usuarioObtener != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuarioObtener.getIdUsuario());
            System.out.println("Nombre: " + usuarioObtener.getNombre());
            System.out.println("Teléfono: " + usuarioObtener.getTelefono());
            System.out.println("Dirección: " + usuarioObtener.getDireccion());
            System.out.println("Email: " + usuarioObtener.getEmail());
            System.out.println("Password: " + usuarioObtener.getPassword());
        } else {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }
    }

    public static void pruebaEliminar() {
        java.sql.Connection conexionEliminar = BibliotecaManager.iniciarConexion();
        UsuarioDao daoEliminar = new UsuarioDao(conexionEliminar);

        daoEliminar.eliminar("2100");
        System.out.println("Usuario eliminado correctamente.");
    }

    public static void pruebaObtenerTodos() {
        java.sql.Connection conexionObtenerTodos = BibliotecaManager.iniciarConexion();
        UsuarioDao daoObtenerTodos = new UsuarioDao(conexionObtenerTodos);

        List<Usuario> usuarios = daoObtenerTodos.obtenerTodos();
        System.out.println("Lista de usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Teléfono: " + usuario.getTelefono());
            System.out.println("Dirección: " + usuario.getDireccion());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Password: " + usuario.getPassword());
            System.out.println();
        }
    }
}
