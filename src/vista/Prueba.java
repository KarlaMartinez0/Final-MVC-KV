/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author g.perezmoreno
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prueba extends JFrame {
    
    public static void main(String[] args) {
        
        // () -> {} Funcion Lambda
        
        SwingUtilities.invokeLater(() -> {
            new Prueba();
        });
    }
    

    JButton boton;

    public Prueba() {
        boton = new JButton("Pulsa!");
        add(boton);
        boton.addActionListener(new OyenteBoton());
        setSize(100, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
class OyenteBoton implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola hiciste click al boton!");
        Toolkit.getDefaultToolkit().beep();
    }
}
