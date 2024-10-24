package lprs.principal;

import java.io.Serializable;

import lprs.consola.ConsolaPrincipal;
import lprs.manejador.ManejadorLP;
import lprs.manejador.ManejadorSesion;
import lprs.persistencia.PersistenciaLP;
import lprs.persistencia.PersistenciaUsuario;

public class LPRS implements Serializable {
	ManejadorLP manejadorLP;
	ManejadorSesion manejadorSesion;

	public LPRS() {
		manejadorLP = new ManejadorLP();
		manejadorSesion = new ManejadorSesion(this);
	}

	public static void main(String[] args) {

		LPRS lprs = new LPRS();
		try {
			lprs.cargarDatos();
			ConsolaPrincipal consolaP = new ConsolaPrincipal(lprs);
			consolaP.mostrarConsolaPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ManejadorLP getManejadorLP() {
		return manejadorLP;
	}

	public void setManejadorLP(ManejadorLP manejadorLP) {
		this.manejadorLP = manejadorLP;
	}

	public ManejadorSesion getManejadorSesion() {
		return manejadorSesion;
	}

	public void setManejadorSesion(ManejadorSesion manejadorSesion) {
		this.manejadorSesion = manejadorSesion;
	}

	public void cargarDatos() throws Exception {

		PersistenciaUsuario persU = new PersistenciaUsuario();
		PersistenciaLP persLP = new PersistenciaLP();
		persU.cargarUsuarios2(manejadorSesion);
		persLP.cargarLP2(manejadorSesion, manejadorLP);
	}

	public void guardarDatos() throws Exception {

		PersistenciaUsuario persU = new PersistenciaUsuario();
		PersistenciaLP persLP = new PersistenciaLP();
		persU.guardarUsuario2(manejadorSesion);
		persLP.guardarLP2(manejadorSesion, manejadorLP);
	}
}
