package lprs.interfaz.profesor.manejarLP.clonarLP;

import lprs.exceptions.ClonarLPException;
import lprs.interfaz.profesor.manejarLP.DialogoManejarLP;
import lprs.interfaz.profesor.manejarLP.PanelInfoLP;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DialogoClonarLP extends JDialog implements ActionListener {
    private PanelInfoLP panelInfoLP;
    private DialogoManejarLP dialogoManejarLP;
    private JComboBox<String> comboLPs;
    private JButton botonClonar;
    private JButton botonCerrar;
    private ArrayList<LearningPath> lps;
    private HashMap<String, LearningPath> lpsHashMap;

    public DialogoClonarLP(DialogoManejarLP dialogoManejarLP) {
        setSize(600,600);
        this.dialogoManejarLP = dialogoManejarLP;
        panelInfoLP = new PanelInfoLP();
        lps = dialogoManejarLP.getInicioProfesor().getProfesor().getLprsActual().getManejadorLP().getLearningPaths();
        lpsHashMap = new HashMap<String, LearningPath>();
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Clonar Learning Path", SwingConstants.CENTER);
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
        botonClonar = new JButton("Clonar");
        botonClonar.addActionListener(this);
        panelBotones.add(botonClonar);
        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCerrar) {
            dispose();
        } else if (e.getSource() == botonClonar) {
            String ID = comboLPs.getSelectedItem().toString();
            LearningPath lp = lpsHashMap.get(ID);
            try {
                dialogoManejarLP.getInicioProfesor().getProfesor().clonarLearningPath(lp.getID());
            } catch (ClonarLPException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                return;
            }
            JOptionPane.showMessageDialog(this, "Learning Path clonado exitosamente");
            dispose();
        } else if (e.getSource() == comboLPs) {
            String ID = comboLPs.getSelectedItem().toString();
            LearningPath lp = lpsHashMap.get(ID);
            panelInfoLP.actualizarLP(lp);
        }
    }
}
