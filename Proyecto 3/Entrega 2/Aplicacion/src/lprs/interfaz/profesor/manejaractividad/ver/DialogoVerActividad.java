package lprs.interfaz.profesor.manejaractividad.ver;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.Actividad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoVerActividad extends JDialog implements ActionListener {
    private PanelComboBoxLPActividad panelComboBoxLPActividad;
    private PanelVerActividad panelVerActividad;
    private DialogoManejarActividad dialogoManejarActividad;
    private JButton botonVolver;
    private JButton botonEditar;
    private JButton botonEliminar;

    public DialogoVerActividad(DialogoManejarActividad dialogoManejarActividad) {
        this.dialogoManejarActividad = dialogoManejarActividad;
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        panelComboBoxLPActividad = new PanelComboBoxLPActividad(dialogoManejarActividad);
        panelComboBoxLPActividad.getComboBoxActividades().addActionListener(this);
        add(panelComboBoxLPActividad, BorderLayout.WEST);

        panelVerActividad = new PanelVerActividad();
        add(panelVerActividad, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(this);
        botonVolver.setFont(new Font("Arial", Font.PLAIN, 16));
        botonVolver.setBackground(new Color(105, 105, 105));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setBorderPainted(false);
        panelBotones.add(botonVolver);

        botonEditar = new JButton("Editar");
        botonEditar.addActionListener(this);
        botonEditar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonEditar.setBackground(new Color(70, 130, 180));
        botonEditar.setForeground(Color.WHITE);
        botonEditar.setBorderPainted(false);
        panelBotones.add(botonEditar);

        botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(this);
        botonEliminar.setFont(new Font("Arial", Font.PLAIN, 16));
        botonEliminar.setBackground(new Color(220, 20, 60));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setBorderPainted(false);
        panelBotones.add(botonEliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    public PanelVerActividad getPanelVerActividad() {
        return panelVerActividad;
    }

    public PanelComboBoxLPActividad getPanelComboBoxLPActividad() {
        return panelComboBoxLPActividad;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelComboBoxLPActividad.getComboBoxActividades()) {
            Actividad actividad = (Actividad) panelComboBoxLPActividad.getComboBoxActividades().getSelectedItem();
            if (actividad != null) {
                panelVerActividad.actualizarActividad(actividad);
            }
        } else if (e.getSource() == botonVolver) {
            this.dispose();
            dialogoManejarActividad.setVisible(true);
        } else if (e.getSource() == botonEditar) {
            Actividad actividad = (Actividad) panelComboBoxLPActividad.getComboBoxActividades().getSelectedItem();
            if (actividad != null) {
                actividad.setTitulo(panelVerActividad.getTxtTitulo().getText());
                actividad.setDescripcion(panelVerActividad.getTxtDescripcion().getText());
                actividad.setFechaLimite(panelVerActividad.getTxtFecha().getText());
                actividad.setObjetivo(panelVerActividad.getTxtObjetivo().getText());
                actividad.setDuracionEsperada(Integer.parseInt(panelVerActividad.getTxtDuracion().getText()));
                actividad.setObligatoria(panelVerActividad.getCheckObligatoria().isSelected());
                actividad.setFechaLimite(panelVerActividad.getTxtFecha().getText());
                actividad.setNivelDificultad(panelVerActividad.getCmbDificultad().getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Actividad editada exitosamente");
                this.dispose();
            }
        } else if (e.getSource() == botonEliminar) {
            Actividad actividad = (Actividad) panelComboBoxLPActividad.getComboBoxActividades().getSelectedItem();
            if (actividad != null) {
                actividad.getLearningPathAsignado().eliminarActividad(actividad);
                JOptionPane.showMessageDialog(this, "Actividad eliminada exitosamente");
            }
        }
    }
}