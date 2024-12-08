package lprs.interfaz.profesor.learningpath;

import lprs.interfaz.profesor.InicioProfesor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoLP extends JDialog implements ActionListener {

    private JButton botonManejarLearningPath;
    private InicioProfesor inicioProfesor;
    public DialogoLP(InicioProfesor inicioProfesor) {
        this.inicioProfesor = inicioProfesor;
        botonManejarLearningPath = new JButton("Manejar Learning Path");
        botonManejarLearningPath.addActionListener(this);
        add(botonManejarLearningPath);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonManejarLearningPath) {
            DialogoManejarLPCreado dialogoManejarLPCreado = new DialogoManejarLPCreado(inicioProfesor);
            dialogoManejarLPCreado.setVisible(true);
        }
    }
}
