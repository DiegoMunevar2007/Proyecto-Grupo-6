package lprs.interfaz.profesor.manejaractividad.crear;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelEncuesta extends JPanel {
    private int cantidadPreguntas;
    private JTextArea txtPregunta;
    public PanelEncuesta() {
        cantidadPreguntas = 0;
        setLayout(new BorderLayout());
        JLabel lblPregunta = new JLabel("Pregunta:" + cantidadPreguntas, SwingConstants.CENTER);
        add(lblPregunta, BorderLayout.NORTH);
        JLabel lblTextoPregunta = new JLabel("Texto de la pregunta:");
        add(lblTextoPregunta, BorderLayout.WEST);
        txtPregunta = new JTextArea();
        add(txtPregunta, BorderLayout.CENTER);
    }
}
