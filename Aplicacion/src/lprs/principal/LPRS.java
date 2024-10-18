package lprs.principal;

import java.util.HashMap;

import lprs.consola.ConsolaPrincipal;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;

public class LPRS {

	private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();

	public static void crearUsuario(String usuario, String contrasena, int tipo) throws Exception{
		Usuario nuevoUsuario;
		if (tipo == 1) {
			nuevoUsuario = new Estudiante(usuario, contrasena);
		} else if (tipo == 2) {
			nuevoUsuario = new Profesor(usuario, contrasena);
		} else {
			throw new Exception("Este no es un tipo valido, vuelva a intentar");
		}
		LPRS.agregarUsuario(usuario, nuevoUsuario);
	}

	public static Usuario obtenerUsuario(String ID) throws Exception{
		Usuario usuarioEncontrado=usuarios.get(ID);
		if (usuarioEncontrado == null) {
			throw new Exception("Usuario no encontrado");
		}
		else {
			return usuarioEncontrado;
		}
	}

	public static void agregarUsuario(String ID, Usuario usuario) {
		usuarios.put(ID, usuario);
	}
	
	public static void main(String[] args) {
		// TODO: Cargar a partir de la persistencia los usuarios antiguos
		ConsolaPrincipal consolaP = new ConsolaPrincipal();
		try {
			consolaP.mostrarConsolaPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Usuario iniciarSesion(Usuario usuario, String contrasena) throws Exception {
		if (usuario.getContrasenia().equals(contrasena)){
			return usuario;
		}
		else {
			throw new Exception("La contraseña ingresada no es valida");
		}
	}

}
