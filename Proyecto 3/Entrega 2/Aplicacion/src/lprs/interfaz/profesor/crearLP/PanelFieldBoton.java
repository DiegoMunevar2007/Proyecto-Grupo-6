package lprs.interfaz.profesor.crearLP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFieldBoton extends JPanel  {

    private JTextField text;
    private JButton boton;

    public PanelFieldBoton(String textoBoton, String textoField) {
        setLayout(new BorderLayout());
        text = new JTextField();
        add(text, BorderLayout.CENTER);
        boton = new JButton(textoBoton);
        add(boton, BorderLayout.SOUTH);
    }
    public String getText() {
        return text.getText();
    }
    public JButton getBoton() {
        return boton;
    }

}
