package lprs.principal;

import java.util.HashMap;

import lprs.consola.ConsolaPrincipal;
import lprs.logica.cuentas.Usuario;

public class LPRS {

	private static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();

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

	public static Usuario obtenerUsuario(String ID) {
		return usuarios.get(ID);
	}

	public static void agregarUsuario(String ID, Usuario usuario) {
		usuarios.put(ID, usuario);
	}
}
