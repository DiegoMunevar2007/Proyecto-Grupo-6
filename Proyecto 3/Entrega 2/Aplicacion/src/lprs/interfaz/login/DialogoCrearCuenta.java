package lprs.interfaz.login;

import lprs.interfaz.InterfazPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setSize(300, 320);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelAuxiliar = new JPanel();
        panelAuxiliar.setLayout(new BorderLayout());
        panelAuxiliar.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelDatos = new PanelDatos();
        panelAuxiliar.add(panelDatos, BorderLayout.NORTH);

        comboBox = new JComboBox<>();
        comboBox.addItem("Seleccione tipo de cuenta");
        comboBox.addItem("Estudiante");
        comboBox.addItem("Profesor");
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setBackground(new Color(70, 130, 180));
        comboBox.setForeground(Color.WHITE);
        comboBox.addActionListener(this);
        panelAuxiliar.add(comboBox, BorderLayout.SOUTH);

        add(panelAuxiliar, BorderLayout.CENTER);

        JPanel panelAuxiliarBtn = new JPanel();
        panelAuxiliarBtn.setLayout(new FlowLayout());
        panelAuxiliarBtn.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnCrearCuenta = new JButton("Crear cuenta");
        btnCrearCuenta.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCrearCuenta.setBackground(new Color(34, 139, 34));
        btnCrearCuenta.setForeground(Color.WHITE);
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.addActionListener(this);
        panelAuxiliarBtn.add(btnCrearCuenta);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorderPainted(false);
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
                } else if (tipoCuenta.equals("Estudiante")) {
                    interfazPrincipal.getLprs().getManejadorSesion().crearUsuario(usuario, contrasena, 1);
                    JOptionPane.showMessageDialog(this, "Estudiante creado exitosamente");
                } else if (tipoCuenta.equals("Profesor")) {
                    interfazPrincipal.getLprs().getManejadorSesion().crearUsuario(usuario, contrasena, 2);
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