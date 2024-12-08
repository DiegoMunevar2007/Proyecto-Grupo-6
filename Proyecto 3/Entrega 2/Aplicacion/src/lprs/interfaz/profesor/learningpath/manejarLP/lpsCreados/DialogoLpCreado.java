package lprs.interfaz.profesor.learningpath.manejarLP.lpsCreados;

import lprs.interfaz.profesor.learningpath.manejarLP.DialogoManejarLP;
import lprs.interfaz.profesor.learningpath.manejarLP.PanelInfoLP;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DialogoLpCreado extends JDialog implements ActionListener {
    private PanelInfoLP panelInfoLP;
    private DialogoManejarLP dialogoManejarLP;
    private JComboBox<String> comboLPs;
    private JButton botonModificar;
    private JButton botonEliminar;
    private JButton botonCerrar;
    private ArrayList<LearningPath> lps;
    private HashMap<String, LearningPath> lpsHashMap;

    public DialogoLpCreado(DialogoManejarLP dialogoManejarLP) {
        setSize(600,600);
        this.dialogoManejarLP = dialogoManejarLP;
        panelInfoLP = new PanelInfoLP();
        lps = dialogoManejarLP.getInicioProfesor().getProfesor().getLearningPathsCreadosLista();
        lpsHashMap = new HashMap<String, LearningPath>();
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Learning Paths Creados");
        titulo.setBorder(new EmptyBorder(30, 30, 30, 30));
        add(titulo, BorderLayout.NORTH);
        add(panelInfoLP, BorderLayout.CENTER);

        JPanel panelCombo = new JPanel();
        panelCombo.setBorder(new EmptyBorder(30, 30, 30, 30));
        comboLPs = new JComboBox<String>();
        for (LearningPath lp : lps) {
            comboLPs.addItem(lp.getTitulo() + "(" + lp.getID() + ")");
            lpsHashMap.put(lp.getTitulo() + "(" + lp.getID() + ")", lp);
        }
        comboLPs.addActionListener(this);
        panelCombo.add(comboLPs);
        add(panelCombo, BorderLayout.WEST);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(30, 30, 30, 30));
        panelBotones.setLayout(new FlowLayout());
        botonModificar = new JButton("Modificar");
        botonModificar.addActionListener(this);
        panelBotones.add(botonModificar);
        botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(this);
        panelBotones.add(botonEliminar);
        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCerrar) {
            dispose();
        } else if (e.getSource() == botonModificar) {
            String objetivos = panelInfoLP.getObjetivos();
            String[] objetivosArray = objetivos.split("\n");
            ArrayList<String> objetivosList = new ArrayList<>();
            for (String obj : objetivosArray) {
                objetivosList.add(obj);
            }
            String ID = comboLPs.getSelectedItem().toString();
            LearningPath lp = lpsHashMap.get(ID);

            dialogoManejarLP.getInicioProfesor().getProfesor().modificarLearningPath(lp.getID(), panelInfoLP.getTitulo(), panelInfoLP.getDescripcion(), panelInfoLP.getDificultad(), objetivosList);
        } else if (e.getSource() == botonEliminar) {
            String ID = comboLPs.getSelectedItem().toString();
            LearningPath lp = lpsHashMap.get(ID);
            dialogoManejarLP.getInicioProfesor().getProfesor().eliminarLearningPath(lp.getID());
            JOptionPane.showMessageDialog(this, "Learning Path eliminado exitosamente");
            dispose();
        } else if (e.getSource() == comboLPs) {
            String ID = comboLPs.getSelectedItem().toString();
            LearningPath lp = lpsHashMap.get(ID);
            panelInfoLP.actualizarLP(lp);
        }
    }
}
