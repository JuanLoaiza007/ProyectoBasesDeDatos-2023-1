package Controladores;

/**
 * Bases de datos 750006C-01 Proyecto de curso Profesor: Oswaldo Solarte
 *
 * Archivo: SubcontroladorDevoluciones.java
 *
 * Licencia: GNU-GPL
 *
 * @version 1.0
 *
 * @author Alejandro Guerrero Cano (202179652-3743)
 * {@literal <"alejandro.cano@correounivalle.edu.co">}
 * @author Juan David Loaiza Santiago (202177570-3743)
 * {@literal <"juan.loaiza.santiago@correounivalle.edu.co">}
 * @author Juan Sebastian Muñoz Rojas (202177436-3743)
 * {@literal <"juan.munoz.rojas@correounivalle.edu.co">}
 *
 */
import BasesDeDatos.BibliotecaManager;
import Dao.DevuelveUsuarioEjemplarDao;
import Modelos.DevuelveUsuarioEjemplar;
import Modelos.GenerarMultasDao;
import Paneles.AvisosEmergentes;
import Paneles.PanelDevoluciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SubcontroladorDevoluciones {

    protected PanelDevoluciones panel = new PanelDevoluciones();

    protected DevuelveUsuarioEjemplar registroSeleccionado = null;

    /**
     * Constructor de la clase
     *
     * @param panel Un JPanel
     */
    public SubcontroladorDevoluciones(PanelDevoluciones panel) {
        this.panel = panel;

        panel.addListenerNuevo(oyenteNuevo);
        panel.addListenerGuardar(oyenteGuardar);
        panel.addListenerCancelar(oyenteCancelar);

        cargarRegistros();
        panel.modoPasivo();
    }

    // ------------------ METODOS ------------------
    public void cargarModoInicial() {
        panel.limpiarTabla();
        panel.limpiarCampos();
        cargarRegistros();
        panel.modoPasivo();
    }

    /**
     * Transforma un objeto a una fila de la tabla y lo agrega
     *
     * @param e El objeto que transformará
     */
    public void cargarObjetoEnTabla(DevuelveUsuarioEjemplar e) {
        int nroPrestamo = e.getNroConsecutivoPrestamo();
        String idUsuario = e.getIdUsuario();
        String isbn = e.getIsbn();
        String nroEjemplar = e.getNroEjemplar();
        java.sql.Timestamp fecha = e.getFecha();

        panel.nuevaFilaTabla(nroPrestamo, idUsuario, isbn, nroEjemplar, fecha);
    }

    /**
     * Carga todos los registros a la tabla
     */
    public void cargarRegistros() {
        List<DevuelveUsuarioEjemplar> libros;

        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();

        DevuelveUsuarioEjemplarDao dao = new DevuelveUsuarioEjemplarDao(conexion);

        try {
            libros = dao.obtenerTodos();

            for (DevuelveUsuarioEjemplar libroActual : libros) {
                cargarObjetoEnTabla(libroActual);
            }
        } catch (SQLException ex) {
            System.out.println("Error intentando cargar las devoluciones: " + ex);
        }
    }

    // ------------------ METODOS AUXILIARES DE SEGURIDAD ------------------
    public boolean txtfEstaVacio(String contenido, String nombreCampo) {
        boolean resultado = true;

        if (contenido.isEmpty()) {
            AvisosEmergentes.mostrarMensaje("El campo de " + nombreCampo + " esta vacio. Digite algo.");
        } else {
            resultado = false;
        }

        return resultado;
    }

    public boolean txtfTieneNumero(String contenido, String nombreCampo) {
        boolean resultado = false;

        try {
            Integer.parseInt(contenido);
            if (Integer.parseInt(contenido) >= 0) {
                resultado = true;
            } else {
                AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". " + contenido + " no es un número válido, digite un numero entero mayor a cero");
            }
        } catch (NumberFormatException ex) {
            AvisosEmergentes.mostrarMensaje("Error en el campo " + nombreCampo + ". " + contenido + " no es un número válido, digite un numero entero mayor a cero");
        }
        return resultado;
    }

    // ------------------ LISTENERS ------------------
    ActionListener oyenteNuevo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoInsertar();
        }
    };

    ActionListener oyenteGuardar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Variables que guardan los campos
            int nroPrestamo = -1;
            String idUsuario = panel.getTxtf_idUsuario();
            String isbn = panel.getTxtf_isbn();
            String nroEjemplar = panel.getTxtf_nroEjemplar();
            java.sql.Timestamp fecha = new Timestamp(System.currentTimeMillis());

            //Comprobacion de campos vacios
            boolean camposVacios = true;

            if (!txtfEstaVacio(Integer.toString(nroPrestamo), "Nro Prestamo")) {
                if (!txtfEstaVacio(idUsuario, "Id Usuario")) {
                    if (!txtfEstaVacio(nroEjemplar, "Nro Ejemplar")) {
                        camposVacios = false;
                    }
                }
            }

            boolean datosValidados = false;

            // Obtencion de campos dificiles
            if (!camposVacios) {
                if (txtfTieneNumero(panel.getTxtf_nroPrestamo(), "Nro Prestamo")) {
                    datosValidados = true;
                }
            }
            

            if (datosValidados) {
                // Insercion
                nroPrestamo = Integer.parseInt(panel.getTxtf_nroPrestamo());

                registroSeleccionado = new DevuelveUsuarioEjemplar(nroPrestamo, idUsuario, isbn, nroEjemplar, fecha);

                try {
                    if (!camposVacios) {
                        java.sql.Connection conexion = BibliotecaManager.iniciarConexion();
                        DevuelveUsuarioEjemplarDao dao = new DevuelveUsuarioEjemplarDao(conexion);

                        dao.insertar(registroSeleccionado);

                        registroSeleccionado = null;
                        cargarModoInicial();
                    }
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("duplicate key value violates unique constraint")) {
                        AvisosEmergentes.mostrarError("Ya se ha registrado la devolucion de este ejemplar para este prestamo.");
                    } else if (ex.getMessage().contains("violates foreign key constraint \"prestamo_fk\"")) {
                        AvisosEmergentes.mostrarError("El numero de prestamo no existe o no corresponde a los datos del prestamo");
                    } else if (ex.getMessage().contains("violates foreign key constraint \"usuario_fk\"")) {
                        AvisosEmergentes.mostrarError("El id de usuario no existe o no corresponde a los datos del prestamo");
                    } else if (ex.getMessage().contains("violates foreign key constraint \"ejemplar_fk\"")) {
                        AvisosEmergentes.mostrarError("El Nro Ejemplar \"" + nroEjemplar + "\" no existe o no corresponde a los datos del prestamo");
                    } else if (ex.getMessage().contains("violates foreign key constraint \"isbn_fk\"")) {
                        AvisosEmergentes.mostrarError("El Nro Ejemplar \"" + isbn + "\" no existe o no corresponde a los datos del prestamo");
                    } else if (ex.getMessage().contains("violates foreign key constraint \"prestamo_ejemplar_fk\"")) {
                        AvisosEmergentes.mostrarError("El Nro Ejemplar \"" + nroEjemplar + "\" no existe o no corresponde a los datos del prestamo");
                    } else {
                        System.out.println("Error al intentar registrar esta devolucion: " + ex.getMessage());
                    }
                }

                GenerarMultasDao.generarMultas(BibliotecaManager.iniciarConexion());
            }
        }
    };

    ActionListener oyenteCancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.modoPasivo();
            panel.limpiarCampos();
        }
    };

    /**
     * Funcion para dar el panel a la instancia superior (Vista)
     *
     * @return El panel que maneja el subcontrolador
     */
    public javax.swing.JPanel getPanel() {
        return panel;
    }
}
