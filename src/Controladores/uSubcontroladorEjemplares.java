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
import Dao.UsuarioDao;
import Modelos.Ejemplar;
import Modelos.Usuario;
import Paneles.AvisosEmergentes;
import Paneles.uPanelEjemplares;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;

public class uSubcontroladorEjemplares {
    
    protected uPanelEjemplares panel = new uPanelEjemplares();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Ejemplar registroSeleccionado = null;   
    protected Usuario usuarioActual = null;      
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorEjemplares(uPanelEjemplares panel){        
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelPrincipal);
        panel.addListenerBuscar(oyenteBuscar);        
        panel.addListenerPedir(oyentePedir);
        panel.addListenerFilasTabla(oyenteFilasTabla);           
        
        cargarRegistros();        
        //panel.modoPasivo();
        
    }
    
    // ------------------ METODOS ------------------    
    public void cargarModoInicial(){
        panel.limpiarTabla();
        cargarRegistros();
        //panel.modoPasivo();        
    }     
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(Ejemplar e){
        String isbn = e.getIsbn();
        String nroEjemplar = e.getNroEjemplar();
        String sala = e.getSala();
        String nroPasillo = Integer.toString(e.getNroPasillo());
        String nroEstante = Integer.toString(e.getEstante());
        String nroCajon = Integer.toString(e.getNroCajon());
        
        panel.nuevaFilaTabla(isbn, nroEjemplar, sala, nroPasillo, nroEstante, nroCajon);
    } 

        /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Ejemplar> ejemplares;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        EjemplarDao dao = new EjemplarDao(conexion);
        ejemplares = dao.obtenerTodos();
        
        for(Ejemplar ejemplarActual: ejemplares){
            cargarObjetoEnTabla(ejemplarActual);
        }
    } 
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar();
        
        EjemplarDao dao = new EjemplarDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            List<Ejemplar> ejemplaresBuscados = dao.obtener(parametro);
            
            if (ejemplaresBuscados == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                for(Ejemplar ejemplarActual: ejemplaresBuscados){
                    cargarObjetoEnTabla(ejemplarActual);
                }

            }
        }

    }      
    
    public void cargarUsuario(String idUsuario){
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        UsuarioDao dao = new UsuarioDao(conexion);
        usuarioActual = dao.obtenerPorId(idUsuario);

        if (usuarioActual == null) { // Si no se encontró una ubicacion entonces recargar la tabla
            AvisosEmergentes.mostrarMensaje("Error interno, el usuario no se ha cargado correctamente");
        }        
    }    
        
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteMostrarPanelPrincipal = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelPrincipal");
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };    
    
    ActionListener oyentePedir = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            AvisosEmergentes.mostrarMensaje("El prestamo lo realiza un empleado, acercate a uno para efectuar tu prestamo.");
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
                System.out.println("jaja tiraba esa");
            }

            if (Mouse_evt.getClickCount() == 1) {
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                String nroEjemplar = table.getValueAt(table.getSelectedRow(), 1).toString();
                String sala = table.getValueAt(table.getSelectedRow(), 2).toString();
                int nroPasillo = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
                int nroEstante = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
                int nroCajon = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
                
                registroSeleccionado = new Ejemplar(isbn, nroEjemplar, sala, nroPasillo, nroEstante, nroCajon); 
                
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
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
