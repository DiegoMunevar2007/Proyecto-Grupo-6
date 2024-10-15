package lprs.logica.cuentas;

import java.util.ArrayList;
import java.util.List;

import lprs.logica.learningPath.LearningPath;

public class Estudiante extends Usuario {
	private List<LearningPath> learningPathsInscritos;

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
	}

}
