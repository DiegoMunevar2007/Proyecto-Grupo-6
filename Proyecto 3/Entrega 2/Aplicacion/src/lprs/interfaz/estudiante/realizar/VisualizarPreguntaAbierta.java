package lprs.interfaz.estudiante.realizar;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VisualizarPreguntaAbierta extends JPanel implements ActionListener {
    private ArrayList<PreguntaAbierta> preguntasAbiertas;
    private ArrayList<PreguntaAbiertaRealizable> preguntasAbiertasRealizables;
    private JTextField txtRespuesta;
    private int cantidadPreguntasVistas;
    private int cantidadPreguntas;
    private JButton btnSiguiente;
    private JTextField txtPregunta;

    public VisualizarPreguntaAbierta(ArrayList<PreguntaAbierta> preguntasAbiertas) {
        this.preguntasAbiertas = preguntasAbiertas;
        this.preguntasAbiertasRealizables = new ArrayList<>();
        this.cantidadPreguntasVistas = 0;
        this.cantidadPreguntas = preguntasAbiertas.size();
        txtPregunta = new JTextField();
        setLayout(new BorderLayout());
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);
        txtRespuesta = new JTextField();
        mostrarPregunta();
    }

    public void mostrarPregunta() {
        removeAll();
        PreguntaAbierta preguntaAbierta = preguntasAbiertas.get(cantidadPreguntasVistas);
        txtPregunta.setText(preguntaAbierta.getEnunciado());
        add(txtPregunta, BorderLayout.NORTH);
        add(txtRespuesta, BorderLayout.CENTER);
        add(btnSiguiente, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSiguiente) {
            PreguntaAbiertaRealizable preguntaRealizable = new PreguntaAbiertaRealizable(txtRespuesta.getText(), preguntasAbiertas.get(cantidadPreguntasVistas));
            preguntasAbiertasRealizables.add(preguntaRealizable);
            cantidadPreguntasVistas++;
            txtRespuesta.setText("");
            if (cantidadPreguntasVistas < cantidadPreguntas) {
                mostrarPregunta();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz finalizado, no hay más preguntas");
            }
        }
    }

    public ArrayList getPreguntasRealizadas() {
        return preguntasAbiertasRealizables;
    }
}