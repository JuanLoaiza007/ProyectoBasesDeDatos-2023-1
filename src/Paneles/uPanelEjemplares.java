package Paneles;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PanelAdministrar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class uPanelEjemplares extends javax.swing.JPanel {

    /**
     * Creacion de un modelo de tabla NO editable
     */
    private DefaultTableModel modeloTabla = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
    /** Creates new form PanelAdministrar */
    public uPanelEjemplares() {
        initComponents();
        table_principal.setModel(modeloTabla);
        configurarTabla();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_cabecera = new javax.swing.JPanel();
        panel_titulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_volver = new javax.swing.JButton();
        panel_botones = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtf_buscar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_pedir = new javax.swing.JButton();
        lbl_solicitar = new javax.swing.JLabel();
        panel_contenido = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_principal = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setMinimumSize(new java.awt.Dimension(687, 500));
        setPreferredSize(new java.awt.Dimension(687, 500));
        setLayout(new java.awt.BorderLayout());

        panel_cabecera.setBackground(new java.awt.Color(255, 255, 255));
        panel_cabecera.setLayout(new java.awt.BorderLayout());

        panel_titulo.setBackground(new java.awt.Color(204, 204, 204));
        panel_titulo.setMinimumSize(new java.awt.Dimension(687, 56));
        panel_titulo.setPreferredSize(new java.awt.Dimension(687, 56));

        jLabel1.setFont(new java.awt.Font("San Francisco Text", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ejemplares disponibles");
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 20));

        btn_volver.setBackground(new java.awt.Color(0, 102, 102));
        btn_volver.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/angle-left_16px.png"))); // NOI18N

        javax.swing.GroupLayout panel_tituloLayout = new javax.swing.GroupLayout(panel_titulo);
        panel_titulo.setLayout(panel_tituloLayout);
        panel_tituloLayout.setHorizontalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        panel_tituloLayout.setVerticalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tituloLayout.createSequentialGroup()
                .addGroup(panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_tituloLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_tituloLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panel_cabecera.add(panel_titulo, java.awt.BorderLayout.PAGE_START);

        panel_botones.setBackground(new java.awt.Color(204, 204, 204));
        panel_botones.setMinimumSize(new java.awt.Dimension(687, 56));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        btn_buscar.setBackground(new java.awt.Color(0, 102, 102));
        btn_buscar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_buscar.setPreferredSize(new java.awt.Dimension(26, 26));

        jLabel2.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Buscar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        btn_pedir.setBackground(new java.awt.Color(0, 153, 0));
        btn_pedir.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_pedir.setForeground(new java.awt.Color(255, 255, 255));
        btn_pedir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus_16px.png"))); // NOI18N
        btn_pedir.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_pedir.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_pedir.setPreferredSize(new java.awt.Dimension(30, 30));

        lbl_solicitar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_solicitar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_solicitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_solicitar.setText("¿Como pido un prestamo?");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbl_solicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btn_pedir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_pedir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_solicitar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_botonesLayout = new javax.swing.GroupLayout(panel_botones);
        panel_botones.setLayout(panel_botonesLayout);
        panel_botonesLayout.setHorizontalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_botonesLayout.setVerticalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel_cabecera.add(panel_botones, java.awt.BorderLayout.CENTER);

        add(panel_cabecera, java.awt.BorderLayout.PAGE_START);

        panel_contenido.setBackground(new java.awt.Color(204, 204, 204));

        table_principal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_principal.setRowHeight(40);
        jScrollPane2.setViewportView(table_principal);

        javax.swing.GroupLayout panel_contenidoLayout = new javax.swing.GroupLayout(panel_contenido);
        panel_contenido.setLayout(panel_contenidoLayout);
        panel_contenidoLayout.setHorizontalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_contenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_contenidoLayout.setVerticalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_contenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panel_contenido, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    // ------------------ CONFIGURACION DE LA TABLA ------------------
    /**
     * Crea los titulos de la tabla
     */
    /**
     * Crea los titulos de la tabla
     */
    public void configurarTabla() {
        
        String[] titulosTabla = new String[]{"ISBN", "Nro Ejemplar", "Sala", "Nro Pasillo", "Nro Estante", "Nro Cajon"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
    
    public void nuevaFilaTabla(String isbn, String nroEjemplar, String sala, String nroPasillo, String nroEstante, String nroCajon) {
        modeloTabla.addRow(new Object[]{
            isbn, nroEjemplar, sala, nroPasillo, nroEstante, nroCajon
        });
    }
    
    
    // ------------------ FUNCIONES DE LIMPIEZA ------------------
    /**
     * Elimina todos los registros de la tabla en la GUI
     */
    public void limpiarTabla() {
        int filasTabla = modeloTabla.getRowCount();
        for (int i = 0; i < filasTabla; i++) {
            modeloTabla.removeRow(0);
        }
    }    
    
    
    // ------------------ MODOS ------------------
    /**
     * Este modo se activa cuando no hay ningun registro de la tabla seleccionado
     * 
     * En este modo solo esta habilitado la seleccion de elementos en la tabla
     */
    public void modoPasivo(){
        btn_pedir.setEnabled(false);
        lbl_solicitar.setForeground(new java.awt.Color(102, 102, 102));
    }
    
    /**
     * Este modo se activa cuando se selecciona un registro de la tabla.
     * 
     * En este modo solo esta habilitado la seleccion de elementos en la tabla,
     * y el boton para solicitar
     */
    public void modoRegistroTablaSeleccionado(){
        btn_pedir.setEnabled(true);
        lbl_solicitar.setForeground(new java.awt.Color(0, 102, 102));
    }    
    
    
    // ------------------ LISTENERS ------------------
    public void addListenerVolver(ActionListener listener){
        btn_volver.addActionListener(listener);
    }
    
    public void addListenerBuscar(ActionListener listener){
        btn_buscar.addActionListener(listener);
    }
    
    public void addListenerPedir(ActionListener listener){
        btn_pedir.addActionListener(listener);
    }
    
    public void addListenerFilasTabla(MouseListener listener){
        table_principal.addMouseListener(listener);
    }
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public String getTxtf_buscar() {
        return txtf_buscar.getText();
    }

    public void setTxtf_buscar(String texto) {
        txtf_buscar.setText(texto);
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_pedir;
    private javax.swing.JButton btn_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_solicitar;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_cabecera;
    private javax.swing.JPanel panel_contenido;
    private javax.swing.JPanel panel_titulo;
    private javax.swing.JTable table_principal;
    private javax.swing.JTextField txtf_buscar;
    // End of variables declaration//GEN-END:variables

}
