package lprs.interfaz.profesor.manejaractividad.crear;

import com.jcalendar.event.CalendarEvent;
import com.jcalendar.pane.calendar.CalendarPane;
import com.jcalendar.pane.calendar.CalendarSelectionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelActividadBasica extends JPanel implements CalendarSelectionListener {
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtObjetivo;
    private JTextField txtDuracion;
    private JCheckBox checkObligatoria;
    private CalendarPane calendario;
    private JComboBox<String> cmbDificultad;
    private String fecha;

    public PanelActividadBasica() {
        fecha = "";
        setLayout(new GridLayout(4 ,4, 10, 10));
        setPreferredSize(new Dimension(400, 300));
        setBorder( new CompoundBorder(new EmptyBorder(10, 0, 10, 10),new TitledBorder("Actividad")));
        JLabel lblTitulo = new JLabel("Título:");
        add(lblTitulo);
        txtTitulo = new JTextField();
        add(txtTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        add(lblDescripcion);
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        add(txtDescripcion);

        JLabel lblObjetivo = new JLabel("Objetivo:");
        add(lblObjetivo);
        txtObjetivo = new JTextField();
        add(txtObjetivo);


        JLabel lblDuracion = new JLabel("Duración:");
        add(lblDuracion);
        txtDuracion = new JTextField();
        add(txtDuracion);

        JLabel lblObligatoria = new JLabel("Obligatoria:");
        add(lblObligatoria);
        checkObligatoria = new JCheckBox();
        add(checkObligatoria);

        JLabel lblFecha = new JLabel("Fecha:");
        add(lblFecha);
        calendario = new CalendarPane();
        calendario.addCalendarSelectionListener(this);
        add(calendario);

        JLabel lblDificultad = new JLabel("Dificultad:");
        add(lblDificultad);
        cmbDificultad = new JComboBox<>();
        cmbDificultad.addItem("Principiante");
        cmbDificultad.addItem("Intermedio");
        cmbDificultad.addItem("Avanzado");
        add(cmbDificultad);

    }
    @Override
    public void selectionChanged(CalendarEvent calendarEvent) {
        if (calendarEvent.getDate() != null) {
          fecha = calendarEvent.getDay() + "/" + calendarEvent.getMonth() + "/" + calendarEvent.getYear();
        }
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public JComboBox<String> getCmbDificultad() {
        return cmbDificultad;
    }

    public CalendarPane getCalendario() {
        return calendario;
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
    public String getFecha() {
        return fecha;
    }
}
