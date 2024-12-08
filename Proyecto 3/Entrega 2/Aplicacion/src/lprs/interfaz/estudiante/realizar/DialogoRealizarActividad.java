package lprs.interfaz.estudiante.realizar;

import lprs.exceptions.ActividadPreviaException;
import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.contenido.*;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogoRealizarActividad extends JDialog implements ActionListener {
    private InicioEstudiante inicioEstudiante;
    private ActividadRealizable actividadRealizable;
    private JPanel panelActividad;
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtDuracion;
    private Actividad actividad;
    private JButton botonVolver;
    private JButton botonEntregar;

    public DialogoRealizarActividad(Actividad actividad,InicioEstudiante inicioEstudiante) {
        this.actividad= actividad;
        this.inicioEstudiante = inicioEstudiante;
        setSize(800, 600);
        setLayout(new BorderLayout());
        JPanel panelActividadAuxiliar = new JPanel();
        panelActividadAuxiliar.setLayout(new GridLayout(3,2,10,10));
        panelActividadAuxiliar.add(new JLabel("Actividad:"));
        txtTitulo = new JTextField(actividad.getTitulo());
        txtTitulo.setEditable(false);
        panelActividadAuxiliar.add(txtTitulo);
        panelActividadAuxiliar.add(new JLabel("Descripcion:"));
        txtDescripcion = new JTextArea(actividad.getDescripcion());
        txtDescripcion.setEditable(false);
        panelActividadAuxiliar.add(new JScrollPane(txtDescripcion));
        panelActividadAuxiliar.add(new JLabel("Duracion:"));
        txtDuracion = new JTextField(actividad.getDuracionEsperada());
        txtDuracion.setEditable(false);
        panelActividadAuxiliar.add(txtDuracion);
        add(panelActividadAuxiliar, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonVolver = new JButton("Volver");
        botonEntregar = new JButton("Entregar");
        botonVolver.addActionListener(this);
        botonEntregar.addActionListener(this);
        panelBotones.add(botonVolver);
        panelBotones.add(botonEntregar);
        add(panelBotones, BorderLayout.SOUTH);
        panelActividad = new JPanel();
        add(panelActividad, BorderLayout.EAST);
        if (actividad instanceof Tarea){
            setPanelTarea((Tarea) actividad);
        }
        else if (actividad instanceof Quiz){
            setPanelQuiz((Quiz) actividad);
        }
        else if (actividad instanceof RecursoEducativo){
            setPanelRecurso((RecursoEducativo) actividad);
        }
        else if (actividad instanceof Examen){
            setPanelExamen((Examen) actividad);
        }
        else if (actividad instanceof Encuesta){
            setPanelEncuesta((Encuesta) actividad);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            this.dispose();
            inicioEstudiante.setVisible(true);
        } else if (e.getSource() == botonEntregar) {
            if (actividadRealizable != null){
                if (actividadRealizable instanceof TareaRealizable){
                    actividadRealizable.enviarActividad(new ArrayList());}
                else if (actividadRealizable instanceof QuizRealizable){
                VisualizarQuiz visualizarQuiz = (VisualizarQuiz) panelActividad.getComponent(0);

                if (visualizarQuiz.getPreguntasRealizadas().size()<((Quiz) actividad).getPreguntasQuiz().size()){
                    JOptionPane.showMessageDialog(this, "No se han respondido todas las preguntas");
                    return;
                }
                ArrayList preguntasRealizadas = visualizarQuiz.getPreguntasRealizadas();
                for (int i = 0; i < preguntasRealizadas.size(); i++) {
                    PreguntaCerradaRealizable preguntaRealizada = (PreguntaCerradaRealizable) preguntasRealizadas.get(i);
                    PreguntaCerrada pregunta = ((Quiz) actividad).getPreguntasQuiz().get(i);
                    if (preguntaRealizada.getOpcionEscogida().equals(pregunta.getCorrecta())) {
                        ((QuizRealizable) actividadRealizable).incCorrectas();
                    }
                }
                    actividadRealizable.enviarActividad(preguntasRealizadas);
                }

                else if (actividadRealizable instanceof RecursoRealizable){
                    actividadRealizable.enviarActividad(new ArrayList());
                }
                else if (actividadRealizable instanceof ExamenRealizable){
                    VisualizarPreguntaAbierta visualizarPreguntaAbierta = (VisualizarPreguntaAbierta) panelActividad.getComponent(0);
                    ArrayList preguntasRealizadas = visualizarPreguntaAbierta.getPreguntasRealizadas();
                    actividadRealizable.enviarActividad(preguntasRealizadas);
                }
                else if (actividadRealizable instanceof EncuestaRealizable){
                    VisualizarPreguntaAbierta visualizarPreguntaAbierta = (VisualizarPreguntaAbierta) panelActividad.getComponent(0);
                    ArrayList preguntasRealizadas = visualizarPreguntaAbierta.getPreguntasRealizadas();
                    actividadRealizable.enviarActividad(preguntasRealizadas);

                }
                }
            JOptionPane.showMessageDialog(this, "Actividad entrega con exito");
            this.dispose();
            inicioEstudiante.setVisible(true);
        }
    }




    public void setPanelTarea(Tarea tarea) {
        actividadRealizable = new TareaRealizable(tarea, inicioEstudiante.getEstudiante());
        try {
            actividadRealizable.realizarActividad();
            panelActividad.removeAll();
            VisualizarSecciones visualizarSecciones = new VisualizarSecciones(tarea.getSecciones());
            panelActividad.add(visualizarSecciones);
            panelActividad.updateUI();
        } catch (ActividadPreviaException e) {
            int respuesta = JOptionPane.showOptionDialog(this, "¿Desea hacer la actividad sin realizar las previas? ", "Prerequisitos no completados", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (respuesta == JOptionPane.YES_OPTION) {
                actividadRealizable.setBypassActividadPrevia(true);
                panelActividad.removeAll();
                VisualizarSecciones visualizarSecciones = new VisualizarSecciones(tarea.getSecciones());
                panelActividad.add(visualizarSecciones);
                panelActividad.updateUI();
            } else {
                return;
            }
        }
    }

    public void setPanelQuiz(Quiz quiz){
        actividadRealizable = new QuizRealizable(inicioEstudiante.getEstudiante(), quiz);
        try {
            actividadRealizable.realizarActividad();
            panelActividad.removeAll();
            VisualizarQuiz visualizarPreguntas = new VisualizarQuiz(quiz.getPreguntasQuiz());
            panelActividad.add(visualizarPreguntas);
            panelActividad.updateUI();
        } catch (ActividadPreviaException e) {
            int respuesta = JOptionPane.showOptionDialog(this, "¿Desea hacer la actividad sin realizar las previas? ", "Prerequisitos no completados", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (respuesta == JOptionPane.YES_OPTION){
                actividadRealizable.setBypassActividadPrevia(true);
                panelActividad.removeAll();
                VisualizarQuiz visualizarPreguntas = new VisualizarQuiz(quiz.getPreguntasQuiz());
                panelActividad.add(visualizarPreguntas);
                panelActividad.updateUI();
            }
            else{
                return;
            }
        }
    }
    public void setPanelRecurso(RecursoEducativo recursoEducativo){
        actividadRealizable = new RecursoRealizable(recursoEducativo, inicioEstudiante.getEstudiante());
        try{
            actividadRealizable.realizarActividad();
            panelActividad.removeAll();
            VisualizarRecurso visualizarRecurso = new VisualizarRecurso(recursoEducativo);
            panelActividad.add(visualizarRecurso);
            panelActividad.updateUI();
        } catch (ActividadPreviaException e) {
            int respuesta = JOptionPane.showOptionDialog(this, "¿Desea hacer la actividad sin realizar las previas? ", "Prerequisitos no completados", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (respuesta == JOptionPane.YES_OPTION){
                actividadRealizable.setBypassActividadPrevia(true);
                panelActividad.removeAll();
                VisualizarRecurso visualizarRecurso = new VisualizarRecurso(recursoEducativo);
                panelActividad.add(visualizarRecurso);
                panelActividad.updateUI();
            }
            else{
                return;
            }
        }
    }

    public void setPanelExamen(Examen examen){
        actividadRealizable = new ExamenRealizable(inicioEstudiante.getEstudiante(), examen);
        try {
            actividadRealizable.realizarActividad();
            panelActividad.removeAll();
            VisualizarPreguntaAbierta visualizarPreguntas = new VisualizarPreguntaAbierta(examen.getPreguntasExamen());
            panelActividad.add(visualizarPreguntas);
            panelActividad.updateUI();
        }
        catch (ActividadPreviaException e) {
            int respuesta = JOptionPane.showOptionDialog(this, "¿Desea hacer la actividad sin realizar las previas? ", "Prerequisitos no completados", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (respuesta == JOptionPane.YES_OPTION){
                actividadRealizable.setBypassActividadPrevia(true);
                panelActividad.removeAll();
                VisualizarPreguntaAbierta visualizarPreguntas = new VisualizarPreguntaAbierta(examen.getPreguntasExamen());
                panelActividad.add(visualizarPreguntas);
                panelActividad.updateUI();
            }
            else{
                return;
            }
        }
    }

    public void setPanelEncuesta (Encuesta encuesta){
        actividadRealizable = new EncuestaRealizable(encuesta, inicioEstudiante.getEstudiante());
        try {
            actividadRealizable.realizarActividad();
            panelActividad.removeAll();
            VisualizarPreguntaAbierta visualizarPreguntas = new VisualizarPreguntaAbierta(encuesta.getPreguntasEncuesta());
            panelActividad.add(visualizarPreguntas);
            panelActividad.updateUI();
        }
        catch (ActividadPreviaException e) {
            int respuesta = JOptionPane.showOptionDialog(this, "¿Desea hacer la actividad sin realizar las previas? ", "Prerequisitos no completados", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (respuesta == JOptionPane.YES_OPTION){
                actividadRealizable.setBypassActividadPrevia(true);
                panelActividad.removeAll();
                VisualizarPreguntaAbierta visualizarPreguntas = new VisualizarPreguntaAbierta(encuesta.getPreguntasEncuesta());
                panelActividad.add(visualizarPreguntas);
                panelActividad.updateUI();
            }
            else{
                return;
            }
        }
    }

}
