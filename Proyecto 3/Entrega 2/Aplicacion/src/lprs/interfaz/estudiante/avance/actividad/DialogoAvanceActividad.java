package lprs.interfaz.estudiante.avance.actividad;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.interfaz.estudiante.PanelComboBox;
import lprs.logica.contenido.*;
import lprs.logica.contenido.realizable.*;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoAvanceActividad extends JDialog implements ActionListener {
    private PanelComboBox panelComboBox;
    private InicioEstudiante inicioEstudiante;
    private JButton btnSalir;

    public DialogoAvanceActividad(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        setLayout(new BorderLayout(10, 10));
        setTitle("Avance de Actividad");
        setLocationRelativeTo(null);
        setSize(600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        panelComboBox = new PanelComboBox(inicioEstudiante);
        panelComboBox.getComboBoxActividades().addActionListener(this);
        add(panelComboBox, BorderLayout.WEST);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelComboBox.getComboBoxActividades()) {
            if (panelComboBox.getComboBoxActividades().getSelectedItem() == null || "Seleccione una actividad".equals(panelComboBox.getComboBoxActividades().getSelectedItem())) {
                return;
            }
            String id = panelComboBox.getComboBoxActividades().getSelectedItem().toString();
            Actividad actividad = panelComboBox.getActividad(id);
            LearningPath learningPath = actividad.getLearningPathAsignado();
            Estudiante estudiante = inicioEstudiante.getEstudiante();
            if (actividad instanceof Tarea) {
                TareaRealizable tareaRealizable = (TareaRealizable) estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelTarea panelTarea = new PanelTarea(tareaRealizable);
                getContentPane().removeAll();
                add(panelTarea, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                add(btnSalir, BorderLayout.SOUTH);
                revalidate();
                repaint();
            } else if (actividad instanceof RecursoEducativo) {
                RecursoRealizable recursoRealizable = (RecursoRealizable) estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelRecurso panelRecurso = new PanelRecurso(recursoRealizable);
                getContentPane().removeAll();
                add(panelRecurso, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                add(btnSalir, BorderLayout.SOUTH);
                revalidate();
                repaint();
            } else if (actividad instanceof Quiz) {
                ActividadRealizable actividadRealizable = estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelQuiz panelQuiz = new PanelQuiz((QuizRealizable) actividadRealizable);
                getContentPane().removeAll();
                add(panelQuiz, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                add(btnSalir, BorderLayout.SOUTH);
                revalidate();
                repaint();
            } else if (actividad instanceof Examen) {
                ActividadRealizable actividadRealizable = estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelExamen panelExamen = new PanelExamen((ExamenRealizable) actividadRealizable);
                getContentPane().removeAll();
                add(panelExamen, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                add(btnSalir, BorderLayout.SOUTH);
                revalidate();
                repaint();
            } else if (actividad instanceof Encuesta) {
                ActividadRealizable actividadRealizable = estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelEncuesta panelEncuesta = new PanelEncuesta((EncuestaRealizable) actividadRealizable);
                getContentPane().removeAll();
                add(panelEncuesta, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                add(btnSalir, BorderLayout.SOUTH);
                revalidate();
                repaint();
            }
        } else if (e.getSource() == btnSalir) {
            dispose();
        }
    }
}