package lprs.logica.cuentas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

public class Estudiante extends Usuario {
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
	public Estudiante(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.tipo = ESTUDIANTE;
		learningPathsInscritos = new ArrayList<LearningPath>();
	}

	/**
	 * Inscribe al estudiante en una ruta de aprendizaje.
	 *
	 * @param ID el ID de la ruta de aprendizaje en la que inscribir al estudiante
	 */
	public void inscribirLearningPath(String ID) {
		LearningPath lP = LearningPath.getLearningPath(ID);
		learningPathsInscritos.add(lP);
		lP.aniadirEstudiante(this);
		Avance nuevoAvance = new Avance(new Date(),lP); //TODO: Verificar que Date de esta manera si sea valido
		avancesEstudiante.put(ID, nuevoAvance);
	}

	/**
	 * Elimina la inscripción del estudiante en una ruta de aprendizaje.
	 *
	 * @param ID el ID de la ruta de aprendizaje de la que eliminar la inscripción
	 *           del estudiante
	 */
	public void eliminarLearningPath(String ID) {
		LearningPath lP = LearningPath.getLearningPath(ID);
		learningPathsInscritos.remove(lP);
		lP.eliminarEstudiante(this);
		avancesEstudiante.remove(ID);
	}

}
