package lprs.logica.contenido;

import java.util.Date;

import lprs.logica.learningPath.LearningPath;



public class Tarea extends Actividad {
    private static final String EXITOSO = "Exitoso";
    private static final String NOEXITOSO = "No exitoso";

    public Tarea(String titulo, String descripcion, String objetivo, int duracionEsperada,
            boolean obligatoria, Date fechaLimite, LearningPath lP, String estado) {
        super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP);
        this.estado = estado;
    }

    public Tarea(Actividad actividad) {
        super(actividad);
    }

    public void setEstado(String estado) {
        if (estado.equals(EXITOSO) || estado.equals(NOEXITOSO)) {
            this.estado = estado;
        } else {
            System.out.println("El estado de la tarea debe ser 'Exitoso' o 'No exitoso'.");
        }
    }

    public Actividad crearActividadRealizable(Actividad actividad) {
        return new Tarea(actividad);
    }
}
