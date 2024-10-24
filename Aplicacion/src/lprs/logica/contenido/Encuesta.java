package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class Encuesta extends Actividad {
	public ArrayList<PreguntaAbierta> preguntasEncuesta;

	public Encuesta(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			String fechaLimite, LearningPath lP, String dificultad) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.preguntasEncuesta = new ArrayList<PreguntaAbierta>();
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
		return new EncuestaRealizable(this, estudiante);
	}

}
