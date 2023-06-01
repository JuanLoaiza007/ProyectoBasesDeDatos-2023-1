package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorEditoriales.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.EditorialDao;
import Modelos.Editorial;
import Paneles.AvisosEmergentes;
import Paneles.PanelEditoriales;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SubcontroladorEditoriales {
    
    protected PanelEditoriales panel = new PanelEditoriales();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Editorial registroSeleccionado = null;
    
    public SubcontroladorEditoriales(PanelEditoriales panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
//        panel.addListenerBuscar(oyenteBuscar);
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
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    /**
     * Funcion para dar el panel a la instancia superior (Vista)
     * @return El panel que maneja el subcontrolador
     */
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTabla(Editorial e){
        String id = e.getCodigoEditorial();
        String nombre = e.getNombre();
        String paginaWeb = e.getPaginaWeb();
        String paisOrigen = e.getPaisOrigen();
            
        panel.nuevaFilaTabla(id, nombre, paginaWeb, paisOrigen);
    }
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Editorial> editoriales;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        EditorialDao dao = new EditorialDao(conexion);
        editoriales = dao.obtenerTodos();
        
        for(Editorial editorialActual: editoriales){
            cargarObjetoEnTabla(editorialActual);
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscarRegistro(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        EditorialDao dao = new EditorialDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            panel.limpiarTabla();
            cargarRegistros();            
        } else {

            Editorial editorialBuscada = dao.obtener(parametro);
            
            if (editorialBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                panel.limpiarTabla();
                panel.modoPasivo();
            } else {
                panel.limpiarTabla();                
                cargarObjetoEnTabla(editorialBuscada);
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
            resultado = true;
        } catch(NumberFormatException ex){
            AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". "+ contenido + " no es un número válido, digite un numero entero mayor a cero");
        }
        return resultado;
    }
    
    public boolean txtfTieneFecha(String contenido, String nombreCampo){
        boolean resultado = false;        
        
        try{
            LocalDateTime.parse(contenido);
        } catch (DateTimeParseException ex){
            AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". "+ contenido + " no es una fecha válida");
        }
        return resultado;
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
    
    ActionListener oyenteBuscarRegistro = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarRegistro();
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
            panel.setId(registroSeleccionado.getCodigoEditorial());
            panel.setNombreEditorial(registroSeleccionado.getNombre());
            panel.setPaginaWeb(registroSeleccionado.getPaginaWeb());
            panel.setPaisOrigen(registroSeleccionado.getPaisOrigen());
            
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

                EditorialDao dao = new EditorialDao(conexion);

                dao.eliminar(registroSeleccionado);
                registroSeleccionado = null;
                
                panel.limpiarTabla();
                cargarRegistros();
                panel.modoPasivo();

                BibliotecaManager.detenerConexion(conexion);
            }
        }
    };
    
    ActionListener oyenteGuardar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Variables que guardan los campos
            String id = panel.getTxtf_id().getText();
            String nombre = panel.getTxtf_nombreEditorial().getText();
            String paginaWeb = panel.getTxtf_paginaWeb().getText();
            String paisOrigen = panel.getTxtf_paisOrigen().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            if(!txtfEstaVacio(id, "id")){ 
                if(!txtfEstaVacio(nombre, "nombre de editorial")){ 
                    if(!txtfEstaVacio(paginaWeb, "pagina web")){ 
                        if(!txtfEstaVacio(paisOrigen, "pais de origen")){                           
                            
                            camposVacios = false;
                        }
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
//            try {
//                nroPasillo = Integer.parseInt(panel.getTxtf_nroPasillo().getText());
//                try {
//                    estante = Integer.parseInt(panel.getTxtf_estante().getText());
//                    try {
//                        nroCajon = Integer.parseInt(panel.getTxtf_nroCajon().getText());
//                        ubicacionSeleccionada = new Ubicacion(id, nombreSala, nroPasillo, estante, nroCajon);
//                        
//                        datosValidados = true;
//                    } catch (NumberFormatException ex) {
//                        AvisosEmergentes.mostrarMensaje("Nro Cajon no es valido: " + panel.getTxtf_nroCajon().getText());
//                    }
//                } catch (NumberFormatException ex) {
//                    AvisosEmergentes.mostrarMensaje("Estante no es valido: " + panel.getTxtf_estante().getText());
//                }
//
//            } catch (NumberFormatException ex) {
//                AvisosEmergentes.mostrarMensaje("Nro Pasillo no es valido: " + panel.getTxtf_nroPasillo().getText());
//            }          
// 
//            if (datosValidados) {
//                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
//                UbicacionDao dao = new UbicacionDao(conexion);
//                if(panel.idEsManual()){ // El id se asigna manualmente por lo que es una insercion
//                   
//                    dao.insertar(ubicacionSeleccionada);
//                    ubicacionSeleccionada = null;
//
//                    panel.limpiarTabla();
//                    panel.limpiarCampos();
//                    cargarUbicaciones();
//                    panel.modoPasivo();
//
//                    BibliotecaManager.detenerConexion(conexion);
//                } else{ // El id es fijo por lo que se esta realizando una actualizacion
//                    String mensaje = "¿Seguro que deseas editar la informacion de este registro? \n"
//                            + "Esta operacion es irreversible";
//
//                    if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {
//
//                        dao.modificar(ubicacionSeleccionada);
//                        ubicacionSeleccionada = null;
//
//                        panel.limpiarTabla();
//                        panel.limpiarCampos();
//                        cargarUbicaciones();
//                        panel.modoPasivo();
//
//                        
//                    }
//                }
//                
//                BibliotecaManager.detenerConexion(conexion);
//            }
            
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
                String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                String paginaWeb = table.getValueAt(table.getSelectedRow(), 2).toString();
                String paisOrigen = table.getValueAt(table.getSelectedRow(), 3).toString();
                
                registroSeleccionado = new Editorial(id, nombre, paginaWeb, paisOrigen);
                
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