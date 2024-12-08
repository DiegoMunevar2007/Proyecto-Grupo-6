package lprs.interfaz.profesor.seguimiento.LearningPath;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
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
    private JComboBox actividadesRealizadas;
    public DialogoActividadesEstudiante(DialogoSeguimientoLP dialogoSeguimientoLP) {
        setTitle("Actividades de un estudiante");
        setSize(600, 200);
        setLayout(new BorderLayout());
        JLabel tituloPanel = new JLabel("Actividades de un estudiante",SwingConstants.CENTER);
        add(tituloPanel,BorderLayout.NORTH);
        panelLPEstudiante = new PanelLPYEstudiante(dialogoSeguimientoLP.getProfesor());
        panelLPEstudiante.getEstudiantes().addActionListener(this);
        add(panelLPEstudiante,BorderLayout.WEST);
        actividadesRealizadas = new JComboBox();
        actividadesRealizadas.setBounds(10, 40, 100, 20);
        actividadesRealizadas.addItem("Seleccione una actividad");
        actividadesRealizadas.addActionListener(this);
        add(actividadesRealizadas,BorderLayout.CENTER);

        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new GridLayout(4,2,20,20));
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
        add(panelInformacion,BorderLayout.EAST);
        btnOk = new JButton("OK");
        btnOk.setBounds(10, 70, 200, 20);
        btnOk.addActionListener(this);
        add(btnOk, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==panelLPEstudiante.getEstudiantes()){
            if (!"Seleccione un estudiante".equals(panelLPEstudiante.getEstudiantes().getSelectedItem())){
                actividadesRealizadas.removeAllItems();
                LearningPath lp = (LearningPath) panelLPEstudiante.getLearningPaths().getSelectedItem();
                Estudiante estudiante = (Estudiante) panelLPEstudiante.getEstudiantes().getSelectedItem();
                actividadesRealizadas.addItem("Seleccione una actividad");
                for (Actividad actividad : (estudiante.getAvance(lp.getID()).getActividadesCompletadasLista())){
                    ActividadRealizable actividadRealizable = estudiante.getAvance(lp.getID()).getActividadesCompletadas().get(actividad);
                    actividadesRealizadas.addItem(actividadRealizable);
                }
            }
        } else if (e.getSource()==actividadesRealizadas){
            if (!"Seleccione una actividad".equals(actividadesRealizadas.getSelectedItem()) && actividadesRealizadas.getSelectedItem()!=null){
                actualizarInfo((ActividadRealizable) actividadesRealizadas.getSelectedItem());
            }
        } else if (e.getSource()==btnOk){
            dispose();
        }
    }
    public void actualizarInfo(ActividadRealizable actividad){
        titulo.setText(actividad.getActividadBase().getTitulo());
        descripcion.setText(actividad.getActividadBase().getDescripcion());
        objetivo.setText(actividad.getActividadBase().getObjetivo());
        estado.setText(actividad.getEstado());
    }
}
