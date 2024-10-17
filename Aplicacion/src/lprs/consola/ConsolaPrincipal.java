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

		System.out.println("Bienvenido a learning Path Recommendation System :) ");
		System.out.println("Iniciar Sesion: 1 , Crear cuenta: 2");

		System.out.println("Ingrese una opcion (1   o   2): ");

		int opcion = lectura.nextInt();

		if (opcion == 1) {
			System.out.println("----------------Iniciar Sesion------------------");

			System.out.println("Ingrese su Usuario: ");

			String usuarioID = lectura.next();
			Usuario usuarioEncontrado = LPRS.obtenerUsuario(usuarioID);
			if (usuarioEncontrado == null) {
				throw new Exception("Usuario no encontrado");
			}
			System.out.println("Ingrese su contraseña: ");

			String contrasena = lectura.next();
			System.out.println(contrasena);
			System.out.println(usuarioEncontrado.getContrasenia());
			if (contrasena.equals(usuarioEncontrado.getContrasenia())) {
				System.out.println("Bienvenido " + usuarioEncontrado.getUsuario());
				if (usuarioEncontrado.getTipo() == "Estudiante") {
					ConsolaEstudiante consolaEstudiante = new ConsolaEstudiante((Estudiante) usuarioEncontrado);
					consolaEstudiante.mostrarConsolaEstudiante();
				} else {

				}
			} else {
				throw new Exception("Contraseña incorrecta, vuelva a intentarlo");
			}
		} else if (opcion == 2) {
			System.out.println("----------------Crear Usuario------------------");
			System.out.println("Ingrese su Usuario: ");

			String usuario = lectura.next();

			System.out.println("Ingrese su contraseña: ");

			String contrasena = lectura.next();

			System.out.println("Estudiante: 1 , Profesor: 2 ");

			int tipo = lectura.nextInt();
			Usuario nuevoUsuario;
			if (tipo == 1) {
				nuevoUsuario = new Estudiante(usuario, contrasena);
			} else if (tipo == 2) {
				nuevoUsuario = new Profesor(usuario, contrasena);
			} else {
				throw new Exception("Este no es un tipo valido, vuelva a intentar");
			}
			LPRS.agregarUsuario(usuario, nuevoUsuario);
			System.out.println("Usuario agregado con exito yipeee");
			mostrarConsolaPrincipal();

		} else {
			throw new Exception("Esta no es una opcion valida, vuelva a intentar");
		}

	}

	public void mostrarOpciones(int cantidad, String[] opciones) {
		System.out.println("Seleccione una opcion: ");
		for (int i = 0; i < cantidad; i++) {
			System.out.println(i+1 + ". " + opciones[i]);
		}
	}

}
