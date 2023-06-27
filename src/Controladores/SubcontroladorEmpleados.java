package Controladores;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: SubcontroladorEmpleados.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import BasesDeDatos.BibliotecaManager;
import Dao.EmpleadoDao;
import Modelos.Empleado;
import Paneles.AvisosEmergentes;
import Paneles.PanelEmpleados;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JTable;

public class SubcontroladorEmpleados {
    
    protected PanelEmpleados panel = new PanelEmpleados();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedId;
    protected int selectedRow;
    protected Empleado registroSeleccionado = null;      
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorEmpleados(PanelEmpleados panel){
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAvanzado);
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
    public void cargarObjetoEnTabla(Empleado e){
        String id = e.getIdEmpleado();
        String nombre = e.getNombre();
        String cargo = e.getCargo();
        
        panel.nuevaFilaTabla(id, nombre, cargo);
    }  
    
    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros(){
        List<Empleado> empleados;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        EmpleadoDao dao = new EmpleadoDao(conexion);
        empleados = dao.obtenerTodos();
        
        for(Empleado empleadoActual: empleados){
            cargarObjetoEnTabla(empleadoActual);
        }
    }   
    
    public void buscar(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getTxtf_buscar().getText();
        
        EmpleadoDao dao = new EmpleadoDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            cargarModoInicial();            
        } else {
            Empleado empleadoBuscado = dao.obtener(parametro);
            
            if (empleadoBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                cargarModoInicial();
            } else {
                panel.limpiarTabla();   
                panel.limpiarCampos();
                cargarObjetoEnTabla(empleadoBuscado);
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
    
    
    public static String generarPasswordAleatorio() {

        Random random = new Random();
        int longitud = 4 + random.nextInt(5) ; // longitud entre 8 y 14 caracteres
        StringBuilder conjunto = new StringBuilder(); // Nota: Esto es una clase de String más eficiente para meter caracteres usando append
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            conjunto.append(caracteres.charAt(indice));
        }

        return conjunto.toString();
    }
    
    
    // ------------------ LISTENERS ------------------
    /**
     * Envia un mensaje a la instancia superior (Vista) para que cargue el panel de administrar
     */    
    
    ActionListener oyenteMostrarPanelAvanzado = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAvanzado");
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
            panel.setId(registroSeleccionado.getIdEmpleado());
            panel.setNombre(registroSeleccionado.getNombre());
            panel.setCargo(registroSeleccionado.getCargo());
            
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

                    EmpleadoDao dao = new EmpleadoDao(conexion);

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
            String id = panel.getId().getText();
            String nombre = panel.getNombre().getText();
            String cargo = panel.getCargo().getText();
            
            //Comprobacion de campos vacios
            boolean camposVacios = true;
            
            if(!txtfEstaVacio(id, "Id empleado")){ 
                if(!txtfEstaVacio(nombre, "Nombre")){  
                    if(!txtfEstaVacio(cargo, "Cargo")){ 
                        camposVacios = false;                           
                    }
                }
            }
       
            // Obtencion de campos dificiles
            boolean datosValidados = false;
            datosValidados = true;
            
            // Insercion o modificacion
            String password = generarPasswordAleatorio();
            
            try{
                if (datosValidados && !camposVacios) {
                    registroSeleccionado = new Empleado(id, nombre, cargo, password);

                    java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                    EmpleadoDao dao = new EmpleadoDao(conexion);

                    if(panel.idEsManual()){ // El id se asigna manualmente por lo que es una insercion

                        dao.insertar(registroSeleccionado);
                        AvisosEmergentes.mostrarMensaje("Su contraseña de ingreso será: " + password);

                        registroSeleccionado = null;
                        cargarModoInicial();

                        BibliotecaManager.detenerConexion(conexion);
                    } else{ // El id es fijo por lo que se esta realizando una actualizacion
                        String mensaje = "¿Seguro que deseas editar la informacion de este registro? \n"
                                + "Esta operacion es irreversible";

                        if (AvisosEmergentes.preguntarYesOrNo(mensaje)) {

                            dao.modificar(registroSeleccionado);
                            
                            AvisosEmergentes.mostrarMensaje("Su contraseña se actualizó a: " + password);

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

                if (Mouse_evt.getClickCount() == 1) {
                    String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String nombre = table.getValueAt(table.getSelectedRow(), 1).toString();
                    String cargo = table.getValueAt(table.getSelectedRow(), 2).toString();

                    registroSeleccionado = new Empleado(id, nombre, cargo, "");

                    panel.limpiarCampos();
                    panel.modoRegistroTablaSeleccionado();
                }
            } catch (NumberFormatException e) {
                
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {              
                decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelEmpleados");
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
    
    /**
     * Funcion para dar el panel a la instancia superior (Vista)
     * @return El panel que maneja el subcontrolador
     */
    public javax.swing.JPanel getPanel(){
        return panel;
    }
    
}
