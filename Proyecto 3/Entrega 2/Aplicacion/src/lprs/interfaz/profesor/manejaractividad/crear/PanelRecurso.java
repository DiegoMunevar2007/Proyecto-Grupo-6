package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.RecursoEducativo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecurso extends JPanel  {
    private JComboBox<String> tipoRecurso;
    private JTextField txtLink;

    public PanelRecurso() {
        setLayout(new GridLayout(2,2, 10, 10));
        setBorder( new CompoundBorder(new EmptyBorder(70, 70, 70, 70),new TitledBorder("Recurso")));
        JLabel lblTipoRecurso = new JLabel("Tipo de recurso:");
        add(lblTipoRecurso);
        tipoRecurso = new JComboBox<>();
        tipoRecurso.addItem("Libro");
        tipoRecurso.addItem("Video");
        tipoRecurso.addItem("PDF");
        tipoRecurso.addItem("Sitio Web");
        add(tipoRecurso);
        JLabel lblLink = new JLabel("Link:");
        add(lblLink);
        txtLink = new JTextField();
        add(txtLink);

    }
    public PanelRecurso(RecursoEducativo recurso){
        setLayout(new GridLayout(2,2, 10, 10));
        JLabel lblTipoRecurso = new JLabel("Tipo de recurso:");
        add(lblTipoRecurso);
        tipoRecurso = new JComboBox<>();
        tipoRecurso.addItem("Libro");
        tipoRecurso.addItem("Video");
        tipoRecurso.addItem("PDF");
        tipoRecurso.addItem("Sitio Web");
        add(tipoRecurso);
        tipoRecurso.setEditable(false);
        tipoRecurso.setSelectedItem(recurso.getTipoRecurso());
        JLabel lblLink = new JLabel("Link:");
        add(lblLink);
        txtLink = new JTextField(recurso.getUrl());
        txtLink.setEditable(false);
        add(txtLink);
    }

    public String getTipoRecurso() {
        return tipoRecurso.getSelectedItem().toString();
    }
    public String getLink() {
        return txtLink.getText();
    }
}
