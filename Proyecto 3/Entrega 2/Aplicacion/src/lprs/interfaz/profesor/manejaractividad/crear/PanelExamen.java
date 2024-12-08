package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Examen;

import javax.swing.*;
import java.awt.*;

public class PanelExamen extends JPanel {
    private JTextField txtPregunta;
    private JTextField txtRespuesta;
    private Examen examen;
    private int cantidadPreguntas;
    public PanelExamen() {
        setLayout(new GridLayout(2,2, 10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        add(lblPregunta);
        txtPregunta = new JTextField();
        add(txtPregunta);
        JLabel lblRespuesta = new JLabel("Respuesta:");
        add(lblRespuesta);
        txtRespuesta = new JTextField();
        add(txtRespuesta);
    }
    public PanelExamen(Examen examen){
        this.examen = examen;
        cantidadPreguntas = 0;
        setLayout(new GridLayout(2,2, 10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        add(lblPregunta);
        txtPregunta = new JTextField(examen.getPreguntasExamen().get(0).getEnunciado());
        add(txtPregunta);
        JLabel lblRespuesta = new JLabel("Respuesta:");
        add(lblRespuesta);
        txtRespuesta = new JTextField();
        add(txtRespuesta);
    }
}
