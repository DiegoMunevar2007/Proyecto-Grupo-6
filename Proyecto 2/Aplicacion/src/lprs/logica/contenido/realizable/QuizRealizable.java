package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class QuizRealizable extends ActividadRealizable {

	private Quiz actividadBase;
	private double calificacion;
	private int correctas;
	private ArrayList<PreguntaCerradaRealizable> preguntas;

	public QuizRealizable(Estudiante estudiante, Quiz quizBase) {
			super(estudiante);
			this.actividadBase = quizBase;
			preguntas = new ArrayList<PreguntaCerradaRealizable>();
		}
		@Override
		public ArrayList realizarActividad() throws ActividadPreviaException {
			try {
				verificarEligibilidad();
			} catch (ActividadPreviaException e) {
				throw e;
			}
	
			System.out.println("Realizando quiz...");
			ArrayList<PreguntaCerrada> preguntasQuiz = actividadBase.getPreguntasQuiz();
			tiempoTomado = (int) System.currentTimeMillis();
			return preguntasQuiz;
		}
	
		@Override
		public void guardarActividad(ArrayList respuestas) {
			preguntas = respuestas;
			LearningPath lP = actividadBase.getLearningPathAsignado();
			estudiante.getAvance(lP.getID()).addActividadRealizada(this);
		}
	
		@Override
		public void enviarActividad(ArrayList respuestas) {
			guardarActividad(respuestas);
			if (correctas == 0) {
				calificacion = 0;
			} else {
				calificacion = ((double) correctas / actividadBase.getPreguntasQuiz().size()) * 100;
			}
			System.out.println("Calificacion: " + calificacion + "%");
			System.out.println("Preguntas correctas: " + correctas);
			if (calificacion >= actividadBase.getCalificacionMinima()) {
				System.out.println("Felicidades, ha aprobado el quiz");
				estudiante.getAvance(actividadBase.getLearningPathAsignado().getID()).addActividadRealizada(this);
			} else {
				System.out.println("Lo siento, no ha aprobado el quiz");
			}
	
			Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
			profesor.addActividadPendiente(this);
			
		}
	
		@Override
		public void setEstado(String estado) throws EstadoException {
			
		}
	
		@Override
		public Actividad getActividadBase() {
			// TODO Auto-generated method stub
			return actividadBase;
		}
		public void incCorrectas() {
			correctas++;
		}
	
		@Override
		public void calificarActividad() {
	
		}
		public ArrayList<PreguntaCerradaRealizable> getPreguntas() {
			return preguntas;
		}
		public double getCalificacion() {
			return calificacion;
		}
		
		public void setCalificacion(double calificacion) {
			this.calificacion=calificacion;
		}

	public double getCorrectas() {
		return correctas;
	}
}
