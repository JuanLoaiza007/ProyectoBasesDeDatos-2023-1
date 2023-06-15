package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorUsuarios.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.UsuarioDao;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.PanelUsuarios;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;
import java.sql.SQLException;

public class SubcontroladorUsuarios {
    
    protected PanelUsuarios panel = new PanelUsuarios();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Usuario registroSeleccionado = null;        
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorUsuarios(PanelUsuarios panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAvanzado);
        panel.addListenerBuscar(oyenteBuscar);
        panel.addListenerBorrar(oyenteBorrar);
        panel.addListenerFilasTabla(oyenteFilasTabla);         
        
        cargarRegistros();
        panel.modoPasivo();        
    }
    
    
    // ------------------ METODOS ------------------    
    public void cargarModoInicial(){
        panel.limpiarTabla();
        cargarRegistros();
        panel.modoPasivo();        
    }    
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(Usuario e){
        String idUsuario = e.getIdUsuario();
        String nombre = e.getNombre();
        String telefono = e.getTelefono();
        String direccion = e.getDireccion();
        String email = e.getEmail();
        String password = e.getPassword();
        
        panel.nuevaFilaTabla(idUsuario, nombre, telefono, direccion, email, password);
    }   
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Usuario> usuarios;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        UsuarioDao dao = new UsuarioDao(conexion);
        usuarios = dao.obtenerTodos();
        
        for(Usuario usuarioActual: usuarios){
            cargarObjetoEnTabla(usuarioActual);
        }
    }  
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        UsuarioDao dao = new UsuarioDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Usuario usuarioBuscado = dao.obtenerPorId(parametro);
            
            if (usuarioBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();
                cargarObjetoEnTabla(usuarioBuscado);
            }
        }

    }    
    
    
    // ------------------ LISTENERS ------------------
    /**
     * Envia un mensaje a la instancia superior (Vista) para que cargue el panel de administrar
     */      
    ActionListener oyenteMostrarPanelAvanzado = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAvanzado");
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
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

                    UsuarioDao dao = new UsuarioDao(conexion);

                    dao.eliminar(registroSeleccionado);
                    registroSeleccionado = null;

                    panel.limpiarTabla();
                    cargarRegistros();
                    panel.modoPasivo();

                    BibliotecaManager.detenerConexion(conexion);
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
                AvisosEmergentes.mostrarMensaje("Es posible que el usuario a borrar tenga otras dependencias "
                        + "(ej.multas) asegurese de eliminarlas antes de eliminar el usuario");
            }

        }
    }; 
    
    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTabla = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
            
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRow = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String idUsuario = table.getValueAt(table.getSelectedRow(), 0).toString();
                String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                String telefono = table.getValueAt(table.getSelectedRow(), 2).toString();
                String direccion = table.getValueAt(table.getSelectedRow(), 3).toString();
                String email = table.getValueAt(table.getSelectedRow(), 4).toString();  
                String password = table.getValueAt(table.getSelectedRow(), 5).toString();                 
                
                registroSeleccionado = new Usuario(idUsuario, nombre, telefono, direccion, email, password);
                
                panel.modoRegistroTablaSeleccionado();
            }
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
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
