package lprs.logica.contenido;

import java.util.ArrayList;
import java.util.Date;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.learningPath.LearningPath;

public class Examen extends Actividad{
	public ArrayList<PreguntaAbierta> preguntasExamen;
	
	public Examen(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			Date fechaLimite, LearningPath lP, String dificultad, ArrayList<PreguntaAbierta> preguntasEncuesta) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.preguntasExamen = preguntasEncuesta;
	}
	
	public ArrayList<PreguntaAbierta> getPreguntasExamen() {
        return preguntasExamen;
    }
	
	public void setPreguntasExamen(ArrayList<PreguntaAbierta> preguntas) {
        this.preguntasExamen = preguntas;
    }
	
	public void addPreguntaExamen(PreguntaAbierta pregunta) {
		preguntasExamen.add(pregunta);
    }
	
	public void removePreguntaExamen(PreguntaAbierta pregunta) {
		preguntasExamen.remove(pregunta);
    }
	
	@Override
	public Actividad crearActividadRealizable(Actividad actividad) {
		return null;
	}
	
	@Override
	public void setEstado(String estado) {
		
	}

}
	