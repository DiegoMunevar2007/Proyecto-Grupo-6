package lprs.logica.contenido.realizable;

import java.util.ArrayList;

import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class QuizRealizable extends ActividadRealizable {

	Quiz actividadBase;
	double calificacion;
	int correctas;
	ArrayList<PreguntaCerradaRealizable> preguntas = new ArrayList<PreguntaCerradaRealizable>();

	public QuizRealizable(Estudiante estudiante, Quiz quizBase) {
		super(estudiante);
		this.actividadBase = quizBase;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void realizarActividad() {
		ArrayList<PreguntaCerrada> preguntasQuiz = actividadBase.getPreguntasQuiz();
		for (PreguntaCerrada pregunta : preguntasQuiz) {
			System.out.println(pregunta.getEnunciado());
			System.out.println("Opciones:");
			Opcion[] opciones = pregunta.getOpciones();
			for (int i = 0; i < pregunta.getOpciones().length; i++) {
				System.out.println(i + ". " + opciones[i].getOpcion());
			}
			System.out.println("Ingrese el número de la respuesta correcta:");
			int respuesta = Integer.parseInt(System.console().readLine());
			PreguntaCerradaRealizable preguntaRealizable = new PreguntaCerradaRealizable(pregunta, opciones[respuesta]);
			preguntas.add(preguntaRealizable);
			if (preguntaRealizable.verificarOpcion(opciones[respuesta])) {
				correctas++;
			}
		}
		calificacion = (preguntasQuiz.size() / correctas) * 100;
		System.out.println("Calificacion: " + calificacion + "%");
		System.out.println("Preguntas correctas: " + correctas);
		if (calificacion >= actividadBase.getCalificacionMinima()) {
			System.out.println("Felicidades, ha aprobado el quiz");
			estudiante.getAvance(actividadBase.getLearningPathAsignado().getID()).addActividadRealizada(this);
			if (actividadBase.isObligatoria()) {
				double porcentajeActividades = estudiante.getAvance(actividadBase.getLearningPathAsignado().getID())
						.getActividadesCompletadas();

			}

		} else {
			System.out.println("Lo siento, no ha aprobado el quiz");
		}
	}

	@Override
	public void guardarActividad() {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void enviarActividad() {
		guardarActividad();
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
