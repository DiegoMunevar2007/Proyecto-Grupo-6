package lprs.interfaz.profesor.manejaractividad.crear;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelBotonesPregunta extends JPanel {
    JButton btnAgregarPregunta;
    JButton btnVolver;

    public PanelBotonesPregunta() {
        btnAgregarPregunta = new JButton("Agregar Pregunta");
        add(btnAgregarPregunta);
        btnVolver = new JButton("Volver");
        add(btnVolver);
    }
    public  JButton getBtnAgregarPregunta() {
        return btnAgregarPregunta;
    }
    public JButton getBtnVolver() {
        return btnVolver;
    }
    public void addListener(ActionListener listener) {
        btnAgregarPregunta.addActionListener(listener);
        btnVolver.addActionListener(listener);
    }
}
