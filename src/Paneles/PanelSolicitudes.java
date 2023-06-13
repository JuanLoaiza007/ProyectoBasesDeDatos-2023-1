/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

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
 * Archivo: PanelAdministrar.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PanelSolicitudes extends javax.swing.JPanel {

    
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
        
        String[] titulosTabla = new String[]{"Nro. consecutivo", "Usuario", "Empleado", "ISBN", "Titulo", "Descripcion", "Fecha"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }  
        
    public void nuevaFilaTabla(String nroConsecutivoSolicitud, String idUsuario, 
            String idEmpleado, String isbn, String titulo, String descripcion, Timestamp fecha) {
        modeloTabla.addRow(new Object[]{
            nroConsecutivoSolicitud, idUsuario, idEmpleado, isbn, titulo, descripcion, fecha
        });
    }
    
    public void limpiarTabla() {
        int filasTabla = modeloTabla.getRowCount();
        for (int i = 0; i < filasTabla; i++) {
            modeloTabla.removeRow(0);
        }
    }    
    
    /** Creates new form PanelAdministrar */
    public PanelSolicitudes() {
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

        panelAdministrar_titulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbl_buscar = new javax.swing.JLabel();
        btn_negar = new javax.swing.JButton();
        btn_aprobar = new javax.swing.JButton();
        txtf_buscar = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_principal = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setMinimumSize(new java.awt.Dimension(687, 500));
        setPreferredSize(new java.awt.Dimension(687, 500));
        setLayout(new java.awt.BorderLayout());

        panelAdministrar_titulo.setBackground(new java.awt.Color(204, 204, 204));
        panelAdministrar_titulo.setOpaque(false);
        panelAdministrar_titulo.setPreferredSize(new java.awt.Dimension(687, 60));

        jLabel1.setFont(new java.awt.Font("San Francisco Text", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Solicitudes");
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 20));

        javax.swing.GroupLayout panelAdministrar_tituloLayout = new javax.swing.GroupLayout(panelAdministrar_titulo);
        panelAdministrar_titulo.setLayout(panelAdministrar_tituloLayout);
        panelAdministrar_tituloLayout.setHorizontalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrar_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAdministrar_tituloLayout.setVerticalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrar_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelAdministrar_titulo, java.awt.BorderLayout.PAGE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(687, 60));

        lbl_buscar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_buscar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_buscar.setText("Buscar:");

        btn_negar.setBackground(new java.awt.Color(204, 0, 51));
        btn_negar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_negar.setForeground(new java.awt.Color(255, 255, 255));
        btn_negar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_negar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_negarActionPerformed(evt);
            }
        });

        btn_aprobar.setBackground(new java.awt.Color(0, 153, 0));
        btn_aprobar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_aprobar.setForeground(new java.awt.Color(255, 255, 255));
        btn_aprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N

        txtf_buscar.setBackground(new java.awt.Color(255, 255, 255));
        txtf_buscar.setForeground(new java.awt.Color(0, 0, 0));

        btn_buscar.setBackground(new java.awt.Color(0, 102, 102));
        btn_buscar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_buscar.setPreferredSize(new java.awt.Dimension(26, 26));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(lbl_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btn_aprobar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_negar)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_buscar)
                        .addComponent(btn_negar)
                        .addComponent(btn_aprobar)
                        .addComponent(txtf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        table_principal.setForeground(new java.awt.Color(0, 0, 0));
        table_principal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_principal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_principal.getTableHeader().setResizingAllowed(false);
        table_principal.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_principal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_negarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_negarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_negarActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscarActionPerformed

   public JTextField getTxtf_buscar() {
        return txtf_buscar;
    }

    public void setTxtf_buscar(String texto) {
        txtf_buscar.setText(texto);
    }
    
    public void addListenerBuscar(ActionListener listener){
        btn_buscar.addActionListener(listener);
    }
    
    public void addListenerFilasTabla(MouseListener listener){
        table_principal.addMouseListener(listener);
    }    

    public void modoInsertar(){
        table_principal.setEnabled(false);
        
        //btn_borrar.setEnabled(false);
        //lbl_borrar.setForeground(new java.awt.Color(102, 102, 102));
    }   
    
    public void modoEditar(){
        modoInsertar();
    }
    
    public void modoRegistroTablaSeleccionado(){
        modoPasivo();
        
        //btn_borrar.setEnabled(true);
        //lbl_borrar.setForeground(new java.awt.Color(0, 102, 102));
    }   
    
    public void modoPasivo(){
        table_principal.setEnabled(true);
        
        //btn_borrar.setEnabled(false);
        //lbl_borrar.setForeground(new java.awt.Color(102, 102, 102));
    }    

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aprobar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_negar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_buscar;
    private javax.swing.JPanel panelAdministrar_titulo;
    private javax.swing.JTable table_principal;
    private javax.swing.JTextField txtf_buscar;
    // End of variables declaration//GEN-END:variables

}
