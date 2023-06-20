package Paneles;

/**
 * Bases de datos 750006C-01
 * Proyecto de curso
 * Profesor: Oswaldo Solarte
 * 
 * Archivo: MiniVentana.java
 * Licencia: GNU-GPL
 * @version 1.0
 * 
 * @author Alejandro Guerrero Cano      (202179652-3743) {@literal <"alejandro.cano@correounivalle.edu.co">} 
 * @author Juan David Loaiza Santiago   (202177570-3743) {@literal <"juan.loaiza.santiago@correounivalle.edu.co">} 
 * @author Juan Sebastian Muñoz Rojas   (202177436-3743) {@literal <"juan.munoz.rojas@correounivalle.edu.co">} 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Funcion que automatiza el proceso de solicitar JFrames para mostrar texto en
 * una miniventana que se superpone a la pantalla
 * @author ihuntgore
 */
public class MiniVentana extends JDialog {

    public MiniVentana(String titulo, String texto) {

        // Configurar propiedades de la ventana
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(titulo);

        // Crear un JTextArea para mostrar el texto y agregarlo a un JScrollPane
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 100)); // Tamaño fijo de la ventana

        // Crear un JPanel para el panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        // Crear un JLabel con el texto "Presione Ctrl + C"
        JLabel mensajeLabel = new JLabel("Seleccione y presione Ctrl + C para copiar el contenido");
        panelInferior.add(mensajeLabel);

        // Crear un contenedor para agregar el JScrollPane y el panel inferior
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Ajustar el tamaño de la ventana al contenido
        pack();
    }
}