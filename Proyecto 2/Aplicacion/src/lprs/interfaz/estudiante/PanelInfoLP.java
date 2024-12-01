package lprs.interfaz.estudiante;

import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;

public class PanelInfoLP extends JPanel {

    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtNivelDificultad;
    private JTextArea txtObjetivos;
    private JTextArea txtDuracion;
    private JTextArea txtRating;

    public PanelInfoLP() {
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Título"));
        txtTitulo = new JTextField();
        txtTitulo.setEditable(false);
        add(txtTitulo);

        add(new JLabel("Descripción"));
        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        add(txtDescripcion);
        add(new JLabel("Nivel de dificultad"));
        txtNivelDificultad = new JTextField();
        txtNivelDificultad.setEditable(false);
        add(txtNivelDificultad);
        add(new JLabel("Objetivos"));
        txtObjetivos = new JTextArea();
        txtObjetivos.setEditable(false);
        add(txtObjetivos);
        add(new JLabel("Duración"));
        txtDuracion = new JTextArea();
        txtDuracion.setEditable(false);
        add(txtDuracion);
        add(new JLabel("Rating"));
        txtRating = new JTextArea();
        txtRating.setEditable(false);
        add(txtRating);
    }
    public void cambiarInformacion(LearningPath lp){
        txtTitulo.setText(lp.getTitulo());
        txtDescripcion.setText(lp.getDescripcion());
        txtNivelDificultad.setText(lp.getNivelDificultad());
        txtObjetivos.setText("");
        for (String objetivo : lp.getObjetivos()) {
            txtObjetivos.append(objetivo + "\n");
        }
        txtDuracion.setText(Integer.toString(lp.getDuracion()));
        txtRating.setText(String.valueOf(lp.getRating()));
    }
}
