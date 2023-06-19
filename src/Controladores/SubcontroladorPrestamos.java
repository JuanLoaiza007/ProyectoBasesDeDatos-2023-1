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
import Dao.EjemplarDao;
import Dao.LibroDao;
import Dao.PrestamoDao;
import Dao.PrestamoEjemplarDao;
import Dao.UsuarioDao;
import Modelos.Ejemplar;
import Modelos.Libro;
import Modelos.Prestamo;
import Modelos.PrestamoEjemplar;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.PanelPrestamos;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTable;

public class SubcontroladorPrestamos {
    
    protected PanelPrestamos panel = new PanelPrestamos();
    protected String idInterno;
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Prestamo registroSeleccionado = null;    
    protected PrestamoEjemplar registroSeleccionado2 = null;    
    protected Usuario usuarioActual = null;
    protected String idUsuarioActual = null;
    protected String idUsuarioIngresado = null;    
    protected Libro libroActual = null;
    protected Ejemplar ejemplarActual = null;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorPrestamos(PanelPrestamos panel){        
        this.panel = panel;
        
//        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
        panel.addListenerBuscar(oyenteBuscar);
        panel.addListenerNuevo(oyenteNuevo);
        panel.addListenerEditar(oyenteEditar);
        panel.addListenerBorrar(oyenteBorrar);
        panel.addListenerGuardar(oyenteGuardar);
        panel.addListenerCancelar(oyenteCancelar);
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
    public void cargarObjetoEnTabla(Prestamo e){
        String nroConsecutivo = Integer.toString(e.getNroConsecutivoPrestamo());
        String idUsuario = e.getIdUsuario();
        String idEmpleado = e.getIdEmpleado();
        Timestamp fechaRealizacion = e.getFechaRealizacion();
        
        panel.nuevaFilaTabla(nroConsecutivo, idUsuario, idEmpleado, fechaRealizacion);
    }
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Prestamo> prestamos;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        PrestamoDao dao = new PrestamoDao(conexion);
        prestamos = dao.obtenerTodos();
        
        for(Prestamo prestamoActual: prestamos){
            cargarObjetoEnTabla(prestamoActual);
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        PrestamoDao dao = new PrestamoDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Prestamo prestamoBuscado = dao.obtener(parametro);
            
            if (prestamoBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(prestamoBuscado);
            }
        }

    }
    
    
    // ------------------ METODOS AUXILIARES DE SEGURIDAD ------------------
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
        usuarioActual = null;        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        UsuarioDao dao = new UsuarioDao(conexion);
        usuarioActual = dao.obtenerPorId(idUsuario);

        if (usuarioActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");
        }        
    }    
    
    public int sgteNroConsecutivo(){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        PrestamoDao dao = new PrestamoDao(conexion);        
        
        return dao.obtenerUltimoNroConsecutivo() + 1;
    }
   
    public String idUsuarioActual(){
        idUsuarioActual = null;
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
    
    public boolean existeLibro(String isbn){
        libroActual = null;
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        LibroDao dao = new LibroDao(conexion);
        libroActual = dao.obtener(isbn);
        if(libroActual == null){
            AvisosEmergentes.mostrarMensaje("El libro ingresado no existe en la biblioteca");
            return false;
        }else{
            return true;
        }
    }
    
    public boolean existeUsuario(String idUsuario){
        usuarioActual = null;
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();   
        
        UsuarioDao dao = new UsuarioDao(conexion);
        usuarioActual = dao.obtenerPorId(idUsuario);
        
        if(usuarioActual == null){
            AvisosEmergentes.mostrarMensaje("El usuario ingresado no se encuentra registrado");
            return false;
        }else{
            return true;
        }         
    }
    
    public boolean existeEjemplar(String isbn, String nroEjemplar){
        ejemplarActual = null;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();   
        
        EjemplarDao dao = new EjemplarDao(conexion);  
        ejemplarActual = dao.obtenerEjemplar(isbn, nroEjemplar);
        
        if(ejemplarActual == null){
            AvisosEmergentes.mostrarMensaje("El ejemplar ingresado no pertenece a la biblioteca");
            return false;
        }else{
            return true;
        }          
    }
    
    // ------------------ LISTENERS ------------------
    /**
     * Envia un mensaje a la instancia superior (Vista) para que cargue el panel de administrar
     */    
    ActionListener oyenteMostrarPanelAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAdministrar");
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };    
    
    ActionListener oyenteNuevo = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoInsertar();
        }
    };

    ActionListener oyenteEditar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setISBN(registroSeleccionado2.getIsbn());
            panel.setIdUsuario(registroSeleccionado.getIdUsuario());            
            panel.setNroEjemplar(registroSeleccionado2.getNroEjemplar());
            //panel.setFecha(registroSeleccionado.getCodigoEditorial());            
            
            panel.modoEditar();
        }
    };

    ActionListener oyenteBorrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = "¿Seguro que deseas eliminar este registro? \n"
                    + "Esta operacion es irreversible";
            try{
                if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {
                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

                    PrestamoDao dao = new PrestamoDao(conexion);
                    PrestamoEjemplarDao dao2 = new PrestamoEjemplarDao(conexion);

                    dao.eliminar(registroSeleccionado);
                    dao2.eliminar(registroSeleccionado2);
                    registroSeleccionado = null;
                    registroSeleccionado2 = null;

                    panel.limpiarTabla();
                    cargarRegistros();
                    panel.modoPasivo();

                    BibliotecaManager.detenerConexion(conexion);
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

        }
    }; 
    
    ActionListener oyenteGuardar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Variables que guardan los campos
            int nroConsecutivo = sgteNroConsecutivo();
            String idUsuario = panel.getIdUsuario().getText();
            String idEmpleado = idInterno;
            String isbn = panel.getISBN().getText();
            String nroEjemplar = panel.getNroEjemplar().getText();
            Timestamp fechaRealizacion = panel.getFechaRealizacion();
            Timestamp fechaDevolucion = panel.getFechaDevolucion();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(isbn, "ISBN")){ 
                if(!txtfEstaVacio(idUsuario, "idUsuario")){  
                    if(!txtfEstaVacio(nroEjemplar, "Número de ejemplar")){
                        if(existeUsuario(idUsuario)){
                            if(existeLibro(isbn)){
                                if(existeEjemplar(isbn, nroEjemplar)){
                                    camposVacios = false; 
                                }
                            }
                        }                                                  
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;
            
            // Insercion o modificacion
            
            registroSeleccionado = new Prestamo(nroConsecutivo, idUsuario, idEmpleado, fechaRealizacion);
            registroSeleccionado2 = new PrestamoEjemplar(nroConsecutivo, isbn, nroEjemplar, fechaDevolucion);
            
            try{
                if (datosValidados && !camposVacios) {

                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    java.sql.Connection conexion2 = BibliotecaManager.iniciarConexion();                    
                    PrestamoDao dao = new PrestamoDao(conexion);
                    PrestamoEjemplarDao dao2 = new PrestamoEjemplarDao(conexion2);

                    if(panel.idEsManual()){ // El id se asigna manualmente por lo que es una insercion

                        dao.insertar(registroSeleccionado);
                        
                        dao2.insertar(registroSeleccionado2);

                        registroSeleccionado = null;
                        registroSeleccionado2 = null;
                        
                        cargarModoInicial();

                        BibliotecaManager.detenerConexion(conexion);
                        BibliotecaManager.detenerConexion(conexion2);                        
                    } else{ // El id es fijo por lo que se esta realizando una actualizacion
                        String mensaje = "¿Seguro que deseas editar la informacion de este registro? \n"
                                + "Esta operacion es irreversible";

                        if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {

                            dao.modificar(registroSeleccionado);
                           

                            registroSeleccionado = null;
                            cargarModoInicial();
                        }
                    }

                    BibliotecaManager.detenerConexion(conexion);
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
    
    ActionListener oyenteCancelar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoPasivo();
            panel.limpiarCampos();
        }
    };

    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTabla = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
        /*    
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRow = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                String codigoArea = table.getValueAt(table.getSelectedRow(), 1).toString();
                String codigoEditorial = table.getValueAt(table.getSelectedRow(), 2).toString();
                String titulo = table.getValueAt(table.getSelectedRow(), 3).toString();
                String anioPublicacion = table.getValueAt(table.getSelectedRow(), 4).toString();
                int nroPaginas = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
                
                registroSeleccionado = new Libro(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, nroPaginas);
                
                panel.limpiarCampos();
                panel.modoRegistroTablaSeleccionado();
            }*/
        }

        @Override
        public void mouseClicked(MouseEvent e) {
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

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
    
    /**
     * Funcion para dar el panel a la instancia superior (Vista)
     * @return El panel que maneja el subcontrolador
     */
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
