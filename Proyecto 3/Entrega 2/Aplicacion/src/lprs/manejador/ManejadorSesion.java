package lprs.manejador;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.exceptions.UsuarioNotFoundException;
import lprs.exceptions.UsuarioRepetidoException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class ManejadorSesion implements Serializable {
	private static final String ESTUDIANTE = "Estudiante";
	private static final String PROFESOR = "Profesor";
	private LPRS lprsActual;
	private HashMap<String, Usuario> usuarios;

	public ManejadorSesion(LPRS lprsActual) {
		this.lprsActual = lprsActual;
		usuarios = new HashMap<String, Usuario>();
	}

	public void crearUsuario(String usuario, String contrasena, int tipo) throws Exception {
		Usuario nuevoUsuario;

		if (usuarios.containsKey(usuario)) {
			throw new UsuarioRepetidoException(usuario);
		}
		if (tipo == 1) {
			nuevoUsuario = new Estudiante(usuario, contrasena, lprsActual);
		} else if (tipo == 2) {

			nuevoUsuario = new Profesor(usuario, contrasena, lprsActual);
		} else {
			throw new Exception("Este no es un tipo valido, vuelva a intentar");
		}
		agregarUsuario(nuevoUsuario);
	}

	public Usuario  obtenerUsuario(String ID) throws UsuarioNotFoundException {
		Usuario usuarioEncontrado = usuarios.get(ID);
		if (usuarioEncontrado == null) {
			throw new UsuarioNotFoundException("Usuario no encontrado", ID);
		} else {
			return usuarioEncontrado;
		} 
	}

	public void agregarUsuario(Usuario usuario) {
		HashMap<String, Usuario> hashUsuarios = getUsuarios();
		hashUsuarios.put(usuario.getUsuario(), usuario);
	}

	public Usuario iniciarSesion(String ID, String contrasena) throws Exception {
		Usuario usuario;
        usuario = obtenerUsuario(ID);
        if (usuario.getContrasenia().equals(contrasena)) {
			return usuario;
		} else {
			throw new ContraseniaIncorrectaException("La contraseña ingresada no es valida");

		}
	}

	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

	public Collection<Usuario> getUsuariosLista() {
		return getUsuarios().values();
	}

	public String getEstudianteFinal() {
		return ESTUDIANTE;
	}

	public String getProfesorFinal() {
		return PROFESOR;
	}

	public LPRS getLprsActual() {
		return lprsActual;
	}

	public void setLprsActual(LPRS lprsActual) {
		this.lprsActual = lprsActual;
	}

	public static String getEstudiante() {
		return ESTUDIANTE;
	}

	public static String getProfesor() {
		return PROFESOR;
	}

}
