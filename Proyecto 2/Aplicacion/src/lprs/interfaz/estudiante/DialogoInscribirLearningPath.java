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
        setLayout(new BorderLayout());
        setTitle("Inscribir Learning Path");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        panelLabels = new JPanel();
        panelLabels.setLayout(new BorderLayout());
        panelLabels.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelLabels.add(new JLabel("Inscribir Learning Path", SwingConstants.CENTER), BorderLayout.NORTH);
        panelLabels.add(new JLabel("Seleccione un interés", SwingConstants.CENTER), BorderLayout.CENTER);
        add(panelLabels, BorderLayout.NORTH);

        panelComboBoxes = new JPanel();
        panelComboBoxes.setLayout(new GridLayout(2, 1));
        panelComboBoxes.setBorder(new EmptyBorder(20, 20, 20, 20));
        comboBoxIntereses = new JComboBox<>();
        comboBoxIntereses.addItem("Seleccione un interés");
        LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
        for (String interes : lprs.getManejadorLP().getKeyWords()) {
            comboBoxIntereses.addItem(interes);
        }
        comboBoxIntereses.addActionListener(this);
        panelComboBoxes.add(comboBoxIntereses);
        comboBoxLPs = new JComboBox<>();
        comboBoxLPs.addItem("Seleccione un Learning Path");
        comboBoxLPs.addActionListener(this);
        panelComboBoxes.add(comboBoxLPs);
        add(panelComboBoxes, BorderLayout.WEST);
        panelInfoLP = new PanelInfoLP();
        add(panelInfoLP, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(this);
        panelBotones.add(btnInscribir);
        btnSalir = new JButton("Salir");
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
            if (llave!= null && !"Seleccione un Learning Path".equals(comboBoxLPs.getSelectedItem())) {
                LPRS lprs = inicioEstudiante.getInterfazPrincipal().getLprs();
                String id = llave.substring(llave.indexOf("(") + 1, llave.indexOf(")"));
                LearningPath lp = lprs.getManejadorLP().getLearningPath(id);
                panelInfoLP.cambiarInformacion(lp);
            }
        } else if (e.getSource() == btnInscribir) {
            String llave = (String) comboBoxLPs.getSelectedItem();
            if (llave!= null && !"Seleccione un Learning Path".equals(comboBoxLPs.getSelectedItem())) {
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
