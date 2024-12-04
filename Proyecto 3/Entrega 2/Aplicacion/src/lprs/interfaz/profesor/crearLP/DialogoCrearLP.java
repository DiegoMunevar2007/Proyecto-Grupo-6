package lprs.interfaz.profesor.crearLP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoCrearLP extends JDialog implements ActionListener {
    private PanelCrearLearningPath panelCrearLearningPath;
    private JButton botonCrear;
    private JButton botonSalir;
    public DialogoCrearLP() {
        setLayout(new BorderLayout());
        JLabel lblTitulo = new JLabel("Crear Learning Path", SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        panelCrearLearningPath = new PanelCrearLearningPath();
        add(panelCrearLearningPath, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        botonCrear = new JButton("Crear");
        botonCrear.addActionListener(this);
        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        panelBotones.add(botonCrear);
        panelBotones.add(botonSalir);
        add(panelBotones, BorderLayout.SOUTH);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==botonCrear) {
            // Crear Learning Path
            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente");
            dispose();
        } else if (e.getSource()==botonSalir) {
            dispose();
        }
    }
    public static void main(String[] args) {
        DialogoCrearLP dialogo = new DialogoCrearLP();
        dialogo.setVisible(true);
    }
}
