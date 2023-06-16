package Paneles;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: PanelCambiarContrasena.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

public class PanelCambiarContrasena extends javax.swing.JPanel {

    /** Creates new form PanelAdministrar */
    public PanelCambiarContrasena() {
        initComponents(); 
        
        pass_anterior.addKeyListener(protector);
        pass_nueva.addKeyListener(protector);
        pass_nueva2.addKeyListener(protector);
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
        jPanel4 = new javax.swing.JPanel();
        lbl_contrasenaActual = new javax.swing.JLabel();
        lbl_nuevaContrasena = new javax.swing.JLabel();
        btn_cambiar = new javax.swing.JButton();
        lbl_nuevaContrasena2 = new javax.swing.JLabel();
        pass_nueva = new javax.swing.JPasswordField();
        pass_nueva2 = new javax.swing.JPasswordField();
        pass_anterior = new javax.swing.JPasswordField();

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
        jLabel1.setText("Cambiar contraseña");
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 20));

        btn_volver.setBackground(new java.awt.Color(0, 102, 102));
        btn_volver.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/angle-left_16px.png"))); // NOI18N

        javax.swing.GroupLayout panelAdministrar_tituloLayout = new javax.swing.GroupLayout(panelAdministrar_titulo);
        panelAdministrar_titulo.setLayout(panelAdministrar_tituloLayout);
        panelAdministrar_tituloLayout.setHorizontalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdministrar_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        panelAdministrar_tituloLayout.setVerticalGroup(
            panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdministrar_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdministrar_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(panelAdministrar_titulo, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lbl_contrasenaActual.setFont(new java.awt.Font("San Francisco Text", 1, 18)); // NOI18N
        lbl_contrasenaActual.setForeground(new java.awt.Color(0, 102, 102));
        lbl_contrasenaActual.setText("Contraseña actual: *");

        lbl_nuevaContrasena.setFont(new java.awt.Font("San Francisco Text", 1, 18)); // NOI18N
        lbl_nuevaContrasena.setForeground(new java.awt.Color(0, 102, 102));
        lbl_nuevaContrasena.setText("Nueva contraseña: *");

        btn_cambiar.setBackground(new java.awt.Color(0, 102, 102));
        btn_cambiar.setFont(new java.awt.Font("San Francisco Text", 1, 18)); // NOI18N
        btn_cambiar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cambiar.setText("Cambiar contraseña");

        lbl_nuevaContrasena2.setFont(new java.awt.Font("San Francisco Text", 1, 18)); // NOI18N
        lbl_nuevaContrasena2.setForeground(new java.awt.Color(0, 102, 102));
        lbl_nuevaContrasena2.setText("Confirma la contraseña: *");

        pass_nueva.setBackground(new java.awt.Color(255, 255, 255));
        pass_nueva.setForeground(new java.awt.Color(0, 0, 0));

        pass_nueva2.setBackground(new java.awt.Color(255, 255, 255));
        pass_nueva2.setForeground(new java.awt.Color(0, 0, 0));

        pass_anterior.setBackground(new java.awt.Color(255, 255, 255));
        pass_anterior.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 229, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pass_nueva)
                    .addComponent(lbl_nuevaContrasena2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nuevaContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_contrasenaActual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pass_nueva2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_anterior))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(lbl_contrasenaActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbl_nuevaContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_nueva, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_nuevaContrasena2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass_nueva2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btn_cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    
    // ------------------ FUNCIONES DE LIMPIEZA ------------------
    public void limpiarCampos(){
        pass_anterior.setText("");
        pass_nueva.setText("");
        pass_nueva2.setText("");
    }
    
    
    // ------------------ LISTENERS ------------------   
    KeyAdapter protector = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (c == KeyEvent.VK_SPACE) {
                e.consume();
            }
        }
    };
    
    public void addListenerVolver(ActionListener listener){
        btn_volver.addActionListener(listener);
    }
    
    public void addListenerCambiar(ActionListener listener){
        btn_cambiar.addActionListener(listener);
    }
    
    
    // ------------------ SETTERS Y GETTERS  ------------------
    public String getPass_anterior() {
        return new String(pass_anterior.getPassword());
    }

    public void setPass_anterior(String pass_anterior) {
        this.pass_anterior.setText(pass_anterior);
    }

    public String getPass_nueva() {
        return new String(pass_nueva.getPassword());
    }

    public void setPass_nueva(String pass_nueva) {
        this.pass_nueva.setText(pass_nueva);
    }

    public String getPass_nueva2() {
        return new String(pass_nueva2.getPassword());
    }

    public void setPass_nueva2(String pass_nueva2) {
        this.pass_nueva2.setText(pass_nueva2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbl_contrasenaActual;
    private javax.swing.JLabel lbl_nuevaContrasena;
    private javax.swing.JLabel lbl_nuevaContrasena2;
    private javax.swing.JPanel panelAdministrar_titulo;
    private javax.swing.JPasswordField pass_anterior;
    private javax.swing.JPasswordField pass_nueva;
    private javax.swing.JPasswordField pass_nueva2;
    // End of variables declaration//GEN-END:variables

}
