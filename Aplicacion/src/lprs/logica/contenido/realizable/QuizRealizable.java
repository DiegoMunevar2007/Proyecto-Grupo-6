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
	public void realizarActividad() {
		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			System.out.println(e.getMessage());
			System.out.println("¿Desea continuar con la actividad sin realizar las demás? (S/N)");
			String respuesta = lecturaQuiz.nextLine();
			if (respuesta.equalsIgnoreCase("N")) {
				return;
			} else {
				System.out.println("Continuando con la actividad...");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error: " + e.getMessage());
		}

		System.out.println("Realizando quiz...");
		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			System.out.println(e.getMessage());
			System.out.println("¿Desea continuar con la actividad sin realizar las demás? (S/N)");
			String respuesta = lecturaQuiz.nextLine();
			if (respuesta.equalsIgnoreCase("N")) {
				return;
			} else {
				System.out.println("Continuando con la actividad...");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error: " + e.getMessage());
		}
		ArrayList<PreguntaCerrada> preguntasQuiz = actividadBase.getPreguntasQuiz();
		long tiempoInicial = System.currentTimeMillis();
		for (PreguntaCerrada pregunta : preguntasQuiz) {
			System.out.println(pregunta.getEnunciado());
			System.out.println("Opciones:");
			Opcion[] opciones = pregunta.getOpciones();
			for (int i = 0; i < pregunta.getOpciones().length; i++) {
				System.out.println(i + 1 + ". " + opciones[i].getOpcion());
			}
			System.out.println("Ingrese el número de la respuesta correcta:");
			int respuesta = lecturaQuiz.nextInt();
			PreguntaCerradaRealizable preguntaRealizable = new PreguntaCerradaRealizable(pregunta, opciones[respuesta]);
			preguntas.add(preguntaRealizable);
			if (preguntaRealizable.verificarOpcion(opciones[respuesta - 1])) {
				correctas++;
			}
		}
		long tiempoFinal = System.currentTimeMillis();
		tiempoTomado = (int) (tiempoFinal - tiempoInicial) * 1000;
		enviarActividad();
	}

	@Override
	public void guardarActividad() {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void enviarActividad() {
		guardarActividad();
		calificacion = (actividadBase.getPreguntasQuiz().size() / correctas) * 100;
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

}
