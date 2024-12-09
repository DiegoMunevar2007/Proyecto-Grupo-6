package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoDiagrama extends JDialog implements ActionListener {
    private LearningPath lp;
    private int anio;
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
    private JButton btnVolver;

    public DialogoDiagrama(DialogoManejarSeguimiento dialogoManejarSeguimiento, LearningPath lp, int anio) {
        setLayout(new BorderLayout());
        setSize(900, 900);
        setLocationRelativeTo(null);
        setTitle("Diagrama del Learning Path");

        JLabel label = new JLabel("Diagrama del Learning Path: " + lp.getTitulo() + " del año " + anio, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(label, BorderLayout.NORTH);

        this.lp = lp;
        this.anio = anio;
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;

        JPanel panelDiagrama = new JPanel();
        panelDiagrama.setLayout(new GridLayout(4, 3, 10, 10));
        panelDiagrama.setBorder(new EmptyBorder(20, 20, 20, 20));
        for (int i = 1; i <= 12; i++) {
            PanelDiagramaMes panelDiagramaMes = new PanelDiagramaMes(i, anio, lp);
            panelDiagrama.add(panelDiagramaMes);
        }
        add(panelDiagrama, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVolver.setBackground(new Color(70, 130, 180));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorderPainted(false);
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
            dialogoManejarSeguimiento.setVisible(true);
        }
    }
}