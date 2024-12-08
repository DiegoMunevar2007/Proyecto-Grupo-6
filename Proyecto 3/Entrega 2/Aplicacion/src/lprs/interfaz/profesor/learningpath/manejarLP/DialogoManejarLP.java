package lprs.interfaz.profesor.learningpath.manejarLP;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.learningpath.manejarLP.clonarLP.DialogoClonarLP;
import lprs.interfaz.profesor.learningpath.manejarLP.crearLP.DialogoCrearLP;
import lprs.interfaz.profesor.learningpath.manejarLP.lpsCreados.DialogoLpCreado;
import lprs.principal.LPRS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoManejarLP extends JDialog implements ActionListener {
    private JButton botonVerCreados;
    private JButton botonVerDisponibles;
    private JButton botonCrear;
    private JButton botonSalir;
    private InicioProfesor inicioProfesor;

    public DialogoManejarLP(InicioProfesor inicioProfesor) {
        this.inicioProfesor = inicioProfesor;
        setTitle("Manejar Learning Paths");
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Manejar Learning Paths", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(new EmptyBorder(50, 50, 50, 50));

        botonVerCreados = new JButton("Ver Learning Paths Creados");
        botonVerCreados.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVerCreados.setBackground(new Color(70, 130, 180));
        botonVerCreados.setForeground(Color.WHITE);
        botonVerCreados.setBorderPainted(false);
        botonVerCreados.addActionListener(this);
        panelBotones.add(botonVerCreados);

        botonVerDisponibles = new JButton("Ver Learning Paths Disponibles");
        botonVerDisponibles.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVerDisponibles.setBackground(new Color(70, 130, 180));
        botonVerDisponibles.setForeground(Color.WHITE);
        botonVerDisponibles.setBorderPainted(false);
        botonVerDisponibles.addActionListener(this);
        panelBotones.add(botonVerDisponibles);

        botonCrear = new JButton("Crear Learning Path");
        botonCrear.setFont(new Font("Arial", Font.PLAIN, 16));
        botonCrear.setBackground(new Color(34, 139, 34));
        botonCrear.setForeground(Color.WHITE);
        botonCrear.setBorderPainted(false);
        botonCrear.addActionListener(this);
        panelBotones.add(botonCrear);

        botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 16));
        botonSalir.setBackground(new Color(220, 20, 60));
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBorderPainted(false);
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
            inicioProfesor.setVisible(true);
        }
    }

    public LPRS getLprs() {
        return inicioProfesor.getProfesor().getLprsActual();
    }

    public InicioProfesor getInicioProfesor() {
        return inicioProfesor;
    }
}