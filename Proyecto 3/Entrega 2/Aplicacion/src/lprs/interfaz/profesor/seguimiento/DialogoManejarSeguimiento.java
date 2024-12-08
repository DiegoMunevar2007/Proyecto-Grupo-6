package lprs.interfaz.profesor.seguimiento;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.interfaz.profesor.seguimiento.LearningPath.DialogoSeguimientoLP;

import javax.swing.*;
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
        setSize(300, 300);
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1,40,40));
        btnVerLps = new JButton("Ver Learning Paths");
        btnVerLps.setBounds(10, 10, 200, 20);
        btnVerLps.addActionListener(this);
        panelBotones.add(btnVerLps);
        btnVerPendientes = new JButton("Ver Pendientes");
        btnVerPendientes.setBounds(10, 40, 200, 20);
        btnVerPendientes.addActionListener(this);
        panelBotones.add(btnVerPendientes);
        btnVerDiagrama = new JButton("Ver Diagrama");
        btnVerDiagrama.setBounds(10, 70, 200, 20);
        btnVerDiagrama.addActionListener(this);
        panelBotones.add(btnVerDiagrama);
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 100, 200, 20);
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
        }

    }
    public InicioProfesor getInicioProfesor(){
        return inicioProfesor;
    }
}
