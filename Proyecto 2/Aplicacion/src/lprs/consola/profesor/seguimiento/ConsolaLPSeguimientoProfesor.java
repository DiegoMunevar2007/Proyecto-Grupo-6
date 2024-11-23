package lprs.consola.profesor.seguimiento;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.*;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.Avance;
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
        boolean continuar = true;
        while (continuar) {
            String[] opciones = { "Ver las actividades de un estudiante", "Ver los estudiantes inscritos en un learning path", "Salir" };
            consolaProfesor.mostrarOpciones(opciones.length, opciones);
            int opcion = consolaProfesor.pedirInt("Seleccione una opción: ");
            if (opcion == 1) {
                verActividadesEstudiante();
            } else if (opcion == 2) {
                verEstudiantesInscritos();
            } else if (opcion == opciones.length) {
                System.out.println("Hasta luego!");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }
    }

    public void mostrarAvanceActividad(Actividad actividad, ActividadRealizable actividadRealizable) {
        {
            System.out.println("Actividad: " + actividad.getTitulo());
            System.out.println("Descripción: " + actividad.getDescripcion());
            System.out.println("Objetivo: " + actividad.getObjetivo());
            if (actividadRealizable instanceof QuizRealizable) {
                mostrarAvanceQuiz((QuizRealizable) actividadRealizable);
                System.out.println("Calificación: " + ((QuizRealizable) actividadRealizable).getCalificacion());
            } else if (actividadRealizable instanceof TareaRealizable) {
                mostrarAvanceTarea((TareaRealizable) actividadRealizable);
            } else if (actividadRealizable instanceof ExamenRealizable) {
                mostrarAvanceExamen((ExamenRealizable) actividadRealizable);
                System.out.println("Estado: " + actividadRealizable.getEstado());
            } else if (actividadRealizable instanceof EncuestaRealizable) {
                mostrarAvanceEncuesta((EncuestaRealizable) actividadRealizable);
            } else {
                System.out.println("No hay información adicional.");
            }
        }
    }
    public void mostrarAvanceQuiz(QuizRealizable quizRealizable) {
        System.out.println("Preguntas: ");
        ArrayList<PreguntaCerradaRealizable> preguntas = quizRealizable.getPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta escogida: " + preguntas.get(i).getOpcionEscogida().getOpcion());
        }
    }
    public void mostrarAvanceTarea(TareaRealizable tareaRealizable) {
        System.out.println("Estado: " + tareaRealizable.getEstado());
    }
    public void mostrarAvanceExamen(ExamenRealizable examenRealizable) {
        System.out.println("Preguntas realizadas: ");
        ArrayList<PreguntaAbiertaRealizable> preguntas = examenRealizable.getPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta: " + preguntas.get(i).getRespuesta());
        }
    }
    public void mostrarAvanceEncuesta(EncuestaRealizable encuestaRealizable) {
        System.out.println("Preguntas realizadas: ");
        ArrayList<PreguntaAbiertaRealizable> preguntas = encuestaRealizable.getPreguntasRealizadas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta: " + preguntas.get(i).getRespuesta());
        }
    }




    public void verActividadesEstudiante(){
        LearningPath lp = pedirLearningPath(consolaProfesor.getProfesor());
        if (lp == null) {
            return;
        }
        ArrayList<Estudiante> listaEstudiantes = lp.getEstudiantesInscritos();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            System.out.println(i+1 + ". " + listaEstudiantes.get(i).getUsuario());
        }
        int opcion = consolaProfesor.pedirInt("Seleccione el estudiante que desea ver: ");
        Estudiante estudiante = listaEstudiantes.get(opcion-1);
        System.out.println("Actividades realizadas de " + estudiante.getUsuario());
        ArrayList<Actividad> actividadesRealizadas = estudiante.getAvance(lp.getID()).getActividadesCompletadasLista();
        for (int i = 0; i < actividadesRealizadas.size(); i++) {
            System.out.println(i + ". " + actividadesRealizadas.get(i).getTitulo());
        }
        Actividad actividad = actividadesRealizadas.get(consolaProfesor.pedirInt("Seleccione la actividad que desea ver: "));
        System.out.println("¿Que desea hacer?");
        boolean continuar = true;
        while (continuar) {
            String[] opciones = { "Ver entrega del estudiante", "Salir" };
            consolaProfesor.mostrarOpciones(opciones.length, opciones);
            int opcion2 = consolaProfesor.pedirInt("Seleccione una opción: ");
            if (opcion2 == 1) {
                mostrarAvanceActividad(actividad, estudiante.getAvance(lp.getID()).getActividadesCompletadas().get(actividad));
            } else if (opcion2 == 2) {
                continuar = false;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }
    }

    public void verEstudiantesInscritos(){
        LearningPath lp = pedirLearningPath(consolaProfesor.getProfesor());
        if (lp == null) {
            return;
        }
        ArrayList<Estudiante> listaEstudiantes = lp.getEstudiantesInscritos();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            System.out.println(i+1 + ". " + listaEstudiantes.get(i).getUsuario());
        }
    }

    public LearningPath pedirLearningPath(Profesor profesor){
        ArrayList<LearningPath> learningPaths = new ArrayList<>(profesor.getLearningPathsCreados());
        if (learningPaths.isEmpty()) {
            System.out.println("No tiene learning paths creados.");
            return null;
        }
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i+1 + ". " + learningPaths.get(i).getTitulo());
        }
        int opcion = consolaProfesor.pedirInt("Seleccione el learning path que desea ver: ");
        return learningPaths.get(opcion-1);
    }
}
