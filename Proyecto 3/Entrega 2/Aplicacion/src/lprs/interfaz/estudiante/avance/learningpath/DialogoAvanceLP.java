package lprs.interfaz.estudiante.avance.learningpath;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DialogoAvanceLP extends JDialog implements ActionListener {
    private JComboBox<String> comboBoxLearningPaths;
    private PanelAvanceLP panelAvanceLP;
    private InicioEstudiante inicioEstudiante;
    private HashMap<String, LearningPath> learningPaths;
    private JButton btnSalir;

    public DialogoAvanceLP(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        this.learningPaths = new HashMap<>();
        setLayout(new BorderLayout(10, 10));
        setTitle("Avance de Learning Path");
        setSize(800, 600);
        setLocationRelativeTo(inicioEstudiante);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        panelAvanceLP = new PanelAvanceLP();
        add(new JLabel("Avance de Learning Path", SwingConstants.CENTER), BorderLayout.NORTH);
        add(panelAvanceLP, BorderLayout.CENTER);

        comboBoxLearningPaths = new JComboBox<>();
        comboBoxLearningPaths.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxLearningPaths.setBackground(new Color(70, 130, 180));
        comboBoxLearningPaths.setForeground(Color.WHITE);
        comboBoxLearningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioEstudiante.getEstudiante().getLearningPathsInscritos()) {
            comboBoxLearningPaths.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
            learningPaths.put(lp.getTitulo() + " (" + lp.getID() + ")", lp);
        }
        comboBoxLearningPaths.addActionListener(this);
        add(comboBoxLearningPaths, BorderLayout.WEST);

        JPanel panelAux = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelAux.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        panelAux.add(btnSalir);
        add(panelAux, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLearningPaths) {
            if (!"Seleccione un Learning Path".equals(comboBoxLearningPaths.getSelectedItem())) {
                LearningPath lp = learningPaths.get(comboBoxLearningPaths.getSelectedItem());
                Avance avance = inicioEstudiante.getEstudiante().getAvance(lp.getID());
                panelAvanceLP.cambiarInformacion(avance);
            }
        } else if (e.getSource() == btnSalir) {
            dispose();
            inicioEstudiante.setVisible(true);
        }
    }
}