package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.cuentas.Estudiante;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class PanelEncuesta extends JPanel {
    private int cantidadPreguntas;
    private JTextArea txtPregunta;
    private ArrayList<PreguntaAbierta> preguntas;
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
    public PanelEncuesta(Encuesta encuesta){
        preguntas = encuesta.getPreguntasEncuesta();
        setLayout(new BorderLayout());
        JLabel lblPregunta = new JLabel("Pregunta:" + cantidadPreguntas, SwingConstants.CENTER);
        add(lblPregunta, BorderLayout.NORTH);
        JLabel lblTextoPregunta = new JLabel("Texto de la pregunta:");
        add(lblTextoPregunta, BorderLayout.WEST);
        txtPregunta = new JTextArea(preguntas.get(0).getEnunciado());
        add(txtPregunta, BorderLayout.CENTER);
    }


    public void agregarPregunta(){
        cantidadPreguntas++;
        JLabel lblPregunta = new JLabel("Pregunta:" + cantidadPreguntas, SwingConstants.CENTER);
        add(lblPregunta, BorderLayout.NORTH);
        JLabel lblTextoPregunta = new JLabel("Texto de la pregunta:");
        add(lblTextoPregunta, BorderLayout.WEST);
        txtPregunta = new JTextArea();
        add(txtPregunta, BorderLayout.CENTER);
    }
}
