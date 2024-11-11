package lprs.consola;

import lprs.principal.LPRS;

public class ConsolaPrincipal {

	protected LPRS lprsActual;

	public ConsolaPrincipal(LPRS lprs) {
		lprsActual = lprs;
	}

	public void mostrarOpciones(int cantidad, String[] opciones) {
		System.out.println("--------------------------------");
		System.out.println("Seleccione una opcion: ");
		for (int i = 0; i < cantidad; i++) {
			System.out.println(i + 1 + ". " + opciones[i]);
		}
		System.out.println("--------------------------------");
	}

}
