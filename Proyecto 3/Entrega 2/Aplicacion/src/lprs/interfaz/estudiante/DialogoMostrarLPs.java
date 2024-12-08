package lprs.interfaz.estudiante;

import lprs.interfaz.InterfazPrincipal;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class DialogoMostrarLPs extends JFrame implements ActionListener {
    private Estudiante estudiante;
    private JComboBox<String> comboBox;
    private PanelInfoLP panelInfoLP;
    private Map<String, LearningPath> learningPathMap;
    private JButton btnSalir;
    private InicioEstudiante inicioEstudiante;

    public DialogoMostrarLPs(InicioEstudiante inicioEstudiante) {
        this.estudiante = inicioEstudiante.getEstudiante();
        this.inicioEstudiante = inicioEstudiante;
        setTitle("Ver Learning Paths inscritos - " + estudiante.getUsuario());
        setSize(700, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JLabel lblTitulo = new JLabel("Learning Paths inscritos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        learningPathMap = new HashMap<>();
        comboBox = new JComboBox<>();
        comboBox.addActionListener(this);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(new Color(70, 130, 180));
        comboBox.setForeground(Color.WHITE);
        comboBox.addItem("Seleccione un Learning Path");
        for (LearningPath lp : estudiante.getLearningPathsInscritos()) {
            String llave = lp.getTitulo() + " (" + lp.getID() + ")";
            comboBox.addItem(llave);
            learningPathMap.put(llave, lp);
        }
        add(comboBox, BorderLayout.WEST);

        panelInfoLP = new PanelInfoLP();
        add(panelInfoLP, BorderLayout.CENTER);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        add(btnSalir, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String key = (String) comboBox.getSelectedItem();
            if (!"Seleccione un Learning Path".equals(key)) {
                LearningPath lp = learningPathMap.get(key);
                panelInfoLP.cambiarInformacion(lp);
            }
        } else if (e.getSource() == btnSalir) {
            dispose();
            inicioEstudiante.setVisible(true);
        }
    }
}