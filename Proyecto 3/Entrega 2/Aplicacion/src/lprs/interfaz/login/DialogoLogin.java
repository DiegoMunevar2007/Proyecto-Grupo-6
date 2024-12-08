package lprs.interfaz.login;

import lprs.interfaz.InterfazPrincipal;
import lprs.interfaz.estudiante.InicioEstudiante;
import lprs.interfaz.profesor.InicioProfesor;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setSize(300, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        panelDatos = new PanelDatos();
        add(panelDatos, BorderLayout.CENTER);

        JPanel panelAuxiliarBtn = new JPanel();
        panelAuxiliarBtn.setLayout(new FlowLayout());
        panelAuxiliarBtn.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnIniciarSesion.setBackground(new Color(70, 130, 180));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.addActionListener(this);
        panelAuxiliarBtn.add(btnIniciarSesion);

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
        if (e.getSource() == btnIniciarSesion) {
            String usuario = panelDatos.getUsuario();
            String contrasena = panelDatos.getContrasena();
            try {
                Usuario usuarioIniciado = interfazPrincipal.getLprs().getManejadorSesion().iniciarSesion(usuario, contrasena);
                if (usuarioIniciado instanceof Estudiante) {
                    dispose();
                    interfazPrincipal.changeDialog(new InicioEstudiante((Estudiante) usuarioIniciado, interfazPrincipal));
                } else if (usuarioIniciado instanceof Profesor) {
                    dispose();
                    interfazPrincipal.changeDialog(new InicioProfesor((Profesor) usuarioIniciado, interfazPrincipal));
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