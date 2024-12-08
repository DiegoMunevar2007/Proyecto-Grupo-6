package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoQuizVF extends JDialog implements ActionListener {
    private JTextField txtPregunta;
    private JComboBox<String> verdaderoFalso;
    private JButton btnAgregarPregunta;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private QuizVerdaderoFalso quizVF;
    private DialogoCrearActividad dialogoCrearActividad;
    public DialogoQuizVF(QuizVerdaderoFalso quizVF, DialogoCrearActividad dialogoCrearActividad) {
        this.quizVF = quizVF;
        this.dialogoCrearActividad = dialogoCrearActividad;
        setLayout(new BorderLayout(10, 10));
        setSize(400, 400);
        JPanel panelGrid = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        panelGrid.add(lblPregunta);
        txtPregunta = new JTextField();
        panelGrid.add(txtPregunta);
        JLabel lblRespuesta = new JLabel("Respuesta:");
        panelGrid.add(lblRespuesta);
        verdaderoFalso = new JComboBox<>();
        verdaderoFalso.addItem("Verdadero");
        verdaderoFalso.addItem("Falso");
        panelGrid.add(verdaderoFalso);

        // Panel for the buttons
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnAgregarPregunta.addActionListener(this);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(this);
        panelButtons.add(btnAgregarPregunta);
        panelButtons.add(btnCancelar);
        panelButtons.add(btnContinuar);

        // Add the panels to the main panel
        add(panelGrid, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            this.dispose();
        } else if (e.getSource() == btnAgregarPregunta) {
            Opcion[] opciones = new Opcion[2];
            opciones[0] = new Opcion("Verdadero", "");
            opciones[1] = new Opcion("Falso", "");
            if (verdaderoFalso.getSelectedIndex() == 0) {
                quizVF.addPreguntaQuiz(new PreguntaCerrada(txtPregunta.getText(), opciones[0], opciones));
            } else {
                quizVF.addPreguntaQuiz(new PreguntaCerrada(txtPregunta.getText(), opciones[1], opciones));
            }
            JOptionPane.showMessageDialog(this, "Pregunta agregada exitosamente");
            limpiarCampos();
        } else if (e.getSource() == btnContinuar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this, dialogoCrearActividad.getLp(), quizVF);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this, dialogoCrearActividad.getLp(), quizVF);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }
    public void limpiarCampos() {
        txtPregunta.setText("");
    }
}