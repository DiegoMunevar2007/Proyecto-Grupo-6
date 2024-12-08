package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setTitle("Crear Quiz Multiple");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel panelPregunta = new JPanel(new BorderLayout(10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelPregunta.add(lblPregunta, BorderLayout.NORTH);
        txtPregunta = new JTextField();
        txtPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPregunta.add(txtPregunta, BorderLayout.CENTER);
        add(panelPregunta, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(8, 2, 10, 10));
        txtOpciones = new JTextField[4];
        txtJustificaciones = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            JLabel lblOpcion = new JLabel("Opción " + (i + 1) + ":");
            lblOpcion.setFont(new Font("Arial", Font.PLAIN, 14));
            panelOpciones.add(lblOpcion);
            txtOpciones[i] = new JTextField();
            txtOpciones[i].setFont(new Font("Arial", Font.PLAIN, 14));
            panelOpciones.add(txtOpciones[i]);
            JLabel lblJustificacion = new JLabel("Justificación " + (i + 1) + ":");
            lblJustificacion.setFont(new Font("Arial", Font.PLAIN, 14));
            panelOpciones.add(lblJustificacion);
            txtJustificaciones[i] = new JTextField();
            txtJustificaciones[i].setFont(new Font("Arial", Font.PLAIN, 14));
            panelOpciones.add(txtJustificaciones[i]);
        }
        add(panelOpciones, BorderLayout.CENTER);

        JPanel panelOpcionCorrecta = new JPanel(new BorderLayout(10, 10));
        JLabel lblOpcionCorrecta = new JLabel("Opción Correcta:");
        lblOpcionCorrecta.setFont(new Font("Arial", Font.BOLD, 16));
        lblOpcionCorrecta.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelOpcionCorrecta.add(lblOpcionCorrecta, BorderLayout.WEST);
        cmbOpcionCorrecta = new JComboBox<>(new String[]{"Opción 1", "Opción 2", "Opción 3", "Opción 4"});
        cmbOpcionCorrecta.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbOpcionCorrecta.setBackground(new Color(70, 130, 180));
        cmbOpcionCorrecta.setForeground(Color.WHITE);
        panelOpcionCorrecta.add(cmbOpcionCorrecta, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnAgregarPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAgregarPregunta.setBackground(new Color(70, 130, 180));
        btnAgregarPregunta.setForeground(Color.WHITE);
        btnAgregarPregunta.setBorderPainted(false);
        btnAgregarPregunta.addActionListener(this);
        panelBotones.add(btnAgregarPregunta);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnCancelar);

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(this);
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
                opciones[i] = new Opcion(txtOpciones[i].getText(), txtJustificaciones[i].getText());
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