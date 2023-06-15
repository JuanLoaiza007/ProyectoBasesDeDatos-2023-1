package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorDescargas.java
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
import Modelos.DescargaUsuarioLibro;
import Paneles.AvisosEmergentes;
import Paneles.MiniVentana;
import Paneles.PanelDescargas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTable;

public final class SubcontroladorDescargas { 
    
    protected PanelDescargas panel = new PanelDescargas();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected DescargaUsuarioLibro registroSeleccionado = null;       
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorDescargas(PanelDescargas panel){        
        this.panel = panel;
        
        panel.addListenerVolver(oyenteVolver);
        panel.addListenerBuscar(oyenteBuscar);
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
    public void cargarObjetoEnTabla(DescargaUsuarioLibro e){
        String isbn = e.getIsbn();
        String direccionUrl = e.getDireccionUrl();
        String idUsuario = e.getIdUsuario();
        Timestamp fecha = e.getFecha();
        String direccionIp = e.getDireccionIp();
        
        panel.nuevaFilaTabla(isbn, direccionUrl, idUsuario, fecha, direccionIp);      
        
        
    }      
    
    /**
     * Carga los registros de descargas que no han sido atendidos
     */
    public void cargarRegistros(){
        List<DescargaUsuarioLibro> descargas;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        DescargaUsuarioLibroDao dao = new DescargaUsuarioLibroDao(conexion);
        descargas = dao.obtenerTodos();
        
        for(DescargaUsuarioLibro solicitudActual: descargas){
//            if(solicitudActual.getIdEmpleado().isEmpty())
                cargarObjetoEnTabla(solicitudActual);
        }
    }      
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        DescargaUsuarioLibroDao dao = new DescargaUsuarioLibroDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            
            
            List<DescargaUsuarioLibro> descargas = dao.obtener(parametro);
            if(descargas.isEmpty()){
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ninguna descarga de\n"
                                                + "un usuario con ese id");
                panel.setTxtf_buscar("");
            } else{
                panel.limpiarTabla();
                panel.setTxtf_buscar("");
                for(DescargaUsuarioLibro descargaActual: descargas){
                cargarObjetoEnTabla(descargaActual);
            }
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
            decirAInstanciaSuperior.mensaje("DescargaUsuarioLibroMostrarPanelAvanzado");
        }
    };    
    
    ActionListener oyenteVolver = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelLibrosDigitales");
        }
    };
    
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };   
    
    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTabla = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable tabla = (JTable) e.getSource();
            
            int columnaSeleccionada = tabla.getSelectedColumn();
            
            if(columnaSeleccionada == 4 || columnaSeleccionada == 5){
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

