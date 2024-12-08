package lprs.interfaz.estudiante.avance.learningpath;

import com.jcalendar.pane.calendar.CalendarPane;
import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.jcalendar.pane.calendar.CalendarPane;
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
        this.learningPaths = new HashMap<String, LearningPath>();
        setLayout(new BorderLayout());
        panelAvanceLP = new PanelAvanceLP();
        add(new JLabel("Avance de Learning Path"), BorderLayout.NORTH);
        add(panelAvanceLP, BorderLayout.CENTER);
        setSize(800, 600);
        setLocationRelativeTo(inicioEstudiante);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        comboBoxLearningPaths = new JComboBox<>();
        comboBoxLearningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioEstudiante.getEstudiante().getLearningPathsInscritos()) {
            comboBoxLearningPaths.addItem(lp.getTitulo()+" ("+lp.getID()+")");
            learningPaths.put(lp.getTitulo()+" ("+lp.getID()+")", lp);
        }
        comboBoxLearningPaths.addActionListener(this);
        add(comboBoxLearningPaths, BorderLayout.WEST);
        JPanel panelAux = new JPanel();
        panelAux.setLayout(new FlowLayout());
        panelAux.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnSalir = new JButton("Salir");

        btnSalir.addActionListener(this);
        panelAux.add(btnSalir);
        add(panelAux, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLearningPaths) {
            if (!"Seleccione un Learning Path".equals(comboBoxLearningPaths.getSelectedItem())) {
                LearningPath lp = learningPaths.get(comboBoxLearningPaths.getSelectedItem());
                Avance avance =inicioEstudiante.getEstudiante().getAvance(lp.getID());
                panelAvanceLP.cambiarInformacion(avance);
            }
        } else if (e.getSource() == btnSalir) {
            dispose();
            inicioEstudiante.setVisible(true);
        }
    }
}
