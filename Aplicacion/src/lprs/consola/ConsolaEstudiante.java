package lprs.consola;

import java.util.List;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.CentralPersistencia;
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
                "Salir" };

        mostrarOpciones(3, opciones);
        int opcion = lectura.nextInt();

        if (opcion == 1) {
            mostrarLearningPaths();
            mostrarConsolaEstudiante();
        } else if (opcion == 2) {
            String id = escogerLearningPath();
            estudiante.inscribirLearningPath(id);
            mostrarConsolaEstudiante();
        } else if (opcion == 3) {
            
        } else if (opcion==4) {
        	System.out.println("Hasta luego!");
            CentralPersistencia cp = new CentralPersistencia(lprsActual);
            cp.guardarDatos();
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
        else {
        	for (int i =1; i<learningPathsDisponibles.size(); i++) {
        		System.out.println(Integer.toString(i)+". "+ learningPathsDisponibles.get(i).getTitulo());
        	}
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

    public void escogerAvance() {
    String id =mostrarLearningPaths();
    Avance avance =estudiante.getAvance(id);
    System.out.println(avance.getTasaExito());
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
