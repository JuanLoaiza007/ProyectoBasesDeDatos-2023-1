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
import Dao.LibroDao;
import Modelos.Libro;
import Paneles.AvisosEmergentes;
import Paneles.MiniVentana;
import Paneles.PanelLibros;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public class SubcontroladorLibros {
    
    protected PanelLibros panel = new PanelLibros();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Libro registroSeleccionado = null;    
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorLibros(PanelLibros panel){        
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
    public void cargarObjetoEnTabla(Libro e){
        String isbn = e.getIsbn();
        String codigoArea = e.getCodigoArea();
        String codigoEditorial = e.getCodigoEditorial();
        String titulo = e.getTitulo();
        String anioPublicacion = e.getAnioPublicacion();
        String nroPaginas = Integer.toString(e.getNroPaginas());
        
        panel.nuevaFilaTabla(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, nroPaginas);
    }
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Libro> libros;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        LibroDao dao = new LibroDao(conexion);
        libros = dao.obtenerTodos();
        
        for(Libro libroActual: libros){
            cargarObjetoEnTabla(libroActual);
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        LibroDao dao = new LibroDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Libro libroBuscado = dao.obtener(parametro);
            
            if (libroBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(libroBuscado);
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
            panel.setISBN(registroSeleccionado.getIsbn());
            panel.setCodigoArea(registroSeleccionado.getCodigoArea());
            panel.setCodigoEditorial(registroSeleccionado.getCodigoEditorial());            
            panel.setTitulo(registroSeleccionado.getTitulo());
            panel.setAnioPublicacion(registroSeleccionado.getAnioPublicacion());
            panel.setNroPaginas(Integer.toString(registroSeleccionado.getNroPaginas()));
            
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

                    LibroDao dao = new LibroDao(conexion);

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
            String codigoArea = panel.getCodigoArea().getText();
            String codigoEditorial = panel.getCodigoEditorial().getText();
            String titulo = panel.getTitulo().getText();
            String anioPublicacion = panel.getAnioPublicacion().getText();
            String nroPaginas = panel.getNroPaginas().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(isbn, "ISBN")){ 
                if(!txtfEstaVacio(codigoArea, "Codigo Area")){  
                    if(!txtfEstaVacio(codigoEditorial, "Codigo Editorial")){                           
                        if(!txtfEstaVacio(titulo, "Titulo")){    
                            if(!txtfEstaVacio(anioPublicacion, "Año de publicación")){
                                if(txtfTieneNumero(anioPublicacion, "Año de publicación")){
                                    if(txtfTieneNumero(nroPaginas, "Número de paginas")){
                                        camposVacios = false;                           
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
            
            registroSeleccionado = new Libro(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, Integer.parseInt(nroPaginas));
            
            try{
                if (datosValidados && !camposVacios) {

                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    LibroDao dao = new LibroDao(conexion);

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
                if(ex.getMessage().contains("duplicate key value violates unique constraint")){
                    AvisosEmergentes.mostrarMensaje("Ya hay un libro registrado con ese ISBN");
                } else if(ex.getMessage().contains("violates foreign key constraint")){
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
                String codigoArea = table.getValueAt(table.getSelectedRow(), 1).toString();
                String codigoEditorial = table.getValueAt(table.getSelectedRow(), 2).toString();
                String titulo = table.getValueAt(table.getSelectedRow(), 3).toString();
                String anioPublicacion = table.getValueAt(table.getSelectedRow(), 4).toString();
                int nroPaginas = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
                
                registroSeleccionado = new Libro(isbn, codigoArea, codigoEditorial, titulo, anioPublicacion, nroPaginas);
                
                panel.limpiarCampos();
                panel.modoRegistroTablaSeleccionado();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTable tabla = (JTable) e.getSource();

            int columnaSeleccionada = tabla.getSelectedColumn();

            if (columnaSeleccionada == 0) {
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
    
    /**
     * Funcion para dar el panel a la instancia superior (Vista)
     * @return El panel que maneja el subcontrolador
     */
    public javax.swing.JPanel getPanel(){
        return panel;
    }
}
