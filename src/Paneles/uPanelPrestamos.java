package Paneles;

import java.sql.Timestamp;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: uPanelPrestamos.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class uPanelPrestamos extends javax.swing.JPanel {

    /** Creates new form PanelAdministrar */
    public uPanelPrestamos() {
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
        jLabel1.setText("Prestamos");
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
        table_principal.setRowHeight(40);
        table_principal.getTableHeader().setResizingAllowed(false);
        table_principal.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_principal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
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
        String[] titulosTabla = new String[]{"Nro. consecutivo", "Usuario", "Empleado", "Fecha"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }  
        
    public void nuevaFilaTabla(int nroConsecutivoPrestamo, String idUsuario, String idEmpleado, Timestamp fechaRealizacion) {
        modeloTabla.addRow(new Object[]{
            nroConsecutivoPrestamo, idUsuario, idEmpleado, fechaRealizacion
        });
    }

    
    // ------------------ FUNCIONES DE LIMPIEZA ------------------
    public void limpiarTabla() {
        int filasTabla = modeloTabla.getRowCount();
        for (int i = 0; i < filasTabla; i++) {
            modeloTabla.removeRow(0);
        }
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelAdministrar_titulo;
    private javax.swing.JTable table_principal;
    // End of variables declaration//GEN-END:variables

}
