package lprs.interfaz.estudiante.realizar;

import javax.swing.*;

public class DialogoRealizarActividad extends JDialog {
    public DialogoRealizarActividad() {
        setTitle("Realizar Actividad");
        setSize(700, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Realizar Actividad"));
    }
}
