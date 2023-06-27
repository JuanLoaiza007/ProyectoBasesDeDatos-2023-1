package Paneles;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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

public class PanelEditoriales extends javax.swing.JPanel {
    
    /** Creates new form PanelAdministrar */
    public PanelEditoriales() {
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
        btn_borrar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        lbl_nuevo = new javax.swing.JLabel();
        lbl_editar = new javax.swing.JLabel();
        lbl_borrar = new javax.swing.JLabel();
        btn_editar = new javax.swing.JButton();
        panel_contenido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        lbl_cancelar = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JButton();
        lbl_guardar = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        txtf_id = new javax.swing.JTextField();
        lbl_nombreEditorial = new javax.swing.JLabel();
        txtf_nombreEditorial = new javax.swing.JTextField();
        lbl_paginaWeb = new javax.swing.JLabel();
        txtf_paginaWeb = new javax.swing.JTextField();
        lbl_paisOrigen = new javax.swing.JLabel();
        txtf_paisOrigen = new javax.swing.JTextField();
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
        jLabel1.setText("Editoriales");
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

        btn_borrar.setBackground(new java.awt.Color(204, 0, 51));
        btn_borrar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_borrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_16px.png"))); // NOI18N
        btn_borrar.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_borrar.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_borrar.setPreferredSize(new java.awt.Dimension(30, 30));

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
        lbl_nuevo.setText("Nuevo");

        lbl_editar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_editar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_editar.setText("Editar");

        lbl_borrar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_borrar.setForeground(new java.awt.Color(0, 102, 102));
        lbl_borrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_borrar.setText("Borrar");

        btn_editar.setBackground(new java.awt.Color(0, 102, 102));
        btn_editar.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        btn_editar.setForeground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil_16px.png"))); // NOI18N
        btn_editar.setMaximumSize(new java.awt.Dimension(30, 30));
        btn_editar.setMinimumSize(new java.awt.Dimension(30, 30));
        btn_editar.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_borrar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_borrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nuevo)
                    .addComponent(lbl_editar)
                    .addComponent(lbl_borrar))
                .addContainerGap())
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

        lbl_id.setBackground(new java.awt.Color(0, 0, 0));
        lbl_id.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_id.setText("Id Editorial:");

        txtf_id.setEnabled(false);

        lbl_nombreEditorial.setBackground(new java.awt.Color(0, 0, 0));
        lbl_nombreEditorial.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_nombreEditorial.setText("Nombre Editorial:");

        lbl_paginaWeb.setBackground(new java.awt.Color(0, 0, 0));
        lbl_paginaWeb.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_paginaWeb.setText("Pagina web:");

        lbl_paisOrigen.setBackground(new java.awt.Color(0, 0, 0));
        lbl_paisOrigen.setFont(new java.awt.Font("San Francisco Text", 1, 16)); // NOI18N
        lbl_paisOrigen.setText("País de origen:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_id)
                            .addComponent(lbl_nombreEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_nombreEditorial)
                            .addComponent(lbl_paginaWeb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_paginaWeb)
                            .addComponent(lbl_paisOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtf_paisOrigen))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_guardar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cancelar)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addContainerGap(54, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_nombreEditorial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_nombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_paginaWeb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_paginaWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_paisOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtf_paisOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cancelar)
                    .addComponent(lbl_guardar))
                .addContainerGap())
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
        
        String[] titulosTabla = new String[]{"Id", "Nombre", "Web", "Pais"};
        modeloTabla.setColumnIdentifiers(titulosTabla);        
 
        // CENTRAR CONTENIDO DE COLUMNAS
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < titulosTabla.length; i++){
            table_principal.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
    
    public void nuevaFilaTabla(String id, String nombre, String paginaWeb, String paisOrigen) {
        modeloTabla.addRow(new Object[]{
            id, nombre, paginaWeb, paisOrigen
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
    
    /**
     * Limpiar el contenido de todos los campos de teto
     */
    public void limpiarCampos(){
        txtf_id.setText("");
        txtf_nombreEditorial.setText("");
        txtf_paisOrigen.setText("");
        txtf_paginaWeb.setText("");
        txtf_buscar.setText("");
    }
    
    
    // ------------------ MODOS ------------------
    /**
     * Este modo se activa cuando se va a crear un registro nuevo.
     * 
     * La tabla esta deshabilitada, todos los campos estan disponibles.
     * Los botones de guardar, borrar y cancelar estan activos.
     */
    public void modoInsertar(){
        table_principal.setEnabled(false);
        
        btn_nuevo.setEnabled(false);
        lbl_nuevo.setForeground(new java.awt.Color(102, 102, 102));
        btn_editar.setEnabled(false);
        lbl_editar.setForeground(new java.awt.Color(102, 102, 102));
        btn_borrar.setEnabled(false);
        lbl_borrar.setForeground(new java.awt.Color(102, 102, 102));
        btn_guardar.setEnabled(true);
        lbl_guardar.setForeground(new java.awt.Color(0, 102, 102));
        btn_cancelar.setEnabled(true);
        lbl_cancelar.setForeground(new java.awt.Color(0, 102, 102));
        
        txtf_id.setEnabled(true);
        lbl_id.setForeground(new java.awt.Color(0, 0, 0));
        txtf_nombreEditorial.setEnabled(true);
        lbl_nombreEditorial.setForeground(new java.awt.Color(0, 0, 0));
        txtf_paginaWeb.setEnabled(true);
        lbl_paginaWeb.setForeground(new java.awt.Color(0, 0, 0));
        txtf_paisOrigen.setEnabled(true);
        lbl_paisOrigen.setForeground(new java.awt.Color(0, 0, 0));
    }
    
    /**
     * Este modo se activa cuando se va a modificar un registro existente.
     * 
     * La tabla esta deshabilitada, todos los campos estan disponibles 
     * A EXCEPCION de los campos que representan la PK en la base de datos.
     * 
     * Los botones de guardar, borrar y cancelar estan activos.
     */
    public void modoEditar(){
        modoInsertar();
        txtf_id.setEnabled(false);
    }
    
    /**
     * Este modo se activa cuando se selecciona un registro de la tabla.
     * 
     * En este modo solo esta habilitado la seleccion de elementos en la tabla,
     * el boton para empezar a insertar registros y los botones para editar y borrar
     */
    public void modoRegistroTablaSeleccionado(){
        modoPasivo();
        
        btn_editar.setEnabled(true);
        lbl_editar.setForeground(new java.awt.Color(0, 102, 102));
        btn_borrar.setEnabled(true);
        lbl_borrar.setForeground(new java.awt.Color(0, 102, 102));
    }
    
    /**
     * Este modo se activa cuando no hay ningun registro de la tabla seleccionado
     * ni se está editando algun registro.
     * 
     * En este modo solo esta habilitado la seleccion de elementos en la tabla
     * y el boton para empezar a insertar registros
     */
    public void modoPasivo(){        
        btn_nuevo.setEnabled(true);
        lbl_nuevo.setForeground(new java.awt.Color(0, 102, 102));
        btn_editar.setEnabled(false);
        lbl_editar.setForeground(new java.awt.Color(102, 102, 102));
        btn_borrar.setEnabled(false);
        lbl_borrar.setForeground(new java.awt.Color(102, 102, 102));
        btn_guardar.setEnabled(false);
        lbl_guardar.setForeground(new java.awt.Color(102, 102, 102));
        btn_cancelar.setEnabled(false);
        lbl_cancelar.setForeground(new java.awt.Color(102, 102, 102));
        
        txtf_id.setEnabled(false);
        lbl_id.setForeground(new java.awt.Color(102, 102, 102));
        txtf_nombreEditorial.setEnabled(false);
        lbl_nombreEditorial.setForeground(new java.awt.Color(102, 102, 102));
        txtf_paginaWeb.setEnabled(false);
        lbl_paginaWeb.setForeground(new java.awt.Color(102, 102, 102));
        txtf_paisOrigen.setEnabled(false);
        lbl_paisOrigen.setForeground(new java.awt.Color(102, 102, 102));
    }
    
    
    // ------------------ OTROS ------------------
    /**
     * Revisa si el campo asociado al id del registro esta activo y permite escribir.
     * @return true, si el campo esta activo; false, si el campo no esta activo.
     */
    public boolean idEsManual(){
        return txtf_id.isEnabled();
    }
    
    
    // ------------------ LISTENERS ------------------
    public void addListenerVolver(ActionListener listener){
        btn_volver.addActionListener(listener);
    }
    
    public void addListenerBuscar(ActionListener listener){
        btn_buscar.addActionListener(listener);
    }
    
    public void addListenerNuevo(ActionListener listener){
        btn_nuevo.addActionListener(listener);
    }
    
    public void addListenerEditar(ActionListener listener){
        btn_editar.addActionListener(listener);
    }
    
    public void addListenerBorrar(ActionListener listener){
        btn_borrar.addActionListener(listener);
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
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public JTextField getTxtf_buscar() {
        return txtf_buscar;
    }

    public void setTxtf_buscar(String texto) {
        txtf_buscar.setText(texto);
    }
    
    public JTextField getTxtf_id() {
        return txtf_id;
    }
    
    public void setId(String texto) {
        txtf_id.setText(texto);
    }

    public JTextField getTxtf_paisOrigen() {
        return txtf_paisOrigen;
    }
    
    public void setPaisOrigen(String texto) {
        txtf_paisOrigen.setText(texto);
    }

    public JTextField getTxtf_nombreEditorial() {
        return txtf_nombreEditorial;
    }
    
    public void setNombreEditorial(String texto) {
        txtf_nombreEditorial.setText(texto);
    }

    public JTextField getTxtf_paginaWeb() {
        return txtf_paginaWeb;
    }
    
    public void setPaginaWeb(String texto) {
        txtf_paginaWeb.setText(texto);
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_borrar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_borrar;
    private javax.swing.JLabel lbl_cancelar;
    private javax.swing.JLabel lbl_editar;
    private javax.swing.JLabel lbl_guardar;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_nombreEditorial;
    private javax.swing.JLabel lbl_nuevo;
    private javax.swing.JLabel lbl_paginaWeb;
    private javax.swing.JLabel lbl_paisOrigen;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_cabecera;
    private javax.swing.JPanel panel_contenido;
    private javax.swing.JPanel panel_titulo;
    private javax.swing.JTable table_principal;
    private javax.swing.JTextField txtf_buscar;
    private javax.swing.JTextField txtf_id;
    private javax.swing.JTextField txtf_nombreEditorial;
    private javax.swing.JTextField txtf_paginaWeb;
    private javax.swing.JTextField txtf_paisOrigen;
    // End of variables declaration//GEN-END:variables

}
