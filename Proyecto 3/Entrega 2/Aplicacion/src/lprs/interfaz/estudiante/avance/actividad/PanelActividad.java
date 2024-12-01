package lprs.interfaz.estudiante.avance.actividad;

import lprs.logica.contenido.Actividad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelActividad extends JPanel {

    JLabel lblTitulo;
    JLabel lblDescripcion;
    JLabel lblObjetivo;
    JTextField txtTitulo;
    JTextArea txtDescripcion;
    JTextArea txtObjetivo;
    public PanelActividad(Actividad actividad) {
        setLayout(null);
        setBorder( new EmptyBorder(20, 20, 20, 20));
        lblTitulo = new JLabel("Titulo");
        lblTitulo.setBounds(10, 10, 100, 20);
        add(lblTitulo);
        txtTitulo = new JTextField(actividad.getTitulo());
        txtTitulo.setBounds(120, 10, 200, 20);
        add(txtTitulo);
        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setBounds(10, 40, 100, 20);
        add(lblDescripcion);
        txtDescripcion = new JTextArea(actividad.getDescripcion());
        txtDescripcion.setBounds(120, 40, 200, 100);
        add(txtDescripcion);
        lblObjetivo = new JLabel("Objetivo");
        lblObjetivo.setBounds(10, 150, 100, 20);
        add(lblObjetivo);
        txtObjetivo = new JTextArea(actividad.getObjetivo());
        txtObjetivo.setBounds(120, 150, 200, 100);
        add(txtObjetivo);
    }
}
