package lprs.interfaz.profesor.manejaractividad.ver;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.Actividad;

import javax.swing.*;
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
        panelComboBoxLPActividad = new PanelComboBoxLPActividad(dialogoManejarActividad);
        panelComboBoxLPActividad.getComboBoxActividades().addActionListener(this);
        add(panelComboBoxLPActividad, BorderLayout.WEST);
        panelVerActividad = new PanelVerActividad();
        add(panelVerActividad, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel(new FlowLayout());
        botonVolver = new JButton("Volver");
        botonEditar = new JButton("Editar");
        botonEliminar = new JButton("Eliminar");
        botonVolver.addActionListener(this);
        botonEditar.addActionListener(this);
        botonEliminar.addActionListener(this);
        panelBotones.add(botonVolver);
        panelBotones.add(botonEditar);
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
