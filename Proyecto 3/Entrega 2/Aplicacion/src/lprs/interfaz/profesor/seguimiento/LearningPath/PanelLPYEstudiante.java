package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLPYEstudiante extends JPanel implements ActionListener {

    private JComboBox learningPaths;
    private JComboBox estudiantes;
    private Profesor profesor;
    public PanelLPYEstudiante(Profesor profesor) {
        this.profesor = profesor;
        setLayout(new BorderLayout(10,50));
        learningPaths = new JComboBox<>();
        learningPaths.setBounds(10, 10, 200, 20);
        learningPaths.addActionListener(this);
        learningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath learningPath : profesor.getLearningPathsCreadosLista()) {
            learningPaths.addItem(learningPath);
        }
        estudiantes = new JComboBox<>();
        estudiantes.setBounds(10, 40, 200, 20);
        estudiantes.addItem("Seleccione un estudiante");
        add(learningPaths, BorderLayout.NORTH);
        add(estudiantes, BorderLayout.CENTER);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == learningPaths) {
            if (!"Seleccione un Learning Path".equals(learningPaths.getSelectedItem())) {
                LearningPath lp = (LearningPath) learningPaths.getSelectedItem();;
                for (Estudiante estudiante : lp.getEstudiantesInscritos()) {
                    estudiantes.addItem(estudiante);
                }
            }
        }
    }

    public JComboBox getLearningPaths() {
        return learningPaths;
    }

    public JComboBox getEstudiantes() {
        return estudiantes;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}
