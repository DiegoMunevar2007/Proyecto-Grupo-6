package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoAgregarActividadSeguimiento extends JDialog implements ActionListener {

    private JComboBox listaActividades;
    private JButton btnContinuar;
    private JButton btnVolver;
    private JDialog dialogoOrigen;
    private Actividad actividad;
    public DialogoAgregarActividadSeguimiento(JDialog dialogoOrigen, LearningPath lp, Actividad actividad){
        this.dialogoOrigen = dialogoOrigen;
        this.actividad  = actividad;
        setSize(300, 300);
        setLayout(new BorderLayout());
        JLabel lblSeleccionarActividad = new JLabel("Seleccionar Actividad Seguimiento:",SwingConstants.CENTER);
        lblSeleccionarActividad.setBounds(10, 10, 200, 20);
        add(lblSeleccionarActividad, BorderLayout.NORTH);
        listaActividades = new JComboBox();
        listaActividades.setBounds(10, 40, 200, 20);
        listaActividades.addItem("Seleccionar");
        for (Actividad act : lp.getActividades()) {
            if (act.equals(actividad) || act.getActividadesPrevias().contains(actividad)) {
                continue;
            }
            listaActividades.addItem(act);
        }
        add(listaActividades, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
         btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(this);
        panelBotones.add(btnContinuar);
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnVolver) {
            this.dispose();
        } else if (e.getSource() == btnContinuar) {
            if (listaActividades.getSelectedItem().toString().equals("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Seleccione una Actividad");
            } else {
                Actividad act = (Actividad) listaActividades.getSelectedItem();
                actividad.addActividadPrevia(act);
                dialogoOrigen.setVisible(true);
                this.dispose();
            }
        }
    }
}
