package lprs.interfaz.estudiante.realizar;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeleccionarActividad extends JDialog implements ActionListener {
    private InicioEstudiante inicioEstudiante;
    private JComboBox comboBoxActividades;
    private JComboBox comboBoxLP;

    public DialogoSeleccionarActividad(InicioEstudiante inicioEstudiante) {
        setTitle("Realizar Actividad");
        setSize(700, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        this.inicioEstudiante = inicioEstudiante;
        JPanel panelComboBox = new JPanel();
        comboBoxActividades = new JComboBox();
        comboBoxLP = new JComboBox();
        comboBoxLP.addItem("Seleccione un Learning Path");
        for (lprs.logica.learningPath.LearningPath lp : inicioEstudiante.getEstudiante().getLearningPathsInscritos()) {
            comboBoxLP.addItem(lp);
        }
        comboBoxLP.addActionListener(this);
        panelComboBox.add(comboBoxLP);
        comboBoxActividades.addItem("Seleccione una actividad");
        comboBoxActividades.addActionListener(this);
        panelComboBox.add(comboBoxActividades);
        add(panelComboBox, BorderLayout.WEST);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(this);
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(this);
        panelBotones.add(botonContinuar);
        panelBotones.add(botonCancelar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLP) {
            comboBoxActividades.removeAllItems();
            if (comboBoxLP.getSelectedIndex() == 0) {
                return;
            }
            LearningPath lp = (LearningPath) comboBoxLP.getSelectedItem();
            if (lp != null) {
                for (Actividad actividad : lp.getActividades()) {
                    comboBoxActividades.addItem(actividad);
                }
            }
        } else if (e.getSource() == comboBoxActividades) {
            Actividad actividad = (Actividad) comboBoxActividades.getSelectedItem();
        } else if (e.getActionCommand().equals("Continuar")) {
            Actividad actividad = (Actividad) comboBoxActividades.getSelectedItem();
            if (actividad != null) {
                DialogoRealizarActividad dialogoRealizarActividad = new DialogoRealizarActividad(actividad, inicioEstudiante);
                dialogoRealizarActividad.setVisible(true);
                this.dispose();
            }
        } else if (e.getActionCommand().equals("Cancelar")) {
            this.dispose();
            inicioEstudiante.setVisible(true);
        }
    }
}
