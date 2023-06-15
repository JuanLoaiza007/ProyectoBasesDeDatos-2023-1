package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorEjemplares.java
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
import Modelos.Ejemplar;
import Paneles.AvisosEmergentes;
import Paneles.PanelEjemplares;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public final class SubcontroladorEjemplares {
   
    protected PanelEjemplares panel = new PanelEjemplares();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Ejemplar registroSeleccionado = null;       
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorEjemplares(PanelEjemplares panel){
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
        
        String parametro = panel.getTxtf_buscar().getText();
        
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
                panel.limpiarCampos();
                for(Ejemplar ejemplarActual: ejemplaresBuscados){
                    cargarObjetoEnTabla(ejemplarActual);
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
            panel.setNroEjemplar(registroSeleccionado.getNroEjemplar());
            panel.setSala(registroSeleccionado.getSala());            
            panel.setNroPasillo(Integer.toString(registroSeleccionado.getNroPasillo()));
            panel.setNroEstante(Integer.toString(registroSeleccionado.getEstante()));
            panel.setNroCajon(Integer.toString(registroSeleccionado.getNroCajon()));
            
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

                    EjemplarDao dao = new EjemplarDao(conexion);

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
            String nroEjemplar = panel.getNroEjemplar().getText();
            String sala = panel.getSala().getText();
            String nroPasillo = panel.getNroPasillo().getText();
            String nroEstante = panel.getNroEstante().getText();
            String nroCajon = panel.getNroCajon().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(isbn, "ISBN")){ 
                if(!txtfEstaVacio(nroEjemplar, "Número ejemplar")){  
                    if(!txtfEstaVacio(sala, "Sala")){                           
                        if(!txtfEstaVacio(nroPasillo, "Número pasillo")){    
                            if(!txtfEstaVacio(nroEstante, "Número estante")){
                                if(!txtfEstaVacio(nroCajon, "Número cajón")){
                                    if(txtfTieneNumero(nroEjemplar, "Número ejemplar")){
                                        if(txtfTieneNumero(nroPasillo, "Número pasillo")){
                                            if(txtfTieneNumero(nroEstante, "Número estante")){    
                                                if(txtfTieneNumero(nroCajon, "Número cajón")){
                                                    camposVacios = false;                           
                                                }
                                            }
                                        }    
                                    }
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
            
            registroSeleccionado = new Ejemplar(isbn, nroEjemplar, sala, Integer.parseInt(nroPasillo), Integer.parseInt(nroEstante), Integer.parseInt(nroCajon));
            
            try{
                if (datosValidados && !camposVacios) {

                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    EjemplarDao dao = new EjemplarDao(conexion);

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
                System.out.println(ex.getMessage());
                if(ex.getMessage().contains("llave duplicada viola restricción de unicidad «ejemplar_nro_ejemplar_key»")){
                    AvisosEmergentes.mostrarMensaje("Ya hay un ejemplar con ese número");
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
                String nroEjemplar = table.getValueAt(table.getSelectedRow(), 1).toString();
                String sala = table.getValueAt(table.getSelectedRow(), 2).toString();
                int nroPasillo = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
                int nroEstante = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
                int nroCajon = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
                
                registroSeleccionado = new Ejemplar(isbn, nroEjemplar, sala, nroPasillo, nroEstante, nroCajon); 
                
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
