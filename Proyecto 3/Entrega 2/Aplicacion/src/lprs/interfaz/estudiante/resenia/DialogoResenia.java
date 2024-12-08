package lprs.interfaz.estudiante.resenia;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.interfaz.estudiante.PanelComboBox;
import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Resenia;
import lprs.logica.cuentas.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private DialogoManejarActividad dialogoManejarActividad;

    public DialogoResenia(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        initialize();
    }

    public DialogoResenia(DialogoManejarActividad dialogoManejarActividad) {
        this.dialogoManejarActividad = dialogoManejarActividad;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        setTitle("Reseña de Learning Path");
        setLocationRelativeTo(null);
        setSize(600, 300);

        panelComboBox = new PanelComboBox(inicioEstudiante);
        add(panelComboBox, BorderLayout.WEST);

        panelResenia = new JPanel(new GridLayout(2, 2, 10, 10));
        panelResenia.setBorder(new EmptyBorder(20, 20, 20, 20));
        labelResenia = new JLabel("Reseña:");
        labelResenia.setFont(new Font("Arial", Font.BOLD, 16));
        panelResenia.add(labelResenia);
        textAreaResenia = new JTextArea();
        textAreaResenia.setFont(new Font("Arial", Font.PLAIN, 14));
        panelResenia.add(textAreaResenia);
        panelResenia.add(new JLabel(""));

        slider = new JSlider(1, 5);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panelResenia.add(slider);

        add(panelResenia, BorderLayout.CENTER);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonEnviar.setBackground(new Color(34, 139, 34));
        botonEnviar.setForeground(Color.WHITE);
        botonEnviar.setBorderPainted(false);
        botonEnviar.addActionListener(this);
        panelBotones.add(botonEnviar);

        botonCancelar = new JButton("Cancelar");
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
        if (e.getSource() == botonEnviar) {
            if (panelComboBox.getComboBoxActividades().getSelectedItem().toString().equals("Seleccione una actividad")) {
                JOptionPane.showMessageDialog(this, "Seleccione una actividad");
                return;
            }
            if (textAreaResenia.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ingrese una reseña");
                return;
            }
            Usuario usuario = dialogoManejarActividad != null ? dialogoManejarActividad.getInicioProfesor().getProfesor() : inicioEstudiante.getEstudiante();
            Resenia resenia = new Resenia(usuario, textAreaResenia.getText(), slider.getValue());
            Actividad actividad = panelComboBox.getActividad(panelComboBox.getComboBoxActividades().getSelectedItem().toString());
            try {
                actividad.addResenia(resenia);
                JOptionPane.showMessageDialog(this, "Reseña enviada con éxito");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (e.getSource() == botonCancelar) {
            dispose();
            if (dialogoManejarActividad != null) {
                dialogoManejarActividad.setVisible(true);
            } else {
                inicioEstudiante.setVisible(true);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        labelResenia.setText("Calificación: " + slider.getValue());
    }
}