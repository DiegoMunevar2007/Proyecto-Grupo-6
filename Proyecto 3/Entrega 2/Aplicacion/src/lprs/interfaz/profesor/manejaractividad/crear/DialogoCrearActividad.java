package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.*;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DialogoCrearActividad extends JDialog implements ActionListener, WindowListener {
    private PanelActividadBasica panelActividadBasica;
    private JComboBox<String> tipoActividad;
    private JButton btnCrearActividad;
    private JButton btnCancelar;
    private DialogoManejarActividad dialogoManejarActividad;
    private LearningPath lp;

    public DialogoCrearActividad(LearningPath lp, DialogoManejarActividad dialogoManejarActividad) {
        this.dialogoManejarActividad = dialogoManejarActividad;
        this.lp = lp;
        setTitle("Crear Actividad");
        setSize(900, 900);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Crear Actividad", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        panelActividadBasica = new PanelActividadBasica();
        tipoActividad = new JComboBox<>();
        tipoActividad.addItem("Seleccionar");
        tipoActividad.addItem("Encuesta");
        tipoActividad.addItem("Examen");
        tipoActividad.addItem("Recurso");
        tipoActividad.addItem("Quiz Verdadero/Falso");
        tipoActividad.addItem("Quiz Multiple");
        tipoActividad.addItem("Tarea");
        tipoActividad.setFont(new Font("Arial", Font.PLAIN, 16));
        tipoActividad.setBackground(new Color(70, 130, 180));
        tipoActividad.setForeground(Color.WHITE);
        tipoActividad.addActionListener(this);
        panelActividadBasica.add(new JLabel("Tipo de actividad:"));
        panelActividadBasica.add(tipoActividad);
        add(panelActividadBasica, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnCrearActividad = new JButton("Crear Actividad");
        btnCrearActividad.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCrearActividad.setBackground(new Color(34, 139, 34));
        btnCrearActividad.setForeground(Color.WHITE);
        btnCrearActividad.setBorderPainted(false);
        btnCrearActividad.addActionListener(this);
        panelBotones.add(btnCrearActividad);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
        this.addWindowListener(this);
    }

    private void crearActividad() {
        String tipo = tipoActividad.getSelectedItem().toString();
        if (tipo.equals("Encuesta")) {
            Encuesta encuesta = null;
            try {
                encuesta = new Encuesta(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(encuesta);
            this.dispose();
            DialogoEncuesta encuestaDialogo = new DialogoEncuesta(encuesta, this);
            encuestaDialogo.setVisible(true);
        } else if (tipo.equals("Examen")) {
            Examen examen = null;
            try {
                examen = new Examen(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(examen);
            this.dispose();
            DialogoExamen examenDialogo = new DialogoExamen(examen, this);
            examenDialogo.setVisible(true);
        } else if (tipo.equals("Recurso")) {
            String tipoRecurso = JOptionPane.showInputDialog("Ingrese el tipo de recurso");
            String link = JOptionPane.showInputDialog("Ingrese el link del recurso");
            RecursoEducativo recurso = null;
            try {
                recurso = new RecursoEducativo(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString(), tipoRecurso, link);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(recurso);
            this.dispose();
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this.dialogoManejarActividad, lp, recurso);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this.dialogoManejarActividad, lp, recurso);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoManejarActividad.setVisible(true);
        } else if (tipo.equals("Quiz Verdadero/Falso")) {
            float calificacion = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la calificación mínima para aprobar el quiz"));
            QuizVerdaderoFalso quizVerdaderoFalso = null;
            try {
                quizVerdaderoFalso = new QuizVerdaderoFalso(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString(), calificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(quizVerdaderoFalso);
            this.dispose();
            DialogoQuizVF verdaderoFalso = new DialogoQuizVF(quizVerdaderoFalso, this);
            verdaderoFalso.setVisible(true);
        } else if (tipo.equals("Quiz Multiple")) {
            float calificacion = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la calificación mínima para aprobar el quiz"));
            QuizMultiple quizMultiple = null;
            try {
                quizMultiple = new QuizMultiple(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString(), calificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(quizMultiple);
            this.dispose();
            DialogoQuizMultiple multiple = new DialogoQuizMultiple(quizMultiple, this);
            multiple.setVisible(true);
        } else if (tipo.equals("Tarea")) {
            Tarea tarea = null;
            try {
                tarea = new Tarea(panelActividadBasica.getTxtTitulo().getText(),
                        panelActividadBasica.getTxtDescripcion().getText(),
                        panelActividadBasica.getTxtObjetivo().getText(),
                        Integer.parseInt(panelActividadBasica.getTxtDuracion().getText()),
                        panelActividadBasica.getCheckObligatoria().isSelected(), panelActividadBasica.getFecha(),
                        lp,
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(tarea);
            this.dispose();
            DialogoTarea tareaDialogo = new DialogoTarea(this, tarea);
            tareaDialogo.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrearActividad) {
            crearActividad();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
            dialogoManejarActividad.setVisible(true);
        }
    }

    public LearningPath getLp() {
        return lp;
    }

    public DialogoManejarActividad getDialogoManejarActividad() {
        return dialogoManejarActividad;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}