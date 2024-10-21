package lprs.consola;

import java.util.ArrayList;
import java.util.Collection;

import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaProfesor extends ConsolaPrincipal {
    Profesor profesor;

    public ConsolaProfesor(LPRS lprsActual, Profesor usuarioEncontrado) {
        super(lprsActual);
        profesor = usuarioEncontrado;
    }

    public void mostrarConsolaProfesor() {
        System.out.println("Bienvenido " + profesor.getUsuario());
        String[] opciones = { "Ver mis Learning Paths hechos", "Ver los learning Paths disponibles",
                "Crear un learning path", "Salir" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            mostrarLearningPathsDisponibles();

        } else if (opcion == 3) {
            crearLearningPath();
        } else if (opcion == 4) {
            System.out.println("Hasta luego!");
            try {
                lprsActual.guardarDatos();
            } catch (Exception e) {
                System.out.println("Error al guardar los datos");
            }
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaProfesor();
        }

    }

    public void crearLearningPath() {
        lectura.nextLine();
        System.out.println("Ingrese el titulo del Learning Path: ");
        String titulo = lectura.nextLine();

        System.out.println("Ingrese la descripcion del Learning Path: ");
        String descripcion = lectura.nextLine();

        System.out.println("Ingrese el nivel de dificultad del Learning Path: (Principiante, Intermedio, Avanzado)");
        String nivelDificultad = lectura.nextLine();
        if (!nivelDificultad.equals("Principiante") && !nivelDificultad.equals("Intermedio")
                && !nivelDificultad.equals("Avanzado")) {
            System.out.println("Nivel de dificultad no válido. Por favor, ingrese un nivel de dificultad válido.");
            crearLearningPath();
            return;
        }
        ArrayList<String> objetivos = new ArrayList<>();
        boolean terminado = false;
        while (!terminado) {
            System.out.println("Ingrese un objetivo: ");
            String objetivo = lectura.nextLine();
            objetivos.add(objetivo);

            System.out.println("¿Desea agregar otro objetivo? (s/n): ");
            String respuesta = lectura.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                terminado = true;
            }
        }
        String ID =profesor.crearLearningPath(titulo, descripcion, nivelDificultad, objetivos);
        System.out.println("El learning path se ha creado exitosamente con el ID: "+ ID);
        mostrarConsolaProfesor();
    }

    public void mostrarLearningPathsHechos() {
        Collection<LearningPath> learningPathsHechos = profesor.getLearningPathsCreados();
        if (learningPathsHechos.isEmpty()) {
            System.out.println("No tienes Learning Paths creados.");
            mostrarConsolaProfesor();
            return;
        }
        int indice = 1;
        for (LearningPath lp : learningPathsHechos) {
            System.out.println(indice + ". " + lp.getTitulo());
            indice++;
        }
        System.out.println("¿Que desea hacer?");
        String[] opciones = { "Ver informacion detallada del Learning Path", "Modificar un Learning Path",
                "Eliminar un Learning Path", "Volver" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            System.out.println("Ingrese el número del Learning Path que desea ver: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > learningPathsHechos.size()) {
                System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
                mostrarLearningPathsHechos();
                return;
            }
            LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];
            mostrarLearningPathCompleto(lp);
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            System.out.println("Ingrese el número del Learning Path que desea modificar: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > learningPathsHechos.size()) {
                System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
                mostrarLearningPathsHechos();
                return;
            }
            LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];
            modificarLearningPath(lp);
        } else if (opcion == 3) {
            System.out.println("Ingrese el número del Learning Path que desea eliminar: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > learningPathsHechos.size()) {
                System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
                mostrarLearningPathsHechos();
                return;
            }
            LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];
            profesor.eliminarLearningPath(lp.getID());
            mostrarLearningPathsHechos();
        } else if (opcion == 4) {
            mostrarConsolaProfesor();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsHechos();
        }
    }

    public void modificarLearningPath(LearningPath lp) {
        System.out.println("¿Desea modificar el título? (s/n)");
        String respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo título: ");
            String titulo = lectura.nextLine();
            lp.setTitulo(titulo);
        }
        System.out.println("¿Desea modificar la descripción? (s/n)");
        respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la nueva descripción: ");
            String descripcion = lectura.nextLine();
            lp.setDescripcion(descripcion);
        }
        System.out.println("¿Desea modificar el nivel de dificultad? (s/n)");
        respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo nivel de dificultad: ");
            String nivelDificultad = lectura.next();
            lp.setNivelDificultad(nivelDificultad);
        }
        System.out.println("¿Desea modificar los objetivos? (s/n)");
        respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            ArrayList<String> objetivos = new ArrayList<>();
            boolean terminado = false;
            while (!terminado) {
                System.out.println("Ingrese un objetivo: ");
                String objetivo = lectura.nextLine();
                objetivos.add(objetivo);

                System.out.println("¿Desea agregar otro objetivo? (s/n): ");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("n")) {
                    terminado = true;
                }
            }
            lp.setObjetivos(objetivos);
        }
        System.out.println("Learning Path modificado con éxito.");
        mostrarLearningPathsHechos();
    }

    public void mostrarLearningPathCompleto(LearningPath lp) {
        System.out.println("Titulo: " + lp.getTitulo());
        System.out.println("Descripcion: " + lp.getDescripcion());
        System.out.println("Nivel de dificultad: " + lp.getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : lp.getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + lp.getDuracion());
        System.out.println("Rating: " + lp.getRating());
    }
}
