package lprs.interfaz.estudiante.avance.actividad;

import lprs.logica.contenido.realizable.RecursoRealizable;

import java.awt.*;

public class PanelRecurso extends PanelActividad {
    public PanelRecurso(RecursoRealizable recurso) {
     super(recurso.getActividadBase());
        setLayout(new GridLayout(3,1,30,30));
    }
}
