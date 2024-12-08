package lprs.interfaz;

import lprs.interfaz.login.DialogoCrearCuenta;
import lprs.interfaz.login.DialogoLogin;
import lprs.persistencia.PersistenciaGeneral;
import lprs.principal.LPRS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class InterfazPrincipal extends JFrame implements ActionListener {
    private LPRS lprs;
    private JLabel lblTitulo;
    private JButton btnIniciarSesion;
    private JButton btnSalir;
    private JButton btnCrearCuenta;

    public InterfazPrincipal(LPRS lprs) {
        this.lprs = lprs;
        setTitle("LPRS");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitulo = new JLabel("Bienvenido al Learning Path Recommendation System", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelAuxiliar = new JPanel();
        panelAuxiliar.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelAuxiliar.setLayout(new GridLayout(3, 1, 20, 20));

        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.addActionListener(this);
        btnIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnIniciarSesion.setBackground(new Color(70, 130, 180));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setBorderPainted(false);
        panelAuxiliar.add(btnIniciarSesion);

        btnCrearCuenta = new JButton("Crear cuenta");
        btnCrearCuenta.addActionListener(this);
        btnCrearCuenta.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCrearCuenta.setBackground(new Color(34, 139, 34));
        btnCrearCuenta.setForeground(Color.WHITE);
        btnCrearCuenta.setBorderPainted(false);
        panelAuxiliar.add(btnCrearCuenta);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(this);
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        panelAuxiliar.add(btnSalir);

        add(panelAuxiliar, BorderLayout.CENTER);
        setSize(400, 300);
        setVisible(true);
    }

    public LPRS getLprs() {
        return lprs;
    }

    public void changeDialog(Window newDialog) {
        this.dispose();
        newDialog.setVisible(true);
    }

    public void cambiarTamanio(int ancho, int alto) {
        setSize(ancho, alto);
    }

    public static void main(String[] args) {
        LPRS lprs = new LPRS();

        try {
            lprs = PersistenciaGeneral.cargarDatos("Persistencia.dat");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

        new InterfazPrincipal(lprs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion) {
            changeDialog(new DialogoLogin(this));
        } else if (e.getSource() == btnCrearCuenta) {
            changeDialog(new DialogoCrearCuenta(this));
        } else if (e.getSource() == btnSalir) {
            try {
                PersistenciaGeneral.guardarDatos(lprs, "Persistencia.dat");
                JOptionPane.showMessageDialog(this, "Datos guardados exitosamente");
                System.exit(0);
            } catch (Exception ex) {
                System.out.println("Error al guardar los datos");
                ex.printStackTrace();
            }
        }
    }
}