package lprs.interfaz.profesor;

import lprs.interfaz.InterfazPrincipal;
import lprs.interfaz.profesor.learningpath.manejarLP.DialogoManejarLP;
import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.cuentas.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioProfesor extends JFrame implements ActionListener {
    private JButton botonManejarLearningPaths;
    private JButton botonManejarActividades;
    private JButton botonHacerSeguimiento;
    private JButton botonSalir;
    private Profesor usuarioIniciado;
    private InterfazPrincipal interfazPrincipal;
    public InicioProfesor(Profesor usuarioIniciado, InterfazPrincipal interfazPrincipal) {
        super("Inicio Profesor");
        this.interfazPrincipal = interfazPrincipal;
        this.usuarioIniciado = usuarioIniciado;
        setSize(800, 600);
        setLocationRelativeTo(interfazPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.add(new JLabel("Bienvenido " + usuarioIniciado.getUsuario(), SwingConstants.CENTER));
        panelTexto.add(new JLabel("Seleccione una opción:", SwingConstants.CENTER));
        add(panelTexto, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(120, 120, 120, 120));
        panelBotones.setLayout(new GridLayout(2, 2, 80,80));
        botonManejarLearningPaths = new JButton("Manejar Learning Paths");
        botonManejarLearningPaths.addActionListener(this);
        panelBotones.add(botonManejarLearningPaths);

        botonManejarActividades = new JButton("Manejar Actividades");
        botonManejarActividades.addActionListener(this);
        panelBotones.add(botonManejarActividades);

        botonHacerSeguimiento = new JButton("Hacer Seguimiento");
        botonHacerSeguimiento.addActionListener(this);
        panelBotones.add(botonHacerSeguimiento);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonManejarLearningPaths) {
            this.dispose();
            DialogoManejarLP dialogoManejarLP = new DialogoManejarLP(this);
            dialogoManejarLP.setVisible(true);

        } else if (e.getSource() == botonManejarActividades) {
            this.dispose();
            DialogoManejarActividad dialogoManejarActividad = new DialogoManejarActividad(this);
            dialogoManejarActividad.setVisible(true);
        } else if (e.getSource() == botonHacerSeguimiento) {
            this.dispose();
            DialogoManejarSeguimiento dialogoManejarSeguimiento = new DialogoManejarSeguimiento(this);
            dialogoManejarSeguimiento.setVisible(true);
        } else if (e.getSource() == botonSalir) {
            this.dispose();
            interfazPrincipal.changeDialog(interfazPrincipal);
        }
    }

    public Profesor getProfesor() {
        return usuarioIniciado;
    }
}
