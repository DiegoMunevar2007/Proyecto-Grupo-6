package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setTitle("Seleccionar Learning Path");
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JLabel lblSeleccionarLP = new JLabel("Seleccionar Learning Path:", SwingConstants.CENTER);
        lblSeleccionarLP.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeleccionarLP.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(lblSeleccionarLP, BorderLayout.NORTH);

        listaLP = new JComboBox<>();
        listaLP.addItem("Seleccionar un Learning Path");
        for (LearningPath lp : dialogoManejarActividad.getInicioProfesor().getProfesor().getLearningPathsCreadosLista()) {
            listaLP.addItem(lp);
        }
        listaLP.setFont(new Font("Arial", Font.PLAIN, 14));
        listaLP.setBackground(new Color(70, 130, 180));
        listaLP.setForeground(Color.WHITE);
        add(listaLP, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(this);
        panelBotones.add(btnContinuar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 14));
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