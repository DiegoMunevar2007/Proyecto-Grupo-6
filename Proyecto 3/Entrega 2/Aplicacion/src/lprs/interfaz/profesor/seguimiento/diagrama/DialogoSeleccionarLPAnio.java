package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeleccionarLPAnio extends JDialog implements ActionListener {
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
    private JComboBox learningPaths;
    private JComboBox<String> anios;
    private JButton btnAceptar;
    private JButton btnCancelar;
    public DialogoSeleccionarLPAnio(DialogoManejarSeguimiento dialogoManejarSeguimiento) {
        setTitle("Seleccionar Learning Path");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        JPanel panelCombo = new JPanel();
        this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;
        learningPaths = new JComboBox<>();
        learningPaths.setBounds(10, 10, 200, 20);
        learningPaths.addItem("Seleccione un Learning Path");
        learningPaths.addActionListener(this);
        for (LearningPath learningPath : dialogoManejarSeguimiento.getInicioProfesor().getProfesor().getLearningPathsCreadosLista()) {
            learningPaths.addItem(learningPath);
        }
        panelCombo.add(learningPaths);
        anios = new JComboBox<>();
        anios.setBounds(10, 40, 200, 20);
        anios.addItem("Seleccione un año");
        panelCombo.add(anios);
        panelCombo.setBounds(10, 10, 200, 100);
        add(panelCombo);
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(10, 120, 100, 20);
        btnAceptar.addActionListener(this);
        add(btnAceptar);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(120, 120, 100, 20);
        btnCancelar.addActionListener(this);
        add(btnCancelar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == learningPaths) {
            if (!"Seleccione un Learning Path".equals(learningPaths.getSelectedItem())) {
                LearningPath lp = (LearningPath) learningPaths.getSelectedItem();
                for (int anio : lp.getCantidadActividadesPorDia().keySet()) {
                    if (String.valueOf(anio).length()==4) {
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
