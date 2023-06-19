package Vista;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: VistaDashboardAdminEmpleado.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class VistaDashboardAdminEmpleado extends javax.swing.JFrame {

    /** Creates new form VistaPrincipal */
    public VistaDashboardAdminEmpleado() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_rol = new javax.swing.JLabel();
        panel_lateral = new javax.swing.JPanel();
        btn_administrar = new javax.swing.JButton();
        btn_avanzado = new javax.swing.JButton();
        btn_solicitudes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_prestamos = new javax.swing.JButton();
        btn_multas = new javax.swing.JButton();
        btn_devoluciones = new javax.swing.JButton();
        panel_contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 700));

        panel_cabecera.setBackground(new java.awt.Color(0, 102, 102));
        panel_cabecera.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("San Francisco Text", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Panel de Control");

        btn_salir.setBackground(new java.awt.Color(0, 153, 153));
        btn_salir.setForeground(new java.awt.Color(255, 255, 255));
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/on-off-button.png"))); // NOI18N
        btn_salir.setBorder(null);
        btn_salir.setBorderPainted(false);
        btn_salir.setContentAreaFilled(false);
        btn_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salir.setFocusPainted(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_64px.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(88, 88));
        jLabel2.setMinimumSize(new java.awt.Dimension(88, 88));
        jLabel2.setPreferredSize(new java.awt.Dimension(88, 88));

        lbl_rol.setForeground(new java.awt.Color(255, 255, 255));
        lbl_rol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_rol.setText("Administrador");

        javax.swing.GroupLayout panel_cabeceraLayout = new javax.swing.GroupLayout(panel_cabecera);
        panel_cabecera.setLayout(panel_cabeceraLayout);
        panel_cabeceraLayout.setHorizontalGroup(
            panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cabeceraLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(lbl_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_cabeceraLayout.setVerticalGroup(
            panel_cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbl_rol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(panel_cabecera, java.awt.BorderLayout.PAGE_START);

        panel_lateral.setBackground(new java.awt.Color(0, 153, 153));
        panel_lateral.setForeground(new java.awt.Color(255, 255, 255));

        btn_administrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_administrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_administrar.setText("Administrar");
        btn_administrar.setBorder(null);
        btn_administrar.setBorderPainted(false);
        btn_administrar.setContentAreaFilled(false);
        btn_administrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_avanzado.setBackground(new java.awt.Color(255, 255, 255));
        btn_avanzado.setForeground(new java.awt.Color(255, 255, 255));
        btn_avanzado.setText("Avanzado");
        btn_avanzado.setBorder(null);
        btn_avanzado.setBorderPainted(false);
        btn_avanzado.setContentAreaFilled(false);
        btn_avanzado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_avanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_avanzadoActionPerformed(evt);
            }
        });

        btn_solicitudes.setBackground(new java.awt.Color(255, 255, 255));
        btn_solicitudes.setForeground(new java.awt.Color(255, 255, 255));
        btn_solicitudes.setText("Solicitudes");
        btn_solicitudes.setBorder(null);
        btn_solicitudes.setBorderPainted(false);
        btn_solicitudes.setContentAreaFilled(false);
        btn_solicitudes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("San Francisco Text", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Biblioteca Univalle");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library_64px.png"))); // NOI18N
        jLabel4.setOpaque(true);

        btn_prestamos.setBackground(new java.awt.Color(255, 255, 255));
        btn_prestamos.setForeground(new java.awt.Color(255, 255, 255));
        btn_prestamos.setText("Prestamos");
        btn_prestamos.setBorder(null);
        btn_prestamos.setBorderPainted(false);
        btn_prestamos.setContentAreaFilled(false);
        btn_prestamos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prestamosActionPerformed(evt);
            }
        });

        btn_multas.setBackground(new java.awt.Color(255, 255, 255));
        btn_multas.setForeground(new java.awt.Color(255, 255, 255));
        btn_multas.setText("Multas");
        btn_multas.setBorder(null);
        btn_multas.setBorderPainted(false);
        btn_multas.setContentAreaFilled(false);
        btn_multas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_devoluciones.setBackground(new java.awt.Color(255, 255, 255));
        btn_devoluciones.setForeground(new java.awt.Color(255, 255, 255));
        btn_devoluciones.setText("Devoluciones");
        btn_devoluciones.setBorder(null);
        btn_devoluciones.setBorderPainted(false);
        btn_devoluciones.setContentAreaFilled(false);
        btn_devoluciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panel_lateralLayout = new javax.swing.GroupLayout(panel_lateral);
        panel_lateral.setLayout(panel_lateralLayout);
        panel_lateralLayout.setHorizontalGroup(
            panel_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
            .addGroup(panel_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_prestamos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_solicitudes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_administrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(btn_avanzado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_multas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_devoluciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_lateralLayout.setVerticalGroup(
            panel_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_administrar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_prestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_devoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_multas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_avanzado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panel_lateral, java.awt.BorderLayout.LINE_START);

        panel_contenido.setBackground(new java.awt.Color(204, 204, 204));
        panel_contenido.setForeground(new java.awt.Color(255, 255, 255));
        panel_contenido.setMinimumSize(new java.awt.Dimension(687, 500));

        javax.swing.GroupLayout panel_contenidoLayout = new javax.swing.GroupLayout(panel_contenido);
        panel_contenido.setLayout(panel_contenidoLayout);
        panel_contenidoLayout.setHorizontalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        panel_contenidoLayout.setVerticalGroup(
            panel_contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(panel_contenido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_avanzadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_avanzadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_avanzadoActionPerformed

    private void btn_prestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_prestamosActionPerformed

    public void doClickAdministrar(){
        btn_administrar.doClick();
    }
    
    public void doClickPrestamos(){
        btn_prestamos.doClick();
    }
    
    public void doClickDevoluciones(){
        btn_devoluciones.doClick();
    }
    
    public void doClickMultas(){
        btn_multas.doClick();
    }
    
    public void doClickSolicitudes(){
        btn_solicitudes.doClick();
    }
    
    public void doClickAvanzado(){
        btn_avanzado.doClick();
    }
    
    public void cambiarPanel(javax.swing.JPanel nuevoPanel){    
        
        getContentPane().remove(panel_contenido); // Eliminar el panel basico.
        panel_contenido = nuevoPanel; // Actualizar el contenido del panel basico
        getContentPane().add(panel_contenido, java.awt.BorderLayout.CENTER); // Agregar el nuevo panel al centro del JFrame
        
        // Funciones para limpiar y repintar el panel
        revalidate();
        repaint();
    }
    
    public void addActionAdministrar(ActionListener listener){
        btn_administrar.addActionListener(listener);
    }
    
    public void addActionPrestamos(ActionListener listener){
        btn_prestamos.addActionListener(listener);
    }
    
    public void addActionDevoluciones(ActionListener listener){
        btn_devoluciones.addActionListener(listener);
    }
    
    public void addActionSolicitudes(ActionListener listener){
        btn_solicitudes.addActionListener(listener);
    }
    
    public void addActionMultas(ActionListener listener){
        btn_multas.addActionListener(listener);
    }
    
    public void addActionAvanzado(ActionListener listener){
        btn_avanzado.addActionListener(listener);
    }
    
    public void addActionSalir(ActionListener listener){
        btn_salir.addActionListener(listener);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaDashboardAdminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDashboardAdminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDashboardAdminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDashboardAdminEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaDashboardAdminEmpleado().setVisible(true);
            }
        });
    }

    public String setRol() {
        return lbl_rol.getText();
    }

    public void setRol(String rol) {
        this.lbl_rol.setText(rol);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_administrar;
    private javax.swing.JButton btn_avanzado;
    private javax.swing.JButton btn_devoluciones;
    private javax.swing.JButton btn_multas;
    private javax.swing.JButton btn_prestamos;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_solicitudes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl_rol;
    private javax.swing.JPanel panel_cabecera;
    private javax.swing.JPanel panel_contenido;
    private javax.swing.JPanel panel_lateral;
    // End of variables declaration//GEN-END:variables

}
