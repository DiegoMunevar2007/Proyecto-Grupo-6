package lprs.principal;

import java.io.Serializable;

import lprs.manejador.ManejadorLP;
import lprs.manejador.ManejadorSesion;
import lprs.metodosPrueba.MetodosPrueba;
import lprs.persistencia.PersistenciaLP;
import lprs.persistencia.PersistenciaUsuario;

public class LPRS implements Serializable {
	ManejadorLP manejadorLP;
	ManejadorSesion manejadorSesion;

	public LPRS() {
		manejadorLP = new ManejadorLP();
		manejadorSesion = new ManejadorSesion(this);
	}

	public static void main(String[] args) throws Exception {

		LPRS lprs = new LPRS();
		// try {
		// lprs.cargarDatos();
		// ConsolaPrincipal consolaP = new ConsolaPrincipal(lprs);
		// consolaP.mostrarConsolaPrincipal();
		// } catch (Exception e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		MetodosPrueba metodoPrueba = new MetodosPrueba(lprs);
		try {
			metodoPrueba.RF1();
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			metodoPrueba.RF2("Estudiante", "Hola");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			metodoPrueba.RF2("Profe", "Profe");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			metodoPrueba.RF2("Profe2", "Profe2");
		} catch (Exception e) {
			e.getMessage();
		}
		metodoPrueba.RF3();
		metodoPrueba.RF4();
		lprs.guardarDatos();
		metodoPrueba.RF5();
		metodoPrueba.RF7();
		metodoPrueba.RF8();
		metodoPrueba.RF10();
		metodoPrueba.RF13();
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
