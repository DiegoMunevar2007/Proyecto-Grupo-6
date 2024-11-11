package lprs.logica.learningPath;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.principal.LPRS;

public class LearningPath implements Serializable {
	private static int numeroLP = 0;
	private String ID;
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private ArrayList<String> objetivos;
	private int duracion;
	private double rating;
	private int calificaciones;
	private ArrayList<Actividad> actividades;
	private ArrayList<Estudiante> estudiantesInscritos;
	private Profesor profesorCreador;
	private Metadato metadatos;
	private LPRS lprsActual;
	private int cantidadObligatorias;

	/**
	 * Constructor para crear un objeto LearningPath.
	 *
	 * @param titulo          el título de la ruta de aprendizaje
	 * @param descripcion     la descripción de la ruta de aprendizaje
	 * @param nivelDificultad el nivel de dificultad de la ruta de aprendizaje
	 * @param objetivos       una lista de los objetivos de la ruta de aprendizaje
	 * @param profesorCreador el profesor que crea la ruta de aprendizaje
	 */
	public LearningPath(String ID, String titulo, String descripcion, String nivelDificultad,
			ArrayList<String> objetivos,
			Profesor profesorCreador, LPRS lprsActual) {
		this.ID = ID;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.objetivos = objetivos;
		this.duracion = 0;
		this.rating = 0;
		this.calificaciones = 0;
		this.actividades = new ArrayList<Actividad>();
		this.estudiantesInscritos = new ArrayList<Estudiante>();
		this.profesorCreador = profesorCreador;
		this.metadatos = new Metadato(obtenerFecha(), "1");
		this.lprsActual = lprsActual;
		this.cantidadObligatorias = 0;
	}

	public String obtenerFecha() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String fechaString = myDateObj.format(myFormatObj);
		return fechaString;
	}

	/**
	 * Constructor para crear una copia de un objeto LearningPath.
	 *
	 * @param LP              el objeto LearningPath a copiar
	 * @param profesorCreador el profesor que crea la copia de la ruta de
	 *                        aprendizaje
	 */
	public LearningPath(String ID, LearningPath LP, Profesor profesorCreador, String fechaCreacion) {
		this.ID = ID;
		this.titulo = LP.getTitulo();
		this.descripcion = LP.getDescripcion();
		this.nivelDificultad = LP.getNivelDificultad();
		this.objetivos = LP.getObjetivos();
		this.duracion = LP.getDuracion();
		this.rating = 0;
		this.calificaciones = 0;
		this.actividades = LP.getActividades();
		this.estudiantesInscritos = new ArrayList<Estudiante>();
		this.profesorCreador = profesorCreador;
		this.metadatos = new Metadato(fechaCreacion, "1");
		this.lprsActual = LP.lprsActual;
	}

	public Tarea crearTarea(String titulo, String descripcion, String objetivo, int duracion, boolean obligatoria,
			String fechaEntrega) {
		Tarea tarea = new Tarea(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, this, objetivo);
		actividades.add(tarea);
		if (obligatoria) {
			cantidadObligatorias++;
			this.duracion = this.duracion + duracion;
		}
		return tarea;
	}

	public RecursoEducativo crearRecursoEducativo(String titulo, String descripcion, String objetivo, int duracion,
			boolean obligatoria, String fechaEntrega, String tipoRecurso, String url) {
		RecursoEducativo recurso = new RecursoEducativo(titulo, descripcion, objetivo, duracion, obligatoria,
				fechaEntrega, this, objetivo, tipoRecurso, url);
		actividades.add(recurso);
		if (obligatoria) {
			cantidadObligatorias++;
			this.duracion = this.duracion + duracion;
		}
		return recurso;
	}

	public QuizMultiple crearQuizMultiple(String titulo, String descripcion, String objetivo, int duracion,
			boolean obligatoria,
			String fechaEntrega, double calificacionMinima) {
		QuizMultiple quiz = new QuizMultiple(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, this,
				objetivo,
				calificacionMinima);
		actividades.add(quiz);
		if (obligatoria) {
			cantidadObligatorias++;
			this.duracion = this.duracion + duracion;
		}
		return quiz;
	}

	public QuizVerdaderoFalso crearQuizVerdaderoFalso(String titulo, String descripcion, String objetivo, int duracion,
			boolean obligatoria,
			String fechaEntrega, double calificacionMinima) {
		QuizVerdaderoFalso quiz = new QuizVerdaderoFalso(titulo, descripcion, objetivo, duracion, obligatoria,
				fechaEntrega, this,
				objetivo,
				calificacionMinima);
		actividades.add(quiz);
		if (obligatoria) {
			cantidadObligatorias++;
			this.duracion = this.duracion + duracion;
		}
		return quiz;
	}

	public Examen crearExamen(String titulo, String descripcion, String objetivo, int duracion, boolean obligatoria,
			String fechaEntrega) {
		Examen examen = new Examen(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, this, objetivo);
		actividades.add(examen);
		if (obligatoria) {
			cantidadObligatorias++;
		}
		return examen;
	}

	public Encuesta crearEncuesta(String titulo, String descripcion, String objetivo, int duracion, boolean obligatoria,
			String fechaEntrega) {
		Encuesta encuesta = new Encuesta(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, this,
				objetivo);
		actividades.add(encuesta);
		if (obligatoria) {
			cantidadObligatorias++;
		}
		return encuesta;
	}

	public int getCantidadObligatorias() {
		return cantidadObligatorias;
	}

	public static int getNumeroLP() {
		return numeroLP;
	}

	public static void setNumeroLP(int numeroLP) {
		LearningPath.numeroLP = numeroLP;
	}

	public int getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(int calificaciones) {
		this.calificaciones = calificaciones;
	}

	public LPRS getLprsActual() {
		return lprsActual;
	}

	public void setLprsActual(LPRS lprsActual) {
		this.lprsActual = lprsActual;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setEstudiantesInscritos(ArrayList<Estudiante> estudiantesInscritos) {
		this.estudiantesInscritos = estudiantesInscritos;
	}

	public void setProfesorCreador(Profesor profesorCreador) {
		this.profesorCreador = profesorCreador;
	}

	public void eliminarActividad(Actividad actividad) {
		actividades.remove(actividad);
	}

	/**
	 * Obtiene el título de la ruta de aprendizaje.
	 *
	 * @return el título de la ruta de aprendizaje
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Establece el título de la ruta de aprendizaje.
	 *
	 * @param titulo el título de la ruta de aprendizaje
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la descripción de la ruta de aprendizaje.
	 *
	 * @return la descripción de la ruta de aprendizaje
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción de la ruta de aprendizaje.
	 *
	 * @param descripcion la descripción de la ruta de aprendizaje
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el nivel de dificultad de la ruta de aprendizaje.
	 *
	 * @return el nivel de dificultad de la ruta de aprendizaje
	 */
	public String getNivelDificultad() {
		return nivelDificultad;
	}

	/**
	 * Establece el nivel de dificultad de la ruta de aprendizaje.
	 *
	 * @param nivelDificultad el nivel de dificultad de la ruta de aprendizaje
	 */
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	/**
	 * Obtiene los objetivos de la ruta de aprendizaje.
	 *
	 * @return una lista de los objetivos de la ruta de aprendizaje
	 */
	public ArrayList<String> getObjetivos() {
		return objetivos;
	}

	/**
	 * Añade un objetivo a la lista de objetivos de la ruta de aprendizaje.
	 *
	 * @param objetivos el objetivo a añadir
	 */
	public void addObjetivo(String objetivos) {
		this.objetivos.add(objetivos);
	}

	/**
	 * Elimina un objetivo de la lista de objetivos de la ruta de aprendizaje.
	 *
	 * @param objetivos el objetivo a eliminar
	 */
	public void delObjetivos(String objetivos) {
		this.objetivos.remove(objetivos);
	}

	/**
	 * Cambia los objetivos de la ruta de aprendizaje.
	 * 
	 * @return
	 */
	public void setObjetivos(ArrayList<String> objetivos) {
		this.objetivos = objetivos;
	}

	/**
	 * Obtiene la calificación de la ruta de aprendizaje.
	 *
	 * @return la calificación de la ruta de aprendizaje
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Cambia la calificación de la ruta de aprendizaje.
	 *
	 * @param d la nueva calificación de la ruta de aprendizaje
	 */
	public void cambiarRating(double d) {
		d = (this.rating * calificaciones + d) / (calificaciones + 1);
		calificaciones++;
		this.rating = d;
	}

	/**
	 * Obtiene el ID de la ruta de aprendizaje.
	 *
	 * @return el ID de la ruta de aprendizaje
	 */
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
		numeroLP = Integer.parseInt(ID);
		numeroLP++;
	}

	/**
	 * Obtiene la duración de la ruta de aprendizaje.
	 *
	 * @return la duración de la ruta de aprendizaje
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Cambia la duración de la ruta de aprendizaje.
	 *
	 * @param duracion la nueva duración de la ruta de aprendizaje
	 */
	public void cambiarDuracion(int duracion) {
		this.duracion = getDuracion() + duracion;
	}

	/**
	 * Obtiene las actividades de la ruta de aprendizaje.
	 *
	 * @return una lista de las actividades de la ruta de aprendizaje
	 */
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	/**
	 * Añade una actividad a la lista de actividades de la ruta de aprendizaje.
	 * 
	 * @param actividad la actividad a añadir
	 */
	public void aniadirActividad(Actividad actividad) {
		actividades.add(actividad);
	}

	/**
	 * Obtiene los estudiantes inscritos en la ruta de aprendizaje.
	 *
	 * @return una lista de los estudiantes inscritos en la ruta de aprendizaje
	 */
	public ArrayList<Estudiante> getEstudiantesInscritos() {
		return estudiantesInscritos;
	}

	/**
	 * Añade un estudiante a la lista de estudiantes inscritos en la ruta de
	 * aprendizaje.
	 *
	 * @param estudiante el estudiante a añadir
	 */
	public void aniadirEstudiante(Estudiante estudiante) {
		estudiantesInscritos.add(estudiante);
	}

	/**
	 * Elimina un estudiante de la lista de estudiantes inscritos en la ruta de
	 * aprendizaje.
	 *
	 * @param estudiante el estudiante a eliminar
	 */
	public void eliminarEstudiante(Estudiante estudiante) {
		estudiantesInscritos.remove(estudiante);
	}

	/**
	 * Obtiene el profesor creador de la ruta de aprendizaje.
	 *
	 */
	public Profesor getProfesorCreador() {
		return profesorCreador;
	}

	/**
	 * Edita la ruta de aprendizaje.
	 */
	public void editarLearningPath(String titulo, String descripcion, String nivelDificultad,
			ArrayList<String> objetivos, Profesor profesorCreador) throws Exception {
		if (profesorCreador == this.profesorCreador) {
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.nivelDificultad = nivelDificultad;
			this.objetivos = objetivos;
			this.metadatos.setFechaModificacion(obtenerFecha());
			String versionActual = this.metadatos.getVersion();
			int versionActualInt = Integer.parseInt(versionActual) + 1;
			this.metadatos.setVersion(Integer.toString(versionActualInt));
		} else {
			throw new Exception("No tienes permiso para editar esta ruta de aprendizaje.");
		}

	}

	/**
	 * Elimina una ruta de aprendizaje.
	 * 
	 */
	public void eliminarLearningPath() {
		ArrayList<Estudiante> estudiantes = getEstudiantesInscritos();
		for (Estudiante estudiante : estudiantes) {
			estudiante.eliminarLearningPath(getID());
		}

		lprsActual.getManejadorLP().getLearningPaths().remove(this);
		lprsActual.getManejadorLP().learningPathsHashMap().remove(this.getID());
	}

	public void setLPRS(LPRS lprs) {
		this.lprsActual = lprs;
	}

	public Metadato getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Metadato metadatos) {
		this.metadatos = metadatos;
	}

}