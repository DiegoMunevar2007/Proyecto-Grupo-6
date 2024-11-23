package lprs.consola.profesor.seguimiento;

import lprs.exceptions.EstadoException;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.*;

import java.util.ArrayList;

public class ConsolaActividadSeguimientoProfesor {
    private ConsolaProfesorSeguimiento consolaProfesor;

    public ConsolaActividadSeguimientoProfesor(ConsolaProfesorSeguimiento consolaProfesor) {
        this.consolaProfesor = consolaProfesor;
    }

    public void calificarActividadesPendientes() {
        ArrayList<ActividadRealizable> actividadesPendientes = consolaProfesor.getProfesor().getActividadesPendientes();
        ActividadRealizable actividad = null;
        for (int i = 0; i < actividadesPendientes.size(); i++) {
            actividad = actividadesPendientes.get(i);
            System.out.println(i + 1 + ". " + actividad.getActividadBase().getTitulo());
        }
        int opcion = consolaProfesor.pedirInt("Seleccione la actividad que desea calificar: ");
        if (opcion < 1 || opcion >= actividadesPendientes.size()) {
            System.out.println("Opción no válida.");
            return;
        }
        actividad = actividadesPendientes.get(opcion - 1);
        if (actividad instanceof EncuestaRealizable) {
            calificarEncuesta((EncuestaRealizable) actividad);
        } else if (actividad instanceof ExamenRealizable) {
            calificarExamen((ExamenRealizable) actividad);
        } else if (actividad instanceof QuizRealizable) {
            calificarQuiz((QuizRealizable) actividad);
        } else if (actividad instanceof TareaRealizable) {
            calificarTarea((TareaRealizable) actividad);
        } else if (actividad instanceof RecursoRealizable) {
            mostrarRecurso((RecursoRealizable) actividad);
        }
    }

    private void calificarEncuesta(EncuestaRealizable encuesta) {
        System.out.println("Información de la encuesta:");
        System.out.println("Preguntas: " + encuesta.getPreguntasRealizadas().size());
        System.out.println("Titulo: " + encuesta.getActividadBase().getTitulo());
        System.out.println("Estudiante: " + encuesta.getEstudiante().getUsuario());
        System.out.println("Estado: " + encuesta.getEstado());
        System.out.println("Contenido de la encuesta:");
        for (int i = 0; i < encuesta.getPreguntasRealizadas().size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = encuesta.getPreguntasRealizadas().get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            System.out.println("Pregunta " + i+1 + ": " + preguntaBase.getEnunciado());
            System.out.println("Respuesta: " + preguntaRealizada.getRespuesta());
        }
    }

    private void calificarExamen(ExamenRealizable examen) {
        System.out.println("Información del examen:");
        System.out.println("Preguntas: " + examen.getPreguntasRealizadas().size());
        System.out.println("Titulo: " + examen.getActividadBase().getTitulo());
        System.out.println("Estudiante: " + examen.getEstudiante().getUsuario());
        System.out.println("Estado: " + examen.getEstado());
        System.out.println("Contenido del examen:");
        for (int i = 0; i < examen.getPreguntasRealizadas().size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = examen.getPreguntasRealizadas().get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            System.out.println("Pregunta " + (i + 1) + ": " + preguntaBase.getEnunciado());
            System.out.println("Respuesta: " + preguntaRealizada.getRespuesta());
        }
        int opcion = consolaProfesor.pedirInt("Desea calificar el examen como exitoso o no exitoso? (1. Exitoso, 2. No exitoso) ");
        try {
            if (opcion == 1) {
                examen.setEstado("Exitoso");
            } else if (opcion == 2) {
                examen.setEstado("No exitoso");
            }
        } catch (EstadoException e) {
            e.printStackTrace();
        }
        examen.getActividadBase().getLearningPathAsignado().getProfesorCreador().actividadCalificada(examen);
    }

    private void calificarQuiz(QuizRealizable quiz) {
        System.out.println("La informacion del quiz es la siguiente: ");
        System.out.println("Titulo del quiz: " + quiz.getActividadBase().getTitulo());
        System.out.println("Descripcion del quiz: " + quiz.getActividadBase().getDescripcion());
        System.out.println("Estudiante: " + quiz.getEstudiante().getUsuario());
        System.out.println("Calificacion: " + quiz.getCalificacion() + "%");
        System.out.println("Preguntas correctas: " + quiz.getCorrectas());
        System.out.println("Desea ver las respuestas del estudiante? (S/N)");
        String respuesta = System.console().readLine();
        if (respuesta.equalsIgnoreCase("S")) {
            for (PreguntaCerradaRealizable pregunta : quiz.getPreguntas()) {
                System.out.println(pregunta.getPreguntaBase().getEnunciado());
                System.out.println("Respuesta correcta: " + pregunta.getPreguntaBase().getCorrecta().getOpcion());
                System.out.println("Respuesta del estudiante: " + pregunta.getOpcionEscogida().getOpcion());
            }
        }
    }

    private void calificarTarea(TareaRealizable tarea) {
        System.out.println("Calificacion de la tarea: ");
        System.out.println("Estudiante: " + tarea.getEstudiante().getUsuario());
        System.out.println("Tarea: " + tarea.getActividadBase().getTitulo());
        System.out.println("Estado de la tarea: " + tarea.getEstado());
        System.out.println("1. No exitoso");
        System.out.println("2. Exitoso");
        int opcion = consolaProfesor.pedirInt("Seleccione el estado de la tarea: ");
        try {
            if (opcion == 1) {
                tarea.setEstado("No exitoso");
            } else if (opcion == 2) {
                tarea.setEstado("Exitoso");
            }
        } catch (EstadoException e) {
            e.printStackTrace();
        }
    }

    private void mostrarRecurso(RecursoRealizable recurso) {
        System.out.println("La informacion del recurso es la siguiente: ");
        System.out.println("Titulo del recurso: " + recurso.getActividadBase().getTitulo());
        System.out.println("Descripcion del recurso: " + recurso.getActividadBase().getDescripcion());
        System.out.println("Estudiante: " + recurso.getEstudiante().getUsuario());
        System.out.println("Esta actividad no se puede calificar.");
    }
}