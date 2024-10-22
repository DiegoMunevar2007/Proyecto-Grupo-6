package lprs.logica.learningPath;

import java.util.ArrayList;
import java.util.Date;

import lprs.logica.contenido.Actividad;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.principal.LPRS;

public class LearningPath {
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
			Profesor profesorCreador, LPRS lprsActual) {
		this.ID = asignarID();
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
		this.metadatos = new Metadato("1");
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
		calificaciones++;
		d = (this.rating * calificaciones + d) / calificaciones;
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
			ArrayList<String> objetivos, Profesor profesorCreador) throws Exception {
		if (profesorCreador == this.profesorCreador) {
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.nivelDificultad = nivelDificultad;
			this.objetivos = objetivos;
			this.metadatos.setFechaModificacion(new Date());
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
		lprsActual.getManejadorLP().learningPathsHashMap().remove(this);
	}
}