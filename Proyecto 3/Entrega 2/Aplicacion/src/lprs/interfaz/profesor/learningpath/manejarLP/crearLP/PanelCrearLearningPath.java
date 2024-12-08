package lprs.interfaz.profesor.learningpath.manejarLP.crearLP;

import lprs.principal.LPRS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCrearLearningPath extends JPanel implements ActionListener {

    private JTextField textTitulo;
    private JTextArea textDescripcion;
    private JComboBox<String> cmbNivel;
    private JTextArea textAreaObjetivos;
    private PanelFieldBoton panelFieldBotonObjetivos;
    private PanelFieldBoton panelFieldBotonKeywords;
    private JButton botonCrear;
    private JButton botonSalir;
    private ArrayList<String> keywords;
    private JPanel panelKeywords;
    public PanelCrearLearningPath(LPRS lprs) {
    keywords = new ArrayList<String>();
    JPanel panelIzquierdo = new JPanel();
    panelIzquierdo.setLayout(new GridLayout(4,2,20,20));
    panelFieldBotonObjetivos = new PanelFieldBoton("Agregar Objetivo", "Objetivo");
    panelFieldBotonObjetivos.getBoton().addActionListener(this);

    JLabel lblTitulo = new JLabel("Titulo");
    panelIzquierdo.add(lblTitulo);
    textTitulo = new JTextField();
    panelIzquierdo.add(textTitulo);

    JLabel lblNivel = new JLabel("Nivel");
    cmbNivel = new JComboBox<>();
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
    ArrayList<String> keywords= lprs.getManejadorLP().getKeyWords();
    panelKeywords = new JPanel();
    panelKeywords.setLayout(new FlowLayout());
    for (String palabra : keywords){
        JCheckBox checkBox = new JCheckBox(palabra);
        checkBox.addActionListener(this);
        panelKeywords.add(checkBox);
    }
    panelDerecho.add(panelKeywords);
    JLabel lblKeywords2 = new JLabel("");
    panelDerecho.add(lblKeywords2);
    panelFieldBotonKeywords = new PanelFieldBoton("Agregar Keyword", "Keyword");
    panelFieldBotonKeywords.getBoton().addActionListener(this);
    panelDerecho.add(panelFieldBotonKeywords);
    add(panelDerecho, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JCheckBox){
            JCheckBox checkBox = (JCheckBox) e.getSource();
            if (checkBox.isSelected()){
                keywords.add(checkBox.getText());
            } else {
                keywords.remove(checkBox.getText());
            }
        } else if (e.getSource() == panelFieldBotonKeywords.getBoton()) {
            keywords.add(panelFieldBotonKeywords.getText());
            JOptionPane.showMessageDialog(this, "La palabra clave ha sido agregada");

        } else if  (e.getSource() == panelFieldBotonObjetivos.getBoton()){
            textAreaObjetivos.append(panelFieldBotonObjetivos.getText() + "\n");
        }
    }
    public String getTitulo(){
        return textTitulo.getText();
    }
    public String getDescripcion(){
        return textDescripcion.getText();
    }
    public String getNivel(){
        return (String) cmbNivel.getSelectedItem();
    }
    public String getObjetivos(){
        return textAreaObjetivos.getText();
    }
    public ArrayList<String> getKeywords(){
        return keywords;
    }
    public void setTitulo(String titulo){
        textTitulo.setText(titulo);
    }

}
