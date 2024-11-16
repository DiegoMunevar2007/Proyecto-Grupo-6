package lprs.consola;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Resenia;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolaPrincipal {

	protected LPRS lprsActual;
	protected Scanner lectura;

	public ConsolaPrincipal(LPRS lprs) {
		lprsActual = lprs;
		lectura = new Scanner(System.in);
	}

	public Scanner getLectura() {
		return lectura;
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

	public String pedirString(String mensaje) {
		System.out.println(mensaje);
		String respuesta = "";
		boolean valido = false;
		while (!valido) {
			try {
				respuesta = lectura.nextLine();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Por favor ingrese un string.");
				lectura.nextLine();
			}
		}
    	return respuesta;
	}
	public int pedirInt(String mensaje) {
		System.out.println(mensaje);
		int respuesta = 0;
		boolean valido = false;
		while (!valido) {
			try {
				respuesta = lectura.nextInt();
				lectura.nextLine();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Por favor ingrese un número entero.");
				lectura.nextLine();
			}
		}
		return respuesta;
	}

	public double pedirDouble(String mensaje) {
    System.out.println(mensaje);
    double respuesta = 0;
    boolean valido = false;
    while (!valido) {
        try {
            respuesta = lectura.nextDouble();
            lectura.nextLine();
            valido = true;
        } catch (InputMismatchException e) {
            System.out.println("Por favor ingrese un número.");
            lectura.nextLine();
        }
    }
    return respuesta;
}

}
