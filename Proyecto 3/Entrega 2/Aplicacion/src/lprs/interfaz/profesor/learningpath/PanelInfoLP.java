package lprs.interfaz.profesor.learningpath;

import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;

public class PanelInfoLP extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblDescripcion;
    private JLabel lblNivel;
    private JLabel lblObjetivos;
    private JLabel lblDuracion;
    private JLabel lblRating;
    private JLabel lblPalabrasClave;
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtNivel;
    private JTextArea txtObjetivos;
    private JTextField txtDuracion;
    private JTextField txtRating;
    private JTextArea txtPalabrasClave;
    public PanelInfoLP(boolean editable){
        setLayout(new GridLayout(7,2,30,30));
        lblTitulo = new JLabel("Título");
        txtTitulo = new JTextField();
        txtTitulo.setEditable(editable);
        add(lblTitulo);
        add(txtTitulo);
        lblDescripcion = new JLabel("Descripción");
        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(editable);
        add(lblDescripcion);
        add(txtDescripcion);

        lblNivel = new JLabel("Nivel de dificultad");
        txtNivel = new JTextField();
        txtNivel.setEditable(editable);
        add(lblNivel);
        add(txtNivel);

        lblObjetivos = new JLabel("Objetivos");
        txtObjetivos = new JTextArea();
        txtObjetivos.setEditable(editable);
        add(lblObjetivos);
        add(txtObjetivos);

        lblDuracion = new JLabel("Duración");
        txtDuracion = new JTextField();
        txtDuracion.setEditable(false);
        add(lblDuracion);
        add(txtDuracion);
        lblRating = new JLabel("Rating");
        txtRating = new JTextField();
        txtRating.setEditable(false);
        add(lblRating);
        add(txtRating);

        lblPalabrasClave = new JLabel("Palabras clave");
        txtPalabrasClave = new JTextArea();
        txtPalabrasClave.setEditable(editable);
        add(lblPalabrasClave);
        add(txtPalabrasClave);
    }
    public void cambiarInfo(LearningPath lp){
        txtTitulo.setText(lp.getTitulo());
        txtDescripcion.setText(lp.getDescripcion());
        txtNivel.setText(lp.getNivelDificultad());
        for (String objetivo : lp.getObjetivos()) {
            txtObjetivos.append(objetivo + "\n");
        }
        txtDuracion.setText(Integer.toString(lp.getDuracion()));
        txtRating.setText(String.valueOf(lp.getRating()));
        for (String palabraClave : lp.getKeyWords()) {
            txtPalabrasClave.append(palabraClave + "\n");
        }
    }
}
