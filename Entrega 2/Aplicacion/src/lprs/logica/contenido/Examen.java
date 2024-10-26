package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.ExamenRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class Examen extends Actividad {
	public ArrayList<PreguntaAbierta> preguntasExamen;

	public Examen(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			String fechaLimite, LearningPath lP, String dificultad) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.preguntasExamen = new ArrayList<PreguntaAbierta>();
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
	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		ExamenRealizable eR= new ExamenRealizable(estudiante, this);
		actividadesRealizablesCreadas.add(eR);
		return eR;
	}

}
