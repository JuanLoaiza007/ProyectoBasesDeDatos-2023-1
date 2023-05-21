package Controlador;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorUbicaciones.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import DAO.UbicacionDAO;
import Modelo.*;
import Paneles.*;
import Objetos.*;
import DAO.postgres.UbicacionDAOImpl;
import java.awt.Point;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

public class SubcontroladorUbicaciones {
    
    protected SubmodeloUbicaciones submodelo = new SubmodeloUbicaciones();
    protected PanelUbicaciones panel = new PanelUbicaciones();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    protected int selectedId;
    protected int selectedRow;
    protected Ubicacion ubicacionSeleccionada = null;
    
    public SubcontroladorUbicaciones(SubmodeloUbicaciones submodelo, PanelUbicaciones panel){
        this.submodelo = submodelo;
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
        panel.addListenerBuscarUbicacion(oyenteBuscarUbicacion);
        panel.addListenerNuevo(oyenteNuevo);
        panel.addListenerEditar(oyenteEditar);
        panel.addListenerBorrar(oyenteBorrar);
        panel.addListenerGuardar(oyenteGuardar);
        panel.addListenerCancelar(oyenteCancelar);
        panel.addListenerFilasTabla(oyenteFilasTabla);
        
        
        cargarUbicaciones();
        panel.modoPasivo();
        
    }
    
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    public void cargarUbicaciones(){
        List<Ubicacion> ubicaciones;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        UbicacionDAO dao = new UbicacionDAOImpl(conexion);    
        ubicaciones = dao.obtenerTodos();
        
        BibliotecaManager.detenerConexion(conexion);
        
        for(Ubicacion ubicacionActual: ubicaciones){
            String id_ubicacion = ubicacionActual.getIdUbicacion();
            String nombre_sala = ubicacionActual.getNombreSala();
            String nro_pasillo = Integer.toString(ubicacionActual.getNroPasillo());
            String estante = Integer.toString(ubicacionActual.getEstante());
            String nro_cajon = Integer.toString(ubicacionActual.getNroCajon());
            
            panel.nuevaFilaTabla(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
        }
    }
    
    public void buscarUbicacion(){        
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        String parametro = panel.getTxtf_buscar().getText();

        UbicacionDAO dao = new UbicacionDAOImpl(conexion);

        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            panel.limpiarTabla();
            cargarUbicaciones();            
        } else {

            Ubicacion ubicacionBuscada = dao.obtener(parametro);
            
            if (ubicacionBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                panel.limpiarTabla();
                panel.modoPasivo();
            } else {
                panel.limpiarTabla();
                
                String id_ubicacion = ubicacionBuscada.getIdUbicacion();
                String nombre_sala = ubicacionBuscada.getNombreSala();
                String nro_pasillo = Integer.toString(ubicacionBuscada.getNroPasillo());
                String estante = Integer.toString(ubicacionBuscada.getEstante());
                String nro_cajon = Integer.toString(ubicacionBuscada.getNroCajon());

                panel.nuevaFilaTabla(id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon);
            }
        }
        
        panel.modoPasivo();
        panel.setTxtf_buscar("");

        BibliotecaManager.detenerConexion(conexion);
    }
    
    ActionListener oyenteMostrarPanelAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAdministrar");
        }
    };
    
    ActionListener oyenteBuscarUbicacion = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarUbicacion();
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
            panel.setId(ubicacionSeleccionada.getIdUbicacion());
            panel.setNombreSala(ubicacionSeleccionada.getNombreSala());
            panel.setNroPasillo("" + ubicacionSeleccionada.getNroPasillo());
            panel.setEstante("" + ubicacionSeleccionada.getEstante());
            panel.setNroCajon("" + ubicacionSeleccionada.getNroCajon());
            
            panel.modoEditar();
        }
    };
    
    ActionListener oyenteBorrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = "¿Seguro que deseas eliminar este registro? \n"
                    + "Esta operacion es irreversible";

            if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {
                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

                UbicacionDAO dao = new UbicacionDAOImpl(conexion);

                dao.eliminar(ubicacionSeleccionada);
                ubicacionSeleccionada = null;
                
                panel.limpiarTabla();
                cargarUbicaciones();
                panel.modoPasivo();

                BibliotecaManager.detenerConexion(conexion);
            }
        }
    };
    
    ActionListener oyenteGuardar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = panel.getTxtf_id().getText();
            String nombreSala = panel.getTxtf_nombreSala().getText();
            int nroPasillo = -1;
            int estante = -1;
            int nroCajon = -1;
       
            boolean datosValidados = false;
            try {
                nroPasillo = Integer.parseInt(panel.getTxtf_nroPasillo().getText());
                try {
                    estante = Integer.parseInt(panel.getTxtf_estante().getText());
                    try {
                        nroCajon = Integer.parseInt(panel.getTxtf_nroCajon().getText());
                        ubicacionSeleccionada = new Ubicacion(id, nombreSala, nroPasillo, estante, nroCajon);
                        
                        datosValidados = true;
                    } catch (NumberFormatException ex) {
                        AvisosEmergentes.mostrarMensaje("Nro Cajon no es valido: " + panel.getTxtf_nroCajon().getText());
                    }
                } catch (NumberFormatException ex) {
                    AvisosEmergentes.mostrarMensaje("Estante no es valido: " + panel.getTxtf_estante().getText());
                }

            } catch (NumberFormatException ex) {
                AvisosEmergentes.mostrarMensaje("Nro Pasillo no es valido: " + panel.getTxtf_nroPasillo().getText());
            }          
 
            if (datosValidados) {
                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                UbicacionDAO dao = new UbicacionDAOImpl(conexion);
                if(panel.idEsManual()){ // El id se asigna manualmente por lo que es una insercion
                   
                    dao.insertar(ubicacionSeleccionada);
                    ubicacionSeleccionada = null;

                    panel.limpiarTabla();
                    panel.limpiarCampos();
                    cargarUbicaciones();
                    panel.modoPasivo();

                    BibliotecaManager.detenerConexion(conexion);
                } else{ // El id es fijo por lo que se esta realizando una actualizacion
                    String mensaje = "¿Seguro que deseas editar la informacion de este registro? \n"
                            + "Esta operacion es irreversible";

                    if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {

                        dao.modificar(ubicacionSeleccionada);
                        ubicacionSeleccionada = null;

                        panel.limpiarTabla();
                        panel.limpiarCampos();
                        cargarUbicaciones();
                        panel.modoPasivo();

                        
                    }
                }
                
                BibliotecaManager.detenerConexion(conexion);
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
            
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRow = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                String nombreSala = table.getValueAt(table.getSelectedRow(), 1).toString();
                int nroPasillo = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
                int estante = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
                int nroCajon = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
                
                ubicacionSeleccionada = new Ubicacion(id, nombreSala, nroPasillo, estante, nroCajon);
                
                panel.limpiarCampos();
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
}
