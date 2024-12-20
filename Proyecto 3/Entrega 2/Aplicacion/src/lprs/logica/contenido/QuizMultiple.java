package lprs.logica.contenido;

import lprs.exceptions.CantidadOpcionesException;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.QuizRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class QuizMultiple extends Quiz {

	public QuizMultiple(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			String fechaLimite, LearningPath lP, String dificultad, double calificacionMinima) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad, calificacionMinima);
	}
	@Override
	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		QuizRealizable qR = new QuizRealizable(estudiante, this);
		actividadesRealizablesCreadas.add(qR);
		return qR;
	}

	@Override
	public void crearPreguntaCerrada(String enunciado, Opcion respuestaCorrecta, Opcion[] opciones) throws Exception  {
		if (opciones.length != 4) {
			throw new CantidadOpcionesException(opciones.length, this);
		}
		PreguntaCerrada pregunta = new PreguntaCerrada(enunciado, respuestaCorrecta, opciones);
		addPreguntaQuiz(pregunta);
	}

	
}
