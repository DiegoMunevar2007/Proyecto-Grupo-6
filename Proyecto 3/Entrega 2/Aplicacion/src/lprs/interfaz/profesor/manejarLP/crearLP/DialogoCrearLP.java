package lprs.interfaz.profesor.manejarLP.crearLP;

import lprs.interfaz.profesor.manejarLP.DialogoManejarLP;
import lprs.logica.cuentas.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogoCrearLP extends JDialog implements ActionListener {
    private PanelCrearLearningPath panelCrearLearningPath;
    private JButton botonCrear;
    private JButton botonSalir;
    private Profesor profesor;
    private DialogoManejarLP dialogoManejarLP;
    public DialogoCrearLP(DialogoManejarLP dialogoManejarLP, Profesor profesor) {
        setLayout(new BorderLayout());
        this.profesor = profesor;
        this.dialogoManejarLP = dialogoManejarLP;
        JLabel lblTitulo = new JLabel("Crear Learning Path", SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        panelCrearLearningPath = new PanelCrearLearningPath(dialogoManejarLP.getLprs());
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
            String titulo = panelCrearLearningPath.getTitulo();
            String descripcion = panelCrearLearningPath.getDescripcion();
            String nivel = panelCrearLearningPath.getNivel();
            String objetivo = panelCrearLearningPath.getObjetivos();
            String[] objetivos = objetivo.split("\n");

            ArrayList<String> objetivosArray = new ArrayList<>();
            for (String obj : objetivos) {
                objetivosArray.add(obj);
            }
            ArrayList<String> keywords = panelCrearLearningPath.getKeywords();
            profesor.crearLearningPath(titulo, descripcion, nivel, objetivosArray, keywords);
            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente");
            dispose();
        } else if (e.getSource()==botonSalir) {
            dispose();
        }
    }

}
