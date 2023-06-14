package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorRegistrarse.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Modelos.Usuario;
import Paneles.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Random;

public class SubcontroladorRegistrarse {
    
    protected PanelRegistrarse panel = new PanelRegistrarse();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    public SubcontroladorRegistrarse(PanelRegistrarse panel){
        this.panel = panel;
        
        panel.addActionRegistrar(oyenteRegistrar);
        panel.addActionVolver(oyenteVolver);
        panel.addActionBoxTipoUsuario(oyenteTipoUsuario);
    }
   
    
    // ------------------ METODOS ------------------
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }

    
    // ------------------ METODOS AUXILIARES DE SEGURIDAD ------------------
    public boolean txtfEstaVacio(String contenido, String nombreCampo) {
        if (contenido.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de " + nombreCampo + " esta vacio. Digite algo.");
            return true;
        }
        return false;
    }
    
    public static boolean esCorreoValido(String correo) {        
        // Comprobación de que hay algo
        if (correo == null || correo.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de Correo esta vacio. Por favor ingrese un correo.");
            return false;
        }

        // Fijamos el indice de la arroa y el punto
        int arrobaIndex = correo.indexOf('@');
        int puntoIndex = correo.lastIndexOf('.');
        
        // Comprobamos que solo hay una arroa
        int segundaArrobaIndex = correo.indexOf('@', arrobaIndex + 1);
        if (segundaArrobaIndex != -1) {
            AvisosEmergentes.mostrarError("Este correo no es válido, tiene más de un simbolo '@'");
            return false;
        }

        if (arrobaIndex < 1 || puntoIndex < arrobaIndex || puntoIndex == correo.length() - 1) {
            AvisosEmergentes.mostrarError("Este correo no es válido, verifique que tenga el formato 'correo_ejemplo@midominio.com'");
            return false;
        }

        String dominio = correo.substring(puntoIndex + 1);
        if(!dominio.equals("com") && !dominio.equals("co")){
            AvisosEmergentes.mostrarError("Este correo no es válido, su dominio debe finalizar en '.com' o '.co'");
            return false;
        }
     
        return true;
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
    
    /**
     * Verifica si un nombre es válido (Solo tiene letras y espacios), tiene varios contextos
     * @param nombre La cadena de texto candidata a ser nombre
     * @param contexto El contexto del nombre (es un nombre de persona, entidad, objeto u otro)
     * @return true, si es un nombre válido; false, si no es un nombre valido
     */
    public static boolean esNombreValido(String nombre, String contexto) {
        // Comprobación de que hay algo
        if (nombre == null || nombre.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de " + contexto + " esta vacio. Por favor ingrese un nombre válido.");
            return false;
        }

        // Comprobación de que todos los caracteres son letras o espacios
        for (int i = 0; i < nombre.length(); i++) {
            if (!Character.isLetter(nombre.charAt(i)) && nombre.charAt(i) != ' ') {
                AvisosEmergentes.mostrarError(contexto + " debe contener solo letras y espacios.");
                return false;
            }
        }

        // Si se llega hasta aquí, el nombre es válido
        return true;
    }
    
    public static boolean esDireccionValida(String direccion) {
        // Comprobación de que hay algo
        if (direccion == null || direccion.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de Dirección esta vacio. Por favor ingrese una dirección válida.");
            return false;
        }

        // Comprobación de que la dirección contiene al menos un número y una letra.
        boolean contieneNumero = false;
        boolean contieneLetra = false;
        for (int i = 0; i < direccion.length(); i++) {
            if (Character.isDigit(direccion.charAt(i))) {
                contieneNumero = true;
            } else if (Character.isLetter(direccion.charAt(i))) {
                contieneLetra = true;
            } 
            if (contieneNumero && contieneLetra) {
                break;
            }
        }
        if (!contieneNumero || !contieneLetra) {
            AvisosEmergentes.mostrarError("La dirección debe contener al menos un número y una letra.");
            return false;
        }

        return true;
    }
    
    public static boolean esTelefonoValido(String telefono) {
        // Comprobación de que hay algo
        if (telefono == null || telefono.isEmpty()) {
            AvisosEmergentes.mostrarError("El campo de Teléfono esta vacio. Por favor ingrese un número de teléfono.");
            return false;
        }

        // Comprobación de longitud
        if (telefono.length() != 10) {
            AvisosEmergentes.mostrarError("El número de teléfono debe tener 10 dígitos.");
            return false;
        }

        // Comprobación de que todos los caracteres son números
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                AvisosEmergentes.mostrarError("El número de teléfono debe contener solo números.");
                return false;
            }
        }

        // Comprobación de que el primer dígito es 3
        if (telefono.charAt(0) != '3') {
            AvisosEmergentes.mostrarError("El número de teléfono debe comenzar con el dígito 3.");
            return false;
        }

        return true;
    }
    
    
    // ------------------ OTROS METODOS ------------------
    public static String generarIdAleatorio() {

        Random random = new Random();
        int longitud = 8 + random.nextInt(7) ; // longitud entre 8 y 14 caracteres
        StringBuilder conjunto = new StringBuilder(); // Nota: Esto es una clase de String más eficiente para meter caracteres usando append
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*,.?";

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            conjunto.append(caracteres.charAt(indice));
        }

        return conjunto.toString();
    }
    

    // ------------------ LISTENERS ------------------
    ActionListener oyenteRegistrar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            
            // Recoleccion de atributos
            String email = panel.getTxtf_correo().getText().toLowerCase();
            String password = panel.getTxtf_contrasena().getText();
            String nombre = panel.getTxtf_nombre().getText();
            String direccion = panel.getTxtf_direccion().getText();
            String telefono = panel.getTxtf_telefono().getText();
            
            // Estableciendo un tipo de usuario
            String tipoUsuario = panel.getBox_tipoUsuario();
            
            // Estableciendo sus atributos específicos y su tipo de atributo
            String tipoAtrib1;
            if (tipoUsuario.equals("Estudiante")) 
                tipoAtrib1 = "Carrera";
            else
                tipoAtrib1 = "Titulo";
            
            String tipoAtrib2;
            if (tipoUsuario.equals("Estudiante")) 
                tipoAtrib2 = "Universidad";
            else
                tipoAtrib2 = "Dependencia";
            
            String usuarioAtrib1 = panel.getTxtf_usuarioAtrib1().getText();
            String usuarioAtrib2 = panel.getTxtf_usuarioAtrib2().getText();
            
            
            // Validacion de atributos
            boolean camposValidados = false;
            
            if (esCorreoValido(email)) {
                if (esContrasenaValida(password)) {
                    if (esNombreValido(nombre, "Nombre")) {
                        if (esDireccionValida(direccion)) {
                            if (esTelefonoValido(telefono)) {
                                if (esNombreValido(usuarioAtrib1, tipoAtrib1)) {
                                    if (esNombreValido(usuarioAtrib2, tipoAtrib2)) {
                                        camposValidados = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            
            // Insercion de registros            
            if(camposValidados){
                String idUsuario = generarIdAleatorio();
                
                java.sql.Connection conexion1 = BibliotecaManager.iniciarConexion();
                Dao.UsuarioDao dao = new Dao.UsuarioDao(conexion1);
                
                try {
                    dao.insertar(new Usuario(idUsuario, nombre, telefono, direccion, email, password));
                    
                    java.sql.Connection conexion2 = BibliotecaManager.iniciarConexion();
                    
                    if(tipoUsuario.equals("Estudiante")){ // Si el usuario es un estudiante
                        Dao.EstudianteDao estudiante = new Dao.EstudianteDao(conexion2);
                        estudiante.insertar(new Modelos.Estudiante(idUsuario, usuarioAtrib1, usuarioAtrib2));
                    } else{ // Si el usuario es un profesor
                        Dao.ProfesorDao profesor = new Dao.ProfesorDao(conexion2);                        
                        profesor.insertar(new Modelos.Profesor(idUsuario, usuarioAtrib1, usuarioAtrib2));
                    }
                    
                    AvisosEmergentes.mostrarMensaje("El registro se ha completado con éxito. Será redirigido a la pantalla de inicio de sesión");
                    decirAInstanciaSuperior.mensaje("SolicitudCambioPanelIngresar");
                } catch (SQLException ex) {
                    if(ex.getMessage().contains("duplicate key value violates unique constraint \"usuario_email_key\"")){
                        AvisosEmergentes.mostrarMensaje("Ya hay un usuario registrado con este correo electrónico.");
                    }
                    System.out.println("Error en subcontroladorRegistrarse: " + ex.getMessage());
                }  
            }
        }
    };
    
    ActionListener oyenteVolver = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            decirAInstanciaSuperior.mensaje("SolicitudCambioPanelIngresar");
        }
    };
    
    /**
     * Cambia los atributos dependiendo del tipo de usuario seleccionado (Profesor/Estudiante)
     */
    ActionListener oyenteTipoUsuario = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                JComboBox box = (JComboBox) evt.getSource();
                String tipoUsuarioSeleccionado = box.getSelectedItem().toString(); 
                
                if(tipoUsuarioSeleccionado == "Estudiante"){
                    panel.setAtributosEstudiante();
                }
                else if(tipoUsuarioSeleccionado == "Profesor"){
                    panel.setAtributosProfesor();
                }                
            } catch(NullPointerException e) {
                System.out.println(e);
            }
        } 
    };

}
