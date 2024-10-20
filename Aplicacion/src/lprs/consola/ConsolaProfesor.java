package lprs.consola;

import java.util.ArrayList;

import lprs.logica.cuentas.Profesor;

public class ConsolaProfesor extends ConsolaPrincipal {
    Profesor profesor;

    public ConsolaProfesor(Profesor usuarioEncontrado) {
        super();
        profesor = usuarioEncontrado;
    }

    public void mostrarConsolaProfesor() {
        System.out.println("Bienvenido + " + profesor.getUsuario());
        String[] opciones = { "Ver mis Leaning Paths hechos", "Ver los learning Paths disponibles",
                "Crear un learning path", "Salir" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {

        } else if (opcion == 2) {
            mostrarLearningPathsDisponibles();

        } else if (opcion == 3) {
        	lectura.nextLine();
            System.out.println("Ingrese el titulo del Learning Path: ");
            String titulo = lectura.nextLine();

            System.out.println("Ingrese la descripcion del Learning Path: ");
            String descripcion = lectura.nextLine();

            System.out.println("Ingrese el nivel de dificultad del Learning Path: ");
            String nivelDificultad = lectura.nextLine();

            int cantidadObjetivos = 0;
            while (true) {
                System.out.println("Cuantos objetivos desea agregar? ");
                if (lectura.hasNextInt()) {
                    cantidadObjetivos = lectura.nextInt();
                    lectura.nextLine();
                    if (cantidadObjetivos > 0) {
                        break; 
                    } else {
                        System.out.println("Por favor, ingrese un número mayor que 0.");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                    lectura.next(); 
                }
            }

            ArrayList<String> objetivos = new ArrayList<String>();
            for (int i = 0; i < cantidadObjetivos; i++) {
                System.out.println("Ingrese el objetivo " + (i + 1) + ": ");
                objetivos.add(lectura.nextLine());
            }

            profesor.crearLearningPath(titulo, descripcion, nivelDificultad, objetivos);
            mostrarConsolaProfesor();
        }

        }
}
