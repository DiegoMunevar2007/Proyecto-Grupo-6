package lprs.interfaz.profesor.learningpath.manejarLP;

import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;

public class PanelInfoLP extends JPanel {
    private JTextField titulo;
    private JTextArea descripcion;
    private JComboBox<String> dificultad;
    private JTextArea objetivos;
    private JTextField duracion;
    private JTextField rating;
    private JTextArea palabrasClave;
    public PanelInfoLP() {
        setLayout(new GridLayout(7, 2,20,20));
        JLabel nombreLabel = new JLabel("Titulo");
        add(nombreLabel);
        titulo = new JTextField("");
        add(titulo);
        JLabel descripcionLabel = new JLabel("Descripcion");
        add(descripcionLabel);
        descripcion = new JTextArea("");
        add(descripcion);
        JLabel dificultadLabel = new JLabel("Dificultad");
        add(dificultadLabel);
        dificultad = new JComboBox<>();
        dificultad.addItem("Principiante");
        dificultad.addItem("Intermedio");
        dificultad.addItem("Avanzado");
        dificultad.setSelectedItem("");
        add(dificultad);
        JLabel objetivosLabel = new JLabel("Objetivos");
        add(objetivosLabel);
        objetivos = new JTextArea("");
        add(objetivos);
        JLabel duracionLabel = new JLabel("Duracion");
        add(duracionLabel);
        duracion = new JTextField("");
        duracion.setEditable(false);
        add(duracion);
        JLabel ratingLabel = new JLabel("Rating");
        add(ratingLabel);
        rating = new JTextField("");
        rating.setEditable(false);
        add(rating);
        JLabel palabrasClaveLabel = new JLabel("Palabras Clave");
        add(palabrasClaveLabel);
        palabrasClave = new JTextArea("");
        add(palabrasClave);
    }
    public void actualizarLP(LearningPath lp) {
        titulo.setText(lp.getTitulo());
        descripcion.setText(lp.getDescripcion());
        dificultad.setSelectedItem(lp.getNivelDificultad());
        objetivos.setText("");
        for (String objetivo : lp.getObjetivos()) {
            objetivos.append(objetivo + "\n");
        }
        duracion.setText(String.valueOf(lp.getDuracion()));
        rating.setText(String.valueOf(lp.getRating()));
        palabrasClave.setText("");
        for (String palabraClave : lp.getKeyWords()) {
            palabrasClave.append(palabraClave + "\n");
        }
    }
    public String getTitulo() {
        return titulo.getText();
    }
    public String getDescripcion() {
        return descripcion.getText();
    }
    public String getDificultad() {
        return (String) dificultad.getSelectedItem();
    }
    public String getObjetivos() {
        return objetivos.getText();
    }
    public String getDuracion() {
        return duracion.getText();
    }
    public String getRating() {
        return rating.getText();
    }
    public String getPalabrasClave() {
        return palabrasClave.getText();
    }

}
