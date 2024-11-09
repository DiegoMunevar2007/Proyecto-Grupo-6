package lprs.consola;

import java.util.HashMap;
import java.util.List;

import lprs.logica.contenido.Actividad;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.Avance;
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
        String[] opciones = { "Ver mis Leaning Paths", "Inscribir un learning Path", "Ver mi avance",
                "Realizar una actividad",
                "Salir" };

        mostrarOpciones(5, opciones);
        int opcion = lectura.nextInt();

        if (opcion == 1) {
            mostrarLearningPaths();
            mostrarConsolaEstudiante();
        } else if (opcion == 2) {
            String id = escogerLearningPath();
            estudiante.inscribirLearningPath(id);
            mostrarConsolaEstudiante();
        } else if (opcion == 3) {

            mostrarConsolaEstudiante();
        } else if (opcion == 4) {
            Actividad actividad = escogerActividad();
            RealizarActividad(actividad);
            mostrarConsolaEstudiante();
        } else if (opcion == 5) {
            System.out.println("Hasta luego!");
            return;
        }

        else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaEstudiante();
        }
    }

    public String mostrarLearningPaths() {
        List<LearningPath> learningPathsDisponibles = estudiante.getLearningPathsInscritos();
        if (learningPathsDisponibles.isEmpty()) {
            System.out.println("No tienes learning paths inscritos");
            mostrarConsolaEstudiante();
            return null;
        }
        for (int i = 0; i < learningPathsDisponibles.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + learningPathsDisponibles.get(i).getTitulo());
        }
        System.out.println("Seleccione un Learning Path: ");
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarLearningPaths();
        }
        LearningPath lP = learningPathsDisponibles.get(opcion - 1);
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("Titulo: " + lP.getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + lP.getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : lP.getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + lP.getDuracion());
        System.out.println("Rating: " + lP.getRating());
        return lP.getID();
    }

    public Actividad escogerActividad() {
        String ID = mostrarLearningPaths();
        LearningPath lP = estudiante.getLprsActual().getManejadorLP().getLearningPath(ID);
        List<Actividad> actividades = lP.getActividades();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en este Learning Path.");
            return null;
        }
        System.out.println("Seleccione una actividad: ");
        for (int i = 0; i < actividades.size(); i++) {
            System.out.println(i + 1 + ". " + actividades.get(i).getTitulo());
        }
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > actividades.size()) {
            System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
            return escogerActividad();
        }
        return actividades.get(opcion - 1);
    }

    public void RealizarActividad(Actividad actividad) {
        actividad.crearActividadRealizable(estudiante).realizarActividad();
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
