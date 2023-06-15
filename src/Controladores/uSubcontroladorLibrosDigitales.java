package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorLibrosDigitales.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.DescargaUsuarioLibroDao;
import Dao.LibroDigitalDao;
import Modelos.DescargaUsuarioLibro;
import Modelos.LibroDigital;
import Paneles.AvisosEmergentes;
import Paneles.uPanelLibrosDigitales;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.sql.Timestamp;
import javax.swing.JTable;

public final class uSubcontroladorLibrosDigitales {
    
    protected uPanelLibrosDigitales panel = new uPanelLibrosDigitales();
    protected String idInterno;
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected LibroDigital registroSeleccionado = null;       
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public uSubcontroladorLibrosDigitales(uPanelLibrosDigitales panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteVolver);
        panel.addListenerDescargas(oyenteMostrarPanelDescargas);
        panel.addListenerBuscar(oyenteBuscar);
        panel.addListenerDescargar(oyenteDescargar);
        panel.addListenerCancelar(oyenteCancelar);
        panel.addListenerFilasTabla(oyenteFilasTabla);     
        
        cargarRegistros();
        panel.modoPasivo();
    }
    
    
    // ------------------ METODOS ------------------    
    public void cargarModoInicial(){
        registroSeleccionado = null;
        panel.limpiarTabla();
        cargarRegistros();
        panel.modoPasivo();        
    } 
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(LibroDigital e){
        String isbn = e.getIsbn();
        String direccionUrl = e.getDireccionUrl();
        String tamanioBytes = Integer.toString(e.getTamanioBytes());
        String formato = e.getFormato();
        
        panel.nuevaFilaTabla(isbn, direccionUrl, tamanioBytes, formato);
    } 
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<LibroDigital> librosDigitales;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        LibroDigitalDao dao = new LibroDigitalDao(conexion);
        librosDigitales = dao.obtenerTodos();
        
        for(LibroDigital libroDigitalActual: librosDigitales){
            cargarObjetoEnTabla(libroDigitalActual);
        }
    }   
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        LibroDigitalDao dao = new LibroDigitalDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            List<LibroDigital> librosDigitalesBuscados = dao.obtener(parametro);
            
            if (librosDigitalesBuscados == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();                   
                for(LibroDigital libroDigitalActual: librosDigitalesBuscados){
                    cargarObjetoEnTabla(libroDigitalActual);
                }

            }
        }

    }
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteVolver = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelPrincipal");
        }
    };
    
    ActionListener oyenteMostrarPanelDescargas = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostraruPanelDescargas");
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };
    
    ActionListener oyenteDescargar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = registroSeleccionado.getIsbn();
            String direccionUrl = registroSeleccionado.getDireccionUrl();
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            String direccionIp = "localhost";
            
            DescargaUsuarioLibro descargaActual = new DescargaUsuarioLibro(isbn, direccionUrl, idInterno, fechaActual, direccionIp);
            
            java.sql.Connection conexion = BibliotecaManager.iniciarConexion();            
            DescargaUsuarioLibroDao dao = new DescargaUsuarioLibroDao(conexion);
            
            dao.insertar(descargaActual);

            registroSeleccionado = null;
            cargarModoInicial();  
            
            AvisosEmergentes.mostrarMensaje("La descarga se ha efectuado con éxito.");
        }
    };    
    
    ActionListener oyenteCancelar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarModoInicial();
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
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                String direccionUrl = table.getValueAt(table.getSelectedRow(), 1).toString();
                int tamanioBytes = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());                
                String formato = table.getValueAt(table.getSelectedRow(), 3).toString();
                
                registroSeleccionado = new LibroDigital(isbn, direccionUrl, tamanioBytes, formato); 
                
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
    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    
}
