package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Seccion;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PanelTarea extends JPanel {
    private JTextField txtTitulo;
    private JTextField txtTipo;
    private JTextArea txtDescripcion;
    private JTextArea txtContenido;
    private JTextField txtEjemplo;
    private JTextField txtExplicacion;
    private JTextField txtPista;
    private JTextField txtResultado;
    private ArrayList<Seccion> secciones;
    private int cantidadSecciones;
    public PanelTarea() {
        secciones = new ArrayList<>();
        cantidadSecciones = 0;
        setLayout(new GridLayout(8, 2, 10, 10));
        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setPreferredSize(new Dimension(100, 20));
        add(lblTitulo);
        txtTitulo = new JTextField();
        add(txtTitulo);
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setPreferredSize(new Dimension(100, 20));
        add(lblTipo);
        txtTipo = new JTextField();
        add(txtTipo);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setPreferredSize(new Dimension(100, 20));
        add(lblDescripcion);
        txtDescripcion = new JTextArea();
        add(txtDescripcion);
        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setPreferredSize(new Dimension(100, 20));
        add(lblContenido);
        txtContenido = new JTextArea();
        add(txtContenido);
        JLabel lblEjemplo = new JLabel("Ejemplo:");
        lblEjemplo.setPreferredSize(new Dimension(100, 20));
        add(lblEjemplo);
        txtEjemplo = new JTextField();
        add(txtEjemplo);
        JLabel lblExplicacion = new JLabel("Explicacion:");
        lblExplicacion.setPreferredSize(new Dimension(100, 20));
        add(lblExplicacion);
        txtExplicacion = new JTextField();
        add(txtExplicacion);

        JLabel lblPista = new JLabel("Pista:");
        lblPista.setPreferredSize(new Dimension(100, 20));
        add(lblPista);
        txtPista = new JTextField();
        add(txtPista);


        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setPreferredSize(new Dimension(100, 20));
        add(lblResultado);
        txtResultado = new JTextField();
        add(txtResultado);
    }


    public void agregarSeccion(){
        Seccion seccion = new Seccion(cantidadSecciones,txtTitulo.getText(),txtTipo.getText(), txtDescripcion.getText(), txtContenido.getText(), txtEjemplo.getText(), txtExplicacion.getText(), txtPista.getText(), txtResultado.getText());
        resetTextFields();
        cantidadSecciones++;
        secciones.add(seccion);
    }

    public void resetTextFields(){
        txtTitulo.setText("");
        txtTipo.setText("");
        txtDescripcion.setText("");
        txtContenido.setText("");
        txtEjemplo.setText("");
        txtExplicacion.setText("");
        txtPista.setText("");
        txtResultado.setText("");
    }
    public ArrayList<Seccion> getSecciones(){
        return secciones;
    }

}
