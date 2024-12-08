package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.interfaz.estudiante.avance.learningpath.PanelAvanceLP;
import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoAvanceLPProfesor extends JDialog implements ActionListener {

    private PanelAvanceLP panelAvanceLP;
    private PanelLPYEstudiante panelLPYEstudiante;
    private JButton btnVolver;
    private DialogoSeguimientoLP dialogoManejarSeguimiento;

    public DialogoAvanceLPProfesor(DialogoSeguimientoLP dialogoManejarSeguimiento) {
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;
        setTitle("Avance del Learning Path");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelAvanceLP = new PanelAvanceLP();
        panelLPYEstudiante = new PanelLPYEstudiante(dialogoManejarSeguimiento.getProfesor());
        panelLPYEstudiante.getEstudiantes().addActionListener(this);
        add(panelLPYEstudiante, BorderLayout.WEST);
        add(panelAvanceLP, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVolver.setBackground(new Color(220, 20, 60));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorderPainted(false);
        btnVolver.addActionListener(this);

        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
        } else if (e.getSource() == panelLPYEstudiante.getEstudiantes()) {
            LearningPath lp = (LearningPath) panelLPYEstudiante.getLearningPaths().getSelectedItem();
            Estudiante estudiante = (Estudiante) panelLPYEstudiante.getEstudiantes().getSelectedItem();
            panelAvanceLP.cambiarInformacion(estudiante.getAvance(lp.getID()));
        }
    }
}