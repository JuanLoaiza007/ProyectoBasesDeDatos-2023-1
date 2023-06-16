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
import Dao.AutorDao;
import Dao.LibroDao;
import Modelos.Autor;
import Modelos.Libro;
import Paneles.AvisosEmergentes;
import Paneles.PanelLibrosAutores;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class SubcontroladorLibrosAutores {
    
    protected PanelLibrosAutores panel = new PanelLibrosAutores();
    protected ComunicadorClases decirAInstanciaSuperior;
    
    // Datos del elemento seleccionado para modificar
    protected int selectedIdLibro;
    protected int selectedRowLibro;
    protected Libro libroSeleccionado = null;    
    
    protected int selectedIdAutor;
    protected int selectedRowAutor;
    protected Autor autorSeleccionado =null;
    
    /**
     * Constructor de la clase
     * @param panel Un JPanel
     */
    public SubcontroladorLibrosAutores(PanelLibrosAutores panel){        
        this.panel = panel;
        
        panel.addListenerVolver(oyenteMostrarPanelAdministrar);   
        panel.addListenerBuscarLibro(oyenteBuscarLibro);
        panel.addListenerBuscarAutor(oyenteBuscarAutor);
        panel.addListenerBuscarAsignacion(oyenteBuscarAsignacion);
        
        panel.addListenerAsignar(oyenteAsignar);
        panel.addListenerDesasignar(oyenteDesasignar);
        
        panel.addListenerTablaLibro(oyenteFilasTablaLibro);
        panel.addListenerTablaAutor(oyenteFilasTablaAutor);
        panel.addListenerTablaAsignaciones(oyenteFilasTablaAsignaciones);
        
        cargarModoInicial();  
        panel.modoInicial();
    }
    
    
    // ------------------ METODOS ------------------
    public void cargarModoInicial(){
        cargarRegistros();
    }
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTablaLibro(Libro e){
        String isbn = e.getIsbn();
        String titulo = e.getTitulo();
        
        panel.nuevaFilaTablaLibros(isbn, titulo);
    }
    
    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     * @param e El objeto que transformará 
     */
    public void cargarObjetoEnTablaAutores(Autor e){
        String isbn = e.getCodigoAutor();
        String nombre1 = e.getPrimerNombre();
        String nombre2 = e.getSegundoNombre();
        String apellido1 = e.getPrimerApellido();
        String apellido2 = e.getSegundoApellido();
        
        String nombreCompleto = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
        
        panel.nuevaFilaTablaAutores(isbn, nombreCompleto);
    }
    
    /**
     * Transforma objetos a una fila de la tabla y lo agrega
     * @param libro El objeto libro
     * @param autor El objeto autor
     */
    public void cargarObjetoEnTablaAsignaciones(Libro libro, Autor autor){
        // DATOS DE UN LIBRO
        String isbn = libro.getIsbn();
        String titulo = libro.getTitulo();
        
        // DATOS DE UN AUTOR
        String codigoAutor = autor.getCodigoAutor();
        String nombre1 = autor.getPrimerNombre();
        String nombre2 = autor.getSegundoNombre();
        String apellido1 = autor.getPrimerApellido();
        String apellido2 = autor.getSegundoApellido();
        
        String nombreCompleto = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
        
        panel.nuevaFilaTablaAsignaciones(isbn, titulo, codigoAutor, nombreCompleto);
    }
    
    /**
     * Carga todos los registros
     */
    public void cargarRegistros(){
        cargarLibros();
        cargarAutores();
        cargarAsignaciones();
    }
    
    /**
     * Recarga todos los registros
     */
    public void recargarRegistros(){
        recargarLibros();
        recargarAutores();
        recargarAsignaciones();
    }
    
    public void cargarLibros(){
        List<Libro> registros;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        LibroDao dao = new LibroDao(conexion);
        registros = dao.obtenerTodos();
        
        for(Libro registroActual: registros){
            cargarObjetoEnTablaLibro(registroActual);
        }
    }    
    
    public void cargarAutores(){
        List<Autor> registros;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        AutorDao dao = new AutorDao(conexion);
        registros = dao.obtenerTodos();
        
        for(Autor registroActual: registros){
            cargarObjetoEnTablaAutores(registroActual);
        }
    }
    
    public void cargarAsignaciones(){
        List<Autor> autores;
        List<Libro> libros;
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        LibroDao daoLibro = new LibroDao(conexion);
        libros = daoLibro.obtenerTodos();
        
        for(Libro libroActual: libros){
            java.sql.Connection conexionAux = BibliotecaManager.iniciarConexion();
            
            LibroDao daoLibroAux = new LibroDao(conexionAux);
            
            autores = daoLibroAux.otenerAutoresLibro(libroActual.getIsbn());
            
            for(Autor autorActual: autores){
                cargarObjetoEnTablaAsignaciones(libroActual, autorActual);
            } 
        }
    }
    
    public void recargarLibros(){
        panel.limpiarCampos();
        panel.limpiarTablaLibros();
        cargarLibros();
        libroSeleccionado = null;
    }
    
    public void recargarAutores(){
        panel.limpiarCampos();
        panel.limpiarTablaAutores();        
        cargarAutores();
        autorSeleccionado = null;
    }
    
    public void recargarAsignaciones() {
        panel.limpiarCampos();
        panel.limpiarTablaAsignaciones();
        cargarAsignaciones();
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscarLibro() {
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getBuscarLibro();
        
        LibroDao dao = new LibroDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            recargarLibros();
        } else {
            Libro libroBuscado = dao.obtener(parametro);
            
            if (libroBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                recargarLibros();
            } else {
                panel.limpiarTablaLibros();   
                panel.limpiarCampos();
                cargarObjetoEnTablaLibro(libroBuscado);
            }
        }
    }
    
    /**
     * Busca un objeto a partir del parametro que se escribió en el txtf_buscar
     * del panel.
     */
    public void buscarAutor(){
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getBuscarAutor();
        
        AutorDao dao = new AutorDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            recargarAutores();    
        } else {

            Autor autorBuscada = dao.obtener(parametro);
            
            if (autorBuscada == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                recargarAutores();
            } else {
                panel.limpiarTablaAutores();   
                panel.limpiarCampos();
                cargarObjetoEnTablaAutores(autorBuscada);
            }
        }
    }
    
    public void buscarAsignacion() {
        
        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
        
        String parametro = panel.getBuscarAsignacion();
        
        LibroDao dao = new LibroDao(conexion);
        
        if (parametro.isEmpty()) { // Si no hay parametro recargar la tabla
            recargarAsignaciones();          
        } else {
            Libro libroBuscado = dao.obtener(parametro);
            
            if (libroBuscado == null) { // Si no se encontró una ubicacion entonces recargar la tabla
                AvisosEmergentes.mostrarMensaje("No se ha encontrado ningun registro con ese Id");
                
                recargarAsignaciones();
            } else {
                panel.limpiarTablaAsignaciones();   
                panel.limpiarCampos();
                
                java.sql.Connection conexionAux = BibliotecaManager.iniciarConexion();                
                LibroDao daoLibroAux = new LibroDao(conexionAux);

                List<Autor> autores = daoLibroAux.otenerAutoresLibro(libroBuscado.getIsbn());

                for (Autor autorActual : autores) {
                    cargarObjetoEnTablaAsignaciones(libroBuscado, autorActual);
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
    /**
     * Envia un mensaje a la instancia superior (Vista) para que cargue el panel de administrar
     */    
    ActionListener oyenteMostrarPanelAdministrar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            decirAInstanciaSuperior.mensaje("SolicitudMostrarPanelAdministrar");
        }
    };
    
    ActionListener oyenteBuscarLibro = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarLibro();
        }
    };    
    
    ActionListener oyenteBuscarAutor = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarAutor();
        }
    }; 
    
    ActionListener oyenteBuscarAsignacion = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarAsignacion();
        }
    }; 

    ActionListener oyenteAsignar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(libroSeleccionado == null)
                AvisosEmergentes.mostrarMensaje("Debe seleccionar un Libro.");
            else if(autorSeleccionado == null)
                AvisosEmergentes.mostrarMensaje("Debe seleccionar un Autor.");
            else{
                List<Libro> registros;

                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                LibroDao dao = new LibroDao(conexion);
                
                try {
                    dao.insertarLibroAutor(libroSeleccionado, autorSeleccionado);
                    AvisosEmergentes.mostrarMensaje("Asignacion realizada con éxito.");
                    recargarRegistros();
                } catch (SQLException ex) {
                    if(ex.getMessage().contains("duplicate key value violates unique constraint"))
                    AvisosEmergentes.mostrarMensaje("Este autor y libro ya estan asignados, no es necesario reasignar");
                } finally {
                    recargarRegistros();
                }
            }
        }
        
    };
    
    ActionListener oyenteDesasignar = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(libroSeleccionado == null)
                AvisosEmergentes.mostrarMensaje("Debe seleccionar un registro de la tabla para desasignar");
            else if(autorSeleccionado == null)
                AvisosEmergentes.mostrarMensaje("Error interno, autorSeleccionado es null");
            else{
                List<Libro> registros;

                java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                LibroDao dao = new LibroDao(conexion);
                
                try {
                    dao.eliminarLibroAutor(libroSeleccionado, autorSeleccionado);
                    AvisosEmergentes.mostrarMensaje("Desasignación éxitosa, el autor y el libro ya no tienen nada que ver.");
                    recargarRegistros();
                } catch (SQLException ex) {
                    System.out.println("Excepcion al desasignar: " + ex);
                    if(ex.getMessage().contains("duplicate key value violates unique constraint"))
                    AvisosEmergentes.mostrarMensaje("Este autor y libro ya estan asignados, no es necesario reasignar");
                } finally {
                    recargarRegistros();
                }
            }
        }
        
    };
    
    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTablaLibro = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
            
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRowLibro = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedIdLibro = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                
                libroSeleccionado = new Libro(isbn, "", "", "", "", 0);
                System.out.println("LibroCargado");
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
    
    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTablaAutor = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
            
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRowAutor = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedIdAutor = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String codigoAutor = table.getValueAt(table.getSelectedRow(), 0).toString();
                
                autorSeleccionado = new Autor(codigoAutor, "", "", "", "");
                System.out.println("AutorCargado");
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
    
    /**
     * Gestiona los clics en las filas de la tabla
     */
    MouseListener oyenteFilasTablaAsignaciones = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent Mouse_evt) {
            
            JTable table = (JTable) Mouse_evt.getSource();
            selectedRowLibro = table.getSelectedRow();
            Point point = Mouse_evt.getPoint();
            
            int row = table.rowAtPoint(point);
            
            try {
                selectedIdLibro = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                selectedIdAutor = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
            } catch (NumberFormatException e) {
                
            }

            if (Mouse_evt.getClickCount() == 1) {
                String isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
                String codigoAutor = table.getValueAt(table.getSelectedRow(), 2).toString();
                
                libroSeleccionado = new Libro(isbn, "", "", "", "", 0);
                autorSeleccionado = new Autor(codigoAutor, "", "", "", "");
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
