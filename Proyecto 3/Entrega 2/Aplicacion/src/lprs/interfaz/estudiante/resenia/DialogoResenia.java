package lprs.interfaz.estudiante.resenia;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.interfaz.estudiante.PanelComboBox;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Resenia;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoResenia extends JDialog implements ActionListener, ChangeListener {
    private InicioEstudiante inicioEstudiante;
    private PanelComboBox panelComboBox;
    private JSlider slider;
    private JPanel panelResenia;
    private JTextArea textAreaResenia;
    private JLabel labelResenia;
    private JPanel panelBotones;
    private JButton botonEnviar;
    private JButton botonCancelar;

    public DialogoResenia(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        setLayout(new BorderLayout());
        panelComboBox = new PanelComboBox(inicioEstudiante);
        add(panelComboBox, BorderLayout.WEST);
        panelResenia = new JPanel();
        panelResenia.setLayout(new GridLayout(2, 2));
        labelResenia = new JLabel("Reseña:");
        panelResenia.add(labelResenia);
        textAreaResenia = new JTextArea();
        panelResenia.add(textAreaResenia);
        panelResenia.add(new JLabel(""));

        slider = new JSlider(1,5);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panelResenia.add(slider);

        add(panelResenia, BorderLayout.CENTER);
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(this);
        panelBotones.add(botonEnviar);
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        panelBotones.add(botonCancelar);

        add(panelBotones, BorderLayout.SOUTH);
        setTitle("Reseña de Learning Path");
        setLocationRelativeTo(null);
        setSize(600, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEnviar) {
            Resenia resenia = new Resenia(inicioEstudiante.getEstudiante(),textAreaResenia.getText(),slider.getValue());
            Actividad actividad = panelComboBox.getActividad(panelComboBox.getComboBoxActividades().getSelectedItem().toString());
            try {
                actividad.addResenia(resenia);
                JOptionPane.showMessageDialog(this, "Reseña enviada con éxito");
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == botonCancelar) {
            dispose();
            inicioEstudiante.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        labelResenia.setText("Calificación: " + slider.getValue());
    }
}
