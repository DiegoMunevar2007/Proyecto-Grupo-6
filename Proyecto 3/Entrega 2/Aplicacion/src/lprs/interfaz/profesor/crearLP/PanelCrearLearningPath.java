package lprs.interfaz.profesor.crearLP;

import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearLearningPath extends JPanel implements ActionListener {

    private JTextField textTitulo;
    private JTextArea textDescripcion;
    private JComboBox<String> cmbNivel;
    private JTextArea textAreaObjetivos;
    private PanelFieldBoton panelFieldBotonObjetivos;
    private PanelFieldBoton panelFieldBotonKeywords;
    private JButton botonCrear;
    private JButton botonSalir;
    public PanelCrearLearningPath(LearningPath lp) {
    JPanel panelIzquierdo = new JPanel();
    panelIzquierdo.setLayout(new GridLayout(4,2,20,20));
    panelFieldBotonObjetivos.getBoton().addActionListener(this);

    JLabel lblTitulo = new JLabel("Titulo");
    panelIzquierdo.add(lblTitulo);
    textTitulo = new JTextField();
    panelIzquierdo.add(textTitulo);

    JLabel lblNivel = new JLabel("Nivel");
    JComboBox<String> cmbNivel = new JComboBox<>();
    cmbNivel.addItem("Principiante");
    cmbNivel.addItem("Intermedio");
    cmbNivel.addItem("Avanzado");
    panelIzquierdo.add(lblNivel);
    panelIzquierdo.add(cmbNivel);

    JLabel lblObjetivos = new JLabel("Objetivos");
    panelIzquierdo.add(lblObjetivos);
    textAreaObjetivos = new JTextArea();
    panelIzquierdo.add(textAreaObjetivos);

    JLabel lblObjetivos2 = new JLabel("");
    panelIzquierdo.add(lblObjetivos2);
    panelFieldBotonObjetivos = new PanelFieldBoton("Agregar Objetivo", "Objetivo");

    panelIzquierdo.add(lblObjetivos2);
    panelIzquierdo.add(panelFieldBotonObjetivos);

    add(panelIzquierdo, BorderLayout.WEST);

    JPanel panelDerecho = new JPanel();
    panelDerecho.setLayout(new GridLayout(4,2,20,20));

    JLabel lblDescripcion = new JLabel("Descripcion");
    panelDerecho.add(lblDescripcion);
    textDescripcion = new JTextArea();
    panelDerecho.add(textDescripcion);

    //TODO: Obtener las palabras claves disponibles a partir de el LPRS

    JLabel lblKeywords = new JLabel("Keywords");
    panelDerecho.add(lblKeywords);
    panelFieldBotonKeywords = new PanelFieldBoton("Agregar Palabra Clave", "Keyword");
    panelDerecho.add(panelFieldBotonKeywords);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
