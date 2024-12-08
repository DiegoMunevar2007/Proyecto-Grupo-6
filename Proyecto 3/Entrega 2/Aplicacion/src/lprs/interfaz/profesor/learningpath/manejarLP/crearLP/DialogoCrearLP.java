package lprs.interfaz.profesor.learningpath.manejarLP.crearLP;

import lprs.interfaz.profesor.learningpath.manejarLP.DialogoManejarLP;
import lprs.logica.cuentas.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        panelCrearLearningPath = new PanelCrearLearningPath(dialogoManejarLP.getLprs());
        panelCrearLearningPath.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(panelCrearLearningPath, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        botonCrear = new JButton("Crear");
        botonCrear.addActionListener(this);
        botonCrear.setFont(new Font("Arial", Font.PLAIN, 16));
        botonCrear.setBackground(new Color(70, 130, 180));
        botonCrear.setForeground(Color.WHITE);
        panelBotones.add(botonCrear);

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        botonSalir.setFont(new Font("Arial", Font.PLAIN, 16));
        botonSalir.setBackground(new Color(105, 105, 105));
        botonSalir.setForeground(Color.WHITE);
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCrear) {
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
        } else if (e.getSource() == botonSalir) {
            dispose();
        }
    }
}