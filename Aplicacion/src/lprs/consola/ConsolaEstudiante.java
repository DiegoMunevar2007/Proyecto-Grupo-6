package lprs.consola;

import java.util.List;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaEstudiante extends ConsolaPrincipal {
    Estudiante estudiante;

    public ConsolaEstudiante(LPRS lprsActual, Estudiante estudiante) {
        super(lprsActual);
        this.estudiante = estudiante;
    }

    public void mostrarConsolaEstudiante() {
        System.out.println("Bienvenido " + estudiante.getUsuario());
        String[] opciones = { "Ver mis Leaning Paths", "Inscribir un learning Path",
                "Salir" };

        mostrarOpciones(3, opciones);
        int opcion = lectura.nextInt();

        if (opcion == 1) {
            escogerLearningPath();
        } else if (opcion == 2) {
            mostrarLearningPathsDisponibles();
            estudiante.inscribirLearningPath(null);
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaEstudiante();
        }
    }

    public void mostrarLearningPaths() {
        List<LearningPath> learningPathsEstudiante = estudiante.getLearningPathsInscritos();
        if (learningPathsEstudiante.isEmpty()) {
            System.out.println("No tienes Learning Paths inscritos.");
            mostrarConsolaEstudiante();
            return;
        }
        for (LearningPath lP : learningPathsEstudiante) {
            System.out.println(lP.getTitulo());
        }
    }

    public String escogerLearningPath() {
        List<LearningPath> learningPathsDisponibles = lprsActual.getManejadorLP().learningPathsDisponibles();
        mostrarLearningPathsDisponibles();
        if (learningPathsDisponibles.isEmpty()) {
            mostrarConsolaEstudiante();
            return null;
        }
        System.out.println("Seleccione un Learning Path: ");
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            return escogerLearningPath();
        }
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("Titulo: " + learningPathsDisponibles.get(opcion - 1).getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + learningPathsDisponibles.get(opcion - 1).getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : learningPathsDisponibles.get(opcion - 1).getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + learningPathsDisponibles.get(opcion - 1).getDuracion());
        System.out.println("Rating: " + learningPathsDisponibles.get(opcion - 1).getRating());
        System.out.println("Desea inscribirse en este Learning Path? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equals("s")) {
            return learningPathsDisponibles.get(opcion - 1).getID();
        } else {
            return null;
        }
    }

}
