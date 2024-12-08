package lprs.interfaz.profesor.seguimiento;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.seguimiento.LearningPath.DialogoSeguimientoLP;
import lprs.interfaz.profesor.seguimiento.diagrama.DialogoSeleccionarLPAnio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoManejarSeguimiento extends JDialog implements ActionListener {

    private JButton btnVerLps;
    private JButton btnVerPendientes;
    private JButton btnVerDiagrama;
    private JButton btnVolver;
    private InicioProfesor inicioProfesor;

    public DialogoManejarSeguimiento(InicioProfesor inicioProfesor) {
        this.inicioProfesor = inicioProfesor;
        setTitle("Manejar Seguimiento");
        setSize(300, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 20, 20));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnVerLps = new JButton("Ver Learning Paths");
        btnVerLps.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerLps.setBackground(new Color(70, 130, 180));
        btnVerLps.setForeground(Color.WHITE);
        btnVerLps.setBorderPainted(false);
        btnVerLps.addActionListener(this);
        panelBotones.add(btnVerLps);

        btnVerPendientes = new JButton("Ver Pendientes");
        btnVerPendientes.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerPendientes.setBackground(new Color(34, 139, 34));
        btnVerPendientes.setForeground(Color.WHITE);
        btnVerPendientes.setBorderPainted(false);
        btnVerPendientes.addActionListener(this);
        panelBotones.add(btnVerPendientes);

        btnVerDiagrama = new JButton("Ver Diagrama");
        btnVerDiagrama.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerDiagrama.setBackground(new Color(255, 165, 0));
        btnVerDiagrama.setForeground(Color.WHITE);
        btnVerDiagrama.setBorderPainted(false);
        btnVerDiagrama.addActionListener(this);
        panelBotones.add(btnVerDiagrama);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVolver.setBackground(new Color(220, 20, 60));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorderPainted(false);
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
            inicioProfesor.setVisible(true);
        } else if (e.getSource() == btnVerLps) {
            DialogoSeguimientoLP dialogoSeguimientoLP = new DialogoSeguimientoLP(this);
            dialogoSeguimientoLP.setVisible(true);
        } else if (e.getSource() == btnVerPendientes) {
            DialogoPendientes dialogoPendientes = new DialogoPendientes(this);
            dialogoPendientes.setVisible(true);
        } else if (e.getSource() == btnVerDiagrama) {
            DialogoSeleccionarLPAnio dialogoSeleccionarLPAnio = new DialogoSeleccionarLPAnio(this);
            dialogoSeleccionarLPAnio.setVisible(true);
        }
    }

    public InicioProfesor getInicioProfesor() {
        return inicioProfesor;
    }
}