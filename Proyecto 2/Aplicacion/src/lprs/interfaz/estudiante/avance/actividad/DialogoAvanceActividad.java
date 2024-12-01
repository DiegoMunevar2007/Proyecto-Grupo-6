package lprs.interfaz.estudiante.avance.actividad;

import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoAvanceActividad extends JDialog implements ActionListener {
    private PanelComboBox panelComboBox;
    private InicioEstudiante inicioEstudiante;
    public DialogoAvanceActividad(InicioEstudiante inicioEstudiante) {
        this.inicioEstudiante = inicioEstudiante;
        setLayout(new BorderLayout());
        panelComboBox = new PanelComboBox(inicioEstudiante);
        panelComboBox.getComboBoxActividades().addActionListener(this);
        add(panelComboBox, BorderLayout.WEST);
        setSize(400, 300);
        setVisible(true);
    }

    public void repintar(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelComboBox.getComboBoxActividades() ) {
            if (panelComboBox.getComboBoxActividades().getSelectedItem() == null || "Seleccione una actividad".equals(panelComboBox.getComboBoxActividades().getSelectedItem())) {
                return;
            }
            String id = panelComboBox.getComboBoxActividades().getSelectedItem().toString();
            Actividad actividad = panelComboBox.getActividad(id);
            LearningPath learningPath = actividad.getLearningPathAsignado();
            Estudiante estudiante = inicioEstudiante.getEstudiante();
            if (actividad instanceof Tarea){
                TareaRealizable tareaRealizable = (TareaRealizable) estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelTarea panelTarea = new PanelTarea(tareaRealizable);
                getContentPane().removeAll();
                add(panelTarea, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                revalidate();
                repaint();
            }
            else if (actividad instanceof RecursoEducativo){
                RecursoRealizable recursoRealizable = (RecursoRealizable) estudiante.getAvance(learningPath.getID()).getActividadesCompletadas().get(actividad);
                PanelRecurso panelRecurso = new PanelRecurso(recursoRealizable);
                getContentPane().removeAll();
                add(panelRecurso, BorderLayout.CENTER);
                add(panelComboBox, BorderLayout.WEST);
                revalidate();
                repaint();
            }
        }
    }
}
