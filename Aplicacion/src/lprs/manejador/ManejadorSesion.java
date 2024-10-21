package lprs.manejador;

import java.util.Collection;
import java.util.HashMap;

import lprs.exceptions.UsuarioNotFoundException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.persistencia.PersistenciaUsuario;

public class ManejadorSesion {
	protected static final String ESTUDIANTE = "Estudiante";
	protected static final String PROFESOR = "Profesor";
	private HashMap<String, Usuario> usuarios
	
	public ManejadorSesion() {
		usuarios =  = new HashMap<String, Usuario>();
	}
	
	
	
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
			throw new UsuarioNotFoundException("Usuario no encontrado", ID);
		} else {
			return usuarioEncontrado;
		}
	}

	public static void agregarUsuario(Usuario usuario) {
		usuarios.put(usuario.getUsuario(), usuario);
	}

	public static Usuario iniciarSesion(String ID, String contrasena) throws Exception {
		Usuario usuario;
		try {
			usuario = obtenerUsuario(ID);
		} catch (UsuarioNotFoundException e) {
			System.out.println("Ocurrio un error! " + e.getMessage());
			System.out.println("El usuario ingresado fue: " + e.getUsuario());
			System.out.println("----------Por favor, vuelva a intentar--------");
			return null;
		}
		if (usuario.getContrasenia().equals(contrasena)) {
			return usuario;
		} else {
			throw new Exception("La contraseña ingresada no es valida");

		}
	}

	public static Collection<Usuario> getUsuarios() {
		return usuarios.values();
	}
}
