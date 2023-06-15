package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorSolicitudes.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.SolicitudDao;
import Modelos.Solicitud;
import Paneles.AvisosEmergentes;
import Paneles.MiniVentana;
import Paneles.PanelSolicitudes;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JTable;

public final class SubcontroladorSolicitudes { 
    
    protected PanelSolicitudes panel = new PanelSolicitudes();
    
    protected String idInterno;
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Solicitud registroSeleccionado = null;       
    
    public SubcontroladorSolicitudes(PanelSolicitudes panel){        
        this.panel = panel;
        
        panel.addListenerBuscar(oyenteBuscar);
        panel.addListenerAprobar(oyenteAprobar);
        panel.addListenerNegar(oyenteNegar);
        panel.addListenerFilasTabla(oyenteFilasTabla);         
        
        cargarRegistros();
        panel.modoPasivo();         
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
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
    public void cargarObjetoEnTabla(Solicitud e){
        String nroConsecutivo = e.getNroConsecutivoSolicitud();
        String idUsuario = e.getIdUsuario();
        String idEmpleado = e.getIdEmpleado();
        String isbn = e.getIsbn();
        String titulo = e.getTitulo();
        String descripcion = e.getDescripcion();
        Timestamp fecha = e.getFecha();
        
        panel.nuevaFilaTabla(nroConsecutivo, idUsuario, idEmpleado, isbn, titulo, descripcion, fecha);      
        
        
    }      
    
    /**
     * Carga los registros de solicitudes que no han sido atendidos
     */
    public void cargarRegistros(){
        List<Solicitud> solicitudes;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        SolicitudDao dao = new SolicitudDao(conexion);
        solicitudes = dao.obtenerTodos();
        
        for(Solicitud solicitudActual: solicitudes){
            if(solicitudActual.getIdEmpleado().isEmpty())
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
        
        SolicitudDao dao = new SolicitudDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Solicitud solicitudBuscada = dao.obtenerPorNroConsecutivo(parametro);
            
            if (solicitudBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();
                cargarObjetoEnTabla(solicitudBuscada);
            }
        }
    }    
    
    
    // ------------------ LISTENERS ------------------
    /**
     * Envia un mensaje a la instancia superior (Vista) para que cargue el panel de administrar
     */      
    ActionListener oyenteBuscar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscar();
        }
    };

    ActionListener oyenteAprobar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = "¿Seguro que desea aprobar esta solicitud? \n"
                    + "(Advertencia) La solicitud no se mostrará más, recuerde \n"
                    + " anotar los datos para crear el nuevo registro.";
            try{
                if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {
                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

                    SolicitudDao dao = new SolicitudDao(conexion);
                    registroSeleccionado.setIdEmpleado(idInterno);                    
                    dao.modificar(registroSeleccionado);

                    cargarModoInicial();
                    
                    AvisosEmergentes.mostrarMensaje("Solicitud aprobada con exito");
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    };    
    
    ActionListener oyenteNegar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = "¿Seguro que desea negar esta solicitud? \n"
                    + "La solicitud será eliminada.";
            try{
                if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {
                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

                    SolicitudDao dao = new SolicitudDao(conexion);                                      
                    dao.eliminar(registroSeleccionado);

                    cargarModoInicial();
                    
                    AvisosEmergentes.mostrarMensaje("La solicitud ha sido denegada.");
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
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
                String nroConsecutivo = table.getValueAt(table.getSelectedRow(), 0).toString();
                String idUsuario = table.getValueAt(table.getSelectedRow(), 1).toString();
                String idEmpleado = table.getValueAt(table.getSelectedRow(), 2).toString();
                String isbn = table.getValueAt(table.getSelectedRow(), 3).toString();
                String titulo = table.getValueAt(table.getSelectedRow(), 4).toString();  
                String descripcion = table.getValueAt(table.getSelectedRow(), 5).toString();    
                Timestamp fecha = (Timestamp) table.getValueAt(table.getSelectedRow(), 6);                    
                
                registroSeleccionado = new  Solicitud(nroConsecutivo, idUsuario, idEmpleado, isbn, titulo, descripcion, fecha);
                
                panel.modoRegistroTablaSeleccionado();
            }
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

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }
    
}

