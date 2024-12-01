package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public abstract class Quiz extends Actividad {
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
	public abstract ActividadRealizable crearActividadRealizable(Estudiante estudiante);

	public void addPreguntaQuiz(PreguntaCerrada pregunta) {
		preguntasQuiz.add(pregunta);
	}

	public abstract void crearPreguntaCerrada(String enunciado, Opcion respuestaCorrecta, Opcion[] opciones) throws Exception;
	public void removePreguntaQuiz(PreguntaCerrada pregunta) {
		preguntasQuiz.remove(pregunta);
	}

	public double getCalificacionMinima() {
		return calificacionMinima;
	}
}
