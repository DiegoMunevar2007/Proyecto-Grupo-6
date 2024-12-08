package lprs.interfaz.estudiante.realizar;

import lprs.logica.contenido.Seccion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;


public class VisualizarSecciones extends JPanel {
    private JTextField txtTitulo;
    private JTextField txtTipo;
    private JTextArea txtDescripcion;
    private JTextArea txtContenido;
    private JTextField txtEjemplo;
    private JTextField txtExplicacion;
    private JTextField txtPista;
    private JTextField txtResultado;
    private JButton btnContinuar;
    private JButton btnCancelar;
    private int cantidadSeccionesVistas;

    private ArrayList<Seccion> secciones;
    public VisualizarSecciones(ArrayList<Seccion> secciones) {
        this.secciones = secciones;
        cantidadSeccionesVistas = 0;
        setLayout(new BorderLayout());
        setBorder( new TitledBorder("Secciones"));
        JPanel panelSecciones = new JPanel(new GridLayout(8,2,10,10));
        JLabel lblTitulo = new JLabel("Titulo:");
        panelSecciones.add(lblTitulo);
        txtTitulo = new JTextField();
        panelSecciones.add(txtTitulo);
        JLabel lblTipo = new JLabel("Tipo:");
        panelSecciones.add(lblTipo);
        txtTipo = new JTextField();
        panelSecciones.add(txtTipo);
        JLabel lblDescripcion = new JLabel("Descripcion:");
        panelSecciones.add(lblDescripcion);
        txtDescripcion = new JTextArea();
        panelSecciones.add(new JScrollPane(txtDescripcion));
        JLabel lblContenido = new JLabel("Contenido:");
        panelSecciones.add(lblContenido);
        txtContenido = new JTextArea();
        panelSecciones.add(new JScrollPane(txtContenido));
        JLabel lblEjemplo = new JLabel("Ejemplo:");
        panelSecciones.add(lblEjemplo);
        txtEjemplo = new JTextField();
        panelSecciones.add(txtEjemplo);
        JLabel lblExplicacion = new JLabel("Explicacion:");
        panelSecciones.add(lblExplicacion);
        txtExplicacion = new JTextField();
        panelSecciones.add(txtExplicacion);
        JLabel lblPista = new JLabel("Pista:");
        panelSecciones.add(lblPista);
        txtPista = new JTextField();
        panelSecciones.add(txtPista);
        JLabel lblResultado = new JLabel("Resultado:");
        panelSecciones.add(lblResultado);
        txtResultado = new JTextField();
        panelSecciones.add(txtResultado);
        add(panelSecciones, BorderLayout.CENTER);
        mostrarSeccion();
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnContinuar = new JButton("Continuar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnContinuar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

    }

    public void mostrarSeccion(){
        if(cantidadSeccionesVistas < secciones.size()){
            Seccion seccion = secciones.get(cantidadSeccionesVistas);
            txtTitulo.setText(seccion.getTitulo());
            txtTipo.setText(seccion.getTipo());
            txtDescripcion.setText(seccion.getDescripcion());
            txtContenido.setText(seccion.getContenido());
            txtEjemplo.setText(seccion.getEjemplo());
            txtExplicacion.setText(seccion.getExplicacion());
            txtPista.setText(seccion.getPista());
            txtResultado.setText(seccion.getResultadoEsperado());
            cantidadSeccionesVistas++;
        }
    }


}
