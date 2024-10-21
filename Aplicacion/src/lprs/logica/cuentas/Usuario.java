package lprs.logica.cuentas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import lprs.exceptions.UsuarioNotFoundException;
import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.PersistenciaUsuario;

public abstract class Usuario {
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

	public static String getEstudiante() {
		return ESTUDIANTE;
	}

	public static String getProfesor() {
		return PROFESOR;
	}

}
