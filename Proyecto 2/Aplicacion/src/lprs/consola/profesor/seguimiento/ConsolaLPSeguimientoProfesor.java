package lprs.consola.profesor.seguimiento;

import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.contenido.Actividad;
import java.util.ArrayList;
import java.util.Collection;

public class ConsolaLPSeguimientoProfesor {
    private final ConsolaProfesorSeguimiento consolaProfesor;

    public ConsolaLPSeguimientoProfesor(ConsolaProfesorSeguimiento consolaProfesor){
        this.consolaProfesor = consolaProfesor;
    }

    public void mostrarConsolaLP(){
        String[] opciones = { "Ver las actividades de un estudiante", "Ver los estudiantes inscritos en un learning path", "Salir" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion = consolaProfesor.getLectura().nextInt();
        if (opcion == 1) {
            verActividadesEstudiante();
        } else if (opcion == 2) {
            verEstudiantesInscritos();
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaLP();
        }
    }
    public void verActividadesEstudiante(){
        LearningPath lp = pedirLearningPath(consolaProfesor.getProfesor());
        ArrayList<Estudiante> listaEstudiantes = lp.getEstudiantesInscritos();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            System.out.println(i + ". " + listaEstudiantes.get(i).getUsuario());
        }
        System.out.println("Seleccione el estudiante que desea ver: ");
        int opcion = consolaProfesor.getLectura().nextInt();
        Estudiante estudiante = listaEstudiantes.get(opcion);
        System.out.println("Actividades realizadas de " + estudiante.getUsuario());
        ArrayList<Actividad> actividadesRealizadas =estudiante.getAvance(lp.getID()).getActividadesCompletadasLista();
        for (int i = 0; i < actividadesRealizadas.size(); i++) {
            System.out.println(i + ". " + actividadesRealizadas.get(i).getTitulo());
        }
        System.out.println("Seleccione la actividad que desea ver: ");
        Actividad actividad = actividadesRealizadas.get(consolaProfesor.getLectura().nextInt());
        consolaProfesor.getLectura().nextLine();
        System.out.println("¿Que desea hacer?");
        String[] opciones = { "Ver detalles de la actividad", "Ver entrega del estudiante", "Salir" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion2 = consolaProfesor.getLectura().nextInt();

    }
    public void verEstudiantesInscritos(){
        LearningPath lp = pedirLearningPath(consolaProfesor.getProfesor());
        ArrayList<Estudiante> listaEstudiantes = lp.getEstudiantesInscritos();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            System.out.println(i + ". " + listaEstudiantes.get(i).getUsuario());
        }
    }
    public LearningPath pedirLearningPath(Profesor profesor){
        ArrayList<LearningPath> learningPaths = new ArrayList<>(profesor.getLearningPathsCreados());
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i+1 + ". " + learningPaths.get(i).getTitulo());
        }
        System.out.println("Seleccione el learning path que desea ver: ");
        int opcion = consolaProfesor.getLectura().nextInt();
        return learningPaths.get(opcion-1);
    }

}
