package lprs.interfaz.login;

import lprs.interfaz.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoCrearCuenta extends JDialog implements ActionListener {
    private JButton btnCrearCuenta;
    private JButton btnCancelar;
    private InterfazPrincipal interfazPrincipal;
    private PanelDatos panelDatos;
    private JComboBox<String> comboBox;
    public DialogoCrearCuenta(InterfazPrincipal interfazPrincipal) {
        this.interfazPrincipal = interfazPrincipal;

        setTitle("Crear cuenta");
        setSize(300, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        add(new JLabel("Crear cuenta"), BorderLayout.NORTH);

        JPanel panelAuxiliar = new JPanel();
        panelAuxiliar.setLayout(new BorderLayout());
        panelDatos = new PanelDatos();
        panelAuxiliar.add(panelDatos, BorderLayout.NORTH);

        comboBox = new JComboBox<>();
        comboBox.addItem("Seleccione tipo de cuenta");
        comboBox.addItem("Estudiante");
        comboBox.addItem("Profesor");
        comboBox.addActionListener(this);
        panelAuxiliar.add(comboBox, BorderLayout.SOUTH);

        add(panelAuxiliar, BorderLayout.CENTER);
        JPanel panelAuxiliarBtn = new JPanel();
        panelAuxiliarBtn.setLayout(new FlowLayout());
        btnCrearCuenta = new JButton("Crear cuenta");
        btnCrearCuenta.addActionListener(this);
        panelAuxiliarBtn.add(btnCrearCuenta);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        panelAuxiliarBtn.add(btnCancelar);
        add(panelAuxiliarBtn, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrearCuenta) {
            String usuario = panelDatos.getUsuario();
            String contrasena = panelDatos.getContrasena();
            String tipoCuenta = (String) comboBox.getSelectedItem();
            try {
                if (tipoCuenta.equals("Seleccione tipo de cuenta")) {
                    JOptionPane.showMessageDialog(this, "Seleccione un tipo de cuenta");
                }
                if (tipoCuenta.equals("Estudiante")) {
                    interfazPrincipal.getLprs().getManejadorSesion().crearUsuario(usuario, contrasena,1);
                    JOptionPane.showMessageDialog(this, "Estudiante creado exitosamente");
                } else if (tipoCuenta.equals("Profesor")) {
                    interfazPrincipal.getLprs().getManejadorSesion().crearUsuario(usuario, contrasena,2);
                    JOptionPane.showMessageDialog(this, "Profesor creado exitosamente");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
            interfazPrincipal.changeDialog(interfazPrincipal);
        }
    }
}
