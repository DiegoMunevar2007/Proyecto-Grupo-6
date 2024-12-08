package lprs.interfaz.estudiante.realizar;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import lprs.logica.contenido.RecursoEducativo;
public class VisualizarRecurso extends JPanel {
    private JTextField txtTipoRecurso;
    private JTextField txtLink;
    private RecursoEducativo recurso;

    public VisualizarRecurso( RecursoEducativo recurso) {
        this.recurso = recurso;
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Recursos"));

        JPanel panelRecursos = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblTipoRecurso = new JLabel("Tipo de Recurso:");
        panelRecursos.add(lblTipoRecurso);
        txtTipoRecurso = new JTextField(recurso.getTipoRecurso());
        txtTipoRecurso.setEditable(false);
        panelRecursos.add(txtTipoRecurso);

        JLabel lblLink = new JLabel("Link:");
        panelRecursos.add(lblLink);
        txtLink = new JTextField(recurso.getUrl());
        txtLink.setEditable(false);
        panelRecursos.add(txtLink);

        add(panelRecursos, BorderLayout.CENTER);
    }

}