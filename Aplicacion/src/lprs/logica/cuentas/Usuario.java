package lprs.logica.cuentas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.PersistenciaUsuario;

public abstract class Usuario {
	protected static final String ESTUDIANTE = "Estudiante";
	protected static final String PROFESOR = "Profesor";
	protected String usuario;
	protected String contrasenia;
	protected String tipo;

	private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();

	public static void crearUsuario(String usuario, String contrasena, int tipo) throws Exception {
		Usuario nuevoUsuario;
		if (tipo == 1) {
			nuevoUsuario = new Estudiante(usuario, contrasena);

		} else if (tipo == 2) {
			nuevoUsuario = new Profesor(usuario, contrasena);
		} else {
			throw new Exception("Este no es un tipo valido, vuelva a intentar");
		}
		agregarUsuario(nuevoUsuario);
		PersistenciaUsuario.guardarUsuario();
	}

	public static Usuario obtenerUsuario(String ID) throws Exception {
		Usuario usuarioEncontrado = usuarios.get(ID);
		if (usuarioEncontrado == null) {
			throw new Exception("Usuario no encontrado");
		} else {
			return usuarioEncontrado;
		}
	}

	public static void agregarUsuario(Usuario usuario) {
		usuarios.put(usuario.getUsuario(), usuario);
	}
	public static Usuario iniciarSesion(String ID, String contrasena) throws Exception {
		Usuario usuario = obtenerUsuario(ID);
		if (usuario.getContrasenia().equals(contrasena)) {
			return usuario;
		} else {
			throw new Exception("La contraseña ingresada no es valida");
		}
	}

	public static Collection<Usuario> getUsuarios() {
		return usuarios.values();
	}
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
