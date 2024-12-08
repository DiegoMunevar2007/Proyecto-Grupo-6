package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.interfaz.profesor.seguimiento.DialogoManejarSeguimiento;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.*;

public class DialogoDiagrama extends JDialog {
    private LearningPath lp;
    private int anio;
    private DialogoManejarSeguimiento dialogoManejarSeguimiento;
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

    }
}
