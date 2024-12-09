package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogoExamen extends JDialog implements ActionListener {
    private JTextArea txtPregunta;
    private JButton btnAgregarPregunta;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private Examen examen;
    private DialogoCrearActividad dialogoCrearActividad;
    private ArrayList<PreguntaAbierta> preguntas;

    public DialogoExamen(Examen examen, DialogoCrearActividad dialogoCrearActividad) {
        this.examen = examen;
        this.dialogoCrearActividad = dialogoCrearActividad;
        this.preguntas = new ArrayList<>();
        setTitle("Crear Examen");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel panelPregunta = new JPanel(new BorderLayout(10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelPregunta.add(lblPregunta, BorderLayout.NORTH);
        txtPregunta = new JTextArea();
        txtPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPregunta.add(new JScrollPane(txtPregunta), BorderLayout.CENTER);
        add(panelPregunta, BorderLayout.CENTER);

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

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            this.dispose();
        } else if (e.getSource() == btnAgregarPregunta) {
            String textoPregunta = txtPregunta.getText();
            if (!textoPregunta.isEmpty()) {
                PreguntaAbierta pregunta = new PreguntaAbierta(textoPregunta);
                preguntas.add(pregunta);
                examen.addPreguntaExamen(pregunta);
                JOptionPane.showMessageDialog(this, "Pregunta agregada exitosamente");
                txtPregunta.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El texto de la pregunta no puede estar vacío");
            }
        } else if (e.getSource() == btnContinuar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this.dialogoCrearActividad.getDialogoManejarActividad(), dialogoCrearActividad.getLp(), examen);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this.dialogoCrearActividad.getDialogoManejarActividad(), dialogoCrearActividad.getLp(), examen);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }
}