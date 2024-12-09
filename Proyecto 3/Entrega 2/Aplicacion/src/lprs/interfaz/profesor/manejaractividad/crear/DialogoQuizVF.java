package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setTitle("Crear Quiz Verdadero/Falso");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel panelGrid = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelGrid.add(lblPregunta);
        txtPregunta = new JTextField();
        txtPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        panelGrid.add(txtPregunta);
        JLabel lblRespuesta = new JLabel("Respuesta:");
        lblRespuesta.setFont(new Font("Arial", Font.BOLD, 16));
        lblRespuesta.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelGrid.add(lblRespuesta);
        verdaderoFalso = new JComboBox<>(new String[]{"Verdadero", "Falso"});
        verdaderoFalso.setFont(new Font("Arial", Font.PLAIN, 14));
        verdaderoFalso.setBackground(new Color(70, 130, 180));
        verdaderoFalso.setForeground(Color.WHITE);
        panelGrid.add(verdaderoFalso);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnAgregarPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAgregarPregunta.setBackground(new Color(70, 130, 180));
        btnAgregarPregunta.setForeground(Color.WHITE);
        btnAgregarPregunta.setBorderPainted(false);
        btnAgregarPregunta.addActionListener(this);
        panelButtons.add(btnAgregarPregunta);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this);
        panelButtons.add(btnCancelar);

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(this);
        panelButtons.add(btnContinuar);

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
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this.dialogoCrearActividad.getDialogoManejarActividad(), dialogoCrearActividad.getLp(), quizVF);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this.dialogoCrearActividad.getDialogoManejarActividad(), dialogoCrearActividad.getLp(), quizVF);
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