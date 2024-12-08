package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoSeleccionarLP extends JDialog implements ActionListener {
    private JComboBox listaLP;
    private JButton btnContinuar;
    private JButton btnVolver;
    private DialogoManejarActividad dialogoManejarActividad;

    public DialogoSeleccionarLP(DialogoManejarActividad dialogoManejarActividad) {
        this.dialogoManejarActividad = dialogoManejarActividad;
        setSize(300, 300);
        setLayout(new BorderLayout());
        JLabel lblSeleccionarLP = new JLabel("Seleccionar Learning Path:");
        add(lblSeleccionarLP, BorderLayout.NORTH);
        listaLP = new JComboBox();
        listaLP.addItem("Seleccionar");
        for (LearningPath lp : dialogoManejarActividad.getInicioProfesor().getProfesor().getLearningPathsCreadosLista()) {
            listaLP.addItem(lp);
        }
        add(listaLP, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(this);
        panelBotones.add(btnContinuar);
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
            dialogoManejarActividad.setVisible(true);
        } else if (e.getSource() == btnContinuar) {
            if (listaLP.getSelectedItem().toString().equals("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Seleccione un Learning Path");
            } else {
                LearningPath lp = (LearningPath) listaLP.getSelectedItem();
                DialogoCrearActividad dialogoCrearActividad = new DialogoCrearActividad(lp, dialogoManejarActividad);
                dialogoCrearActividad.setVisible(true);
                this.dispose();
            }
        }
    }
}
