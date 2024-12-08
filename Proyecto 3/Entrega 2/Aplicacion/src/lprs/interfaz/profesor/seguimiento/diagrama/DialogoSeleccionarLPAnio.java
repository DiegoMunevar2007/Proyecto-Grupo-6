package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeleccionarLPAnio extends JDialog implements ActionListener {
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
    private JComboBox  learningPaths;
    private JComboBox<String> anios;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public DialogoSeleccionarLPAnio(DialogoManejarSeguimiento dialogoManejarSeguimiento) {
        setTitle("Seleccionar Learning Path");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;

        JPanel panelCombo = new JPanel();
        panelCombo.setLayout(new GridLayout(2, 1, 10, 10));
        panelCombo.setBorder(new EmptyBorder(20, 20, 20, 20));

        learningPaths = new JComboBox<>();
        learningPaths.addItem("Seleccione un Learning Path");
        for (LearningPath learningPath : dialogoManejarSeguimiento.getInicioProfesor().getProfesor().getLearningPathsCreadosLista()) {
            learningPaths.addItem(learningPath);
        }
        learningPaths.addActionListener(this);
        panelCombo.add(learningPaths);

        anios = new JComboBox<>();
        anios.addItem("Seleccione un año");
        panelCombo.add(anios);

        add(panelCombo, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAceptar.setBackground(new Color(70, 130, 180));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBorderPainted(false);
        btnAceptar.addActionListener(this);
        panelBotones.add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == learningPaths) {
            anios.removeAllItems();
            anios.addItem("Seleccione un año");
            if (!"Seleccione un Learning Path".equals(learningPaths.getSelectedItem())) {
                LearningPath lp = (LearningPath) learningPaths.getSelectedItem();
                for (int anio : lp.getCantidadActividadesPorDia().keySet()) {
                    if (String.valueOf(anio).length() == 4) {
                        anios.addItem(String.valueOf(anio));
                    }
                }
            }
        } else if (e.getSource() == btnAceptar) {
            LearningPath lp = (LearningPath) learningPaths.getSelectedItem();
            int anio = Integer.parseInt((String) anios.getSelectedItem());
            DialogoDiagrama dialogoDiagrama = new DialogoDiagrama(dialogoManejarSeguimiento, lp, anio);
            dialogoDiagrama.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }
}