package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.interfaz.estudiante.avance.learningpath.DialogoAvanceLP;
import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.cuentas.Profesor;

import javax.swing.*;
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

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 40, 40));

        btnVerActividadesEstudiante = new JButton("Ver las actividades de un estudiante");
        panelBotones.add(btnVerActividadesEstudiante);

        btnVerAvanceEstudiante = new JButton("Ver el avance de un estudiante dentro de un Learning Path");
        panelBotones.add(btnVerAvanceEstudiante);

        btnVolver = new JButton("Volver");
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.CENTER);

        btnVerActividadesEstudiante.addActionListener(this);
        btnVerAvanceEstudiante.addActionListener(this);
        btnVolver.addActionListener(this);
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

        }
    }
    public Profesor getProfesor(){
        return dialogoManejarSeguimiento.getInicioProfesor().getProfesor();
    }
}