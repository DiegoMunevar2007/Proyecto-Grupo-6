package lprs.interfaz.profesor.manejaractividad.ver;

import lprs.interfaz.profesor.manejaractividad.DialogoManejarActividad;
import lprs.logica.contenido.Actividad;
import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComboBoxLPActividad extends JPanel implements ActionListener {
    private JComboBox comboBoxActividades;
    private JComboBox comboBoxLP;
    private DialogoManejarActividad dialogoManejarActividad;

    public PanelComboBoxLPActividad(DialogoManejarActividad dialogoManejarActividad) {
        this.dialogoManejarActividad = dialogoManejarActividad;
        comboBoxActividades = new JComboBox();
        comboBoxActividades.addActionListener(dialogoManejarActividad);
        comboBoxLP = new JComboBox();
        comboBoxLP.addActionListener(this);
        comboBoxLP.addItem("Seleccione un Learning Path");
        for (LearningPath lp :dialogoManejarActividad.getInicioProfesor().getProfesor().getLearningPathsCreadosLista()){
            comboBoxLP.addItem(lp);
        }
        add(comboBoxLP);
        add(comboBoxActividades);

    }

    public JComboBox getComboBoxLP() {
        return comboBoxLP;
    }

    public JComboBox getComboBoxActividades() {
        return comboBoxActividades;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBoxLP){
            comboBoxActividades.removeAllItems();
            if (comboBoxLP.getSelectedIndex() == 0){
                return;
            }
            LearningPath lp = (LearningPath) comboBoxLP.getSelectedItem();
            if (lp != null){
                for (Actividad actividad : lp.getActividades()){
                    comboBoxActividades.addItem(actividad);
                }
            }
        }
    }
}
