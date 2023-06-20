package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorLibros.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.LibroDao;
import Dao.SolicitudDao;
import Dao.UsuarioDao;
import Modelos.Libro;
import Modelos.Solicitud;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.MiniVentana;
import Paneles.uPanelLibros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Timestamp;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public class uSubcontroladorLibros {
    
    protected uPanelLibros panel = new uPanelLibros();
    protected ComunicadorClases decirAInstanciaSuperior;
    protected Usuario usuarioActual = null;    
    protected Solicitud registro = null;
    protected String idUsuarioActual = null;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorLibros(uPanelLibros panel){        
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelPrincipal);
        panel.addListenerSolicitar(oyenteSolicitar);
        panel.addListenerCancelar(oyenteCancelar);
        panel.addListenerEnviar(oyenteEnviar);
        panel.addListenerBuscar(oyenteBuscar); 
        panel.addListenerFilasTabla(oyenteFilasTabla);
        
        cargarRegistros();        
        panel.modoPasivo();
        
    }
    
    // ------------------ METODOS ------------------
    public void cargarModoInicial(){
        panel.limpiarTabla();
        panel.limpiarCampos();
        cargarRegistros();
        panel.modoPasivo();        
    }    
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(Libro e){
        String isbn = e.getIsbn();
        String codigoArea = e.getCodigoArea();
        String codigoEditorial = e.getCodigoEditorial();
        String titulo = e.getTitulo();
        String anioPublicacion = e.getAnioPublicacion();
        String nroPaginas = Integer.toString(e.getNroPaginas());
        
        panel.nuevaFilaTabla(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, nroPaginas);
    }    
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Libro> libros;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        LibroDao dao = new LibroDao(conexion);
        libros = dao.obtenerTodos();
        
        for(Libro libroActual: libros){
            cargarObjetoEnTabla(libroActual);
        }
    } 
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar();
        
        LibroDao dao = new LibroDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Libro libroBuscado = dao.obtener(parametro);
            
            if (libroBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(libroBuscado);
            }
        }

    }    
    
    
    // ------------------ METODOS ------------------
    private void recargarTodo(){
        panel.limpiarCampos();
        panel.reiniciarBoxes();
    }
    
    // ------------------ METODOS AUXILIARES ------------------
    public boolean txtfEstaVacio(String contenido, String nombreCampo){
        boolean resultado = true;
        
        if(contenido.isEmpty())
            AvisosEmergentes.mostrarMensaje("El campo de " + nombreCampo + " esta vacio. Digite algo.");
        else 
            resultado = false;
        
        
        return resultado;
    }

    public boolean txtfTieneNumero(String contenido, String nombreCampo){
        boolean resultado = false;
        
        try{
            Integer.parseInt(contenido);
            if (Integer.parseInt(contenido) >=0){
                resultado = true;
            } else {
                AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". "+ contenido + " no es un número válido, digite un numero entero mayor a cero");
            }
        } catch(NumberFormatException ex){
            AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". "+ contenido + " no es un número válido, digite un numero entero mayor a cero");
        }
        return resultado;
    } 
    
    public void cargarUsuario(String idUsuario){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        UsuarioDao dao = new UsuarioDao(conexion);
        usuarioActual = dao.obtenerPorId(idUsuario);

        if (usuarioActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");
        }        
    }    
    
    public int sgteNroConsecutivo(){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        SolicitudDao dao = new SolicitudDao(conexion);        
        
        return dao.obtenerUltimoNroConsecutivo() + 1;
    }
    
    public String idUsuarioActual(){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        UsuarioDao dao = new UsuarioDao(conexion);
        idUsuarioActual = dao.obtenerIdUsuarioActual(usuarioActual);
        if (idUsuarioActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");
            return null;
        } else {
            return idUsuarioActual;        
        }   
    } 
     
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteMostrarPanelPrincipal = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelPrincipal");
        }
    };
    
    ActionListener oyenteSolicitar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoSolicitar();
        }
    };
    
    ActionListener oyenteCancelar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoPasivo();
            panel.limpiarCampos();
            panel.reiniciarBoxes();
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };    
    
    ActionListener oyenteEnviar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            int nro_consecutivo_solicitud = sgteNroConsecutivo();
            String id_usuario = idUsuarioActual();
            String id_empleado = null;            
            String isbn = panel.getTxtf_isbn();
            String titulo= panel.getTxtf_titulo();
            String descripcion= panel.getTxtf_descripcion();
            java.sql.Timestamp fecha= panel.getFecha(); 
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(isbn, "ISBN")){ 
                if(!txtfEstaVacio(titulo, "Codigo Area")){  
                    if(!txtfEstaVacio(descripcion, "Codigo Editorial")){                           
                        if(!txtfEstaVacio(titulo, "Titulo")){    
                            camposVacios = false;                           
                        }
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;   
            
            // Insercion
            
            registro = new Solicitud(nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha);
            
            try {
                if (datosValidados && !camposVacios) {
                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    SolicitudDao dao = new SolicitudDao(conexion);
                    
                    dao.insertar(registro);
                    
                    String mensaje = "Se ha realizado correctamente la solicitud, pronto obtendra respuesta";
                    
                    AvisosEmergentes.mostrarMensaje(mensaje);
                    cargarModoInicial();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                if(ex.getMessage().contains("llave duplicada viola restricción de unicidad")){
                    AvisosEmergentes.mostrarMensaje("Ya hay un libro registrado con ese ISBN");
                } else if(ex.getMessage().contains("viola la llave foránea")){
                    AvisosEmergentes.mostrarMensaje("No puedes agregar un area o una editorial que no esta registrada");
                }
            }
        }
    
    };
    
    MouseListener oyenteFilasTabla = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JTable tabla = (JTable) e.getSource();            
            selectedRow = tabla.getSelectedRow();

            int columnaSeleccionada = tabla.getSelectedColumn();

            if (columnaSeleccionada == 0) {
                int filaSeleccionada = tabla.getSelectedRow();

                // Obtener el título de la columna
                String tituloColumna = tabla.getColumnName(columnaSeleccionada);

                // Obtener el contenido del campo específico
                Object valorCampo = tabla.getValueAt(filaSeleccionada, columnaSeleccionada);
                String texto = valorCampo.toString();

                // Crear e mostrar la miniventana
                MiniVentana miniVentana = new MiniVentana(tituloColumna, texto);
                miniVentana.setVisible(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    };
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}