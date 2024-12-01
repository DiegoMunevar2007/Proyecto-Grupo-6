package lprs.interfaz.estudiante.avance.learningpath;

import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.Avance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelAvanceLP extends JPanel {
    private JLabel lblTitulo;
    private JTextField txtTitulo;
    private JLabel lblPorcentaje;
    private JTextField txtPorcentaje;
    private JLabel lblFechaInicio;
    private JTextField txtFechaInicio;
    private JLabel lblActividadesCompletadas;
    private JTextArea txtActividadesCompletadas;
    private JLabel lblActividadesPendientes;
    private JTextArea txtActividadesPendientes;
    private JLabel lblTasaExito;
    private JTextField txtTasaExito;
    private JLabel lblTasaFracaso;
    private JTextField txtTasaFracaso;

    public PanelAvanceLP() {
        setLayout(new GridLayout(7,2,30,30));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("Titulo del Learning Path:");
        add(lblTitulo);
        txtTitulo = new JTextField();
        txtTitulo.setEditable(false);
        add(txtTitulo);
        lblPorcentaje = new JLabel("Porcentaje completado:");
        add(lblPorcentaje);
        txtPorcentaje = new JTextField();
        add(txtPorcentaje);
        txtPorcentaje.setEditable(false);
        lblFechaInicio = new JLabel("Fecha de inicio:");
        add(lblFechaInicio);
        txtFechaInicio = new JTextField();
        add(txtFechaInicio);
        txtFechaInicio.setEditable(false);
        lblActividadesCompletadas = new JLabel("Actividades completadas:");
        add(lblActividadesCompletadas);
        txtActividadesCompletadas = new JTextArea();
        add(txtActividadesCompletadas);
        txtActividadesCompletadas.setEditable(false);
        lblActividadesPendientes = new JLabel("Actividades pendientes:");
        add(lblActividadesPendientes);
        txtActividadesPendientes = new JTextArea();
        add(txtActividadesPendientes);
        txtActividadesPendientes.setEditable(false);
        lblTasaExito = new JLabel("Tasa de éxito:");
        add(lblTasaExito);
        txtTasaExito = new JTextField();
        add(txtTasaExito);
        txtTasaExito.setEditable(false);
        lblTasaFracaso = new JLabel("Tasa de fracaso:");
        add(lblTasaFracaso);
        txtTasaFracaso = new JTextField();
        add(txtTasaFracaso);
        txtTasaFracaso.setEditable(false);

    }
    public void cambiarInformacion(Avance avance){
        txtTitulo.setText(avance.getLearningPathCorrespondiente().getTitulo());
        txtPorcentaje.setText(String.valueOf(avance.getActividadesCompletadasPorcentaje()));
        txtFechaInicio.setText(avance.getFechaInicio().toString());
        txtActividadesCompletadas.setText("");
        for (Actividad actividad :avance.getActividadesCompletadasLista()) {
            txtActividadesCompletadas.append(actividad.getTitulo() + "\n");
        }
        txtActividadesPendientes.setText("");
        for (Actividad actividad :avance.getActividadesPendientes()) {
            txtActividadesPendientes.append(actividad.getTitulo() + "\n");
        }
        txtTasaExito.setText(String.valueOf(avance.getTasaExito()));
        txtTasaFracaso.setText(String.valueOf(avance.getTasaFracaso()));
    }
}
