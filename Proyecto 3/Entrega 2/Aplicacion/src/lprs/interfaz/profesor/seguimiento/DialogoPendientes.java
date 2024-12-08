package lprs.interfaz.profesor.seguimiento;

import lprs.exceptions.EstadoException;
import lprs.interfaz.estudiante.avance.actividad.PanelActividad;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.*;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class DialogoPendientes extends JDialog implements ActionListener, WindowListener {
    private JComboBox<LearningPath> comboBoxLearningPaths;
    private JComboBox<ActividadRealizable> comboBoxActividades;
    private PanelActividad panelActividad;
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
    private JButton botonCalificar;
    private ActividadRealizable actividadRealizable;

    public DialogoPendientes(DialogoManejarSeguimiento dialogoManejarSeguimiento) {
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;
        iniciar();
        llenarLPs();
    }

    private void iniciar() {
        setLayout(new BorderLayout());
        setTitle("Actividades pendientes");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        comboBoxLearningPaths = new JComboBox<>();
        comboBoxLearningPaths.addActionListener(this);

        comboBoxActividades = new JComboBox<>();
        comboBoxActividades.addActionListener(this);

        JPanel panelAuxiliarComboBox = new JPanel(new BorderLayout());
        panelAuxiliarComboBox.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelAuxiliarComboBox.add(comboBoxLearningPaths, BorderLayout.NORTH);
        panelAuxiliarComboBox.add(comboBoxActividades, BorderLayout.SOUTH);
        add(panelAuxiliarComboBox, BorderLayout.WEST);

        panelActividad = new PanelActividad();
        botonCalificar = new JButton("Calificar");
        botonCalificar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonCalificar.setBackground(new Color(70, 130, 180));
        botonCalificar.setForeground(Color.WHITE);
        botonCalificar.setBorderPainted(false);
        botonCalificar.addActionListener(this);

        JPanel panelAuxiliarInfo = new JPanel(new BorderLayout());
        panelAuxiliarInfo.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelAuxiliarInfo.add(panelActividad, BorderLayout.CENTER);
        panelAuxiliarInfo.add(botonCalificar, BorderLayout.SOUTH);
        add(panelAuxiliarInfo, BorderLayout.CENTER);

        setVisible(true);
    }

    private void llenarLPs() {
        Profesor profesor = dialogoManejarSeguimiento.getInicioProfesor().getProfesor();
        for (LearningPath lp : profesor.getLearningPathsCreadosLista()) {
            comboBoxLearningPaths.addItem(lp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLearningPaths) {
            actualizar();
        } else if (e.getSource() == comboBoxActividades) {
            actualizarActividad();
        } else if (e.getSource() == botonCalificar) {
            calificarActividad();
        }
    }

    private void actualizar() {
        comboBoxActividades.removeAllItems();
        LearningPath lp = (LearningPath) comboBoxLearningPaths.getSelectedItem();
        Profesor profesor = dialogoManejarSeguimiento.getInicioProfesor().getProfesor();
        for (ActividadRealizable act : profesor.getActividadesPendientes()) {
            comboBoxActividades.addItem(act);
        }
    }

    private void actualizarActividad() {
        if (comboBoxActividades.getSelectedItem() == null) {
            return;
        }
        actividadRealizable = (ActividadRealizable) comboBoxActividades.getSelectedItem();
        panelActividad.cambiarActividad(actividadRealizable);
    }

    private void calificarActividad() {
        if (actividadRealizable instanceof TareaRealizable) {
            calificar();
        } else if (actividadRealizable instanceof EncuestaRealizable) {
            verEncuesta((EncuestaRealizable) actividadRealizable);
        } else if (actividadRealizable instanceof RecursoRealizable) {
            JOptionPane.showMessageDialog(this, "No se puede calificar un recurso");
        } else if (actividadRealizable instanceof QuizRealizable) {
            JOptionPane.showMessageDialog(this, "No se puede calificar un quiz");
        } else if (actividadRealizable instanceof ExamenRealizable) {
            JFrame panel = verExamen((ExamenRealizable) actividadRealizable);
            panel.addWindowListener(this);
        }
    }

    private void calificar() {
        String[] opciones = {"Exitoso", "No Exitoso"};
        int respuesta = JOptionPane.showOptionDialog(this, "Ingrese la calificación", "Calificar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        try {
            if (respuesta == 0) {
                actividadRealizable.setEstado("Exitoso");
            } else {
                actividadRealizable.setEstado("No exitoso");
            }
            actividadRealizable.calificarActividad();
        } catch (EstadoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void verEncuesta(EncuestaRealizable encuesta) {
        JFrame frame = new JFrame("Informacion encuesta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Titulo: " + encuesta.getActividadBase().getTitulo()));
        panel.add(new JLabel("Estudiante: " + encuesta.getEstudiante().getUsuario()));
        panel.add(new JLabel("Estado: " + encuesta.getEstado()));
        panel.add(new JLabel("Preguntas: "));

        List<PreguntaAbiertaRealizable> preguntas = encuesta.getPreguntasRealizadas();
        for (int i = 0; i < preguntas.size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = preguntas.get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            panel.add(new JLabel("Pregunta " + (i + 1) + ": " + preguntaBase.getEnunciado()));
            panel.add(new JLabel("Respuesta: " + preguntaRealizada.getRespuesta()));
        }

        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JFrame verExamen(ExamenRealizable examen) {
        JFrame frame = new JFrame("Informacion examen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Titulo: " + examen.getActividadBase().getTitulo()));
        panel.add(new JLabel("Estudiante: " + examen.getEstudiante().getUsuario()));
        panel.add(new JLabel("Estado: " + examen.getEstado()));
        panel.add(new JLabel("Preguntas: "));

        List<PreguntaAbiertaRealizable> preguntas = examen.getPreguntasRealizadas();
        for (int i = 0; i < preguntas.size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = preguntas.get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            panel.add(new JLabel("Pregunta " + (i + 1) + ": " + preguntaBase.getEnunciado()));
            panel.add(new JLabel("Respuesta: " + preguntaRealizada.getRespuesta()));
        }

        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.setVisible(true);
        return frame;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {
        calificar();
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}