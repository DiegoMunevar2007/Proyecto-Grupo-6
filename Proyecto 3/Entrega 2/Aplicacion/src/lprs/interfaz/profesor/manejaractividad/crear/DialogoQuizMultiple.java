package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoQuizMultiple extends JDialog implements ActionListener {
    private JTextField txtPregunta;
    private JTextField[] txtOpciones;
    private JTextField[] txtJustificaciones;
    private JComboBox<String> cmbOpcionCorrecta;
    private JButton btnAgregarPregunta;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private QuizMultiple quizMultiple;
    private DialogoCrearActividad dialogoCrearActividad;
    public DialogoQuizMultiple(QuizMultiple quizMultiple, DialogoCrearActividad dialogoCrearActividad) {
        this.quizMultiple = quizMultiple;
        this.dialogoCrearActividad = dialogoCrearActividad;
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPregunta = new JPanel(new BorderLayout(10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        panelPregunta.add(lblPregunta, BorderLayout.WEST);
        txtPregunta = new JTextField();
        panelPregunta.add(txtPregunta, BorderLayout.CENTER);
        add(panelPregunta, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(8, 2, 10, 10));
        txtOpciones = new JTextField[4];
        txtJustificaciones = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            JLabel lblOpcion = new JLabel("Opción " + (i + 1) + ":");
            panelOpciones.add(lblOpcion);
            txtOpciones[i] = new JTextField();
            panelOpciones.add(txtOpciones[i]);
            JLabel lblJustificacion = new JLabel("Justificación " + (i + 1) + ":");
            panelOpciones.add(lblJustificacion);
            txtJustificaciones[i] = new JTextField();
            panelOpciones.add(txtJustificaciones[i]);
        }
        add(panelOpciones, BorderLayout.CENTER);

        JPanel panelOpcionCorrecta = new JPanel(new BorderLayout(10, 10));
        JLabel lblOpcionCorrecta = new JLabel("Opción Correcta:");
        panelOpcionCorrecta.add(lblOpcionCorrecta, BorderLayout.WEST);
        cmbOpcionCorrecta = new JComboBox<>(new String[]{"Opción 1", "Opción 2", "Opción 3", "Opción 4"});
        panelOpcionCorrecta.add(cmbOpcionCorrecta, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnCancelar = new JButton("Cancelar");
        btnContinuar = new JButton("Continuar");
        btnAgregarPregunta.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnContinuar.addActionListener(this);
        panelBotones.add(btnAgregarPregunta);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnContinuar);

        JPanel panelExtra = new JPanel(new BorderLayout(10, 10));
        panelExtra.add(panelOpcionCorrecta, BorderLayout.NORTH);
        panelExtra.add(panelBotones, BorderLayout.SOUTH);

        add(panelExtra, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            this.dispose();
        } else if (e.getSource() == btnAgregarPregunta) {
            Opcion[] opciones = new Opcion[4];
            for (int i = 0; i < 4; i++) {
                opciones[i] = new Opcion(txtOpciones[i].getText(),txtJustificaciones[i].getText());
            }
            Opcion opcionCorrecta = opciones[cmbOpcionCorrecta.getSelectedIndex()];
            quizMultiple.addPreguntaQuiz(new PreguntaCerrada(txtPregunta.getText(), opcionCorrecta, opciones));
            JOptionPane.showMessageDialog(this, "Pregunta agregada exitosamente");
            limpiarCampos();
        } else if (e.getSource() == btnContinuar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this, dialogoCrearActividad.getLp(), quizMultiple);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this, dialogoCrearActividad.getLp(), quizMultiple);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }
    public void limpiarCampos() {
        txtPregunta.setText("");
        for (int i = 0; i < 4; i++) {
            txtOpciones[i].setText("");
            txtJustificaciones[i].setText("");
        }
    }
}