package lprs.interfaz.profesor.manejaractividad.crear;

import javax.swing.*;
import java.awt.*;

public class PanelQuizVF extends JPanel  {
    private JTextField txtPregunta;
    private JComboBox<String> verdaderoFalso;

    public PanelQuizVF() {
        setLayout(new GridLayout(2,2, 10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        add(lblPregunta);
        txtPregunta = new JTextField();
        add(txtPregunta);
        JLabel lblRespuesta = new JLabel("Respuesta:");
        add(lblRespuesta);
        verdaderoFalso = new JComboBox<>();
        verdaderoFalso.addItem("Verdadero");
        verdaderoFalso.addItem("Falso");
        add(verdaderoFalso);
    }

}
