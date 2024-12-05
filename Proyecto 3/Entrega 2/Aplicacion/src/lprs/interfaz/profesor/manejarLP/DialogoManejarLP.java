package lprs.interfaz.profesor.manejarLP;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.manejarLP.clonarLP.DialogoClonarLP;
import lprs.interfaz.profesor.manejarLP.crearLP.DialogoCrearLP;
import lprs.interfaz.profesor.manejarLP.lpsCreados.DialogoLpCreado;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DialogoManejarLP extends JDialog implements ActionListener {
    private JButton botonVerCreados;
    private JButton botonVerDisponibles;
    private JButton botonCrear;
    private JButton botonSalir;
    private InicioProfesor inicioProfesor;

    public DialogoManejarLP(InicioProfesor inicioProfesor) {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.inicioProfesor = inicioProfesor;

        JLabel lblTitulo = new JLabel("Manejar Learning Paths", SwingConstants.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 80,80));
        botonVerCreados = new JButton("Ver Learning Paths Creados");
        botonVerCreados.addActionListener(this);
        panelBotones.add(botonVerCreados);

        botonVerDisponibles = new JButton("Ver Learning Paths Disponibles");
        botonVerDisponibles.addActionListener(this);
        panelBotones.add(botonVerDisponibles);

        botonCrear = new JButton("Crear Learning Path");
        botonCrear.addActionListener(this);
        panelBotones.add(botonCrear);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        panelBotones.add(botonSalir);
        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVerCreados) {
            DialogoLpCreado dialogoLpCreado = new DialogoLpCreado(this);
            dialogoLpCreado.setVisible(true);

        } else if (e.getSource() == botonVerDisponibles) {
            DialogoClonarLP dialogoClonarLP = new DialogoClonarLP(this);
            dialogoClonarLP.setVisible(true);

        } else if (e.getSource() == botonCrear) {
            DialogoCrearLP dialogoCrearLP = new DialogoCrearLP(this, inicioProfesor.getProfesor());
            dialogoCrearLP.setVisible(true);

        } else if (e.getSource() == botonSalir) {
            dispose();
        }
    }
    public LPRS getLprs() {
        return inicioProfesor.getProfesor().getLprsActual();
    }
    public InicioProfesor getInicioProfesor() {
        return inicioProfesor;
    }
}
