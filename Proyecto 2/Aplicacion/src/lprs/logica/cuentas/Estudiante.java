package lprs.logica.cuentas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class Estudiante extends Usuario {
	private final String ESTUDIANTE = "Estudiante";
	private HashMap<String, Avance> avancesEstudiante;
	private List<LearningPath> learningPathsInscritos;

	public Avance obtenerAvance(String ID) {
		return avancesEstudiante.get(ID);
	}

	/**
	 * Constructor para crear un objeto Estudiante.
	 *
	 * @param usuario     el nombre de usuario del estudiante
	 * @param contrasenia la contraseña del estudiante
	 */
	public Estudiante(String usuario, String contrasenia, LPRS lprsActual) {
		super(usuario, contrasenia, lprsActual);
		this.tipo = ESTUDIANTE;
		learningPathsInscritos = new ArrayList<LearningPath>();
		avancesEstudiante = new HashMap<String, Avance>();
	}

	/**
	 * Inscribe al estudiante en una ruta de aprendizaje.
	 *
	 * @param ID el ID de la ruta de aprendizaje en la que inscribir al estudiante
	 */
	public void inscribirLearningPath(String ID) {
		LearningPath lP = lprsActual.getManejadorLP().getLearningPath(ID);
		learningPathsInscritos.add(lP);
		lP.aniadirEstudiante(this);
		Avance nuevoAvance = new Avance(lP.obtenerFecha(), lP); // TODO: Verificar que Date de esta manera si sea valido
		avancesEstudiante.put(ID, nuevoAvance);
	}

	/**
	 * Elimina la inscripción del estudiante en una ruta de aprendizaje.
	 *
	 * @param ID el ID de la ruta de aprendizaje de la que eliminar la inscripción
	 *           del estudiante
	 */
	public void eliminarLearningPath(String ID) {
		LearningPath lP = lprsActual.getManejadorLP().getLearningPath(ID);
		learningPathsInscritos.remove(lP);
		lP.eliminarEstudiante(this);
		avancesEstudiante.remove(ID);
	}


	public List<LearningPath> getLearningPathsInscritos() {
		return learningPathsInscritos;
	}

	public HashMap<String, Avance> getAvancesEstudiante() {
		return avancesEstudiante;
	}

	public Avance getAvance(String ID) {
		return avancesEstudiante.get(ID);
	}

}
