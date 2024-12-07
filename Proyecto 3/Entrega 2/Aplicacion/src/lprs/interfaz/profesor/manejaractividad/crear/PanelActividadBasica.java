package lprs.interfaz.profesor.manejaractividad.crear;

import com.jcalendar.event.CalendarEvent;
import com.jcalendar.pane.calendar.CalendarPane;
import com.jcalendar.pane.calendar.CalendarSelectionListener;

import javax.swing.*;
import java.awt.*;

public class PanelActividadBasica extends JPanel implements CalendarSelectionListener {
    JTextField txtTitulo;
    JTextArea txtDescripcion;
    JTextField txtDuracion;
    JCheckBox checkObligatoria;
    CalendarPane calendario;


    public PanelActividadBasica() {
        setLayout(new GridLayout(5,2, 10, 10));
        JLabel lblTitulo = new JLabel("Título:");
        add(lblTitulo);
        txtTitulo = new JTextField();
        add(txtTitulo);
        JLabel lblDescripcion = new JLabel("Descripción:");
        add(lblDescripcion);
        txtDescripcion = new JTextArea();
        add(txtDescripcion);
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
    }
    @Override
    public void selectionChanged(CalendarEvent calendarEvent) {
        if (calendarEvent.getDate() != null) {
            JOptionPane.showMessageDialog(this, "Fecha seleccionada: " + calendarEvent.getDate());
        }
    }
}
