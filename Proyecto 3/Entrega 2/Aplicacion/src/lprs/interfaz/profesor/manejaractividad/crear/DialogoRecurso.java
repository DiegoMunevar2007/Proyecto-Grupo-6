package lprs.interfaz.profesor.manejaractividad.crear;

import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.Tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoRecurso extends JDialog implements ActionListener {
    private JComboBox<String> tipoRecurso;
    private JTextField txtLink;
    private JButton btnAgregarRecurso;
    private JButton btnCancelar;
    private JButton btnContinuar;
    private RecursoEducativo recurso;
    private DialogoCrearActividad dialogoCrearActividad;

    public DialogoRecurso(RecursoEducativo recurso, DialogoCrearActividad dialogoCrearActividad) {
        this.recurso = recurso;
        this.dialogoCrearActividad = dialogoCrearActividad;
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        JPanel panelRecurso = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblTipoRecurso = new JLabel("Tipo de recurso:");
        panelRecurso.add(lblTipoRecurso);
        tipoRecurso = new JComboBox<>(new String[]{"Libro", "Video", "PDF", "Sitio Web"});
        panelRecurso.add(tipoRecurso);
        JLabel lblLink = new JLabel("Link:");
        panelRecurso.add(lblLink);
        txtLink = new JTextField();
        panelRecurso.add(txtLink);
        add(panelRecurso, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregarRecurso = new JButton("Agregar Recurso");
        btnCancelar = new JButton("Cancelar");
        btnContinuar = new JButton("Continuar");
        btnAgregarRecurso.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnContinuar.addActionListener(this);
        panelBotones.add(btnAgregarRecurso);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnContinuar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            this.dispose();
        } else if (e.getSource() == btnAgregarRecurso) {
            String tipo = (String) tipoRecurso.getSelectedItem();
            String link = txtLink.getText();
            if (!link.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Recurso agregado exitosamente");
                txtLink.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El link no puede estar vacío");
            }
        } else if (e.getSource() == btnContinuar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad previa?");
            if (respuesta == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadPrevia dialogoAgregarActividadPrevia = new DialogoAgregarActividadPrevia(this, dialogoCrearActividad.getLp(), recurso);
                dialogoAgregarActividadPrevia.setVisible(true);
                this.dispose();
            }
            int respuesta2 = JOptionPane.showConfirmDialog(this, "¿Desea agregar una actividad de seguimiento?");
            if (respuesta2 == JOptionPane.YES_OPTION) {
                DialogoAgregarActividadSeguimiento dialogoAgregarActividadSeguimiento = new DialogoAgregarActividadSeguimiento(this, dialogoCrearActividad.getLp(), recurso);
                dialogoAgregarActividadSeguimiento.setVisible(true);
                this.dispose();
            }
            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente");
            dialogoCrearActividad.getDialogoManejarActividad().setVisible(true);
        }
    }
}