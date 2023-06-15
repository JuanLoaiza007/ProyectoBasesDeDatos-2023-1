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

public class uPanelDescargas extends javax.swing.JPanel {
    
    /** Creates new form PanelAdministrar */
    public uPanelDescargas() {
        initComponents();
        table_principal.setModel(modeloTabla);
        configurarTabla();
        
        // DESARROLLADOR
        // Estos botones estaran deshabilitados en caso de que no se 
        // haya desarrollado la implementacion
        lbl_buscar.setVisible(false);
        txtf_buscar.setVisible(false);
        btn_buscar.setVisible(false);
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
        btn_volver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbl_buscar = new javax.swing.JLabel();
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
        jLabel1.setText("Descargas");
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 20));

        btn_volver.setBackground(new java.awt.Color(0, 102, 102));
        btn_volver.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/angle-left_16px.png"))); // NOI18N

        javax.swing.GroupLayout panelAdministrar_tituloLayout = new javax.swing.GroupLayout(panelAdministrar_titulo);
        panelAdministrar_titulo.setLayout(panelAdministrar_tituloLayout);
        panelAdministrar_tituloLayout.setHorizontalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrar_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAdministrar_tituloLayout.setVerticalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrar_tituloLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
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

        txtf_buscar.setBackground(new java.awt.Color(255, 255, 255));
        txtf_buscar.setForeground(new java.awt.Color(0, 0, 0));

        btn_buscar.setBackground(new java.awt.Color(0, 102, 102));
        btn_buscar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btn_buscar.setPreferredSize(new java.awt.Dimension(26, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(lbl_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_buscar)
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
        String[] titulosTabla = new String[]{"isbn", "Direccion Url", "Id Usuario", "fecha", "Direccion Ip"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }  
        
    public void nuevaFilaTabla(String isbn, String direccionUrl, String idUsuario, Timestamp fecha, String direccionIp) {
        modeloTabla.addRow(new Object[]{
            isbn, direccionUrl, idUsuario, fecha, direccionIp
        });
    }
    
    
    // ------------------ FUNCIONES DE LIMPIEZA ------------------
    public void limpiarTabla() {
        int filasTabla = modeloTabla.getRowCount();
        for (int i = 0; i < filasTabla; i++) {
            modeloTabla.removeRow(0);
        }
    }    
    
    
    // ------------------ MODOS ------------------
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
    
    
    // ------------------ LISTENERS ------------------
    public void addListenerVolver(ActionListener listener){
        btn_volver.addActionListener(listener);
    }
    
    public void addListenerBuscar(ActionListener listener){
        btn_buscar.addActionListener(listener);
    }
    
    public void addListenerFilasTabla(MouseListener listener){
        table_principal.addMouseListener(listener);
    }    
      
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public JTextField getTxtf_buscar() {
        return txtf_buscar;
    }

    public void setTxtf_buscar(String texto) {
        txtf_buscar.setText(texto);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_volver;
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
