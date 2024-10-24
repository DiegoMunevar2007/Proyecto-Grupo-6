package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class Quiz extends Actividad {
	private double calificacionMinima;
	public ArrayList<PreguntaCerrada> preguntasQuiz;

	public Quiz(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			String fechaLimite, LearningPath lP, String dificultad, double calificacionMinima) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.calificacionMinima = calificacionMinima;
		this.preguntasQuiz = new ArrayList<PreguntaCerrada>();
	}

	public ArrayList<PreguntaCerrada> getPreguntasQuiz() {
		return preguntasQuiz;
	}

	public void setCalificacionMinima(double calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}

	public void setPreguntasExamen(ArrayList<PreguntaCerrada> preguntas) {
		this.preguntasQuiz = preguntas;
	}

	@Override
	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return null;
	}
}
