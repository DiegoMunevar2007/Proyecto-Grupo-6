package lprs.interfaz.estudiante;

import lprs.interfaz.InterfazPrincipal;
import lprs.interfaz.estudiante.avance.actividad.DialogoAvanceActividad;
import lprs.interfaz.estudiante.avance.learningpath.DialogoAvanceLP;
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
        this.interfazPrincipal = interfazPrincipal;
        setTitle("Inicio - " + estudiante.getUsuario());
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BorderLayout());
        panelLabels.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelLabels.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelLabels.add(new JLabel("Inicio", SwingConstants.CENTER), BorderLayout.NORTH);
        panelLabels.add(new JLabel("Bienvenido " + estudiante.getUsuario(), SwingConstants.CENTER), BorderLayout.CENTER);
        panelLabels.add(new JLabel("Seleccione una opción", SwingConstants.CENTER), BorderLayout.SOUTH);
        add(panelLabels, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2,30,20));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnVerLearningPaths = new JButton("Ver mis Learning Paths");
        btnVerLearningPaths.addActionListener(this);
        panelBotones.add(btnVerLearningPaths);

        btnInscribirLearningPath = new JButton("Inscribir un Learning Path");
        btnInscribirLearningPath.addActionListener(this);
        panelBotones.add(btnInscribirLearningPath);

        btnVerAvance = new JButton("Ver mi avance");
        btnVerAvance.addActionListener(this);
        panelBotones.add(btnVerAvance);

        btnRealizarActividad = new JButton("Realizar una actividad");
        btnRealizarActividad.addActionListener(this);
        panelBotones.add(btnRealizarActividad);

        btnReseniarActividad = new JButton("Reseñar una actividad");
        btnReseniarActividad.addActionListener(this);
        panelBotones.add(btnReseniarActividad);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(this);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        setSize(450, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerLearningPaths) {
            mostrarLearningPaths();
        }
        else if (e.getSource() == btnInscribirLearningPath) {
            inscribirLearningPath();
        }
        else if (e.getSource() == btnVerAvance) {
            verAvance();
        } else if (e.getSource() == btnReseniarActividad) {
            DialogoResenia dialogoResenia = new DialogoResenia(this);
            interfazPrincipal.changeDialog(dialogoResenia);
        }
        else if (e.getSource() == btnSalir) {
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
        int opcion = JOptionPane.showOptionDialog(null,"¿Desea ver un avance de learning path o de una actividad?","Seleccione una opción",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Learning Path","Actividad"},"Learning Path");
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