package lprs.principal;

import lprs.consola.ConsolaPrincipal;
import lprs.persistencia.PersistenciaLP;
import lprs.persistencia.PersistenciaUsuario;

public class LPRS {

	public static void main(String[] args) {

		try {
			cargarDatos();
			ConsolaPrincipal consolaP = new ConsolaPrincipal();
			consolaP.mostrarConsolaPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cargarDatos() throws Exception {
		PersistenciaUsuario.cargarUsuarios();
		PersistenciaLP.cargarLP();
	}

	public static void guardarDatos() throws Exception {
		PersistenciaUsuario.guardarUsuario();
		PersistenciaLP.guardarLP();
	}
}
