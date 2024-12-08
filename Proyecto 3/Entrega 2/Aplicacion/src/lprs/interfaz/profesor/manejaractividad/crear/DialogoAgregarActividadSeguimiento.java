package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoAgregarActividadSeguimiento extends JDialog implements ActionListener {

    private JComboBox listaActividades;
    private JButton btnContinuar;
    private JButton btnVolver;
    private JDialog dialogoOrigen;
    private Actividad actividad;

    public DialogoAgregarActividadSeguimiento(JDialog dialogoOrigen, LearningPath lp, Actividad actividad) {
        this.dialogoOrigen = dialogoOrigen;
        this.actividad = actividad;

        setTitle("Agregar Actividad Seguimiento");
        setSize(300, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel lblSeleccionarActividad = new JLabel("Seleccionar Actividad Seguimiento:", SwingConstants.CENTER);
        lblSeleccionarActividad.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeleccionarActividad.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(lblSeleccionarActividad, BorderLayout.NORTH);

        listaActividades = new JComboBox<>();
        listaActividades.addItem("Seleccionar");
        for (Actividad act : lp.getActividades()) {
            if (act.equals(actividad) || act.getActividadesPrevias().contains(actividad)) {
                continue;
            }
            listaActividades.addItem(act);
        }
        listaActividades.setFont(new Font("Arial", Font.PLAIN, 16));
        listaActividades.setBackground(new Color(70, 130, 180));
        listaActividades.setForeground(Color.WHITE);
        add(listaActividades, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(this);
        panelBotones.add(btnContinuar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVolver.setBackground(new Color(220, 20, 60));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorderPainted(false);
        btnVolver.addActionListener(this);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
        } else if (e.getSource() == btnContinuar) {
            if ("Seleccionar".equals(listaActividades.getSelectedItem().toString())) {
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