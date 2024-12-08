package lprs.interfaz.profesor.manejaractividad;

import lprs.interfaz.estudiante.resenia.DialogoResenia;
import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoCrearActividad;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoSeleccionarLP;
import lprs.interfaz.profesor.manejaractividad.crear.PanelActividadBasica;
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
        setSize(600,600);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Manejar Actividad");
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 80, 80));
        panelBotones.setBorder(new EmptyBorder(60, 60, 60, 60));
        botonVerActividades = new JButton("Ver/Editar/Eliminar Actividades");
        botonVerActividades.addActionListener(this);
        panelBotones.add(botonVerActividades);

        botonCrearActividad = new JButton("Crear Actividad");
        botonCrearActividad.addActionListener(this);
        panelBotones.add(botonCrearActividad);

        botonReseniarActividad = new JButton("Reseñar Actividad");
        botonReseniarActividad.addActionListener(this);
        panelBotones.add(botonReseniarActividad);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(this);
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
