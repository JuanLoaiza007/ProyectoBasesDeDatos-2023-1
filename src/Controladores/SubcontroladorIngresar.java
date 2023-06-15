package Controladores;

import BasesDeDatos.BibliotecaManager;
import Paneles.AvisosEmergentes;
import Paneles.PanelIngresar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Bases de datos 750006C-01
 Proyecto de curso
 Profesor: Oswaldo Solarte
 
 Archivo: SubcontroladorIngresar.java
 Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */


public class SubcontroladorIngresar {
    
    protected PanelIngresar panel = new PanelIngresar();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorIngresar(PanelIngresar panel){        
        this.panel = panel;
        
        panel.addActionIngresar(oyenteIngresar);
        panel.addActionRegistrarse(oyenteRegistrarse);
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }

    /**
     * Esta funcion permite conocer si un String es un correoelectronico valido.
     * @param correo El String que queremos evaluar.
     * @return true, si es un correo electrónico; false, si no es un correo electrónico.
     */
    public static boolean esCorreoValido(String correo) {        
        // Comprobación de que hay algo
        if (correo == null || correo.isEmpty()) {
            return false;
        }

        // Fijamos el indice de la arroa y el punto
        int arrobaIndex = correo.indexOf('@');
        int puntoIndex = correo.lastIndexOf('.');
        
        // Comprobamos que solo hay una arroa
        int segundaArrobaIndex = correo.indexOf('@', arrobaIndex + 1);
        if (segundaArrobaIndex != -1) {
            return false;
        }

        if (arrobaIndex < 1 || puntoIndex < arrobaIndex || puntoIndex == correo.length() - 1) {
            return false;
        }

        String dominio = correo.substring(puntoIndex + 1);
        if(!dominio.equals("com") && !dominio.equals("co")){            
            return false;
        }
     
        return true;
    }
    
    public static boolean txtfEstaVacio(String contenido, String nombreCampo) {
        if (contenido.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de " + nombreCampo + " esta vacio. Por favor, ingrese algún valor.");
            return true;
        }
        return false;
    }
    
    public static String getIdUsuario(String correo){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion);
        
        String idInterno = dao.obtenerIdPorCorreo(correo);
            
        return idInterno;
    }
    
    public static boolean existeCorreo(String correo){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion);
        
        boolean respuesta = dao.existeCorreo(correo);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("No existe ningun usuario registrado con este correo.");
        else
            System.out.println("Si existe un correo");
            
        return respuesta;
    }
    
    public static boolean coincidePasswordCorreo(String password, String correo){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion);
        
        boolean respuesta = dao.correspondePasswordEmail(password, correo);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("Contraseña incorrecta. Vuelve a intentarlo.");
        
        return respuesta;
    }
    
    public static boolean existeIdEmpleado(String id){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.EmpleadoDao dao = new Dao.EmpleadoDao(conexion);
        
        boolean respuesta = dao.existeId(id);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("No existe ningun empleado registrado con este nombre.");
        
        return respuesta;
    }
    
    public static boolean coincidePasswordId(String password, String id){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        Dao.EmpleadoDao dao = new Dao.EmpleadoDao(conexion);
        
        boolean respuesta = dao.correspondePasswordId(password, id);
        
        if(!respuesta)
            AvisosEmergentes.mostrarError("Contraseña incorrecta. Vuelve a intentarlo.");
        
        return respuesta;
    }
    
    ActionListener oyenteIngresar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String correo = panel.getTxtf_email().toLowerCase();
            String password = panel.getPassF_password();
    
            if (!txtfEstaVacio(correo, "Correo") && !txtfEstaVacio(password, "Contraseña")) {

                if (esCorreoValido(correo)) {
                    if (existeCorreo(correo) && coincidePasswordCorreo(password, correo))
                        decirAInstanciaSuperior.mensaje("SolicitudCambioVistaDashboardUsuario @" + getIdUsuario(correo));
                } else if (existeIdEmpleado(correo) && coincidePasswordId(password, correo)) {
                    decirAInstanciaSuperior.mensaje("SolicitudCambioVistaDashboardEmpleado @" + correo);
                }
            }
        }
    };
    
    ActionListener oyenteRegistrarse = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            decirAInstanciaSuperior.mensaje("SolicitudCambioPanelRegistrarse");
        }
    };
}
