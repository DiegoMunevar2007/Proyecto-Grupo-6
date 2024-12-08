package lprs.interfaz.estudiante.avance.actividad;

import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.PreguntaCerradaRealizable;
import lprs.logica.contenido.realizable.QuizRealizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelQuiz extends PanelActividad implements ActionListener {
    private JLabel lblPregunta;
    private JLabel lblRespuesta;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JLabel lblCalificacion;
    private JTextField txtCalificacion;
    private ArrayList<PreguntaCerradaRealizable> preguntas;
    private int preguntaActual;
    public PanelQuiz(QuizRealizable quiz) {
        super(quiz.getActividadBase());
        preguntaActual = 0;
        preguntas = quiz.getPreguntas();

        setLayout(new GridLayout(5,2,30,30));
        add(new JLabel("Preguntas"));

        //Panel de pregunta y respuesta
        lblPregunta = new JLabel("Pregunta");
        lblRespuesta = new JLabel("Respuesta");

        JPanel panelAux = new JPanel();
        panelAux.setLayout(new GridLayout(3,1,30,30));
        panelAux.add(lblPregunta);
        panelAux.add(lblRespuesta);

        JPanel panelBotonesPreguntas = new JPanel();
        panelBotonesPreguntas.setLayout(new FlowLayout());
        btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(this);
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);

        panelBotonesPreguntas.add(btnAnterior);
        panelBotonesPreguntas.add(btnSiguiente);
        panelAux.add(panelBotonesPreguntas);

        add(panelAux);

        lblCalificacion = new JLabel("Calificacion");
        txtCalificacion = new JTextField(String.valueOf(quiz.getCalificacion()));
        txtCalificacion.setEditable(false);
        add(lblCalificacion);
        add(txtCalificacion);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAnterior) {
            if (preguntaActual > 0) {
                preguntaActual--;
                PreguntaCerradaRealizable pregunta = preguntas.get(preguntaActual);
                lblPregunta.setText(pregunta.getPreguntaBase().getEnunciado());
                lblRespuesta.setText(pregunta.getOpcionEscogida().getOpcion());
            }
        } else if (e.getSource() == btnSiguiente) {
            if (preguntaActual < preguntas.size() - 1) {
                preguntaActual++;
                PreguntaCerradaRealizable pregunta = preguntas.get(preguntaActual);
                lblPregunta.setText(pregunta.getPreguntaBase().getEnunciado());
                lblRespuesta.setText(pregunta.getOpcionEscogida().getOpcion());
            }
        }
    }
}
