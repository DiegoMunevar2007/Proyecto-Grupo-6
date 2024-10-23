package lprs.logica.contenido;

import java.util.ArrayList;
import java.util.Date;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class Encuesta extends Actividad{
	public ArrayList<PreguntaAbierta> preguntasEncuesta;
	
	public Encuesta(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			Date fechaLimite, LearningPath lP, String dificultad, ArrayList<PreguntaAbierta> preguntasEncuesta) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.preguntasEncuesta = preguntasEncuesta;
	}

	public ArrayList<PreguntaAbierta> getPreguntasEncuesta() {
		return preguntasEncuesta;
	}

	public void setPreguntasEncuesta(ArrayList<PreguntaAbierta> preguntas) {
        this.preguntasEncuesta = preguntas;
    }
	
	public void addPreguntaEncuesta(PreguntaAbierta pregunta) {
		preguntasEncuesta.add(pregunta);
    }
	
	public void removePreguntaEncuesta(PreguntaAbierta pregunta) {
		preguntasEncuesta.remove(pregunta);
    }

	@Override
	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	
