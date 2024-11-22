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
	private Scanner lecturaQuiz;

	public QuizRealizable(Estudiante estudiante, Quiz quizBase) {
			super(estudiante);
			this.actividadBase = quizBase;
			preguntas = new ArrayList<PreguntaCerradaRealizable>();
			lecturaQuiz = new Scanner(System.in);
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
			// TODO Auto-generated method stub
			System.out.println("La informacion del quiz es la siguiente: ");
			System.out.println("Titulo del quiz: " + actividadBase.getTitulo());
			System.out.println("Descripcion del quiz: " + actividadBase.getDescripcion());
			System.out.println("Estudiante: " + estudiante.getUsuario());
			System.out.println("Calificacion: " + calificacion + "%");
			System.out.println("Preguntas correctas: " + correctas);
			System.out.println("Desea ver las respuestas del estudiante? (S/N)");
			String respuesta = System.console().readLine();
			if (respuesta.equalsIgnoreCase("S")) {
				for (PreguntaCerradaRealizable pregunta : this.preguntas) {
					System.out.println(pregunta.getPreguntaBase().getEnunciado());
					System.out.println("Respuesta correcta: " + pregunta.getPreguntaBase().getCorrecta().getOpcion());
					System.out.println("Respuesta del estudiante: " + pregunta.getOpcionEscogida().getOpcion());
				}
			}
	
		}
		public ArrayList<PreguntaCerradaRealizable> getPreguntas() {
			return preguntas;
		}
		public double getCalificacion() {
			return calificacion;
		}

}
