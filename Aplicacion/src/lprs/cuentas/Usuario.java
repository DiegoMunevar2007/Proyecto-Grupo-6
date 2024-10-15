package lprs.cuentas;

import java.util.List;

import lprs.learningPaths.LearningPath;

public abstract class Usuario {
	protected static final String ESTUDIANTE = "Estudiante";
	protected static final String PROFESOR = "Profesor";
	protected String usuario;
	protected String contrasenia;
	protected String tipo;

	public List<LearningPath> learningPathsDisponibles() {
		return LearningPath.getLearningPaths();
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String getTipo() {
		return tipo;
	}

}