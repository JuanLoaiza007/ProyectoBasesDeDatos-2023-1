package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorAutores.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.AutorDao;
import Modelos.Autor;
import Paneles.AvisosEmergentes;
import Paneles.PanelAutores;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;

public class SubcontroladorAutores {
    
    protected PanelAutores panel = new PanelAutores();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Autor registroSeleccionado = null;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorAutores(PanelAutores panel){
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
    public void cargarObjetoEnTabla(Autor e){
        String id = e.getCodigoAutor();
        String primerNombre = e.getPrimerNombre();
        String segundoNombre = e.getSegundoNombre();
        String primerApellido = e.getPrimerApellido();
        String segundoApellido = e.getSegundoApellido();
        
            
        panel.nuevaFilaTabla(id, primerNombre, segundoNombre, primerApellido, segundoApellido);
    }
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Autor> autores;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        AutorDao dao = new AutorDao(conexion);
        autores = dao.obtenerTodos();
        
        for(Autor autorActual: autores){
            cargarObjetoEnTabla(autorActual);
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        AutorDao dao = new AutorDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {

            Autor autorBuscada = dao.obtener(parametro);
            
            if (autorBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(autorBuscada);
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
            panel.setId(registroSeleccionado.getCodigoAutor());
            panel.setPrimerNombre(registroSeleccionado.getPrimerNombre());
            panel.setSegundoNombre(registroSeleccionado.getSegundoNombre());
            panel.setPrimerApellido(registroSeleccionado.getPrimerApellido());
            panel.setSegundoApellido(registroSeleccionado.getSegundoApellido());
            
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

                AutorDao dao = new AutorDao(conexion);

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
            String id = panel.getId().getText();
            String primerNombre = panel.getPrimerNombre().getText();
            String segundoNombre = panel.getSegundoNombre().getText();
            String primerApellido = panel.getPrimerApellido().getText();
            String segundoApellido = panel.getSegundoApellido().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(id, "id")){ 
                if(!txtfEstaVacio(primerNombre, "Primer Nombre")){  
                    if(!txtfEstaVacio(primerApellido, "Primer Apellido")){                           
                        if(!txtfEstaVacio(segundoApellido, "Segundo Apellido")){                           

                            camposVacios = false;
                        }
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;
            
            // Insercion o modificacion
            
            
            if (datosValidados && !camposVacios) {
                
                registroSeleccionado = new Autor(id, primerNombre, segundoNombre, primerApellido, segundoApellido);
                
                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                AutorDao dao = new AutorDao(conexion);
                
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
                String primerNombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                String segundoNombre = table.getValueAt(table.getSelectedRow(), 2).toString();
                String primerApellido = table.getValueAt(table.getSelectedRow(), 3).toString();
                String segundoApellido = table.getValueAt(table.getSelectedRow(), 3).toString();
                
                registroSeleccionado = new Autor(id, primerNombre, segundoNombre, primerApellido, segundoApellido);
                
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