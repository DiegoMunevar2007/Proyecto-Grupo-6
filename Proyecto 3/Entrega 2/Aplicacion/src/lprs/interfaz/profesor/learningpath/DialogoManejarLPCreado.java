package lprs.interfaz.profesor.learningpath;

import lprs.interfaz.profesor.InicioProfesor;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        this.inicioProfesor = inicioProfesor;
        learningPaths = new HashMap<>();
        setTitle("Manejar Learning Path");
        setSize(500, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel panelComboBox = new JPanel(new BorderLayout(10, 10));
        panelComboBox.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel lblSeleccionarLP = new JLabel("Seleccione un Learning Path:");
        lblSeleccionarLP.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeleccionarLP.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelComboBox.add(lblSeleccionarLP, BorderLayout.NORTH);
        comboBox = new JComboBox<>();
        comboBox.addActionListener(this);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(new Color(70, 130, 180));
        comboBox.setForeground(Color.WHITE);
        comboBox.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioProfesor.getProfesor().getLearningPathsCreados()) {
            comboBox.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
            learningPaths.put(lp.getTitulo() + " (" + lp.getID() + ")", lp);
        }
        panelComboBox.add(comboBox, BorderLayout.CENTER);
        add(panelComboBox, BorderLayout.WEST);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        botonEliminar = new JButton("Eliminar");
        botonEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonEliminar.setBackground(new Color(220, 20, 60));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setBorderPainted(false);
        botonEliminar.addActionListener(this);
        panelBotones.add(botonEliminar);

        botonModificar = new JButton("Modificar");
        botonModificar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonModificar.setBackground(new Color(70, 130, 180));
        botonModificar.setForeground(Color.WHITE);
        botonModificar.setBorderPainted(false);
        botonModificar.addActionListener(this);
        panelBotones.add(botonModificar);

        botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Arial", Font.PLAIN, 14));
        botonVolver.setBackground(new Color(34, 139, 34));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setBorderPainted(false);
        botonVolver.addActionListener(this);
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
                String titulo = panelInfoLP.getTitulo();
                String descripcion = panelInfoLP.getDescripcion();
                String nivel = panelInfoLP.getNivel();
                ArrayList<String> objetivosLista = new ArrayList<>();
                String objetivos = panelInfoLP.getObjetivos();
                String[] objetivosArray = objetivos.split("\n");
                for (String objetivo : objetivosArray) {
                    objetivosLista.add(objetivo);
                }

                try {
                    lp.editarLearningPath(titulo, descripcion, nivel, objetivosLista, inicioProfesor.getProfesor());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        } else if (e.getSource() == botonVolver) {
            dispose();
        }
    }
}