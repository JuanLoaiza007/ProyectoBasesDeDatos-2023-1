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

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
        
        // Agregar el JScrollPane a la ventana
        add(scrollPane);
        
        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        
        // Ajustar el tamaño de la ventana al contenido
        pack();
    }
}

