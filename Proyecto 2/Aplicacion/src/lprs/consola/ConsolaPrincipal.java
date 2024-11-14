package lprs.consola;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Resenia;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

import java.util.Scanner;

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
	public void reseniarActividad(Actividad actividad, Usuario usuario){
		Scanner lectura = new Scanner(System.in);
		System.out.println("Ingrese su resenia: ");
		String resenia = lectura.nextLine();
		System.out.println("Ingrese su calificacion: ");
		double calificacion = lectura.nextDouble();
		Resenia nuevaResenia = new Resenia(usuario, resenia, calificacion);
		actividad.addResenia(nuevaResenia);
		System.out.println("Resenia agregada exitosamente.");
		}

}
