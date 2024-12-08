package lprs.interfaz.profesor.manejaractividad;

import lprs.interfaz.estudiante.resenia.DialogoResenia;
import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoCrearActividad;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoSeleccionarLP;
import lprs.interfaz.profesor.manejaractividad.ver.DialogoVerActividad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoManejarActividad extends JDialog implements ActionListener {
    InicioProfesor inicioProfesor;
    JButton botonVerActividades;
    JButton botonCrearActividad;
    JButton botonReseniarActividad;
    JButton botonVolver;

    public DialogoManejarActividad(InicioProfesor inicioProfesor) {
        this.inicioProfesor = inicioProfesor;
        setSize(320, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        JLabel titulo = new JLabel("Manejar Actividad");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 20, 20)); // Reduce the gaps between buttons
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20)); // Reduce the border size

        botonVerActividades = new JButton("Ver/Editar/Eliminar Actividades");
        botonVerActividades.addActionListener(this);
        botonVerActividades.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVerActividades.setBackground(new Color(70, 130, 180));
        botonVerActividades.setForeground(Color.WHITE);
        botonVerActividades.setBorderPainted(false);
        panelBotones.add(botonVerActividades);

        botonCrearActividad = new JButton("Crear Actividad");
        botonCrearActividad.addActionListener(this);
        botonCrearActividad.setFont(new Font("Arial", Font.PLAIN, 16));
        botonCrearActividad.setBackground(new Color(34, 139, 34));
        botonCrearActividad.setForeground(Color.WHITE);
        botonCrearActividad.setBorderPainted(false);
        panelBotones.add(botonCrearActividad);

        botonReseniarActividad = new JButton("Reseñar Actividad");
        botonReseniarActividad.addActionListener(this);
        botonReseniarActividad.setFont(new Font("Arial", Font.PLAIN, 16));
        botonReseniarActividad.setBackground(new Color(255, 165, 0));
        botonReseniarActividad.setForeground(Color.WHITE);
        botonReseniarActividad.setBorderPainted(false);
        panelBotones.add(botonReseniarActividad);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(this);
        botonVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolver.setBackground(new Color(105, 105, 105));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setBorderPainted(false);
        panelBotones.add(botonVolver);

        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            this.dispose();
            inicioProfesor.setVisible(true);
        } else if (e.getSource() == botonCrearActividad) {
            DialogoSeleccionarLP dialogoSeleccionarLP = new DialogoSeleccionarLP(this);
            dialogoSeleccionarLP.setVisible(true);
            this.dispose();
        } else if (e.getSource() == botonVerActividades) {
            this.dispose();
            DialogoVerActividad dialogoVerActividad = new DialogoVerActividad(this);
            dialogoVerActividad.setVisible(true);
        } else if (e.getSource() == botonReseniarActividad) {
            this.dispose();
            DialogoResenia dialogoResenia = new DialogoResenia(this);
            dialogoResenia.setVisible(true);
        }
    }

    public InicioProfesor getInicioProfesor() {
        return inicioProfesor;
    }
}