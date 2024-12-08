package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoCrearActividad extends JDialog implements ActionListener {
    private PanelActividadBasica panelActividadBasica;
    private JComboBox<String> tipoActividad;


    private JComboBox<String> learningPaths;
    private JButton btnCrearActividad;
    private JButton btnCancelar;
    private JPanel panelDerecho;
    private String actividadSeleccionada;
    private DialogoManejarActividad dialogoManejarActividad;
    private LearningPath lp;
    public DialogoCrearActividad(LearningPath lp, DialogoManejarActividad dialogoManejarActividad) {
        this.actividadSeleccionada = "";
        this.dialogoManejarActividad = dialogoManejarActividad;
        this.lp = lp;
        setSize(600, 600);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Crear Actividad");
        add(titulo, BorderLayout.NORTH);
        panelActividadBasica = new PanelActividadBasica();
        tipoActividad = new JComboBox<>();
        tipoActividad.addItem("Seleccionar");
        tipoActividad.addItem("Encuesta");
        tipoActividad.addItem("Examen");
        tipoActividad.addItem("Recurso");
        tipoActividad.addItem("Quiz Verdadero/Falso");
        tipoActividad.addItem("Quiz Multiple");
        tipoActividad.addItem("Encuesta");
        tipoActividad.addActionListener(this);
        panelActividadBasica.add(new JLabel("Tipo de actividad:"));
        panelActividadBasica.add(tipoActividad);
        add(panelActividadBasica, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        btnCrearActividad = new JButton("Crear Actividad");
        btnCrearActividad.addActionListener(this);
        panelBotones.add(btnCrearActividad);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

    }




    private void crearActividad(JPanel panelDerecho) {
        String tipo = tipoActividad.getSelectedItem().toString();
        if (tipo.equals("Encuesta")) {

        } else if (tipo.equals("Examen")) {
        } else if (tipo.equals("Recurso")) {

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
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString(),calificacion);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(quizVerdaderoFalso);
            this.dispose();
            DialogoQuizVF verdaderoFalso = new DialogoQuizVF(quizVerdaderoFalso,this);
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
                        panelActividadBasica.getCmbDificultad().getSelectedItem().toString(),
                        calificacion);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            lp.aniadirActividad(quizMultiple);
            this.dispose();
            DialogoQuizMultiple multiple = new DialogoQuizMultiple(quizMultiple,this);
            multiple.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==btnCrearActividad) {
            crearActividad(panelDerecho);
        }
        else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
        else if (e.getSource() == tipoActividad) {
            actividadSeleccionada = tipoActividad.getSelectedItem().toString();
        }


    }

    public LearningPath getLp() {
        return lp;
    }
    public DialogoManejarActividad getDialogoManejarActividad() {
        return dialogoManejarActividad;
    }
}
