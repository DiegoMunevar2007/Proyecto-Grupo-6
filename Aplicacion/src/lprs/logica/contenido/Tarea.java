package lprs.logica.contenido;
import lprs.logica.contenido.realizable.TareaRealizable;

import java.util.ArrayList;
import java.util.Date;

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
	
	public void addSeccion(Seccion nuevaSeccion) {
        secciones.add(nuevaSeccion);
    }
	
	public void removeSeccion(Seccion seccion) {
        secciones.remove(seccion);
    }

    public Tarea(Actividad actividad) {
        super(actividad);
    }

    public Actividad crearActividadRealizable(Actividad actividad) {
        return new Tarea(actividad);
    }

	@Override
	public void setEstado(String estado) {
		
	}
}
