package lprs.interfaz.estudiante.avance.actividad;

import lprs.logica.contenido.realizable.ActividadRealizable;

import javax.swing.*;
import java.awt.*;

public class PanelTarea extends PanelActividad {
    private JLabel lblEstado;
    private JTextField txtEstado;
    public PanelTarea(ActividadRealizable actividadR) {
        super(actividadR.getActividadBase());
        lblEstado = new JLabel("Estado");
        lblEstado.setBounds(10, 260, 100, 20);
        add(lblEstado);
        txtEstado = new JTextField(actividadR.getEstado());
        txtEstado.setBounds(120, 260, 200, 20);
        add(txtEstado);
        setLayout(new GridLayout(4,1,30,30));
    }
}
