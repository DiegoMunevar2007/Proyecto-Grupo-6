package lprs.interfaz.login;

import lprs.interfaz.InterfazPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDatos extends JPanel{
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    public PanelDatos() {
        setLayout(new BorderLayout());
        JLabel lblTitulo = new JLabel("Iniciar sesión");

        JPanel panelAuxiliar = new JPanel();
        panelAuxiliar.setLayout(new GridLayout(2,2));

        lblUsuario = new JLabel("Usuario:");
        panelAuxiliar.add(lblUsuario);
        txtUsuario = new JTextField();
        panelAuxiliar.add(txtUsuario);
        lblContrasena = new JLabel("Contraseña:");
        panelAuxiliar.add(lblContrasena);
        txtContrasena = new JPasswordField();
        panelAuxiliar.add(txtContrasena);
        add(panelAuxiliar, BorderLayout.CENTER);


    }
    public String getUsuario() {
        return txtUsuario.getText();
    }
    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

}
