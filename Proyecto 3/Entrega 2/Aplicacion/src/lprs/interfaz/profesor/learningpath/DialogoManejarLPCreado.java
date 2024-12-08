package lprs.interfaz.profesor.learningpath;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DialogoManejarLPCreado extends JDialog implements ActionListener {
    private HashMap<String, LearningPath> learningPaths;
    private InicioProfesor inicioProfesor;
    private JComboBox<String> comboBox;
    private JButton botonEliminar;
    private JButton botonModificar;
    private JButton botonVolver;
    private PanelInfoLP panelInfoLP;
    public DialogoManejarLPCreado(InicioProfesor inicioProfesor) {
        this.inicioProfesor=inicioProfesor;
        learningPaths = new HashMap<String , LearningPath>();
        setSize(500,500);
        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new BorderLayout());
        panelComboBox.setBorder(new EmptyBorder(60, 60, 60, 60));
        panelComboBox.add(new JLabel("Seleccione un Learning Path:"), BorderLayout.NORTH);
        comboBox = new JComboBox<String>();
        comboBox.addActionListener(this);
        comboBox.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioProfesor.getProfesor().getLearningPathsCreados()) {
            comboBox.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
            learningPaths.put(lp.getTitulo() + " (" + lp.getID() + ")", lp);
        }
        panelComboBox.add(comboBox, BorderLayout.CENTER);
        add(panelComboBox, BorderLayout.WEST);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
        botonEliminar = new JButton("Eliminar");
        botonModificar = new JButton("Modificar");
        botonVolver = new JButton("Volver");
        botonEliminar.addActionListener(this);
        botonModificar.addActionListener(this);
        botonVolver.addActionListener(this);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonVolver);

        panelInfoLP = new PanelInfoLP(true);
        add(panelInfoLP, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            if (!"Seleccione un Learning Path".equals(comboBox.getSelectedItem())) {
                LearningPath lp = learningPaths.get(comboBox.getSelectedItem());
                panelInfoLP.cambiarInfo(lp);
            }
        } else if (e.getSource() == botonEliminar) {
            if (!"Seleccione un Learning Path".equals(comboBox.getSelectedItem())) {
                LearningPath lp = learningPaths.get(comboBox.getSelectedItem());
                inicioProfesor.getProfesor().eliminarLearningPath(lp.getID());
                comboBox.removeItem(comboBox.getSelectedItem());
                panelInfoLP.cambiarInfo(null);
            }
        } else if (e.getSource() == botonModificar) {
            if (!"Seleccione un Learning Path".equals(comboBox.getSelectedItem())) {
                LearningPath lp = learningPaths.get(comboBox.getSelectedItem());
            }
        } else if (e.getSource() == botonVolver) {
            dispose();
        }
    }
}
