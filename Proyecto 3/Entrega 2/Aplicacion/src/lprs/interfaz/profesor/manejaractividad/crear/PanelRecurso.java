package lprs.interfaz.profesor.manejaractividad.crear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecurso extends JPanel  {
    JComboBox<String> tipoRecurso;
    JTextField txtLink;
    public PanelRecurso() {
        setLayout(new GridLayout(2,2, 10, 10));
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

    public String getTipoRecurso() {
        return tipoRecurso.getSelectedItem().toString();
    }
    public String getLink() {
        return txtLink.getText();
    }
}
