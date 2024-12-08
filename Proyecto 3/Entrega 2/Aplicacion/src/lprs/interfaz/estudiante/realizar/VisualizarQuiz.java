package lprs.interfaz.estudiante.realizar;

import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.PreguntaCerradaRealizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
public class VisualizarQuiz extends JPanel implements ActionListener {
    private ArrayList<PreguntaCerrada> preguntaCerradas;
    private ArrayList<PreguntaCerradaRealizable> preguntaCerradaRealizables;
    private JRadioButton[] opciones;
    private int cantidadPreguntasVistas;
    private int cantidadPreguntas;
    private JButton btnSiguiente;
    private JTextField txtPregunta;

    public VisualizarQuiz(ArrayList<PreguntaCerrada> preguntaCerradas){

        this.preguntaCerradas = preguntaCerradas;
        this.preguntaCerradaRealizables = new ArrayList<>();
        this.cantidadPreguntasVistas = 0;
        this.cantidadPreguntas = preguntaCerradas.size();
        txtPregunta = new JTextField();
        setLayout(new BorderLayout());
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(this);
    }

    public void mostrarPregunta(){
        removeAll();
        PreguntaCerrada preguntaCerrada = preguntaCerradas.get(cantidadPreguntasVistas);
        txtPregunta.setText(preguntaCerrada.getEnunciado());
        add(txtPregunta, BorderLayout.NORTH);
        opciones = new JRadioButton[preguntaCerrada.getOpciones().length];
        ButtonGroup grupo = new ButtonGroup();
        JPanel panelOpciones = new JPanel(new GridLayout(preguntaCerrada.getOpciones().length, 1));
        for (int i = 0; i < preguntaCerrada.getOpciones().length; i++) {
            opciones[i] = new JRadioButton(preguntaCerrada.getOpciones()[i].getOpcion());
            grupo.add(opciones[i]);
            panelOpciones.add(opciones[i]);
        }
        add(panelOpciones, BorderLayout.CENTER);
        btnSiguiente = new JButton("Siguiente");
        add(btnSiguiente, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSiguiente) {
            cantidadPreguntasVistas++;
            if (cantidadPreguntasVistas < cantidadPreguntas) {
                mostrarPregunta();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz finalizado, no hay más preguntas");
            }
        }
    }
    public ArrayList getPreguntasRealizadas() {
        return preguntaCerradaRealizables;
    }
}
