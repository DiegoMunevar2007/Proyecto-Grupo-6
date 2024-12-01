package lprs.interfaz.login;

import lprs.interfaz.InterfazPrincipal;
import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoLogin extends JDialog implements ActionListener {

    private JButton btnIniciarSesion;
    private JButton btnCancelar;
    private PanelDatos panelDatos;
    private InterfazPrincipal interfazPrincipal;
    public DialogoLogin(InterfazPrincipal interfazPrincipal) {
        this.interfazPrincipal = interfazPrincipal;
        setTitle("Iniciar sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(new JLabel("Iniciar sesión"), BorderLayout.NORTH);
        panelDatos = new PanelDatos();
        add(panelDatos, BorderLayout.CENTER);
        JPanel panelAuxiliarBtn = new JPanel();
        panelAuxiliarBtn.setLayout(new FlowLayout());
        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.addActionListener(this);
        panelAuxiliarBtn.add(btnIniciarSesion);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        panelAuxiliarBtn.add(btnCancelar);
        add(panelAuxiliarBtn, BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion) {
            String usuario = panelDatos.getUsuario();
            String contrasena = panelDatos.getContrasena();
            try {
                Usuario usuarioIniciado = interfazPrincipal.getLprs().getManejadorSesion().iniciarSesion(usuario, contrasena);
                if (usuarioIniciado instanceof Estudiante) {
                    dispose();
                    interfazPrincipal.changeDialog(new InicioEstudiante((Estudiante) usuarioIniciado, interfazPrincipal));
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario no es un estudiante");
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
