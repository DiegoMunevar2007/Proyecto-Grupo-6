package lprs.interfaz.estudiante;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PanelComboBox extends JPanel implements ActionListener {
    private JComboBox<String> comboBoxLearningPaths;
    private HashMap<String, LearningPath> learningPaths;
    private JComboBox<String> comboBoxActividades;
    private HashMap<String, Actividad> actividades;
    private InicioEstudiante inicioEstudiante;
    private DialogoManejarActividad dialogoManejarActividad;

    public PanelComboBox(InicioEstudiante inicioEstudiante) {
        learningPaths = new HashMap<String, LearningPath>();
        actividades = new HashMap<String, Actividad>();
        this.inicioEstudiante = inicioEstudiante;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel panelAuxiliarComboBox = new JPanel();
        panelAuxiliarComboBox.setLayout(new GridLayout(4, 1, 30, 30));
        panelAuxiliarComboBox.add(new JLabel("Learning Path:"));
        comboBoxLearningPaths = new JComboBox<String>();
        comboBoxLearningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath lp : inicioEstudiante.getEstudiante().getLearningPathsInscritos()) {
            comboBoxLearningPaths.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
            learningPaths.put(lp.getTitulo() + " (" + lp.getID() + ")", lp);
        }

        comboBoxLearningPaths.addActionListener(this);
        panelAuxiliarComboBox.add(comboBoxLearningPaths);
        panelAuxiliarComboBox.add(new JLabel("Actividad:"));
        comboBoxActividades = new JComboBox<String>();
        comboBoxActividades.addItem("Seleccione una actividad");
        panelAuxiliarComboBox.add(comboBoxActividades);
        add(panelAuxiliarComboBox, BorderLayout.WEST);
    }
    public PanelComboBox(DialogoManejarActividad dialogoManejarActividad) {
        learningPaths = new HashMap<String, LearningPath>();
        actividades = new HashMap<String, Actividad>();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel panelAuxiliarComboBox = new JPanel();
        panelAuxiliarComboBox.setLayout(new GridLayout(4, 1, 30, 30));
        panelAuxiliarComboBox.add(new JLabel("Learning Path:"));
        comboBoxLearningPaths = new JComboBox<String>();
        comboBoxLearningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath lp : dialogoManejarActividad.getInicioProfesor().getProfesor().getLprsActual().getManejadorLP().getLearningPathsDisponibles()) {
            comboBoxLearningPaths.addItem(lp.getTitulo() + " (" + lp.getID() + ")");
            learningPaths.put(lp.getTitulo() + " (" + lp.getID() + ")", lp);
        }

        comboBoxLearningPaths.addActionListener(this);
        panelAuxiliarComboBox.add(comboBoxLearningPaths);
        panelAuxiliarComboBox.add(new JLabel("Actividad:"));
        comboBoxActividades = new JComboBox<String>();
        comboBoxActividades.addItem("Seleccione una actividad");
        panelAuxiliarComboBox.add(comboBoxActividades);
        add(panelAuxiliarComboBox, BorderLayout.WEST);
    }

    public JComboBox<String> getComboBoxActividades() {
        return comboBoxActividades;
    }
    public Actividad getActividad(String id) {
        return actividades.get(id);
    }
    public LearningPath getLearningPathSeleccionado() {
        return learningPaths.get(comboBoxLearningPaths.getSelectedItem());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLearningPaths) {
            comboBoxActividades.removeAllItems();
            comboBoxActividades.addItem("Seleccione una actividad");
            if (!"Seleccione un Learning Path".equals(comboBoxLearningPaths.getSelectedItem())) {
                if (inicioEstudiante != null) {
                    LearningPath lp = learningPaths.get(comboBoxLearningPaths.getSelectedItem());
                    Avance avance = inicioEstudiante.getEstudiante().getAvance(lp.getID());
                    for (Actividad actividad : avance.getActividadesCompletadasLista()) {
                        comboBoxActividades.addItem(actividad.getTitulo() + " (" + actividad.getNumeroActividad() + ")");
                        actividades.put(actividad.getTitulo() + " (" + actividad.getNumeroActividad() + ")", actividad);
                    }
                } else {
                    LearningPath lp = learningPaths.get(comboBoxLearningPaths.getSelectedItem());
                    System.out.println(lp.getActividades().size());
                    for (Actividad actividad : lp.getActividades()) {
                        comboBoxActividades.addItem(actividad.getTitulo() + " (" + actividad.getNumeroActividad() + ")");
                        actividades.put(actividad.getTitulo() + " (" + actividad.getNumeroActividad() + ")", actividad);
                    }
                }
            }
        } else if (e.getSource() == comboBoxActividades) {
            if (!"Seleccione una actividad".equals(comboBoxActividades.getSelectedItem())) {
                Actividad actividad = actividades.get(comboBoxActividades.getSelectedItem());
            }
        }
    }
}
