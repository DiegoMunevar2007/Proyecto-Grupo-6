package lprs.interfaz.estudiante.avance.actividad;

import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.contenido.realizable.ExamenRealizable;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelEncuesta extends PanelActividad implements ActionListener {
    private JLabel lblPregunta;
    private JLabel lblRespuesta;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private ArrayList<PreguntaAbiertaRealizable> preguntas;
    private int preguntaActual;
    public PanelEncuesta(EncuestaRealizable actividad) {
        super(actividad.getActividadBase());
        preguntaActual = 0;
        preguntas = actividad.getPreguntasRealizadas();

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAnterior) {
            if (preguntaActual > 0) {
                preguntaActual--;
                PreguntaAbiertaRealizable pregunta = preguntas.get(preguntaActual);
                lblPregunta.setText(pregunta.getPreguntaBase().getEnunciado());
                lblRespuesta.setText(pregunta.getRespuesta());
            }
        } else if (e.getSource() == btnSiguiente) {
            if (preguntaActual < preguntas.size() - 1) {
                preguntaActual++;
                PreguntaAbiertaRealizable pregunta = preguntas.get(preguntaActual);
                lblPregunta.setText(pregunta.getPreguntaBase().getEnunciado());
                lblRespuesta.setText(pregunta.getRespuesta());
            }
        }
    }
}
