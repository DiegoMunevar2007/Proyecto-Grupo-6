package lprs.interfaz.profesor.manejaractividad.crear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoCrearActividad extends JDialog implements ActionListener {
    private PanelActividadBasica panelActividadBasica;
    private JComboBox<String> tipoActividad;
    private JPanel panelDerecho;
    public DialogoCrearActividad() {
        setSize(600, 600);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Crear Actividad");
        add(titulo, BorderLayout.NORTH);
        panelActividadBasica = new PanelActividadBasica();
        add(panelActividadBasica, BorderLayout.WEST);

        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());
        JLabel lblTipoActividad = new JLabel("Tipo de actividad:");
        panelDerecho.add(lblTipoActividad, BorderLayout.NORTH);
        tipoActividad = new JComboBox<>();
        tipoActividad.addItem("Seleccionar");
        tipoActividad.addItem("Encuesta");
        tipoActividad.addItem("Actividad");
        tipoActividad.addActionListener(this);
        panelDerecho.add(tipoActividad, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.CENTER);

    }




    private void crearActividad(JPanel panelDerecho) {
        panelDerecho.removeAll();
        String tipo = tipoActividad.getSelectedItem().toString();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        if (tipo.equals("Encuesta")) {
            PanelEncuesta panelEncuesta = new PanelEncuesta();
            panel.add(panelEncuesta, BorderLayout.CENTER);
            PanelBotonesPregunta panelBotonesPregunta = new PanelBotonesPregunta();
            panelBotonesPregunta.addListener(this);
            panel.add(panelBotonesPregunta, BorderLayout.SOUTH);
        } else {

        }
        panelDerecho.add(panel, BorderLayout.CENTER);
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==tipoActividad) {
            crearActividad(panelDerecho);
        }
    }
}
