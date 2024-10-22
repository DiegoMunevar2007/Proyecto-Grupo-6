package lprs.logica.contenido;
import java.util.ArrayList;
import java.util.Date;

import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;



public class Tarea extends Actividad {
	public ArrayList<Seccion> secciones;
	
	
	public Tarea(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			Date fechaLimite, LearningPath lP, String dificultad, ArrayList<Seccion> secciones) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.secciones = secciones;
	}
	
	public TareaRealizable RealizarActividad(TareaRealizable tarea) {
		return tarea;
	} 
	
	public ArrayList<Seccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(ArrayList<Seccion> secciones) {
		this.secciones = secciones;
	}

	public void addSeccion(Seccion nuevaSeccion) {
        secciones.add(nuevaSeccion);
    }
	
	public void removeSeccion(Seccion seccion) {
        secciones.remove(seccion);
    }

    public Tarea(Actividad actividad) {
        super(actividad);
    }

    public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
        return new TareaRealizable(this, estudiante);
    }

}
