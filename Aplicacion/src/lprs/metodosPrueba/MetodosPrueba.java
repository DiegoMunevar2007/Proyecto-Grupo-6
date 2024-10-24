package lprs.metodosPrueba;

import java.util.ArrayList;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.Resenia;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class MetodosPrueba {
	private LPRS lprs;
	private Estudiante estudianteIniciado;
	private Profesor profesorIniciado1;
	private Profesor profesorIniciado2;

	public MetodosPrueba(LPRS lprs) {
		this.lprs = lprs;
		this.estudianteIniciado = null;
		this.profesorIniciado1 = null;
	}

	public void RF1() throws Exception {
		System.out.println("Requerimiento funcional 1");
		lprs.getManejadorSesion().crearUsuario("Estudiante", "Hola", 1);
		lprs.getManejadorSesion().crearUsuario("Profe", "Profe", 2);
		lprs.getManejadorSesion().crearUsuario("Profe2", "Profe2", 2);
		System.out.println("----------------------");

	}

	public void RF2(String usuario, String contrasenia) throws Exception {
		System.out.println("Requerimiento funcional 2");
		Usuario usuarioI = lprs.getManejadorSesion().iniciarSesion(usuario, contrasenia);
		if (usuarioI.getTipo().equals("Estudiante")) {
			Estudiante estudiante = (Estudiante) usuarioI;
			System.out.println("Estudiante " + estudiante.getUsuario());
			estudianteIniciado = estudiante;
		} else if (usuarioI.getTipo().equals("Profesor")) {
			Profesor profesor = (Profesor) usuarioI;
			System.out.println("Profesor " + profesor.getUsuario());
			if (profesorIniciado1 == null) {
				profesorIniciado1 = profesor;
			} else {
				profesorIniciado2 = profesor;
			}
		}
		System.out.println("----------------------");

	}

	public void RF3() {
		System.out.println("Requerimiento funcional 3");
		ArrayList<String> objetivos = new ArrayList<String>();
		objetivos.add("Objetivo 1");
		String idLP = profesorIniciado1.crearLearningPath("Titulo learning path", "Descripcion", "Principiante",
				objetivos);
		LearningPath lP = lprs.getManejadorLP().getLearningPath(idLP);
		System.out.println(lP.getID());
		System.out.println(lP.getTitulo());
		System.out.println(lP.getNivelDificultad());
		System.out.println(lP.getDescripcion());
		for (String objetivo : lP.getObjetivos()) {
			System.out.println(objetivo);
		}
		ArrayList<String> objetivos2 = new ArrayList<String>();
		objetivos2.add("Objetivo 2");
		String idLP2 = profesorIniciado2.crearLearningPath("Titulo learning path 2", "Descripcion 2", "Principiante",
				objetivos2);
		LearningPath lP2 = lprs.getManejadorLP().getLearningPath(idLP2);
		System.out.println(lP2.getID());
		System.out.println(lP2.getTitulo());
		System.out.println(lP2.getNivelDificultad());
		System.out.println(lP2.getDescripcion());
		for (String objetivo : lP2.getObjetivos()) {
			System.out.println(objetivo);
		}
		System.out.println("----------------------");
	}

	public void RF4() {
		System.out.println("Requerimiento funcional 4");
		LearningPath lP = profesorIniciado1.getLearningPathCreado("0");
		lP.crearTarea("Tarea 1", "Descripcion de tarea 1", "Objetivo de tarea 1", 10, false, lP.obtenerFecha());
		Tarea tarea2 = lP.crearTarea("Tarea 2", "Descripcion de tarea 2", "Objetivo de tarea 2", 10, true,
				lP.obtenerFecha());
		tarea2.crearSeccion(1, "Seccion 1", "Descripcion de seccion 1", "Objetivo de seccion 1",
				"Contenido de seccion 1",
				"Ejemplo de seccion 1", "Explicacion de seccion 1", "Pista de seccion 1",
				"Resultado esperado de seccion 1");

		Tarea tarea3 = lP.crearTarea("Tarea 3", "Descripcion de tarea 3", "Objetivo de tarea 3", 10, false,
				lP.obtenerFecha());

		tarea2.addActividadSeguimiento(tarea3);
		tarea3.addActividadPrevia(tarea2);

		lP.crearRecursoEducativo("Recurso 1", "Descripcion del recurso", "Objetivo del recurso", 20, false,
				lP.obtenerFecha(), "Video", "youtube.com");

		Encuesta encuesta = lP.crearEncuesta("Encuesta 1", "Descripcion de encuesta 1", "Objetivo de encuesta 1", 10,
				true,
				lP.obtenerFecha());
		encuesta.addPreguntaEncuesta(new PreguntaAbierta("Enunciado de una pregunta abierta de la encuesta 1 "));

		Quiz quiz = lP.crearQuiz("Quiz 1", "Descripcion de quiz 1", "Objetivo de quiz 1", 10, false, lP.obtenerFecha(),
				20);
		Opcion opcion1 = new Opcion("Opcion 1", "Es incorrecta");
		Opcion opcion2 = new Opcion("Opcion 2", "Es correcta");
		Opcion opcion3 = new Opcion("Opcion 3", "Es incorrecta");
		Opcion opcion4 = new Opcion("Opcion 4", "Es incorrecta");
		Opcion[] opcionesCreadas = { opcion1, opcion2, opcion3, opcion4 };
		quiz.addPreguntaQuiz(new PreguntaCerrada("Enunciado de una pregunta del quiz", opcion2, opcionesCreadas));

		Examen examen = lP.crearExamen("Examen 1", "Descripcion de examen 1", "Objetivo de examen 1", 10, false,
				lP.obtenerFecha());
		examen.addPreguntaExamen(new PreguntaAbierta("Enunciado de una pregunta abierta del examen 1"));
		for (Actividad actividad : lP.getActividades()) {
			System.out.println(actividad.getTitulo());
			System.out.println(actividad.getDescripcion());
			System.out.println(actividad.getObjetivo());
			System.out.println(actividad.getDuracionEsperada());
			System.out.println(actividad.getFechaLimite());
		}
		System.out.println("----------------------");
	}

	public void RF5() {
		System.out.println("Requerimiento funcional 5");
		LearningPath lP = profesorIniciado1.getLearningPathCreado("0");
		try {
			lP.editarLearningPath("Titulo modificado", "Descripcion modificada", "Principiante",
					new ArrayList<String>(), profesorIniciado1);
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println(lP.getTitulo());
		System.out.println(lP.getNivelDificultad());
		System.out.println(lP.getDescripcion());
		for (String objetivo : lP.getObjetivos()) {
			System.out.println(objetivo);
		}
		System.out.println("----------------------");
	}

	public void RF7() {
		System.out.println("Requerimiento funcional 7");
		profesorIniciado1.clonarLearningPath("1");
		System.out.println("Learning paths creados por el profesor 1");
		for (LearningPath lP : profesorIniciado1.getLearningPathsCreados()) {
			System.out.println(lP.getID());
			System.out.println(lP.getTitulo());
			System.out.println(lP.getNivelDificultad());
			System.out.println(lP.getDescripcion());
			for (String objetivo : lP.getObjetivos()) {
				System.out.println(objetivo);
			}
		}
		System.out.println("Learning paths creados por el profesor 2");
		for (LearningPath lP : profesorIniciado2.getLearningPathsCreados()) {
			System.out.println(lP.getID());
			System.out.println(lP.getTitulo());
			System.out.println(lP.getNivelDificultad());
			System.out.println(lP.getDescripcion());
			for (String objetivo : lP.getObjetivos()) {
				System.out.println(objetivo);
			}
		}
		System.out.println("----------------------");
	}

	public void RF8() {
		System.out.println("Requerimiento funcional 8");
		LearningPath lP = lprs.getManejadorLP().getLearningPath("0");
		Actividad actividad = lP.getActividades().get(0);
		Resenia resenia = new Resenia(profesorIniciado1, "Resenia de la actividad", 5);
		actividad.addResenia(resenia);
		for (Resenia resenia2 : actividad.getResenias()) {
			System.out.println(resenia2.getContenido());
			System.out.println(resenia2.getRating());
			System.out.println(resenia2.getAutor().getUsuario());
		}
		System.out.println(lP.getRating());
		System.out.println("----------------------");
		Resenia reseniaLP = new Resenia(profesorIniciado2, "Resenia de la actividad pero por profe 2", 4);
		actividad.addResenia(reseniaLP);
		for (Resenia resenia2 : actividad.getResenias()) {
			System.out.println(resenia2.getContenido());
			System.out.println(resenia2.getRating());
			System.out.println(resenia2.getAutor().getUsuario());
		}
		System.out.println(lP.getRating());
		System.out.println("----------------------");
	}

	public void RF10() {
		System.out.println("Requerimiento funcional 10");
		estudianteIniciado.inscribirLearningPath("0");
		System.out.println("Learning paths inscritos por el estudiante");
		for (LearningPath lP : estudianteIniciado.getLearningPathsInscritos()) {
			System.out.println(lP.getID());
			System.out.println(lP.getTitulo());
			System.out.println(lP.getNivelDificultad());
			System.out.println(lP.getDescripcion());
			for (String objetivo : lP.getObjetivos()) {
				System.out.println(objetivo);
			}
		}
		estudianteIniciado.inscribirLearningPath("1");
		System.out.println("Learning paths inscritos por el estudiante");
		for (LearningPath lP : estudianteIniciado.getLearningPathsInscritos()) {
			System.out.println(lP.getID());
			System.out.println(lP.getTitulo());
			System.out.println(lP.getNivelDificultad());
			System.out.println(lP.getDescripcion());
			for (String objetivo : lP.getObjetivos()) {
				System.out.println(objetivo);
			}
		}
		System.out.println("----------------------");

	}

}
