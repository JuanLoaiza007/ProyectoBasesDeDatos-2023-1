package Paneles;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PanelDevoluciones.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PanelDevoluciones extends javax.swing.JPanel {
 
    /** Creates new form PanelAdministrar */
    public PanelDevoluciones() {
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
        panel_contenido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        lbl_cancelar = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JButton();
        lbl_guardar = new javax.swing.JLabel();
        lbl_nroPrestamo = new javax.swing.JLabel();
        txtf_nroPrestamo = new javax.swing.JTextField();
        lbl_nroEjemplar = new javax.swing.JLabel();
        txtf_nroEjemplar = new javax.swing.JTextField();
        txtf_isbn = new javax.swing.JTextField();
        lbl_isbn = new javax.swing.JLabel();
        txtf_idUsuario = new javax.swing.JTextField();
        lbl_idUsuario = new javax.swing.JLabel();
        btn_nuevo = new javax.swing.JButton();
        lbl_nuevo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        jLabel1.setText("Devoluciones");
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 20));

        javax.swing.GroupLayout panel_tituloLayout = new javax.swing.GroupLayout(panel_titulo);
        panel_titulo.setLayout(panel_tituloLayout);
        panel_tituloLayout.setHorizontalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_tituloLayout.setVerticalGroup(
            panel_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_cabecera.add(panel_titulo, java.awt.BorderLayout.PAGE_START);

        add(panel_cabecera, java.awt.BorderLayout.PAGE_START);

        panel_contenido.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btn_guardar.setBackground(new java.awt.Color(0, 204, 102));
        btn_guardar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette_16px.png"))); // NOI18N
        btn_guardar.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_guardar.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_guardar.setPreferredSize(new java.awt.Dimension(30, 30));

        lbl_cancelar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_cancelar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_cancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_cancelar.setText("Cancelar");

        btn_cancelar.setBackground(new java.awt.Color(204, 0, 51));
        btn_cancelar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cancelar.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_cancelar.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_cancelar.setPreferredSize(new java.awt.Dimension(30, 30));

        lbl_guardar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_guardar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_guardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_guardar.setText("Guardar");

        lbl_nroPrestamo.setBackground(new java.awt.Color(0, 0, 0));
        lbl_nroPrestamo.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_nroPrestamo.setText("Nro Prestamo:");

        txtf_nroPrestamo.setEnabled(false);

        lbl_nroEjemplar.setBackground(new java.awt.Color(0, 0, 0));
        lbl_nroEjemplar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_nroEjemplar.setText("Nro Ejemplar:");

        lbl_isbn.setBackground(new java.awt.Color(0, 0, 0));
        lbl_isbn.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_isbn.setText("Isbn:");

        lbl_idUsuario.setBackground(new java.awt.Color(0, 0, 0));
        lbl_idUsuario.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_idUsuario.setText("Id Usuario:");

        btn_nuevo.setBackground(new java.awt.Color(0, 153, 0));
        btn_nuevo.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus_16px.png"))); // NOI18N
        btn_nuevo.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_nuevo.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_nuevo.setPreferredSize(new java.awt.Dimension(30, 30));

        lbl_nuevo.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_nuevo.setForeground(new java.awt.Color(0, 102, 102));
        lbl_nuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nuevo.setText("Registrar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nroPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_nroPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addComponent(lbl_nroEjemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_nroEjemplar)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addComponent(lbl_isbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_isbn)
                            .addComponent(lbl_idUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_idUsuario)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nuevo)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lbl_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_cancelar)
                        .addGap(64, 64, 64))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_nroPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_nroPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_idUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_isbn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_nroEjemplar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_nroEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cancelar)
                    .addComponent(lbl_guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_principal.setRowHeight(30);
        table_principal.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_principal);
        if (table_principal.getColumnModel().getColumnCount() > 0) {
            table_principal.getColumnModel().getColumn(0).setResizable(false);
            table_principal.getColumnModel().getColumn(1).setResizable(false);
            table_principal.getColumnModel().getColumn(2).setResizable(false);
            table_principal.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_contenidoLayout = new javax.swing.GroupLayout(panel_contenido);
        panel_contenido.setLayout(panel_contenidoLayout);
        panel_contenidoLayout.setHorizontalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_contenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_contenidoLayout.setVerticalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(panel_contenido, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    
     // ------------------ CONFIGURACION DE LA TABLA ------------------
    /**
     * Creacion de un modelo de tabla NO editable
     */
    private DefaultTableModel modeloTabla = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
    /**
     * Crea los titulos de la tabla
     */
    public void configurarTabla() {
        
        String[] titulosTabla = new String[]{"Nro Prestamo", "Id Usuario", "Isbn", "Nro Ejemplar", "Fecha"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
    
    public void nuevaFilaTabla(int nroPrestamo, String idUsuario, String isbn, String nroEjemplar, Timestamp fecha) {
        modeloTabla.addRow(new Object[]{
            nroPrestamo, idUsuario, isbn, nroEjemplar, fecha
        });
    }
    
    
    // ------------------ FUNCIONES DE LIMPIEZA ------------------
    public void limpiarTabla() {
        int filasTabla = modeloTabla.getRowCount();
        for (int i = 0; i < filasTabla; i++) {
            modeloTabla.removeRow(0);
        }
    }
    
    public void limpiarCampos(){
        txtf_nroPrestamo.setText("");
        txtf_idUsuario.setText("");
        txtf_isbn.setText("");
        txtf_nroEjemplar.setText("");
    }
    
    
    // ------------------ LISTENERS ------------------
    public void addListenerNuevo(ActionListener listener){
        btn_nuevo.addActionListener(listener);
    }
    
    public void addListenerGuardar(ActionListener listener){
        btn_guardar.addActionListener(listener);
    }
    
    public void addListenerCancelar(ActionListener listener){
        btn_cancelar.addActionListener(listener);
    }
    
    public void addListenerFilasTabla(MouseListener listener){
        table_principal.addMouseListener(listener);
    }
    
    
    // ------------------ MODOS ------------------
    public void modoInsertar(){
        table_principal.setEnabled(false);
        
        btn_nuevo.setEnabled(false);
        lbl_nuevo.setForeground(new java.awt.Color(102, 102, 102));
        btn_guardar.setEnabled(true);
        lbl_guardar.setForeground(new java.awt.Color(0, 102, 102));
        btn_cancelar.setEnabled(true);
        lbl_cancelar.setForeground(new java.awt.Color(0, 102, 102));
        
        txtf_nroPrestamo.setEnabled(true);
        lbl_nroPrestamo.setForeground(new java.awt.Color(0, 0, 0));
        txtf_idUsuario.setEnabled(true);
        lbl_idUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtf_isbn.setEnabled(true);
        lbl_isbn.setForeground(new java.awt.Color(0, 0, 0));           
        txtf_nroEjemplar.setEnabled(true);
        lbl_nroEjemplar.setForeground(new java.awt.Color(0, 0, 0));
    }
    
    public void modoPasivo(){
        table_principal.setEnabled(true);
        
        btn_nuevo.setEnabled(true);
        lbl_nuevo.setForeground(new java.awt.Color(0, 102, 102));
        btn_guardar.setEnabled(false);
        lbl_guardar.setForeground(new java.awt.Color(102, 102, 102));
        btn_cancelar.setEnabled(false);
        lbl_cancelar.setForeground(new java.awt.Color(102, 102, 102));
        
        txtf_nroPrestamo.setEnabled(false);
        lbl_nroPrestamo.setForeground(new java.awt.Color(102, 102, 102));
        txtf_idUsuario.setEnabled(false);
        lbl_idUsuario.setForeground(new java.awt.Color(102, 102, 102));
        txtf_isbn.setEnabled(false);
        lbl_isbn.setForeground(new java.awt.Color(102, 102, 102));        
        txtf_nroEjemplar.setEnabled(false);
        lbl_nroEjemplar.setForeground(new java.awt.Color(102, 102, 102));
    }
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public String getTxtf_idUsuario() {
        return txtf_idUsuario.getText();
    }

    public void setTxtf_idUsuario(String txtf_idUsuario) {
        this.txtf_idUsuario.setText(txtf_idUsuario);
    }

    public String getTxtf_isbn() {
        return txtf_isbn.getText();
    }

    public void setTxtf_isbn(String txtf_isbn) {
        this.txtf_isbn.setText(txtf_isbn);
    }

    public String getTxtf_nroEjemplar() {
        return txtf_nroEjemplar.getText();
    }

    public void setTxtf_nroEjemplar(String txtf_nroEjemplar) {
        this.txtf_nroEjemplar.setText(txtf_nroEjemplar);
    }

    public String getTxtf_nroPrestamo() {
        return txtf_nroPrestamo.getText();
    }

    public void setTxtf_nroPrestamo(String txtf_nroPrestamo) {
        this.txtf_nroPrestamo.setText(txtf_nroPrestamo);
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_cancelar;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JLabel lbl_idUsuario;
    private javax.swing.JLabel lbl_isbn;
    private javax.swing.JLabel lbl_nroEjemplar;
    private javax.swing.JLabel lbl_nroPrestamo;
    private javax.swing.JLabel lbl_nuevo;
    private javax.swing.JPanel panel_cabecera;
    private javax.swing.JPanel panel_contenido;
    private javax.swing.JPanel panel_titulo;
    private javax.swing.JTable table_principal;
    private javax.swing.JTextField txtf_idUsuario;
    private javax.swing.JTextField txtf_isbn;
    private javax.swing.JTextField txtf_nroEjemplar;
    private javax.swing.JTextField txtf_nroPrestamo;
    // End of variables declaration//GEN-END:variables

}