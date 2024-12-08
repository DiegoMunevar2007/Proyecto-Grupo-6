package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.interfaz.profesor.manejaractividad.crear.DialogoAgregarActividadPrevia;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoAgregarActividadSeguimiento;
import lprs.interfaz.profesor.manejaractividad.crear.DialogoCrearActividad;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;

import javax.swing.*;
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
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPregunta = new JPanel(new BorderLayout(10, 10));
        JLabel lblPregunta = new JLabel("Pregunta:");
        panelPregunta.add(lblPregunta, BorderLayout.NORTH);
        txtPregunta = new JTextArea();
        panelPregunta.add(new JScrollPane(txtPregunta), BorderLayout.CENTER);
        add(panelPregunta, BorderLayout.CENTER);

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
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this, dialogoCrearActividad.getLp(), examen);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this, dialogoCrearActividad.getLp(), examen);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }
}