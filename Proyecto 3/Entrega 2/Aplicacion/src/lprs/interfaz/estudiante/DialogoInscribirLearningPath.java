package lprs.interfaz.estudiante;

import lprs.interfaz.InterfazPrincipal;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInscribirLearningPath extends JFrame implements ActionListener {
    private JPanel panelLabels;
    private JPanel panelComboBoxes;
    private JComboBox<String> comboBoxIntereses;
    private JComboBox<String> comboBoxLPs;
    private InicioEstudiante inicioEstudiante;
    private PanelInfoLP panelInfoLP;
    private Estudiante estudiante;
    private JButton btnInscribir;
    private JButton btnSalir;

    public DialogoInscribirLearningPath(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        this.estudiante = inicioEstudiante.getEstudiante();
        setLayout(new BorderLayout(10, 10));
        setTitle("Inscribir Learning Path");
        setLocationRelativeTo(null);
        setSize(450, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        panelLabels = new JPanel(new BorderLayout(10, 10));
        panelLabels.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel lblTitulo = new JLabel("Inscribir Learning Path", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelLabels.add(lblTitulo, BorderLayout.NORTH);
        JLabel lblInteres = new JLabel("Seleccione un interés", SwingConstants.CENTER);
        lblInteres.setFont(new Font("Arial", Font.PLAIN, 14));
        lblInteres.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelLabels.add(lblInteres, BorderLayout.CENTER);
        add(panelLabels, BorderLayout.NORTH);

        panelComboBoxes = new JPanel(new GridLayout(2, 1, 10, 10));
        panelComboBoxes.setBorder(new EmptyBorder(20, 20, 20, 20));
        comboBoxIntereses = new JComboBox<>();
        comboBoxIntereses.addItem("Seleccione un interés");
        LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
        for (String interes : lprs.getManejadorLP().getKeyWords()) {
            comboBoxIntereses.addItem(interes);
        }
        comboBoxIntereses.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxIntereses.setBackground(new Color(70, 130, 180));
        comboBoxIntereses.setForeground(Color.WHITE);
        comboBoxIntereses.addActionListener(this);
        panelComboBoxes.add(comboBoxIntereses);

        comboBoxLPs = new JComboBox<>();
        comboBoxLPs.addItem("Seleccione un Learning Path");
        comboBoxLPs.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxLPs.setBackground(new Color(70, 130, 180));
        comboBoxLPs.setForeground(Color.WHITE);
        comboBoxLPs.addActionListener(this);
        panelComboBoxes.add(comboBoxLPs);
        add(panelComboBoxes, BorderLayout.WEST);

        panelInfoLP = new PanelInfoLP();
        add(panelInfoLP, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        btnInscribir = new JButton("Inscribir");
        btnInscribir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnInscribir.setBackground(new Color(34, 139, 34));
        btnInscribir.setForeground(Color.WHITE);
        btnInscribir.setBorderPainted(false);
        btnInscribir.addActionListener(this);
        panelBotones.add(btnInscribir);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxIntereses) {
            comboBoxLPs.removeAllItems();
            comboBoxLPs.addItem("Seleccione un Learning Path");
            LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
            for (LearningPath lp : lprs.getManejadorLP().getLearningPathsKeywords(comboBoxIntereses.getSelectedItem().toString())) {
                if (!estudiante.getLearningPathsInscritos().contains(lp)) {
                    comboBoxLPs.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
                }
            }
        } else if (e.getSource() == comboBoxLPs) {
            String llave = (String) comboBoxLPs.getSelectedItem();
            if (llave != null && !"Seleccione un Learning Path".equals(comboBoxLPs.getSelectedItem())) {
                LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
                String id = llave.substring(llave.indexOf("(") + 1, llave.indexOf(")"));
                LearningPath lp = lprs.getManejadorLP().getLearningPath(id);
                panelInfoLP.cambiarInformacion(lp);
            }
        } else if (e.getSource() == btnInscribir) {
            String llave = (String) comboBoxLPs.getSelectedItem();
            if (llave != null && !"Seleccione un Learning Path".equals(comboBoxLPs.getSelectedItem())) {
                LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
                String id = llave.substring(llave.indexOf("(") + 1, llave.indexOf(")"));
                LearningPath lp = lprs.getManejadorLP().getLearningPath(id);
                try {
                    estudiante.inscribirLearningPath(id);
                    JOptionPane.showMessageDialog(this, "Learning Path inscrito exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        } else if (e.getSource() == btnSalir) {
            dispose();
            inicioEstudiante.setVisible(true);
        }
    }
}