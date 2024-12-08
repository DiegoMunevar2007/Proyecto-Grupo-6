package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.Seccion;
import lprs.logica.contenido.Tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogoTarea extends JDialog implements ActionListener {
    private JTextField txtTitulo;
    private JTextField txtTipo;
    private JTextArea txtDescripcion;
    private JTextArea txtContenido;
    private JTextField txtEjemplo;
    private JTextField txtExplicacion;
    private JTextField txtPista;
    private JTextField txtResultado;
    private JButton btnAgregarSeccion;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private ArrayList<Seccion> secciones;
    private int cantidadSecciones;
    private DialogoCrearActividad dialogoCrearActividad;
    private Tarea tarea;

    public DialogoTarea(DialogoCrearActividad dialogoCrearActividad, Tarea tarea) {
        this.dialogoCrearActividad = dialogoCrearActividad;
        this.secciones = new ArrayList<>();
        this.tarea = tarea;
        this.cantidadSecciones = 0;
        setSize(600, 600);
        setLayout(new BorderLayout(10, 10));

        JPanel panelTarea = new JPanel(new GridLayout(8, 2, 10, 10));
        JLabel lblTitulo = new JLabel("Titulo:");
        panelTarea.add(lblTitulo);
        txtTitulo = new JTextField();
        panelTarea.add(txtTitulo);

        JLabel lblTipo = new JLabel("Tipo:");
        panelTarea.add(lblTipo);
        txtTipo = new JTextField();
        panelTarea.add(txtTipo);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        panelTarea.add(lblDescripcion);
        txtDescripcion = new JTextArea();
        panelTarea.add(new JScrollPane(txtDescripcion));

        JLabel lblContenido = new JLabel("Contenido:");
        panelTarea.add(lblContenido);
        txtContenido = new JTextArea();
        panelTarea.add(new JScrollPane(txtContenido));

        JLabel lblEjemplo = new JLabel("Ejemplo:");
        panelTarea.add(lblEjemplo);
        txtEjemplo = new JTextField();
        panelTarea.add(txtEjemplo);

        JLabel lblExplicacion = new JLabel("Explicacion:");
        panelTarea.add(lblExplicacion);
        txtExplicacion = new JTextField();
        panelTarea.add(txtExplicacion);

        JLabel lblPista = new JLabel("Pista:");
        panelTarea.add(lblPista);
        txtPista = new JTextField();
        panelTarea.add(txtPista);

        JLabel lblResultado = new JLabel("Resultado:");
        panelTarea.add(lblResultado);
        txtResultado = new JTextField();
        panelTarea.add(txtResultado);

        add(panelTarea, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregarSeccion = new JButton("Agregar Seccion");
        btnCancelar = new JButton("Cancelar");
        btnContinuar = new JButton("Continuar");
        btnAgregarSeccion.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnContinuar.addActionListener(this);
        panelBotones.add(btnAgregarSeccion);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnContinuar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            this.dispose();
        } else if (e.getSource() == btnAgregarSeccion) {
            Seccion seccion = new Seccion(cantidadSecciones, txtTitulo.getText(), txtTipo.getText(), txtDescripcion.getText(), txtContenido.getText(), txtEjemplo.getText(), txtExplicacion.getText(), txtPista.getText(), txtResultado.getText());
            secciones.add(seccion);
            cantidadSecciones++;
            JOptionPane.showMessageDialog(this, "Sección agregada exitosamente");
            resetTextFields();
        } else if (e.getSource() == btnContinuar) {
            tarea.setSecciones(secciones);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this, dialogoCrearActividad.getLp(), tarea);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this, dialogoCrearActividad.getLp(), tarea);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }

    private void resetTextFields() {
        txtTitulo.setText("");
        txtTipo.setText("");
        txtDescripcion.setText("");
        txtContenido.setText("");
        txtEjemplo.setText("");
        txtExplicacion.setText("");
        txtPista.setText("");
        txtResultado.setText("");
    }
}