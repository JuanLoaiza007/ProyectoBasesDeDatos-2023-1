package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorCambiarContrasena.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.EmpleadoDao;
import Dao.UsuarioDao;
import Modelos.Empleado;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.PanelCambiarContrasena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SubcontroladorCambiarContrasena {
   
    protected PanelCambiarContrasena panel = new PanelCambiarContrasena();
    protected ComunicadorClases decirAInstanciaSuperior;
    protected Usuario usuarioActual = null;
    protected Empleado empleadoActual = null;
    
    /**
     * Constructor del subcontrolador 
     * @param panel Un JPanel
     */
    public SubcontroladorCambiarContrasena(PanelCambiarContrasena panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteVolver);
        panel.addListenerCambiar(oyenteCambiar);
        
    }
    
    
    // ------------------ METODOS ------------------
    public void cargarUsuario(String idUsuario){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        UsuarioDao dao = new UsuarioDao(conexion);
        usuarioActual = dao.obtenerPorId(idUsuario);

        if (usuarioActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");
        }        
    }
    
    public void cargarEmpleado(String idEmpleado) {
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        EmpleadoDao dao = new EmpleadoDao(conexion);
        empleadoActual = dao.obtener(idEmpleado);

        if (empleadoActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el empleado no se ha cargado correctamente");
        } 
    }
    
    public void cambiarContrasenaEmpleado(String contrasenaNueva){
        try {
            // Cambio de contraseña para empleado
            empleadoActual.setPassword(contrasenaNueva);

            java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
            Dao.EmpleadoDao dao = new Dao.EmpleadoDao(conexion);

            dao.modificar(empleadoActual);
            
            actualizacionExitosa();
        } catch (SQLException ex) {
            System.out.println("\n\nExcepcion al intentar cambiar la contraseña del Empleado con modificar:\n " + ex);
        }
    }
    
    public void cambiarContrasenaUsuario(String contrasenaNueva){
        try {
            // Cambio de contraseña para empleado
            usuarioActual.setPassword(contrasenaNueva);

            java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
            Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion);
            dao.modificar(usuarioActual);
            
            actualizacionExitosa();
               
        } catch (SQLException ex) {
            System.out.println("\n\nExcepcion al intentar cambiar la contraseña del Usuario con modificar:\n " + ex);
        }
    }
    
    public void actualizacionExitosa(){
        AvisosEmergentes.mostrarMensaje("¡Contraseña actualizada! Será redirigido(a) al inicio de sesión.");         
        decirAInstanciaSuperior.mensaje("SolicitudMostrarVistaLogin");
    }
    
    
    // ------------------ METODOS AUXILIARES DE SEGURIDAD ------------------
    public boolean esEmpleado(){
        return empleadoActual != null;
    }
    
    public static boolean campoEstaVacio(String contenido, String nombreCampo) {
        if (contenido.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de " + nombreCampo + " esta vacio. Digite algo.");
            return true;
        }
        return false;
    }
    
    public static boolean esContrasenaValida(String contrasena) {
        // Comprobación de que hay algo
        if (contrasena == null || contrasena.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de Contraseña esta vacio. Por favor ingrese una contraseña.");
            return false;
        }

        // Comprobación de longitud
        if (contrasena.length() < 8 || contrasena.length() > 30) {
            AvisosEmergentes.mostrarError("La contraseña debe tener entre 8 y 30 caracteres.");
            return false;
        }

        return true;
    }
    
    public static boolean coincidePasswordUsuario(String password, String correo){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion);
        
        boolean respuesta = dao.correspondePasswordEmail(password, correo);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("Contraseña actual incorrecta. Vuelve a intentarlo.");
        
        return respuesta;
    }
    
    public static boolean coincidePasswordEmpleado(String password, String id){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.EmpleadoDao dao = new Dao.EmpleadoDao(conexion);
        
        boolean respuesta = dao.correspondePasswordId(password, id);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("Contraseña actual incorrecta. Vuelve a intentarlo.");
        
        return respuesta;
    }
    
    public static boolean coincidenPasswordsNuevas(String nueva1, String nueva2){
        boolean respuesta = nueva1.equals(nueva2);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("Las contraseñas nuevas no coinciden. \n"
                                        + "Asegúrese de que haya ingresado la misma contraseña \n"
                                        + "en ambos campos de nueva contraseña. ");
            
        return respuesta;
    }
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteVolver = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(esEmpleado()){
                decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAvanzado" );
            } else
                decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelAvanzado" );
        }
    };
    
    ActionListener oyenteCambiar = new ActionListener(){
        @Override
        
        public void actionPerformed(ActionEvent e) {
            String contrasenaAnterior = panel.getPass_anterior();
            String contrasenaNueva = panel.getPass_nueva();
            String contrasenaNueva2 = panel.getPass_nueva2();
            
            boolean datosValidados = !campoEstaVacio(contrasenaAnterior, "Contraseña actual") 
                    && !campoEstaVacio(contrasenaNueva, "Nueva contraseña") 
                    && !campoEstaVacio(contrasenaNueva, "Confirmacion de la nueva contraseña");
            
            boolean coincidePasswordAnterior = false;
            boolean passwordFuerte = false;
            boolean coincidenPasswordsNuevas = false;
            
            if (datosValidados) { // Validará que la contraseña anterior corresponde
                coincidePasswordAnterior = esEmpleado()
                        ? coincidePasswordEmpleado(contrasenaAnterior, empleadoActual.getIdEmpleado()) // Si es empleado
                        : coincidePasswordUsuario(contrasenaAnterior, usuarioActual.getEmail()); // Si es admin

                if (coincidePasswordAnterior){ // Validará que la nueva contraseña es una buena contraseña
                    passwordFuerte = esContrasenaValida(contrasenaNueva);
                    
                    if (passwordFuerte) // Validará que el usuario digitó bien la confirmacion para la nueva contraseña
                        coincidenPasswordsNuevas = coincidenPasswordsNuevas(contrasenaNueva, contrasenaNueva2);
                }
            }
            
            if(coincidenPasswordsNuevas){ // Verificada la condicion más profunda entonces se autoriza el cambio
                if(esEmpleado()){ 
                    cambiarContrasenaEmpleado(contrasenaNueva);
                }
                else { // Cambio de contraseña para usuario
                    cambiarContrasenaUsuario(contrasenaNueva);
                }
            }
        }
    };
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
