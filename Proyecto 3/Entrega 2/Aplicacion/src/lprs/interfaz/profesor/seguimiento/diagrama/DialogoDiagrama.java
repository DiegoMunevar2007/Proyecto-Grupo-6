package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoDiagrama extends JDialog implements ActionListener {
    private LearningPath lp;
    private int anio;
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
    private JButton btnVolver;
    public DialogoDiagrama(DialogoManejarSeguimiento dialogoManejarSeguimiento,  LearningPath lp, int anio) {
       setLayout(new BorderLayout());
       setSize(500, 500);
       JLabel label = new JLabel("Diagrama del Learning Path: " + lp.getTitulo()+" del año: "+anio);
       add(label, BorderLayout.NORTH);
       this.lp = lp;
       this.anio = anio;
       this.dialogoManejarSeguimiento = dialogoManejarSeguimiento;
       JPanel panelDiagrama = new JPanel();
       panelDiagrama.setLayout(new GridLayout(4,3));
       for (int i = 1; i <= 12; i++) {
           PanelDiagramaMes panelDiagramaMes = new PanelDiagramaMes(i,anio,lp);
           panelDiagrama.add(panelDiagramaMes);
       }
        add(panelDiagrama, BorderLayout.CENTER);
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(this);
        btnVolver.setLayout(new FlowLayout());
        add(btnVolver, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            this.dispose();
            dialogoManejarSeguimiento.setVisible(true);
        }
    }
}
