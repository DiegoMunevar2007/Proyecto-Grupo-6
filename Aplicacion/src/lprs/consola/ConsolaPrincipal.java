package lprs.consola;

import java.util.Scanner;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class ConsolaPrincipal {

	Scanner lectura;

	public ConsolaPrincipal() {
		lectura = new Scanner(System.in);
	}

	public void mostrarConsolaPrincipal() throws Exception {
		// TODO: Cargar los usuarios a partir del JSON
		System.out.println("Bienvenido a learning Path Recommendation System :) ");
		System.out.println("Iniciar Sesion: 1 , Crear cuenta: 2");

		System.out.println("Ingrese una opcion (1   o   2): ");

		int opcion = lectura.nextInt();

		if (opcion == 1) {
			Usuario usuarioEncontrado = iniciarSesion();
			if (usuarioEncontrado.getTipo() == "Estudiante") {
				ConsolaEstudiante consolaEstudiante = new ConsolaEstudiante((Estudiante) usuarioEncontrado);
				consolaEstudiante.mostrarConsolaEstudiante();
			} else {
				ConsolaProfesor consolaProfesor = new ConsolaProfesor((Profesor) usuarioEncontrado);
			}

		} else if (opcion == 2) {
			System.out.println("----------------Crear Usuario------------------");
			System.out.println("Ingrese su Usuario: ");

			String usuario = lectura.next();

			System.out.println("Ingrese su contraseña: ");

			String contrasena = lectura.next();

			System.out.println("Estudiante: 1 , Profesor: 2 ");

			int tipo = lectura.nextInt();
			LPRS.crearUsuario(usuario, contrasena, tipo);

			System.out.println("Usuario agregado con exito yipeee");
			mostrarConsolaPrincipal();

		} else {
			throw new Exception("Esta no es una opcion valida, vuelva a intentar");
		}

	}

	public void mostrarOpciones(int cantidad, String[] opciones) {
		System.out.println("Seleccione una opcion: ");
		for (int i = 0; i < cantidad; i++) {
			System.out.println(i + 1 + ". " + opciones[i]);
		}
	}

	public Usuario iniciarSesion() throws Exception {
		System.out.println("----------------Iniciar Sesion------------------");

		System.out.println("Ingrese su Usuario: ");

		String usuarioID = lectura.next();
		Usuario usuarioEncontrado = LPRS.obtenerUsuario(usuarioID);

		System.out.println("Ingrese su contraseña: ");
		String contrasena = lectura.next();
		return LPRS.iniciarSesion(usuarioEncontrado, contrasena);

	}

}
