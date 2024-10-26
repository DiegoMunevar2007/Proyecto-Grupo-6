package lprs.manejador;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import lprs.exceptions.UsuarioNotFoundException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class ManejadorSesion implements Serializable {
	protected static final String ESTUDIANTE = "Estudiante";
	protected static final String PROFESOR = "Profesor";
	private LPRS lprsActual;
	private HashMap<String, Usuario> usuarios;

	public ManejadorSesion(LPRS lprsActual) {
		this.lprsActual = lprsActual;
		usuarios = new HashMap<String, Usuario>();
	}

	public void crearUsuario(String usuario, String contrasena, int tipo) throws Exception {
		Usuario nuevoUsuario;
		if (tipo == 1) {
			if (usuarios.containsKey(usuario)) {
				throw new Exception("El usuario ya existe");
			}
			nuevoUsuario = new Estudiante(usuario, contrasena, lprsActual);

		} else if (tipo == 2) {
			nuevoUsuario = new Profesor(usuario, contrasena, lprsActual);
		} else {
			throw new Exception("Este no es un tipo valido, vuelva a intentar");
		}
		agregarUsuario(nuevoUsuario);
	}

	public Usuario obtenerUsuario(String ID) throws Exception {
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
}
