package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.interfaz.estudiante.avance.learningpath.DialogoAvanceLP;
import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.cuentas.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeguimientoLP extends JDialog implements ActionListener {

    private JButton btnVerActividadesEstudiante;
    private JButton btnVerAvanceEstudiante;
    private JButton btnVolver;
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;

    public DialogoSeguimientoLP(DialogoManejarSeguimiento dialogoManejarSeguimiento) {
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;
        setTitle("Seguimiento de learning paths");
        setSize(300, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 20, 20));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnVerActividadesEstudiante = new JButton("Ver las actividades de un estudiante");
        btnVerActividadesEstudiante.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerActividadesEstudiante.setBackground(new Color(70, 130, 180));
        btnVerActividadesEstudiante.setForeground(Color.WHITE);
        btnVerActividadesEstudiante.setBorderPainted(false);
        btnVerActividadesEstudiante.addActionListener(this);
        panelBotones.add(btnVerActividadesEstudiante);

        btnVerAvanceEstudiante = new JButton("Ver el avance de un estudiante dentro de un Learning Path");
        btnVerAvanceEstudiante.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerAvanceEstudiante.setBackground(new Color(34, 139, 34));
        btnVerAvanceEstudiante.setForeground(Color.WHITE);
        btnVerAvanceEstudiante.setBorderPainted(false);
        btnVerAvanceEstudiante.addActionListener(this);
        panelBotones.add(btnVerAvanceEstudiante);

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
            dialogoManejarSeguimiento.setVisible(true);
        } else if (e.getSource() == btnVerActividadesEstudiante) {
            this.dispose();
            DialogoActividadesEstudiante dialogoActividadesEstudiante = new DialogoActividadesEstudiante(this);
            dialogoActividadesEstudiante.setVisible(true);
        } else if (e.getSource() == btnVerAvanceEstudiante) {
            DialogoAvanceLPProfesor dialogoAvanceLP = new DialogoAvanceLPProfesor(this);
            dialogoAvanceLP.setVisible(true);
        }
    }

    public Profesor getProfesor() {
        return dialogoManejarSeguimiento.getInicioProfesor().getProfesor();
    }
}