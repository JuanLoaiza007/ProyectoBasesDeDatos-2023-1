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
import Dao.LibroDigitalDao;
import Modelos.LibroDigital;
import Paneles.AvisosEmergentes;
import Paneles.PanelLibrosDigitales;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JTable;

public final class SubcontroladorLibrosDigitales {
    
    protected PanelLibrosDigitales panel = new PanelLibrosDigitales();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected LibroDigital registroSeleccionado = null;       
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorLibrosDigitales(PanelLibrosDigitales panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);
        panel.addListenerDescargas(oyenteMostrarPanelDescargas);
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
                panel.limpiarCampos();
                for(LibroDigital libroDigitalActual: librosDigitalesBuscados){
                    cargarObjetoEnTabla(libroDigitalActual);
                }

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
    
    
    // ------------------ LISTENERS ------------------
    ActionListener oyenteMostrarPanelAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAdministrar");
        }
    };
    
    ActionListener oyenteMostrarPanelDescargas = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelDescargas");
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
            panel.setIsbn(registroSeleccionado.getIsbn());     
            panel.setDireccionUrl(registroSeleccionado.getDireccionUrl());
            panel.setTamanioBytes(Integer.toString(registroSeleccionado.getTamanioBytes()));
            panel.setFormato(registroSeleccionado.getFormato());
            
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

                    LibroDigitalDao dao = new LibroDigitalDao(conexion);

                    dao.eliminar(registroSeleccionado);
                    registroSeleccionado = null;

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
            String isbn = panel.getIsbn().getText();
            String direccionUrl = panel.getDireccionUrl().getText();
            String tamanioBytes = panel.getTamanioBytes().getText();
            String formato = panel.getFormato().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(isbn, "ISBN")){ 
                if(!txtfEstaVacio(direccionUrl, "Dirección URL")){  
                    if(!txtfEstaVacio(tamanioBytes, "Tamaño Bytes")){                           
                        if(!txtfEstaVacio(formato, "Formato")){    
                            if(txtfTieneNumero(tamanioBytes, "Tamaño Bytes")){
                                camposVacios = false;                           
                            }
                        }    
                    }
                }    
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;
            
            // Insercion o modificacion
            
            registroSeleccionado = new LibroDigital(isbn, direccionUrl, Integer.parseInt(tamanioBytes), formato);
            
            try{
                if (datosValidados && !camposVacios) {

                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    LibroDigitalDao dao = new LibroDigitalDao(conexion);

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
            }catch(SQLException ex){
                if(ex.getMessage().contains("duplicate key value violates unique constraint")){
                    AvisosEmergentes.mostrarMensaje("Ya existe un registro con este id");
                } else if(ex.getMessage().contains("violates foreign key constraint")){
                    AvisosEmergentes.mostrarMensaje("No puedes referenciar otro registro que no existe");
                } else System.out.println(ex.getMessage());
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
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                String direccionUrl = table.getValueAt(table.getSelectedRow(), 1).toString();
                int tamanioBytes = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());                
                String formato = table.getValueAt(table.getSelectedRow(), 3).toString();
                
                registroSeleccionado = new LibroDigital(isbn, direccionUrl, tamanioBytes, formato); 
                
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
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    // Recibe el listener de la interfaz superior con la que se quiere mantener comunicacion
    public void setListener(ComunicadorClases listener) {
        this.decirAInstanciaSuperior = listener;
    }
    
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
