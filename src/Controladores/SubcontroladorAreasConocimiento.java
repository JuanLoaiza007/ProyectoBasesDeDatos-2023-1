package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorAreasConocimiento.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.AreaConocimientoDao;
import Modelos.AreaConocimiento;
import Paneles.AvisosEmergentes;
import Paneles.PanelAreasConocimiento;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;

public class SubcontroladorAreasConocimiento {
    
    protected PanelAreasConocimiento panel = new PanelAreasConocimiento();
    
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected AreaConocimiento registroSeleccionado = null;
    
    public SubcontroladorAreasConocimiento(PanelAreasConocimiento panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
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
    public void cargarObjetoEnTabla(AreaConocimiento e){
        String id = e.getCodigoArea();
        String codigoAreaPadre = e.getCodigoAreaPadre();
        String nombre = e.getNombre();
        String descripcion = e.getDescripcion();
          
        panel.nuevaFilaTabla(id, codigoAreaPadre, nombre, descripcion);
    }
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<AreaConocimiento> areaConocimientos;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        AreaConocimientoDao dao = new AreaConocimientoDao(conexion);
        areaConocimientos = dao.obtenerTodos();
        
        for(AreaConocimiento areaConocimientoActual: areaConocimientos){
            cargarObjetoEnTabla(areaConocimientoActual);
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        AreaConocimientoDao dao = new AreaConocimientoDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {

            AreaConocimiento areaConocimientoBuscada = dao.obtener(parametro);
            
            if (areaConocimientoBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(areaConocimientoBuscada);
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
            panel.setCodigoArea(registroSeleccionado.getCodigoArea());
            panel.setNombre(registroSeleccionado.getNombre());
            panel.setCodigoAreaPadre(registroSeleccionado.getCodigoAreaPadre());
            panel.setDescripcion(registroSeleccionado.getDescripcion());
            
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

                AreaConocimientoDao dao = new AreaConocimientoDao(conexion);

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
            String id = panel.getCodigoArea().getText();
            String codigoAreaPadre = panel.getCodigoAreaPadre().getText();
            String nombre = panel.getNombre().getText();
            String descripcion = panel.getDescripcion().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(id, "Codigo Area")){ 
                if(!txtfEstaVacio(nombre, "Nombre")){  
                    if(!txtfEstaVacio(descripcion, "Descripcion")){                           
                            camposVacios = false;
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;
            
            // Insercion o modificacion
            
            registroSeleccionado = new AreaConocimiento(id, codigoAreaPadre, nombre, descripcion);
            
            if (datosValidados && !camposVacios) {
                
                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                AreaConocimientoDao dao = new AreaConocimientoDao(conexion);
                
                if(panel.idEsManual()){ // El id se asigna manualmente por lo que es una insercion

                    dao.insertar(registroSeleccionado);
                    
                    registroSeleccionado = null;
                    cargarModoInicial();

                    BibliotecaManager.detenerConexion(conexion);
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
                String codigoAreaPadre = "";
                try {
                    codigoAreaPadre = table.getValueAt(table.getSelectedRow(), 1).toString();
                } catch (java.lang.NullPointerException ex){
                    // No hace nada
                }
                String nombre = table.getValueAt(table.getSelectedRow(), 2).toString();
                String descripcion = table.getValueAt(table.getSelectedRow(), 3).toString();
                
                registroSeleccionado = new AreaConocimiento(id, codigoAreaPadre, nombre, descripcion);
                
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