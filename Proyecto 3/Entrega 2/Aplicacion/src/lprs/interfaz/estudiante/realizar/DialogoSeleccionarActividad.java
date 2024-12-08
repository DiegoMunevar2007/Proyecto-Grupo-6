package lprs.interfaz.estudiante.realizar;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeleccionarActividad extends JDialog implements ActionListener {
    private InicioEstudiante inicioEstudiante;
    private JComboBox comboBoxLP;
    private JComboBox comboBoxActividades;

    public DialogoSeleccionarActividad(InicioEstudiante inicioEstudiante) {
        setTitle("Realizar Actividad");
        setSize(300, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        this.inicioEstudiante = inicioEstudiante;

        JPanel panelComboBox = new JPanel(new GridLayout(2, 1, 10, 10));
        panelComboBox.setBorder(new EmptyBorder(20, 20, 20, 20));

        comboBoxLP = new JComboBox<>();
        comboBoxLP.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxLP.setBackground(new Color(70, 130, 180));
        comboBoxLP.setForeground(Color.WHITE);
        comboBoxLP.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioEstudiante.getEstudiante().getLearningPathsInscritos()) {
            comboBoxLP.addItem(lp);
        }
        comboBoxLP.addActionListener(this);
        panelComboBox.add(comboBoxLP);

        comboBoxActividades = new JComboBox<>();
        comboBoxActividades.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxActividades.setBackground(new Color(70, 130, 180));
        comboBoxActividades.setForeground(Color.WHITE);
        comboBoxActividades.addItem("Seleccione una actividad");
        comboBoxActividades.addActionListener(this);
        panelComboBox.add(comboBoxActividades);

        add(panelComboBox, BorderLayout.WEST);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonContinuar.setBackground(new Color(34, 139, 34));
        botonContinuar.setForeground(Color.WHITE);
        botonContinuar.setBorderPainted(false);
        botonContinuar.addActionListener(this);
        panelBotones.add(botonContinuar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonCancelar.setBackground(new Color(220, 20, 60));
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setBorderPainted(false);
        botonCancelar.addActionListener(this);
        panelBotones.add(botonCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLP) {
            comboBoxActividades.removeAllItems();
            comboBoxActividades.addItem("Seleccione una actividad");
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
            // No action needed for now
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