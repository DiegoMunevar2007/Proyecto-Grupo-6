package lprs.logica.learningPath;

import java.util.ArrayList;
import java.util.HashMap;

import lprs.logica.contenido.Actividad;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;

public class LearningPath {
	private static int numeroLP;
	private String ID;
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private ArrayList<String> objetivos;
	private int duracion;
	private float rating;
	private int calificaciones;
	private ArrayList<Actividad> actividades;
	private ArrayList<Estudiante> estudiantesInscritos;
	private Profesor profesorCreador;
	protected static HashMap<String, LearningPath> learningPathsHash;
	protected static ArrayList<LearningPath> learningPaths;

	/**
	 * Constructor para crear un objeto LearningPath.
	 *
	 * @param titulo          el título de la ruta de aprendizaje
	 * @param descripcion     la descripción de la ruta de aprendizaje
	 * @param nivelDificultad el nivel de dificultad de la ruta de aprendizaje
	 * @param objetivos       una lista de los objetivos de la ruta de aprendizaje
	 * @param duracion        la duración de la ruta de aprendizaje
	 * @param rating          la calificación de la ruta de aprendizaje
	 * @param profesorCreador el profesor que crea la ruta de aprendizaje
	 */
	public LearningPath(String titulo, String descripcion, String nivelDificultad, ArrayList<String> objetivos,
			int duracion, float rating, Profesor profesorCreador) {
		this.ID = asignarID();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.objetivos = new ArrayList<String>();
		this.duracion = duracion;
		this.rating = rating;
		this.calificaciones = 0;
		this.actividades = new ArrayList<Actividad>();
		this.estudiantesInscritos = new ArrayList<Estudiante>();
		this.profesorCreador = profesorCreador;
	}

	/**
	 * Constructor para crear una copia de un objeto LearningPath.
	 *
	 * @param LP              el objeto LearningPath a copiar
	 * @param profesorCreador el profesor que crea la copia de la ruta de
	 *                        aprendizaje
	 */
	public LearningPath(LearningPath LP, Profesor profesorCreador) {
		this.ID = asignarID();
		this.titulo = LP.getTitulo();
		this.descripcion = LP.getDescripcion();
		this.nivelDificultad = LP.getNivelDificultad();
		this.objetivos = LP.getObjetivos();
		this.duracion = LP.getDuracion();
		this.rating = LP.getRating();
		this.calificaciones = LP.calificaciones;
		this.actividades = LP.getActividades();
		this.estudiantesInscritos = new ArrayList<Estudiante>();
		this.profesorCreador = profesorCreador;
	}

	/**
	 * Obtener un learning path por su ID
	 * 
	 * @param ID el ID del learning path
	 * @return el learning path con el ID dado
	 */
	public static LearningPath getLearningPath(String ID) {
		return learningPathsHash.get(ID);
	}

	/**
	 * Añadir un learning path
	 * 
	 * @param learningPath
	 */
	public static void addLearningPath(LearningPath learningPath) {
		learningPaths.add(learningPath);
		learningPathsHash.put(learningPath.getID(), learningPath);
	}

	/**
	 * Obtener todos los learning paths
	 * 
	 * @return una lista con todos los learning paths
	 */
	public static ArrayList<LearningPath> getLearningPaths() {
		return learningPaths;
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
	public void aniadirObjetivos(String objetivos) {
		this.objetivos.add(objetivos);
	}

	/**
	 * Elimina un objetivo de la lista de objetivos de la ruta de aprendizaje.
	 *
	 * @param objetivos el objetivo a eliminar
	 */
	public void eliminarObjetivos(String objetivos) {
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
	public float getRating() {
		return rating;
	}

	/**
	 * Cambia la calificación de la ruta de aprendizaje.
	 *
	 * @param rating la nueva calificación de la ruta de aprendizaje
	 */
	public void cambiarRating(float rating) {
		calificaciones++;
		rating = (this.rating * calificaciones + rating) / calificaciones;
		this.rating = rating;
	}

	/**
	 * Obtiene el ID de la ruta de aprendizaje.
	 *
	 * @return el ID de la ruta de aprendizaje
	 */
	public String getID() {
		return ID;
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
	 * Asigna un nuevo ID a la ruta de aprendizaje.
	 *
	 * @return el nuevo ID asignado
	 */
	private static String asignarID() {
		String idRetorno = Integer.toString(numeroLP);
		numeroLP++;
		return idRetorno;
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
	 * @param profesorCreador el profesor creador de la ruta de aprendizaje
	 */
	public Profesor getProfesorCreador() {
		return profesorCreador;
	}

	/**
	 * Edita la ruta de aprendizaje.
	 */
	public void editarLearningPath(String titulo, String descripcion, String nivelDificultad,
			ArrayList<String> objetivos, Profesor profesorCreador) {
		if (profesorCreador == this.profesorCreador) {
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.nivelDificultad = nivelDificultad;
			this.objetivos = objetivos;
		} else {
			System.out.println("No tienes permiso para editar esta ruta de aprendizaje.");
			// TODO: throw exception en lugar de imprimir mensaje
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
		learningPaths.remove(this);
		learningPathsHash.remove(getID());
	}
}