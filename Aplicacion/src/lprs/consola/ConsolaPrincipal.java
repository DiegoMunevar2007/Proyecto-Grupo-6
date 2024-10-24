package lprs.consola;

import java.util.List;
import java.util.Scanner;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaPrincipal {

	Scanner lectura;
	LPRS lprsActual;
	Usuario usuarioActual;

	public ConsolaPrincipal(LPRS lprs) {
		lectura = new Scanner(System.in);
		lprsActual = lprs;
		usuarioActual = null;
	}

	public void mostrarConsolaPrincipal() throws Exception {
		System.out.println("Bienvenido a learning Path Recommendation System :) ");
		System.out.println("Iniciar Sesion: 1 , Crear cuenta: 2 , Cargar Usuarios: 3");

		System.out.println("Ingrese una opcion (1, 2 o 3): ");

		int opcion = lectura.nextInt();

		if (opcion == 1) {
			Usuario usuarioEncontrado = iniciarSesion();
			if (usuarioEncontrado == null) {
				mostrarConsolaPrincipal();
			} else {
				usuarioActual = usuarioEncontrado;
				if (usuarioEncontrado.getTipo().equals("Estudiante")) {
					ConsolaEstudiante consolaEstudiante = new ConsolaEstudiante(lprsActual,
							(Estudiante) usuarioEncontrado);
					consolaEstudiante.mostrarConsolaEstudiante();
					return;
				} else {
					ConsolaProfesor consolaProfesor = new ConsolaProfesor(lprsActual, (Profesor) usuarioEncontrado);
					consolaProfesor.mostrarConsolaProfesor();
					return;
				}
			}

		} else if (opcion == 2) {
			System.out.println("----------------Crear Usuario------------------");
			System.out.println("Ingrese su Usuario: ");

			String usuario = lectura.next();

			System.out.println("Ingrese su contraseña: ");

			String contrasena = lectura.next();

			System.out.println("Estudiante: 1 , Profesor: 2 ");

			int tipo = lectura.nextInt();
			lprsActual.getManejadorSesion().crearUsuario(usuario, contrasena, tipo);

			System.out.println("¡Usuario agregado con exito!");
			mostrarConsolaPrincipal();

		} else if (opcion == 3) {
			lprsActual.cargarDatos();
			mostrarConsolaPrincipal();
		}

		else {
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

		System.out.println("Ingrese su contraseña: ");
		String contrasena = lectura.next();

		return lprsActual.getManejadorSesion().iniciarSesion(usuarioID, contrasena);

	}

	public void mostrarLearningPathsDisponibles() {

		List<LearningPath> learningPathsDisponibles = lprsActual.getManejadorLP().getLearningPaths();
		if (learningPathsDisponibles.isEmpty()) {
			System.out.println("No hay Learning Paths disponibles.");
			return;
		}
		for (int i = 0; i < learningPathsDisponibles.size(); i++) {
			System.out.println(i + 1 + ". " + learningPathsDisponibles.get(i).getTitulo());
		}
	}

}
