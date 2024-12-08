package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoActividadesEstudiante extends JDialog implements ActionListener {

    private PanelLPYEstudiante panelLPEstudiante;
    private JTextField titulo;
    private JTextField descripcion;
    private JTextField objetivo;
    private JTextField estado;
    private JButton btnOk;
    private JComboBox  actividadesRealizadas;

    public DialogoActividadesEstudiante(DialogoSeguimientoLP dialogoSeguimientoLP) {
        setTitle("Actividades de un estudiante");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel tituloPanel = new JLabel("Actividades de un estudiante", SwingConstants.CENTER);
        tituloPanel.setFont(new Font("Arial", Font.BOLD, 18));
        tituloPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(tituloPanel, BorderLayout.NORTH);

        panelLPEstudiante = new PanelLPYEstudiante(dialogoSeguimientoLP.getProfesor());
        panelLPEstudiante.getEstudiantes().addActionListener(this);
        add(panelLPEstudiante, BorderLayout.WEST);

        actividadesRealizadas = new JComboBox<>();
        actividadesRealizadas.addItem("Seleccione una actividad");
        actividadesRealizadas.addActionListener(this);
        JPanel panelActividades = new JPanel();
        panelActividades.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelActividades.add(actividadesRealizadas);
        add(panelActividades, BorderLayout.CENTER);

        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new GridLayout(4, 2, 20, 20));
        panelInformacion.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelInformacion.add(new JLabel("Titulo:"));
        titulo = new JTextField();
        titulo.setEditable(false);
        panelInformacion.add(titulo);
        panelInformacion.add(new JLabel("Descripcion:"));
        descripcion = new JTextField();
        descripcion.setEditable(false);
        panelInformacion.add(descripcion);
        panelInformacion.add(new JLabel("Objetivo:"));
        objetivo = new JTextField();
        objetivo.setEditable(false);
        panelInformacion.add(objetivo);
        panelInformacion.add(new JLabel("Estado:"));
        estado = new JTextField();
        estado.setEditable(false);
        panelInformacion.add(estado);
        add(panelInformacion, BorderLayout.EAST);

        btnOk = new JButton("OK");
        btnOk.setFont(new Font("Arial", Font.PLAIN, 16));
        btnOk.setBackground(new Color(70, 130, 180));
        btnOk.setForeground(Color.WHITE);
        btnOk.setBorderPainted(false);
        btnOk.addActionListener(this);
        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBoton.add(btnOk);
        add(panelBoton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelLPEstudiante.getEstudiantes()) {
            if (!"Seleccione un estudiante".equals(panelLPEstudiante.getEstudiantes().getSelectedItem())) {
                actividadesRealizadas.removeAllItems();
                LearningPath lp = (LearningPath) panelLPEstudiante.getLearningPaths().getSelectedItem();
                Estudiante estudiante = (Estudiante) panelLPEstudiante.getEstudiantes().getSelectedItem();
                actividadesRealizadas.addItem("Seleccione una actividad");
                for (Actividad actividad : estudiante.getAvance(lp.getID()).getActividadesCompletadasLista()) {
                    ActividadRealizable actividadRealizable = estudiante.getAvance(lp.getID()).getActividadesCompletadas().get(actividad);
                    actividadesRealizadas.addItem(actividadRealizable);
                }
            }
        } else if (e.getSource() == actividadesRealizadas) {
            if (!"Seleccione una actividad".equals(actividadesRealizadas.getSelectedItem()) && actividadesRealizadas.getSelectedItem() != null) {
                actualizarInfo((ActividadRealizable) actividadesRealizadas.getSelectedItem());
            }
        } else if (e.getSource() == btnOk) {
            dispose();
        }
    }

    public void actualizarInfo(ActividadRealizable actividad) {
        titulo.setText(actividad.getActividadBase().getTitulo());
        descripcion.setText(actividad.getActividadBase().getDescripcion());
        objetivo.setText(actividad.getActividadBase().getObjetivo());
        estado.setText(actividad.getEstado());
    }
}