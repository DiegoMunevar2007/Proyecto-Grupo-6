package lprs.interfaz.profesor.manejaractividad.ver;

import com.jcalendar.pane.calendar.CalendarPane;
import lprs.interfaz.estudiante.avance.actividad.PanelActividad;
import lprs.interfaz.profesor.manejaractividad.crear.PanelActividadBasica;
import lprs.logica.contenido.Actividad;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelVerActividad extends JPanel {
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtObjetivo;
    private JTextField txtDuracion;
    private JCheckBox checkObligatoria;
    private JTextField txtFecha;
    private JComboBox<String> cmbDificultad;
    public PanelVerActividad() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Actividad"));
        JPanel panelActividad = new JPanel(new GridLayout(4,3,10,10));
        JLabel lblTitulo = new JLabel("Título:");
        panelActividad.add(lblTitulo);
        txtTitulo = new JTextField();
        panelActividad.add(txtTitulo);
        JLabel lblDescripcion = new JLabel("Descripción:");
        panelActividad.add(lblDescripcion);
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        panelActividad.add(new JScrollPane(txtDescripcion));
        JLabel lblObjetivo = new JLabel("Objetivo:");
        panelActividad.add(lblObjetivo);
        txtObjetivo = new JTextField();
        panelActividad.add(txtObjetivo);
        JLabel lblDuracion = new JLabel("Duración:");
        panelActividad.add(lblDuracion);
        txtDuracion = new JTextField();
        panelActividad.add(txtDuracion);
        JLabel lblObligatoria = new JLabel("Obligatoria:");
        panelActividad.add(lblObligatoria);
        checkObligatoria = new JCheckBox();
        panelActividad.add(checkObligatoria);
        JLabel lblFecha = new JLabel("Fecha:");
        panelActividad.add(lblFecha);
        txtFecha = new JTextField();
        panelActividad.add(txtFecha);
        JLabel lblDificultad = new JLabel("Dificultad:");
        panelActividad.add(lblDificultad);
        cmbDificultad = new JComboBox<>();
        cmbDificultad.addItem("Principiante");
        cmbDificultad.addItem("Intermedio");
        cmbDificultad.addItem("Avanzado");
        panelActividad.add(cmbDificultad);
        add(panelActividad, BorderLayout.CENTER);
    }
    public void actualizarActividad(Actividad actividad){
        txtTitulo.setText(actividad.getTitulo());
        txtDescripcion.setText(actividad.getDescripcion());
        txtObjetivo.setText(actividad.getObjetivo());
        txtDuracion.setText(String.valueOf(actividad.getDuracionEsperada()));
        checkObligatoria.setSelected(actividad.isObligatoria());
        txtFecha.setText(actividad.getFechaLimite());
        cmbDificultad.setSelectedItem(actividad.getNivelDificultad());
    }


    public JComboBox<String> getCmbDificultad() {
        return cmbDificultad;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JCheckBox getCheckObligatoria() {
        return checkObligatoria;
    }

    public JTextField getTxtDuracion() {
        return txtDuracion;
    }

    public JTextField getTxtObjetivo() {
        return txtObjetivo;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }
}
