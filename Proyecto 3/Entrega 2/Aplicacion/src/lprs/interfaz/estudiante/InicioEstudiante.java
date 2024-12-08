package lprs.interfaz.estudiante;

import lprs.interfaz.InterfazPrincipal;
import lprs.interfaz.estudiante.avance.actividad.DialogoAvanceActividad;
import lprs.interfaz.estudiante.avance.learningpath.DialogoAvanceLP;
import lprs.interfaz.estudiante.realizar.DialogoSeleccionarActividad;
import lprs.interfaz.estudiante.resenia.DialogoResenia;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.PersistenciaGeneral;
import lprs.principal.LPRS;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InicioEstudiante extends JFrame implements ActionListener {
    private LPRS lprs;
    private Estudiante estudiante;
    private JLabel lblTitulo;
    private JButton btnVerLearningPaths;
    private JButton btnInscribirLearningPath;
    private JButton btnVerAvance;
    private JButton btnRealizarActividad;
    private JButton btnReseniarActividad;
    private JButton btnSalir;
    private JTextArea textArea;
    private InterfazPrincipal interfazPrincipal;

    public InicioEstudiante(Estudiante estudiante, InterfazPrincipal interfazPrincipal) {
        this.estudiante = estudiante;
        this.lprs = estudiante.getLprsActual();
        this.interfazPrincipal = interfazPrincipal;
        setTitle("Inicio - " + estudiante.getUsuario());
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel panelLabels = new JPanel(new BorderLayout(10, 10));
        panelLabels.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel lblInicio = new JLabel("Inicio", SwingConstants.CENTER);
        lblInicio.setFont(new Font("Arial", Font.BOLD, 20));
        lblInicio.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelLabels.add(lblInicio, BorderLayout.NORTH);
        JLabel lblBienvenido = new JLabel("Bienvenido " + estudiante.getUsuario(), SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBienvenido.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelLabels.add(lblBienvenido, BorderLayout.CENTER);
        JLabel lblSeleccionar = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        lblSeleccionar.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSeleccionar.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelLabels.add(lblSeleccionar, BorderLayout.SOUTH);
        add(panelLabels, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 20, 20));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));

        btnVerLearningPaths = new JButton("Ver mis Learning Paths");
        btnVerLearningPaths.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVerLearningPaths.setBackground(new Color(70, 130, 180));
        btnVerLearningPaths.setForeground(Color.WHITE);
        btnVerLearningPaths.setBorderPainted(false);
        btnVerLearningPaths.addActionListener(this);
        panelBotones.add(btnVerLearningPaths);

        btnInscribirLearningPath = new JButton("Inscribir un Learning Path");
        btnInscribirLearningPath.setFont(new Font("Arial", Font.PLAIN, 14));
        btnInscribirLearningPath.setBackground(new Color(70, 130, 180));
        btnInscribirLearningPath.setForeground(Color.WHITE);
        btnInscribirLearningPath.setBorderPainted(false);
        btnInscribirLearningPath.addActionListener(this);
        panelBotones.add(btnInscribirLearningPath);

        btnVerAvance = new JButton("Ver mi avance");
        btnVerAvance.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVerAvance.setBackground(new Color(70, 130, 180));
        btnVerAvance.setForeground(Color.WHITE);
        btnVerAvance.setBorderPainted(false);
        btnVerAvance.addActionListener(this);
        panelBotones.add(btnVerAvance);

        btnRealizarActividad = new JButton("Realizar una actividad");
        btnRealizarActividad.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRealizarActividad.setBackground(new Color(70, 130, 180));
        btnRealizarActividad.setForeground(Color.WHITE);
        btnRealizarActividad.setBorderPainted(false);
        btnRealizarActividad.addActionListener(this);
        panelBotones.add(btnRealizarActividad);

        btnReseniarActividad = new JButton("Reseñar una actividad");
        btnReseniarActividad.setFont(new Font("Arial", Font.PLAIN, 14));
        btnReseniarActividad.setBackground(new Color(70, 130, 180));
        btnReseniarActividad.setForeground(Color.WHITE);
        btnReseniarActividad.setBorderPainted(false);
        btnReseniarActividad.addActionListener(this);
        panelBotones.add(btnReseniarActividad);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalir.setBackground(new Color(220, 20, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        setSize(500, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerLearningPaths) {
            mostrarLearningPaths();
        } else if (e.getSource() == btnInscribirLearningPath) {
            inscribirLearningPath();
        } else if (e.getSource() == btnVerAvance) {
            verAvance();
        } else if (e.getSource() == btnRealizarActividad) {
            DialogoSeleccionarActividad dialogoSeleccionarActividad = new DialogoSeleccionarActividad(this);
            interfazPrincipal.changeDialog(dialogoSeleccionarActividad);
        } else if (e.getSource() == btnReseniarActividad) {
            DialogoResenia dialogoResenia = new DialogoResenia(this);
            interfazPrincipal.changeDialog(dialogoResenia);
        } else if (e.getSource() == btnSalir) {
            dispose();
            interfazPrincipal.changeDialog(interfazPrincipal);
        }
    }

    public void mostrarLearningPaths() {
        DialogoMostrarLPs dialogoMostrarLPs = new DialogoMostrarLPs(this);
        interfazPrincipal.changeDialog(dialogoMostrarLPs);
    }

    public void inscribirLearningPath() {
        if (lprs.getManejadorLP().getLearningPaths().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Learning Paths disponibles");
            return;
        }
        DialogoInscribirLearningPath dialogoInscribirLearningPath = new DialogoInscribirLearningPath(this);
        interfazPrincipal.changeDialog(dialogoInscribirLearningPath);
    }

    public void verAvance() {
        int opcion = JOptionPane.showOptionDialog(null, "¿Desea ver un avance de learning path o de una actividad?", "Seleccione una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Learning Path", "Actividad"}, "Learning Path");
        if (opcion == 0) {
            DialogoAvanceLP dialogoAvanceLP = new DialogoAvanceLP(this);
            interfazPrincipal.changeDialog(dialogoAvanceLP);
        } else {
            DialogoAvanceActividad dialogoAvanceActividad = new DialogoAvanceActividad(this);
            interfazPrincipal.changeDialog(dialogoAvanceActividad);
        }
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public InterfazPrincipal getInterfazPrincipal() {
        return interfazPrincipal;
    }
}